package cn.xiaochuankeji.tieba.api.log;

import com.alibaba.fastjson.JSONObject;
import okhttp3.v$b;
import okhttp3.z;
import retrofit2.a.a;
import retrofit2.a.l;
import retrofit2.a.o;
import retrofit2.a.q;
import rx.d;

public interface LogUploadService {
    @o(a = "/applog/dev_reset_push")
    d<String> reportResetStatus(@a JSONObject jSONObject);

    @o(a = "/applog/store_client_data")
    @l
    d<String> uploadClientData(@q v$b v_b, @q(a = "json") z zVar);

    @o(a = "/applog/store_client_env")
    d<String> uploadClientEnv(@a JSONObject jSONObject);

    @o(a = "/applog/store_client_log")
    @l
    d<String> uploadClientLog(@q v$b v_b, @q(a = "json") z zVar);

    @o(a = "/applog/store_client_log")
    d<String> uploadClientLog2(@a z zVar);
}
