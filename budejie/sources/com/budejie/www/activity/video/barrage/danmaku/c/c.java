package com.budejie.www.activity.video.barrage.danmaku.c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class c {
    public static String a(InputStream inputStream) {
        byte[] b = b(inputStream);
        return b == null ? null : new String(b);
    }

    public static byte[] b(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }
}
