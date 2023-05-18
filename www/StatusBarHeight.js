var exec = require('cordova/exec');

exports.getStatusBarHeight = function(success, error) {
    exec(success, error, 'StatusBarHeight', 'getStatusBarHeight', []);
}

exports.getNavigationBarHeight = function(success, error) {
    exec(success, error, 'StatusBarHeight', 'getNavigationBarHeight', []);
}
