package cn.v6.sixrooms.utils;

import android.os.Build;
import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CheckIsCompatibleCPU {
    public static boolean hasCompatibleCPU() {
        String str = Build.CPU_ABI;
        String str2 = "none";
        if (VERSION.SDK_INT >= 8) {
            try {
                str2 = (String) Build.class.getDeclaredField("CPU_ABI2").get(null);
            } catch (Exception e) {
                return false;
            }
        }
        if (str.equals("armeabi-v7a") || r0.equals("armeabi-v7a")) {
            return true;
        }
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine;
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    fileReader.close();
                    return false;
                }
            } while (!readLine.contains("ARMv7"));
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
