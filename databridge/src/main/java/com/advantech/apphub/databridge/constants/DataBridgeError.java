package com.advantech.apphub.databridge.constants;

/**
 * ClassName:   DataBridgeError
 * Description: Some common error codes are defined, and users can add their own error codes on this basis
 * CreateDate   2021/08/26
 * Author:  fengchao.dai@advantech.com.cn
 */
public class DataBridgeError {
    public static final int DATABRIDGE_ERR_NO_ERROR = 0;

    public static final int DATABRIDGE_ERR_UNKNOWN_ERROR = 1;
    public static final int DATABRIDGE_ERR_OPERATION_NOT_ALLOWED = 2;
    public static final int DATABRIDGE_ERR_UNKNOWN_FUNCID = 3;
    public static final int DATABRIDGE_ERR_PARAMETER_ERROR = 4;

    public static final int DATABRIDGE_ERR_SERVICE_NULL_POINTER = 100;
    public static final int DATABRIDGE_ERR_SERVICE_UNKNWON_EXCEPTION = 101;
    public static final int DATABRIDGE_ERR_SERVICE_REMOTE_CALLBACK_LIST_NOT_FOUND = 102;

    public static final int DATABRIDGE_ERR_CLIENT_NULL_POINTER = 200;
    public static final int DATABRIDGE_ERR_CLIENT_FAILED_TO_BIND_SERVICE = 201;
    public static final int DATABRIDGE_ERR_CLIENT_SERVICE_ALREADY_CONNECTED = 202;
    public static final int DATABRIDGE_ERR_CLIENT_SERVICE_DISCONNECTED = 203;
    public static final int DATABRIDGE_ERR_CLIENT_REMOTE_EXCEPTION = 204;
    public static final int DATABRIDGE_ERR_CLIENT_FAILED_TO_UNBIND_SERVICE = 205;
    public static final int DATABRIDGE_ERR_CLIENT_UNKNWON_EXCEPTION = 206;

}
