package com.budejie.www.g;

import android.os.Handler;
import android.text.TextUtils;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.htmlpage.c;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

class b$1 implements IUiListener {
    final /* synthetic */ Object a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;
    final /* synthetic */ b d;

    b$1(b bVar, Object obj, String str, Handler handler) {
        this.d = bVar;
        this.a = obj;
        this.b = str;
        this.c = handler;
    }

    public void onCancel() {
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null && jSONObject.has(KEYS.RET)) {
            int i;
            try {
                i = jSONObject.getInt(KEYS.RET);
            } catch (JSONException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i != 0) {
                return;
            }
            if ((this.a instanceof ListItemObject) && ((ListItemObject) this.a).getIfPP()) {
                b.a(b.a(this.d), this.b, Constants.SOURCE_QZONE, StatisticCodeTable.PROFILE);
            } else if (this.a instanceof HuodongBean) {
                b.a(b.a(this.d), this.b, Constants.SOURCE_QZONE, "huodong");
            } else {
                if (this.c != null) {
                    this.c.sendEmptyMessage(9);
                }
                if (!((BudejieApplication) b.a(this.d).getApplication()).g().ae.a().booleanValue() || ((ListItemObject) this.a).isForwardNoCollect()) {
                }
                if (TextUtils.isEmpty(this.b)) {
                    b.a(b.a(this.d), this.b, Constants.SOURCE_QZONE, "app");
                } else {
                    b.a(b.a(this.d), this.b, Constants.SOURCE_QZONE, "topic");
                }
                this.d.a(c.SHARE_PLATFORM_QZONE);
            }
        }
    }

    public void onError(UiError uiError) {
    }
}
