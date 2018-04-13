package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

class ag {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final int g;
    public final String h;
    public final String i;
    public final y j;

    public static void a(File file, FilenameFilter filenameFilter, int i, Comparator<File> comparator) {
        File[] listFiles = file.listFiles(filenameFilter);
        if (listFiles != null && listFiles.length > i) {
            Arrays.sort(listFiles, comparator);
            int length = listFiles.length;
            int length2 = listFiles.length;
            int i2 = 0;
            while (i2 < length2) {
                File file2 = listFiles[i2];
                if (length > i) {
                    file2.delete();
                    length--;
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public ag(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7, String str8, y yVar) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = i;
        this.h = str7;
        this.i = str8;
        this.j = yVar;
    }
}
