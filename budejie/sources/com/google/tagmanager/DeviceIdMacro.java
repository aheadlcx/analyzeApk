package com.google.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

class DeviceIdMacro extends FunctionCallImplementation {
    private static final String ID = FunctionType.DEVICE_ID.toString();
    private final Context mContext;

    public static String getFunctionId() {
        return ID;
    }

    public DeviceIdMacro(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        String androidId = getAndroidId(this.mContext);
        return androidId == null ? Types.getDefaultValue() : Types.objectToValue(androidId);
    }

    @VisibleForTesting
    protected String getAndroidId(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}
