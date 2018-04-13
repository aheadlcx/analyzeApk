package cn.xiaochuankeji.tieba.api.subject;

import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface SubjectService {
    @o(a = "/subject/base")
    d<SubjectJson> get(@a JSONObject jSONObject);
}
