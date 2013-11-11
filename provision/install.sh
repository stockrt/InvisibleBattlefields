#!/bin/bash

if [[ "$HOSTNAME" != "registry" && "$1" != "-f" ]]
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
echo "- Configurando sistema..."
CORE_VERSION="3.5.16"
PLAY_VERSION="2.2.0"
REGISTRY_HOME=${REGISTRY_HOME:-"/vagrant"}
USER_APP=${USER_APP:-"vagrant"}
USER_HOME=${USER_HOME:-"/home/$USER_APP"}
echo "CORE_VERSION=\"$CORE_VERSION\""
echo "PLAY_VERSION=\"$PLAY_VERSION\""
echo "REGISTRY_HOME=\"$REGISTRY_HOME\""
echo "USER_APP=\"$USER_APP\""
echo "USER_HOME=\"$USER_HOME\""

# 64 or 32 bits?
if [[ "$(uname -m)" == "x86_64" ]]
then
    COREDX_PKG="coredx-$CORE_VERSION-Linux_2.6_x86_64_gcc43-Evaluation.tgz"
else
    COREDX_PKG="coredx-$CORE_VERSION-Linux_2.6_x86_gcc43-Evaluation.tgz"
fi

if [[ ! -d "$REGISTRY_HOME" ]]
then
    echo "Diretório home do projeto não foi encontrado: $REGISTRY_HOME" 1>&2
    exit
fi

if [[ ! -d "$USER_HOME" ]]
then
    echo "Diretório home do usuário do projeto ($USER_APP) não foi encontrado: $USER_HOME" 1>&2
    exit
fi

echo "# Config" > /config.sh
echo "export CORE_VERSION=\"$CORE_VERSION\"" >> /config.sh
echo "export PLAY_VERSION=\"$PLAY_VERSION\"" >> /config.sh
echo "export REGISTRY_HOME=\"$REGISTRY_HOME\"" >> /config.sh
echo "export USER_APP=\"$USER_APP\"" >> /config.sh
echo "export USER_HOME=\"$USER_HOME\"" >> /config.sh
echo >> /config.sh
cat $REGISTRY_HOME/provision/config.sh >> /config.sh
echo "- Pronto!"

# Pacotes
echo
echo "- Instalando pacotes..."
DEBIAN_FRONTEND="noninteractive"
apt-get -f install
dpkg --configure -a
apt-get -y update
apt-get -y install build-essential linux-headers-$(uname -r)
apt-get -y install curl zip unzip
echo "- Pronto!"

# MySQL
echo
echo "- Instalando MySQL..."
sudo debconf-set-selections <<< "mysql-server-<version> mysql-server/root_password password registry"
sudo debconf-set-selections <<< "mysql-server-<version> mysql-server/root_password_again password registry"
apt-get -y install mysql-server-5.5
mysql --password=registry < $REGISTRY_HOME/provision/sql/registry_grant.sql
echo "- Pronto!"

# CoreDX
echo
echo "- Instalando CoreDX..."
echo "$COREDX_PKG"
mkdir -p /opt/coredx
tar xzf $REGISTRY_HOME/provision/coredx/$COREDX_PKG -C /opt/coredx/
echo "- Pronto!"

# Java
echo
echo "- Instalando Java..."
$REGISTRY_HOME/script/java-dl.sh >/dev/null 2>&1
chown -R ${USER_APP}: /opt/jdk*
echo "- Pronto!"

# Play
echo
echo "- Instalando Play..."
cd $USER_HOME
wget -q -c http://downloads.typesafe.com/play/$PLAY_VERSION/play-${PLAY_VERSION}.zip
echo A | unzip -q play-${PLAY_VERSION}.zip >/dev/null 2>&1
chown -R ${USER_APP}: play*
echo "- Pronto!"

# Aliases
echo
echo "- Instalando Aliases..."
cat $REGISTRY_HOME/provision/bashrc > $USER_HOME/.bashrc
echo "- Pronto!"

# rc.local set
echo
echo "- Instalando rc.local..."
cat $REGISTRY_HOME/provision/rc.local > /etc/rc.local
chmod 755 /etc/rc.local
echo "- Pronto!"

# rc.local run
echo
echo -n "- Executando rc.local..."
/etc/rc.local

# Message
echo
echo "- Aguarde enquanto são executados:
    - Gateway
    - Registry (Web Server e Core Server)
    - Migração do banco de dados
E então tente acessar: http://registry.vm"

# Wait
timeout=180
wtime=0
echo
echo "- Aguardando start dos processos por até $timeout segundos..."
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
sleep 5 # Aguardar alguns segundos devido ao bind
echo
if [[ $flag_ok -eq 1 ]]
then
    echo "- Pronto!
Já pode acessar: http://registry.vm"
else
    echo "- Problema no start dos processos. Por favor tente subir manualmente.
Utilize \"vagrant ssh\" e as aliases configurados no ambiente." 1>&2
fi
