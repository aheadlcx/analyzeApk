package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.helper.PeersRegisteredListener;

final class DOM$PeerManagerListener extends PeersRegisteredListener {
    final /* synthetic */ DOM this$0;

    private DOM$PeerManagerListener(DOM dom) {
        this.this$0 = dom;
    }

    protected synchronized void onFirstPeerRegistered() {
        DOM.access$300(this.this$0).addRef();
        DOM.access$300(this.this$0).addUpdateListener(DOM.access$1900(this.this$0));
    }

    protected synchronized void onLastPeerUnregistered() {
        DOM.access$2000(this.this$0).clear();
        DOM.access$300(this.this$0).removeUpdateListener(DOM.access$1900(this.this$0));
        DOM.access$300(this.this$0).release();
    }
}
