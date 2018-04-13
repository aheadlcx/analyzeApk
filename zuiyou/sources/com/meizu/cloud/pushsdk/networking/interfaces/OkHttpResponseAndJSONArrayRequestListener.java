package com.meizu.cloud.pushsdk.networking.interfaces;

import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import org.json.JSONArray;

public interface OkHttpResponseAndJSONArrayRequestListener {
    void onError(ANError aNError);

    void onResponse(Response response, JSONArray jSONArray);
}
