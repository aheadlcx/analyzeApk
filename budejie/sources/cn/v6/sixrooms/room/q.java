package cn.v6.sixrooms.room;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity.GtAppDlgTask;
import com.a.a.d.a;
import org.json.JSONObject;

final class q implements a {
    final /* synthetic */ GtAppDlgTask a;

    q(GtAppDlgTask gtAppDlgTask) {
        this.a = gtAppDlgTask;
    }

    public final void gtResult(boolean z, String str) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.a.a.handler.post(new r(this, jSONObject.getString("geetest_challenge"), jSONObject.getString("geetest_validate"), jSONObject.getString("geetest_seccode")));
            } catch (Exception e) {
                this.a.a.handleErrorResult("10002", this.a.a.getString(R.string.gt_error), this.a.a);
            }
        }
    }

    public final void closeGt() {
    }
}
