package cn.xiaochuankeji.tieba.push.service;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.concurrent.TimeUnit;
import tv.danmaku.ijk.media.player.IjkMediaPlayer$OnNativeInvokeListener;

class c {
    long a;
    String b;
    int c;

    c(String str) throws NumberFormatException {
        JSONObject parseObject = JSON.parseObject(str);
        Object string = parseObject.getString("heart");
        this.a = TimeUnit.SECONDS.toMillis((long) (TextUtils.isEmpty(string) ? 180 : Integer.parseInt(string)));
        this.b = parseObject.getString("ip");
        this.c = parseObject.getIntValue(IjkMediaPlayer$OnNativeInvokeListener.ARG_PORT);
        this.c = Integer.parseInt(parseObject.getString(IjkMediaPlayer$OnNativeInvokeListener.ARG_PORT));
    }

    public String toString() {
        return "Route{heartbeat=" + this.a + ", ip=" + this.b + ", port=" + this.c + '}';
    }
}
