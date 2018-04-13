package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

abstract class TrackingTag extends FunctionCallImplementation {
    public abstract void evaluateTrackingTag(Map<String, Value> map);

    public TrackingTag(String str, String... strArr) {
        super(str, strArr);
    }

    public boolean isCacheable() {
        return false;
    }

    public Value evaluate(Map<String, Value> map) {
        evaluateTrackingTag(map);
        return Types.getDefaultValue();
    }
}
