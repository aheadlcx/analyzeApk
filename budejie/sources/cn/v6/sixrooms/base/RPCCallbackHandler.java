package cn.v6.sixrooms.base;

import java.util.HashSet;
import java.util.Iterator;

public abstract class RPCCallbackHandler<T> implements RPCCallback<T> {
    private static final HashSet<RPCCallbackHandler<?>> a = new HashSet();
    private static final Object b = new Object();
    private Object c;
    private int d;
    private String e;
    private int f;
    private RCPHandleState g;
    private RPCException h;
    private Object i;
    private boolean j;

    public enum RCPHandleState {
        RPC_STATE_CREATED,
        RPC_STATE_SUCCESS,
        RPC_STATE_CANCELED,
        RPC_STATE_ERROR
    }

    protected abstract void handleResult(T t);

    public RPCCallbackHandler(Object obj, int i) {
        this(obj, i, 0);
    }

    public RPCCallbackHandler(Object obj) {
        this(obj, 0, 0);
    }

    public RPCCallbackHandler(Object obj, int i, int i2) {
        this.i = b;
        this.c = obj;
        this.d = i;
        this.f = 0;
        this.j = false;
        this.g = RCPHandleState.RPC_STATE_CREATED;
        this.i = b;
        this.f = i2;
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        this.e = stackTraceElement.getClassName() + "::" + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber();
        a(this);
    }

    private static synchronized void a(RPCCallbackHandler<?> rPCCallbackHandler) {
        synchronized (RPCCallbackHandler.class) {
            VLDebug.Assert(a.add(rPCCallbackHandler));
        }
    }

    private static synchronized boolean b(RPCCallbackHandler<?> rPCCallbackHandler) {
        boolean remove;
        synchronized (RPCCallbackHandler.class) {
            remove = a.remove(rPCCallbackHandler);
        }
        return remove;
    }

    public static synchronized void cancelByHolder(Object obj) {
        synchronized (RPCCallbackHandler.class) {
            if (obj != null) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    RPCCallbackHandler rPCCallbackHandler = (RPCCallbackHandler) it.next();
                    if (rPCCallbackHandler.getHolder() == obj) {
                        rPCCallbackHandler.a();
                        it.remove();
                    }
                }
            }
        }
    }

    public static synchronized void cancelByHandler(RPCCallbackHandler<?> rPCCallbackHandler) {
        synchronized (RPCCallbackHandler.class) {
            if (a.remove(rPCCallbackHandler)) {
                rPCCallbackHandler.a();
            }
        }
    }

    public Object getHolder() {
        return this.c;
    }

    public RCPHandleState getState() {
        return this.g;
    }

    public T getResult() {
        T t = this.i;
        if (t == b) {
            return null;
        }
        return t;
    }

    public int getThread() {
        return this.d;
    }

    public String getDesc() {
        return this.e;
    }

    public int getDelayMs() {
        return this.f;
    }

    public RPCException getRPCError() {
        return this.h;
    }

    public void onResult(T t) {
        VLDebug.Assert(this.h == null);
        if (b(this)) {
            this.g = RCPHandleState.RPC_STATE_SUCCESS;
            this.i = t;
            VLScheduler.instance.schedule(this.f, this.d, new a(this, t));
        }
    }

    public void onError(RPCException rPCException) {
        VLDebug.Assert(this.i == b);
        if (b(this)) {
            this.h = rPCException;
            this.g = RCPHandleState.RPC_STATE_ERROR;
            VLScheduler.instance.schedule(this.f, this.d, new b(this, rPCException));
        }
    }

    protected void handleError(RPCException rPCException) {
    }

    private void a() {
        this.j = true;
        this.g = RCPHandleState.RPC_STATE_CANCELED;
    }
}
