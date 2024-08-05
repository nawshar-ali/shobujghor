import boto3
import json
import subprocess

DYNAMO_HOST = "http://localhost:7466"
SQS_HOST = "http://localhost:7466"

DEAD_LETTER_QUEUES = [
    "dev-order-dlq.fifo",
    "dev-notification-dlq.fifo",
    "dev-order-dlq.fifo"
]

QUEUES = [
    "dev-order-queue.fifo",
    "dev-notification-queue.fifo",
    "dev-order-queue.fifo"
]

dynamodb = boto3.resource("dynamodb", endpoint_url=DYNAMO_HOST)
sqs = boto3.client(
    "sqs",
    region_name="us-east-1",
    aws_access_key_id="",
    aws_secret_access_key="",
    endpoint_url=SQS_HOST,
)

def execute_shell_cmd(sh_script):
    print(f"====== Executing {sh_script} ======")
    subprocess.call(["sh", f"./{sh_script}"])

def insert_data_in_table(table_name, file_name):
    table = dynamodb.Table(table_name)
    with open(file_name) as key_val:
        key_val = json.load(key_val)
        for kv in key_val:
            r = table.put_item(Item=kv)
    print(f"{table_name} is loaded")

def delete_all_queues():
    print("===== Deleting all queues =====")
    for q_name in DEAD_LETTER_QUEUES + QUEUES:
        try:
            sqs.delete_queue(QueueUrl=f"{SQS_HOST}/queue/{q_name}")
        except:
            pass

def create_all_queues():
    print("===== Creating SQS queues =====")
    for q in DEAD_LETTER_QUEUES:
        dlq = sqs.create_queue(
            QueueName=q,
            Attributes={
                "DelaySeconds": "0",
                "MessageRetentionPeriod": "86400",
                "VisibilityTimeout": "90",
                "FifoQueue": "true",
            },
        )

        dlq_arn = sqs.get_queue_attributes(
            QueueUrl=dlq["QueueUrl"], AttributeNames=["QueueArn"]
        )

        index = DEAD_LETTER_QUEUES.index(q)
        main_q = sqs.create_queue(
            QueueName=QUEUES[index],
            Attributes={
                "DelaySeconds": "0",
                "MessageRetentionPeriod": "86400",
                "VisibilityTimeout": "60",
                "FifoQueue": "true",
                                "RedrivePolicy": json.dumps(
                                    {
                                        "deadLetterTargetArn": dlq_arn["Attributes"]["QueueArn"],
                                        "maxReceiveCount": "3",
                                    }
                                ),
                                "ContentBasedDeduplication": "true",
            },
        )
        print(f"Created queue : {main_q['QueueUrl']}")
        print(f"Created queue : {dlq['QueueUrl']}")

if __name__ == "__main__":
    execute_shell_cmd("makeSsmParameter.sh")
    execute_shell_cmd("create_table.sh")

    insert_data_in_table("dev-category-info", "data/category.json")
    insert_data_in_table("dev-user-info", "data/user.json")
    insert_data_in_table("dev-error-info", "data/error.json")
    insert_data_in_table("dev-item-info", "data/item.json")

    delete_all_queues()
    create_all_queues()