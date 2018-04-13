package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage implements a {
    public static final int MESSAGE_TYPE_ACCOUNT = 3;
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;
    private static final long serialVersionUID = 1;
    private String alias;
    private boolean arrived = false;
    private String category;
    private String content;
    private String description;
    private HashMap<String, String> extra = new HashMap();
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;
    private String userAccount;

    public static MiPushMessage fromBundle(Bundle bundle) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.messageId = bundle.getString("messageId");
        miPushMessage.messageType = bundle.getInt("messageType");
        miPushMessage.passThrough = bundle.getInt("passThrough");
        miPushMessage.alias = bundle.getString("alias");
        miPushMessage.userAccount = bundle.getString("user_account");
        miPushMessage.topic = bundle.getString("topic");
        miPushMessage.content = bundle.getString("content");
        miPushMessage.description = bundle.getString("description");
        miPushMessage.title = bundle.getString("title");
        miPushMessage.isNotified = bundle.getBoolean("isNotified");
        miPushMessage.notifyId = bundle.getInt("notifyId");
        miPushMessage.notifyType = bundle.getInt("notifyType");
        miPushMessage.category = bundle.getString("category");
        miPushMessage.extra = (HashMap) bundle.getSerializable(PushConstants.EXTRA);
        return miPushMessage;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getCategory() {
        return this.category;
    }

    public String getContent() {
        return this.content;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getUserAccount() {
        return this.userAccount;
    }

    public boolean isArrivedMessage() {
        return this.arrived;
    }

    public boolean isNotified() {
        return this.isNotified;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setArrivedMessage(boolean z) {
        this.arrived = z;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setExtra(Map<String, String> map) {
        this.extra.clear();
        if (map != null) {
            this.extra.putAll(map);
        }
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setMessageType(int i) {
        this.messageType = i;
    }

    public void setNotified(boolean z) {
        this.isNotified = z;
    }

    public void setNotifyId(int i) {
        this.notifyId = i;
    }

    public void setNotifyType(int i) {
        this.notifyType = i;
    }

    public void setPassThrough(int i) {
        this.passThrough = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public void setUserAccount(String str) {
        this.userAccount = str;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("messageId", this.messageId);
        bundle.putInt("passThrough", this.passThrough);
        bundle.putInt("messageType", this.messageType);
        if (!TextUtils.isEmpty(this.alias)) {
            bundle.putString("alias", this.alias);
        }
        if (!TextUtils.isEmpty(this.userAccount)) {
            bundle.putString("user_account", this.userAccount);
        }
        if (!TextUtils.isEmpty(this.topic)) {
            bundle.putString("topic", this.topic);
        }
        bundle.putString("content", this.content);
        if (!TextUtils.isEmpty(this.description)) {
            bundle.putString("description", this.description);
        }
        if (!TextUtils.isEmpty(this.title)) {
            bundle.putString("title", this.title);
        }
        bundle.putBoolean("isNotified", this.isNotified);
        bundle.putInt("notifyId", this.notifyId);
        bundle.putInt("notifyType", this.notifyType);
        if (!TextUtils.isEmpty(this.category)) {
            bundle.putString("category", this.category);
        }
        if (this.extra != null) {
            bundle.putSerializable(PushConstants.EXTRA, this.extra);
        }
        return bundle;
    }

    public String toString() {
        return "messageId={" + this.messageId + "},passThrough={" + this.passThrough + "},alias={" + this.alias + "},topic={" + this.topic + "},userAccount={" + this.userAccount + "},content={" + this.content + "},description={" + this.description + "},title={" + this.title + "},isNotified={" + this.isNotified + "},notifyId={" + this.notifyId + "},notifyType={" + this.notifyType + "}, category={" + this.category + "}, extra={" + this.extra + "}";
    }
}
