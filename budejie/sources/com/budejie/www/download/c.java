package com.budejie.www.download;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.budejie.www.download.BDJDownloadService.Command;
import com.sprite.ads.internal.log.ADLog;

public class c {
    private static Context a;
    private static c b = new c();

    public static c a() {
        return b;
    }

    public Context b() {
        return a;
    }

    public void a(String str, Context context) {
        if (a == null) {
            a = context;
        }
        if (a == null) {
            ADLog.d("下载服务无法开启，由于mContext为null");
        } else if (Environment.getExternalStorageDirectory() == null) {
            ADLog.d("下载服务无法开启，sd卡路径创建失败");
        } else {
            String concat = Environment.getExternalStorageDirectory().getPath().concat("/").concat(a.getPackageName().replace(".", "")).concat("/apkdown");
            Intent intent = new Intent(a, BDJDownloadService.class);
            intent.putExtra("command", Command.DOWNLOAD);
            intent.putExtra("download_path", concat);
            intent.putExtra("url", str);
            a.startService(intent);
        }
    }

    public void a(String str, String str2) {
        if (!str.endsWith(".apk")) {
            str = str.concat(".apk");
        }
        Intent intent = new Intent(a, BDJDownloadService.class);
        intent.putExtra("command", Command.INSTALL_APK);
        intent.putExtra("apk_name", str);
        intent.putExtra("apk_path", str2);
        a.startService(intent);
    }
}
