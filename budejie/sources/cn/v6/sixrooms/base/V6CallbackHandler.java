package cn.v6.sixrooms.base;

import android.util.SparseArray;

public abstract class V6CallbackHandler<T> extends RPCCallbackHandler<T> {
    private static final SparseArray<a> a = new SparseArray();

    private static class a {
        int a = -110000;
    }

    static {
        a aVar = new a();
        a.put(aVar.a, aVar);
    }

    public V6CallbackHandler(Object obj, int i) {
        super(obj, i);
    }

    public V6CallbackHandler(Object obj) {
        super(obj);
    }

    public V6CallbackHandler(Object obj, int i, int i2) {
        super(obj, i, i2);
    }

    protected void handleError(RPCException rPCException) {
        super.handleError(rPCException);
        int code = rPCException.getCode();
        if (code <= 0) {
            a("网络错误, 请稍后再试");
        } else if (((a) a.get(code)) == null) {
            a(rPCException.getMessage());
        }
    }

    private void a(String str) {
        VLScheduler.instance.schedule(0, 0, new c(this, str));
    }
}
