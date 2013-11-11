#!/bin/bash

JAVA_VER="7"
JAVA_REL="0"
JAVA_UPD="45"
JAVA_BLD="18"

#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jdk-7u45-linux-x64.tar.gz
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jdk-7u45-linux-i586.tar.gz
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jre-7u45-linux-x64.tar.gz
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jre-7u45-linux-i586.tar.gz
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jdk-7u45-macosx-x64.dmg
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jre-7u45-macosx-x64.tar.gz
#http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jre-7u45-macosx-x64.dmg

if [[ "$USER" != "root" ]]
then
    echo "You must run this script as root (I need to install some packages)."
    exit 1
fi

# Linux or MacOS?
case "$OSTYPE" in
    linux*)
        HOST_OS="linux"
        JAVA_EXT=".tar.gz"
    ;;
    darwin*)
        HOST_OS="macosx"
        JAVA_EXT=".dmg"
    ;;
esac

# 64 or 32 bits?
if [[ "$(uname -m)" == "x86_64" ]]
then
    HOST_ARCH="x64"
else
    HOST_ARCH="i586"
fi

JAVA_BINS="jdk-${JAVA_VER}u${JAVA_UPD}-${HOST_OS}-${HOST_ARCH}${JAVA_EXT}"
pushd /opt
test ! -f $JAVA_BINS && \
wget --no-check-certificate --header "Cookie: gpw_e24=a" "http://download.oracle.com/otn-pub/java/jdk/${JAVA_VER}u${JAVA_UPD}-b${JAVA_BLD}/${JAVA_BINS}"

if [[ "$JAVA_EXT" == ".tar.gz" ]]
then
    tar xzvf $JAVA_BINS
    ln -snf "jdk1.${JAVA_VER}.${JAVA_REL}_${JAVA_UPD}" java
    echo
    echo '- Configure your environment variables:
    export JAVA_HOME="/opt/java"
    export PATH="$JAVA_HOME/bin:$PATH"
    '
else
    open $JAVA_BINS
fi
echo "- Done!"
