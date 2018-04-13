package cn.xiaochuankeji.tieba.ui.auth.a;

import android.text.TextUtils;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.modules.a.i;
import com.izuiyou.a.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements cn.htjyb.netlib.d.a {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private d f;
    private i g;
    private String h;

    public a(int i, String str, String str2, String str3, String str4, i iVar) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = iVar;
    }

    public a a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", cn.xiaochuankeji.tieba.background.a.g().c());
            if (1 == this.a || 5 == this.a) {
                jSONObject.put("opentype", 1);
            } else if (2 == this.a || 4 == this.a) {
                jSONObject.put("opentype", 2);
            } else if (3 == this.a) {
                jSONObject.put("opentype", 3);
            }
            jSONObject.put("openid", this.c);
            jSONObject.put("openkey", this.b);
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("unionid", this.h);
            }
            if ((this.a == 1 || this.a == 5) && !TextUtils.isEmpty(this.e)) {
                jSONObject.put("userdata", this.e);
            }
            jSONObject.put("access_token", this.d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        this.f = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/s/user/account/openlogin"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
        this.f.b();
        return this;
    }

    public void a(String str) {
        this.h = str;
    }

    public void b() {
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
    }

    public void onTaskFinish(d dVar) {
        b.d("login result:" + dVar.c.a);
        int i = this.a;
        if (dVar.c.a) {
            JSONObject jSONObject = dVar.c.c;
            if (TextUtils.isEmpty(jSONObject.optString("token"))) {
                b.d("get token empty");
                this.g.a(i, false, 0, "没有获取token");
                return;
            } else if (a(jSONObject)) {
                b.d("save account info and reload");
                b(jSONObject);
                boolean z = dVar.c.c.optInt("register", 0) == 1;
                cn.xiaochuankeji.tieba.background.a.i().a(false, z);
                c();
                cn.xiaochuankeji.tieba.background.a.i().u();
                if (z) {
                    this.g.a(i);
                    return;
                } else {
                    this.g.a(i, true, 0, null);
                    return;
                }
            } else {
                this.g.a(i, false, 0, "解析数据失败");
                return;
            }
        }
        this.g.a(i, false, dVar.c.b, dVar.c.c());
    }

    private boolean a(JSONObject jSONObject) {
        if (jSONObject.optLong("mid") > 0) {
            return true;
        }
        return false;
    }

    private void b(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
        long optLong = jSONObject.optLong("mid");
        String optString = jSONObject.optString("passwd");
        String optString2 = jSONObject.optString("token");
        i.a(optLong);
        i.a(false);
        i.a(jSONObject);
        i.a(optString);
        i.b(optString2);
    }

    private void c() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
