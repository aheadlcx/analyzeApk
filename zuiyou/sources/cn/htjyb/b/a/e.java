package cn.htjyb.b.a;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class e<T> extends d<T> {
    protected boolean mServerMore = false;
    protected long mServerOffset = 0;

    protected long getQueryMoreOffset() {
        return this.mServerOffset;
    }

    public boolean hasMore() {
        return this.mServerMore;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        int i = 0;
        if (0 == this._offset) {
            this._items.clear();
        }
        if (jSONObject.optInt("more") != 1) {
            z = false;
        }
        this.mServerMore = z;
        this.mServerOffset = (long) jSONObject.optInt("offset");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || parseItem(optJSONObject) == null)) {
                    this._items.add(parseItem(optJSONObject));
                }
                i++;
            }
        }
        notifyListUpdate();
    }

    public void clear() {
        this.mServerMore = false;
        this.mServerOffset = 0;
        super.clear();
    }
}
