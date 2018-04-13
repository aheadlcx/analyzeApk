package com.meizu.cloud.pushsdk.platform.message;

import android.text.TextUtils;
import com.meizu.cloud.a.a;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BasicPushStatus implements Serializable {
    public static final String SUCCESS_CODE = "200";
    public static final String TAG = "BasicPushStatus";
    public String code;
    public String message;

    public abstract void parseValueData(JSONObject jSONObject) throws JSONException;

    public BasicPushStatus(String str) {
        JSONObject parse = parse(str);
        if (parse != null && "200".equals(this.code) && !parse.isNull("value")) {
            try {
                parseValueData(parse.getJSONObject("value"));
            } catch (JSONException e) {
                a.d(TAG, "parse value data error " + e.getMessage() + " json " + str);
            }
        }
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    protected JSONObject parse(String str) {
        JSONException e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return jSONObject;
            }
            try {
                if (!jSONObject.isNull("code")) {
                    setCode(jSONObject.getString("code"));
                }
                if (jSONObject.isNull("message")) {
                    return jSONObject;
                }
                setMessage(jSONObject.getString("message"));
                return jSONObject;
            } catch (JSONException e2) {
                e = e2;
                a.d(TAG, "covert json error " + e.getMessage());
                return jSONObject;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            jSONObject = null;
            e = jSONException;
            a.d(TAG, "covert json error " + e.getMessage());
            return jSONObject;
        }
    }

    public String toString() {
        return "BasicPushStatus{code='" + this.code + '\'' + ", message='" + this.message + '\'' + '}';
    }
}
