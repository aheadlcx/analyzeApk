package com.tencent.tinker.loader.hotplug.interceptor;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;

public class HandlerMessageInterceptor extends Interceptor<Callback> {
    private static Field sMCallbackField;
    private final MessageHandler mMessageHandler;
    private final Handler mTarget;

    public interface MessageHandler {
        boolean handleMessage(Message message);
    }

    private static class CallbackWrapper implements Callback, ITinkerHotplugProxy {
        private final MessageHandler a;
        private final Callback b;
        private volatile boolean c = false;

        CallbackWrapper(MessageHandler messageHandler, Callback callback) {
            this.a = messageHandler;
            this.b = callback;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            if (this.c) {
                return false;
            }
            this.c = true;
            if (!this.a.handleMessage(message)) {
                z = this.b != null ? this.b.handleMessage(message) : false;
            }
            this.c = false;
            return z;
        }
    }

    static {
        sMCallbackField = null;
        synchronized (HandlerMessageInterceptor.class) {
            if (sMCallbackField == null) {
                try {
                    sMCallbackField = ShareReflectUtil.findField(Handler.class, "mCallback");
                } catch (Throwable th) {
                }
            }
        }
    }

    public HandlerMessageInterceptor(Handler handler, MessageHandler messageHandler) {
        this.mTarget = handler;
        this.mMessageHandler = messageHandler;
    }

    @Nullable
    protected Callback fetchTarget() throws Throwable {
        return (Callback) sMCallbackField.get(this.mTarget);
    }

    @NonNull
    protected Callback decorate(@Nullable Callback callback) throws Throwable {
        return (callback == null || !ITinkerHotplugProxy.class.isAssignableFrom(callback.getClass())) ? new CallbackWrapper(this.mMessageHandler, callback) : callback;
    }

    protected void inject(@Nullable Callback callback) throws Throwable {
        sMCallbackField.set(this.mTarget, callback);
    }
}
