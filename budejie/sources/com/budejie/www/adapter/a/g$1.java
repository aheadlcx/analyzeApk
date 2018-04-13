package com.budejie.www.adapter.a;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.NotificationSettingItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class g$1 implements OnClickListener {
    NotificationSettingItem a = ((NotificationSettingItem) this.b.b.getTag());
    final /* synthetic */ g$a b;
    final /* synthetic */ String c;
    final /* synthetic */ g d;

    g$1(g gVar, g$a g_a, String str) {
        this.d = gVar;
        this.b = g_a;
        this.c = str;
    }

    public void onClick(View view) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.d.a(this.c, g.a(this.d).getInt(this.a.getDb_key(), 0) == 0 ? "1" : "0"), new a<String>(this) {
            final /* synthetic */ g$1 a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                int i = 1;
                super.onSuccess(str);
                Log.i("NotificationSettingListAdapter", str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) && jSONObject.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) == 0) {
                        this.a.b.b.setBackgroundResource(g.a(this.a.d).getInt(this.a.a.getDb_key(), 0) == 1 ? R.drawable.switch_on : R.drawable.switch_off);
                        g.a(this.a.d, g.a(this.a.d).edit());
                        Editor b = g.b(this.a.d);
                        String db_key = this.a.a.getDb_key();
                        if (g.a(this.a.d).getInt(this.a.a.getDb_key(), 0) != 0) {
                            i = 0;
                        }
                        b.putInt(db_key, i);
                        g.b(this.a.d).commit();
                    }
                    String string = g.c(this.a.d).getString(R.string.operate_fail);
                    if (jSONObject.has("msg") && !TextUtils.isEmpty(jSONObject.getString("msg"))) {
                        string = jSONObject.getString("msg");
                    }
                    g.a(this.a.d, an.a(g.c(this.a.d), string, -1));
                    g.d(this.a.d).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    g.a(this.a.d, an.a(g.c(this.a.d), g.c(this.a.d).getString(R.string.operate_fail), -1));
                    g.d(this.a.d).show();
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
                g.a(this.a.d, an.a(g.c(this.a.d), g.c(this.a.d).getString(R.string.operate_fail), -1));
                g.d(this.a.d).show();
            }
        });
    }
}
