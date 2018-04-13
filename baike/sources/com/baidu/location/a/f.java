package com.baidu.location.a;

import android.location.Location;
import com.baidu.location.Jni;
import com.baidu.location.d.b;
import com.baidu.location.d.i;
import com.baidu.location.d.j;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;
import qsbk.app.core.utils.ACache;

public class f {
    private static f a = null;
    private static String b = "Temp_in.dat";
    private static File c = new File(i.a, b);
    private static StringBuffer d = null;
    private static boolean e = true;
    private static int f = 0;
    private static int g = 0;
    private static long h = 0;
    private static long i = 0;
    private static long j = 0;
    private static double k = 0.0d;
    private static double l = 0.0d;
    private static int m = 0;
    private static int n = 0;
    private static int o = 0;
    private String p = null;
    private boolean q = true;

    private f(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.p = str;
    }

    public static f a() {
        if (a == null) {
            a = new f(b.a().c());
        }
        return a;
    }

    private String a(int i) {
        if (!c.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            if (!a(readInt, randomAccessFile.readInt(), randomAccessFile.readInt())) {
                randomAccessFile.close();
                d();
                return null;
            } else if (i == 0 || i == readInt + 1) {
                randomAccessFile.close();
                return null;
            } else {
                long j = (12 + 0) + ((long) ((i - 1) * 1024));
                randomAccessFile.seek(j);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.seek(j + 4);
                for (readInt = 0; readInt < readInt2; readInt++) {
                    bArr[readInt] = randomAccessFile.readByte();
                }
                randomAccessFile.close();
                return new String(bArr);
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean a(int i, int i2, int i3) {
        return (i < 0 || i > j.ab) ? false : (i2 < 0 || i2 > i + 1) ? false : i3 >= 1 && i3 <= i + 1 && i3 <= j.ab;
    }

    private boolean a(Location location, int i, int i2) {
        if (location == null || !j.X || !this.q) {
            return false;
        }
        if (j.Z < 5) {
            j.Z = 5;
        } else if (j.Z > 1000) {
            j.Z = 1000;
        }
        if (j.aa < 5) {
            j.aa = 5;
        } else if (j.aa > ACache.TIME_HOUR) {
            j.aa = ACache.TIME_HOUR;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        long time = location.getTime() / 1000;
        if (e) {
            f = 1;
            d = new StringBuffer("");
            d.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.p, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            g = d.length();
            h = time;
            k = longitude;
            l = latitude;
            i = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            j = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            e = false;
            return true;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(latitude, longitude, l, k, fArr);
        long j = time - h;
        if (fArr[0] < ((float) j.Z) && j < ((long) j.aa)) {
            return false;
        }
        if (d == null) {
            f++;
            g = 0;
            d = new StringBuffer("");
            d.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.p, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            g = d.length();
            h = time;
            k = longitude;
            l = latitude;
            i = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            j = (long) Math.floor((latitude * 100000.0d) + 0.5d);
        } else {
            k = longitude;
            l = latitude;
            long floor = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            long floor2 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            m = (int) (time - h);
            n = (int) (floor - i);
            o = (int) (floor2 - j);
            d.append(String.format(Locale.CHINA, "%d,%d,%d|", new Object[]{Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(o)}));
            g = d.length();
            h = time;
            i = floor;
            j = floor2;
        }
        if (g + 15 > 750) {
            a(d.toString());
            d = null;
        }
        if (f >= j.ab) {
            this.q = false;
        }
        return true;
    }

    private boolean a(String str) {
        if (str == null || !str.startsWith("&nr")) {
            return false;
        }
        if (!c.exists() && !d()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (a(readInt, readInt2, readInt3)) {
                if (j.Y) {
                    if (readInt == j.ab) {
                        if (str.equals(a(readInt3 == 1 ? j.ab : readInt3 - 1))) {
                            randomAccessFile.close();
                            return false;
                        }
                    } else if (readInt3 > 1 && str.equals(a(readInt3 - 1))) {
                        randomAccessFile.close();
                        return false;
                    }
                }
                randomAccessFile.seek(((long) (((readInt3 - 1) * 1024) + 12)) + 0);
                if (str.length() > 750) {
                    randomAccessFile.close();
                    return false;
                }
                String encode = Jni.encode(str);
                int length = encode.length();
                if (length > 1020) {
                    randomAccessFile.close();
                    return false;
                }
                randomAccessFile.writeInt(length);
                randomAccessFile.writeBytes(encode);
                if (readInt == 0) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(2);
                } else if (readInt < j.ab - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(readInt + 1);
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt + 2);
                } else if (readInt == j.ab - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(j.ab);
                    if (readInt2 == 0 || readInt2 == 1) {
                        randomAccessFile.writeInt(2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(1);
                } else if (readInt3 == readInt2) {
                    readInt = readInt3 == j.ab ? 1 : readInt3 + 1;
                    r2 = readInt == j.ab ? 1 : readInt + 1;
                    randomAccessFile.seek(4);
                    randomAccessFile.writeInt(r2);
                    randomAccessFile.writeInt(readInt);
                } else {
                    readInt = readInt3 == j.ab ? 1 : readInt3 + 1;
                    if (readInt == readInt2) {
                        r2 = readInt == j.ab ? 1 : readInt + 1;
                        randomAccessFile.seek(4);
                        randomAccessFile.writeInt(r2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt);
                }
                randomAccessFile.close();
                return true;
            }
            randomAccessFile.close();
            d();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static String b() {
        if (c == null) {
            return null;
        }
        if (!c.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (!a(readInt, readInt2, readInt3)) {
                randomAccessFile.close();
                d();
                return null;
            } else if (readInt2 == 0 || readInt2 == readInt3) {
                randomAccessFile.close();
                return null;
            } else {
                long j = 0 + ((long) (((readInt2 - 1) * 1024) + 12));
                randomAccessFile.seek(j);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j + 4);
                for (readInt3 = 0; readInt3 < readInt4; readInt3++) {
                    bArr[readInt3] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                readInt3 = readInt < j.ab ? readInt2 + 1 : readInt2 == j.ab ? 1 : readInt2 + 1;
                randomAccessFile.seek(4);
                randomAccessFile.writeInt(readInt3);
                randomAccessFile.close();
                return str;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static void c() {
        e = true;
        d = null;
        f = 0;
        g = 0;
        h = 0;
        i = 0;
        j = 0;
        k = 0.0d;
        l = 0.0d;
        m = 0;
        n = 0;
        o = 0;
    }

    private static boolean d() {
        if (c.exists()) {
            c.delete();
        }
        if (!c.getParentFile().exists()) {
            c.getParentFile().mkdirs();
        }
        try {
            c.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            c();
            return c.exists();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean a(Location location) {
        return a(location, j.Z, j.aa);
    }
}
