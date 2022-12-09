#!/bin/bash

#while read A B C ETC
#do
#echo "${A}-${B}-${C}-${ETC}"
#done < test.txt
variables=()
while read line;
arr=()
do
  if [[ "$line" == *private* ]]; then
    temp=`echo $line | cut -d '{' -f2`
    word=`echo $temp | cut -d '}' -f1`
    arr+=(`echo $word`)
    echo $arr
  fi
done < test.txt