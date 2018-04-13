package qsbk.app.model;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;

public class RssArticle extends Article {
    public String[] actions;
    public int actorCount;
    public List<BaseUser> actors;
    public String city;
    public String distance;
    public String district;
    public String type;

    public interface Type {
        public static final String HOT = "hot";
        public static final String NEARBY = "nearby";
        public static final String PREFER = "prefer";
        public static final String PROMOTE = "promote";
        public static final String SUB = "sub";
    }

    public RssArticle(JSONObject jSONObject) throws QiushibaikeException {
        super(jSONObject);
    }

    public boolean containsAction(String str) {
        if (this.actions == null || this.actions.length <= 0) {
            return false;
        }
        for (String equals : this.actions) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    protected void a(JSONObject jSONObject) throws QiushibaikeException {
        int i = 0;
        super.a(jSONObject);
        this.type = jSONObject.optString("type");
        this.distance = jSONObject.optString("distance");
        this.district = jSONObject.optString("district");
        this.city = jSONObject.optString("city");
        this.actorCount = jSONObject.optInt("actors_num");
        JSONArray optJSONArray = jSONObject.optJSONArray("action");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            int length = optJSONArray.length();
            this.actions = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                this.actions[i2] = optJSONArray.optString(i2);
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("actors");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            int length2 = optJSONArray2.length();
            this.actors = new LinkedList();
            while (i < length2) {
                this.actors.add(BaseUser.optFromJsonObject(optJSONArray2.optJSONObject(i)));
                i++;
            }
        }
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONArray jSONArray;
        int i;
        int size;
        JSONObject toJSONObject = super.toJSONObject();
        toJSONObject.put("type", this.type);
        toJSONObject.put("distance", this.distance);
        toJSONObject.put("district", this.district);
        toJSONObject.put("city", this.city);
        toJSONObject.put("actors_num", this.actorCount);
        if (this.actions != null && this.actions.length > 0) {
            jSONArray = new JSONArray();
            for (Object put : this.actions) {
                jSONArray.put(put);
            }
            toJSONObject.put("action", jSONArray);
        }
        if (this.actors != null && this.actors.size() > 0) {
            size = this.actors.size();
            jSONArray = new JSONArray();
            for (i = 0; i < size; i++) {
                jSONArray.put(((BaseUser) this.actors.get(i)).encodeToJsonObject());
            }
            toJSONObject.put("actors", jSONArray);
        }
        return toJSONObject;
    }
}
