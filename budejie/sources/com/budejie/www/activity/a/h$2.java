package com.budejie.www.activity.a;

import com.budejie.www.type.TopicList;
import com.budejie.www.util.an;
import java.util.Collection;
import net.tsz.afinal.a.a;

class h$2 extends a<String> {
    final /* synthetic */ h a;

    h$2(h hVar) {
        this.a = hVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        h.d(this.a).c();
    }

    public void a(String str) {
        try {
            h.d(this.a).c();
            TopicList c = com.budejie.www.j.a.c(h.a(this.a), str);
            h.a(this.a, c.getInfo().np);
            if (h.b(this.a) != 0) {
                if (h.c(this.a).b() != 6) {
                    h.d(this.a).setPullLoadEnable(true);
                }
                h.c(this.a).a(true);
            } else {
                if (h.c(this.a).b() != 6) {
                    h.d(this.a).setPullLoadEnable(false);
                }
                h.c(this.a).a(false);
            }
            Collection topics = c.getTopics();
            if (topics == null || topics.size() <= 0) {
                an.a(h.a(this.a), "已经没有更多帖子了", -1).show();
                return;
            }
            an.a(topics, h.a(this.a).b, h.a(this.a).a);
            h.e(this.a).b(topics);
            this.a.a.addAll(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
