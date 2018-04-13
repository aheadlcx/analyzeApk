package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebPicUploadResult {
    public static final String RESP_UPLOAD_PIC_PARAM_CODE = "code";
    public static final String RESP_UPLOAD_PIC_PARAM_DATA = "data";
    public static final int RESP_UPLOAD_PIC_SUCC_CODE = 1;
    private int a = -2;
    private String b;

    private WebPicUploadResult() {
    }

    public int getCode() {
        return this.a;
    }

    public String getPicId() {
        return this.b;
    }

    public static WebPicUploadResult parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        WebPicUploadResult webPicUploadResult = new WebPicUploadResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            webPicUploadResult.a = jSONObject.optInt("code", -2);
            webPicUploadResult.b = jSONObject.optString("data", "");
            return webPicUploadResult;
        } catch (JSONException e) {
            return webPicUploadResult;
        }
    }
}
