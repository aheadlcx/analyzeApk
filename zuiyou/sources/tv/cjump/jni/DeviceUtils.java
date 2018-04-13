package tv.cjump.jni;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

public class DeviceUtils {
    private static ARCH a = ARCH.Unknown;

    public enum ARCH {
        Unknown,
        ARM,
        X86,
        MIPS,
        ARM64
    }

    public static synchronized ARCH a() {
        RandomAccessFile randomAccessFile;
        IOException e;
        FileNotFoundException e2;
        ARCH arch;
        Throwable th;
        synchronized (DeviceUtils.class) {
            byte[] bArr = new byte[20];
            File file = new File(Environment.getRootDirectory(), "lib/libc.so");
            if (file.canRead()) {
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        randomAccessFile.readFully(bArr);
                        int i = bArr[18] | (bArr[19] << 8);
                        switch (i) {
                            case 3:
                                a = ARCH.X86;
                                break;
                            case 8:
                                a = ARCH.MIPS;
                                break;
                            case 40:
                                a = ARCH.ARM;
                                break;
                            case 183:
                                a = ARCH.ARM64;
                                break;
                            default:
                                Log.e("NativeBitmapFactory", "libc.so is unknown arch: " + Integer.toHexString(i));
                                break;
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e2 = e4;
                        try {
                            e2.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            arch = a;
                            return arch;
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        e32 = e6;
                        e32.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                        arch = a;
                        return arch;
                    }
                } catch (FileNotFoundException e7) {
                    e2 = e7;
                    randomAccessFile = null;
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    arch = a;
                    return arch;
                } catch (IOException e8) {
                    e322 = e8;
                    randomAccessFile = null;
                    e322.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    arch = a;
                    return arch;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = null;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            }
            arch = a;
        }
        return arch;
    }

    public static String b() {
        return Build.CPU_ABI;
    }

    public static String c() {
        try {
            Field declaredField = Build.class.getDeclaredField("CPU_ABI2");
            if (declaredField == null) {
                return null;
            }
            Object obj = declaredField.get(null);
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a(String str) {
        String b = b();
        if (!TextUtils.isEmpty(b) && b.equalsIgnoreCase(str)) {
            return true;
        }
        if (TextUtils.isEmpty(c()) || !b.equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    public static boolean d() {
        return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
    }

    public static boolean e() {
        return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
    }

    public static boolean f() {
        return d() || e();
    }

    public static boolean g() {
        return (a("armeabi-v7a") || a("armeabi")) && ARCH.ARM.equals(a());
    }

    public static boolean h() {
        return a("x86") || ARCH.X86.equals(a());
    }
}
