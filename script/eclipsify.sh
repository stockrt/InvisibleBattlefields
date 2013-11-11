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
echo "- Versão do java:"
java -version
echo
echo "- Versão do javac:"
javac -version
echo
echo "- Preparando o projeto para importar no Eclipse."
echo "play clean-all"
$HOME/play-$PLAY_VERSION/play clean-all
echo "play eclipse"
$HOME/play-$PLAY_VERSION/play eclipse
echo
echo "- Pronto!"

echo
echo "#######################################################################"
echo "Certifique-se de ter um Java versão 7 instalado.
    java -version
    javac -version

Recomenda-se importar para o workspace sem copiar, assim os updates com git pull
ficam disponíveis de imediato no ambiente do Eclipse."
echo "#######################################################################"
echo
echo "- Path a ser importado (sem copiar) para o workspace do Eclispe:"
pwd
