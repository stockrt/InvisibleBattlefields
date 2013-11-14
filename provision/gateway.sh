#!/bin/bash

source /config.sh
pkill -f "$REPOSITORY_HOME/InvisibleBattlefields/lib/contextnet.jar 0.0.0.0 5500"
sleep 2
java -Djava.library.path=$LIBRARY_PATH -jar $REPOSITORY_HOME/InvisibleBattlefields/lib/contextnet.jar 0.0.0.0 5500
