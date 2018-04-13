package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

interface ResolvedFunctionCallBuilder {
    ResolvedPropertyBuilder createResolvedPropertyBuilder(String str);

    void setFunctionResult(Value value);
}
