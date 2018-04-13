package cn.htjyb.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class b {
    public static String a(InputStream inputStream, Charset charset) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            String a = a(new BufferedReader(new InputStreamReader(inputStream, charset)));
            return a;
        } finally {
            inputStream.close();
        }
    }

    public static String a(BufferedReader bufferedReader) throws IOException {
        if (bufferedReader == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(readLine).append("\n");
        }
    }
}
