### 说明

1. 主要代码文件都位于`app/src/main/java/com/example/collectinformation/`目录下，共5个`.kt`文件，使用kotlin语言编程。
   - `MainActivity.kt`：app主界面点击跳转代码，`DeviceInfoCollector`公共object定义。
   - `AppActivity.kt`：应用信息界面UI绘制与输出。
   - `LocActivity.kt`：定位信息界面UI绘制与输出。
   - `PhoneActivity.kt`：设备信息界面UI绘制与输出。
   - `SensorActivity.kt`：传感器信息界面UI绘制与输出。
2. `app/src/main/res/layout/`目录下为额外的UI绘制文件，共5个`.xml`，分别对应上面的5个界面。其中只使用了`activity_main.xml`，另外四个信息界面的UI已经在各自的`.kt`文件中绘制好了。
3. `app/src/main/AndroidManifest.xml`为app配置文件，其中application标签前为添加的内容（添加了三个uses-permission，和一段query）
4. 所有图标都位于`app/src/main/res/drawable/`，矢量图来源：[ByteDance IconPark](https://iconpark.oceanengine.com/official)。
5. 在`app/src/main/res/values/strings.xml`中可以定义一些`string`。
6. 使用的IDE为Android Studio。开发环境的配置需要**先安装java jdk和SDK**，而后安装Android Studio。Windows可以参考：[Android Studio 安装配置教程 - Windows(详细版)-CSDN博客](https://blog.csdn.net/qq_38436214/article/details/105073213)。



### 问题

1. 现有代码能获取到的信息并不很丰富，可以进行扩充。
   - 可以直接在已有的5个`.kt`文件中修改，定位与传感器信息的输出位置已经标在`TODO`处
   - 其他信息需要调整的，也可以增加/删减，都是一个模板
   - 是否可以尝试获取应用运行状态、访问相册、文件、相机、用户id信息等等 ([Android中的权限](https://developer.android.com/guide/topics/permissions/overview?hl=zh-cn))，**边界在哪里**可以讨论
2. MainActivity.kt的`getUserInfo`中有尝试获取定位的代码。虽然AndroidManifest.xml文件中配置了位置权限使得MainActivity.kt中的`locationManager.getLastKnownLocation`语句语法合法不报错，但是获取位置信息是失败的，运行app时并没有打印定位信息。可以**调研或源码阅读**分析为什么这里无法获得。
3. 猜测传感器需要的权限可能不会很高？([安卓传感器概览](https://developer.android.com/develop/sensors-and-location/sensors/sensors_overview?hl=zh-cn))



### 一些截图

1. Android Studio成功配置好后，新建一个空白项目，打开手机模拟器，运行项目中默认的代码，可以输出Hello Android。

   <img src=".\screenshots\1.png" alt="1test，Android studio起模拟器运行编写的程序代码" style="zoom:50%;" />

2. AndroidManifest.xml配置文件。

   <img src=".\screenshots\2.png" alt="2为本程序添加权限，使其能够获取到更多信息" style="zoom:50%;" />

4. 导入本仓库并运行app，会显示主界面，有四个卡片，可以点击查看相应的信息。

   <img src=".\screenshots\6.png" alt="主界面" style="zoom:50%;" />

5. 例如，手机信息界面会输出系统信息、用户信息，应用信息界面输出各app信息。

   <img src=".\screenshots\4.png" alt="设备信息" style="zoom:50%;" /><img src=".\screenshots\5.png" alt="设备信息" style="zoom:50%;" />
6. 总体开发环境。

   <img src=".\screenshots\7.png" alt="5总体开发环境" style="zoom:50%;" />


