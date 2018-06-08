#!/bin/sh

gradle clean build -PdisablePreDex --stacktrace

npm i

npm run marmot
