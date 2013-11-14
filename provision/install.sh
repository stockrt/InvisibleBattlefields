#!/bin/bash

if [[ "$HOSTNAME" != "ibf" && "$1" != "-f" ]]
then
    echo "You must run this script on the VM, not in your host."
    exit
fi

if [[ "$USER" != "root" ]]
then
    echo "You must run this script as root (I need to install some packages)."
    exit
fi

# Config
echo
echo "- Configuring..."
CORE_VERSION="3.5.16"
PLAY_VERSION="2.2.0"
REPOSITORY_HOME=${REPOSITORY_HOME:-"/vagrant"}
USER_APP=${USER_APP:-"vagrant"}
USER_HOME=${USER_HOME:-"/home/$USER_APP"}
echo "CORE_VERSION=\"$CORE_VERSION\""
echo "PLAY_VERSION=\"$PLAY_VERSION\""
echo "REPOSITORY_HOME=\"$REPOSITORY_HOME\""
echo "USER_APP=\"$USER_APP\""
echo "USER_HOME=\"$USER_HOME\""

# 64 or 32 bits?
if [[ "$(uname -m)" == "x86_64" ]]
then
    COREDX_PKG="coredx-$CORE_VERSION-Linux_2.6_x86_64_gcc43-Evaluation.tgz"
else
    COREDX_PKG="coredx-$CORE_VERSION-Linux_2.6_x86_gcc43-Evaluation.tgz"
fi

if [[ ! -d "$REPOSITORY_HOME" ]]
then
    echo "Home directory for the project was not found: $REPOSITORY_HOME" 1>&2
    exit
fi

if [[ ! -d "$USER_HOME" ]]
then
    echo "Home directory for the project user ($USER_APP) was not found: $USER_HOME" 1>&2
    exit
fi

echo "# Config" > /config.sh
echo "export CORE_VERSION=\"$CORE_VERSION\"" >> /config.sh
echo "export PLAY_VERSION=\"$PLAY_VERSION\"" >> /config.sh
echo "export REPOSITORY_HOME=\"$REPOSITORY_HOME\"" >> /config.sh
echo "export USER_APP=\"$USER_APP\"" >> /config.sh
echo "export USER_HOME=\"$USER_HOME\"" >> /config.sh
echo >> /config.sh
cat $REPOSITORY_HOME/provision/config.sh >> /config.sh
echo "- Done!"

# Packages
echo
echo "- Installing packages..."
DEBIAN_FRONTEND="noninteractive"
apt-get -f install
dpkg --configure -a
apt-get -y update
apt-get -y install build-essential linux-headers-$(uname -r)
apt-get -y install curl zip unzip
echo "- Done!"

# MySQL
echo
echo "- Installing MySQL..."
sudo debconf-set-selections <<< "mysql-server-<version> mysql-server/root_password password ibf"
sudo debconf-set-selections <<< "mysql-server-<version> mysql-server/root_password_again password ibf"
apt-get -y install mysql-server-5.5
mysql --password=ibf < $REPOSITORY_HOME/provision/sql/ibf_grant.sql
echo "- Done!"

# CoreDX
echo
echo "- Installing CoreDX..."
echo "$COREDX_PKG"
mkdir -p /opt/coredx
tar xzf $REPOSITORY_HOME/provision/coredx/$COREDX_PKG -C /opt/coredx/
echo "- Done!"

# Java
echo
echo "- Installing Java..."
$REPOSITORY_HOME/script/java-dl.sh >/dev/null 2>&1
chown -R ${USER_APP}: /opt/jdk*
echo "- Done!"

# Play
echo
echo "- Installing Play..."
cd $USER_HOME
wget -q -c http://downloads.typesafe.com/play/$PLAY_VERSION/play-${PLAY_VERSION}.zip
echo A | unzip -q play-${PLAY_VERSION}.zip >/dev/null 2>&1
chown -R ${USER_APP}: play*
echo "- Done!"

# Aliases
echo
echo "- Installing Aliases..."
grep -q "source /config.sh" $USER_HOME/.bashrc >/dev/null 2>&1 || \
echo "
# Config
source /config.sh" >> $USER_HOME/.bashrc
echo "- Done!"

# rc.local set
echo
echo "- Installing rc.local..."
cat $REPOSITORY_HOME/provision/rc.local > /etc/rc.local
chmod 755 /etc/rc.local
echo "- Done!"

# rc.local run
echo
echo -n "- Executing rc.local..."
/etc/rc.local

# Message
echo
echo "- Please wait until we run:
    - Gateway
    - Core and Web Servers
    - Database evolutions/migrations
Then try to access: http://ibf.vm"

# Wait
timeout=300
wtime=0
echo
echo "- Waiting daemons to start up for at most $timeout seconds..."
while [[ $wtime -lt $timeout ]]
do
    flag_ok=1
    netstat -anl | grep -q :5500 || flag_ok=0
    netstat -anl | grep -q :9000 || flag_ok=0

    if [[ $flag_ok -eq 1 ]]; then break; fi

    sleep 1
    wtime=$((wtime+1))
done

# Done
sleep 5 # Wait a few seconds due to bind process
echo
if [[ $flag_ok -eq 1 ]]
then
    echo "- Done!
You may now access: http://ibf.vm"
else
    echo "- Problem starting daemons. Please try to start by hand.
Use \"vagrant ssh\" and the aliases configured in the environment." 1>&2
fi
