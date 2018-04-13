package cn.xiaochuankeji.tieba.api.upload;

import cn.xiaochuankeji.tieba.json.ConvertImageIdJson;
import cn.xiaochuankeji.tieba.json.OSSTokenJson;
import cn.xiaochuankeji.tieba.json.upload.AllCheckJson;
import cn.xiaochuankeji.tieba.json.upload.BlockInitJson;
import cn.xiaochuankeji.tieba.json.upload.GetVideoIdJson;
import cn.xiaochuankeji.tieba.json.upload.ImgResultJson;
import com.alibaba.fastjson.JSONObject;
import okhttp3.v$b;
import okhttp3.z;
import retrofit2.a.a;
import retrofit2.a.l;
import retrofit2.a.o;
import retrofit2.a.q;
import rx.d;

public interface UploadService {
    @o(a = "/zyapi/upload/blockinit")
    d<BlockInitJson> blockInit(@a JSONObject jSONObject);

    @o(a = "/upload/genid")
    d<ConvertImageIdJson> convertMediaUrl(@a JSONObject jSONObject);

    @o(a = "/upload/oss_config")
    d<OSSTokenJson> getOssToken(@a JSONObject jSONObject);

    @o(a = "/video/gen_videothumb")
    d<GetVideoIdJson> getVideoId(@a JSONObject jSONObject);

    @o(a = "/zyapi/upload/blockcomplete")
    d<AllCheckJson> uploadComplete(@a JSONObject jSONObject);

    @o(a = "/upload/img")
    @l
    d<ImgResultJson> uploadImg(@q v$b v_b, @q(a = "json") z zVar);

    @o(a = "/zyapi/upload/blockdata")
    @l
    d<String> uploadVideo(@q v$b v_b, @q(a = "json") z zVar);
}
