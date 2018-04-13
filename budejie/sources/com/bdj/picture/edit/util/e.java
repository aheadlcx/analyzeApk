package com.bdj.picture.edit.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import com.ali.auth.third.login.LoginConstants;
import com.bdj.picture.edit.a.h;
import java.io.File;
import java.io.IOException;

public class e {
    public static final String a = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/budejie" + "/sticker/");

    public static File a(Context context, String str, String str2) {
        if (a.a()) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2);
            if (file2.exists()) {
                return file2;
            }
            try {
                file2.createNewFile();
                return file2;
            } catch (IOException e) {
                e.printStackTrace();
                return file2;
            }
        }
        Toast.makeText(context, context.getResources().getString(h.media_unmounted_msg), 0).show();
        return null;
    }

    public static boolean a(Context context, String str) {
        if (str == null) {
            return false;
        }
        if (!a.a()) {
            Toast.makeText(context, context.getResources().getString(h.media_unmounted_msg), 0).show();
            return false;
        } else if (new File(str).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static String a(String str) {
        if (str == null || str.length() < 7) {
            return null;
        }
        return str.substring(7).replace("/", "-").replace(":", "-").replace(LoginConstants.EQUAL, "-").replace("?", "-").replace("&", "-").replace(".", "-");
    }
}
