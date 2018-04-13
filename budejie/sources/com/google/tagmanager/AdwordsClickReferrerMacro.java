package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class AdwordsClickReferrerMacro extends FunctionCallImplementation {
    private static final String COMPONENT = Key.COMPONENT.toString();
    private static final String CONVERSION_ID = Key.CONVERSION_ID.toString();
    private static final String ID = FunctionType.ADWORDS_CLICK_REFERRER.toString();
    private final Context context;

    public static String getFunctionId() {
        return ID;
    }

    public AdwordsClickReferrerMacro(Context context) {
        super(ID, CONVERSION_ID);
        this.context = context;
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        Value value = (Value) map.get(CONVERSION_ID);
        if (value == null) {
            return Types.getDefaultValue();
        }
        String valueToString = Types.valueToString(value);
        value = (Value) map.get(COMPONENT);
        String clickReferrer = InstallReferrerUtil.getClickReferrer(this.context, valueToString, value != null ? Types.valueToString(value) : null);
        return clickReferrer != null ? Types.objectToValue(clickReferrer) : Types.getDefaultValue();
    }
}
