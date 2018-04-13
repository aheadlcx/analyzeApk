package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpTask;
import qsbk.app.im.datastore.GroupStore;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.LogUtil;

public class JoinedGroupInfoService extends Service {
    List<GroupInfo> a = new ArrayList();
    private int b;
    private HttpTask c;
    private int d;
    private boolean e;
    private GroupStore f;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.d("service onStart");
        if (QsbkApp.currentUser != null) {
            this.f = GroupStore.getInstance(QsbkApp.currentUser.userId);
            this.b = 1;
            a();
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void a() {
        String format = String.format(Constants.URL_MY_GROUP_LIST, new Object[]{Integer.valueOf(this.b)});
        LogUtil.d("joinUrl======" + format);
        this.c = new HttpTask(format, format, new c(this));
        this.c.execute(new Void[0]);
    }

    private void b() {
        if (this.e) {
            this.b++;
            LogUtil.d("joined==========" + this.b);
            a();
            return;
        }
        this.f.deleteJoinedGroup();
        this.b = 1;
        if (this.a.size() > 0) {
            this.f.insert(this.a);
        }
        stopSelf();
    }
}
