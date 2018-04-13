package qsbk.app.live.widget;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class hl extends Callback {
    final /* synthetic */ LivePushEndDialog a;

    hl(LivePushEndDialog livePushEndDialog) {
        this.a = livePushEndDialog;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("room_id", this.a.n + "");
        hashMap.put("stream_id", this.a.o);
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            JSONObject optJSONObject = baseResponse.getParent().optJSONObject("record");
            if (optJSONObject != null && optJSONObject.optBoolean("is_cover")) {
                this.a.j = optJSONObject.optLong("duration");
                this.a.k = optJSONObject.optLong("visitor_count");
                this.a.m = optJSONObject.optLong("coupon");
                this.a.l = optJSONObject.optLong("vote_count");
                this.a.k();
            }
        }
    }
}
