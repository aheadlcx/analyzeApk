package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage implements a {
    public static final int MESSAGE_TYPE_ACCOUNT = 3;
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;
    private String a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private String k;
    private String l;
    private String m;
    private boolean n = false;
    private HashMap<String, String> o = new HashMap();

    public static MiPushMessage fromBundle(Bundle bundle) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.a = bundle.getString("messageId");
        miPushMessage.b = bundle.getInt("messageType");
        miPushMessage.g = bundle.getInt("passThrough");
        miPushMessage.d = bundle.getString("alias");
        miPushMessage.f = bundle.getString("user_account");
        miPushMessage.e = bundle.getString("topic");
        miPushMessage.c = bundle.getString("content");
        miPushMessage.k = bundle.getString("description");
        miPushMessage.l = bundle.getString("title");
        miPushMessage.j = bundle.getBoolean("isNotified");
        miPushMessage.i = bundle.getInt("notifyId");
        miPushMessage.h = bundle.getInt("notifyType");
        miPushMessage.m = bundle.getString("category");
        miPushMessage.o = (HashMap) bundle.getSerializable("extra");
        return miPushMessage;
    }

    public String getAlias() {
        return this.d;
    }

    public String getCategory() {
        return this.m;
    }

    public String getContent() {
        return this.c;
    }

    public String getDescription() {
        return this.k;
    }

    public Map<String, String> getExtra() {
        return this.o;
    }

    public String getMessageId() {
        return this.a;
    }

    public int getMessageType() {
        return this.b;
    }

    public int getNotifyId() {
        return this.i;
    }

    public int getNotifyType() {
        return this.h;
    }

    public int getPassThrough() {
        return this.g;
    }

    public String getTitle() {
        return this.l;
    }

    public String getTopic() {
        return this.e;
    }

    public String getUserAccount() {
        return this.f;
    }

    public boolean isArrivedMessage() {
        return this.n;
    }

    public boolean isNotified() {
        return this.j;
    }

    public void setAlias(String str) {
        this.d = str;
    }

    public void setArrivedMessage(boolean z) {
        this.n = z;
    }

    public void setCategory(String str) {
        this.m = str;
    }

    public void setContent(String str) {
        this.c = str;
    }

    public void setDescription(String str) {
        this.k = str;
    }

    public void setExtra(Map<String, String> map) {
        this.o.clear();
        if (map != null) {
            this.o.putAll(map);
        }
    }

    public void setMessageId(String str) {
        this.a = str;
    }

    public void setMessageType(int i) {
        this.b = i;
    }

    public void setNotified(boolean z) {
        this.j = z;
    }

    public void setNotifyId(int i) {
        this.i = i;
    }

    public void setNotifyType(int i) {
        this.h = i;
    }

    public void setPassThrough(int i) {
        this.g = i;
    }

    public void setTitle(String str) {
        this.l = str;
    }

    public void setTopic(String str) {
        this.e = str;
    }

    public void setUserAccount(String str) {
        this.f = str;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("messageId", this.a);
        bundle.putInt("passThrough", this.g);
        bundle.putInt("messageType", this.b);
        if (!TextUtils.isEmpty(this.d)) {
            bundle.putString("alias", this.d);
        }
        if (!TextUtils.isEmpty(this.f)) {
            bundle.putString("user_account", this.f);
        }
        if (!TextUtils.isEmpty(this.e)) {
            bundle.putString("topic", this.e);
        }
        bundle.putString("content", this.c);
        if (!TextUtils.isEmpty(this.k)) {
            bundle.putString("description", this.k);
        }
        if (!TextUtils.isEmpty(this.l)) {
            bundle.putString("title", this.l);
        }
        bundle.putBoolean("isNotified", this.j);
        bundle.putInt("notifyId", this.i);
        bundle.putInt("notifyType", this.h);
        if (!TextUtils.isEmpty(this.m)) {
            bundle.putString("category", this.m);
        }
        if (this.o != null) {
            bundle.putSerializable("extra", this.o);
        }
        return bundle;
    }

    public String toString() {
        return "messageId={" + this.a + "},passThrough={" + this.g + "},alias={" + this.d + "},topic={" + this.e + "},userAccount={" + this.f + "},content={" + this.c + "},description={" + this.k + "},title={" + this.l + "},isNotified={" + this.j + "},notifyId={" + this.i + "},notifyType={" + this.h + "}, category={" + this.m + "}, extra={" + this.o + h.d;
    }
}
