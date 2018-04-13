package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.EventInfo;

interface DebugInformationHandler {
    void receiveEventInfo(EventInfo eventInfo);
}
