package qsbk.app.model;

import com.baidu.mobstat.Config;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupInfo$PicInfo implements Serializable {
    public String picUrl;
    public int status;

    public GroupInfo$PicInfo(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.picUrl = jSONObject.getString("pic_url");
            if (!(this.picUrl == null || this.picUrl.endsWith("/format/webp"))) {
                this.picUrl += "/format/webp";
            }
            this.status = jSONObject.optInt(Config.STAT_SDK_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
