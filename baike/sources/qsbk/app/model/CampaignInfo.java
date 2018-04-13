package qsbk.app.model;

import com.umeng.analytics.pro.b;
import java.io.Serializable;
import org.json.JSONObject;

public class CampaignInfo implements Serializable {
    public int endTime;
    public boolean hasCampaigned;
    public String manifesto;

    private CampaignInfo() {
    }

    public static CampaignInfo parse(JSONObject jSONObject) {
        CampaignInfo campaignInfo = new CampaignInfo();
        campaignInfo.hasCampaigned = jSONObject.optBoolean("has_campaigned");
        campaignInfo.endTime = jSONObject.optInt(b.q);
        campaignInfo.manifesto = jSONObject.optString("reason");
        return campaignInfo;
    }
}
