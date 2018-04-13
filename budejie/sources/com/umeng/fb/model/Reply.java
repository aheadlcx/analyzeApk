package com.umeng.fb.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import u.fb.b;
import u.fb.p;

public abstract class Reply implements Comparable<Reply> {
    private static final String a = Reply.class.getName();
    private static final String j = "content";
    private static final String k = "reply_id";
    private static final String l = "appkey";
    private static final String m = "user_id";
    private static final String n = "feedback_id";
    private static final String o = "type";
    private static final String p = "datetime";
    private static final String q = "status";
    protected String b;
    protected String c;
    protected String d;
    protected String e;
    protected String f;
    protected TYPE g;
    protected Date h;
    protected STATUS i;

    public enum STATUS {
        SENDING("sending"),
        NOT_SENT("not_sent"),
        SENT("sent");
        
        private final String a;

        private STATUS(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }

        public static STATUS get(String str) {
            if (SENDING.toString().equals(str)) {
                return SENDING;
            }
            if (NOT_SENT.toString().equals(str)) {
                return NOT_SENT;
            }
            if (SENT.toString().equals(str)) {
                return SENT;
            }
            throw new RuntimeException(new StringBuilder(String.valueOf(str)).append("Cannot convert ").append(str).append(" to enum ").append(STATUS.class.getName()).toString());
        }
    }

    public enum TYPE {
        NEW_FEEDBACK("new_feedback"),
        DEV_REPLY("dev_reply"),
        USER_REPLY("user_reply");
        
        private final String a;

        private TYPE(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }

        public static TYPE get(String str) {
            if (NEW_FEEDBACK.toString().equals(str)) {
                return NEW_FEEDBACK;
            }
            if (DEV_REPLY.toString().equals(str)) {
                return DEV_REPLY;
            }
            if (USER_REPLY.toString().equals(str)) {
                return USER_REPLY;
            }
            throw new RuntimeException(new StringBuilder(String.valueOf(str)).append("Cannot convert ").append(str).append(" to enum ").append(TYPE.class.getName()).toString());
        }
    }

    Reply(String str, String str2, String str3, String str4, TYPE type) {
        this.b = str;
        this.c = p.a();
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = type;
        this.h = new Date();
        this.i = STATUS.NOT_SENT;
    }

    Reply(JSONObject jSONObject) throws JSONException {
        this.b = jSONObject.optString("content", "");
        this.c = jSONObject.optString(k, "");
        this.d = jSONObject.optString("appkey", "");
        this.e = jSONObject.optString("user_id", "");
        this.f = jSONObject.optString(n, "");
        try {
            this.g = TYPE.get(jSONObject.getString("type"));
            try {
                this.h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jSONObject.getString(p));
            } catch (ParseException e) {
                try {
                    this.h = new SimpleDateFormat().parse(jSONObject.getString(p));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    b.b(a, "Reply(JSONObject json): error parsing datetime from json " + jSONObject.optString(p, "") + ", using current Date instead.");
                    this.h = new Date();
                }
            }
            this.i = STATUS.get(jSONObject.optString("status", STATUS.NOT_SENT.toString()));
        } catch (Exception e3) {
            throw new JSONException(e3.getMessage());
        }
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("content", this.b);
            jSONObject.put(k, this.c);
            jSONObject.put("appkey", this.d);
            jSONObject.put("user_id", this.e);
            jSONObject.put(n, this.f);
            jSONObject.put("type", this.g);
            jSONObject.put(p, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(this.h));
            jSONObject.put("status", this.i.toString());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int compareTo(Reply reply) {
        return this.h.compareTo(reply.h);
    }

    public String getContent() {
        return this.b;
    }

    public Date getDatetime() {
        return this.h;
    }

    public STATUS getStatus() {
        return this.i;
    }
}
