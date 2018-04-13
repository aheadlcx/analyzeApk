package com.budejie.www.activity.video.barrage.danmaku.a.a;

import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;

public class c implements com.budejie.www.activity.video.barrage.danmaku.a.c<JSONArray> {
    private JSONArray a;
    private InputStream b;

    public c(InputStream inputStream) throws JSONException {
        a(inputStream);
    }

    private void a(InputStream inputStream) throws JSONException {
        if (inputStream == null) {
            throw new NullPointerException("input stream cannot be null!");
        }
        this.b = inputStream;
        a(com.budejie.www.activity.video.barrage.danmaku.c.c.a(this.b));
    }

    private void a(String str) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            this.a = new JSONArray(str);
        }
    }

    public void a() {
        com.budejie.www.activity.video.barrage.danmaku.c.c.c(this.b);
        this.b = null;
        this.a = null;
    }
}
