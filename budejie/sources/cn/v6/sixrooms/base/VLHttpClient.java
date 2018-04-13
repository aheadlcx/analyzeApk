package cn.v6.sixrooms.base;

import android.os.Handler;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class VLHttpClient {
    private int a = 0;
    private SparseArray<a> b = new SparseArray();
    private String c = "Mozilla/5.0 (compatible)";
    private String d = "*/*";
    private String e = "gzip";

    public interface VLHttpFileDownloadListener {
        void onResProgress(int i, int i2);
    }

    private static class a {
        public int a;
        public int b;
        public boolean c;
        public List<VLResHandler> d;

        private a() {
        }
    }

    public synchronized boolean httpCancelTask(int i, VLResHandler vLResHandler) {
        boolean z;
        a aVar = (a) this.b.get(i);
        if (aVar == null || aVar.b == 4) {
            z = false;
        } else {
            if (vLResHandler != null) {
                if (aVar.d == null) {
                    aVar.d = new ArrayList();
                }
                aVar.d.add(vLResHandler);
            }
            aVar.c = true;
            z = true;
        }
        return z;
    }

    public synchronized int httpFileDownloadTask(boolean z, String str, String str2, int i, VLHttpFileDownloadListener vLHttpFileDownloadListener, VLAsyncHandler<Object> vLAsyncHandler) {
        int i2;
        i2 = this.a + 1;
        this.a = i2;
        a aVar = new a();
        this.b.put(i2, aVar);
        aVar.a = i2;
        aVar.b = 0;
        aVar.c = false;
        aVar.d = null;
        new Thread(new f(this, str2, vLAsyncHandler, aVar, str, z, i, vLHttpFileDownloadListener)).start();
        return i2;
    }

    public synchronized int httpPostTask(Handler handler, String str, String str2, byte[] bArr, VLResHandler vLResHandler) {
        int i;
        i = this.a + 1;
        this.a = i;
        a aVar = new a();
        this.b.put(i, aVar);
        aVar.a = i;
        aVar.b = 0;
        aVar.c = false;
        aVar.d = null;
        handler.post(new g(this, aVar, vLResHandler, str, str2, bArr));
        return i;
    }

    public synchronized int httpGetTask(Handler handler, String str, VLResHandler vLResHandler) {
        int i;
        i = this.a + 1;
        this.a = i;
        a aVar = new a();
        this.b.put(i, aVar);
        aVar.a = i;
        aVar.b = 0;
        aVar.c = false;
        aVar.d = null;
        handler.post(new h(this, aVar, vLResHandler, str));
        return i;
    }
}
