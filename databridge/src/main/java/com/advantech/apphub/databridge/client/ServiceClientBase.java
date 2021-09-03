package com.advantech.apphub.databridge.client;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import com.advantech.apphub.databridge.constants.DataBridgeError;

/**
 * ClassName:   ServiceClientBase
 * Description: The base class of service client is used to simplify the steps and logic of
 * *           Apphub databridge client (that is SolutionApp) binding Apphub databridge
 * *           service
 * CreateDate   2021/08/19
 * Author:  fengchao.dai@advantech.com.cn
 */
public abstract class ServiceClientBase {
    private String LOG_TAG = "ServiceClientBase";
    boolean mIsDebugLogEnabled = true;

    void log(String logStr) {
        if (mIsDebugLogEnabled)
            Log.d(LOG_TAG, String.format("PID[%d], TID[%d] :  %s", Process.myPid(), Process.myTid(), logStr));
    }

    Context mClientCtx;  //Context of current client Activity/Service
    ServiceConnectionBase mServiceConnection;
    android.os.IInterface mServiceBinder;


    public ServiceClientBase(String tagStr, Context ctx) {
        LOG_TAG = tagStr;
        setCurrentClientContext(ctx);
    }

    void setCurrentClientContext(Context ctx) {
        mClientCtx = ctx;
    }

    Context getCurrentClientContext() {
        return mClientCtx;
    }

    void setCurrentServiceConnection(ServiceConnectionBase conn) {
        mServiceConnection = conn;
    }

    ServiceConnectionBase getCurrentServiceConnection() {
        return mServiceConnection;
    }


    int bindService(String intentAction) {
        boolean bindResult;

        log("Binding Service. (" + intentAction + ")");
        Intent bindingIntent = new Intent();
        bindingIntent.setAction(intentAction);
        bindingIntent.setPackage("com.adv.client");
        bindResult = getCurrentClientContext().bindService(
                bindingIntent,
                getCurrentServiceConnection(),
                Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT | Context.BIND_ABOVE_CLIENT);

        if (!bindResult) {
            log("Failed to bind Service ...");
            return DataBridgeError.DATABRIDGE_ERR_CLIENT_FAILED_TO_BIND_SERVICE;
        }
        log("Bind Service OK. Waiting for ServiceConnection callback to get service instance.");

        return DataBridgeError.DATABRIDGE_ERR_NO_ERROR;
    }


    int unbindService() {
        log("unbindService() is called");
        if (getCurrentClientContext() == null || getCurrentServiceConnection() == null) {
            return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
        }

        try {
            getCurrentClientContext().unbindService(getCurrentServiceConnection());
        } catch (IllegalArgumentException e) {
            return DataBridgeError.DATABRIDGE_ERR_CLIENT_FAILED_TO_UNBIND_SERVICE;
        }

        releaseServiceBinder();

        log("Context.unbindService() OK.");

        return DataBridgeError.DATABRIDGE_ERR_NO_ERROR;
    }


    //For service connection to pass the service binder instance
    void setServiceBinder(android.os.IInterface serviceBinder) {
        mServiceBinder = serviceBinder;
    }

    android.os.IInterface getServiceBinder() {
        return mServiceBinder;
    }

    //Release the service binder instance when unbind. Also can be called in ServiceConnec
    void releaseServiceBinder() {
        mServiceBinder = null;
    }
}
