# XunjiSDK-Android


XunjiSDK-Android 是一套基于 Android 4.3 及以上版本的室内地图应用程序开发接口，供开发者在自己的Android应用中加入室内地图相关的功能，包括：地图显示（多楼层、多栋楼）、室内导航、模拟导航、语音播报等功能。

## 获取key

## 添加依赖

```


建议使用marven 方式
// marven
  compile ('com.locnavi:navigation:0.0.1', {
        exclude group: 'com.android.support'
    })



```
如果仅仅使用定位模块请参考ipslocation demo README


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
## 加入权限
```
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

```

## 使用
初始化

在Application 的onCreate 方法中进行初始化
``` 
  使用默认配置信息
    XJMapSDK.init(context, IPSMAP_APP_KEY);
    或
    定制配置信息 ,使用微信分享功能请实现相关的接口
    XJMapSDK.init(new XJMapSDK.Configuration.Builder(context)
                .appKey(Constants.IPSMAP_APP_KEY)
                .shareToWechatListener(this)
                 .debug(false)
                  //开启debug 后有log 日志,打正式版请务必关闭debug 日志
                  // 默认是false , 如果项目正式上线 debug 是false 
                  //以下情况: debug 只能是 true 如果是开发人员给出的测试 mapid(在正式版道一循上不显示,道一循Beta 版的列表显示)
                .build());




  微信分享功能可以参考以下代码,需要替换自己申请的id

      @Override
      public void shareToWechat(String url, String title, String description, Bitmap bitmap) {
          try {
              IWXAPI wxApi = WXAPIFactory.createWXAPI(context, Constants.WECHAT_APP_ID);
              wxApi.registerApp(Constants.WECHAT_APP_ID);
              if (!wxApi.isWXAppInstalled()) {
                  T.showShort("未安装微信");
                  return;
              }
              WXWebpageObject webpage = new WXWebpageObject();
              webpage.webpageUrl = url;
              WXMediaMessage msg = new WXMediaMessage(webpage);
              msg.title = title;
              msg.description = description;
              msg.setThumbImage(bitmap);
              SendMessageToWX.Req req = new SendMessageToWX.Req();
              req.transaction = buildTransaction("webpage");
              req.message = msg;
              req.scene = SendMessageToWX.Req.WXSceneSession;
              wxApi.sendReq(req);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

                
```



启动地图方式1,携带目的地和地图id,导航到目的地
```
XJMapSDK.openIpsMapActivity(Context context, String mapId, String targetId);

```

启动地图方式2,仅仅传递地图的id
```
XJMapSDK.openIpsMapActivity(Context context, String mapId);

```

定位监听,获取当前的位置,可以参考ipslocation demo ,需要提前获取定位和蓝牙权限
```
xjClient = new XJClient(context, map_id);
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

activity 结束时调用
```
@Override
protected void onDestroy() {
    super.onDestroy();
    xjClient.stop();
}
```

## 混淆
```
-dontwarn com.baidu.**
-keep class com.baidu.** {*;}
-dontwarn com.iflytek.**
-keep class com.iflytek.**{*;}
-keep public class com.sails.engine.patterns.IconPatterns
```

微信分享以及复制跳转请参考demo

## FAQ
1.0
![](/pic/7991511168017_.pic.jpg)
![](/pic/8021511168507_.pic.jpg)
出现上面的类似xml资源文件缺失的情况:
两种解决方案:
1. 在通过gradle 引用是加入exclude group: 'com.android.support' ,并且自己加入compile 'com.android.support:appcompat-v7:版本号'
建议方式.建议版本号25.3.1
2. 修改项目的support 支持和  compile 'com.android.support:appcompat-v7:25.3.1' 版本号一致

2.0 
```
app如果使用了okhttp ,glide ...出现第三发开源库 冲突
两种解决方案:
1.通过  exclude group: "com.squareup.okhttp3" 方式处理
然后保留项目的okhttp和glide 
2.保持和sdk的一致引入的第三方库版本号一致.否则有可能出现冲突
```
```
"glide"             : "com.github.bumptech.glide:glide:3.7.0",
"okhttp"            : "com.squareup.okhttp3:okhttp:3.8.0",
"gson"              : "com.google.code.gson:gson:2.8.2",
 ```        


 3.0
 
![](/pic/AC0BDB3E-C313-4644-AB5F-F3C8FA209AEC.png) 
```


    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
            flatDir {
                dirs 'libs'
            }
        }
    }
    
    compileOptions {
         sourceCompatibility JavaVersion.VERSION_1_8
         targetCompatibility JavaVersion.VERSION_1_8
     }
```


