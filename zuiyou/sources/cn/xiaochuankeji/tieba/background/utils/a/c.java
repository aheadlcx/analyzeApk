package cn.xiaochuankeji.tieba.background.utils.a;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer$OnNativeInvokeListener;

public class c {
    public String a;
    public String b;
    public long c;
    public String d;
    public int e;
    public String f;

    protected JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("img_url", this.a);
            jSONObject.put("fmt", this.d);
            jSONObject.put("img_size", this.b);
            jSONObject.put("download_ts", Long.valueOf(this.c));
            jSONObject.put("ts", Long.valueOf(System.currentTimeMillis()));
            jSONObject.put(IjkMediaPlayer$OnNativeInvokeListener.ARG_HTTP_CODE, Integer.valueOf(this.e));
            jSONObject.put("err_msg", this.f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
