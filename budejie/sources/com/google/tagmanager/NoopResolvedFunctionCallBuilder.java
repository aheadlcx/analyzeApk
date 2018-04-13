package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

class NoopResolvedFunctionCallBuilder implements ResolvedFunctionCallBuilder {
    NoopResolvedFunctionCallBuilder() {
    }

    public ResolvedPropertyBuilder createResolvedPropertyBuilder(String str) {
        return new NoopResolvedPropertyBuilder();
    }

    public void setFunctionResult(Value value) {
    }
}
