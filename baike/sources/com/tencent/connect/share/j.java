package com.tencent.connect.share;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.tencent.open.a.f;
import com.tencent.open.utils.i;
import java.util.ArrayList;
import qsbk.app.core.utils.PictureGetHelper;

final class j implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Handler b;

    j(ArrayList arrayList, Handler handler) {
        this.a = arrayList;
        this.b = handler;
    }

    public void run() {
        for (int i = 0; i < this.a.size(); i++) {
            Object obj = (String) this.a.get(i);
            if (!i.g((String) obj) && i.h(obj)) {
                Bitmap a = a.a((String) obj, 10000);
                if (a != null) {
                    String str = Environment.getExternalStorageDirectory() + "/tmp/";
                    String str2 = "share2qzone_temp" + i.f((String) obj) + ".jpg";
                    if (a.b((String) obj, (int) PictureGetHelper.IMAGE_SIZE, 10000)) {
                        f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound, compress!");
                        obj = a.a(a, str, str2);
                    } else {
                        f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                    }
                    if (obj != null) {
                        this.a.set(i, obj);
                    }
                }
            }
        }
        Message obtainMessage = this.b.obtainMessage(101);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("images", this.a);
        obtainMessage.setData(bundle);
        this.b.sendMessage(obtainMessage);
    }
}
