package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import org.json.JSONObject;

public interface JSONObjectRequestListener {
    void onError(ANError aNError);

    void onResponse(JSONObject jSONObject);
}
