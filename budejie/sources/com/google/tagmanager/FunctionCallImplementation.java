package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class FunctionCallImplementation {
    private static final String FUNCTION_KEY = "function";
    private final String mFunctionId;
    private final Set<String> mRequiredKeys;

    public abstract Value evaluate(Map<String, Value> map);

    public abstract boolean isCacheable();

    public static String getFunctionKey() {
        return FUNCTION_KEY;
    }

    public String getInstanceFunctionId() {
        return this.mFunctionId;
    }

    public FunctionCallImplementation(String str, String... strArr) {
        this.mFunctionId = str;
        this.mRequiredKeys = new HashSet(strArr.length);
        for (Object add : strArr) {
            this.mRequiredKeys.add(add);
        }
    }

    public Set<String> getRequiredKeys() {
        return this.mRequiredKeys;
    }

    boolean hasRequiredKeys(Set<String> set) {
        return set.containsAll(this.mRequiredKeys);
    }
}
