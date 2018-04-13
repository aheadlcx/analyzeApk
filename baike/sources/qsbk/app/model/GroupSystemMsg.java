package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupSystemMsg {
    public static final int TYPE_BE_KICKED = 2;
    public static final int TYPE_FORBIDDEN = 3;
    public static final int TYPE_NAME_CHANGED = 5;
    public static final int TYPE_SELECTION = 1;
    public static final int TYPE_SET_ADMIN = 6;
    public static final int TYPE_UNFORBIDDEN = 4;
    public static final int TYPE_UNSET_ADMIN = 7;
    public String content;
    public String kids;
    public String sid;
    public String sids;
    public int time;
    public int type;

    private static boolean a(char c) {
        return '0' <= c && c <= '9';
    }

    public static boolean containId(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        int i = 0;
        while (i < length) {
            if (str.startsWith(str2, i) && (length2 + i >= length || !a(str.charAt(length2 + i)))) {
                return true;
            }
            while (i < length && a(str.charAt(i))) {
                i++;
            }
            while (i < length && !a(str.charAt(i))) {
                i++;
            }
        }
        return false;
    }

    public void parse(String str) {
        try {
            a(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject) {
        this.type = jSONObject.optInt("type");
        this.content = jSONObject.optString("data");
        this.kids = jSONObject.optString("kids");
        this.sids = jSONObject.optString("sids");
        this.time = jSONObject.optInt("expire_at");
        this.sid = jSONObject.optString("sid");
    }
}
