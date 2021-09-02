package com.advantech.apphub.databridge.constants;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * ClassName:   ParameterData
 * Description:  This class defines a parameter data, which is passed to SolutionApp by
 * *           the web application through the apphub databridge. It is used for the web
 * *           application to set or obtain data from SolutionApp
 * CreateDate   2021/08/20
 * Author:  fengchao.dai@advantech.com.cn
 */
public class ParameterData implements Parcelable {

    /**
     * The package name of SolutionApp, used to indicate to Apphub databridge
     * that the data is to be forwarded to that application
     */
    private String pkgName;


    /**
     * Function name, which is used to explain to solutionapp which operation
     * the web application needs solutionapp to perform
     */
    private String functionId;


    /**
     * Other parameters, used for extra parameters passed by the web application
     * to SolutionApp, generally in Json format
     */
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

    @Override
    public String toString() {
        return "ParameterData{" +
                "pkgName='" + pkgName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", otherParams='" + otherParams + '\'' +
                '}';
    }
}
