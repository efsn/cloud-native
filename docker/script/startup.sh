#!/bin/bash

# remote debug
env=$SPRING_PROFILES_ACTIVE
if [[ env == 'dev' || env == 'qa' ]]; then
  export java_tool_options=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9000
fi

# add user conf



# default env conf
java -XX:Unlock

# run conf
