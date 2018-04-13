package qsbk.app.im;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.utils.json.JsonKeyName;
import qsbk.app.utils.json.JsonPrivate;

public class ChatMsgEmotionData extends JSONAble {
    @JsonKeyName("height")
    public int height;
    @JsonKeyName("key")
    public String key;
    @JsonPrivate
    public int localResource = -1;
    @JsonKeyName("name")
    public String name;
    @JsonKeyName("namespace")
    public String namespace;
    @JsonKeyName("url")
    public String url;
    @JsonKeyName("width")
    public int width;

    public ChatMsgEmotionData(String str) {
        try {
            parseFromJSONObject(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ChatMsgEmotionData(String str, boolean z) {
        try {
            parseFromJSONObject(new JSONObject(str));
            this.localResource = QsbkApp.mContext.getResources().getIdentifier(this.key, "drawable", QsbkApp.mContext.getPackageName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ChatMsgEmotionData(JSONObject jSONObject) {
        parseFromJSONObject(jSONObject);
    }

    public static void copyValue(ChatMsgEmotionData chatMsgEmotionData, ChatMsgEmotionData chatMsgEmotionData2) {
        if (chatMsgEmotionData != null && chatMsgEmotionData2 != null) {
            if (chatMsgEmotionData.height > 0) {
                chatMsgEmotionData2.height = chatMsgEmotionData.height;
            }
            if (chatMsgEmotionData.width > 0) {
                chatMsgEmotionData2.width = chatMsgEmotionData.width;
            }
            if (!TextUtils.isEmpty(chatMsgEmotionData.key)) {
                chatMsgEmotionData2.key = chatMsgEmotionData.key;
            }
            if (chatMsgEmotionData.hasLocal()) {
                chatMsgEmotionData2.localResource = chatMsgEmotionData.localResource;
            }
            if (!TextUtils.isEmpty(chatMsgEmotionData.name)) {
                chatMsgEmotionData2.name = chatMsgEmotionData.name;
            }
            if (!TextUtils.isEmpty(chatMsgEmotionData.namespace)) {
                chatMsgEmotionData2.namespace = chatMsgEmotionData.namespace;
            }
            if (!TextUtils.isEmpty(chatMsgEmotionData.url)) {
                chatMsgEmotionData2.url = chatMsgEmotionData.url;
            }
        }
    }

    public boolean hasLocal() {
        return this.localResource > 0;
    }

    public String toString() {
        return "ChatMsgEmotionData [url=" + this.url + ", height=" + this.height + ", width=" + this.width + ", name=" + this.name + ", namespace=" + this.namespace + ", key=" + this.key + "]";
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.name == null ? 0 : this.name.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + 31) * 31)) * 31;
        if (this.namespace != null) {
            i = this.namespace.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ChatMsgEmotionData chatMsgEmotionData = (ChatMsgEmotionData) obj;
        if (this.key == null) {
            if (chatMsgEmotionData.key != null) {
                return false;
            }
        } else if (!this.key.equals(chatMsgEmotionData.key)) {
            return false;
        }
        if (this.name == null) {
            if (chatMsgEmotionData.name != null) {
                return false;
            }
        } else if (!this.name.equals(chatMsgEmotionData.name)) {
            return false;
        }
        if (this.namespace == null) {
            if (chatMsgEmotionData.namespace != null) {
                return false;
            }
        } else if (!this.namespace.equals(chatMsgEmotionData.namespace)) {
            return false;
        }
        return true;
    }
}
