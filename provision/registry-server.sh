#!/bin/bash

source /config.sh
pkill -f "$HOME/play-$PLAY_VERSION"
sleep 2
rm -f $REPOSITORY_HOME/Registry/RUNNING_PID
cd $REPOSITORY_HOME/Registry && $HOME/play-$PLAY_VERSION/play $@
