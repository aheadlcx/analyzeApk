package qsbk.app.live.widget;

import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGameHistoryData;

class bz extends NetworkCallback {
    final /* synthetic */ GameHistoryDialog a;

    bz(GameHistoryDialog gameHistoryDialog) {
        this.a = gameHistoryDialog;
    }

    public Map<String, String> getParams() {
        Map hashMap = new HashMap();
        hashMap.put("limit", "20");
        hashMap.put(SocialConstants.PARAM_APP_DESC, String.valueOf(true));
        hashMap.put("roomid", String.valueOf(this.a.l));
        hashMap.put("gameid", String.valueOf(this.a.m));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.has("msg")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("msg");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("extra");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                if (this.a.j()) {
                    this.a.j.clear();
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        LiveGameHistoryData liveGameHistoryData = new LiveGameHistoryData();
                        liveGameHistoryData.w = optJSONArray.optInt(i);
                        if (i < optJSONArray2.length()) {
                            JSONArray optJSONArray3 = optJSONArray2.optJSONArray(i);
                            liveGameHistoryData.h = new ArrayList();
                            if (optJSONArray3 != null) {
                                for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                                    liveGameHistoryData.h.add(Integer.valueOf(optJSONArray3.optInt(i2)));
                                }
                            }
                        }
                        arrayList.add(liveGameHistoryData);
                    }
                    this.a.j.addAll(arrayList);
                } else if (this.a.k()) {
                    this.a.k.clear();
                    this.a.k.addAll((Collection) AppUtils.fromJson(optJSONArray.toString(), new ca(this)));
                } else {
                    this.a.j.clear();
                    this.a.j.addAll((Collection) AppUtils.fromJson(optJSONArray.toString(), new cb(this)));
                }
                this.a.f.notifyDataSetChanged();
            }
        }
        if (this.a.i.isEmpty() && this.a.j.isEmpty() && this.a.k.isEmpty()) {
            this.a.g.setTextOnly(AppUtils.getInstance().getAppContext().getString(R.string.live_game_no_history));
        } else {
            this.a.g.hide();
        }
    }

    public void onFailed(int i, String str) {
        if (this.a.i.isEmpty() && this.a.j.isEmpty()) {
            this.a.g.showError(this.a.h, i, str, new cc(this));
        } else {
            this.a.g.hide();
        }
    }
}
