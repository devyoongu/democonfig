#!/bin/bash

variables=("robiLogLevel" "appName" "chubbUrl" "chubbPort")

robiLogLevel="warn"
appName="guro"
chubbUrl="http://chubb.com"
chubbPort=20201

target="../src/main/java/com/config/demo/BuildConfig.java"

while read line;
do
  if [[ "$line" == *private* ]]; then
    temp=`echo $line | cut -d '{' -f2`
    word=`echo $temp | cut -d '}' -f1`
    front=`echo $line | cut -d '=' -f1`

    for rt in "${variables[@]}"
    do
      if [ $word == $rt ]; then
        re="^[0-9]+$"
        if [[ ${!word} =~ $re ]]; then
          echo $front"="${!word}";" >> ${target}
        else
          echo $front"="'"'${!word}'"'";" >> ${target}
        fi
      fi
    done
  else
    echo $line >> ${target}
  fi

done < temp.txt
