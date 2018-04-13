package qsbk.app.cache;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ SecureFileCache c;

    d(SecureFileCache secureFileCache, String str, String str2) {
        this.c = secureFileCache;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        FileOutputStream fileOutputStream;
        IOException e;
        Throwable th;
        try {
            fileOutputStream = new FileOutputStream(this.c.a(this.c.c(this.a)), false);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf8");
                outputStreamWriter.write(this.b);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (IOException e3) {
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
        } catch (IOException e6) {
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
