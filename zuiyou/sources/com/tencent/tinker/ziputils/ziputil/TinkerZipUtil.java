package com.tencent.tinker.ziputils.ziputil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TinkerZipUtil {
    private static final int BUFFER_SIZE = 16384;

    public static void extractTinkerEntry(TinkerZipFile tinkerZipFile, TinkerZipEntry tinkerZipEntry, TinkerZipOutputStream tinkerZipOutputStream) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = tinkerZipFile.getInputStream(tinkerZipEntry);
            tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry));
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                tinkerZipOutputStream.write(bArr, 0, read);
            }
            tinkerZipOutputStream.closeEntry();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void extractTinkerEntry(TinkerZipEntry tinkerZipEntry, InputStream inputStream, TinkerZipOutputStream tinkerZipOutputStream) throws IOException {
        tinkerZipOutputStream.putNextEntry(tinkerZipEntry);
        byte[] bArr = new byte[16384];
        int read = inputStream.read(bArr);
        while (read != -1) {
            tinkerZipOutputStream.write(bArr, 0, read);
            read = inputStream.read(bArr);
        }
        tinkerZipOutputStream.closeEntry();
    }

    public static void extractLargeModifyFile(TinkerZipEntry tinkerZipEntry, File file, long j, TinkerZipOutputStream tinkerZipOutputStream) throws IOException {
        Throwable th;
        TinkerZipEntry tinkerZipEntry2 = new TinkerZipEntry(tinkerZipEntry);
        tinkerZipEntry2.setMethod(0);
        tinkerZipEntry2.setSize(file.length());
        tinkerZipEntry2.setCompressedSize(file.length());
        tinkerZipEntry2.setCrc(j);
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                tinkerZipOutputStream.putNextEntry(new TinkerZipEntry(tinkerZipEntry2));
                byte[] bArr = new byte[16384];
                for (int read = bufferedInputStream.read(bArr); read != -1; read = bufferedInputStream.read(bArr)) {
                    tinkerZipOutputStream.write(bArr, 0, read);
                }
                tinkerZipOutputStream.closeEntry();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }
}
