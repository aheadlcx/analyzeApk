package qsbk.app.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.Article;

public class CollectionUtils {
    private CollectionUtils() {
    }

    public static String artilesToJsonString(List<Object> list) {
        List<Article> arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof Article) {
                arrayList.add(next);
            }
        }
        String str = null;
        if (!(arrayList == null || arrayList.isEmpty())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("total", arrayList.size());
                JSONArray jSONArray = new JSONArray();
                for (Article toJSONObject : arrayList) {
                    jSONArray.put(toJSONObject.toJSONObject());
                }
                str = "items";
                jSONObject.put(str, jSONArray);
            } catch (JSONException e) {
                str = e;
                str.printStackTrace();
            } finally {
                jSONObject.toString();
            }
        }
        if (str == null || str.length() <= 2) {
            return "{items:[],total:0}";
        }
        return str;
    }
}
