package cz.msebera.android.httpclient.extras;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;

@TargetApi(4)
public final class PRNGFixes {
    private static final byte[] a = f();

    public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
        private static final File a = new File("/dev/urandom");
        private static final Object b = new Object();
        private static DataInputStream c;
        private static OutputStream d;
        private boolean e;

        protected void engineSetSeed(byte[] bArr) {
            try {
                OutputStream b;
                synchronized (b) {
                    b = b();
                }
                b.write(bArr);
                b.flush();
                this.e = true;
            } catch (IOException e) {
                try {
                    Log.w(PRNGFixes.class.getSimpleName(), "Failed to mix seed into " + a);
                } finally {
                    this.e = true;
                }
            }
        }

        protected void engineNextBytes(byte[] bArr) {
            if (!this.e) {
                engineSetSeed(PRNGFixes.d());
            }
            try {
                DataInputStream a;
                synchronized (b) {
                    a = a();
                }
                synchronized (a) {
                    a.readFully(bArr);
                }
            } catch (Throwable e) {
                throw new SecurityException("Failed to read from " + a, e);
            }
        }

        protected byte[] engineGenerateSeed(int i) {
            byte[] bArr = new byte[i];
            engineNextBytes(bArr);
            return bArr;
        }

        private DataInputStream a() {
            DataInputStream dataInputStream;
            synchronized (b) {
                if (c == null) {
                    try {
                        c = new DataInputStream(new FileInputStream(a));
                    } catch (Throwable e) {
                        throw new SecurityException("Failed to open " + a + " for reading", e);
                    }
                }
                dataInputStream = c;
            }
            return dataInputStream;
        }

        private OutputStream b() throws IOException {
            OutputStream outputStream;
            synchronized (b) {
                if (d == null) {
                    d = new FileOutputStream(a);
                }
                outputStream = d;
            }
            return outputStream;
        }
    }

    private static class a extends Provider {
        public a() {
            super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
            put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

    private PRNGFixes() {
    }

    public static void apply() {
        b();
        c();
    }

    private static void b() throws SecurityException {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 18) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", new Class[]{byte[].class}).invoke(null, new Object[]{d()});
                int intValue = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", new Class[]{String.class, Long.TYPE}).invoke(null, new Object[]{"/dev/urandom", Integer.valueOf(1024)})).intValue();
                if (intValue != 1024) {
                    throw new IOException("Unexpected number of bytes read from Linux PRNG: " + intValue);
                }
            } catch (Throwable e) {
                throw new SecurityException("Failed to seed OpenSSL PRNG", e);
            }
        }
    }

    private static void c() throws SecurityException {
        if (VERSION.SDK_INT <= 18) {
            Provider[] providers = Security.getProviders("SecureRandom.SHA1PRNG");
            if (providers == null || providers.length < 1 || !a.class.equals(providers[0].getClass())) {
                Security.insertProviderAt(new a(), 1);
            }
            SecureRandom secureRandom = new SecureRandom();
            if (a.class.equals(secureRandom.getProvider().getClass())) {
                try {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                    if (!a.class.equals(secureRandom.getProvider().getClass())) {
                        throw new SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: " + secureRandom.getProvider().getClass());
                    }
                    return;
                } catch (Throwable e) {
                    throw new SecurityException("SHA1PRNG not available", e);
                }
            }
            throw new SecurityException("new SecureRandom() backed by wrong Provider: " + secureRandom.getProvider().getClass());
        }
    }

    private static byte[] d() {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(System.currentTimeMillis());
            dataOutputStream.writeLong(System.nanoTime());
            dataOutputStream.writeInt(Process.myPid());
            dataOutputStream.writeInt(Process.myUid());
            dataOutputStream.write(a);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    private static String e() {
        try {
            return (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] f() {
        StringBuilder stringBuilder = new StringBuilder();
        String str = Build.FINGERPRINT;
        if (str != null) {
            stringBuilder.append(str);
        }
        str = e();
        if (str != null) {
            stringBuilder.append(str);
        }
        try {
            return stringBuilder.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }
}
