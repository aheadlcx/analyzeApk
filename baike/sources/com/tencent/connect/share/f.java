package com.tencent.connect.share;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.c;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.ArrayList;

class f implements c {
    final /* synthetic */ IUiListener a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ Activity c;
    final /* synthetic */ QzoneShare d;

    f(QzoneShare qzoneShare, IUiListener iUiListener, Bundle bundle, Activity activity) {
        this.d = qzoneShare;
        this.a = iUiListener;
        this.b = bundle;
        this.c = activity;
    }

    public void a(int i, String str) {
        this.a.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
    }

    public void a(int i, ArrayList<String> arrayList) {
        if (i == 0) {
            this.b.putStringArrayList("imageUrl", arrayList);
        }
        this.d.b(this.c, this.b, this.a);
    }
}
