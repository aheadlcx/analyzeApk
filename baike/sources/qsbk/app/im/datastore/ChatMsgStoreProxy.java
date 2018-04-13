package qsbk.app.im.datastore;

import java.util.Collection;
import java.util.List;
import qsbk.app.im.ChatMsg;

public class ChatMsgStoreProxy extends BaseChatMsgStore {
    private BaseChatMsgStore d;
    private String e;

    public ChatMsgStoreProxy(String str, int i) {
        super(str);
        this.e = str;
        if (i == 3) {
            this.d = GroupChatMsgStore.getInstance(str);
        } else {
            this.d = ChatMsgStore.getChatMsgStore(str);
        }
    }

    public static ChatMsgStoreProxy newInstance(String str, int i) {
        return new ChatMsgStoreProxy(str, i);
    }

    public List<ChatMsg> get(int i, int i2, String str, String str2) {
        return this.d.get(i, i2, str, str2);
    }

    public List<ChatMsg> get(int i, int i2, String str) {
        return this.d.get(i, i2, str);
    }

    public int getUnReadCountWith(String str) {
        return this.d.getUnReadCountWith(str);
    }

    public int deleteMessagesWith(String... strArr) {
        return this.d.deleteMessagesWith(strArr);
    }

    public int deleteMessagesWithUid(String... strArr) {
        return this.d.deleteMessagesWithUid(strArr);
    }

    public int deleteMessagesWith(long... jArr) {
        return this.d.deleteMessagesWith(jArr);
    }

    public int deleteMessagesWith(String str) {
        return this.d.deleteMessagesWith(str);
    }

    public int deleteAllMessages() {
        return this.d.deleteAllMessages();
    }

    public int getTotalUnReadCount() {
        return this.d.getTotalUnReadCount();
    }

    public int updateMessageState(String str, int i) {
        return this.d.updateMessageState(str, i);
    }

    public int updateMessageState(long j, int i) {
        return this.d.updateMessageState(j, i);
    }

    public void markMessagesToReadWith(int i) {
        this.d.markMessagesToReadWith(i);
    }

    public void markAllMessagesToRead() {
        this.d.markAllMessagesToRead();
    }

    public int[] getUnreadCountWithIds(String[] strArr) {
        return this.d.getUnreadCountWithIds(strArr);
    }

    public long insert(ChatMsg chatMsg) {
        return this.d.insert(chatMsg);
    }

    public int insert(List<ChatMsg> list) {
        return this.d.insert((List) list);
    }

    public List<ChatMsg> get(long... jArr) {
        return this.d.get(jArr);
    }

    public int updateMessageData(ChatMsg chatMsg) {
        return this.d.updateMessageData(chatMsg);
    }

    public int setMessageReadedBatch(List<Long> list) {
        return this.d.setMessageReadedBatch(list);
    }

    public int setMessageReaded(List<String> list) {
        return this.d.setMessageReaded(list);
    }

    public List<String> getUnreadMsgIds(String str) {
        return this.d.getUnreadMsgIds(str);
    }

    public int updateMessageStateBatch(List<String> list, int i) {
        return this.d.updateMessageStateBatch(list, i);
    }

    public List<ChatMsg> getSendFailMsg() {
        BaseChatMsgStore baseChatMsgStore = null;
        if (this.d instanceof ChatMsgStore) {
            baseChatMsgStore = GroupChatMsgStore.getInstance(this.e);
        } else if (this.d instanceof GroupChatMsgStore) {
            baseChatMsgStore = ChatMsgStore.getChatMsgStore(this.e);
        }
        List<ChatMsg> sendFailMsg = this.d.getSendFailMsg();
        if (baseChatMsgStore != null) {
            Collection sendFailMsg2 = baseChatMsgStore.getSendFailMsg();
            if (sendFailMsg2 != null && sendFailMsg2.size() > 0) {
                sendFailMsg.addAll(sendFailMsg2);
            }
        }
        return sendFailMsg;
    }

    public boolean isChatMsgExist(String str) {
        return this.d.isChatMsgExist(str);
    }
}
