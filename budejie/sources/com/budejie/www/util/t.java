package com.budejie.www.util;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.http.util.EncodingUtils;

public class t {
    public static void a(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a(Context context, String str) {
        String str2 = "";
        try {
            if (!new File(context.getFilesDir() + "/" + str).exists()) {
                return str2;
            }
            FileInputStream openFileInput = context.openFileInput(str);
            byte[] bArr = new byte[openFileInput.available()];
            openFileInput.read(bArr);
            str2 = EncodingUtils.getString(bArr, "UTF-8");
            openFileInput.close();
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
