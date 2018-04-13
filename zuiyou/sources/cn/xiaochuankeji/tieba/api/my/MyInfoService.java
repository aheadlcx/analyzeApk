package cn.xiaochuankeji.tieba.api.my;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MyFavorListJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface MyInfoService {
    @o(a = "/favor/delete")
    d<EmptyJson> deleteFavor(@a JSONObject jSONObject);

    @o(a = "/favor/list")
    d<MyFavorListJson> loadFavorList(@a JSONObject jSONObject);
}
