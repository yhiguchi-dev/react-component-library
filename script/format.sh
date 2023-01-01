#!/bin/bash

set -eux

cd "$(dirname "$0")/../"

cd application

./gradlew spotlessApply

find ./servlet -type f -name "*.jsp" | xargs -I FILE mv FILE FILE.html

pnpm fix

find ./servlet -type f -name "*.jsp.html" | xargs -I FILE bash -c 'mv FILE $(echo $(dirname FILE)/$(basename FILE .html))'
