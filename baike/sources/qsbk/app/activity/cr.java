package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.ToastAndDialog;

class cr implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ cq b;

    cr(cq cqVar, JSONObject jSONObject) {
        this.b = cqVar;
        this.a = jSONObject;
    }

    public void run() {
        if (this.b.c != null && !this.b.c.isFinishing()) {
            try {
                if (this.a.has("circle_score") && this.a.getInt("circle_score") > 0) {
                    ToastAndDialog.makePositiveToast(this.b.c, String.format("坚持审帖好运来，获得%d糗友圈积分", new Object[]{Integer.valueOf(this.a.getInt("circle_score"))})).show();
                }
                if (this.a.has("circle_rank")) {
                    int i = this.a.getInt("circle_rank");
                    if (i > 0) {
                        CircleUpgradeDialog.show(this.b.c, i);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
