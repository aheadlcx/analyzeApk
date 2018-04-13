package com.baidu.mobads.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class u {
    public static List<File> a(String str, String str2) {
        return a(str, str2, null);
    }

    public static List<File> a(String str, String str2, String str3) {
        return a(a(str), a(str2), str3);
    }

    public static List<File> a(File file, File file2, String str) {
        if (file == null || file2 == null) {
            return null;
        }
        List<File> arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration entries = zipFile.entries();
        ZipEntry zipEntry;
        if (b(str)) {
            while (entries.hasMoreElements()) {
                zipEntry = (ZipEntry) entries.nextElement();
                if (!a(file2, arrayList, zipFile, zipEntry, zipEntry.getName())) {
                    return arrayList;
                }
            }
        }
        while (entries.hasMoreElements()) {
            zipEntry = (ZipEntry) entries.nextElement();
            String name = zipEntry.getName();
            if (name.contains(str) && !a(file2, arrayList, zipFile, zipEntry, name)) {
                return arrayList;
            }
        }
        return arrayList;
    }

    private static boolean a(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) {
        OutputStream bufferedOutputStream;
        Throwable th;
        InputStream inputStream = null;
        File file2 = new File(file + File.separator + str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            if (!a(file2)) {
                return false;
            }
        } else if (!b(file2)) {
            return false;
        } else {
            try {
                InputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = null;
                    inputStream = bufferedInputStream;
                    inputStream.close();
                    bufferedOutputStream.close();
                    throw th;
                }
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = bufferedInputStream;
                    inputStream.close();
                    bufferedOutputStream.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                inputStream.close();
                bufferedOutputStream.close();
                throw th;
            }
        }
        return true;
    }

    private static boolean a(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean b(File file) {
        boolean z = false;
        if (file == null) {
            return z;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!a(file.getParentFile())) {
            return z;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    private static File a(String str) {
        return b(str) ? null : new File(str);
    }

    private static boolean b(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
