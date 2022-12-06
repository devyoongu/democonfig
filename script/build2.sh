#!/bin/bash

CUR_DIR=$(readlink -f "$(dirname "$0")")

#source "$CUR_DIR/env/target_env.sh"

cd "$CUR_DIR/.."

./gradlew clean

./gradlew cmdLineJavaExec
# -Penv=$RELEASE_TARGET

#echo $RELEASE_TARGET
cd $CUR_DIR
