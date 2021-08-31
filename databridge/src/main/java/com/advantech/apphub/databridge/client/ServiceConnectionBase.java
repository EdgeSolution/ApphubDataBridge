package com.advantech.apphub.databridge.client;

import android.content.ServiceConnection;
import android.os.Process;
import android.util.Log;

/**
 * ClassName:   ServiceConnectionBase
 * Description: TODO
 * CreateDate   2021/08/19
 */
public abstract class ServiceConnectionBase implements ServiceConnection {
    private String LOG_TAG = "SDK UnknownSrvConn";

    void log(String logStr) {
        Log.d(LOG_TAG, String.format("PID[%d], TID[%d] :  %s", Process.myPid(), Process.myTid(), logStr));
    }

    ServiceClientBase mServiceClient;

    public ServiceConnectionBase(String tagStr) {
        LOG_TAG = tagStr;
    }

    void setCurrentServiceClient(ServiceClientBase client) {
        mServiceClient = client;
    }

    ServiceClientBase getCurrentServiceClient() {
        return mServiceClient;
    }

    abstract void on_service_connected();

    abstract void on_service_disconnected();
}
