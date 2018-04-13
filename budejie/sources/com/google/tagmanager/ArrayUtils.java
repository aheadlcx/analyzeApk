package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.EventInfo;
import com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
import com.google.analytics.containertag.proto.Debug.ResolvedProperty;
import com.google.analytics.containertag.proto.Debug.ResolvedRule;

class ArrayUtils {
    private ArrayUtils() {
    }

    public static EventInfo[] appendToArray(EventInfo[] eventInfoArr, EventInfo eventInfo) {
        Object obj = new EventInfo[(eventInfoArr.length + 1)];
        System.arraycopy(eventInfoArr, 0, obj, 0, eventInfoArr.length);
        obj[eventInfoArr.length] = eventInfo;
        return obj;
    }

    public static ResolvedFunctionCall[] appendToArray(ResolvedFunctionCall[] resolvedFunctionCallArr, ResolvedFunctionCall resolvedFunctionCall) {
        Object obj = new ResolvedFunctionCall[(resolvedFunctionCallArr.length + 1)];
        System.arraycopy(resolvedFunctionCallArr, 0, obj, 0, resolvedFunctionCallArr.length);
        obj[resolvedFunctionCallArr.length] = resolvedFunctionCall;
        return obj;
    }

    public static ResolvedProperty[] appendToArray(ResolvedProperty[] resolvedPropertyArr, ResolvedProperty resolvedProperty) {
        Object obj = new ResolvedProperty[(resolvedPropertyArr.length + 1)];
        System.arraycopy(resolvedPropertyArr, 0, obj, 0, resolvedPropertyArr.length);
        obj[resolvedPropertyArr.length] = resolvedProperty;
        return obj;
    }

    public static ResolvedRule[] appendToArray(ResolvedRule[] resolvedRuleArr, ResolvedRule resolvedRule) {
        Object obj = new ResolvedRule[(resolvedRuleArr.length + 1)];
        System.arraycopy(resolvedRuleArr, 0, obj, 0, resolvedRuleArr.length);
        obj[resolvedRuleArr.length] = resolvedRule;
        return obj;
    }
}
