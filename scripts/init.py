import boto3
import json
import subprocess

DYNAMO_HOST = "http://localhost:7466"

dynamodb = boto3.resource("dynamodb", endpoint_url=DYNAMO_HOST)

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

if __name__ == "__main__":
    execute_shell_cmd("create_table.sh")

    insert_data_in_table("dev-category-info", "data/category.json")
    insert_data_in_table("dev-user-info", "data/user.json")
    insert_data_in_table("dev-error-info", "data/error.json")