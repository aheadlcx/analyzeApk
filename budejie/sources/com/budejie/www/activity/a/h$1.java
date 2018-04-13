package com.budejie.www.activity.a;

import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.type.TopicList;
import com.budejie.www.util.an;
import java.util.List;
import net.tsz.afinal.a.a;

class h$1 extends a<String> {
    final /* synthetic */ h a;

    h$1(h hVar) {
        this.a = hVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        h.a(this.a).a();
    }

    public void onFailure(Throwable th, int i, String str) {
        h.a(this.a).b();
    }

    public void a(String str) {
        h.a(this.a).b();
        try {
            h.a(this.a);
            if ("share_type".equals(h.a(this.a).g)) {
                TopicList c = com.budejie.www.j.a.c(h.a(this.a), str);
                h.a(this.a, c.getInfo().np);
                if (h.b(this.a) != 0) {
                    if (h.c(this.a).a() != 5) {
                        h.d(this.a).setPullLoadEnable(true);
                    }
                    h.c(this.a).a(true);
                } else {
                    if (h.c(this.a).a() != 5) {
                        h.d(this.a).setPullLoadEnable(false);
                    }
                    h.c(this.a).a(false);
                }
                List topics = c.getTopics();
                if (topics == null || topics.size() <= 0) {
                    h.a(this.a).a("share_type", true);
                } else {
                    h.a(this.a).a("share_type", false);
                    an.a(topics, h.a(this.a).b, h.a(this.a).a);
                }
                h.e(this.a).a(topics);
                try {
                    k.a(h.a(this.a), ((ListItemObject) topics.get(0)).getVideouri());
                } catch (Exception e) {
                    e.printStackTrace();
                    k.a(h.a(this.a)).p();
                }
                this.a.a = topics;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            h.e(this.a).b();
        }
    }
}
