package cn.v6.sixrooms.presenter;

import android.text.TextUtils;
import cn.v6.sixrooms.engine.AddFollowEngine;
import cn.v6.sixrooms.engine.CancelFollowEngine;
import cn.v6.sixrooms.engine.IsFollowEngine;
import cn.v6.sixrooms.presenter.runnable.FollowPresenterable;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.view.interfaces.FollowViewable;
import java.util.concurrent.CopyOnWriteArrayList;

public class FollowPresenter implements FollowPresenterable {
    private static volatile FollowPresenter d;
    private AddFollowEngine a;
    private CancelFollowEngine b;
    private IsFollowEngine c;
    private boolean e = false;
    private boolean f = false;
    private CopyOnWriteArrayList<FollowViewable> g = new CopyOnWriteArrayList();

    private FollowPresenter() {
        if (this.a == null) {
            this.a = new AddFollowEngine(new c(this));
        }
        if (this.b == null) {
            this.b = new CancelFollowEngine(new d(this));
        }
        this.c = new IsFollowEngine(new e(this));
    }

    public static FollowPresenter getInstance() {
        if (d == null) {
            synchronized (FollowPresenter.class) {
                if (d == null) {
                    d = new FollowPresenter();
                }
            }
        }
        return d;
    }

    public void onDestroy() {
        this.e = false;
        this.g.clear();
        d = null;
    }

    public void register(FollowViewable followViewable) {
        if (!this.g.contains(followViewable)) {
            this.g.add(followViewable);
        }
    }

    public void unregister(FollowViewable followViewable) {
        this.g.remove(followViewable);
    }

    public void getFollowStatus(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            this.c.getIsFollowLive(str2, str3);
        } else {
            this.c.getIsFollowLiveNew(str, str3);
        }
    }

    public boolean getLoacaFollowStatus() {
        return this.e;
    }

    public void clearFollowStatus() {
        this.e = false;
    }

    public void followOrCancel(String str, String str2, String str3) {
        if (this.e) {
            this.b.cancelFollow(str, str2, str3);
            return;
        }
        this.a.addFollow(str, str2, str3);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getFromRoomPage(), StatisticValue.getInstance().getFromRoomModule());
    }

    public boolean getIsChange() {
        return this.f != this.e;
    }
}
