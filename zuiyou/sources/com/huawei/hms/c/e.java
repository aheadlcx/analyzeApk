package com.huawei.hms.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class e {
    private final PackageManager a;

    public enum a {
        ENABLED,
        DISABLED,
        NOT_INSTALLED
    }

    public e(Context context) {
        this.a = context.getPackageManager();
    }

    public a a(String str) {
        try {
            if (this.a.getApplicationInfo(str, 0).enabled) {
                return a.ENABLED;
            }
            return a.DISABLED;
        } catch (NameNotFoundException e) {
            return a.NOT_INSTALLED;
        }
    }

    public int b(String str) {
        try {
            PackageInfo packageInfo = this.a.getPackageInfo(str, 16);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    public String c(String str) {
        try {
            PackageInfo packageInfo = this.a.getPackageInfo(str, 16);
            if (packageInfo == null || packageInfo.versionName == null) {
                return "";
            }
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public String d(String str) {
        byte[] e = e(str);
        if (e == null || e.length == 0) {
            return null;
        }
        return b.b(f.a(e), true);
    }

    private byte[] e(String str) {
        Exception e;
        try {
            PackageInfo packageInfo = this.a.getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.signatures.length <= 0) {
                c.a(null);
                com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
                return new byte[0];
            }
            InputStream a = c.a(packageInfo.signatures[0].toByteArray());
            byte[] encoded = CertificateFactory.getInstance("X.509").generateCertificate(a).getEncoded();
            c.a(a);
            return encoded;
        } catch (NameNotFoundException e2) {
            e = e2;
            try {
                com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
                com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
                return new byte[0];
            } finally {
                c.a(null);
            }
        } catch (IOException e3) {
            e = e3;
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
            return new byte[0];
        } catch (CertificateException e4) {
            e = e4;
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
            return new byte[0];
        }
    }

    public boolean a(String str, String str2) {
        try {
            PackageInfo packageInfo = this.a.getPackageInfo(str, 8);
            if (packageInfo == null || packageInfo.providers == null) {
                return false;
            }
            for (ProviderInfo providerInfo : packageInfo.providers) {
                if (str2.equals(providerInfo.authority)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean a(String str, String str2, String str3) {
        Exception e;
        boolean z = false;
        PackageInfo packageArchiveInfo = this.a.getPackageArchiveInfo(str, 64);
        if (packageArchiveInfo != null && packageArchiveInfo.signatures.length > 0 && str2.equals(packageArchiveInfo.packageName)) {
            InputStream inputStream = null;
            try {
                inputStream = c.a(packageArchiveInfo.signatures[0].toByteArray());
                z = str3.equalsIgnoreCase(b.b(f.a(CertificateFactory.getInstance("X.509").generateCertificate(inputStream).getEncoded()), true));
                c.a(inputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
                    return z;
                } finally {
                    c.a(inputStream);
                }
            } catch (CertificateException e3) {
                e = e3;
                com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
                return z;
            }
        }
        return z;
    }
}
