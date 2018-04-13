package com.meizu.cloud.pushsdk.handler.impl.fileupload;

import android.os.Environment;
import com.meizu.cloud.a.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTask {
    private static final int BUFF_SIZE = 1048576;
    private static final int M_SIZE = 1024;
    private static final String TAG = "ZipTask";
    private File zipFile;

    public ZipTask(String str) {
        this.zipFile = new File(str);
    }

    public boolean zip(String... strArr) {
        try {
            if (!this.zipFile.exists()) {
                this.zipFile.getParentFile().mkdirs();
            }
            Collection arrayList = new ArrayList();
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            for (String str : strArr) {
                File file = new File(absolutePath + str);
                if (file.exists()) {
                    arrayList.add(file);
                }
            }
            zipFiles(arrayList, this.zipFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            a.d(TAG, "zip file error " + e.getMessage());
            return false;
        }
    }

    public boolean zip(List<String> list) throws Exception {
        if (!this.zipFile.exists()) {
            this.zipFile.getParentFile().mkdirs();
        }
        Collection arrayList = new ArrayList();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        for (String str : list) {
            File file = new File(absolutePath + str);
            if (file.exists()) {
                arrayList.add(file);
            }
        }
        zipFiles(arrayList, this.zipFile);
        return true;
    }

    private void zipFiles(Collection<File> collection, File file) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File zipFile : collection) {
            zipFile(zipFile, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    private void zipFiles(Collection<File> collection, File file, String str) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File zipFile : collection) {
            zipFile(zipFile, zipOutputStream, "");
        }
        zipOutputStream.setComment(str);
        zipOutputStream.close();
    }

    private void zipFile(File file, ZipOutputStream zipOutputStream, String str) throws Exception {
        String str2 = str + (str.trim().length() == 0 ? "" : File.separator) + file.getName();
        if (file.isDirectory()) {
            for (File zipFile : file.listFiles()) {
                zipFile(zipFile, zipOutputStream, str2);
            }
            return;
        }
        a.a(TAG, "current file " + str2 + "/" + file.getName() + " size is " + (file.length() / 1024) + "KB");
        if (file.length() < 10485760) {
            byte[] bArr = new byte[1048576];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    bufferedInputStream.close();
                    zipOutputStream.flush();
                    zipOutputStream.closeEntry();
                    return;
                }
            }
        }
    }
}
