package cn.xiaochuankeji.tieba.common.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class d {
    private static final byte[] a = new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8};
    private static final byte[] b = "30aa0ddd".getBytes();

    public static String a(Context context) {
        return a(context, "UUDid", "key_uudid", "30aa0ddd6ba932c6f650783133139553", ".uudid/zuiyou.conf");
    }

    public static String b(Context context) {
        return a(context, "Did", "key_did", "f020709f4cf61dc452d56ba7e59263d2", ".did/zuiyou.conf");
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        Object c = c(context.getSharedPreferences(str, 0).getString(str2, null));
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String c2;
        try {
            c2 = c(System.getString(context.getContentResolver(), str3));
            if (!TextUtils.isEmpty(c2)) {
                return c2;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c2 = c(a(str4));
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        return c2;
    }

    public static void a(Context context, String str) {
        a(context, "UUDid", "key_uudid", "30aa0ddd6ba932c6f650783133139553", ".uudid/zuiyou.conf", str);
    }

    public static void b(Context context, String str) {
        a(context, "Did", "key_did", "f020709f4cf61dc452d56ba7e59263d2", ".did/zuiyou.conf", str);
    }

    private static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        Object string;
        String b = b(str5);
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Object string2 = sharedPreferences.getString(str2, null);
        if (TextUtils.isEmpty(string2) || !string2.equals(b)) {
            sharedPreferences.edit().putString(str2, b).apply();
        }
        try {
            string = System.getString(context.getContentResolver(), str3);
            if (TextUtils.isEmpty(string) || !string.equals(b)) {
                System.putString(context.getContentResolver(), str3, b);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        string = a(str4);
        if (TextUtils.isEmpty(string) || !string.equals(b)) {
            a(str4, b);
        }
    }

    private static String a(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        String str2 = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory(), str);
            if (file.exists()) {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read > 0) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            str2 = byteArrayOutputStream.toString();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e3) {
                                }
                            }
                        } catch (Exception e4) {
                            e = e4;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                return str2;
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e7) {
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e9) {
                        e = e9;
                        byteArrayOutputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return str2;
                    } catch (Throwable th3) {
                        byteArrayOutputStream = null;
                        th = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e10) {
                    e = e10;
                    byteArrayOutputStream = null;
                    fileInputStream = null;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return str2;
                } catch (Throwable th32) {
                    byteArrayOutputStream = null;
                    fileInputStream = null;
                    th = th32;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
        }
        return str2;
    }

    private static void a(String str, String str2) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory(), str);
            file.getParentFile().mkdirs();
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str2.getBytes());
                    fileOutputStream.close();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
    }

    private static String b(String str) {
        byte[] bytes = ("zuiyou_" + str).getBytes();
        try {
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
            Key secretKeySpec = new SecretKeySpec(b, "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            bytes = instance.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(bytes, 2);
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] decode = Base64.decode(str, 2);
        try {
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
            Key secretKeySpec = new SecretKeySpec(b, "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            decode = instance.doFinal(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = new String(decode);
        if (TextUtils.isEmpty(str2) || !str2.startsWith("zuiyou_")) {
            return null;
        }
        return str2.substring("zuiyou_".length());
    }
}
