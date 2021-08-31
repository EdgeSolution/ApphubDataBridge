package com.advantech.apphub.databridge.client;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.os.IBinder;

import com.advantech.apphub.databridge.service.IDataBridgeService;


/**
 * ClassName:   AppHubDataBridgeServiceConnection
 * Description: TODO
 * CreateDate   2021/08/23
 */
public abstract class AppHubDataBridgeServiceConnection extends ServiceConnectionBase {
    private static final String TAG = "AppHubDataBridgeServiceConnection";
    IDataBridgeService mDataBridgeService = null;
    final Object SYNCLOCK_SERVICE_CONNECTION_OP = new Object();

    public AppHubDataBridgeServiceConnection(String tagStr) {
        super(tagStr);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (SYNCLOCK_SERVICE_CONNECTION_OP) {
            final IDataBridgeService dataBridgeService = IDataBridgeService.Stub.asInterface(iBinder);
            if (dataBridgeService != null) { //Do not trigger on_service_connected() if service disconnected.
                log("Going to trigger on_service_connected() !!");
                mServiceClient.setServiceBinder(dataBridgeService); //Pass the service binder instance to ServiceClient.
                AppHubDataBridgeServiceConnection.this.on_service_connected();
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        synchronized (SYNCLOCK_SERVICE_CONNECTION_OP) {
            log("onServiceDisconnected(). Service is disconnected ...");
            mDataBridgeService = null;
            mServiceClient.releaseServiceBinder();
            on_service_disconnected();
        }
    }

    //Extended callback interfaces to inform that service is connected/disconnected
    public abstract void on_service_connected();

    public abstract void on_service_disconnected();
}
