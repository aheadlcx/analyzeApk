package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.DataLayerEventEvaluationInfo;
import com.google.analytics.containertag.proto.Debug.EventInfo;
import com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo;
import com.google.android.gms.common.util.VisibleForTesting;

class DebugEventInfoBuilder implements EventInfoBuilder {
    private DebugDataLayerEventEvaluationInfoBuilder dataLayerEventBuilder;
    @VisibleForTesting
    EventInfo eventInfoBuilder = new EventInfo();
    private DebugInformationHandler handler;
    private DebugMacroEvaluationInfoBuilder macroBuilder;

    public DebugEventInfoBuilder(int i, String str, String str2, String str3, DebugInformationHandler debugInformationHandler) {
        this.eventInfoBuilder.eventType = i;
        this.eventInfoBuilder.containerVersion = str;
        this.eventInfoBuilder.containerId = str2;
        this.eventInfoBuilder.key = str3;
        this.handler = debugInformationHandler;
        if (i == 1) {
            this.eventInfoBuilder.dataLayerEventResult = new DataLayerEventEvaluationInfo();
            this.dataLayerEventBuilder = new DebugDataLayerEventEvaluationInfoBuilder(this.eventInfoBuilder.dataLayerEventResult);
            return;
        }
        this.eventInfoBuilder.macroResult = new MacroEvaluationInfo();
        this.macroBuilder = new DebugMacroEvaluationInfoBuilder(this.eventInfoBuilder.macroResult);
    }

    public MacroEvaluationInfoBuilder createMacroEvaluationInfoBuilder() {
        return this.macroBuilder;
    }

    public DataLayerEventEvaluationInfoBuilder createDataLayerEventEvaluationInfoBuilder() {
        return this.dataLayerEventBuilder;
    }

    public void processEventInfo() {
        this.handler.receiveEventInfo(this.eventInfoBuilder);
    }
}
