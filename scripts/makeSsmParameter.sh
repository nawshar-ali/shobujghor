#!/usr/bin/env sh

#module - notification
aws ssm --endpoint-url http://localhost:7466 put-parameter \
--name "/dev/email/username" \
--value "nowsher.ali395@gmail.com" \
--type "String" \
--overwrite

aws ssm --endpoint-url http://localhost:7466 put-parameter \
--name "/dev/email/password" \
--value "cnzpcjkzmglzjvmj" \
--type "String" \
--overwrite