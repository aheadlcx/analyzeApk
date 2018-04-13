package qsbk.app.utils;

import java.util.Iterator;
import org.json.JSONObject;
import qsbk.app.activity.group.SplashAdStatUtil;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Announcement;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;

class ba implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ az b;

    ba(az azVar, int i) {
        this.b = azVar;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        Announcement b;
        if (!jSONObject.has("version_id")) {
            onFailure(-1, "");
            b = this.b.a.b(jSONObject);
            if (b != null) {
                SplashAdManager.mAnnouncement = b;
            }
        } else if (this.b.a.isLoading()) {
            SplashAdGroup a = this.b.a.a(jSONObject);
            b = this.b.a.b(jSONObject);
            if (b != null) {
                SplashAdManager.mAnnouncement = b;
            }
            if (a != null) {
                if (a.version != this.a) {
                    SplashAdManager.clearTime();
                }
                this.b.a.a(a);
                Iterator it = this.b.a.b.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
                this.b.a.b(a);
            }
            this.b.a.b(2);
            this.b.a.a(jSONObject.toString());
        }
    }

    public void onFailure(int i, String str) {
        if (this.b.a.isLoading()) {
            SplashAdStatUtil.loadSelfFail();
        }
        this.b.a.b(4);
    }
}
