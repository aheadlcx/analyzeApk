package org.mozilla.javascript.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.commonjs.module.provider.ParsedContentType;

public class a {
    public static URL a(String str) {
        if (str.indexOf(58) >= 2) {
            try {
                return new URL(str);
            } catch (MalformedURLException e) {
            }
        }
        return null;
    }

    public static Object a(String str, boolean z, String str2) throws IOException {
        URL a = a(str);
        InputStream inputStream = null;
        if (a == null) {
            try {
                File file = new File(str);
                String str3 = null;
                int length = (int) file.length();
                InputStream fileInputStream = new FileInputStream(file);
                String str4 = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th2;
            }
        }
        String encoding;
        URLConnection openConnection = a.openConnection();
        inputStream = openConnection.getInputStream();
        if (z) {
            ParsedContentType parsedContentType = new ParsedContentType(openConnection.getContentType());
            str3 = parsedContentType.getContentType();
            encoding = parsedContentType.getEncoding();
        } else {
            str3 = null;
            encoding = null;
        }
        int contentLength = openConnection.getContentLength();
        if (contentLength > 1048576) {
            str4 = str3;
            str3 = encoding;
            fileInputStream = inputStream;
            length = -1;
        } else {
            String str5 = str3;
            str3 = encoding;
            fileInputStream = inputStream;
            length = contentLength;
            str4 = str5;
        }
        if (length <= 0) {
            length = 4096;
        }
        try {
            Object readStream = Kit.readStream(fileInputStream, length);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (!z) {
                return readStream;
            }
            if (str3 != null) {
                str2 = str3;
            } else if (readStream.length > 3 && readStream[0] == (byte) -1 && readStream[1] == (byte) -2 && readStream[2] == (byte) 0 && readStream[3] == (byte) 0) {
                str2 = "UTF-32LE";
            } else if (readStream.length > 3 && readStream[0] == (byte) 0 && readStream[1] == (byte) 0 && readStream[2] == (byte) -2 && readStream[3] == (byte) -1) {
                str2 = "UTF-32BE";
            } else if (readStream.length > 2 && readStream[0] == (byte) -17 && readStream[1] == (byte) -69 && readStream[2] == (byte) -65) {
                str2 = "UTF-8";
            } else if (readStream.length > 1 && readStream[0] == (byte) -1 && readStream[1] == (byte) -2) {
                str2 = "UTF-16LE";
            } else if (readStream.length > 1 && readStream[0] == (byte) -2 && readStream[1] == (byte) -1) {
                str2 = "UTF-16BE";
            } else if (str2 == null) {
                if (a == null) {
                    str2 = System.getProperty("file.encoding");
                } else if (str4 == null || !str4.startsWith("application/")) {
                    str2 = "US-ASCII";
                } else {
                    str2 = "UTF-8";
                }
            }
            Object str6 = new String(readStream, str2);
            if (str6.length() <= 0 || str6.charAt(0) != '﻿') {
                return str6;
            }
            return str6.substring(1);
        } catch (Throwable th3) {
            th2 = th3;
            inputStream = fileInputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th2;
        }
    }
}
