package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

interface ResolvedPropertyBuilder {
    ValueBuilder createPropertyValueBuilder(Value value);
}
