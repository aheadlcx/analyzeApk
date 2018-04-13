package cn.tatagou.sdk.c.a;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class a {
    private static char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] b = new byte[256];

    public static String encode(String str) {
        return new String(encode(str.getBytes()));
    }

    public static String decode(String str) {
        return new String(decode(str.toCharArray()));
    }

    public static char[] encode(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            Object obj;
            Object obj2;
            int i3 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i3 |= bArr[i2 + 1] & 255;
                obj = 1;
            } else {
                obj = null;
            }
            i3 <<= 8;
            if (i2 + 2 < bArr.length) {
                i3 |= bArr[i2 + 2] & 255;
                obj2 = 1;
            } else {
                obj2 = null;
            }
            cArr[i + 3] = a[obj2 != null ? i3 & 63 : 64];
            int i4 = i3 >> 6;
            int i5 = i + 2;
            char[] cArr2 = a;
            if (obj != null) {
                i3 = i4 & 63;
            } else {
                i3 = 64;
            }
            cArr[i5] = cArr2[i3];
            i3 = i4 >> 6;
            cArr[i + 1] = a[i3 & 63];
            cArr[i + 0] = a[(i3 >> 6) & 63];
            i2 += 3;
            i += 4;
        }
        return cArr;
    }

    public static byte[] decode(char[] cArr) {
        int i = 0;
        int length = cArr.length;
        int i2 = 0;
        while (i2 < cArr.length) {
            if (cArr[i2] > 'ÿ' || b[cArr[i2]] < (byte) 0) {
                length--;
            }
            i2++;
        }
        i2 = (length / 4) * 3;
        if (length % 4 == 3) {
            i2 += 2;
        }
        if (length % 4 == 2) {
            i2++;
        }
        byte[] bArr = new byte[i2];
        i2 = 0;
        length = 0;
        int i3 = 0;
        while (i < cArr.length) {
            int i4;
            if (cArr[i] > 'ÿ') {
                i4 = -1;
            } else {
                i4 = b[cArr[i]];
            }
            if (i4 >= 0) {
                int i5 = length << 6;
                length = i3 + 6;
                i3 = i5 | i4;
                if (length >= 8) {
                    i4 = length - 8;
                    length = i2 + 1;
                    bArr[i2] = (byte) ((i3 >> i4) & 255);
                    i2 = length;
                    length = i3;
                    i3 = i4;
                } else {
                    int i6 = i3;
                    i3 = length;
                    length = i6;
                }
            }
            i++;
        }
        if (i2 == bArr.length) {
            return bArr;
        }
        throw new Error("Miscalculated data length (wrote " + i2 + " instead of " + bArr.length + ")");
    }

    public static void encode(File file) throws IOException {
        if (file.exists()) {
            a(file, encode(a(file)));
        } else {
            System.exit(0);
        }
    }

    public static void decode(File file) throws IOException {
        if (file.exists()) {
            a(file, decode(b(file)));
        } else {
            System.exit(0);
        }
    }

    static {
        int i;
        for (i = 0; i < 256; i++) {
            b[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            b[i] = (byte) (i - 65);
        }
        for (i = 97; i <= 122; i++) {
            b[i] = (byte) ((i + 26) - 97);
        }
        for (i = 48; i <= 57; i++) {
            b[i] = (byte) ((i + 52) - 48);
        }
        b[43] = (byte) 62;
        b[47] = (byte) 63;
    }

    private static byte[] a(File file) throws IOException {
        InputStream bufferedInputStream;
        Throwable th;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        System.out.println(e);
                        throw th;
                    }
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        System.out.println(e2);
                    }
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return toByteArray;
            } catch (Throwable th3) {
                th = th3;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    private static char[] b(File file) throws IOException {
        Reader bufferedReader;
        Throwable th;
        Reader reader = null;
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        try {
            Reader fileReader = new FileReader(file);
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                reader = fileReader;
                if (charArrayWriter != null) {
                    try {
                        charArrayWriter.close();
                    } catch (Exception e) {
                        System.out.println(e);
                        throw th;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
            try {
                char[] cArr = new char[16384];
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read == -1) {
                        break;
                    } else if (read > 0) {
                        charArrayWriter.write(cArr, 0, read);
                    }
                }
                if (charArrayWriter != null) {
                    try {
                        charArrayWriter.close();
                    } catch (Exception e2) {
                        System.out.println(e2);
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return charArrayWriter.toCharArray();
            } catch (Throwable th3) {
                th = th3;
                reader = fileReader;
                if (charArrayWriter != null) {
                    charArrayWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (charArrayWriter != null) {
                charArrayWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (reader != null) {
                reader.close();
            }
            throw th;
        }
    }

    private static void a(File file, byte[] bArr) throws IOException {
        Throwable th;
        OutputStream outputStream = null;
        OutputStream bufferedOutputStream;
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
                outputStream = fileOutputStream;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e) {
                        System.out.println(e);
                        throw th;
                    }
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
            try {
                bufferedOutputStream.write(bArr);
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e2) {
                        System.out.println(e2);
                        return;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = fileOutputStream;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    private static void a(File file, char[] cArr) throws IOException {
        Writer bufferedWriter;
        Throwable th;
        Writer writer = null;
        try {
            Writer fileWriter = new FileWriter(file);
            try {
                bufferedWriter = new BufferedWriter(fileWriter);
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter = null;
                writer = fileWriter;
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw th;
                    }
                }
                if (writer != null) {
                    writer.close();
                }
                throw th;
            }
            try {
                bufferedWriter.write(cArr);
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Throwable th3) {
                th = th3;
                writer = fileWriter;
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (writer != null) {
                writer.close();
            }
            throw th;
        }
    }
}
