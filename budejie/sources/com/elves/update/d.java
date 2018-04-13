package com.elves.update;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import com.budejie.www.activity.video.a;
import java.io.File;

public class d {
    a a;
    Context b;
    String c = "";
    boolean d = false;
    Handler e = new d$1(this);
    private StringBuffer f = new StringBuffer("http://jie.spriteapp.com/message.php?type=android");

    public d(Context context) {
        this.b = context;
        this.a = new a(context);
    }

    private void a(String str) {
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(fromFile);
            this.b.sendBroadcast(intent);
        } catch (Exception e) {
        }
    }

    public void a(String str, String str2, boolean z, String str3) {
        new d$2(this, str, str3, z, str2).start();
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static boolean a() {
        if (a.a()) {
            return true;
        }
        return false;
    }
}
