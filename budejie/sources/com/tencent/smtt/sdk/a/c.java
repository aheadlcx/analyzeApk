package com.tencent.smtt.sdk.a;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.p;
import com.tencent.smtt.utils.x;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

final class c extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ ThirdAppInfoNew b;

    c(String str, Context context, ThirdAppInfoNew thirdAppInfoNew) {
        this.a = context;
        this.b = thirdAppInfoNew;
        super(str);
    }

    public void run() {
        if (VERSION.SDK_INT >= 8) {
            if (b.a == null) {
                try {
                    b.a = "65dRa93L".getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    b.a = null;
                    TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                }
            }
            if (b.a == null) {
                TbsLog.e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                return;
            }
            String string = TbsDownloadConfig.getInstance(this.a).mPreferences.getString(TbsConfigKey.KEY_DESkEY_TOKEN, "");
            String str = "";
            String str2 = "";
            if (TextUtils.isEmpty(string)) {
                String str3 = str2;
                str2 = str;
                str = str3;
            } else {
                str2 = string.substring(0, string.indexOf("&"));
                str = string.substring(string.indexOf("&") + 1, string.length());
            }
            boolean z = TextUtils.isEmpty(str2) || str2.length() != 96 || TextUtils.isEmpty(str) || str.length() != 24;
            try {
                JSONObject b;
                x a = x.a();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(z ? a.b() + p.a().b() : a.f() + str2).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
                }
                try {
                    b = b.c(this.b, this.a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    b = null;
                }
                if (b == null) {
                    TbsLog.e("sdkreport", "post -- jsonData is null!");
                    return;
                }
                try {
                    byte[] bytes = b.toString().getBytes("utf-8");
                    byte[] a2 = z ? p.a().a(bytes) : p.a(bytes, str);
                    httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a2.length));
                    try {
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        outputStream.write(a2);
                        outputStream.flush();
                        if (httpURLConnection.getResponseCode() != 200) {
                            TbsLog.e("sdkreport", "Post failed -- not 200");
                        }
                    } catch (Throwable th) {
                        TbsLog.e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                    }
                } catch (Throwable th2) {
                }
            } catch (IOException e3) {
                TbsLog.e("sdkreport", "Post failed -- IOException:" + e3);
            } catch (AssertionError e4) {
                TbsLog.e("sdkreport", "Post failed -- AssertionError:" + e4);
            } catch (NoClassDefFoundError e5) {
                TbsLog.e("sdkreport", "Post failed -- NoClassDefFoundError:" + e5);
            }
        }
    }
}
