package qsbk.app.open.auth;

import android.content.Intent;
import qsbk.app.utils.Md5;

public class RequestInfo {
    public static final String KEY_CLIENT_APP_SIGNATURE = "client_signature";
    public static final String KEY_CLIENT_ID = "client_id";
    public static final String KEY_PACKAGE_NAME = "package_name";
    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_REDIRECT_URL = "redirect_uri";
    Intent a;

    public RequestInfo(Intent intent, String str, String str2) {
        this.a = intent;
        this.a.putExtra(KEY_CLIENT_APP_SIGNATURE, str);
        this.a.putExtra("package_name", str2);
    }

    public static RequestInfo getAppInfoFromLocal() {
        return null;
    }

    public static RequestInfo getAppInfoFromServer() {
        return null;
    }

    public String getPackageName() {
        return this.a.getStringExtra("package_name");
    }

    public String getClientId() {
        return this.a.getStringExtra("client_id");
    }

    public String getPermissions() {
        return this.a.getStringExtra(KEY_PERMISSIONS);
    }

    public String getClientSignature() {
        return this.a.getStringExtra(KEY_CLIENT_APP_SIGNATURE);
    }

    public String getSignatureMD5() {
        return Md5.MD5(getClientSignature());
    }

    public String getRedirectURI() {
        return this.a.getStringExtra("redirect_uri");
    }
}
