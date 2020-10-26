#!/bin/bash
#example1.com,example2.com,example3.com
hosts=$1
IFS=',' read -r -a my_hosts_arr <<< "$hosts"
MY_HOSTS_COUNT=${#my_hosts_arr[@]}
for myhost in "${my_hosts_arr[@]}"
do
  echo $myhost
done
