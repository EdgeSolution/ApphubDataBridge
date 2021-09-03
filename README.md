# ApphubDataBridge

[简体中文](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/README_ZH.md)

Apphub is a solution for Android / Linux / windows device monitoring, management and deployment. Databridge is a plug-in provided by apphub. Through this plug-in, users can easily establish a connection between their local applications and cloud applications, so as to achieve the purpose of remote management and control

With apphub databridge, users only need to focus on their own local app and cloud application development, and do not need to care about the interaction process between local and cloud, which greatly reduces the workload and work difficulty

The project is a development library for apphub databridge local Android application development. Users can easily connect their applications with apphub databridge through the development library


# Quick Start
## Dependency
````
implementation 'io.github.EdgeSolution:databridge:1.0.2'
````

##  Implement abstract class
Write an implementation class that extends from the AppHubDataBridgeClient abstract class and implements setValue and getValue methods 
````
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

## Initialization
````
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

## setValue and getValue
Complete the business logic related to setting data and obtaining data in the setValue and getValue methods of your inherited class 

## reportData
Call the reportdatasync or reportDataAsync method with the object of your implementation class to send data to the cloud application 
### reportDataSync(ReportData data) method
When reportDataSync() sends data, it will wait for the response from the cloud application,  it may take time, so it can not be invoked in the main thread. 
### reportDataAsync(ReportData data) method
When the reportDataAsync() method sends data, the data will be returned immediately after it is sent to the apphub agent, so the time when the cloud application receives the data cannot be determined 


# Sample
Please refer to sample_lightcontrol Application module source code
The lightcontrol cloud application screen is as follows
![](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/images/lightcontrol_web.png)

The lightcontrol android application screen is as follows
![](https://github.com/EdgeSolution/ApphubDataBridge/blob/main/images/lightcontrol_app.png)

