package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.Base64;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam.ExtraTaskCallback;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ShareWebViewRequestParam extends BaseWebViewRequestParam {
    private WeiboMultiMessage b;
    private String c;
    private String d;
    private byte[] e;
    private String f;
    private String g;
    private String h;

    public ShareWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        this(authInfo, webRequestType, str, 0, str2, str3, context);
    }

    public ShareWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, i, str2, str3, context);
    }

    public boolean hasExtraTask() {
        if (this.e == null || this.e.length <= 0) {
            return super.hasExtraTask();
        }
        return true;
    }

    public void doExtraTask(ExtraTaskCallback extraTaskCallback) {
        super.doExtraTask(extraTaskCallback);
        WeiboParameters weiboParameters = new WeiboParameters(getBaseData().getAuthInfo().getAppKey());
        weiboParameters.put(SocialConstants.PARAM_IMG_URL, new String(this.e));
        new AsyncWeiboRunner(this.a).requestAsync("http://service.weibo.com/share/mobilesdk_uppic.php", weiboParameters, "POST", new b(this, extraTaskCallback));
    }

    protected void a(Bundle bundle) {
        if (this.b != null) {
            this.b.toBundle(bundle);
        }
        bundle.putString("token", this.f);
        bundle.putString("packageName", this.g);
        bundle.putString("hashKey", this.h);
    }

    protected void b(Bundle bundle) {
        this.b = new WeiboMultiMessage();
        this.b.toObject(bundle);
        this.f = bundle.getString("token");
        this.g = bundle.getString("packageName");
        this.h = bundle.getString("hashKey");
        a();
    }

    public String getRequestUrl() {
        String appKey = getBaseData().getAuthInfo().getAppKey();
        Builder buildUpon = Uri.parse("http://service.weibo.com/share/mobilesdk.php").buildUpon();
        buildUpon.appendQueryParameter("title", this.d);
        buildUpon.appendQueryParameter("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        if (!TextUtils.isEmpty(appKey)) {
            buildUpon.appendQueryParameter("source", appKey);
        }
        if (!TextUtils.isEmpty(this.f)) {
            buildUpon.appendQueryParameter("access_token", this.f);
        }
        Object aid = Utility.getAid(this.a, appKey);
        if (!TextUtils.isEmpty(aid)) {
            buildUpon.appendQueryParameter("aid", aid);
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

    public void updateRequestUrl(String str) {
        this.c = str;
    }

    public void setMultiMessage(WeiboMultiMessage weiboMultiMessage) {
        this.b = weiboMultiMessage;
    }

    public void setToken(String str) {
        this.f = str;
    }

    public void setHashKey(String str) {
        this.h = str;
    }

    public void setPackageName(String str) {
        this.g = str;
    }

    private void a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b.textObject instanceof TextObject) {
            stringBuilder.append(this.b.textObject.text);
        }
        if (this.b.imageObject instanceof ImageObject) {
            ImageObject imageObject = this.b.imageObject;
            a(imageObject.imagePath, imageObject.imageData);
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
                            this.e = Base64.encodebyte(bArr2);
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
            this.e = Base64.encodebyte(bArr);
        }
    }
}
