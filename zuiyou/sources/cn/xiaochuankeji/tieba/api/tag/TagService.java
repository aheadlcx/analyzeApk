package cn.xiaochuankeji.tieba.api.tag;

import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTagList;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface TagService {
    @o(a = "/navigator/list")
    d<NavigatorTagList> getNavTags(@a JSONObject jSONObject);

    @o(a = "/navigator/save")
    d<NavigatorTagList> saveNavTags(@a JSONObject jSONObject);
}
