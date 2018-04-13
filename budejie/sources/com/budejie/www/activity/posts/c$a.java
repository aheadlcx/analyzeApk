package com.budejie.www.activity.posts;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import com.budejie.www.activity.htmlpage.a;
import com.budejie.www.label.widget.ProgressWebView.c;
import com.tencent.smtt.sdk.ValueCallback;
import java.io.File;

public class c$a implements c {
    final /* synthetic */ c a;

    public c$a(c cVar) {
        this.a = cVar;
    }

    public void a(ValueCallback<Uri> valueCallback, String str) {
        if (this.a.c == null) {
            this.a.c = valueCallback;
            a.c(this.a.d);
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            this.a.startActivityForResult(Intent.createChooser(intent, null), 3);
            this.a.d = Environment.getExternalStorageDirectory().getPath() + "/fuiou_wmp/temp";
            new File(this.a.d).mkdirs();
            this.a.d += File.separator + "compress.jpg";
        }
    }
}
