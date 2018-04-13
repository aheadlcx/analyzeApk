package com.budejie.www.g;

import android.os.Handler;
import android.util.Log;
import com.budejie.www.bean.HuodongBean;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

class b$6 implements IUiListener {
    final /* synthetic */ Handler a;
    final /* synthetic */ HuodongBean b;
    final /* synthetic */ b c;

    b$6(b bVar, Handler handler, HuodongBean huodongBean) {
        this.c = bVar;
        this.a = handler;
        this.b = huodongBean;
    }

    public void onError(UiError uiError) {
        Log.i("ListenerEx", "onError" + uiError.errorMessage);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        Log.i("ListenerEx", "onComplete");
        if (jSONObject != null && jSONObject.has(KEYS.RET)) {
            int i;
            try {
                i = jSONObject.getInt(KEYS.RET);
            } catch (JSONException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i == 0) {
                if (this.a != null) {
                    this.a.sendEmptyMessage(9);
                }
                b.a(b.a(this.c), String.valueOf(this.b.getTheme_id()), "qqFriends", "theme");
            }
        }
    }

    public void onCancel() {
        Log.i("ListenerEx", "onCancel");
    }
}
