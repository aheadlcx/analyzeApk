package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
import com.google.analytics.containertag.proto.Debug.ResolvedProperty;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

class DebugResolvedFunctionCallBuilder implements ResolvedFunctionCallBuilder {
    private ResolvedFunctionCall resolvedFunctionCall;

    public DebugResolvedFunctionCallBuilder(ResolvedFunctionCall resolvedFunctionCall) {
        this.resolvedFunctionCall = resolvedFunctionCall;
    }

    public ResolvedPropertyBuilder createResolvedPropertyBuilder(String str) {
        ResolvedProperty resolvedProperty = new ResolvedProperty();
        resolvedProperty.key = str;
        this.resolvedFunctionCall.properties = ArrayUtils.appendToArray(this.resolvedFunctionCall.properties, resolvedProperty);
        return new DebugResolvedPropertyBuilder(resolvedProperty);
    }

    public void setFunctionResult(Value value) {
        this.resolvedFunctionCall.result = DebugValueBuilder.copyImmutableValue(value);
    }
}
