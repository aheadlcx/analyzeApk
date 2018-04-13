package com.umeng.commonsdk.internal.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

public class d {

    public static class a {
        public String a;
        public String b;
        public int c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
    }

    public static a a() {
        a aVar;
        int i;
        try {
            aVar = new a();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
                Object obj = 1;
                String readLine = bufferedReader.readLine();
                i = 0;
                while (true) {
                    if (readLine == null) {
                        try {
                            if (readLine == "") {
                                break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    String[] split = readLine.split(":\\s+", 2);
                    if (!(obj == null || split == null || split.length <= 1)) {
                        aVar.a = split[1];
                        obj = null;
                    }
                    if (split != null && split.length > 1 && split[0].contains("processor")) {
                        i++;
                    }
                    if (split != null && split.length > 1 && split[0].contains("Features")) {
                        aVar.d = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("implementer")) {
                        aVar.e = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("architecture")) {
                        aVar.f = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("variant")) {
                        aVar.g = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("part")) {
                        aVar.h = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("revision")) {
                        aVar.i = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("Hardware")) {
                        aVar.j = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("Revision")) {
                        aVar.k = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("Serial")) {
                        aVar.l = split[1];
                    }
                    if (split != null && split.length > 1 && split[0].contains("implementer")) {
                        aVar.e = split[1];
                    }
                    readLine = bufferedReader.readLine();
                }
            } catch (Exception e2) {
                i = 0;
            }
        } catch (Exception e3) {
            aVar = null;
            i = 0;
        }
        aVar.c = i;
        return aVar;
    }

    public static String b() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    public static String c() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception e) {
        }
        return str.trim();
    }

    public static String d() {
        String str = "";
        try {
            str = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine().trim();
        } catch (Exception e) {
        }
        return str;
    }
}
