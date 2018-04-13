package com.ali.auth.third.core.util;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils {
    public static void delete(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else {
            deleteDirectory(file);
        }
    }

    private static void deleteDirectory(File file) {
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                deleteDirectory(file2);
            } else if (file2.isFile()) {
                file2.delete();
            }
        }
    }

    public static void writeFileData(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFileData(Context context, String str) {
        Exception e;
        String str2 = "";
        String str3;
        try {
            FileInputStream openFileInput = context.openFileInput(str);
            int available = openFileInput.available();
            if (available <= 0) {
                return str2;
            }
            byte[] bArr = new byte[available];
            openFileInput.read(bArr);
            str3 = new String(bArr, "UTF-8");
            try {
                openFileInput.close();
                return str3;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str3 = str2;
            e = exception;
            e.printStackTrace();
            return str3;
        }
    }
}
