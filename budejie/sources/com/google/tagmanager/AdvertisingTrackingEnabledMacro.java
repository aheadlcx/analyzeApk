package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class AdvertisingTrackingEnabledMacro extends FunctionCallImplementation {
    private static final String ID = FunctionType.ADVERTISING_TRACKING_ENABLED.toString();

    public static String getFunctionId() {
        return ID;
    }

    public AdvertisingTrackingEnabledMacro() {
        super(ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        return Types.objectToValue(Boolean.valueOf(true));
    }
}
