package qsbk.app.widget;

import com.facebook.common.util.UriUtil;
import org.json.JSONObject;
import qsbk.app.R;

public class QiushiEmotionHandler$EmotionData {
    public static final QiushiEmotionHandler$EmotionData DELETE = new QiushiEmotionHandler$EmotionData("DELETE_EMOTION", "qb_s_delete", R.drawable.qb_s_delete);
    private String a;
    private String b;
    private int c;

    QiushiEmotionHandler$EmotionData(JSONObject jSONObject) {
        this.a = jSONObject.optString("name");
        this.b = jSONObject.optString(UriUtil.LOCAL_RESOURCE_SCHEME);
    }

    QiushiEmotionHandler$EmotionData(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public String getName() {
        return this.a;
    }

    public String getRes() {
        return this.b;
    }

    public int getResId() {
        return this.c;
    }
}
