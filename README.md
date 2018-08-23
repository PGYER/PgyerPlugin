                                            ### 蒲公英SDK Cordova插件

### 注册应用获取 App Key

App Key：唯一标识一个应用的 Key，在蒲公英上的每一个 App 都有一个唯一的 App Key，开发者可以在应用管理页面首页查看。

![](https://static.pgyer.com/image/view/admin_images/1a2818d1f66c77a80970481b48a4145b)

###安装插件

cordova plugin add pgyerplugin --variable ANDROID_APPID=[Android App Key] --variable IOS_APPID=[iOS App Key]


### 使用插件
#### 上报 Crash 异常

```
cordova.exec(success, error, 'PgyerPlugin', 'crashRegister', [""]);

function success(msg) {
    alert(msg);
}

function error(msg) {
    alert('失败: ' + msg);
}

```

#### 用户反馈
```
cordova.exec(success, error, 'PgyerPlugin', 'showFeedback', [""]);

```

#### 检查更新
```
cordova.exec(success, error, 'PgyerPlugin', 'checkUpdate', [""]);

```

#### 备注
更多高级用法Android端修改PgyerPlugin.java文件，iOS端修改PgyerPlugin.h和PgyerPlugin.m文件，具体修改方式参考 [新版本Android SDK 集成指南](https://www.pgyer.com/doc/view/new_sdk_android_guide) 和 [iOS SDK 集成指南](https://www.pgyer.com/doc/view/sdk_ios_guide)。

下载[Demo工程](https://github.com/rikyou215/PgyerPluginDemo)


