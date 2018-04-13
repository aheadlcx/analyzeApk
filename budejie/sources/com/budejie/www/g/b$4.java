package com.budejie.www.g;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListItemObject;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

class b$4 implements IUiListener {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ Handler b;
    final /* synthetic */ b c;

    b$4(b bVar, ListItemObject listItemObject, Handler handler) {
        this.c = bVar;
        this.a = listItemObject;
        this.b = handler;
    }

    public void onError(UiError uiError) {
        Log.e("wuzhenlin", "onError" + uiError.errorMessage);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        Log.e("wuzhenlin", "onComplete");
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
            if (this.a.getIfPP()) {
                b.a(b.a(this.c), this.a.getWid(), "qqFriends", StatisticCodeTable.PROFILE);
                return;
            }
            if (this.b != null) {
                this.b.sendEmptyMessage(9);
            }
            if (!((BudejieApplication) b.a(this.c).getApplication()).g().ae.a().booleanValue() || this.a.isForwardNoCollect()) {
            }
            if (TextUtils.isEmpty(this.a.getWid())) {
                b.a(b.a(this.c), this.a.getWid(), "qqFriends", "app");
            } else {
                b.a(b.a(this.c), this.a.getWid(), "qqFriends", "topic");
            }
        }
    }

    public void onCancel() {
        Log.e("wuzhenlin", "onCancel");
    }
}
