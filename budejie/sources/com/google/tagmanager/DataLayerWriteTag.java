package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.List;
import java.util.Map;

class DataLayerWriteTag extends TrackingTag {
    private static final String CLEAR_PERSISTENT_DATA_LAYER_PREFIX = Key.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private static final String ID = FunctionType.DATA_LAYER_WRITE.toString();
    private static final String VALUE = Key.VALUE.toString();
    private final DataLayer mDataLayer;

    public static String getFunctionId() {
        return ID;
    }

    public DataLayerWriteTag(DataLayer dataLayer) {
        super(ID, VALUE);
        this.mDataLayer = dataLayer;
    }

    public void evaluateTrackingTag(Map<String, Value> map) {
        pushToDataLayer((Value) map.get(VALUE));
        clearPersistent((Value) map.get(CLEAR_PERSISTENT_DATA_LAYER_PREFIX));
    }

    private void clearPersistent(Value value) {
        if (value != null && value != Types.getDefaultObject()) {
            String valueToString = Types.valueToString(value);
            if (valueToString != Types.getDefaultString()) {
                this.mDataLayer.clearPersistentKeysWithPrefix(valueToString);
            }
        }
    }

    private void pushToDataLayer(Value value) {
        if (value != null && value != Types.getDefaultObject()) {
            Object valueToObject = Types.valueToObject(value);
            if (valueToObject instanceof List) {
                for (Object valueToObject2 : (List) valueToObject2) {
                    if (valueToObject2 instanceof Map) {
                        this.mDataLayer.push((Map) valueToObject2);
                    }
                }
            }
        }
    }
}
