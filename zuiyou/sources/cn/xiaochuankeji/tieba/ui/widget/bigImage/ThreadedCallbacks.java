package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Keep;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Keep
public final class ThreadedCallbacks implements InvocationHandler {
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    private static final Object NON_SENSE = new Object();
    private final Handler mHandler;
    private final Object mTarget;

    private ThreadedCallbacks(Handler handler, Object obj) {
        this.mHandler = handler;
        this.mTarget = obj;
    }

    public static <T> T create(Class<T> cls, T t) {
        return create(MAIN_HANDLER, cls, t);
    }

    public static <T> T create(Handler handler, Class<T> cls, T t) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ThreadedCallbacks(handler, t));
    }

    public Object invoke(Object obj, final Method method, final Object[] objArr) throws Throwable {
        if (method.getReturnType().equals(Void.TYPE)) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                method.invoke(this.mTarget, objArr);
            } else {
                this.mHandler.post(new Runnable(this) {
                    final /* synthetic */ ThreadedCallbacks c;

                    public void run() {
                        try {
                            method.invoke(this.c.mTarget, objArr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            return NON_SENSE;
        }
        throw new RuntimeException("Method should return void: " + method);
    }
}
