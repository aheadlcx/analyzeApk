package cn.xiaochuankeji.tieba.api.log;

import com.alibaba.fastjson.JSONObject;
import okhttp3.z;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface LogService {
    @o(a = "/stat/action")
    d<Void> sendActionLog(@a JSONObject jSONObject);

    @o(a = "/diagnosis/app_report")
    d<Void> sendErrorDiagnosis(@a z zVar);

    @o(a = "/diagnosis/image")
    d<Void> sendPicHttpStatReporter(@a JSONObject jSONObject);

    @o(a = "/stat/duration")
    d<Void> sendPostDurStatReporter(@a JSONObject jSONObject);

    @o(a = "/diagnosis/video")
    d<Void> sendVideoDiagnosis(@a JSONObject jSONObject);
}
