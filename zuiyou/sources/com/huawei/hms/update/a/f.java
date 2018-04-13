package com.huawei.hms.update.a;

import android.content.Context;
import android.os.Environment;
import com.huawei.hms.update.a.a.a;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.b.b;
import com.huawei.hms.update.b.d;
import com.huawei.hms.update.provider.UpdateProvider;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class f implements a {
    private final Context a;
    private final d b = new b();
    private com.huawei.hms.update.a.a.b c;
    private File d;
    private final c e = new c();

    public f(Context context) {
        this.a = context.getApplicationContext();
    }

    private synchronized void b(com.huawei.hms.update.a.a.b bVar) {
        this.c = bVar;
    }

    private synchronized void a(int i, int i2, int i3) {
        if (this.c != null) {
            this.c.a(i, i2, i3, this.d);
        }
    }

    public Context a() {
        return this.a;
    }

    public void b() {
        com.huawei.hms.support.log.a.b("OtaUpdateDownload", "Enter cancel.");
        b(null);
        this.b.b();
    }

    public void a(com.huawei.hms.update.a.a.b bVar) {
        throw new IllegalStateException("Not supported.");
    }

    public void a(com.huawei.hms.update.a.a.b bVar, c cVar) {
        com.huawei.hms.c.a.a(bVar, "callback must not be null.");
        com.huawei.hms.support.log.a.b("OtaUpdateDownload", "Enter downloadPackage.");
        b(bVar);
        if (cVar == null || !cVar.a()) {
            com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Invalid update info.");
            a(PushConstants.ONTIME_NOTIFICATION, 0, 0);
            return;
        }
        if ("mounted".equals(Environment.getExternalStorageState())) {
            this.d = UpdateProvider.getLocalFile(this.a, "hms/HwMobileService.apk");
            if (this.d == null) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Failed to get local file for downloading.");
                a(2204, 0, 0);
                return;
            }
            File parentFile = this.d.getParentFile();
            if (parentFile == null || !(parentFile.mkdirs() || parentFile.isDirectory())) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Failed to create directory for downloading file.");
                a(PushConstants.ONTIME_NOTIFICATION, 0, 0);
                return;
            } else if (parentFile.getUsableSpace() < ((long) (cVar.c * 3))) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, No space for downloading file.");
                a(2203, 0, 0);
                return;
            } else {
                try {
                    a(cVar);
                    return;
                } catch (com.huawei.hms.update.b.a e) {
                    com.huawei.hms.support.log.a.c("OtaUpdateDownload", "In downloadPackage, Canceled to download the update file.");
                    a(2101, 0, 0);
                    return;
                }
            }
        }
        com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In downloadPackage, Invalid external storage for downloading file.");
        a(2204, 0, 0);
    }

    private static boolean a(String str, File file) {
        byte[] a = com.huawei.hms.c.f.a(file);
        if (a != null) {
            return com.huawei.hms.c.b.b(a, true).equalsIgnoreCase(str);
        }
        return false;
    }

    void a(c cVar) throws com.huawei.hms.update.b.a {
        com.huawei.hms.support.log.a.b("OtaUpdateDownload", "Enter downloadPackage.");
        OutputStream outputStream = null;
        try {
            this.e.a(a());
            if (!this.e.b(cVar.b, cVar.c, cVar.d)) {
                this.e.a(cVar.b, cVar.c, cVar.d);
                outputStream = a(this.d, cVar.c);
            } else if (this.e.b() != this.e.a()) {
                outputStream = a(this.d, cVar.c);
                outputStream.a((long) this.e.b());
            } else if (a(cVar.d, this.d)) {
                a(2000, 0, 0);
                return;
            } else {
                this.e.a(cVar.b, cVar.c, cVar.d);
                outputStream = a(this.d, cVar.c);
            }
            int a = this.b.a(cVar.b, outputStream, this.e.b(), this.e.a());
            if (a != 200 && a != 206) {
                com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In DownloadHelper.downloadPackage, Download the package, HTTP code: " + a);
                a(PushConstants.ONTIME_NOTIFICATION, 0, 0);
                this.b.a();
                com.huawei.hms.c.c.a(outputStream);
            } else if (a(cVar.d, this.d)) {
                a(2000, 0, 0);
                this.b.a();
                com.huawei.hms.c.c.a(outputStream);
            } else {
                a(PushConstants.DELAY_NOTIFICATION, 0, 0);
                this.b.a();
                com.huawei.hms.c.c.a(outputStream);
            }
        } catch (IOException e) {
            com.huawei.hms.support.log.a.d("OtaUpdateDownload", "In DownloadHelper.downloadPackage, Failed to download." + e.getMessage());
            a(PushConstants.ONTIME_NOTIFICATION, 0, 0);
        } finally {
            this.b.a();
            com.huawei.hms.c.c.a(outputStream);
        }
    }

    private h a(File file, int i) throws IOException {
        return new g(this, file, i, i);
    }
}
