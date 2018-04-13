package com.ishumei.d;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.ishumei.f.d;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class e {
    private static e d = null;
    private String a = "";
    private String b = "";
    private Context c = null;
    private final FileFilter e = new FileFilter(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean accept(File file) {
            String name = file.getName();
            try {
                if (!name.startsWith(d.g("9c8f8a"))) {
                    return false;
                }
                for (int i = 3; i < name.length(); i++) {
                    if (!Character.isDigit(name.charAt(i))) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    };

    private e() {
        try {
            this.c = com.ishumei.b.d.a;
            g();
        } catch (Exception e) {
        }
    }

    private int a(String str) {
        Closeable fileInputStream;
        Closeable bufferedReader;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            } catch (IOException e) {
                bufferedReader = fileInputStream;
                com.ishumei.f.e.a(closeable);
                com.ishumei.f.e.a(bufferedReader);
                return -1;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                bufferedReader = null;
                th = th3;
                com.ishumei.f.e.a(bufferedReader);
                com.ishumei.f.e.a(fileInputStream);
                throw th;
            }
            try {
                int b = b(bufferedReader.readLine());
                com.ishumei.f.e.a(bufferedReader);
                com.ishumei.f.e.a(fileInputStream);
                return b;
            } catch (IOException e2) {
                closeable = bufferedReader;
                bufferedReader = fileInputStream;
                com.ishumei.f.e.a(closeable);
                com.ishumei.f.e.a(bufferedReader);
                return -1;
            } catch (Throwable th4) {
                th = th4;
                com.ishumei.f.e.a(bufferedReader);
                com.ishumei.f.e.a(fileInputStream);
                throw th;
            }
        } catch (IOException e3) {
            bufferedReader = null;
            com.ishumei.f.e.a(closeable);
            com.ishumei.f.e.a(bufferedReader);
            return -1;
        } catch (Throwable th22) {
            fileInputStream = null;
            th = th22;
            bufferedReader = null;
            com.ishumei.f.e.a(bufferedReader);
            com.ishumei.f.e.a(fileInputStream);
            throw th;
        }
    }

    private int a(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int read = fileInputStream.read(bArr);
            int i = 0;
            while (i < read) {
                if (bArr[i] == (byte) 10 || i == 0) {
                    if (bArr[i] == (byte) 10) {
                        i++;
                    }
                    int i2 = i;
                    while (i2 < read) {
                        int i3 = i2 - i;
                        if (bArr[i2] != str.charAt(i3)) {
                            continue;
                            break;
                        } else if (i3 == str.length() - 1) {
                            return a(bArr, i2);
                        } else {
                            i2++;
                        }
                    }
                    continue;
                }
                i++;
            }
        } catch (IOException e) {
        } catch (NumberFormatException e2) {
        }
        return -1;
    }

    private int a(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != (byte) 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }

    public static e a() {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = new e();
                }
            }
        }
        return d;
    }

    private int b(String str) {
        return (str == null || !str.matches("0-[\\d]+$")) ? -1 : Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    private void g() {
        try {
            for (String split : com.ishumei.f.e.c(d.g("d08f8d909cd09c8f8a96919990"))) {
                String split2;
                String[] split3 = split2.split(":");
                if (2 == split3.length) {
                    CharSequence trim = split3[0].trim();
                    split2 = split3[1].trim();
                    if (TextUtils.equals(d.g("979e8d9b889e8d9a"), trim) || TextUtils.equals(d.g("899a919b908da0969b"), trim)) {
                        this.b = split2;
                    } else if (TextUtils.equals(d.g("af8d909c9a8c8c908d"), trim) || TextUtils.equals(d.g("92909b9a93df919e929a"), trim)) {
                        this.a = split2;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private int h() {
        try {
            return new File(d.g("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f908c8c969d939a")).listFiles(this.e).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public int d() {
        if (VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int a = a(d.g("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f908c8c969d939a"));
            if (a == -1) {
                a = a(d.g("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f8d9a8c9a918b"));
            }
            return a == -1 ? h() : a;
        } catch (SecurityException e) {
            return -1;
        } catch (Exception e2) {
            return -1;
        }
    }

    public int e() {
        Throwable th;
        int i = -1;
        for (int i2 = 0; i2 < d(); i2++) {
            File file = new File(d.g("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad09c8f8a") + i2 + d.g("d09c8f8a998d9a8ed09c8f8a96919990a0929e87a0998d9a8e"));
            if (file.exists()) {
                byte[] bArr = new byte[128];
                Closeable fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(bArr);
                    int i3 = 0;
                    while (Character.isDigit(bArr[i3]) && i3 < 128) {
                        i3++;
                    }
                    Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                    if (valueOf.intValue() > i) {
                        i = valueOf.intValue();
                    }
                    com.ishumei.f.e.a(fileInputStream);
                } catch (NumberFormatException e) {
                    com.ishumei.f.e.a(fileInputStream);
                } catch (Exception e2) {
                    return -1;
                } catch (Throwable th2) {
                    com.ishumei.f.e.a(fileInputStream);
                }
            }
        }
        if (i != -1) {
            return i;
        }
        Closeable fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(d.g("d08f8d909cd09c8f8a96919990"));
            try {
                int a = a(d.g("9c8f8adfb2b785"), (FileInputStream) fileInputStream2) * 1000;
                if (a > i) {
                    i = a;
                }
                com.ishumei.f.e.a(fileInputStream2);
                return i;
            } catch (Throwable th3) {
                th = th3;
                com.ishumei.f.e.a(fileInputStream2);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream2 = null;
            com.ishumei.f.e.a(fileInputStream2);
            throw th;
        }
    }

    @TargetApi(16)
    public long f() {
        Throwable th;
        if (this.c != null) {
            if (VERSION.SDK_INT >= 16) {
                try {
                    MemoryInfo memoryInfo = new MemoryInfo();
                    ((ActivityManager) this.c.getSystemService("activity")).getMemoryInfo(memoryInfo);
                    return memoryInfo.totalMem;
                } catch (Exception e) {
                }
            } else {
                Closeable closeable = null;
                try {
                    Closeable fileInputStream = new FileInputStream(d.g("d08f8d909cd0929a9296919990"));
                    try {
                        long a = ((long) a(d.g("b29a92ab908b9e93"), (FileInputStream) fileInputStream)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        try {
                            com.ishumei.f.e.a(fileInputStream);
                            return a;
                        } catch (Exception e2) {
                            return a;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = fileInputStream;
                        try {
                            com.ishumei.f.e.a(closeable);
                            throw th;
                        } catch (Exception e3) {
                            return -1;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    com.ishumei.f.e.a(closeable);
                    throw th;
                }
            }
        }
        return 0;
    }
}
