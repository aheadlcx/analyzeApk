package com.umeng.fb.model;

import com.umeng.fb.model.Reply.TYPE;
import org.json.JSONException;
import org.json.JSONObject;

public class UserReply extends Reply {
    public UserReply(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4, TYPE.USER_REPLY);
    }

    UserReply(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        if (this.g != TYPE.USER_REPLY) {
            throw new JSONException(new StringBuilder(String.valueOf(UserReply.class.getName())).append(".type must be ").append(TYPE.USER_REPLY).toString());
        }
    }

    public JSONObject toJson() {
        return super.toJson();
    }
}
