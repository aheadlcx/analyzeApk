package qsbk.app.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CircleTopicCategory extends QbBean {
    public String id;
    public String name;
    public int rank;
    public int status;
    public String updateAt;
    public String updateId;

    public static CircleTopicCategory parseJson(JSONObject jSONObject) {
        CircleTopicCategory circleTopicCategory = new CircleTopicCategory();
        circleTopicCategory.name = jSONObject.optString("name");
        circleTopicCategory.id = jSONObject.optString("id");
        circleTopicCategory.updateAt = jSONObject.optString("updated_at");
        circleTopicCategory.updateId = jSONObject.optString("updator_id");
        return circleTopicCategory;
    }

    public static ArrayList<CircleTopicCategory> parseJsonArray(JSONArray jSONArray) {
        ArrayList<CircleTopicCategory> arrayList = new ArrayList();
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(parseJson(jSONArray.getJSONObject(i)));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CircleTopicCategory circleTopicCategory = (CircleTopicCategory) obj;
        if (this.id != null) {
            return this.id.equals(circleTopicCategory.id);
        }
        if (circleTopicCategory.id != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }
}
