package com.advantech.apphub.databridge.client;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.advantech.apphub.databridge.constants.DataBridgeError;
import com.advantech.apphub.databridge.constants.ParameterData;
import com.advantech.apphub.databridge.constants.ReportData;
import com.advantech.apphub.databridge.constants.ResponseData;
import com.advantech.apphub.databridge.service.IDataBridgeService;


/**
 * ClassName:   AppHubDataBridgeClient
 * Description: An abstract class needs to be implemented by users themselves, provides
 * *           users with a method of binding Apphub databridge service, provides a method
 * *           for SolutionApp to actively report data to web applications, and reserves
 * *           an interface for web applications to set and get data from SolutionApp.
 * CreateDate   2021/08/19
 * Author:  fengchao.dai@advantech.com.cn
 */
public abstract class AppHubDataBridgeClient extends ServiceClientBase {

    public AppHubDataBridgeClient(String tagStr, Context ctx) {
        super(tagStr, ctx);
    }

    private IDataBridgeService getDataBridgeServiceBinder() {
        return (IDataBridgeService) getServiceBinder();
    }

    /**
     * Determine if SolutionApp is connected to Apphub databridge service
     *
     * @return True indicates that there is a connection, and true indicates that there
     * is no connection
     */
    public boolean isDataBridgeServiceConnected() {
        return getDataBridgeServiceBinder() != null;
    }

    private IDataBridgeClient mDataBridgeClient = new IDataBridgeClient.Stub() {
        @Override
        public ResponseData setData(ParameterData param) throws RemoteException {
            return setValue(param);
        }

        @Override
        public ResponseData getData(ParameterData param) throws RemoteException {
            return getValue(param);
        }
    };

    /**
     * Used to bind Apphub databridge service
     *
     * @param conn: conn is used to feed back the connection status. If the connection is
     *              successful, it will call back on_service_connected method. If the
     *              connection is disconnected, call back on_service_disconnected
     *              method, users can add their own business logic to these two callback
     *              functions
     * @return Reference DataBridgeError
     */
    public int bindDataBridgeService(ServiceConnectionBase conn) {
        log("bindDataBridgeService() is called");

        if (isDataBridgeServiceConnected()) {
            log("Service is already connected. Do nothing.");
            return DataBridgeError.DATABRIDGE_ERR_CLIENT_SERVICE_ALREADY_CONNECTED;
        }

        //Try startSerice() be before bind to ensure that service is started.
        Intent startIntent = new Intent();
        startIntent.setAction("apphub.action.StartDataBridgeService");
        startIntent.setPackage("com.adv.client");
        getCurrentClientContext().startService(startIntent);

        //Set reference of current ServiceClient instance to the ServiceConnection instance,
        // make ServiceConnection able to pass back the service binder intance when service
        // is connected and able get current client activity context
        conn.setCurrentServiceClient(this);

        //Do service binding
        this.setCurrentServiceConnection(conn);
        return super.bindService("apphub.action.BindDataBridgeService");
    }


    /**
     * Used to unbind Apphub databridge service
     *
     * @return Reference DataBridgeError
     */
    public int unbindDataBridgeService() {
        log("unbindDataBridgeService() is called");
        return super.unbindService();
    }


    /**
     * Register the Apphub databridge client(that is SolutionApp), to the Apphub databridge
     * service, so that Apphub and SolutionApp establish a two-way communication channel.
     * This method is usually called in the on_service_connected method in the
     * ServiceConnectionBase implementation class of SolutionApp
     *
     * @param pkgName: Package name of solution app
     * @return Reference DataBridgeError
     */
    public int registerDataBridgeClient(String pkgName) {
        log(String.format("registerDataBridgeClient(%s) is called! ", pkgName));
        int ret;

        try {
            if (getDataBridgeServiceBinder() == null || pkgName == null || pkgName.isEmpty()) {
                return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
            }
            ret = getDataBridgeServiceBinder().registerDataBridgeClient(pkgName, mDataBridgeClient);
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = DataBridgeError.DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION;
            log("registerDataBridgeClient failed!");
        }
        return ret;
    }

    /**
     * Unregister the Apphub databridge client (that is solutionapp)
     *
     * @param pkgName: Package name of solution app
     * @return Reference DataBridgeError
     */
    public int unregisterDataBridgeClient(String pkgName) {
        log(String.format("unregisterDataBridgeClient(%s) is called! ", pkgName));
        int ret;

        try {
            if (getDataBridgeServiceBinder() == null || pkgName == null || pkgName.isEmpty()) {
                return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
            }
            ret = getDataBridgeServiceBinder().unregisterDataBridgeClient(pkgName);
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = DataBridgeError.DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION;
            log("unregisterDataBridgeClient failed!");
        }
        return ret;
    }

    /**
     * Enable or disable Apphub databrige log
     *
     * @param enable: true means enable log, false means forbid log
     * @return Reference DataBridgeError
     */
    public int toggleDebugLog(boolean enable) {
        int ret;
        try {
            if (getDataBridgeServiceBinder() == null) {
                return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
            }
            ret = getDataBridgeServiceBinder().toggleDebugLog(enable);
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = DataBridgeError.DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION;
            log("toggleDebugLog failed!");
        }
        return ret;
    }

    /**
     * Report data to the web application in a synchronous manner. This method may be
     * time-consuming and therefore cannot be called on the UI thread
     *
     * @param data: Reported data content
     * @return Reference DataBridgeError
     */
    public int reportDataSync(ReportData data) {
        log("reportDataSync() is called! ");
        int ret;
        try {
            if (getDataBridgeServiceBinder() == null || data == null) {
                return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
            }
            ret = getDataBridgeServiceBinder().reportDataSync(data);
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = DataBridgeError.DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION;
            log("reportDataSync failed!");
        }
        return ret;
    }

    /**
     * Report data to the web application in an asynchronous manner. When the method returns,
     * the reported data only reaches the Apphub agent, and it cannot be determined whether
     * it reaches the web application.
     *
     * @param data: Reported data content
     * @return Reference DataBridgeError
     */
    public int reportDataAsync(ReportData data) {
        log("reportDataAsync() is called! ");
        int ret;
        try {
            if (getDataBridgeServiceBinder() == null || data == null) {
                return DataBridgeError.DATABRIDGE_ERR_CLIENT_NULL_POINTER;
            }
            ret = getDataBridgeServiceBinder().reportDataAsync(data);
        } catch (RemoteException e) {
            e.printStackTrace();
            ret = DataBridgeError.DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION;
            log("reportDataAsync failed!");
        }
        return ret;
    }

    /**
     * The interface method reserved for SolutionApp, used for web applications to send
     * setting data events to SolutionApp
     *
     * @param param:Event parameters passed by the web application to SolutionApp
     * @return Reference ResponseData
     */
    public abstract ResponseData setValue(ParameterData param);


    /**
     * The interface method reserved for SolutionApp, used for web applications to send
     * getting data events to SolutionApp
     *
     * @param param:Event parameters passed by the web application to SolutionApp
     * @return Reference ResponseData
     */
    public abstract ResponseData getValue(ParameterData param);
}
