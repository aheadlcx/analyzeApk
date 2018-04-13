package com.kubility.demo;

import java.io.File;
import java.io.IOException;

public class a {
    private File a;
    private int b = 2;
    private int c = 8000;
    private boolean d;

    private a(File file, boolean z) {
        this.a = file;
        this.d = z;
    }

    public File a() {
        return this.a;
    }

    public void a(File file) {
        this.a = file;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public static a a(File file, String str) {
        return a(file, str, false);
    }

    public static a a(File file, String str, boolean z) {
        File file2 = null;
        if (file != null) {
            if (file.exists()) {
                file2 = new File(file, str);
                if (!file2.exists()) {
                    try {
                        file2.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                file.mkdirs();
                file2 = new File(file, str);
                if (!file2.exists()) {
                    try {
                        file2.createNewFile();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return new a(file2, z);
    }
}
