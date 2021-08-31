package com.advantech.apphub.databridge.constants;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:   ResponseData
 * Description: TODO
 * CreateDate   2021/08/20
 */
public class ResponseData implements Parcelable {
    //Fields
    private String pkgName;
    private String functionId;
    private String otherDatas;
    private int result;
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
