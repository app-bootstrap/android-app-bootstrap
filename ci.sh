#!/bin/sh

gradle clean build -PdisablePreDex --stacktrace

npm i npm@6 -g --registry=https://registry.npm.taobao.org

npm i

npm run reliable
