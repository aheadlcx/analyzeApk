package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.PrefrenceKeys;

class ei extends NetworkCallback {
    final /* synthetic */ LivePushActivity a;

    ei(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onPreExecute() {
        super.onPreExecute();
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.cq = 0;
        this.a.ch = jSONObject.optString("rtmp_publish_url");
        this.a.aA = jSONObject.optString("stream_id");
        LogUtils.d(LivePushActivity.TAG, "live address:" + this.a.ch + ", and stream id:" + this.a.aA);
        this.a.d = (CommonVideo) new BaseResponse(jSONObject).getResponse(new ej(this));
        this.a.d.stream_id = this.a.aA;
        this.a.d.rtmp_live_url = this.a.ch;
        this.a.d.game_id = (long) this.a.cG;
        this.a.bv = this.a.d.share;
        if (this.a.bu != null) {
            this.a.ap();
        } else {
            this.a.aJ();
        }
        this.a.d();
        this.a.g(this.a.cI ? CustomButton.POSITION_BOTTOM : "a");
    }

    public void onFailed(int i, String str) {
        if (this.a.bF.getVisibility() == 0) {
            this.a.i();
        } else {
            this.a.H();
        }
    }

    public void onFinished() {
        this.a.cg.setEnabled(true);
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("content", this.a.bM.getText().toString().trim());
        hashMap.put("enable", this.a.cq + "");
        if (this.a.bN.isSelected() && this.a.cu != null && this.a.cu.isValid()) {
            hashMap.put("lat", this.a.cu.latitude + "");
            hashMap.put("lon", this.a.cu.longitude + "");
            hashMap.put("city", this.a.cu.city);
            hashMap.put("district", this.a.cu.district);
        }
        hashMap.put("live_source", PreferenceUtils.instance().getString(PrefrenceKeys.LIVE_PUSH_SOURCE, "2"));
        if (this.a.cF) {
            hashMap.put("game_id", this.a.cG + "");
        }
        if (this.a.ao && this.a.ap) {
            hashMap.put("game_id", "666");
        }
        return hashMap;
    }
}
