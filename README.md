# android-app-bootstrap

[![Build Status](https://img.shields.io/travis/xudafeng/android-app-bootstrap.svg?style=flat-square)](https://travis-ci.org/xudafeng/android-app-bootstrap)

A starting tutorial for Android application.

[native-in-practice](//xudafeng.github.io/slide/archives/native-in-practice)

## Coverage

```bash
$ ./gradlew clean createDebugCoverageReport
```

## Linter

```bash
$ ./gradlew check
```

## DataHub

```bash
$ npm i macaca-datahub -g
$ datahub server -c ./macaca-datahub.config.js --verbose
```

## Coverage

```bash
$ npm i macaca-cli -g
$ macaca coverage -r java -f ./coverage.exec -c ./android_app_bootstrap/build/intermediates/classes/debug -s ./android_app_bootstrap/src/main/java --html ./reporter
```

[collect coverage data](//github.com/xudafeng/android-app-bootstrap/blob/master/android_app_bootstrap/src/main/java/com/github/android_app_bootstrap/common/Utils.java#L132)

## Output

npmcdn.com: [android_app_bootstrap-debug.apk](//npmcdn.com/android-app-bootstrap@latest/android_app_bootstrap/build/outputs/apk/android_app_bootstrap-debug.apk)

## License

The MIT License (MIT)
