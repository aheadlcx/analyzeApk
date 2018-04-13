package com.budejie.www.activity.a;

import com.budejie.www.type.TopicList;
import com.budejie.www.util.an;
import java.util.Collection;
import net.tsz.afinal.a.a;

class g$2 extends a<String> {
    final /* synthetic */ g a;

    g$2(g gVar) {
        this.a = gVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        g.d(this.a).c();
    }

    public void a(String str) {
        try {
            g.d(this.a).c();
            TopicList c = com.budejie.www.j.a.c(g.a(this.a), str);
            g.a(this.a, c.getInfo().np);
            if (g.b(this.a) != 0) {
                if (g.c(this.a).b() != 6) {
                    g.d(this.a).setPullLoadEnable(true);
                }
                g.c(this.a).a(true);
            } else {
                if (g.c(this.a).b() != 6) {
                    g.d(this.a).setPullLoadEnable(false);
                }
                g.c(this.a).a(false);
            }
            Collection topics = c.getTopics();
            if (topics == null || topics.size() <= 0) {
                an.a(g.a(this.a), "已经没有更多帖子了", -1).show();
                return;
            }
            an.a(topics, g.a(this.a).b, g.a(this.a).a);
            g.e(this.a).b(topics);
            this.a.a.addAll(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
