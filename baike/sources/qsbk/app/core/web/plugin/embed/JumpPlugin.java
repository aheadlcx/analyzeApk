package qsbk.app.core.web.plugin.embed;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class JumpPlugin extends Plugin {
    public static final String ACTION_TO_WITHDRAW = "toWithdraw";
    public static final String MODEL = "jump";

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (ACTION_TO_WITHDRAW.equals(str)) {
            AppUtils.getInstance().getUserInfoProvider().toJump(this.b.getCurActivity(), ACTION_TO_WITHDRAW);
        }
    }

    public void onDestroy() {
    }
}
