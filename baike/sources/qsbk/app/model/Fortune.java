package qsbk.app.model;

import org.json.JSONObject;

public class Fortune {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_RATE = 2;
    public String name;
    public int type;
    public String value;

    public Fortune(JSONObject jSONObject) {
        this.name = jSONObject.optString("name");
        this.type = jSONObject.optInt("type");
        this.value = jSONObject.optString("value");
    }

    public boolean isRateType() {
        return this.type == 2;
    }
}
