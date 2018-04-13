package qsbk.app.core.net;

import org.json.JSONObject;
import qsbk.app.core.net.response.BaseResponse;

public abstract class Callback extends NetworkCallback {
    public void onSuccess(JSONObject jSONObject) {
        onSuccess(new BaseResponse(jSONObject));
    }

    public void onSuccess(BaseResponse baseResponse) {
    }
}
