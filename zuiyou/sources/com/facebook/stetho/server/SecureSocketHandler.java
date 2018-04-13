package com.facebook.stetho.server;

import android.content.Context;
import android.net.Credentials;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;

public abstract class SecureSocketHandler implements SocketHandler {
    private final Context mContext;

    protected abstract void onSecured(LocalSocket localSocket) throws IOException;

    public SecureSocketHandler(Context context) {
        this.mContext = context;
    }

    public final void onAccepted(LocalSocket localSocket) throws IOException {
        try {
            enforcePermission(this.mContext, localSocket);
            onSecured(localSocket);
        } catch (PeerAuthorizationException e) {
            LogUtil.e("Unauthorized request: " + e.getMessage());
        }
    }

    private static void enforcePermission(Context context, LocalSocket localSocket) throws IOException, PeerAuthorizationException {
        Credentials peerCredentials = localSocket.getPeerCredentials();
        int uid = peerCredentials.getUid();
        int pid = peerCredentials.getPid();
        if (LogUtil.isLoggable(2)) {
            LogUtil.v("Got request from uid=%d, pid=%d", Integer.valueOf(uid), Integer.valueOf(pid));
        }
        String str = "android.permission.DUMP";
        if (context.checkPermission(str, pid, uid) != 0) {
            throw new PeerAuthorizationException("Peer pid=" + pid + ", uid=" + uid + " does not have " + str);
        }
    }
}
