#!/bin/bash

source /config.sh
pkill -f "$REGISTRY_HOME/Registry/lib/contextnet.jar 0.0.0.0 5500"
sleep 2
java -Djava.library.path=$LIBRARY_PATH -jar $REGISTRY_HOME/Registry/lib/contextnet.jar 0.0.0.0 5500
