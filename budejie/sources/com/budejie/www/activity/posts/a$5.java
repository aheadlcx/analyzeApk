package com.budejie.www.activity.posts;

import com.budejie.www.bean.ListInfo;
import com.budejie.www.busevent.LoadMoreEvent;
import com.budejie.www.busevent.LoadMoreEvent.LoadMoreAction;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import net.tsz.afinal.a.a;

class a$5 extends a<String> {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (!this.a.isDetached()) {
            a.i(this.a).c();
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            a.i(this.a).b();
            ListInfo a = com.budejie.www.j.a.a(str);
            a.b(this.a, a.count);
            a.d(this.a, false);
            a.a(this.a, a.np);
            if (a.z(this.a) != 0) {
                a.B(this.a);
            }
            ArrayList a2 = com.budejie.www.j.a.a(this.a.getActivity(), str);
            if (a2 != null) {
                a.b(this.a, a2);
            } else {
                String str2 = "";
                if (a.q(this.a) != null) {
                    str2 = a.q(this.a).url;
                }
                MobclickAgent.onEvent(a.b(this.a), "E03_A13", "加载更多数据为空－" + str2);
            }
            if (a.z(this.a) == 0 || a.x(this.a) <= 0) {
                a.i(this.a).setPullLoadEnable(false);
                a.i(this.a).a("没有更多的数据。", null);
                return;
            }
            a.i(this.a).setPullLoadEnable(true);
            EventBus.getDefault().post(new LoadMoreEvent(LoadMoreAction.LOAD_FINISH));
        }
    }
}
