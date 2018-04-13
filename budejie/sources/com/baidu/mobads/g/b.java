package com.baidu.mobads.g;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.baidu.mobads.a.a;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class b extends File {
    private static DexClassLoader f = null;
    private e a;
    private Class<?> b;
    private Context c;
    private PublicKey d;
    private IXAdLogger e;

    public b(String str, Context context) {
        this(str, context, null);
    }

    public b(String str, Context context, e eVar) {
        super(str);
        this.b = null;
        this.c = null;
        this.e = XAdSDKFoundationFacade.getInstance().getAdLogger();
        this.c = context;
        this.a = eVar;
        if (eVar != null) {
            try {
                String str2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBcp8gg3O7bjdnz+pSxg+JH/mbcKfm7dEjcRqVNAFwG7bTpLwDQh40bZJzrcBKQWbD6kArR6TPuQUCMQ09/y55Vk1P2Kq7vJGGisFpjlqv2qlg8drLdhXkLQUt/SeZVJgT+CNxVbuzxAF61EEf8M0MHi1I2dm6n6lOA6fomiCD9wIDAQAB";
                this.d = c("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBcp8gg3O7bjdnz+pSxg+JH/mbcKfm7dEjcRqVNAFwG7bTpLwDQh40bZJzrcBKQWbD6kArR6TPuQUCMQ09/y55Vk1P2Kq7vJGGisFpjlqv2qlg8drLdhXkLQUt/SeZVJgT+CNxVbuzxAF61EEf8M0MHi1I2dm6n6lOA6fomiCD9wIDAQAB");
            } catch (Exception e) {
                this.d = null;
            }
        }
    }

    protected void a() {
        if (this.a != null) {
            String a = a(new File(getAbsolutePath()));
            String b = b(this.a.d());
            if (!b.equalsIgnoreCase(a)) {
                throw new g$a("doCheckApkIntegrity failed, md5sum: " + a + ", checksum in json info: " + b);
            }
            return;
        }
        Log.i("XAdLocalApkFile", "built-in apk, no need to check");
    }

    protected Class<?> b() {
        if (this.b == null) {
            File file = new File(getAbsolutePath());
            try {
                this.b = b(file);
            } catch (Exception e) {
                file.delete();
            }
        }
        return this.b;
    }

    protected void a(String str) {
        renameTo(new File(str));
    }

    protected double c() {
        return this.a == null ? 0.0d : this.a.b();
    }

    private String b(String str) {
        if (this.d != null) {
            byte[] decode = Base64.decode(str, 0);
            try {
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(2, this.d);
                return new String(instance.doFinal(decode), "UTF-8").trim();
            } catch (Throwable e) {
                this.e.e("ErrorWhileVerifySigNature", e);
            }
        }
        return null;
    }

    private static PublicKey c(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("InvalidKeySpecException");
        } catch (NullPointerException e3) {
            throw new Exception("NullPointerException");
        }
    }

    private String a(File file) {
        InputStream fileInputStream;
        IXAdLogger iXAdLogger;
        Object[] objArr;
        Exception e;
        Throwable th;
        String str = "";
        try {
            fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                do {
                } while (new DigestInputStream(fileInputStream, instance).read(new byte[4096]) != -1);
                byte[] digest = instance.digest();
                int i = 0;
                while (i < digest.length) {
                    String str2 = str + Integer.toString((digest[i] & 255) + 256, 16).substring(1);
                    i++;
                    str = str2;
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        iXAdLogger = this.e;
                        objArr = new Object[]{"XAdLocalApkFile", e2.getMessage()};
                        iXAdLogger.e(objArr);
                        return str;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    this.e.e("XAdLocalApkFile", e.getMessage());
                    str = "";
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e22) {
                            iXAdLogger = this.e;
                            objArr = new Object[]{"XAdLocalApkFile", e22.getMessage()};
                            iXAdLogger.e(objArr);
                            return str;
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e222) {
                            this.e.e("XAdLocalApkFile", e222.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            this.e.e("XAdLocalApkFile", e.getMessage());
            str = "";
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    @TargetApi(14)
    private Class<?> b(File file) {
        Class<?> cls;
        this.e.d("XAdLocalApkFile", "Android version:" + VERSION.RELEASE);
        String absolutePath;
        try {
            synchronized (g.class) {
                absolutePath = file.getAbsolutePath();
                ClassLoader classLoader = getClass().getClassLoader();
                String absolutePath2 = this.c.getFilesDir().getAbsolutePath();
                String str = "com.baidu.mobads.container.AllInOneXAdContainerFactory";
                a.o = System.currentTimeMillis();
                ClassLoader dexClassLoader = new DexClassLoader(absolutePath, absolutePath2, null, classLoader);
                a.p = System.currentTimeMillis();
                f = dexClassLoader;
                this.e.i("XAdLocalApkFile", "dexPath=" + absolutePath + ", cl=" + classLoader + ", dir=" + absolutePath2 + ", loader=" + dexClassLoader + ", len=" + file.length() + ", list=" + file.list());
                cls = Class.forName("com.baidu.mobads.container.AllInOneXAdContainerFactory", true, dexClassLoader);
            }
        } catch (Exception e) {
            absolutePath = e.getMessage();
            this.e.e("XAdLocalApkFile", absolutePath);
            cls = null;
        }
        this.e.i("XAdLocalApkFile", "jar.path=" + file.getAbsolutePath() + ", clz=" + cls);
        return cls;
    }

    public static DexClassLoader d() {
        return f;
    }
}
