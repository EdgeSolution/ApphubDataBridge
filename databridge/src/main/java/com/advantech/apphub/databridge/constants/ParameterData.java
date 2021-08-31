package com.advantech.apphub.databridge.constants;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * ClassName:   ParameterData
 * Description: TODO
 * CreateDate   2021/08/20
 */
public class ParameterData implements Parcelable {
    //Fields
    private String pkgName;
    private String functionId;
    private String otherParams;

    //Clone object method.
    public void copyFrom(ParameterData obj) {
        this.pkgName = obj.pkgName;
        this.functionId = obj.functionId;
        this.otherParams = obj.otherParams;
    }

    //Parcelable impl step 1:   Constructor with Parcel parameter
    public ParameterData() {
    }

    public ParameterData(@NonNull String pkgName, String functionId, String otherParams) {
        this.pkgName = pkgName;
        this.functionId = functionId;
        this.otherParams = otherParams;
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

    public String getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(String otherParams) {
        this.otherParams = otherParams;
    }

    public ParameterData(Parcel in) {
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
        parcel.writeString(otherParams);
    }

    //Parcelable impl step 3:   impl readFromParcel() method
    private void readFromParcel(Parcel in) {
        pkgName = in.readString();
        functionId = in.readString();
        otherParams = in.readString();
    }

    //Parcelable impl step 4:   Assign the static field "CREATOR" in the following format
    public static final Creator<ParameterData> CREATOR =
            new Creator<ParameterData>() {
                public ParameterData createFromParcel(Parcel in) {
                    return new ParameterData(in);
                }

                public ParameterData[] newArray(int size) {
                    return new ParameterData[size];
                }
            };

    @Override
    public String toString() {
        return "ParameterData{" +
                "pkgName='" + pkgName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", otherParams='" + otherParams + '\'' +
                '}';
    }
}
