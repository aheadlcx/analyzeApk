package com.google.tagmanager;

class NoopEventInfoDistributor implements EventInfoDistributor {
    NoopEventInfoDistributor() {
    }

    public EventInfoBuilder createMacroEvalutionEventInfo(String str) {
        return new NoopEventInfoBuilder();
    }

    public EventInfoBuilder createDataLayerEventEvaluationEventInfo(String str) {
        return new NoopEventInfoBuilder();
    }

    public boolean debugMode() {
        return false;
    }
}
