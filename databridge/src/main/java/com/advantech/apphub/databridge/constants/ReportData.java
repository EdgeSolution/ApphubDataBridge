package com.advantech.apphub.databridge.constants;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:   ReportData
 * Description: This class defines a report data format. It is used by SolutionApp to
 * *           actively send data to web applications through Apphub databridge.
 * CreateDate   2021/08/20
 * Author:  fengchao.dai@advantech.com.cn
 */
public class ReportData implements Parcelable {

    /**
     * The package name of SolutionApp, which is used to indicate to the apphub databridge
     * and the web application which application the data comes from
     */
    private String pkgName;


    /**
     * Function name, used to indicate the type of event reported to the apphub databridge
     * and web application
     */
    private String functionId;


    /**
     * Data content sent
     */
    private String content;


    //Clone object method.
    public void copyFrom(ReportData obj) {
        this.pkgName = obj.pkgName;
        this.functionId = obj.functionId;
        this.content = obj.content;
    }

    //Parcelable impl step 1:   Constructor with Parcel parameter
    public ReportData(String pkgName, String functionId, String content) {
        this.pkgName = pkgName;
        this.functionId = functionId;
        this.content = content;
    }

    public ReportData(Parcel in) {
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
        parcel.writeString(content);
    }

    //Parcelable impl step 3:   impl readFromParcel() method
    private void readFromParcel(Parcel in) {
        pkgName = in.readString();
        functionId = in.readString();
        content = in.readString();
    }

    //Parcelable impl step 4:   Assign the static field "CREATOR" in the following format
    public static final Creator<ReportData> CREATOR =
            new Creator<ReportData>() {
                public ReportData createFromParcel(Parcel in) {
                    return new ReportData(in);
                }

                public ReportData[] newArray(int size) {
                    return new ReportData[size];
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "pkgName='" + pkgName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
