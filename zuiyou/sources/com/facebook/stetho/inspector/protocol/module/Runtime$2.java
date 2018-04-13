package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;

class Runtime$2 implements DisconnectReceiver {
    final /* synthetic */ JsonRpcPeer val$peer;

    Runtime$2(JsonRpcPeer jsonRpcPeer) {
        this.val$peer = jsonRpcPeer;
    }

    public void onDisconnect() {
        Runtime.access$100().remove(this.val$peer);
    }
}
