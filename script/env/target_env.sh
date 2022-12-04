RELEASE_TARGET="devel"
SUPPORTED_RELEASE_TARGETS=("hana" "guro" "hdcard" "chubb")

if [ $# -lt 1 ]; then
        echo "Invalid parameters."
        echo "1. enter release target: ${SUPPORTED_RELEASE_TARGETS[@]}"
        exit 1
fi

RELEASE_TARGET=$1

# check release target
PASS=false
for rt in ${SUPPORTED_RELEASE_TARGETS[@]};
do
#       echo "string compare, rt: $rt, target: $RELEASE_TARGET"
    if [ $RELEASE_TARGET == $rt ]; then
        PASS=true
        break
    fi
done
echo "release target: $RELEASE_TARGET, check: $PASS"
if ! $PASS; then
    echo "not supported release target: $RELEASE_TARGET"
    exit 1
fi
