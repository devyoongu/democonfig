#!/bin/bash

CUR_DIR="$( cd "$( dirname "$0" )" && pwd )"
source "$CUR_DIR/env/target_env.sh"

echo "read env.."
echo "RELEASE_TARGET>>"$RELEASE_TARGET

variables=("robiLogLevel" "appName" "chubbUrl" "chubbPort")
robiLogLevel="warn"
appName=$RELEASE_TARGET
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
