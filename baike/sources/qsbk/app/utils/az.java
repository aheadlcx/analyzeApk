package qsbk.app.utils;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Announcement;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;

class az implements Runnable {
    final /* synthetic */ SplashAdManager a;

    az(SplashAdManager splashAdManager) {
        this.a = splashAdManager;
    }

    public void run() {
        String a = this.a.a();
        SplashAdGroup splashAdGroup = null;
        if (a != null) {
            try {
                splashAdGroup = this.a.a(new JSONObject(a));
                Announcement b = this.a.b(new JSONObject());
                if (b != null) {
                    SplashAdManager.mAnnouncement = b;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (splashAdGroup != null) {
            this.a.a(splashAdGroup);
            Iterator it = this.a.b.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.a.b(splashAdGroup);
        }
        new SimpleHttpTask(String.format(Constants.SPLASH_AD_URL, new Object[]{Integer.valueOf(0)}), new ba(this, splashAdGroup == null ? 0 : splashAdGroup.version)).syncRun();
    }
}
