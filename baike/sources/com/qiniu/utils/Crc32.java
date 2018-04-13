package com.qiniu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class Crc32 {
    public static long calc(byte[] bArr, int i, int i2) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr, i, i2);
        return crc32.getValue();
    }

    public static long calc(byte[] bArr) {
        return calc(bArr, 0, bArr.length);
    }

    public static long calc(File file) throws IOException {
        InputStream fileInputStream = new FileInputStream(file);
        long calc = calc(fileInputStream);
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        return calc;
    }

    public static long calc(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[65536];
        CRC32 crc32 = new CRC32();
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read <= 0) {
                return crc32.getValue();
            }
            crc32.update(bArr, 0, read);
        }
    }
}
