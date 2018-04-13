package com.google.tagmanager;

import android.os.Build.VERSION;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class OsVersionMacro extends FunctionCallImplementation {
    private static final String ID = FunctionType.OS_VERSION.toString();

    public static String getFunctionId() {
        return ID;
    }

    public OsVersionMacro() {
        super(ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        return Types.objectToValue(VERSION.RELEASE);
    }
}
