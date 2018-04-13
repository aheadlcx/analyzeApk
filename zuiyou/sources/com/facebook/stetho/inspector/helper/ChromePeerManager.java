package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.PendingRequestCallback;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ChromePeerManager {
    private static final String TAG = "ChromePeerManager";
    @GuardedBy
    private PeerRegistrationListener mListener;
    @GuardedBy
    private final Map<JsonRpcPeer, DisconnectReceiver> mReceivingPeers = new HashMap();
    @GuardedBy
    private JsonRpcPeer[] mReceivingPeersSnapshot;

    private class UnregisterOnDisconnect implements DisconnectReceiver {
        private final JsonRpcPeer mPeer;

        public UnregisterOnDisconnect(JsonRpcPeer jsonRpcPeer) {
            this.mPeer = jsonRpcPeer;
        }

        public void onDisconnect() {
            ChromePeerManager.this.removePeer(this.mPeer);
        }
    }

    public synchronized void setListener(PeerRegistrationListener peerRegistrationListener) {
        this.mListener = peerRegistrationListener;
    }

    public synchronized boolean addPeer(JsonRpcPeer jsonRpcPeer) {
        boolean z;
        if (this.mReceivingPeers.containsKey(jsonRpcPeer)) {
            z = false;
        } else {
            DisconnectReceiver unregisterOnDisconnect = new UnregisterOnDisconnect(jsonRpcPeer);
            jsonRpcPeer.registerDisconnectReceiver(unregisterOnDisconnect);
            this.mReceivingPeers.put(jsonRpcPeer, unregisterOnDisconnect);
            this.mReceivingPeersSnapshot = null;
            if (this.mListener != null) {
                this.mListener.onPeerRegistered(jsonRpcPeer);
            }
            z = true;
        }
        return z;
    }

    public synchronized void removePeer(JsonRpcPeer jsonRpcPeer) {
        if (this.mReceivingPeers.remove(jsonRpcPeer) != null) {
            this.mReceivingPeersSnapshot = null;
            if (this.mListener != null) {
                this.mListener.onPeerUnregistered(jsonRpcPeer);
            }
        }
    }

    public synchronized boolean hasRegisteredPeers() {
        return !this.mReceivingPeers.isEmpty();
    }

    private synchronized JsonRpcPeer[] getReceivingPeersSnapshot() {
        if (this.mReceivingPeersSnapshot == null) {
            this.mReceivingPeersSnapshot = (JsonRpcPeer[]) this.mReceivingPeers.keySet().toArray(new JsonRpcPeer[this.mReceivingPeers.size()]);
        }
        return this.mReceivingPeersSnapshot;
    }

    public void sendNotificationToPeers(String str, Object obj) {
        sendMessageToPeers(str, obj, null);
    }

    public void invokeMethodOnPeers(String str, Object obj, PendingRequestCallback pendingRequestCallback) {
        Util.throwIfNull(pendingRequestCallback);
        sendMessageToPeers(str, obj, pendingRequestCallback);
    }

    private void sendMessageToPeers(String str, Object obj, @Nullable PendingRequestCallback pendingRequestCallback) {
        for (JsonRpcPeer invokeMethod : getReceivingPeersSnapshot()) {
            try {
                invokeMethod.invokeMethod(str, obj, pendingRequestCallback);
            } catch (Throwable e) {
                LogRedirector.e(TAG, "Error delivering data to Chrome", e);
            }
        }
    }
}
