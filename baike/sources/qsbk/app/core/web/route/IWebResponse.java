package qsbk.app.core.web.route;

import org.json.JSONException;
import org.json.JSONObject;

public interface IWebResponse {
    void webResponse(JSONObject jSONObject) throws JSONException;
}
