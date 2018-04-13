package qsbk.app.cafe.plugin;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;

public class LocationPlugin extends Plugin implements LocationCallBack {
    public static final String MODEL = "location";
    LocationHelper a = null;
    private Callback c = null;

    public LocationHelper getLocationHelper() {
        if (this.a == null) {
            this.a = new LocationHelper(this.b.getCurActivity());
        }
        return this.a;
    }

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if ("get_location".equalsIgnoreCase(str)) {
            this.c = callback;
            getLocationHelper().startLocate(this);
            return;
        }
        callback.sendResult(1, "not implemented", "");
    }

    public void onDestroy() {
    }

    public void onLocateFail(int i) {
        if (this.c != null) {
            this.c.sendResult(1, "location fail type:" + i, "");
            this.c = null;
        }
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        if (this.c != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ParamKey.LATITUDE, d);
                jSONObject.put(ParamKey.LONGITUDE, d2);
                jSONObject.put("district", str);
                jSONObject.put("city", str2);
            } catch (Exception e) {
            }
            this.c.sendResult(0, "", jSONObject);
            this.c = null;
        }
    }
}
