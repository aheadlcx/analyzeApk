package cn.xiaochuankeji.tieba.api.upload;

import cn.xiaochuankeji.tieba.background.upload.e;
import cn.xiaochuankeji.tieba.json.ConvertImageIdJson;
import cn.xiaochuankeji.tieba.json.ConvertMediaInfo;
import cn.xiaochuankeji.tieba.json.OSSTokenJson;
import cn.xiaochuankeji.tieba.json.upload.AllCheckJson;
import cn.xiaochuankeji.tieba.json.upload.BlockInitJson;
import cn.xiaochuankeji.tieba.json.upload.ImgResultJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import okhttp3.v$b;
import okhttp3.z;
import rx.d;

public class a {
    private UploadService a = ((UploadService) e.b().a(UploadService.class));

    public d<OSSTokenJson> a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", Integer.valueOf(1));
        return this.a.getOssToken(jSONObject);
    }

    public d<ImgResultJson> a(v$b v_b) {
        JSONObject parseObject = JSON.parseObject(cn.xiaochuankeji.tieba.background.utils.d.a.a());
        parseObject.put("restype", "uri");
        return this.a.uploadImg(v_b, z.create(null, parseObject.toString()));
    }

    public d<BlockInitJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("size", Long.valueOf(j));
        return this.a.blockInit(jSONObject);
    }

    public d<String> a(v$b v_b, long j, int i) {
        JSONObject parseObject = JSON.parseObject(cn.xiaochuankeji.tieba.background.utils.d.a.a());
        parseObject.put("uploadid", Long.valueOf(j));
        parseObject.put("block", Integer.valueOf(i));
        return this.a.uploadVideo(v_b, z.create(null, parseObject.toString()));
    }

    public d<AllCheckJson> a(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uploadid", Long.valueOf(j));
        jSONObject.put("busstype", "zuiyou_video");
        jSONObject.put("conttype", str);
        return this.a.uploadComplete(jSONObject);
    }

    public d<ConvertImageIdJson> a(List<ConvertMediaInfo> list, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("res", list);
        jSONObject.put("type", str);
        return this.a.convertMediaUrl(jSONObject);
    }
}
