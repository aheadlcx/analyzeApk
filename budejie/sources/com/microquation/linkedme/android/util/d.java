package com.microquation.linkedme.android.util;

import android.text.TextUtils;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.f.b;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class d {
    public static String a(InputStream inputStream) throws IOException {
        return a(inputStream, "UTF-8");
    }

    public static String a(InputStream inputStream, String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            str = "UTF-8";
        }
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        Reader inputStreamReader = new InputStreamReader(inputStream, str);
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = new char[1024];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read < 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                b.a(a.a, th.getMessage());
            }
        }
    }

    public static void a(OutputStream outputStream, String str) throws IOException {
        a(outputStream, str, "UTF-8");
    }

    public static void a(OutputStream outputStream, String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        Writer outputStreamWriter = new OutputStreamWriter(outputStream, str2);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }
}
