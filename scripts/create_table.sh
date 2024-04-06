#!/usr/bin/env sh

export BASE_DIR=$PWD
for i in $(find ../* -name "table-*.json" );
    do
        echo "Creating table : "$i
        aws dynamodb create-table --cli-input-json file://$i --region "us-east-1" --endpoint-url http://dynamodb.host:7466 --no-cli-pager

done;