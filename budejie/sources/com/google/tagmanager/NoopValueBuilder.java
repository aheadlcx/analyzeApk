package com.google.tagmanager;

class NoopValueBuilder implements ValueBuilder {
    NoopValueBuilder() {
    }

    public ValueBuilder getListItem(int i) {
        return new NoopValueBuilder();
    }

    public ValueBuilder getMapKey(int i) {
        return new NoopValueBuilder();
    }

    public ValueBuilder getMapValue(int i) {
        return new NoopValueBuilder();
    }

    public ValueBuilder getTemplateToken(int i) {
        return new NoopValueBuilder();
    }

    public MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension() {
        return new NoopMacroEvaluationInfoBuilder();
    }
}
