package com.sina.weibo.sdk.web.b;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.sina.weibo.sdk.a.b;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.net.e;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.b.b.a;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class d extends b {
    private WeiboMultiMessage b;
    private String c;
    private String d;
    private byte[] e;
    private String f;
    private String g;
    private String h;

    public d(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, i, str2, str3, context);
    }

    public boolean a() {
        if (this.e == null || this.e.length <= 0) {
            return super.a();
        }
        return true;
    }

    public void a(a aVar) {
        super.a(aVar);
        e eVar = new e(c().getAuthInfo().getAppKey());
        eVar.a(SocialConstants.PARAM_IMG_URL, new String(this.e));
        new com.sina.weibo.sdk.net.a(this.a).a("http://service.weibo.com/share/mobilesdk_uppic.php", eVar, "POST", new d$1(this, aVar));
    }

    protected void a(Bundle bundle) {
        if (this.b != null) {
            this.b.toBundle(bundle);
        }
        bundle.putString(INoCaptchaComponent.token, this.f);
        bundle.putString("packageName", this.g);
        bundle.putString("hashKey", this.h);
    }

    protected void b(Bundle bundle) {
        this.b = new WeiboMultiMessage();
        this.b.toObject(bundle);
        this.f = bundle.getString(INoCaptchaComponent.token);
        this.g = bundle.getString("packageName");
        this.h = bundle.getString("hashKey");
        d();
    }

    public String b() {
        String appKey = c().getAuthInfo().getAppKey();
        Builder buildUpon = Uri.parse("http://service.weibo.com/share/mobilesdk.php").buildUpon();
        buildUpon.appendQueryParameter("title", this.d);
        buildUpon.appendQueryParameter(GameAppOperation.QQFAV_DATALINE_VERSION, "0041005000");
        if (!TextUtils.isEmpty(appKey)) {
            buildUpon.appendQueryParameter("source", appKey);
        }
        if (!TextUtils.isEmpty(this.f)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.f);
        }
        Object b = j.b(this.a, appKey);
        if (!TextUtils.isEmpty(b)) {
            buildUpon.appendQueryParameter(DeviceInfo.TAG_ANDROID_ID, b);
        }
        if (!TextUtils.isEmpty(this.g)) {
            buildUpon.appendQueryParameter("packagename", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            buildUpon.appendQueryParameter("key_hash", this.h);
        }
        if (!TextUtils.isEmpty(this.c)) {
            buildUpon.appendQueryParameter("picinfo", this.c);
        }
        buildUpon.appendQueryParameter("luicode", "10000360");
        buildUpon.appendQueryParameter("lfid", "OP_" + appKey);
        return buildUpon.build().toString();
    }

    public void a(WeiboMultiMessage weiboMultiMessage) {
        this.b = weiboMultiMessage;
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.h = str;
    }

    public void c(String str) {
        this.g = str;
    }

    private void d() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b.textObject instanceof TextObject) {
            stringBuilder.append(this.b.textObject.g + " ");
        }
        if (!(this.b.mediaObject == null || !(this.b.mediaObject instanceof WebpageObject) || TextUtils.isEmpty(this.b.mediaObject.a))) {
            stringBuilder.append(this.b.mediaObject.a);
        }
        if (this.b.imageObject instanceof ImageObject) {
            ImageObject imageObject = this.b.imageObject;
            a(imageObject.h, imageObject.g);
        }
        this.d = stringBuilder.toString();
    }

    private void a(String str, byte[] bArr) {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.canRead() && file.length() > 0) {
                    byte[] bArr2 = new byte[((int) file.length())];
                    FileInputStream fileInputStream2 = null;
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            fileInputStream.read(bArr2);
                            this.e = b.b(bArr2);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    return;
                                } catch (Exception e) {
                                    return;
                                }
                            }
                            return;
                        } catch (IOException e2) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (bArr != null) {
                            }
                            return;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            fileInputStream2 = fileInputStream;
                            th = th3;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (bArr != null) {
                            return;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (SecurityException e6) {
        }
        if (bArr != null && bArr.length > 0) {
            this.e = b.b(bArr);
        }
    }
}
