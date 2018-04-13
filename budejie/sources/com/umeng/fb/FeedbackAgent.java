package com.umeng.fb;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import java.util.List;
import u.fb.b;
import u.fb.l;
import u.fb.o;

public class FeedbackAgent {
    private static final String a = FeedbackAgent.class.getName();
    private Context b;
    private Store c = Store.getInstance(this.b);

    public FeedbackAgent(Context context) {
        this.b = context;
    }

    public void setDebug(boolean z) {
        b.a = z;
    }

    public List<String> getAllConversationIds() {
        return this.c.getAllConversationIds();
    }

    public Conversation getConversationById(String str) {
        return this.c.getConversationById(str);
    }

    public Conversation getDefaultConversation() {
        List allConversationIds = getAllConversationIds();
        if (allConversationIds == null || allConversationIds.size() < 1) {
            b.c(a, "getDefaultConversation: No conversation saved locally. Create a new one.");
            return new Conversation(this.b);
        }
        b.c(a, "getDefaultConversation: There are " + allConversationIds.size() + " saved locally, use the first one by default.");
        return getConversationById((String) allConversationIds.get(0));
    }

    public void sync() {
        getDefaultConversation().sync(new FeedbackAgent$1(this));
    }

    private void a(String str) {
        NotificationManager notificationManager = (NotificationManager) this.b.getSystemService("notification");
        CharSequence string = this.b.getString(o.b(this.b));
        Intent intent = new Intent(this.b, ConversationActivity.class);
        intent.setFlags(131072);
        notificationManager.notify(0, new Builder(this.b).setSmallIcon(l.c(this.b)).setContentTitle(string).setTicker(string).setContentText(str).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.b, 0, intent, 0)).build());
    }

    public UserInfo getUserInfo() {
        return this.c.getUserInfo();
    }

    public void setUserInfo(UserInfo userInfo) {
        this.c.saveUserInfo(userInfo);
    }

    public long getUserInfoLastUpdateAt() {
        return this.c.getUserInfoLastUpdateAt();
    }

    public void startFeedbackActivity() {
        try {
            Intent intent = new Intent();
            intent.setClass(this.b, ConversationActivity.class);
            this.b.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
