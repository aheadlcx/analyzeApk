package qsbk.app.core.utils;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;

public class SdcardUtils {
    public static boolean createFolder(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        try {
            return file.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File createNewFile(String str, String str2) throws IOException {
        if (!isSDCardMounted()) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(str, str2);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        file.setReadable(true);
        return file;
    }

    public static File createNewFile(String str) throws IOException {
        if (!isSDCardMounted()) {
            return null;
        }
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public static long getAvailableStorage() {
        long j = 0;
        if (!isSDCardMounted()) {
            return j;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (RuntimeException e) {
            return j;
        }
    }

    public static boolean isAvailableSpace(long j) {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (j < ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
