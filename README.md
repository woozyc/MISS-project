### 一、主要代码

主要代码可分为三部分。

#### 1.app配置代码

位于`app/src/main/AndroidManifest.xml`，其中：

* 添加了一些需要用到的，以及未来可能用到的权限，于`uses-permission`标签中。
* 添加了一段query。
* 添加了app中使用到的若干个activity，于`activity`标签中。

#### 2.app UI代码

位于`app/src/main/res/layout/`目录下，其中：

* `activity_main.xml`：app主界面的UI设计。
* `activity_phone.xml`：手机信息界面的UI设计。
* `activity_app.xml`：应用信息界面的UI设计。
* `activity_loc.xml`：定位信息界面的UI设计。
* `activity_sensor.xml`：传感器信息界面的UI设计。

**注意：其中仅main和sensor进行了设计，其余界面的UI设计在kotlin代码中以另一种方式实现。**

#### 3. app功能代码

位于`app/src/main/java/com/example/collectinformation/`目录下

- `MainActivity.kt`：app主界面点击跳转代码。
- `PhoneActivity.kt`：手机信息界面UI绘制与输出。
- `AppActivity.kt`：应用信息界面UI绘制与输出。
- `LocActivity.kt`：定位信息界面UI绘制与输出。
- `SensorActivity.kt`：传感器信息输出。
- `DeviceInfoCollector.kt`：公共object定义，其中实现了一些获取信息的代码。
- `util/SystemUtils.java`：其中实现了一些获取信息的代码。

### 二、其他

* 所有图标都位于`app/src/main/res/drawable/`，矢量图来源：[ByteDance IconPark](https://iconpark.oceanengine.com/official)。

* 在`app/src/main/res/values/strings.xml`中可以定义一些`string`。

* 使用的IDE为Android Studio。开发环境的配置需要**先安装java jdk和SDK**，而后安装Android Studio。Windows可以参考：[Android Studio 安装配置教程 - Windows(详细版)-CSDN博客](https://blog.csdn.net/qq_38436214/article/details/105073213)。

### 三、实现展示

1. Android Studio成功配置好后，新建一个空白项目，打开手机模拟器，运行项目中默认的代码，可以输出Hello Android。

   <img src=".\screenshots\1.png" alt="1test，Android studio起模拟器运行编写的程序代码" style="zoom:50%;" />

2. 导入本仓库并运行app，会显示主界面，有四个卡片，可以点击查看相应的信息。

   <img src=".\screenshots\2.png" alt="主界面" style="zoom:50%;" />

3. 点击`手机信息`，会输出系统信息、和部分用户信息。包括设备厂商、安卓版本、SDK版本、安卓ID、品牌名、屏幕分辨率、DPI、内存信息、MAC地址、存储信息、CPU信息等。

   ![3](screenshots\3.png )

4. 点击`应用信息`，会输出各app信息。包括app的总数目，以及每个app的名称、包名称、版本号和版本代码。

   ![4](screenshots\4.png)

5. 点击`定位信息`，没有输出。

   ![5](screenshots\5.png)

6. 点击`传感器信息`,会输出目前支持的传感器列表，并以其中一个加速度传感器为例，输出其数据。

   ![6](screenshots\6.png)



### 四、问题

* 应用信息获取的不够深入

  * ...

* 用户信息获取的太少

  * ...

* 未来期望实现的功能：

  * 获取CPU实时频率和温度。因为温度传感器在安卓4.0后被移除，因此考虑用CPU温度作为一个参考
  * 获取定位信息。其实现相较于一些普通信息来说，需要额外的权限。对于其难易程度也做了相关的分析
  * ...

  
