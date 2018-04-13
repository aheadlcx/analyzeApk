package com.tencent.smtt.sdk;

import com.tencent.tbs.video.interfaces.IUserStateChangedListener;

class aw implements IUserStateChangedListener {
    final /* synthetic */ av a;

    aw(av avVar) {
        this.a = avVar;
    }

    public void onUserStateChanged() {
        this.a.a.c();
    }
}
