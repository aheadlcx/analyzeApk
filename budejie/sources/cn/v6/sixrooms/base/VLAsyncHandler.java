package cn.v6.sixrooms.base;

import java.util.HashSet;
import java.util.Iterator;

public abstract class VLAsyncHandler<T> {
    private static final int ERRORCODE_DEFAULT = Integer.MAX_VALUE;
    private static HashSet<VLAsyncHandler<?>> gAsyncHandlers = new HashSet();
    private int mDelayMs = 0;
    private String mDesc;
    private int mErrorCode = Integer.MAX_VALUE;
    private Object mHolder;
    private boolean mIsCancelled = false;
    private T mParam = null;
    private VLAsyncHandler$VLAsyncRes mRes = VLAsyncHandler$VLAsyncRes.VLAsyncResSuccess;
    private String mStr = null;
    private int mThread;

    protected abstract void handler(boolean z);

    private static synchronized void addAsyncHandler(VLAsyncHandler<?> vLAsyncHandler) {
        synchronized (VLAsyncHandler.class) {
            VLDebug.Assert(gAsyncHandlers.add(vLAsyncHandler));
        }
    }

    private static synchronized boolean finishAsyncHandler(VLAsyncHandler<?> vLAsyncHandler) {
        boolean remove;
        synchronized (VLAsyncHandler.class) {
            remove = gAsyncHandlers.remove(vLAsyncHandler);
        }
        return remove;
    }

    public static synchronized void cancelByHolder(Object obj) {
        synchronized (VLAsyncHandler.class) {
            if (obj != null) {
                Iterator it = gAsyncHandlers.iterator();
                while (it.hasNext()) {
                    VLAsyncHandler vLAsyncHandler = (VLAsyncHandler) it.next();
                    if (vLAsyncHandler.getHolder() == obj) {
                        vLAsyncHandler.mIsCancelled = true;
                        vLAsyncHandler.handlerInternal(false, VLAsyncHandler$VLAsyncRes.VLAsyncResCanceled, null, null);
                        it.remove();
                    }
                }
            }
        }
    }

    public static synchronized void cancelByHandler(VLAsyncHandler<?> vLAsyncHandler) {
        synchronized (VLAsyncHandler.class) {
            if (gAsyncHandlers.remove(vLAsyncHandler)) {
                vLAsyncHandler.mIsCancelled = true;
                vLAsyncHandler.handlerInternal(false, VLAsyncHandler$VLAsyncRes.VLAsyncResCanceled, null, null);
            }
        }
    }

    public VLAsyncHandler(Object obj, int i) {
        this.mHolder = obj;
        this.mThread = i;
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        this.mDesc = stackTraceElement.getClassName() + "::" + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber();
        addAsyncHandler(this);
    }

    public void setDelay(int i) {
        this.mDelayMs = i;
    }

    public Object getHolder() {
        return this.mHolder;
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public void handlerSuccess() {
        handlerSuccess(null);
    }

    public void handlerSuccess(T t) {
        if (finishAsyncHandler(this)) {
            handlerInternal(true, VLAsyncHandler$VLAsyncRes.VLAsyncResSuccess, t, null);
        }
    }

    public void handlerError(VLAsyncHandler$VLAsyncRes vLAsyncHandler$VLAsyncRes, String str) {
        handlerError(vLAsyncHandler$VLAsyncRes, str, Integer.MAX_VALUE);
    }

    public void handlerError(VLAsyncHandler$VLAsyncRes vLAsyncHandler$VLAsyncRes, String str, int i) {
        if (finishAsyncHandler(this)) {
            handlerInternal(false, vLAsyncHandler$VLAsyncRes, i, null, str);
        }
    }

    private void handlerInternal(boolean z, VLAsyncHandler$VLAsyncRes vLAsyncHandler$VLAsyncRes, T t, String str) {
        handlerInternal(z, vLAsyncHandler$VLAsyncRes, Integer.MAX_VALUE, t, str);
    }

    private void handlerInternal(boolean z, VLAsyncHandler$VLAsyncRes vLAsyncHandler$VLAsyncRes, int i, T t, String str) {
        this.mRes = vLAsyncHandler$VLAsyncRes;
        this.mErrorCode = i;
        this.mParam = t;
        this.mStr = str;
        VLScheduler.instance.schedule(this.mDelayMs, this.mThread, new d(this, z));
    }

    protected VLAsyncHandler$VLAsyncRes getRes() {
        return this.mRes;
    }

    protected int getErrorCode() {
        return this.mErrorCode;
    }

    protected String getStr() {
        return this.mStr;
    }

    protected T getParam() {
        return this.mParam;
    }

    protected String getDesc() {
        return this.mDesc;
    }
}
