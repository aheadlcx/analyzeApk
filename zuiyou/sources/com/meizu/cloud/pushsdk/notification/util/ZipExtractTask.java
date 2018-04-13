package com.meizu.cloud.pushsdk.notification.util;

import android.os.SystemClock;
import com.meizu.cloud.a.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ZipExtractTask {
    private static final String LOG_TAG = "ZipExtractTask";
    private String mEextractedDir = this.mOutput.getAbsolutePath();
    private final File mInput;
    private final File mOutput;

    public ZipExtractTask(String str, String str2) {
        this.mInput = new File(str);
        this.mOutput = new File(str2);
        a.a(LOG_TAG, "Extract mInput file = " + this.mInput.toString());
        a.a(LOG_TAG, "Extract mOutput file = " + this.mOutput.toString());
    }

    private void deleteZipFile() {
        if (this.mInput != null && this.mInput.exists()) {
            if (this.mInput.delete()) {
                a.a(LOG_TAG, "Delete file:" + this.mInput.toString() + " after extracted.");
            } else {
                a.a(LOG_TAG, "Can't delete file:" + this.mInput.toString() + " after extracted.");
            }
        }
    }

    public boolean doUnzipSync() {
        return unzip() > 0;
    }

    private long unzip() {
        ZipException zipException;
        String str;
        ZipException zipException2;
        IOException iOException;
        IOException iOException2;
        long j;
        Object obj;
        Throwable th;
        String str2 = null;
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        long j2 = 0;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(this.mInput);
            try {
                Object obj2;
                Object obj3;
                Enumeration entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    try {
                        ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                        if (!zipEntry.isDirectory()) {
                            String name = zipEntry.getName();
                            if (str2 == null && name != null) {
                                str2 = name.split("/")[0];
                                a.a(LOG_TAG, "Extract temp directory=" + this.mOutput + "/" + str2);
                            }
                            File file = new File(this.mOutput, name);
                            if (!file.getParentFile().exists()) {
                                if (file.getParentFile().mkdirs()) {
                                    a.a(LOG_TAG, "Make Destination directory=" + file.getParentFile().getAbsolutePath());
                                } else {
                                    a.a(LOG_TAG, "Can't make destination directory=" + file.getParentFile().getAbsolutePath());
                                }
                            }
                            OutputStream fileOutputStream = new FileOutputStream(file);
                            j2 += (long) copy(zipFile.getInputStream(zipEntry), fileOutputStream);
                            fileOutputStream.close();
                        }
                    } catch (ZipException e) {
                        zipException = e;
                        str = str2;
                        zipException2 = zipException;
                    } catch (IOException e2) {
                        iOException = e2;
                        str = str2;
                        iOException2 = iOException;
                    }
                }
                str = this.mOutput + "/" + str2;
                if (this.mEextractedDir.equals(str)) {
                    obj2 = null;
                } else {
                    FileUtil.copyFolder(str, this.mEextractedDir);
                    obj2 = 1;
                }
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e3) {
                        a.d(LOG_TAG, "Extracted IOException:" + e3.toString());
                        j = j2;
                        obj3 = obj2;
                        str = str2;
                        obj = obj3;
                    }
                }
                j = j2;
                obj3 = obj2;
                str = str2;
                obj = obj3;
            } catch (ZipException e4) {
                zipException = e4;
                str = null;
                zipException2 = zipException;
                try {
                    a.d(LOG_TAG, "ZipException :" + zipException2.toString());
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException iOException22) {
                            a.d(LOG_TAG, "Extracted IOException:" + iOException22.toString());
                            obj = null;
                            j = j2;
                        }
                    }
                    obj = null;
                    j = j2;
                    a.a(LOG_TAG, "Extract file " + this.mInput + ", UseTime =" + String.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                    if (obj != null) {
                        FileUtil.deleteDirectory(this.mOutput + "/" + str);
                    }
                    deleteZipFile();
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException iOException222) {
                            a.d(LOG_TAG, "Extracted IOException:" + iOException222.toString());
                        }
                    }
                    throw th;
                }
            } catch (IOException e22) {
                iOException = e22;
                str = null;
                iOException222 = iOException;
                a.d(LOG_TAG, "Extracted IOException:" + iOException222.toString());
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException iOException2222) {
                        a.d(LOG_TAG, "Extracted IOException:" + iOException2222.toString());
                        obj = null;
                        j = j2;
                    }
                }
                obj = null;
                j = j2;
                a.a(LOG_TAG, "Extract file " + this.mInput + ", UseTime =" + String.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                if (obj != null) {
                    FileUtil.deleteDirectory(this.mOutput + "/" + str);
                }
                deleteZipFile();
                return j;
            }
        } catch (ZipException e42) {
            zipFile = null;
            zipException2 = e42;
            str = null;
            a.d(LOG_TAG, "ZipException :" + zipException2.toString());
            if (zipFile != null) {
                zipFile.close();
            }
            obj = null;
            j = j2;
            a.a(LOG_TAG, "Extract file " + this.mInput + ", UseTime =" + String.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
            if (obj != null) {
                FileUtil.deleteDirectory(this.mOutput + "/" + str);
            }
            deleteZipFile();
            return j;
        } catch (IOException e222) {
            zipFile = null;
            iOException2222 = e222;
            str = null;
            a.d(LOG_TAG, "Extracted IOException:" + iOException2222.toString());
            if (zipFile != null) {
                zipFile.close();
            }
            obj = null;
            j = j2;
            a.a(LOG_TAG, "Extract file " + this.mInput + ", UseTime =" + String.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
            if (obj != null) {
                FileUtil.deleteDirectory(this.mOutput + "/" + str);
            }
            deleteZipFile();
            return j;
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
            if (zipFile != null) {
                zipFile.close();
            }
            throw th;
        }
        a.a(LOG_TAG, "Extract file " + this.mInput + ", UseTime =" + String.valueOf(SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
        if (obj != null) {
            FileUtil.deleteDirectory(this.mOutput + "/" + str);
        }
        deleteZipFile();
        return j;
    }

    private int copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        int i = 0;
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i = read + i;
            } catch (IOException e) {
                a.d(LOG_TAG, "Extracted IOException:" + e.toString());
                try {
                    bufferedOutputStream.close();
                } catch (IOException e2) {
                    a.d(LOG_TAG, "out.close() IOException e=" + e2.toString());
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e22) {
                    a.d(LOG_TAG, "in.close() IOException e=" + e22.toString());
                }
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e3) {
                    a.d(LOG_TAG, "out.close() IOException e=" + e3.toString());
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e32) {
                    a.d(LOG_TAG, "in.close() IOException e=" + e32.toString());
                }
                throw th;
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
        } catch (IOException e222) {
            a.d(LOG_TAG, "out.close() IOException e=" + e222.toString());
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e2222) {
            a.d(LOG_TAG, "in.close() IOException e=" + e2222.toString());
        }
        return i;
    }
}
