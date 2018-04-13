package qsbk.app.live.ui;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.map.Location;
import qsbk.app.core.map.NearbyEngine;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.AppUtils;

class cs extends NetworkCallback {
    final /* synthetic */ LiveBaseActivity a;

    cs(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onPreExecute() {
        this.a.aG = true;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.a(jSONObject);
        if (!jSONObject.isNull("distance")) {
            this.a.mDistance = jSONObject.optDouble("distance");
        }
        this.a.aj();
    }

    public void onFailed(int i, String str) {
        this.a.b("(" + i + ")" + str);
        if (i == -20) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(this.a.getActivity(), 1001);
            return;
        }
        if (i < 0) {
            this.a.f(str);
        }
        this.a.N();
    }

    public void onFinished() {
        this.a.aG = false;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("room_id", Long.toString(this.a.aE));
        if (this.a.d.author.getPlatformId() > 0) {
            hashMap.put("anchorid", this.a.d.author.getOriginId() + "");
            hashMap.put("anchorsource", this.a.d.author.getOrigin() + "");
            Location lastSavedLocation = NearbyEngine.getLastSavedLocation(false);
            if (lastSavedLocation != null) {
                hashMap.put(ParamKey.LONGITUDE, lastSavedLocation.longitude + "");
                hashMap.put(ParamKey.LATITUDE, lastSavedLocation.latitude + "");
            } else if (!(this.a.bf == ((double) Location.NON_LOCATION) || this.a.bg == ((double) Location.NON_LOCATION))) {
                hashMap.put(ParamKey.LONGITUDE, this.a.bf + "");
                hashMap.put(ParamKey.LATITUDE, this.a.bg + "");
            }
        }
        return hashMap;
    }
}
