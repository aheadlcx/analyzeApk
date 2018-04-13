package cn.xiaochuankeji.tieba.api.log;

import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.v$b;
import okhttp3.z;
import rx.d;

public class b {
    public d<String> a(v$b v_b, String str) {
        JSONObject parseObject = JSON.parseObject(a.a());
        parseObject.put("opid", str);
        parseObject.put("is_gzipped", Boolean.valueOf(true));
        parseObject.put("is_crypto", Boolean.valueOf(true));
        return ((LogUploadService) c.b(LogUploadService.class)).uploadClientLog(v_b, z.create(null, parseObject.toJSONString()));
    }

    public static d<String> b(v$b v_b, String str) {
        JSONObject parseObject = JSON.parseObject(a.a());
        parseObject.put("opid", str);
        parseObject.put("is_gzipped", Boolean.valueOf(true));
        parseObject.put("is_crypto", Boolean.valueOf(true));
        return ((LogUploadService) c.b(LogUploadService.class)).uploadClientData(v_b, z.create(null, parseObject.toString()));
    }

    public static d<String> a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("opid", str2);
        jSONObject.put("is_gzipped", Boolean.valueOf(true));
        jSONObject.put("is_crypto", Boolean.valueOf(true));
        jSONObject.put("content", str);
        return ((LogUploadService) c.b(LogUploadService.class)).uploadClientEnv(jSONObject);
    }
}
