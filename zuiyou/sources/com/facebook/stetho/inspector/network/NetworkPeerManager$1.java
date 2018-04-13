package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.helper.PeersRegisteredListener;

class NetworkPeerManager$1 extends PeersRegisteredListener {
    final /* synthetic */ NetworkPeerManager this$0;

    NetworkPeerManager$1(NetworkPeerManager networkPeerManager) {
        this.this$0 = networkPeerManager;
    }

    protected void onFirstPeerRegistered() {
        AsyncPrettyPrinterExecutorHolder.ensureInitialized();
        if (NetworkPeerManager.access$000(this.this$0) == null && NetworkPeerManager.access$100(this.this$0) != null) {
            NetworkPeerManager.access$002(this.this$0, new AsyncPrettyPrinterRegistry());
            NetworkPeerManager.access$100(this.this$0).populatePrettyPrinters(NetworkPeerManager.access$000(this.this$0));
        }
        NetworkPeerManager.access$200(this.this$0).cleanupFiles();
    }

    protected void onLastPeerUnregistered() {
        NetworkPeerManager.access$200(this.this$0).cleanupFiles();
        AsyncPrettyPrinterExecutorHolder.shutdown();
    }
}
