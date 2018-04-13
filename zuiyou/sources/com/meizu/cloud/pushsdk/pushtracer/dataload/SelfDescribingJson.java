package com.meizu.cloud.pushsdk.pushtracer.dataload;

import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Preconditions;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.HashMap;
import java.util.Map;

public class SelfDescribingJson implements DataLoad {
    private final String TAG;
    private final HashMap<String, Object> payload;

    public SelfDescribingJson(String str) {
        this(str, new HashMap());
    }

    public SelfDescribingJson(String str, TrackerDataload trackerDataload) {
        this.TAG = SelfDescribingJson.class.getSimpleName();
        this.payload = new HashMap();
        setSchema(str);
        setData(trackerDataload);
    }

    public SelfDescribingJson(String str, SelfDescribingJson selfDescribingJson) {
        this.TAG = SelfDescribingJson.class.getSimpleName();
        this.payload = new HashMap();
        setSchema(str);
        setData(selfDescribingJson);
    }

    public SelfDescribingJson(String str, Object obj) {
        this.TAG = SelfDescribingJson.class.getSimpleName();
        this.payload = new HashMap();
        setSchema(str);
        setData(obj);
    }

    public SelfDescribingJson setSchema(String str) {
        Preconditions.checkNotNull(str, "schema cannot be null");
        Preconditions.checkArgument(!str.isEmpty(), "schema cannot be empty.");
        this.payload.put(Parameters.SCHEMA, str);
        return this;
    }

    public SelfDescribingJson setData(TrackerDataload trackerDataload) {
        if (trackerDataload != null) {
            this.payload.put(Parameters.DATA, trackerDataload.getMap());
        }
        return this;
    }

    public SelfDescribingJson setData(Object obj) {
        if (obj != null) {
            this.payload.put(Parameters.DATA, obj);
        }
        return this;
    }

    public SelfDescribingJson setData(SelfDescribingJson selfDescribingJson) {
        if (this.payload != null) {
            this.payload.put(Parameters.DATA, selfDescribingJson.getMap());
        }
        return this;
    }

    @Deprecated
    public void add(String str, String str2) {
        Logger.i(this.TAG, "Payload: add(String, String) method called - Doing nothing.", new Object[0]);
    }

    @Deprecated
    public void add(String str, Object obj) {
        Logger.i(this.TAG, "Payload: add(String, Object) method called - Doing nothing.", new Object[0]);
    }

    @Deprecated
    public void addMap(Map<String, Object> map) {
        Logger.i(this.TAG, "Payload: addMap(Map<String, Object>) method called - Doing nothing.", new Object[0]);
    }

    @Deprecated
    public void addMap(Map map, Boolean bool, String str, String str2) {
        Logger.i(this.TAG, "Payload: addMap(Map, Boolean, String, String) method called - Doing nothing.", new Object[0]);
    }

    public Map<String, Object> getMap() {
        return this.payload;
    }

    public String toString() {
        return Util.mapToJSONObject(this.payload).toString();
    }

    public long getByteSize() {
        return Util.getUTF8Length(toString());
    }
}
