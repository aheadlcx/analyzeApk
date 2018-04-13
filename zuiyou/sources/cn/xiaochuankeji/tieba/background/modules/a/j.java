package cn.xiaochuankeji.tieba.background.modules.a;

import android.text.TextUtils;
import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.g;
import cn.xiaochuan.base.BaseApplication;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class j implements cn.htjyb.netlib.d.a {
    private d a;
    private a b;
    private String c;

    public interface a {
        void a(boolean z, Object obj);
    }

    public j(String str, a aVar) {
        this.c = str;
        this.b = aVar;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/set_avatar");
        b d = cn.xiaochuankeji.tieba.background.a.d();
        Collection arrayList = new ArrayList();
        Object obj = this.c;
        try {
            if (this.c != null && this.c.contains("file:///android_asset/")) {
                String substring = this.c.substring("file:///android_asset/".length());
                obj = cn.xiaochuankeji.tieba.background.a.e().B() + substring;
                cn.htjyb.c.a.b.a(BaseApplication.getAppContext().getAssets().open(substring), new File(obj));
            }
            if (!TextUtils.isEmpty(obj)) {
                arrayList.add(new b.b(new File(obj), "avatar"));
                this.a = new g(b, d, arrayList, jSONObject, this);
                this.a.b();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void onTaskFinish(d dVar) {
        if (dVar.c.a) {
            cn.xiaochuankeji.tieba.background.a.i().a(dVar.c.c);
            b();
            cn.htjyb.b.a avatarPicture = cn.xiaochuankeji.tieba.background.a.g().q().getAvatarPicture();
            cn.htjyb.c.a.b.a(new File(this.c), new File(avatarPicture.cachePath()));
            if (this.b != null) {
                this.b.a(true, avatarPicture);
            }
        } else if (this.b != null) {
            this.b.a(false, dVar.c.c());
        }
    }

    private void b() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
