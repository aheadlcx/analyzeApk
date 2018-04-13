package com.tencent.connect.share;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.c;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.ArrayList;

class b implements c {
    final /* synthetic */ Bundle a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ IUiListener d;
    final /* synthetic */ Activity e;
    final /* synthetic */ QQShare f;

    b(QQShare qQShare, Bundle bundle, String str, String str2, IUiListener iUiListener, Activity activity) {
        this.f = qQShare;
        this.a = bundle;
        this.b = str;
        this.c = str2;
        this.d = iUiListener;
        this.e = activity;
    }

    public void a(int i, String str) {
        if (i == 0) {
            this.a.putString("imageLocalUrl", str);
        } else if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.c)) {
            if (this.d != null) {
                this.d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
            }
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
            return;
        }
        this.f.c(this.e, this.a, this.d);
    }

    public void a(int i, ArrayList<String> arrayList) {
    }
}
