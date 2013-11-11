#!/bin/bash

if [[ "$HOSTNAME" == "registry" ]]
then
    echo "You must run this script on your host, not in the VM."
    exit 1
fi

if [[ "$USER" == "root" ]]
then
    echo "You must run this script as yourself (not as root)."
    exit 1
fi

PLAY_VERSION="2.2.0"

pushd $HOME
wget -c http://downloads.typesafe.com/play/$PLAY_VERSION/play-${PLAY_VERSION}.zip
echo A | unzip play-${PLAY_VERSION}.zip
popd

pushd ../Registry
echo
echo "- java version:"
java -version
echo
echo "- javac version:"
javac -version
echo
echo "- Preparing project to import into Eclipse."
echo "play clean-all"
$HOME/play-$PLAY_VERSION/play clean-all
echo "play eclipse"
$HOME/play-$PLAY_VERSION/play eclipse
echo
echo "- Done!"

echo
echo "#######################################################################"
echo "Ensure that you have Java version 7 installed.
    java -version
    javac -version

It is recommended that when importing the project to workspace you do not copy
(do not check the copy option) so any update from git can reflect immediately
to your Eclipse environment."
echo "#######################################################################"
echo
echo "- Path to be imported (without copying) to the Eclipse workspace:"
pwd
