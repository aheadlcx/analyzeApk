package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.os.Environment;
import com.spriteapp.booklibrary.c.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {
    public static String getNativeStorePath() {
        return a.b + "nativeBookStore.txt";
    }

    public static String getFileContent(String str) {
        String str2;
        Exception e;
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            str2 = str3;
            while (true) {
                try {
                    str3 = bufferedReader.readLine();
                    if (str3 == null) {
                        break;
                    }
                    str3 = str2 + str3;
                    bufferedReader.close();
                    str2 = str3;
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str3;
            e = exception;
        }
        return str2;
        e.printStackTrace();
        return str2;
    }

    public static File getNativeStoreFile() {
        File file = new File(getNativeStorePath());
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static String createRootPath(Context context) {
        String str = "";
        if (!isSdCardAvailable() || context.getExternalCacheDir() == null) {
            return context.getCacheDir().getPath();
        }
        return context.getExternalCacheDir().getPath();
    }

    public static boolean isSdCardAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String createDir(String str) {
        try {
            File file = new File(str);
            if (file.getParentFile().exists()) {
                file.mkdir();
                return file.getAbsolutePath();
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.mkdir();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String createFile(File file) {
        try {
            if (file.getParentFile().exists()) {
                file.createNewFile();
                return file.getAbsolutePath();
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.createNewFile();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String str, String str2) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(str));
            outputStreamWriter.write(str2);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
