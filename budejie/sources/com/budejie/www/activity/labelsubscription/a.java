package com.budejie.www.activity.labelsubscription;

import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.busevent.LoginAction;
import com.budejie.www.c.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.util.z;
import de.greenrobot.event.EventBus;
import java.util.List;

public class a {
    public void a() {
        BudejieApplication.a.a(RequstMethod.GET, new h("http://d.api.budejie.com/tag/subscribe").b().a().toString(), new j(), new net.tsz.afinal.a.a<Object>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onStart() {
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
            }

            public void onSuccess(final Object obj) {
                if (obj != null) {
                    new Thread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            List g = z.g(obj.toString());
                            if (g != null && g.size() > 0) {
                                for (int size = g.size() - 1; size >= 0; size--) {
                                    if ("r".equals(((RecommendSubscription) g.get(size)).getType())) {
                                        g.remove(size);
                                    }
                                }
                                new b(BudejieApplication.g).a(g);
                                EventBus.getDefault().post(LoginAction.LOGIN);
                            }
                        }
                    }, "SynchronizationLabel-Thread").start();
                }
            }
        });
    }
}
