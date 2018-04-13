package com.tencent.weibo.sdk.android.component.sso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.weibo.sdk.android.component.sso.tools.Cryptor;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AuthReceiver extends BroadcastReceiver {
    static final String ACTION = "com.tencent.sso.AUTH";

    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(ACTION) || !intent.getStringExtra("com.tencent.sso.PACKAGE_NAME").equals(context.getPackageName())) {
            return;
        }
        if (intent.getBooleanExtra("com.tencent.sso.AUTH_RESULT", false)) {
            String stringExtra = intent.getStringExtra("com.tencent.sso.WEIBO_NICK");
            WeiboToken revert = revert(new Cryptor().decrypt(intent.getByteArrayExtra("com.tencent.sso.ACCESS_TOKEN"), "&-*)Wb5_U,[^!9'+".getBytes(), 10));
            if (AuthHelper.listener != null) {
                AuthHelper.listener.onAuthPassed(stringExtra, revert);
                return;
            }
            return;
        }
        int intExtra = intent.getIntExtra("com.tencent.sso.RESULT", 1);
        String stringExtra2 = intent.getStringExtra("com.tencent.sso.WEIBO_NICK");
        if (AuthHelper.listener != null) {
            AuthHelper.listener.onAuthFail(intExtra, stringExtra2);
        }
    }

    private WeiboToken revert(byte[] bArr) {
        WeiboToken weiboToken = new WeiboToken();
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            weiboToken.accessToken = dataInputStream.readUTF();
            weiboToken.expiresIn = dataInputStream.readLong();
            weiboToken.refreshToken = dataInputStream.readUTF();
            weiboToken.openID = dataInputStream.readUTF();
            weiboToken.omasToken = dataInputStream.readUTF();
            weiboToken.omasKey = dataInputStream.readUTF();
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                }
            }
            if (dataInputStream == null) {
                return weiboToken;
            }
            try {
                dataInputStream.close();
                return weiboToken;
            } catch (IOException e2) {
                return weiboToken;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e4) {
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e5) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e6) {
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e7) {
                }
            }
        }
    }
}
