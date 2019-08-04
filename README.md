# android-app-bootstrap

[![Build Status](https://img.shields.io/travis/app-boostrap/android-app-bootstrap.svg?style=flat-square)](https://travis-ci.org/app-boostrap/android-app-bootstrap)

A starting tutorial for Android application.

[native-in-practice](//xudafeng.github.io/slide/archives/native-in-practice)

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

[collect coverage data](//github.com/app-boostrap/android-app-bootstrap/blob/master/android_app_bootstrap/src/main/java/com/github/android_app_bootstrap/common/Utils.java#L132)

## Output

npmcdn.com: [android_app_bootstrap-debug.apk](//npmcdn.com/android-app-bootstrap@latest/android_app_bootstrap/build/outputs/apk/android_app_bootstrap-debug.apk)

<!-- GITCONTRIBUTOR_START -->

## Contributors

|[<img src="https://avatars1.githubusercontent.com/u/1011681?v=4" width="100px;"/><br/><sub><b>xudafeng</b></sub>](https://github.com/xudafeng)<br/>|[<img src="https://avatars3.githubusercontent.com/u/21149374?v=4" width="100px;"/><br/><sub><b>hanqiongly</b></sub>](https://github.com/hanqiongly)<br/>|[<img src="https://avatars3.githubusercontent.com/u/1209810?v=4" width="100px;"/><br/><sub><b>paradite</b></sub>](https://github.com/paradite)<br/>|
| :---: | :---: | :---: |


This project follows the git-contributor [spec](https://github.com/xudafeng/git-contributor), auto updated at `Sun Aug 04 2019 12:06:41 GMT+0800`.

<!-- GITCONTRIBUTOR_END -->

## License

The MIT License (MIT)
