package qsbk.app.im.datastore;

import android.util.Log;
import com.xiaomi.mipush.sdk.Constants;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ContactListItem;
import qsbk.app.utils.Md5;
import qsbk.app.utils.image.issue.Logger;

public class StoreTest {
    private static final String a = StoreTest.class.getSimpleName();
    private final ContactListItemStore b;
    private final ChatMsgStore c;
    private final DraftStore d;

    private static class a<T> implements Callback<T> {
        private final String a;

        public a(String str) {
            this.a = str;
        }

        public void onFinished(T t) {
            Logger.getInstance().debug(StoreTest.a, "onFinished", this.a + t);
        }

        public void onFailure(Throwable th) {
            Logger.getInstance().debug(StoreTest.a, "onFailure", this.a + th.toString());
        }
    }

    public StoreTest(ContactListItemStore contactListItemStore, ChatMsgStore chatMsgStore, DraftStore draftStore) {
        this.c = chatMsgStore;
        this.b = contactListItemStore;
        this.d = draftStore;
    }

    public void testQiushiNotify() {
        ChatMsg chatMsg = new ChatMsg(20, "{\"jump\":\"comment\",\"jump_data\":{\"votes\":{\"down\":-9,\"up\":135},\"is_mine\":true,\"m_type\":\"comment\",\"format\":\"image\",\"created_at\":1451012427,\"state\":\"publish\",\"content\":\"公司每人一朵，来个妹子收走吧\",\"published_at\":1451015118,\"tag\":\"\",\"comments_count\":10,\"user\":{\"avatar_updated_at\":1441882592,\"last_visited_at\":1395745799,\"created_at\":1395745799,\"state\":\"bonded\",\"last_device\":\"android_2.8.1\",\"role\":\"admin\",\"login\":\"咩名字好呢\",\"id\":15028498,\"icon\":\"20150910105631.jpg\"},\"image\":\"app114409766.jpg\",\"image_size\":{\"s\":[198,352,13595],\"m\":[451,800,58751]},\"allow_comment\":true,\"share_count\":0,\"type\":\"fresh\",\"id\":114409766},\"d\":\"你的糗事收到了 10 个评论，不错哟！\"}");
    }

