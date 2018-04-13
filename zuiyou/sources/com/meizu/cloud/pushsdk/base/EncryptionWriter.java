package com.meizu.cloud.pushsdk.base;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

class EncryptionWriter implements ILogWriter {
    private String TAG = "EncryptionWriter";
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private EncryptBase64 mEncryptBase64 = new EncryptBase64("lo");
    private int mFileCount = 7;
    private String mFileSuffixName = ".log.txt";
    private BufferedWriter mWriter;

    class ComparatorByLastModified implements Comparator<File> {
        ComparatorByLastModified() {
        }

        public int compare(File file, File file2) {
            long lastModified = file.lastModified() - file2.lastModified();
            if (lastModified > 0) {
                return -1;
            }
            if (lastModified == 0) {
                return 0;
            }
            return 1;
        }
    }

    void checkFileCount(File file) {
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(EncryptionWriter.this.mFileSuffixName);
            }
        });
        if (listFiles != null || listFiles.length > this.mFileCount) {
            Arrays.sort(listFiles, new ComparatorByLastModified());
            for (int i = this.mFileCount; i < listFiles.length; i++) {
                listFiles[i].delete();
            }
        }
    }

    public void open(String str) throws IOException {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            String format = this.mDateFormat.format(new Date());
            File file2 = new File(str, format + this.mFileSuffixName);
            if (!file2.exists()) {
                if (file2.createNewFile()) {
                    checkFileCount(file);
                } else {
                    Log.e(this.TAG, "create new file " + format + " failed !!!");
                }
            }
            this.mWriter = new BufferedWriter(new FileWriter(file2, true));
            return;
        }
        throw new IOException("create " + str + " dir failed!!!");
    }

    public void write(String str, String str2, String str3) throws IOException {
        if (this.mWriter != null) {
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.append(str2);
            stringBuffer.append(" ");
            stringBuffer.append(str3);
            this.mWriter.write(this.mEncryptBase64.encode(stringBuffer.toString().getBytes()));
            this.mWriter.write("\r\n");
        }
    }

    public void close() throws IOException {
        if (this.mWriter != null) {
            this.mWriter.flush();
            this.mWriter.close();
            this.mWriter = null;
        }
    }
}
