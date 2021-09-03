# ApphubDataBridge

[English](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/README.md)

Apphub是一个Android/Linux/Windows设备监控管理部署的解决方案. databridge是Apphub提供的一个插件,通过该插件用户可以很方便的将自己的本地应用与云端应用建立连接从而达到远程管理控制的目的. 

有了Apphub databridge用户只需要专注于自己的本地端app和云端应用开发, 不需要关心本地端与云端之间交互的过程, 这样极大的减少了工作量和工作难度

而该项目是Apphub databridg本地端android应用开发的一个开发库, 用户通过该开发库就可以很容易的将自己的应用与Apphub databridge串接起来


# 快速开始
## 依赖
````
implementation 'io.github.EdgeSolution:databridge:1.0.2'
````

## 实现抽象类
写一个实现类继承于AppHubDataBridgeClient抽象类, 实现setValue和getValue方法
````java
public class MyAppHubDataBridgeClient extends AppHubDataBridgeClient {
    private String tag;
    private Handler handler;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    MyAppHubDataBridgeClient(String tagStr, Context ctx) {
        super(tagStr, ctx);
        // TODO: Add your own business logic 
    }

    @Override
    public ResponseData setValue(ParameterData param) {
        // TODO: Add your own business logic 
        return null;
    }

    @Override
    public ResponseData getValue(ParameterData param) {
        // TODO: Add your own business logic 
        return null;
    }
}
````

## 初始化
````java
    private MyAppHubDataBridgeClient myAppHubDataBridgeClient;

    AppHubDataBridgeServiceConnection dataBridgeServiceConnection = new AppHubDataBridgeServiceConnection("LOG_TAG") {
        @Override
        public void on_service_connected() {
            myAppHubDataBridgeClient.registerDataBridgeClient("You app package name");
            // TODO: Add your own business logic 
        }

        @Override
        public void on_service_disconnected() {
            // TODO: Add your own business logic
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAppHubDataBridgeClient = new MyAppHubDataBridgeClient("LOG_TAG", getApplicationContext());
        myAppHubDataBridgeClient.bindDataBridgeService(dataBridgeServiceConnection);
    }
````
## 主要功能
### 云端应用向本地应用设置/获取数据
在你的继承类的setValue和getValue方法中完成设置数据和获取数据的相关的业务逻辑

### 本地应用向云端应用主动上报数据
用你实现类的对象调用reportDataSync或reportDataAsync方法向cloud应用程序发送数据
#### reportDataSync(ReportData data)方法
reportDataSync()方法发送数据时,会等待cloud应用的回应,可能会耗时,因此不能在主线程中调用
#### reportDataAsync(ReportData data)方法
int reportDataAsync()方法发送数据时,数据发送到Apphub Agent后就会立即返回,所以cloud应用接收到数据的时机无法确定


# 范例
请参考sample_lightcontrol application module源码
lightcontrol cloud应用画面如下
![](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/images/lightcontrol_web.png)

lightcontrol android应用画面如下
![](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/images/lightcontrol_app.png)
