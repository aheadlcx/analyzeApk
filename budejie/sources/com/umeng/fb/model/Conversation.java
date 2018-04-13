package com.umeng.fb.model;

import android.content.Context;
import com.umeng.fb.model.Reply.TYPE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import u.fb.a;
import u.fb.p;

public class Conversation {
    private static final String b = Conversation.class.getName();
    private static ExecutorService d = Executors.newSingleThreadExecutor();
    List<Reply> a = new ArrayList();
    private Context c;
    private String e;
    private String f;
    private String g;
    private Map<String, Reply> h;

    public interface SyncListener {
        void onReceiveDevReply(List<DevReply> list);

        void onSendUserReply(List<Reply> list);
    }

    public synchronized List<Reply> getReplyList() {
        this.a.clear();
        this.a.addAll(this.h.values());
        Collections.sort(this.a);
        return this.a;
    }

    public Conversation(Context context) {
        this.c = context;
        this.g = a.i(this.c);
        this.e = p.a(this.c);
        this.f = a.d(this.c);
        this.h = new ConcurrentHashMap();
    }

    Conversation(String str, JSONArray jSONArray, Context context) throws JSONException {
        this.c = context;
        this.g = a.i(this.c);
        this.e = str;
        this.f = a.d(this.c);
        this.h = new HashMap();
        if (jSONArray != null && jSONArray.length() >= 1) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                Reply reply = null;
                if (TYPE.NEW_FEEDBACK.toString().equals(string)) {
                    reply = new UserTitleReply(jSONObject);
                } else if (TYPE.USER_REPLY.toString().equals(string)) {
                    reply = new UserReply(jSONObject);
                } else if (TYPE.DEV_REPLY.toString().equals(string)) {
                    reply = new DevReply(jSONObject);
                }
                if (reply == null) {
                    throw new JSONException("Failed to create Conversation using given JSONArray: " + jSONArray + " at element " + i + ": " + jSONObject);
                }
                if (!this.h.containsKey(reply.c)) {
                    this.h.put(reply.c, reply);
                }
            }
            b();
        }
    }

    public void addUserReply(String str) {
        Reply userTitleReply;
        if (this.h.size() < 1) {
            userTitleReply = new UserTitleReply(str, this.g, this.f, this.e);
        } else {
            userTitleReply = new UserReply(str, this.g, this.f, this.e);
        }
        if (!this.h.containsKey(userTitleReply.c)) {
            this.h.put(userTitleReply.c, userTitleReply);
        }
        b();
    }

    private void b() {
        Store.getInstance(this.c).saveCoversation(this);
    }

    JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        for (Entry value : this.h.entrySet()) {
            jSONArray.put(((Reply) value.getValue()).toJson());
        }
        return jSONArray;
    }

    public String getId() {
        return this.e;
    }

    public void sync(SyncListener syncListener) {
        d.execute(new Conversation$1(this, new Conversation$SyncHandler(this, syncListener)));
    }
}
