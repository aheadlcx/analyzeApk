package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

class NoopResolvedPropertyBuilder implements ResolvedPropertyBuilder {
    NoopResolvedPropertyBuilder() {
    }

    public ValueBuilder createPropertyValueBuilder(Value value) {
        return new NoopValueBuilder();
    }
}
