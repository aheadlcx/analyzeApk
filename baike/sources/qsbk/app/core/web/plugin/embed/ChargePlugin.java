package qsbk.app.core.web.plugin.embed;

import android.app.Activity;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class ChargePlugin extends Plugin {
    public static final int CHAREG_CODE = 199;
    public static final String MODEL = "charge";
    private static final String[] a = new String[]{"charge"};
    private Callback c = null;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (a[0].equalsIgnoreCase(str)) {
            Activity curActivity = this.b.getCurActivity();
            this.c = callback;
            AppUtils.getInstance().getUserInfoProvider().toPay(curActivity, CHAREG_CODE);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == CHAREG_CODE && this.c != null) {
            if (i2 == -1) {
                this.c.sendResult(0, "", "充值成功!");
            } else {
                this.c.sendResult(1, "充值失败,请重试!", "");
            }
            this.c = null;
        }
    }

    public void onDestroy() {
    }
}
