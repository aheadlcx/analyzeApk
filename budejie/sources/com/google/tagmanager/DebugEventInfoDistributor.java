package com.google.tagmanager;

class DebugEventInfoDistributor implements EventInfoDistributor {
    private String containerId;
    private String containerVersion;
    private DebugInformationHandler handler;

    public DebugEventInfoDistributor(DebugInformationHandler debugInformationHandler, String str, String str2) {
        this.handler = debugInformationHandler;
        this.containerVersion = str;
        this.containerId = str2;
    }

    public EventInfoBuilder createMacroEvalutionEventInfo(String str) {
        return new DebugEventInfoBuilder(2, this.containerVersion, this.containerId, str, this.handler);
    }

    public EventInfoBuilder createDataLayerEventEvaluationEventInfo(String str) {
        return new DebugEventInfoBuilder(1, this.containerVersion, this.containerId, str, this.handler);
    }

    public boolean debugMode() {
        return true;
    }
}
