#!/bin/bash

source /config.sh

if [[ "$1" == "registry" ]]
then
    coreserver="$1"
    port=9000
    shift
elif [[ "$1" == "ibf" ]]
then
    coreserver="$1"
    port=9001
    shift
else
    cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play $@
    exit
fi

pidfile="$REPOSITORY_HOME/InvisibleBattlefields/${coreserver}.pid"
pkill -f "DcoreServer=$coreserver"
sleep 2
rm -f "$pidfile"
cd "$REPOSITORY_HOME/InvisibleBattlefields" && "$HOME/play-$PLAY_VERSION/play" -Dhttp.port=$port -Dpidfile.path="$pidfile" -DcoreServer="$coreserver" $@
