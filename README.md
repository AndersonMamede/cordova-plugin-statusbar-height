# cordova-plugin-statusbar-height
> Android，iOS

## Installation

```bash
cordova plugin add https://github.com/toptive/cordova-plugin-statusbar-height.git
```

## Methods

### cordova.plugins.StatusBarHeight.getStatusBarHeight

## cordova.plugins.StatusBarHeight.getNavigationBarHeight


###  Demo

```javascript
document.addEventListener('deviceready', getStatusbarHeight, false);
function getStatusbarHeight() {
    cordova.plugins.StatusBarHeight.getStatusBarHeight(
        function(value) {
            console.log(value);
        },
        function(error) {
            console.log(error);
        }
    )
}

document.addEventListener('deviceready', getStatusbarHeight, false);
function getStatusbarHeight() {
    cordova.plugins.StatusBarHeight.getNavigationBarHeight(
        function(value) {
            console.log(value);
        },
        function(error) {
            console.log(error);
        }
    )
}
```

### Supported Platforms

* andorid
* ios

