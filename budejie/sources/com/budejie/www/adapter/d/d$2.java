package com.budejie.www.adapter.d;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.androidex.util.ImageUtil;
import com.budejie.www.util.aa;
import com.davemorrissey.labs.subscaleview.a;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.c;
import java.io.File;

class d$2 implements c {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void onLoadingStarted(String str, View view) {
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.a.f.d != null && !TextUtils.isEmpty(str)) {
            aa.b("PostDedailHeadRow", "onLoadingComplete imageUri=" + str);
            File imageFile = ImageUtil.getImageFile(str);
            if (imageFile != null) {
                aa.b("PostDedailHeadRow", "imageFile!=null");
                this.a.f.d.setImage(a.b(imageFile.getPath()));
            }
        }
    }

    public void onLoadingCancelled(String str, View view) {
    }
}
