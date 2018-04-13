package com.nostra13.universalimageloader.b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class b {
    public static final Map<String, String> a = new HashMap();

    static {
        a();
    }

    private static void a() {
        a.put("jpg", "FFD8FF");
        a.put("png", "89504E47");
        a.put("gif", "47494638");
        a.put("bmp", "424D");
    }

    public static final String a(File file) {
        String a;
        FileNotFoundException e;
        IOException e2;
        byte[] bArr = new byte[50];
        try {
            InputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bArr);
            a = a(bArr);
            try {
                fileInputStream.close();
            } catch (FileNotFoundException e3) {
                e = e3;
                e.printStackTrace();
                return a;
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                return a;
            }
        } catch (FileNotFoundException e5) {
            FileNotFoundException fileNotFoundException = e5;
            a = null;
            e = fileNotFoundException;
            e.printStackTrace();
            return a;
        } catch (IOException e6) {
            IOException iOException = e6;
            a = null;
            e2 = iOException;
            e2.printStackTrace();
            return a;
        }
        return a;
    }

    public static final String a(byte[] bArr) {
        String valueOf = String.valueOf(b(bArr));
        for (Entry entry : a.entrySet()) {
            if (valueOf.toUpperCase().startsWith((String) entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    public static final String b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }
}
