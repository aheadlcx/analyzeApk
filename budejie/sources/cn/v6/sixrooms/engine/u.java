package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.game.GameCenterBean;
import org.json.JSONException;
import org.json.JSONObject;

final class u extends VLAsyncHandler<String> {
    final /* synthetic */ GameCenterBean a;
    final /* synthetic */ GameParamsEngine b;

    u(GameParamsEngine gameParamsEngine, GameCenterBean gameCenterBean) {
        this.b = gameParamsEngine;
        this.a = gameCenterBean;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                Object string = jSONObject.getString("flag");
                Object string2 = jSONObject.getString("content");
                if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string) && string.equals("001")) {
                    this.b.b.onGetParams(this.a, "content", string2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.b.b.onGetFail("数据解析异常");
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.b != null) {
            this.b.b.onGetFail("网络超时");
        }
    }
}
