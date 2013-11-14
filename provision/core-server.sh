#!/bin/bash

source /config.sh

if [[ "$1" == "registry" ]]
then
    pkill -f "DcoreServer=registry"
    sleep 2
    rm -f $REPOSITORY_HOME/InvisibleBattlefields/registry.pid
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play -DcoreServer=registry -Dpidfile.path=$REPOSITORY_HOME/InvisibleBattlefields/registry.pid -Dhttp.port=9000 $@
elif [[ "$1" == "ibf" ]]
then
    pkill -f "DcoreServer=ibf"
    sleep 2
    rm -f $REPOSITORY_HOME/InvisibleBattlefields/ibf.pid
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play -DcoreServer=ibf -Dpidfile.path=$REPOSITORY_HOME/InvisibleBattlefields/ibf.pid -Dhttp.port=9001 $@
else
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play $@
fi
