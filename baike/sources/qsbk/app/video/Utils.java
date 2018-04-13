package qsbk.app.video;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.StorageUtils;

public class Utils {
    public static String getFileName(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getCacheFilePath(String str) {
        return getCacheDirPath() + File.separator + getFileName(str);
    }

    public static String getCacheDirPath() {
        return StorageUtils.getCacheDirectory(QsbkApp.mContext, true) + File.separator + "qbvideo";
    }

    private static void b(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                listFiles[i].getName();
                if (System.currentTimeMillis() - listFiles[i].lastModified() > 600000) {
                    listFiles[i].delete();
                }
            }
        }
    }

    public static void trimFile() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new o());
    }
}
