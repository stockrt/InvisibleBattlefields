#!/bin/bash

source /config.sh

if [[ "$1" == "registry" ]]
then
    port=9000
elif [[ "$1" == "ibf" ]]
then
    port=9001
else
    # Comando simples
    cd "$REPOSITORY_HOME/InvisibleBattlefields" && "$HOME/play-$PLAY_VERSION/play" $@
    exit
fi

# Core Server
coreserver="$1"
pidfile="$REPOSITORY_HOME/InvisibleBattlefields/${coreserver}.pid"
shift

pkill -f "DcoreServer=$coreserver"
sleep 2
rm -f "$pidfile"
cd "$REPOSITORY_HOME/InvisibleBattlefields" && "$HOME/play-$PLAY_VERSION/play" -Dhttp.port=$port -Dpidfile.path="$pidfile" -DcoreServer="$coreserver" $@
