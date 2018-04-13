package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.helper.PeersRegisteredListener;

final class CSS$PeerManagerListener extends PeersRegisteredListener {
    final /* synthetic */ CSS this$0;

    private CSS$PeerManagerListener(CSS css) {
        this.this$0 = css;
    }

    protected synchronized void onFirstPeerRegistered() {
        CSS.access$200(this.this$0).addRef();
    }

    protected synchronized void onLastPeerUnregistered() {
        CSS.access$200(this.this$0).release();
    }
}
