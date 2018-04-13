package com.budejie.www.goddubbing.c;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.text.TextUtils;
import com.budejie.www.goddubbing.b.a;
import com.budejie.www.util.aa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class d {
    private static String a;

    public static String a() {
        return f("/RecordFinder/");
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        File file = new File(a + "/RecordFinder/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return a + "/RecordFinder/" + str;
    }

    public static String b() {
        return f("/GifVideoFinder");
    }

    public static String c() {
        return b("/GifVideoFinder", "/GifVideoName.mp4");
    }

    public static String d() {
        return f("/GifFinder");
    }

    public static String e() {
        return b("/GifFinder", "/GifName.gif");
    }

    public static String f() {
        return f("/DownloadVideoFinder");
    }

    public static String g() {
        return b("/DownloadVideoFinder", "/DownloadVideoName.mp4");
    }

    public static String h() {
        return b("/MixVideoFinder", "/MixVideoName.mp4");
    }

    public static String i() {
        return f("/TextFinder");
    }

    public static String j() {
        return b("/TextFinder", "/RecorderText.txt");
    }

    public static String k() {
        return f("/MixRecorderFinder");
    }

    public static String l() {
        return b("/MixRecorderFinder", "/MixRecorderName.mp4");
    }

    public static String m() {
        return f(a.b);
    }

    public static String n() {
        return f("/DownloadImageFinder");
    }

    public static String o() {
        return b("/DownloadImageFinder", "/DownloadImageName.jpg");
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        File file = new File(a + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath() + str2;
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        File file = new File(a + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    public static boolean a(Map<Integer, String> map) {
        BufferedWriter bufferedWriter;
        IOException iOException;
        Throwable th;
        boolean z = false;
        if (e.a(map)) {
            return false;
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(j()));
            try {
                boolean z2 = false;
                for (String str : map.values()) {
                    try {
                        if (e(str) > 0) {
                            bufferedWriter.write("file '" + str + "'");
                            bufferedWriter.newLine();
                            z2 = true;
                        }
                    } catch (IOException e) {
                        IOException iOException2 = e;
                        z = z2;
                        iOException = iOException2;
                    }
                }
                if (bufferedWriter == null) {
                    return z2;
                }
                try {
                    bufferedWriter.close();
                    return z2;
                } catch (IOException e2) {
                    if (!TextUtils.isEmpty(e2.getMessage())) {
                        aa.e("FileUtil", e2.getMessage());
                    }
                    return z2;
                }
            } catch (IOException e3) {
                iOException = e3;
                try {
                    if (!TextUtils.isEmpty(iOException.getMessage())) {
                        aa.e("FileUtil", iOException.getMessage());
                    }
                    if (bufferedWriter != null) {
                        return z;
                    }
                    try {
                        bufferedWriter.close();
                        return z;
                    } catch (IOException iOException3) {
                        if (TextUtils.isEmpty(iOException3.getMessage())) {
                            return z;
                        }
                        aa.e("FileUtil", iOException3.getMessage());
                        return z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException iOException32) {
                            if (!TextUtils.isEmpty(iOException32.getMessage())) {
                                aa.e("FileUtil", iOException32.getMessage());
                            }
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            iOException32 = e4;
            bufferedWriter = null;
            if (TextUtils.isEmpty(iOException32.getMessage())) {
                aa.e("FileUtil", iOException32.getMessage());
            }
            if (bufferedWriter != null) {
                return z;
            }
            bufferedWriter.close();
            return z;
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    public static void a(Context context) {
        if (q()) {
            a = Environment.getExternalStorageDirectory().getPath() + a.a;
        } else {
            a = context.getFilesDir().getPath() + a.a;
        }
    }

    public static String p() {
        return f(a.b);
    }

    public static boolean b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return true;
        }
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isFile() && file2.exists()) {
                file2.delete();
            }
            i++;
        }
        return true;
    }

    public static boolean q() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static int e(String str) {
        int i = 0;
        if (d(str)) {
            try {
                i = Integer.parseInt(a(str, 9));
            } catch (Exception e) {
            }
        }
        return i;
    }

    public static String a(String str, int i) {
        if (!d(str)) {
            return "";
        }
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return mediaMetadataRetriever.extractMetadata(i);
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("FileUtil", e.getMessage());
            }
            return "";
        }
    }

    public static void a(Context context, String str) {
        MediaScannerConnection.scanFile(context, new String[]{str}, null, new d$1());
    }

    public static boolean a(String str, String str2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
        while (fileInputStream.read(bArr) != -1) {
            fileOutputStream.write(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
        return true;
    }
}
