# XunjiSDK-Android


XJlocation-Android 是一套基于 Android 4.3 及以上版本的室内地图应用程序开发接口，供开发者在自己的Android应用中加入室内地图相关的功能，包括：获取当前位置等功能。

## 获取AppKey和MapId
请联系开发人员

## 添加依赖

```
注意如果同时使用了Xunji的导航模块则不用导入,com.locnavi:map 导航模块已经导入了xunji-location 模块
   compile 'com.locnavi:location:0.0.6'
```

## 目前支持的cpu 架构 arm,暂时不支持其他架构,请配置下面的cpu架构
```
ndk {
            // 必须设置cpu类型,设置支持的 SO 库构架,强烈建议仅仅支持'armeabi',
            //如果添加全部平台的架构,包会变很大,市场面98% 都是armabi,如果想支持其他的cpu类型,请拷贝demo的跟根文件下的v7a
            //和v8a 到App相应的的cpu文件,
            //,默认仅仅支持'armeabi',不需要拷贝'armeabi',

            abiFilters 'armeabi'
}
```


## 加入编译限制

```
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

```

## 加入权限
导入XunjiSDK后需要
```
    <!-- sdk 使用需要的权限 -->
    <!-- if use wifi indoor positioning -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <!-- if use ble indoor positioning -->
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    <!-- general permission -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <!-- 连接网络权限，用于执行云端语音能力 -->
    <!-- 获取手机录音机使用权限，听写、识别、语义理解需要用到此权限 -->
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <!-- 读取网络信息状态 -->
    <!-- 允许程序改变网络连接状态 -->
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
    <!-- 读取手机信息权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- 读取联系人权限，上传联系人需要用到此权限 -->
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <!-- 外存储写权限，构建语法需要用到此权限 -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!-- 外存储读权限，构建语法需要用到此权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <!-- 配置权限，用来记录应用配置信息 -->
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.READ_LOGS" />
```

## 使用
初始化

以下的功能都需要在在Application 的onCreate 方法中进行初始化

注意如果同时使用了xunji的导航模块,并且已经初始化导航模块,则不用初始化定位模块,xunji导航模块 已经对定位进行了初始化,

```
     XJLocationSDK.init(context,appKey);

```

## 功能一  定位功能
1.定位监听,获取当前的位置,可以参考xunjilocation demo ,需要提前获取定位和蓝牙权限,XJLocation 包括经纬度等信息
```

xjClient = new XJClient(context, map_id);
xjClient.registerLocationListener(new XJLocationListener() {
    @Override
    public void onReceiveLocation(XJLocation xjLocationLocation){
    if(xjLocationLocation == null){
        //定位失败;
        return;
    }
    //是否在Map内
    xjLocationLocation.isInThisMap()

    }
});

// xjClient.start(); 调用一次回调一次注册的结果
// 如果想要循环回调,请循环调用 xjClient.start();
xjClient.start();
```

2.获取最近的区域的名字,此方法比较耗性能,请在子线程进行调用


```
xjClient.getNearestLocationRegion()
```

3.activity 结束时调用
```
@Override
protected void onDestroy() {
    super.onDestroy();
    xjClient.stop();
}
```


## 混淆
```
-keep public class com.sails.engine.patterns.IconPatterns
```