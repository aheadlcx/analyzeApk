package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import org.json.JSONObject;

public interface OkHttpResponseAndJSONObjectRequestListener {
    void onError(ANError aNError);

    void onResponse(Response response, JSONObject jSONObject);
}
