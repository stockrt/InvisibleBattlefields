# 64 or 32 bits?
if [[ "$(uname -m)" == "x86_64" ]]
then
    LIB_ARCH="lib64"
    CORE_TARGET_ARCH="Linux_2.6_x86_64_gcc43"
    LIC_GREP=" CPU=x86_64 "
else
    LIB_ARCH="lib"
    CORE_TARGET_ARCH="Linux_2.6_x86_gcc43"
    LIC_GREP=" CPU=x86 "
fi

# Home
export COREDX_HOME="/opt/coredx/coredx-$CORE_VERSION-Evaluation"
export JAVA_HOME="/opt/java"
export LIBRARY_PATH="$COREDX_HOME/target/$CORE_TARGET_ARCH/lib:$REGISTRY_HOME/provision/$LIB_ARCH:$REGISTRY_HOME/Registry/lib"
export PATH="$JAVA_HOME/bin:$PATH"

# Play
export PLAY_OPTS="-Xms256m -Xmx256m -Djava.library.path=$LIBRARY_PATH"

# Core
if [[ -d "$REGISTRY_HOME/provision" ]]
then
    export LD_PRELOAD="$REGISTRY_HOME/provision/$LIB_ARCH/libmacspoof.so.1.0.1"
    export TLM_LICENSE="$REGISTRY_HOME/provision/coredx/coredx.lic"
    mac="$(grep "$LIC_GREP" "$TLM_LICENSE" | egrep -o 'HOSTID=[[:alnum:]]* ' | cut -d = -f 2)"
    export MAC_ADDRESS="${mac:0:2}:${mac:2:2}:${mac:4:2}:${mac:6:2}:${mac:8:2}:${mac:10:2}"
fi
