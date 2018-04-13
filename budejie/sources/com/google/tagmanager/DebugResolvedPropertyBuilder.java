package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.ResolvedProperty;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

class DebugResolvedPropertyBuilder implements ResolvedPropertyBuilder {
    private ResolvedProperty resolvedProperty;

    public DebugResolvedPropertyBuilder(ResolvedProperty resolvedProperty) {
        this.resolvedProperty = resolvedProperty;
    }

    public ValueBuilder createPropertyValueBuilder(Value value) {
        Value copyImmutableValue = DebugValueBuilder.copyImmutableValue(value);
        this.resolvedProperty.value = copyImmutableValue;
        return new DebugValueBuilder(copyImmutableValue);
    }
}
