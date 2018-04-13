package com.flurry.android;

import com.alipay.sdk.sys.a;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;

final class t implements Runnable {
    private /* synthetic */ Map a;
    private /* synthetic */ InstallReceiver b;

    t(InstallReceiver installReceiver, Map map) {
        this.b = installReceiver;
        this.a = map;
    }

    public final void run() {
        Throwable th;
        Closeable closeable = null;
        try {
            File parentFile = this.b.b.getParentFile();
            if (parentFile.mkdirs() || parentFile.exists()) {
                Closeable dataOutputStream = new DataOutputStream(new FileOutputStream(this.b.b));
                try {
                    Object obj = 1;
                    for (Entry entry : this.a.entrySet()) {
                        if (obj == 1) {
                            obj = null;
                        } else {
                            dataOutputStream.writeUTF(a.b);
                        }
                        dataOutputStream.writeUTF((String) entry.getKey());
                        dataOutputStream.writeUTF("=");
                        dataOutputStream.writeUTF((String) entry.getValue());
                    }
                    dataOutputStream.writeShort(0);
                    r.a(dataOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = dataOutputStream;
                }
            } else {
                ah.b("InstallReceiver", "Unable to create persistent dir: " + parentFile);
                r.a(null);
            }
        } catch (Throwable th3) {
            th = th3;
            try {
                ah.b("InstallReceiver", "", th);
                r.a(closeable);
            } catch (Throwable th4) {
                th = th4;
                r.a(closeable);
                throw th;
            }
        }
    }
}
