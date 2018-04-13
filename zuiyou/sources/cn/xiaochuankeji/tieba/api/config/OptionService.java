package cn.xiaochuankeji.tieba.api.config;

import cn.xiaochuankeji.tieba.json.ABTestingJson;
import com.alibaba.fastjson.JSONObject;
import okhttp3.ab;
import retrofit2.a.a;
import retrofit2.a.f;
import retrofit2.a.o;
import retrofit2.a.x;
import rx.d;

public interface OptionService {
    @f
    d<ab> downloadPicFromNet(@x String str);

    @o(a = "/share/getabtesting")
    d<ABTestingJson> getAbTestingResult(@a JSONObject jSONObject);
}
