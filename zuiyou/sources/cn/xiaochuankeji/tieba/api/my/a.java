package cn.xiaochuankeji.tieba.api.my;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MyFavorListJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private MyInfoService a = ((MyInfoService) c.a().a(MyInfoService.class));

    public d<MyFavorListJson> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("t", Long.valueOf(j));
        jSONObject.put("mid", Long.valueOf(j2));
        return this.a.loadFavorList(jSONObject);
    }

    public d<EmptyJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        return this.a.deleteFavor(jSONObject);
    }
}
