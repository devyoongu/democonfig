#!/bin/bash

targets=("robiLogLevel" "appName" "chubbUrl" "chubbPort")

robiLogLevel=warn
appName=guro
chubbUrl=http://chubb.com
chubbPort=20201

target="target.txt"

while read line;
#arr=()
do
  if [[ "$line" == *private* ]]; then
    temp=`echo $line | cut -d '{' -f2`
    word=`echo $temp | cut -d '}' -f1`
#    arr+=(`echo $word`)
#    echo $arr
echo $word

#    for rt in "${targets[@]}"
#    do
#      if [ $word == $rt ]; then
#          echo "$word""$rt"
#          echo "$word" > $file
#          break
#      fi
#    done
  fi
done < test.txt
