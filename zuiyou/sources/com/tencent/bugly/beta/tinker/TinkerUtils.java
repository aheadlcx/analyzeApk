package com.tencent.bugly.beta.tinker;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.Signature;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.tinker.lib.tinker.Tinker;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Formatter;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TinkerUtils {
    public static final int ERROR_PATCH_CONDITION_NOT_SATISFIED = -24;
    public static final int ERROR_PATCH_CRASH_LIMIT = -23;
    public static final int ERROR_PATCH_GOOGLEPLAY_CHANNEL = -20;
    public static final int ERROR_PATCH_MEMORY_LIMIT = -22;
    public static final int ERROR_PATCH_ROM_SPACE = -21;
    public static final int MIN_MEMORY_HEAP_SIZE = 45;
    public static final String PLATFORM = "platform";
    private static final String TAG = "Tinker.TinkerUtils";
    private static boolean background = false;

    public static class ScreenState {

        interface a {
            void a();
        }

        ScreenState(Context context, a aVar) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.registerReceiver(new TinkerUtils$ScreenState$1(this, aVar), intentFilter);
        }
    }

    public static boolean isGooglePlay() {
        return false;
    }

    public static boolean isBackground() {
        return background;
    }

    public static void setBackground(boolean z) {
        background = z;
    }

    public static int checkForPatchRecover(long j, int i) {
        if (isGooglePlay()) {
            return -20;
        }
        if (i < 45) {
            return -22;
        }
        if (checkRomSpaceEnough(j)) {
            return 0;
        }
        return -21;
    }

    public static boolean isXposedExists(Throwable th) {
        for (StackTraceElement className : th.getStackTrace()) {
            String className2 = className.getClassName();
            if (className2 != null && className2.contains("de.robv.android.xposed.XposedBridge")) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean checkRomSpaceEnough(long j) {
        long availableBlocks;
        long j2;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            try {
                long blockSize = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
                j2 = availableBlocks;
                availableBlocks = blockSize;
            } catch (Exception e) {
                j2 = availableBlocks;
                availableBlocks = 0;
                if (availableBlocks != 0) {
                }
                return false;
            }
        } catch (Exception e2) {
            availableBlocks = 0;
            j2 = availableBlocks;
            availableBlocks = 0;
            if (availableBlocks != 0) {
            }
            return false;
        }
        if (availableBlocks != 0 || r4 <= j) {
            return false;
        }
        return true;
    }

    public static String getExceptionCauseString(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (th != null) {
            try {
                if (th.getCause() == null) {
                    break;
                }
                th = th.getCause();
            } catch (Throwable th2) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (th != null) {
            th.printStackTrace(printStream);
        }
        String toVisualString = toVisualString(byteArrayOutputStream.toString());
        try {
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return toVisualString;
    }

    private static String toVisualString(String str) {
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        if (toCharArray == null) {
            return null;
        }
        char c;
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] > '') {
                toCharArray[i] = '\u0000';
                c = '\u0001';
                break;
            }
            i++;
        }
        c = '\u0000';
        if (c != '\u0000') {
            return new String(toCharArray, 0, i);
        }
        return str;
    }

    public static boolean copy(File file, File file2) throws IOException {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        boolean z = false;
        if (!(file == null || file2 == null || !file.exists())) {
            try {
                BufferedOutputStream bufferedOutputStream2;
                String absolutePath = file2.getAbsolutePath();
                File file3 = new File(absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)));
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[5120];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream2.write(bArr, 0, read);
                    }
                    bufferedOutputStream2.flush();
                    z = true;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static String getSignature(Context context) {
        String str = "";
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length > 0) {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()));
                MessageDigest.getInstance("SHA-1");
                return new String(x509Certificate.getEncoded());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String bytesToHexString(byte[] bArr, boolean z) {
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        Appendable stringBuffer = new StringBuffer(length * 2);
        Formatter formatter = new Formatter(stringBuffer);
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(bArr[i])});
        }
        formatter.close();
        return z ? stringBuffer.toString().toUpperCase() : stringBuffer.toString().toLowerCase();
    }

    public static byte[] readJarEntry(File file, String str) {
        byte[] bArr = null;
        if (file != null) {
            try {
                if (file.exists() && !TextUtils.isEmpty(str)) {
                    bArr = readJarEntry(new JarFile(file), str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] readJarEntry(JarFile jarFile, String str) {
        byte[] bArr = null;
        if (jarFile != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    bArr = readJarEntry(jarFile, jarFile.getJarEntry(str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] readJarEntry(JarFile jarFile, JarEntry jarEntry) {
        byte[] bArr = null;
        if (!(jarFile == null || jarEntry == null)) {
            try {
                bArr = readBytes(jarFile.getInputStream(jarEntry));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr;
    }

    public static byte[] readBytes(InputStream inputStream) {
        try {
            byte[] bArr = new byte[512];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void rollbackPatch(Context context) {
        Tinker.with(context).rollbackPatch();
    }
}
