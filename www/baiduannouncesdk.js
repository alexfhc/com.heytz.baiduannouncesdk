var exec = require('cordova/exec');

exports.init = function () {
  exec(null, null, "baiduannouncesdk", "init",
    []);
};

exports.start = function (success, error) {
  exec(success, error, "baiduannouncesdk", "start",
    []);
};

exports.stop = function () {
  exec(null, null, "baiduannouncesdk", "stop",
    []);
};
exports.announce = function (word) {
  exec(null, null, "baiduannouncesdk", "announce",
    [word]);
};
// exports.startPlay = function () {
//   exec(null, null, "AudioRecorder", "startPlay",
//     []);
// };
// exports.stopPlay = function () {
//   exec(null, null, "AudioRecorder", "stopPlay",
//     []);
// };