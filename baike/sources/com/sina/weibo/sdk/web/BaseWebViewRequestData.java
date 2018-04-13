package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.auth.AuthInfo;

public class BaseWebViewRequestData implements Parcelable {
    public static final Creator<BaseWebViewRequestData> CREATOR = new a();
    private String a;
    private AuthInfo b;
    private WebRequestType c;
    private String d;
    private String e;
    private int f;

    public void setCallback(String str) {
        this.d = str;
    }

    public String getCallback() {
        return this.d;
    }

    public BaseWebViewRequestData(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3) {
        this(authInfo, webRequestType, str, 0, str2, str3);
    }

    public BaseWebViewRequestData(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3) {
        this.f = 0;
        this.d = str;
        this.b = authInfo;
        this.c = webRequestType;
        this.e = str2;
        this.a = str3;
        this.f = i;
    }

    public int getCallbackType() {
        return this.f;
    }

    public void setCallbackType(int i) {
        this.f = i;
    }

    public String getSpecifyTitle() {
        return this.e;
    }

    public void setSpecifyTitle(String str) {
        this.e = str;
    }

    public String getUrl() {
        return this.a;
    }

    public AuthInfo getAuthInfo() {
        return this.b;
    }

    public WebRequestType getType() {
        return this.c;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.b = authInfo;
    }

    public void setType(WebRequestType webRequestType) {
        this.c = webRequestType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c == null ? -1 : this.c.ordinal());
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    protected BaseWebViewRequestData(Parcel parcel) {
        this.f = 0;
        this.a = parcel.readString();
        this.b = (AuthInfo) parcel.readParcelable(AuthInfo.class.getClassLoader());
        int readInt = parcel.readInt();
        this.c = readInt == -1 ? null : WebRequestType.values()[readInt];
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }
}
