package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import com.google.tagmanager.ResourceUtil.ExpandedResource;
import com.google.tagmanager.ResourceUtil.ExpandedResourceBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonUtils {
    private JsonUtils() {
    }

    public static ExpandedResource expandedResourceFromJsonString(String str) throws JSONException {
        Value jsonObjectToValue = jsonObjectToValue(new JSONObject(str));
        ExpandedResourceBuilder newBuilder = ExpandedResource.newBuilder();
        for (int i = 0; i < jsonObjectToValue.mapKey.length; i++) {
            newBuilder.addMacro(ExpandedFunctionCall.newBuilder().addProperty(Key.INSTANCE_NAME.toString(), jsonObjectToValue.mapKey[i]).addProperty(Key.FUNCTION.toString(), Types.functionIdToValue(ConstantMacro.getFunctionId())).addProperty(ConstantMacro.getValueKey(), jsonObjectToValue.mapValue[i]).build());
        }
        return newBuilder.build();
    }

    private static Value jsonObjectToValue(Object obj) throws JSONException {
        return Types.objectToValue(jsonObjectToObject(obj));
    }

    @VisibleForTesting
    static Object jsonObjectToObject(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            Map hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, jsonObjectToObject(jSONObject.get(str)));
            }
            return hashMap;
        }
    }
}
