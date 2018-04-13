package com.umeng.fb.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store {
    private static final String a = Store.class.getName();
    private static Store b = null;
    private static final String d = "umeng_feedback_conversations";
    private static final String e = "umeng_feedback_user_info";
    private static final String f = "user";
    private static final String g = "last_update_at";
    private static final String h = "last_sync_at";
    private Context c;

    private Store(Context context) {
        this.c = context.getApplicationContext();
    }

    public static Store getInstance(Context context) {
        if (b == null) {
            b = new Store(context);
        }
        return b;
    }

    public void saveCoversation(Conversation conversation) {
        this.c.getSharedPreferences(d, 0).edit().putString(conversation.getId(), conversation.a().toString()).commit();
    }

    public void saveUserInfo(UserInfo userInfo) {
        this.c.getSharedPreferences(e, 0).edit().putString(f, userInfo.toJson().toString()).putLong(g, System.currentTimeMillis()).commit();
    }

    public UserInfo getUserInfo() {
        String string = this.c.getSharedPreferences(e, 0).getString(f, "");
        if ("".equals(string)) {
            return null;
        }
        try {
            return new UserInfo(new JSONObject(string));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getUserInfoLastUpdateAt() {
        return this.c.getSharedPreferences(e, 0).getLong(g, 0);
    }

    public long getUserInfoLastSyncAt() {
        return this.c.getSharedPreferences(e, 0).getLong(h, 0);
    }

    public void setUserInfoLastSyncAt(long j) {
        this.c.getSharedPreferences(e, 0).edit().putLong(h, j).commit();
    }

    public Conversation getConversationById(String str) {
        try {
            return new Conversation(str, new JSONArray(this.c.getSharedPreferences(d, 0).getString(str, "")), this.c);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllConversationIds() {
        Map all = this.c.getSharedPreferences(d, 0).getAll();
        List<String> arrayList = new ArrayList();
        for (String add : all.keySet()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    void a() {
        this.c.getSharedPreferences(d, 0).edit().clear().commit();
        this.c.getSharedPreferences(e, 0).edit().clear().commit();
    }
}
