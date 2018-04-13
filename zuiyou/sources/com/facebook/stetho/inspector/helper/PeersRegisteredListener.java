package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PeersRegisteredListener implements PeerRegistrationListener {
    private AtomicInteger mPeers = new AtomicInteger(0);

    protected abstract void onFirstPeerRegistered();

    protected abstract void onLastPeerUnregistered();

    public final void onPeerRegistered(JsonRpcPeer jsonRpcPeer) {
        if (this.mPeers.incrementAndGet() == 1) {
            onFirstPeerRegistered();
        }
        onPeerAdded(jsonRpcPeer);
    }

    public final void onPeerUnregistered(JsonRpcPeer jsonRpcPeer) {
        if (this.mPeers.decrementAndGet() == 0) {
            onLastPeerUnregistered();
        }
        onPeerRemoved(jsonRpcPeer);
    }

    protected void onPeerAdded(JsonRpcPeer jsonRpcPeer) {
    }

    protected void onPeerRemoved(JsonRpcPeer jsonRpcPeer) {
    }
}
