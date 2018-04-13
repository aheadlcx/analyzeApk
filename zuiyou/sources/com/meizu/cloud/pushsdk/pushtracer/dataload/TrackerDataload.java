package com.meizu.cloud.pushsdk.pushtracer.dataload;

import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.HashMap;
import java.util.Map;

public class TrackerDataload implements DataLoad {
    private final String TAG = TrackerDataload.class.getSimpleName();
    private final HashMap<String, Object> dataload = new HashMap();

    public void add(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Logger.i(this.TAG, "The keys value is empty, returning without adding key: " + str, new Object[0]);
        } else {
            this.dataload.put(str, str2);
        }
    }

    public void add(String str, Object obj) {
        if (obj == null) {
            Logger.i(this.TAG, "The keys value is empty, returning without adding key: " + str, new Object[0]);
        } else {
            this.dataload.put(str, obj);
        }
    }

    public void addMap(Map<String, Object> map) {
        if (map == null) {
            Logger.i(this.TAG, "Map passed in is null, returning without adding map.", new Object[0]);
        } else {
            this.dataload.putAll(map);
        }
    }

    public void addMap(Map map, Boolean bool, String str, String str2) {
        if (map == null) {
            Logger.i(this.TAG, "Map passed in is null, returning nothing.", new Object[0]);
            return;
        }
        String jSONObject = Util.mapToJSONObject(map).toString();
        Logger.i(this.TAG, "Adding new map: " + jSONObject, new Object[0]);
        if (bool.booleanValue()) {
            add(str, Util.base64Encode(jSONObject));
        } else {
            add(str2, jSONObject);
        }
    }

    public Map getMap() {
        return this.dataload;
    }

    public String toString() {
        return Util.mapToJSONObject(this.dataload).toString();
    }

    public long getByteSize() {
        return Util.getUTF8Length(toString());
    }
}
