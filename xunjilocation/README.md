# XunjiSDK-Android


Ipslocation-Android 是一套基于 Android 4.3 及以上版本的室内地图应用程序开发接口，供开发者在自己的Android应用中加入室内地图相关的功能，包括：获取当前位置等功能。

## 获取AppKey和MapId
请联系开发人员

## 添加依赖

```
注意如果同时使用了Xunji的导航模块则不用导入,com.locnavi:map 导航模块已经导入了ips-location 模块
   compile 'com.locnavi:location:0.0.3'
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

注意如果同时使用了ipsmap的导航模块,并且已经初始化导航模块,则不用初始化定位模块,ipsmap导航模块 已经对定位进行了初始化,

```
     XJLocationSDK.init(context,appKey);

```

## 功能一  定位功能
1.定位监听,获取当前的位置,可以参考ipslocation demo ,需要提前获取定位和蓝牙权限
```

xjClient = new XJClient(context, map_id);
//携带用户id用法
xjClient = new XJClient(MainActivity.this, Constants.IPSMAP_MAP_ID,Constants.IPSMAP_USER_ID);
xjClient.registerLocationListener(new IpsLocationListener() {
    @Override
    public void onReceiveLocation(IpsLocation ipsLocation){
    if(ipsLocation == null){
        //定位失败;
        return;
    }
    //是否在Map内
    ipsLocation.isInThisMap()

    }
});
xjClient.start();
```

2.activity 结束时调用
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

错误码

```
    //Error code message
    public  static final  int ERROR_CODE_0 = 0;
    public static final String ERROR_MESSAGE_0 = "蓝牙需要重启";

    public  static final  int ERROR_CODE_1 = 1;
    public static final String ERROR_MESSAGE_1 = "没有读取地图的权限";

    public  static final  int ERROR_CODE_2 = 2;
    public static final String ERROR_MESSAGE_2 = "地图信息不完整";

    public  static final  int ERROR_CODE_3 = 3;
    public static final String ERROR_MESSAGE_3 = "网络异常 加载地图失败";

    public  static final  int ERROR_CODE_4 = 4;
    public static final String ERROR_MESSAGE_4 = "没有找到目的地,请检查id是否正确!";

    public  static final  int ERROR_CODE_5 = 5;
    public static final String ERROR_MESSAGE_5 = "路径规划失败!";

    public  static final  int ERROR_CODE_6 = 6;
    public static final String ERROR_MESSAGE_6 = "定位失败!";

    public  static final  int ERROR_CODE_7 = 7;
    public static final String ERROR_MESSAGE_7 = "正在加载地图!";

    public  static final  int ERROR_CODE_8 = 8;
    public static final String ERROR_MESSAGE_8 = "没有设置目的地列表!";
```


