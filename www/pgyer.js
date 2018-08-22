var exec = require('cordova/exec');

exports.showFeedback= function (arg0, success, error) {
    exec(success, error, 'PgyerPlugin', 'showFeedback', [arg0]);
};

exports.crashRegister= function (arg0, success, error) {
    exec(success, error, 'PgyerPlugin', 'crashRegister', [arg0]);
};

exports.checkUpdate= function (arg0, success, error) {
    exec(success, error, 'PgyerPlugin', 'checkUpdate', [arg0]);
};
