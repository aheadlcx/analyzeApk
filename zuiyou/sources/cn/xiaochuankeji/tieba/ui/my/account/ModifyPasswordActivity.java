package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.background.modules.a.b;
import cn.xiaochuankeji.tieba.ui.my.a;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSONObject;
import rx.e;

public class ModifyPasswordActivity extends a {
    cn.xiaochuankeji.tieba.api.account.a l = new cn.xiaochuankeji.tieba.api.account.a();

    public static void a(Activity activity) {
        activity.startActivity(new Intent(activity, ModifyPasswordActivity.class));
    }

    protected boolean a(Bundle bundle) {
        this.d = "修改密码";
        this.f = "完成";
        this.g = "请输入新密码";
        return true;
    }

    protected void d_() {
        this.j.setVisibility(8);
        this.i.setInputType(145);
    }

    protected void a(final String str) {
        if (d.a(str)) {
            g.a((Activity) this);
            this.l.b(str).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<JSONObject>(this) {
                final /* synthetic */ ModifyPasswordActivity b;

                public /* synthetic */ void onNext(Object obj) {
                    a((JSONObject) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    g.c(this.b);
                    cn.xiaochuankeji.tieba.background.utils.g.a(th == null ? "密码修改失败" : th.getMessage());
                }

                public void a(JSONObject jSONObject) {
                    long longValue = jSONObject.getLong("mid").longValue();
                    String string = jSONObject.getString("pw");
                    if (TextUtils.isEmpty(string)) {
                        string = d.e(str);
                    }
                    b i = cn.xiaochuankeji.tieba.background.a.i();
                    i.a(longValue);
                    i.a(string);
                    cn.xiaochuankeji.tieba.background.a.i().t();
                    cn.xiaochuankeji.tieba.background.utils.g.a("密码修改成功");
                    g.c(this.b);
                    this.b.setResult(-1);
                    this.b.finish();
                }
            });
            return;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a("密码格式错误");
    }
}
