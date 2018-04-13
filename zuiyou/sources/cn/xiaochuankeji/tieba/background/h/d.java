package cn.xiaochuankeji.tieba.background.h;

import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.push.b.e;
import com.izuiyou.a.a.b;
import org.greenrobot.eventbus.c;

public class d {
    private int a = -1;
    private int b = -1;
    private int c = -1;
    private String d = null;
    private String e = null;
    private String f = null;

    private static class a {
        private static final d a = new d();
    }

    public static d a() {
        return a.a;
    }

    public void b() {
        cn.xiaochuankeji.tieba.background.a.c().edit().putInt("key_last_notify_count", cn.xiaochuankeji.tieba.background.a.c().getInt("key_last_notify_count", 0) + 1).apply();
    }

    public void c() {
        cn.xiaochuankeji.tieba.background.a.c().edit().putInt("key_last_notify_count", 0).apply();
    }

    public void d() {
        b.c("reload crumb count");
        this.a = e();
        this.b = n();
        this.c = this.b + this.a;
        c.a().d(new cn.xiaochuankeji.tieba.push.c.c());
    }

    private int n() {
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            return 0;
        }
        return e.a();
    }

    public int e() {
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            return 0;
        }
        return cn.xiaochuankeji.tieba.background.a.c().getInt("key_last_notify_count", 0);
    }

    private void o() {
        if (this.a == -1 || this.b == -1 || this.c == -1) {
            this.a = e();
            this.b = n();
            this.c = this.b + this.a;
        }
    }

    public int f() {
        o();
        return this.b;
    }

    public int g() {
        o();
        return this.a;
    }

    public int h() {
        o();
        return this.c;
    }

    public static void i() {
        cn.xiaochuankeji.tieba.background.a.c().edit().remove("key_message_count").remove("key_chat_count").apply();
    }

    public String j() {
        return this.d;
    }

    public String k() {
        return this.e;
    }

    public String l() {
        return this.f;
    }

    public void a(int i) {
        a(i, null);
    }

    public void a(int i, Object obj) {
        m();
        switch (i) {
            case 1:
                this.d = "icon";
                return;
            case 2:
                this.d = "push";
                if (obj instanceof Post) {
                    this.e = "post";
                    this.f = String.valueOf(((Post) obj)._ID);
                    return;
                } else if (obj instanceof Topic) {
                    this.e = "topic";
                    this.f = String.valueOf(((Topic) obj)._topicID);
                    return;
                } else {
                    return;
                }
            case 3:
                this.d = "msg";
                return;
            case 4:
                this.d = "chat";
                return;
            case 5:
                this.d = "share";
                return;
            default:
                return;
        }
    }

    public void m() {
        this.d = null;
        this.e = null;
        this.f = null;
    }
}
