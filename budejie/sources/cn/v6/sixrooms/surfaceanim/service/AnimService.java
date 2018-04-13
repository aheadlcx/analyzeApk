package cn.v6.sixrooms.surfaceanim.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import cn.v6.sixrooms.surfaceanim.AnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.AnimViewControl;

public class AnimService extends Service {
    public static final String BUNDLE_ANIM_FACTORY_KEY = "anim_factory";
    public static final int MSG_WHAT_ADD_SCENE = 1;
    private Handler a = new a(this);
    private Messenger b = new Messenger(this.a);
    private AnimViewControl c;

    public IBinder onBind(Intent intent) {
        try {
            this.c = new AnimViewControl(this, (AnimSceneFactory) Class.forName(intent.getStringExtra(BUNDLE_ANIM_FACTORY_KEY)).newInstance(), 0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        return this.b.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        if (this.c != null) {
            this.c.release();
        }
        return super.onUnbind(intent);
    }
}
