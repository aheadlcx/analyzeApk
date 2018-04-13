package cn.xiaochuankeji.tieba.api.tag;

import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTagList;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private TagService a = ((TagService) c.a().a(TagService.class));

    public d<NavigatorTagList> a() {
        return this.a.getNavTags(new JSONObject());
    }

    public d<NavigatorTagList> a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("list", jSONArray);
        return this.a.saveNavTags(jSONObject);
    }
}
