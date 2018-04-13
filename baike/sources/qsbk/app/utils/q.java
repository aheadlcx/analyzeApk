package qsbk.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.FileUtils.CallBack;

final class q extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ File a;
    final /* synthetic */ File b;
    final /* synthetic */ CallBack c;

    q(File file, File file2, CallBack callBack) {
        this.a = file;
        this.b = file2;
        this.c = callBack;
    }

    protected Boolean a(Void[] voidArr) {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.a);
            FileOutputStream fileOutputStream = new FileOutputStream(this.b);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return Boolean.valueOf(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    protected void a(Boolean bool) {
        if (this.c != null) {
            this.c.onResult(bool.booleanValue());
        }
    }
}
