package com.budejie.www.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

class av$1 implements BudejieDownloadHelper$a {
    final /* synthetic */ Activity a;

    av$1(Activity activity) {
        this.a = activity;
    }

    public void a(View view, Dialog dialog) {
        dialog.dismiss();
        BudejieDownloadHelper.a(this.a, "http://d.spriteapp.cn/android/57/20150320/64538170/ev_library_1.0.apk");
    }
}
