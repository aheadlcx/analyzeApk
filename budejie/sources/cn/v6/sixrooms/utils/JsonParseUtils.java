package cn.v6.sixrooms.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseUtils {
    private static Gson a = new Gson();

    public static <T> String obj2Json(T t) {
        return a.toJson(t);
    }

    public static <T> T json2Obj(String str, Class<T> cls) {
        return a.fromJson(str, cls);
    }

    public static <T> T json2List(String str, Type type) {
        return a.fromJson(str, type);
    }

    public static String getString(JSONObject jSONObject, String str) {
        String str2 = "";
        try {
            str2 = jSONObject.getString(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static int getInt(JSONObject jSONObject, String str) {
        int i = -1;
        try {
            i = jSONObject.getInt(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static JSONObject getJSONObject(JSONObject jSONObject, String str) {
        JSONObject jSONObject2 = null;
        try {
            jSONObject2 = jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject2;
    }

    public static boolean isJson(String str) {
        return new JsonParser().parse(str).isJsonObject();
    }

    public static boolean isJsonArray(String str) {
        return new JsonParser().parse(str).isJsonArray();
    }

    public static Gson getGson() {
        return a;
    }
}
