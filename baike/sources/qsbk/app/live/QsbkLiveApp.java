package qsbk.app.live;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import qsbk.app.core.utils.AppUtils;

public class QsbkLiveApp extends Application {
    private static QsbkLiveApp a;

    public void onCreate() {
        super.onCreate();
        a = this;
        Fresco.initialize(this);
        AppUtils.init(this, 2, "go!live!", "dev");
        AppUtils.getInstance().setUserInfoProvider(new c(this));
        AppUtils.getInstance().setImageProvider(new d(this));
    }

    public static synchronized QsbkLiveApp getInstance() {
        QsbkLiveApp qsbkLiveApp;
        synchronized (QsbkLiveApp.class) {
            qsbkLiveApp = a;
        }
        return qsbkLiveApp;
    }

    public static Context getAppContext() {
        return a.getApplicationContext();
    }
}
