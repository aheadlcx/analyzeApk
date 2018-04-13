package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.cloud.thirdparty.ae.a;
import java.util.HashMap;

public class ai {
    private ah a;
    private ae b = ae.a();

    public ai(ah ahVar) {
        this.a = ahVar;
    }

    public void a(String str, byte[] bArr) throws n {
        if (bArr == null) {
            Log.e("TextUploader", "text data is null.");
            return;
        }
        String a = al.a("text");
        ce d = ac.d(str);
        d.a("stream_id", a);
        d.a("scene", ac.a("global", "scene", ""), false);
        d.a("userparams", ac.c());
        d.a("data_type", "text");
        d.a(i.a);
        if (this.a != null) {
            d.a("dts", "5");
            String d2 = ac.d(d);
            String c = ac.c(d);
            cb.a("TextUploader", "params=" + d2.toString());
            this.a.a(d2, c, bArr, bArr.length);
            this.b.a(a, new a(Long.valueOf(System.currentTimeMillis()), null, "", new HashMap()));
            this.b.a(a, System.currentTimeMillis());
        }
    }
}
