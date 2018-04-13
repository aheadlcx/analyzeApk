package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.DebugEvents;
import com.google.analytics.containertag.proto.Debug.EventInfo;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.protobuf.nano.MessageNano;
import java.io.IOException;

class CtfeDebugInformationHandler implements DebugInformationHandler {
    @VisibleForTesting
    static final String CTFE_URL_PATH_PREFIX = "/d?";
    @VisibleForTesting
    static final int NUM_EVENTS_PER_SEND = 1;
    private int currentDebugEventNumber;
    private NetworkClient mClient;
    private CtfeHost mCtfeHost;
    private DebugEvents mDebugEvents;

    @VisibleForTesting
    CtfeDebugInformationHandler(NetworkClient networkClient, CtfeHost ctfeHost) {
        this.mCtfeHost = ctfeHost;
        this.mClient = networkClient;
        this.mDebugEvents = new DebugEvents();
    }

    public CtfeDebugInformationHandler(CtfeHost ctfeHost) {
        this(new NetworkClientFactory().createNetworkClient(), ctfeHost);
    }

    public synchronized void receiveEventInfo(EventInfo eventInfo) {
        this.mDebugEvents.event = ArrayUtils.appendToArray(this.mDebugEvents.event, eventInfo);
        if (this.mDebugEvents.event.length >= 1 && sendDebugInformationtoCtfe()) {
            this.mDebugEvents.clear();
        }
    }

    private byte[] getDebugEventsAsBytes() throws IOException {
        return MessageNano.toByteArray(this.mDebugEvents);
    }

    private boolean sendDebugInformationtoCtfe() {
        try {
            NetworkClient networkClient = this.mClient;
            CtfeHost ctfeHost = this.mCtfeHost;
            int i = this.currentDebugEventNumber;
            this.currentDebugEventNumber = i + 1;
            networkClient.sendPostRequest(ctfeHost.constructCtfeDebugUrl(i), getDebugEventsAsBytes());
            return true;
        } catch (IOException e) {
            Log.e("CtfeDebugInformationHandler: Error sending information to server that handles debug information: " + e.getMessage());
            return false;
        }
    }
}
