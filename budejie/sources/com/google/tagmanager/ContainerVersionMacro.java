package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class ContainerVersionMacro extends FunctionCallImplementation {
    private static final String ID = FunctionType.CONTAINER_VERSION.toString();
    private final Runtime mRuntime;

    public static String getFunctionId() {
        return ID;
    }

    public ContainerVersionMacro(Runtime runtime) {
        super(ID, new String[0]);
        this.mRuntime = runtime;
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        String version = this.mRuntime.getResource().getVersion();
        return version == null ? Types.getDefaultValue() : Types.objectToValue(version);
    }
}
