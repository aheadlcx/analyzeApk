package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;

public class AuthInfo implements Parcelable {
    public static final Creator<AuthInfo> CREATOR = new b();
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";

    public AuthInfo(Context context, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = context.getPackageName();
        this.e = Utility.getSign(context, this.d);
    }

    public String getAppKey() {
        return this.a;
    }

    public String getRedirectUrl() {
        return this.b;
    }

    public String getScope() {
        return this.c;
    }

    public String getPackageName() {
        return this.d;
    }

    public String getKeyHash() {
        return this.e;
    }

    public Bundle getAuthBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.SSO_APP_KEY, this.a);
        bundle.putString(WBConstants.SSO_REDIRECT_URL, this.b);
        bundle.putString("scope", this.c);
        bundle.putString("packagename", this.d);
        bundle.putString("key_hash", this.e);
        return bundle;
    }

    public static AuthInfo parseBundleData(Context context, Bundle bundle) {
        return new AuthInfo(context, bundle.getString(WBConstants.SSO_APP_KEY), bundle.getString(WBConstants.SSO_REDIRECT_URL), bundle.getString("scope"));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    protected AuthInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
