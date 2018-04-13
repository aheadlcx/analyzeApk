package qsbk.app.live.widget;

import org.json.JSONObject;
import qsbk.app.core.net.Callback;

class gp extends Callback {
    final /* synthetic */ LevelGiftDialog a;

    gp(LevelGiftDialog levelGiftDialog) {
        this.a = levelGiftDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("left_seconds");
        if (optLong > 0) {
            this.a.a(optLong);
        }
    }
}
