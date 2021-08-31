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
 * Description: TODO
 * CreateDate   2021/08/19
 */
public abstract class AppHubDataBridgeClient extends ServiceClientBase {

    public AppHubDataBridgeClient(String tagStr, Context ctx) {
        super(tagStr, ctx);
    }

    private IDataBridgeService getDataBridgeServiceBinder() {
        return (IDataBridgeService) getServiceBinder();
    }

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
        // make ServiceConnection able to pass back the service binder intance when service is connected and able get current client activity context
        conn.setCurrentServiceClient(this);

        //Do service binding
        this.setCurrentServiceConnection(conn);
        return super.bindService("apphub.action.BindDataBridgeService");
    }


    public int unbindDataBridgeService() {
        log("unbindDataBridgeService() is called");
        return super.unbindService();
    }

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

    public abstract ResponseData setValue(ParameterData param);

    public abstract ResponseData getValue(ParameterData param);
}
