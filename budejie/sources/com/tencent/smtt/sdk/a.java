package com.tencent.smtt.sdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class a {
    public static int a = 600;
    private static int b = 0;

    public static int a() {
        IOException e;
        Throwable th;
        Throwable th2;
        int i = 0;
        if (b > 0) {
            return b;
        }
        String str = "";
        BufferedReader bufferedReader;
        try {
            int indexOf;
            String readLine;
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    indexOf = readLine.indexOf("MemTotal:");
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th3) {
                    th = th3;
                }
            } while (-1 == indexOf);
            readLine = readLine.substring(indexOf + "MemTotal:".length()).trim();
            if (readLine != null && readLine.length() != 0 && readLine.contains("k")) {
                i = Integer.parseInt(readLine.substring(0, readLine.indexOf("k")).trim()) / 1024;
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (IOException e4) {
            e3 = e4;
            bufferedReader = null;
            try {
                e3.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                b = i;
                return b;
            } catch (Throwable th4) {
                th2 = th4;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th2;
        }
        b = i;
        return b;
    }
}
