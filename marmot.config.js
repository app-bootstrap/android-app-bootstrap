'use strict';

const fs = require('fs');
const path = require('path');
const helper = require('marmot-cli/lib/helper');

const pkg = require('./package');

const appId = pkg.name.replace(/-/g, '_');

const dotGradle = path.join(__dirname, appId, 'build.gradle');
const dotGradleContent = fs.readFileSync(dotGradle, 'utf8');

module.exports = async function () {
  const gradle = await helper
    .androidUtils
    .parseGradle(dotGradleContent);

  console.log(JSON.stringify(gradle, null, 2));
  const version = `${gradle.ext.buildVersionName}.${gradle.ext.buildVersionCode}`;
  const outputPath = `${appId}/build/outputs`;
  return {
    files: [
      `${outputPath}/`,
    ],
    packages: [
      {
        version,
        type: 'debug',
        path: `${outputPath}/apk/debug/${appId}-debug.apk`
      }
    ],
    testInfo: {
    },
    extraInfo: {
    }
  };
};

