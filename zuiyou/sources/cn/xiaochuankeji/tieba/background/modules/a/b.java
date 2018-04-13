package cn.xiaochuankeji.tieba.background.modules.a;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.modules.a.a.a;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.HttpException;
import rx.e;

public class b implements a {
    private long a;
    private String b;
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o = false;
    private Member p;
    private long q;
    private boolean r = true;
    private boolean s = false;
    private final HashSet<a> t = new HashSet();

    public b() {
        v();
    }

    private void v() {
        String string = cn.xiaochuankeji.tieba.background.a.a().getString("AccountData", null);
        if (string != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                this.a = jSONObject.optLong("uid");
                this.b = jSONObject.optString("pw");
                this.c = jSONObject.optString("tk", null);
                this.r = jSONObject.optBoolean("guest", true);
                this.d = jSONObject.optInt("posts", 0);
                this.e = jSONObject.optInt("verify_count", 0);
                this.f = jSONObject.optInt("reviews", 0);
                this.g = jSONObject.optInt("ugcvideo_creates", 0);
                this.h = jSONObject.optInt("tale_article_creates", 0);
                this.i = jSONObject.optInt("likeds", 0);
                this.j = jSONObject.optInt("block_topics", 0);
                this.l = jSONObject.optInt("gotlikes", 0);
                this.m = jSONObject.optInt("favors", 0);
                this.n = jSONObject.optInt("favorlist_count", 0);
                this.k = jSONObject.optInt("block_users", 0);
                this.o = jSONObject.optBoolean("phoneUser", false);
                this.q = jSONObject.optLong("jail_ts", 0);
                this.p = new Member(jSONObject.optJSONObject("member_info"));
            } catch (JSONException e) {
            }
        }
    }

    public void t() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.a);
            jSONObject.put("pw", this.b);
            jSONObject.put("tk", this.c);
            jSONObject.put("guest", this.r);
            jSONObject.put("posts", this.d);
            jSONObject.put("verify_count", this.e);
            jSONObject.put("reviews", this.f);
            jSONObject.put("ugcvideo_creates", this.g);
            jSONObject.put("tale_article_creates", this.h);
            jSONObject.put("likeds", this.i);
            jSONObject.put("block_topics", this.j);
            jSONObject.put("gotlikes", this.l);
            jSONObject.put("favors", this.m);
            jSONObject.put("favorlist_count", this.n);
            jSONObject.put("block_users", this.k);
            jSONObject.put("phoneUser", this.o);
            jSONObject.put("jail_ts", this.q);
            if (this.p != null) {
                jSONObject.put("member_info", this.p.serializeTo());
            }
        } catch (JSONException e) {
        }
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putString("AccountData", jSONObject.toString());
        edit.apply();
    }

    public void a(long j) {
        this.a = j;
        cn.xiaochuankeji.tieba.background.a.o().b();
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((a) it.next()).onTokenChanged();
        }
    }

    public void a(a aVar) {
        this.t.add(aVar);
    }

    public void b(a aVar) {
        this.t.remove(aVar);
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public long c() {
        return this.a;
    }

    public int g() {
        return this.d;
    }

    public int h() {
        return this.e;
    }

    public int i() {
        return this.f;
    }

    public int j() {
        return this.g;
    }

    public int k() {
        return this.h;
    }

    public int l() {
        return this.i;
    }

    public int m() {
        return this.l;
    }

    public int n() {
        return this.j;
    }

    public int o() {
        return this.n;
    }

    public Member q() {
        return this.p;
    }

    public int p() {
        return this.k;
    }

    public boolean r() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public boolean a(String str, String str2, String str3) {
        return str.compareTo(d.c(new StringBuilder().append(str3).append(str2).toString())) == 0;
    }

    public void a(com.alibaba.fastjson.JSONObject jSONObject) {
        this.d = jSONObject.getIntValue("posts");
        this.e = jSONObject.getIntValue("verify_count");
        this.f = jSONObject.getIntValue("reviews");
        this.g = jSONObject.getIntValue("ugcvideo_creates");
        this.h = jSONObject.getIntValue("tale_article_creates");
        this.m = jSONObject.getIntValue("favors");
        this.n = jSONObject.getIntValue("favorlist_count");
        this.i = jSONObject.getIntValue("likeds");
        this.j = jSONObject.getIntValue("block_topics");
        this.k = jSONObject.getIntValue("block_users");
        this.l = jSONObject.getIntValue("gotlikes");
        long longValue = jSONObject.getLongValue("jail_ts");
        if (longValue > this.q) {
            this.q = longValue;
            this.s = true;
        } else {
            this.s = false;
        }
        try {
            a(new JSONObject(jSONObject.toJSONString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("member_info");
            if (optJSONObject == null) {
                return;
            }
            if (optJSONObject.optInt("id", 0) <= 0) {
                this.r = true;
                return;
            }
            if (optJSONObject.optInt("isreg", 0) == 0) {
                this.r = true;
            }
            this.p = new Member(optJSONObject);
        }
    }

    public void b(long j) {
        this.p = new Member(j);
        this.p.setName("游客");
        this.p.setIsRegistered(0);
        this.p.setSign("该用户尚未注册");
    }

    public boolean d() {
        return this.r;
    }

    public void a(boolean z, boolean z2) {
        com.izuiyou.a.a.b.d("set isGuest:" + z + ", _isguester:" + this.r);
        if (this.r != z) {
            this.r = z;
        }
    }

    public void e() {
        w();
        cn.xiaochuankeji.tieba.background.a.o().c();
        cn.xiaochuankeji.tieba.background.a.a().edit().putInt("kLoginBySMS", -1).putInt("kCertifyCounter", -1).putBoolean("key_recommend_notification", true).putBoolean("key_comment_notification", true).putBoolean("kChatMsgNotification", true).apply();
        this.a = 0;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.m = 0;
        this.i = 0;
        this.j = 0;
        this.r = true;
        this.p.clear();
        t();
        u();
    }

    private void w() {
        File file = new File(cn.xiaochuankeji.tieba.background.a.e().r() + "ugcvideo_attri_content.dat");
        if (file.exists()) {
            file.delete();
        }
    }

    public void u() {
        v();
        cn.xiaochuankeji.tieba.background.a.n().clear();
    }

    public void f() {
        if (d() || this.a == 0) {
            cn.xiaochuankeji.tieba.background.a.h().a(null);
        } else {
            new cn.xiaochuankeji.tieba.api.account.a().b().a(new e<com.alibaba.fastjson.JSONObject>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((com.alibaba.fastjson.JSONObject) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    cn.xiaochuankeji.tieba.analyse.a.a(th);
                    th.printStackTrace();
                    if (((th instanceof HttpException) && ((HttpException) th).code() == 401) || ((th instanceof ClientErrorException) && ((ClientErrorException) th).errCode() == -11)) {
                        cn.xiaochuankeji.tieba.background.a.i().e();
                        cn.xiaochuankeji.tieba.background.a.i().a(true, false);
                        cn.xiaochuankeji.tieba.background.a.h().a(null);
                    }
                }

                public void a(com.alibaba.fastjson.JSONObject jSONObject) {
                    Object string = jSONObject.getString("did_action");
                    if (!TextUtils.isEmpty(string)) {
                        AppController.instance().updateDeviceID(string);
                    }
                    String string2 = jSONObject.getString("token");
                    b i = cn.xiaochuankeji.tieba.background.a.i();
                    i.a(new JSONObject(jSONObject));
                    i.b(string2);
                }
            });
        }
    }

    public boolean s() {
        return this.s;
    }
}