    public void testChatMsgStore() {
        long currentTimeMillis = System.currentTimeMillis();
        ChatMsg chatMsg = new ChatMsg(1, "哈哈哈");
        chatMsg.from = "123456";
        chatMsg.fromnick = "樊俊？！";
        chatMsg.inout = 1;
        chatMsg.iscontent = true;
        chatMsg.msgid = Md5.MD5_16(chatMsg.from + System.currentTimeMillis());
        chatMsg.status = 4;
        chatMsg.time = System.currentTimeMillis();
        chatMsg.to = "888888";
        chatMsg.uid = "123456";
        chatMsg.fromicon = "fanjun.jpg";
        long insert = this.c.insert(chatMsg);
        Logger.getInstance().debug(a, "testChatMsgStore", "Step1，insert one chatmsg " + Constants.ACCEPT_TIME_SEPARATOR_SP + insert);
        Log.e(a, "dbid --> " + insert);
        chatMsg = (ChatMsg) this.c.get(0, 30, chatMsg.uid).get(0);
        Logger.getInstance().debug(a, "testChatMsgStore", "Step2, get one " + chatMsg.toString());
        Log.e(a, "get one --> " + chatMsg.toString());
        int unreadCountWithUser = this.c.getUnreadCountWithUser(chatMsg.uid);
        Logger.getInstance().debug(a, "testChatMsgStore", "Step3, get unread count " + unreadCountWithUser);
        Log.e(a, "unread -> " + unreadCountWithUser);
        unreadCountWithUser = this.c.updateMessageState(insert, 5);
        Logger.getInstance().debug(a, "testChatMsgStore", "Step4, update state. " + unreadCountWithUser);
        Log.e(a, "update -> " + unreadCountWithUser);
        int deleteMessagesWith = this.c.deleteMessagesWith(chatMsg.uid);
        Logger.getInstance().debug(a, "testChatMsgStore", "Step5, delete message " + deleteMessagesWith);
        Log.e(a, "delete " + deleteMessagesWith);
        Logger.getInstance().debug(a, "testChatMsgStore", "cost " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void testChatMsgStoreAsync() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = "Step1，insert one chatmsg ";
        str = "Step2, get one ";
        str = "Step3, update ";
        str = "Step4, delete ";
        ChatMsg chatMsg = new ChatMsg(1, "呵呵呵");
        chatMsg.from = "123456";
        chatMsg.fromnick = "樊俊？！";
        chatMsg.inout = 1;
        chatMsg.iscontent = true;
        chatMsg.msgid = Md5.MD5_16(chatMsg.from + System.currentTimeMillis());
        chatMsg.status = 4;
        chatMsg.time = System.currentTimeMillis();
        chatMsg.to = "888888";
        chatMsg.uid = "123456";
        this.c.insertAsync(chatMsg, new bb(this, chatMsg));
        this.c.getAsync(0, 30, chatMsg.uid, new a("Step2, get one "));
        Logger.getInstance().debug(a, "testChatMsgStoreAsync", "cost " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void testContactListItemStore() {
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.icon = "my_icon_00100203.jpg";
        contactListItem.id = "1234567";
        contactListItem.mLastContent = "哟西！！";
        contactListItem.mLastUpdateTime = System.currentTimeMillis();
        contactListItem.msgId = contactListItem.mLastUpdateTime;
        contactListItem.name = "樊老板";
        Logger.getInstance().debug(a, "testContactListItemStore", "Step1, insert one " + this.b.insert(contactListItem));
        contactListItem = (ContactListItem) this.b.get(0, 30).get(0);
        Logger.getInstance().debug(a, "testContactListItemStore", "Step2, get one " + contactListItem.toString());
        contactListItem.id = "7654321";
        contactListItem.mLastUpdateTime = System.currentTimeMillis() + 1000;
        contactListItem.mLastContent = " = =!!!";
        this.b.insert(contactListItem);
        contactListItem = (ContactListItem) this.b.get(10, System.currentTimeMillis(), true).get(0);
        Logger.getInstance().debug(a, "testContactListItemStore", "Step3, get one again " + contactListItem);
        contactListItem.mLastContent = "哟西你的头啊》";
        contactListItem.mLastUpdateTime = System.currentTimeMillis();
        this.b.update(contactListItem);
        String str = "Step4, get one again and again ";
        contactListItem = (ContactListItem) this.b.get(0, 30).get(0);
        Logger.getInstance().debug(a, "testContactListItemStore", str + contactListItem);
        this.b.updateData("1234567", "eeeeee", System.currentTimeMillis() + 2000, 0);
        Logger.getInstance().debug(a, "testContactListItemStore", str + this.b.get(0, 30));
        Logger.getInstance().debug(a, "testContactListItemStore", "delete " + this.b.delete(contactListItem.id, 0));
    }

    public void testContactListItemStoreAsync() {
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.icon = "my_icon_20120203.jpg";
        contactListItem.id = "5555555";
        contactListItem.mLastContent = "呜呜呜呜呜！！";
        contactListItem.mLastUpdateTime = System.currentTimeMillis();
        contactListItem.msgId = contactListItem.mLastUpdateTime;
        contactListItem.name = "徐老板";
        String str = "insert one async ";
        this.b.insertAsync(contactListItem, new a("insert one async "));
        str = "get one async ";
        this.b.getAsync(0, 20, new a("get one async "));
        contactListItem.mLastContent = "哭死我了。.。";
        contactListItem.mLastUpdateTime = System.currentTimeMillis();
        str = "update one ";
        this.b.updateAsync(contactListItem, new a("update one "));
        this.b.getAsync(0, 20, new a("get one async "));
        str = " update one again  ";
        this.b.updateDataAsync(contactListItem.id, "恶心死了", System.currentTimeMillis(), 0, new a(" update one again  "));
        this.b.getAsync(0, 20, new a("get one async "));
        this.b.deleteAsync(new a("delete one "), contactListItem.id, 0);
    }

    public void testDraftStore() {
        ChatMsg chatMsg = new ChatMsg(100);
        chatMsg.data = "我是草稿！";
        chatMsg.uid = "1234567";
        chatMsg.time = System.currentTimeMillis();
        Logger.getInstance().debug(a, "testDraftStore", "insert one " + this.d.insert(chatMsg));
        chatMsg.uid = "7654321";
        chatMsg.time = System.currentTimeMillis();
        this.d.insert(chatMsg);
        chatMsg.uid = "7777777";
        chatMsg.time = System.currentTimeMillis();
        this.d.insert(chatMsg);
        Logger.getInstance().debug(a, "testDraftStore", this.d.get(0, "1234567", "7654321", "7777777").toString());
        chatMsg = this.d.get(chatMsg.uid, 0);
        Logger.getInstance().debug(a, "testDraftStore", "get one " + chatMsg.data + " ," + chatMsg.uid + Constants.ACCEPT_TIME_SEPARATOR_SP + chatMsg.time);
        chatMsg.data = "额额鹅鹅鹅";
        chatMsg.time = System.currentTimeMillis();
        Logger.getInstance().debug(a, "testDraftStore", "update " + this.d.update(chatMsg));
        chatMsg = this.d.get(chatMsg.uid, 0);
        Logger.getInstance().debug(a, "testDraftStore", "get one again " + chatMsg.data + " ," + chatMsg.uid + Constants.ACCEPT_TIME_SEPARATOR_SP + chatMsg.time);
        Logger.getInstance().debug(a, "testDraftStore", "delete " + this.d.delete(chatMsg.uid));
        Logger.getInstance().debug(a, "testDraftStore", "after delete " + this.d.get(chatMsg.uid, 0));
    }

    public void testDraftStoreAsync() {
        ChatMsg chatMsg = new ChatMsg(100);
        chatMsg.data = "哈哈哈哈哈。.。";
        chatMsg.uid = "8888888";
        chatMsg.time = System.currentTimeMillis();
        this.d.deleteAsync(chatMsg.uid, new a("Step4, delete one async "));
    }
}
