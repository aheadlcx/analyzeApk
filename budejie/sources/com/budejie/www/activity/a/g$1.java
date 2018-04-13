package com.budejie.www.activity.a;

import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.type.TopicList;
import com.budejie.www.util.an;
import java.util.List;
import net.tsz.afinal.a.a;

class g$1 extends a<String> {
    final /* synthetic */ g a;

    g$1(g gVar) {
        this.a = gVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        g.a(this.a).a();
    }

    public void onFailure(Throwable th, int i, String str) {
        g.a(this.a).b();
    }

    public void a(String str) {
        g.a(this.a).b();
        try {
            g.a(this.a);
            if ("posts_type".equals(g.a(this.a).g)) {
                TopicList c = com.budejie.www.j.a.c(g.a(this.a), str);
                g.a(this.a, c.getInfo().np);
                if (g.b(this.a) != 0) {
                    if (g.c(this.a).a() != 5) {
                        g.d(this.a).setPullLoadEnable(true);
                    }
                    g.c(this.a).a(true);
                } else {
                    if (g.c(this.a).a() != 5) {
                        g.d(this.a).setPullLoadEnable(false);
                    }
                    g.c(this.a).a(false);
                }
                List topics = c.getTopics();
                if (c.getTop_topic() != null) {
                    ListItemObject top_topic = c.getTop_topic();
                    top_topic.setTopTopic(true);
                    topics.add(0, top_topic);
                }
                if (topics == null || topics.size() <= 0) {
                    g.a(this.a).a("posts_type", true);
                } else {
                    g.a(this.a).a("posts_type", false);
                    an.a(topics, g.a(this.a).b, g.a(this.a).a);
                }
                this.a.a = topics;
                g.e(this.a).a(topics);
                try {
                    k.a(g.a(this.a), ((ListItemObject) topics.get(0)).getVideouri());
                } catch (Exception e) {
                    e.printStackTrace();
                    k.a(g.a(this.a)).p();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            g.e(this.a).b();
        }
    }
}
