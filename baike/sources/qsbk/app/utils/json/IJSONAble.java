package qsbk.app.utils.json;

import org.json.JSONObject;

public interface IJSONAble {
    JSONObject encodeToJsonObject();

    void initJSONDefaultValue();

    void parseFromJSONObject(JSONObject jSONObject);
}
