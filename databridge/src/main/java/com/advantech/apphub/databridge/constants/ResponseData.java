package com.advantech.apphub.databridge.constants;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:   ResponseData
 * Description:  This class defines a response data, which is used by solutionapp to
 * *            return execution results to the web application through apphub databridge
 * CreateDate   2021/08/20
 * Author:  fengchao.dai@advantech.com.cn
 */
public class ResponseData implements Parcelable {

    /**
     * The package name of SolutionApp, which is used to indicate to the apphub databridge
     * and the web application which application the data comes from
     */
    private String pkgName;


    /**
     * Function name, used to indicate to the apphub databridge and the web application
     * which operation the data responds to
     */
    private String functionId;


    /**
     * Other data, used to pass additional parameters, usually in Json format
     */
    private String otherDatas;


    /**
     * Execution result, 0 means successful execution, non-zero means execution failed
     */
    private int result;


    /**
     * The error code, which may be the value in DataBridgeError, or other user-defined values
     */
    private int errorCode;


    //Clone object method.
    public void copyFrom(ResponseData obj) {
        this.pkgName = obj.pkgName;
        this.functionId = obj.functionId;
        this.otherDatas = obj.otherDatas;
        this.result = obj.result;
        this.errorCode = obj.errorCode;
    }

    //Parcelable impl step 1:   Constructor with Parcel parameter
    public ResponseData() {
    }

    public ResponseData(String pkgName, String functionId, String otherDatas, int result, int errorCode) {
        this.pkgName = pkgName;
        this.functionId = functionId;
        this.otherDatas = otherDatas;
        this.result = result;
        this.errorCode = errorCode;
    }

    public ResponseData(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pkgName);
        parcel.writeString(functionId);
        parcel.writeString(otherDatas);
        parcel.writeInt(result);
        parcel.writeInt(errorCode);
    }

    //Parcelable impl step 3:   impl readFromParcel() method
    private void readFromParcel(Parcel in) {
        pkgName = in.readString();
        functionId = in.readString();
        otherDatas = in.readString();
        result = in.readInt();
        errorCode = in.readInt();
    }

    //Parcelable impl step 4:   Assign the static field "CREATOR" in the following format
    public static final Creator<ResponseData> CREATOR =
            new Creator<ResponseData>() {
                public ResponseData createFromParcel(Parcel in) {
                    return new ResponseData(in);
                }

                public ResponseData[] newArray(int size) {
                    return new ResponseData[size];
                }
            };


    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getOtherDatas() {
        return otherDatas;
    }

    public void setOtherDatas(String otherDatas) {
        this.otherDatas = otherDatas;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "pkgName='" + pkgName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", otherDatas='" + otherDatas + '\'' +
                ", result=" + result +
                ", errorCode=" + errorCode +
                '}';
    }
}
