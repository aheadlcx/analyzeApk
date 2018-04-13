package qsbk.app.core.net.response;

import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.utils.AppUtils;

public class BaseResponse {
    public String key;
    public JSONObject parent;
    public String response;
    public String token;

    public BaseResponse(JSONObject jSONObject) {
        this.response = jSONObject != null ? jSONObject.toString() : null;
        this.parent = jSONObject;
    }

    public JSONObject getParent() {
        return this.parent;
    }

    public JSONObject getData() {
        return this.parent.optJSONObject("data");
    }

    public String getDataStr() {
        JSONObject data = getData();
        String str = "";
        if (data != null) {
            return data.toString();
        }
        return str;
    }

    public String getSimpleDataStr() {
        return getSimpleDataStr("data");
    }

    public String getSimpleDataStr(String str) {
        return this.parent.optString(str);
    }

    public int getSimpleDataInt(String str) {
        return this.parent.optInt(str);
    }

    public boolean getSimpleDataBoolean(String str) {
        return this.parent.optBoolean(str);
    }

    public long getSimpleDataLong(String str) {
        return this.parent.optLong(str);
    }

    public <T> T getResponse(TypeToken<T> typeToken) {
        return AppUtils.fromJson(this.response, typeToken);
    }

    public <T> T getResponse(String str, TypeToken<T> typeToken) {
        JSONObject optJSONObject = this.parent.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return AppUtils.fromJson(optJSONObject.toString(), typeToken);
    }

    public <T> List<T> getListResponse(TypeToken<List<T>> typeToken) {
        return (List) AppUtils.fromJson(getDataArrStr(), typeToken);
    }

    public <T> List<T> getListResponse(String str, TypeToken<List<T>> typeToken) {
        JSONArray optJSONArray = this.parent.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        return (List) AppUtils.fromJson(optJSONArray.toString(), typeToken);
    }

    public JSONArray getDataArr() {
        return this.parent.optJSONArray("data");
    }

    public String getDataArrStr() {
        JSONArray dataArr = getDataArr();
        String str = "";
        if (dataArr != null) {
            return dataArr.toString();
        }
        return str;
    }
}
