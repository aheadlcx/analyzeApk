package com.budejie.www.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.budejie.www.R;
import com.elves.update.DownloadServer;

public class BudejieDownloadHelper {
    public static DownloadStatus a = DownloadStatus.successed;

    public enum DownloadStatus {
        successed,
        failed,
        loading
    }

    public static void a(Context context, BudejieDownloadHelper$a budejieDownloadHelper$a) {
        Dialog dialog = new Dialog(context, R.style.custom_download_dlg);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.download_dialog_layout, null);
        ((ImageView) inflate.findViewById(R.id.download_dialog_cancel)).setOnClickListener(new BudejieDownloadHelper$1(dialog));
        ((Button) inflate.findViewById(R.id.download_apk_btn)).setOnClickListener(new BudejieDownloadHelper$2(budejieDownloadHelper$a, dialog));
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static void a(Context context, String str) {
        String str2 = Environment.getExternalStorageDirectory().getPath() + "/budejie/shipin";
        Intent intent = new Intent(context, DownloadServer.class);
        intent.putExtra("apkPath", str2);
        intent.putExtra("url", "http://d.spriteapp.cn/android/57/20150320/64538170/ev_library_1.0.apk");
        intent.putExtra("apkName", "ev_library.apk");
        context.startService(intent);
    }

    public static boolean b(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
