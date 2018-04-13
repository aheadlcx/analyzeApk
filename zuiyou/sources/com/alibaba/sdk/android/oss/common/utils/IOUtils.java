package com.alibaba.sdk.android.oss.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {
    private static final int BUFFER_SIZE = 4096;

    public static String readStreamAsString(InputStream inputStream, String str) throws IOException {
        Throwable th;
        if (inputStream == null) {
            return "";
        }
        Writer stringWriter = new StringWriter();
        char[] cArr = new char[1024];
        Reader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                try {
                    int read = bufferedReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    stringWriter.write(cArr, 0, read);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            String obj = stringWriter.toString();
            inputStream.close();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (stringWriter == null) {
                return obj;
            }
            stringWriter.close();
            return obj;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            inputStream.close();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (stringWriter != null) {
                stringWriter.close();
            }
            throw th;
        }
    }

    public static byte[] readStreamAsBytesArray(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] readStreamAsBytesArray(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        long j = 0;
        while (j < ((long) i)) {
            int read = inputStream.read(bArr, 0, Math.min(2048, (int) (((long) i) - j)));
            if (read <= -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            j += (long) read;
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static void readStreamToFile(InputStream inputStream, File file) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                return;
            }
        }
    }

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }
}
