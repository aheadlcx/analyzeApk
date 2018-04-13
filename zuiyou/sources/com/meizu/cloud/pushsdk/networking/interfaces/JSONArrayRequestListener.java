package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import org.json.JSONArray;

public interface JSONArrayRequestListener {
    void onError(ANError aNError);

    void onResponse(JSONArray jSONArray);
}
