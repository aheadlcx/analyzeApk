package cn.v6.sixrooms.presenter;

import android.os.Handler;
import android.text.TextUtils;
import cn.v6.sixrooms.bean.RedBean;
import cn.v6.sixrooms.engine.RedInfoEngine;
import cn.v6.sixrooms.view.interfaces.RedViewable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RedPresenter {
    private static RedPresenter a;
    private static final String e = RedPresenter.class.getSimpleName();
    private CopyOnWriteArrayList<RedViewable> b = new CopyOnWriteArrayList();
    private RedInfoEngine c;
    private ScheduledExecutorService d;
    private String f;
    private String g;
    private RedBean h;
    private Handler i = new j(this);

    public static RedPresenter getInstance() {
        if (a == null) {
            synchronized (RedPresenter.class) {
                if (a == null) {
                    a = new RedPresenter();
                }
            }
        }
        return a;
    }

    public void updateLocalRed(int i) {
        if (this.h != null) {
            this.h.setCurrentRed(String.valueOf(i));
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((RedViewable) it.next()).updateRed(this.h);
            }
        }
    }

    public void register(RedViewable redViewable, String str, String str2) {
        if (!this.b.contains(redViewable)) {
            this.b.add(redViewable);
        }
        if (this.h != null) {
            redViewable.updateRed(this.h);
        }
        this.f = str;
        this.g = str2;
        if (this.c == null) {
            a();
        }
        this.c.getRedInfo(str, str2);
    }

    public void unregister(RedViewable redViewable) {
        this.b.remove(redViewable);
        this.f = "";
        this.g = "";
    }

    public void onDestroy() {
        if (!(this.d == null || this.d.isShutdown())) {
            this.d.shutdownNow();
        }
        this.c = null;
        a = null;
    }

    private RedPresenter() {
        if (this.c == null) {
            a();
        }
        if (this.d == null) {
            this.d = Executors.newSingleThreadScheduledExecutor();
            this.d.scheduleAtFixedRate(new k(this), 0, 300000, TimeUnit.MILLISECONDS);
        }
    }

    private void a() {
        this.c = new RedInfoEngine(new l(this));
    }

    static /* synthetic */ void a(RedPresenter redPresenter) {
        if (!TextUtils.isEmpty(redPresenter.f) && !TextUtils.isEmpty(redPresenter.g) && redPresenter.c != null) {
            redPresenter.c.getRedInfo(redPresenter.f, redPresenter.g);
        }
    }
}
