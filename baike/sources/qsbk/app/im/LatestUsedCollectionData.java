package qsbk.app.im;

import java.io.Serializable;
import qsbk.app.im.CollectEmotion.CollectImageDomain;
import qsbk.app.im.CollectEmotion.CollectImageLocal;

public class LatestUsedCollectionData implements Serializable {
    public static final int COLLECTION_TYPE = 1;
    public static final int EMOTION_TYPE = 2;
    public static final int LOCAL_TYPE = 3;
    public static final int MANAGE_TYPE = 4;
    public ChatMsgEmotionData chatMsgEmotionData;
    public CollectImageDomain collectImageDomain;
    public CollectImageLocal collectImageLocal;
    public String data;
    public int id;
    public int type;
    public long usedTime;

    public LatestUsedCollectionData(ChatMsgEmotionData chatMsgEmotionData) {
        this.type = 2;
        this.chatMsgEmotionData = chatMsgEmotionData;
        this.data = chatMsgEmotionData.encodeToJsonObject().toString();
    }

    public LatestUsedCollectionData(CollectImageDomain collectImageDomain) {
        this.type = 1;
        this.collectImageDomain = collectImageDomain;
        this.data = collectImageDomain.encodeToJsonObject().toString();
    }

    public LatestUsedCollectionData(CollectImageLocal collectImageLocal) {
        this.type = 3;
        this.collectImageLocal = collectImageLocal;
        this.data = collectImageLocal.encodeToJsonObject().toString();
    }

    public LatestUsedCollectionData(int i, String str) {
        this.type = i;
        this.data = str;
    }

    public LatestUsedCollectionData(int i, int i2, String str, long j) {
        this.type = i2;
        this.data = str;
        this.usedTime = j;
        this.id = i;
        parse(str);
    }

    public void parse(String str) {
        if (this.type == 2) {
            this.chatMsgEmotionData = new ChatMsgEmotionData(str, true);
        } else if (this.type == 1) {
            this.collectImageDomain = new CollectImageDomain(str, true);
        } else if (this.type == 3) {
            this.collectImageLocal = new CollectImageLocal(str, true);
        }
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LatestUsedCollectionData)) {
            return false;
        }
        int i = ((LatestUsedCollectionData) obj).type;
        if (this.type == i) {
            if (i == 2) {
                if (this.chatMsgEmotionData.equals(((LatestUsedCollectionData) obj).chatMsgEmotionData)) {
                    return true;
                }
            } else if (i == 1) {
                if (this.collectImageDomain.equals(((LatestUsedCollectionData) obj).collectImageDomain)) {
                    return true;
                }
            } else if (i == 3 && this.collectImageLocal.equals(((LatestUsedCollectionData) obj).collectImageLocal)) {
                return true;
            }
        }
        return false;
    }
}
