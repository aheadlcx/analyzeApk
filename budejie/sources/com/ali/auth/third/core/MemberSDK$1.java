package com.ali.auth.third.core;

import com.ali.auth.third.core.callback.ResultCallback;

class MemberSDK$1 implements Runnable {
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ Class val$clazz;

    MemberSDK$1(Class cls, ResultCallback resultCallback) {
        this.val$clazz = cls;
        this.val$callback = resultCallback;
    }

    public void run() {
        this.val$callback.onSuccess(MemberSDK.getService(this.val$clazz));
    }
}
