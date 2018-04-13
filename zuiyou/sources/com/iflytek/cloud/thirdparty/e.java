package com.iflytek.cloud.thirdparty;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public final class e {
    private static final char[] b = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private PrintStream a;

    private static int a(InputStream inputStream, byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            bArr[i] = (byte) read;
        }
        return bArr.length;
    }

    private void a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[57];
        this.a = new PrintStream(outputStream);
        while (true) {
            int a = a(inputStream, bArr);
            if (a != 0) {
                for (int i = 0; i < a; i += 3) {
                    if (i + 3 <= a) {
                        a(outputStream, bArr, i, 3);
                    } else {
                        a(outputStream, bArr, i, a - i);
                    }
                }
                if (a >= 57) {
                    this.a.println();
                } else {
                    return;
                }
            }
            return;
        }
    }

    private static void a(OutputStream outputStream, byte[] bArr, int i, int i2) {
        byte b;
        if (i2 == 1) {
            b = bArr[i];
            outputStream.write(b[(b >>> 2) & 63]);
            outputStream.write(b[(b << 4) & 48]);
            outputStream.write(61);
            outputStream.write(61);
        } else if (i2 == 2) {
            b = bArr[i];
            r1 = bArr[i + 1];
            outputStream.write(b[(b >>> 2) & 63]);
            outputStream.write(b[((b << 4) & 48) + ((r1 >>> 4) & 15)]);
            outputStream.write(b[(r1 << 2) & 60]);
            outputStream.write(61);
        } else {
            b = bArr[i];
            r1 = bArr[i + 1];
            byte b2 = bArr[i + 2];
            outputStream.write(b[(b >>> 2) & 63]);
            outputStream.write(b[((b << 4) & 48) + ((r1 >>> 4) & 15)]);
            outputStream.write(b[((r1 << 2) & 60) + ((b2 >>> 6) & 3)]);
            outputStream.write(b[b2 & 63]);
        }
    }

    public final String a(byte[] bArr) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString("8859_1");
        } catch (Exception e) {
            throw new Error("CharacterEncoder.encode internal error");
        }
    }
}
