package cn.xiaochuankeji.tieba.api.user;

import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private UserService a = ((UserService) c.a().a(UserService.class));

    public d<JSONObject> a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(str, Integer.valueOf(i));
        return this.a.sendSettingPush(jSONObject);
    }

    public d<SettingJson> a() {
        return this.a.getSettingPush(new JSONObject());
    }
}
