package cn.xiaochuankeji.tieba.background.favorite;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Favorite implements Serializable {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_POST_COUNT = "post_count";
    private long id;
    private String name;
    private int postCount;

    public Favorite(JSONObject jSONObject) {
        this.id = jSONObject.optLong("id");
        this.name = jSONObject.optString("name");
        this.postCount = jSONObject.optInt(KEY_POST_COUNT);
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("name", this.name);
        jSONObject.put(KEY_POST_COUNT, this.postCount);
        return jSONObject;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPostCount() {
        return this.postCount;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPostCount(int i) {
        this.postCount = i;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Favorite) && ((Favorite) obj).id == this.id) {
            return true;
        }
        return false;
    }
}
