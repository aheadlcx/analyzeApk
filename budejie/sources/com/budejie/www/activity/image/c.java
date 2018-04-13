package com.budejie.www.activity.image;

import android.os.Environment;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.a;

public final class c {
    public static String a() {
        if (a.a()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return BudejieApplication.b().getApplicationContext().getFilesDir().getAbsolutePath();
    }
}
