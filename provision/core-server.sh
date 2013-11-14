#!/bin/bash

source /config.sh
pkill -f "$HOME/play-$PLAY_VERSION"
sleep 2
rm -f $REPOSITORY_HOME/InvisibleBattlefields/RUNNING_PID
cd $REPOSITORY_HOME/InvisibleBattlefields && $HOME/play-$PLAY_VERSION/play $@
