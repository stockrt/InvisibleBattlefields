#!/bin/bash

source /config.sh

if [[ "$1" == "registry" ]]
then
    pidfile="$REPOSITORY_HOME/InvisibleBattlefields/registry.pid"
    pkill -f "DcoreServer=registry"
    sleep 2
    rm -f $pidfile
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play -DcoreServer=registry -Dpidfile.path=$pidfile -Dhttp.port=9000 $@
elif [[ "$1" == "ibf" ]]
then
    pidfile="$REPOSITORY_HOME/InvisibleBattlefields/ibf.pid"
    pkill -f "DcoreServer=ibf"
    sleep 2
    rm -f $pidfile
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play -DcoreServer=ibf -Dpidfile.path=$pidfile -Dhttp.port=9001 $@
else
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play $@
fi
