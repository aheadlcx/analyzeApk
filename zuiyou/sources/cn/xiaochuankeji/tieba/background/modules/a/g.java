package cn.xiaochuankeji.tieba.background.modules.a;

import android.text.TextUtils;
import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.common.e.c;
import org.json.JSONException;
import org.json.JSONObject;

public class g implements cn.htjyb.netlib.d.a {
    d a;
    a b;

    public interface a {
        void a(boolean z, String str);
    }

    public g(a aVar) {
        this.b = aVar;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        if (!AppController.instance().deviceIDUpdated()) {
            try {
                jSONObject.put("uuid", c.a(BaseApplication.getAppContext()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.a = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/register_guest"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
        this.a.b();
    }

    public void onTaskFinish(d dVar) {
        if (dVar.c.a) {
            if (this.b != null) {
                this.b.a(true, null);
            }
            if (0 == cn.xiaochuankeji.tieba.background.a.g().c()) {
                JSONObject jSONObject = dVar.c.c;
                a(jSONObject);
                b();
                Object optString = jSONObject.optString("did_action");
                if (!TextUtils.isEmpty(optString)) {
                    AppController.instance().updateDeviceID(optString);
                }
                cn.xiaochuankeji.tieba.background.a.i().u();
                b.a().a(cn.xiaochuankeji.tieba.background.a.g().c());
                cn.xiaochuankeji.tieba.background.a.i().a(true, false);
            }
        } else if (this.b != null) {
            this.b.a(false, dVar.c.c());
        }
    }

    private void a(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("mid");
        String optString = jSONObject.optString("pw");
        String optString2 = jSONObject.optString("token");
        b i = cn.xiaochuankeji.tieba.background.a.i();
        i.a(optLong);
        i.a(optString);
        i.b(optLong);
        i.b(optString2);
    }

    private void b() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
