package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ShareSecurityCheck {
    private static final String TAG = "Tinker.SecurityCheck";
    private static String mPublicKeyMd5 = null;
    private final Context mContext;
    private final HashMap<String, String> metaContentMap = new HashMap();
    private final HashMap<String, String> packageProperties = new HashMap();

    public ShareSecurityCheck(Context context) {
        this.mContext = context;
        if (mPublicKeyMd5 == null) {
            init(this.mContext);
        }
    }

    public HashMap<String, String> getMetaContentMap() {
        return this.metaContentMap;
    }

    public HashMap<String, String> getPackagePropertiesIfPresent() {
        if (!this.packageProperties.isEmpty()) {
            return this.packageProperties;
        }
        String str = (String) this.metaContentMap.get(ShareConstants.PACKAGE_META_FILE);
        if (str == null) {
            return null;
        }
        for (String str2 : str.split("\n")) {
            if (!(str2 == null || str2.length() <= 0 || str2.startsWith("#"))) {
                String[] split = str2.split("=", 2);
                if (split != null && split.length >= 2) {
                    this.packageProperties.put(split[0].trim(), split[1].trim());
                }
            }
        }
        return this.packageProperties;
    }

    public boolean verifyPatchMetaSignature(File file) {
        Throwable e;
        JarFile jarFile;
        if (!SharePatchFileUtil.isLegalFile(file)) {
            return false;
        }
        JarFile jarFile2;
        try {
            jarFile2 = new JarFile(file);
            try {
                Enumeration entries = jarFile2.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) entries.nextElement();
                    if (jarEntry != null) {
                        String name = jarEntry.getName();
                        if (!name.startsWith("META-INF/") && name.endsWith(ShareConstants.META_SUFFIX)) {
                            this.metaContentMap.put(name, SharePatchFileUtil.loadDigestes(jarFile2, jarEntry));
                            Certificate[] certificates = jarEntry.getCertificates();
                            if (certificates == null || !check(file, certificates)) {
                                if (jarFile2 != null) {
                                    try {
                                        jarFile2.close();
                                    } catch (Throwable e2) {
                                        Log.e(TAG, file.getAbsolutePath(), e2);
                                    }
                                }
                                return false;
                            }
                        }
                    }
                }
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (Throwable e22) {
                        Log.e(TAG, file.getAbsolutePath(), e22);
                    }
                }
                return true;
            } catch (Exception e3) {
                e22 = e3;
                jarFile = jarFile2;
                try {
                    throw new TinkerRuntimeException(String.format("ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail", new Object[]{file.getAbsolutePath(), Long.valueOf(file.length())}), e22);
                } catch (Throwable th) {
                    e22 = th;
                    jarFile2 = jarFile;
                    if (jarFile2 != null) {
                        try {
                            jarFile2.close();
                        } catch (Throwable e4) {
                            Log.e(TAG, file.getAbsolutePath(), e4);
                        }
                    }
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                if (jarFile2 != null) {
                    jarFile2.close();
                }
                throw e22;
            }
        } catch (Exception e5) {
            e22 = e5;
            jarFile = null;
            throw new TinkerRuntimeException(String.format("ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail", new Object[]{file.getAbsolutePath(), Long.valueOf(file.length())}), e22);
        } catch (Throwable th3) {
            e22 = th3;
            jarFile2 = null;
            if (jarFile2 != null) {
                jarFile2.close();
            }
            throw e22;
        }
    }

    private boolean check(File file, Certificate[] certificateArr) {
        if (certificateArr.length > 0) {
            int length = certificateArr.length - 1;
            while (length >= 0) {
                try {
                    if (mPublicKeyMd5.equals(SharePatchFileUtil.getMD5(certificateArr[length].getEncoded()))) {
                        return true;
                    }
                    length--;
                } catch (Throwable e) {
                    Log.e(TAG, file.getAbsolutePath(), e);
                }
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private void init(Context context) {
        try {
            mPublicKeyMd5 = SharePatchFileUtil.getMD5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            if (mPublicKeyMd5 == null) {
                throw new TinkerRuntimeException("get public key md5 is null");
            }
            SharePatchFileUtil.closeQuietly(null);
        } catch (Throwable e) {
            throw new TinkerRuntimeException("ShareSecurityCheck init public key fail", e);
        } catch (Throwable th) {
            SharePatchFileUtil.closeQuietly(null);
        }
    }
}
