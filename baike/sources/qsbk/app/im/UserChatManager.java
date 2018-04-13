package qsbk.app.im;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.DispatchQueue;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.fragments.ShareToImType;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.GroupChatMsgStore;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.im.image.IMImageSize;
import qsbk.app.im.image.IMImageSizeHelper;
import qsbk.app.im.image.IMImageSizeHelper.Size;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupNotice;
import qsbk.app.model.GroupSystemMsg;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.model.LaiseeVoice;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SpringFestivalUtil;
import qsbk.app.utils.TemporaryNoteUtils;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

public class UserChatManager implements IChatMsgListener, ISyncChatMsgListener {
    public static final String GROUP_PREFIX = "g_";
    public static final String GROUP_PREFIX_SERVER = "ig_";
    public static final String SYNC_PREFIX = "p";
    public static final String USER_PREFIX = "s_";
    public static final String USER_PREFIX_SERVER = "is_";
    private static final String a = UserChatManager.class.getSimpleName();
    public static UserChatManager currentChatManager;
    private String b;
    private ChatMsgStore c;
    private GroupChatMsgStore d;
    private GroupNoticeStore e;
    private SyncManager f;
    private ContactListItemStore g;
    private a h = new a(this, Util.imStorageQueue);
    private String i = null;
    private ChatClientManager j = null;
    private Set<String> k = new HashSet();
    private ChatContext l = new ChatContext();
    private HashMap<Long, Pair<String, List<String>>> m = new HashMap();
    public CopyOnWriteArrayList<IChatMsgListener> observers = new CopyOnWriteArrayList();

    public static class ChatContext {
        public static final int CHAT_CONTACT = 1;
        public static final int CHAT_GROUP = 3;
        public static final int CHAT_HIDE = 0;
        public static final int CHAT_USER = 2;
        public String id = null;
        public int type = 0;
    }

    private class a implements Runnable {
        final DispatchQueue a;
        final List<ChatMsg> b = new ArrayList();
        final /* synthetic */ UserChatManager c;

        a(UserChatManager userChatManager, DispatchQueue dispatchQueue) {
            this.c = userChatManager;
            this.a = dispatchQueue;
        }

        a a(ChatMsg chatMsg) {
            synchronized (this.b) {
                if (!this.b.contains(chatMsg)) {
                    this.b.add(chatMsg);
                }
            }
            return this;
        }

        void a() {
            this.a.cancelRunnable(this);
        }

        void a(long j) {
            a();
            Logger.getInstance().debug(UserChatManager.a, "Batch runOnHandler", "添加消息到队列去 ");
            if (j <= 0) {
                this.a.postRunnable(this);
            } else {
                this.a.postRunnable(this, j);
            }
        }

        public void run() {
            int i;
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "添加完消息，准备写数据库 ");
            List arrayList = new ArrayList();
            List<ChatMsg> arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            List arrayList4 = new ArrayList();
            List arrayList5 = new ArrayList();
            HashMap hashMap = new HashMap();
            Editor edit = PreferenceManager.getDefaultSharedPreferences(QsbkApp.mContext).edit();
            List<ChatMsg> arrayList6 = new ArrayList(this.b.size());
            synchronized (this.b) {
                arrayList6.addAll(this.b);
                this.b.clear();
            }
            int i2 = 0;
            int i3 = 0;
            for (ChatMsg chatMsg : arrayList6) {
                ChatMsg chatMsg2;
                if (chatMsg2 != null) {
                    if (chatMsg2.type == 301) {
                        GroupSystemMsg groupSystemMsg = chatMsg2.getGroupSystemMsg();
                        if (groupSystemMsg.type == 2) {
                            if (GroupSystemMsg.containId(groupSystemMsg.kids, this.c.i)) {
                                arrayList.add(chatMsg2.gid);
                            } else {
                                new GroupMemberManager(Integer.valueOf(chatMsg2.gid).intValue()).loadMemberFromServer(GroupMemberManager.EMPTY_CALLBACK);
                            }
                        } else if (groupSystemMsg.type == 3) {
                            if (groupSystemMsg.sids.equals("0")) {
                                SharePreferenceUtils.setSharePreferencesValue("mute_all_" + chatMsg2.gid, true);
                                SharePreferenceUtils.setSharePreferencesValue("mute_all_time_" + chatMsg2.gid, groupSystemMsg.time);
                            } else if (GroupSystemMsg.containId(groupSystemMsg.sids, this.c.i)) {
                                edit.putInt("mute_time_" + chatMsg2.gid + "_" + this.c.i, groupSystemMsg.time);
                            }
                        } else if (groupSystemMsg.type == 4) {
                            if (groupSystemMsg.sid.equals("0")) {
                                SharePreferenceUtils.setSharePreferencesValue("mute_all_" + chatMsg2.gid, false);
                            } else if (groupSystemMsg.sid.equals(this.c.i)) {
                                edit.remove("mute_time_" + chatMsg2.gid + "_" + this.c.i);
                            }
                        } else if (groupSystemMsg.type == 5) {
                            arrayList2.add(chatMsg2);
                        } else if (groupSystemMsg.type == 6) {
                            new GroupMemberManager(Integer.valueOf(chatMsg2.gid).intValue()).loadMemberFromServer(GroupMemberManager.EMPTY_CALLBACK);
                        } else if (groupSystemMsg.type == 7) {
                            new GroupMemberManager(Integer.valueOf(chatMsg2.gid).intValue()).loadMemberFromServer(GroupMemberManager.EMPTY_CALLBACK);
                        }
                    }
                    if (chatMsg2.type == 3) {
                        this.c.a(chatMsg2);
                    }
                    if (chatMsg2.isContentMsg()) {
                        chatMsg2.status = 4;
                        chatMsg2.inout = 1;
                        chatMsg2.uid = chatMsg2.from;
                        hashMap.put(chatMsg2.gid, chatMsg2);
                        arrayList5.add(chatMsg2);
                        if (GroupMsgUtils.isOpen(chatMsg2.gid, true)) {
                            i2++;
                            arrayList3.add(chatMsg2);
                            i = i3;
                            i3 = i2;
                            i2 = i3;
                            i3 = i;
                        } else {
                            i3++;
                            arrayList4.add(chatMsg2);
                        }
                    }
                    i = i3;
                    i3 = i2;
                    i2 = i3;
                    i3 = i;
                }
            }
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "循环分类完毕 ");
            SharePreferenceUtils.adaptCommit(edit);
            String[] strArr = new String[0];
            arrayList.toArray(strArr);
            this.c.d.deleteMessagesWithUid(strArr);
            this.c.g.delete(strArr, 3);
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "删除完毕 ");
            for (ChatMsg chatMsg22 : arrayList2) {
                String str = chatMsg22.gid;
                GroupNotifier.updateGroupInfo(str, chatMsg22.gnick, chatMsg22.gicon);
                GroupNotifier.notify(Integer.valueOf(str).intValue(), 1);
            }
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "通知完毕 ");
            int insert = this.c.d.insert(arrayList3);
            i = this.c.d.insert(arrayList4);
            this.c.d.addUserTotalMsgUnread(insert, true);
            this.c.d.addUserTotalMsgUnread(i, false);
            int size = arrayList5.size();
            String[] strArr2 = new String[size];
            for (i3 = 0; i3 < size; i3++) {
                strArr2[i3] = ((ChatMsg) arrayList5.get(i3)).msgid;
            }
            List byMsgIds = this.c.d.getByMsgIds(strArr2);
            for (int i4 = 0; i4 < size; i4++) {
                chatMsg22 = (ChatMsg) arrayList5.get(i4);
                for (i2 = 0; i2 < byMsgIds.size(); i2++) {
                    ChatMsg chatMsg3 = (ChatMsg) byMsgIds.get(i2);
                    if (chatMsg22.msgid.equals(chatMsg3.msgid)) {
                        chatMsg22.dbid = chatMsg3.dbid;
                        byMsgIds.remove(i2);
                        i = i2 - 1;
                        break;
                    }
                }
            }
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "存库消息完毕 ");
            Collection<ChatMsg> values = hashMap.values();
            Object obj = 1;
            if (values.size() == 1 && insert <= 0 && arrayList3.size() > 0) {
                obj = null;
            }
            if (obj != null) {
                for (ChatMsg chatMsg222 : values) {
                    Util.imStorageQueue.postRunnable(new jm(this, chatMsg222));
                }
            }
            Logger.getInstance().debug(UserChatManager.a, "Batch run", "更新最近会话列表完毕，准备展示 ");
            for (ChatMsg chatMsg2222 : arrayList6) {
                Iterator it = this.c.observers.iterator();
                while (it.hasNext()) {
                    ((IChatMsgListener) it.next()).onGroupMessageReceived(chatMsg2222);
                }
            }
            arrayList6.clear();
        }
    }

    public UserChatManager(String str, String str2) {
        this.i = str;
        this.b = str2;
        this.j = new ChatClientManager(this.i, str2);
        this.j.subscribe(b());
        this.j.setMessageCallback(this);
        this.j.setSyncMessageCallback(this);
        this.c = ChatMsgStore.getChatMsgStore(this.i);
        this.d = GroupChatMsgStore.getInstance(this.i);
        this.e = GroupNoticeStore.getInstance(this.i);
        this.g = ContactListItemStore.getContactStore(this.i);
        this.f = new SyncManager();
    }

    public static boolean isCurrentUser(String str) {
        if (currentChatManager == null || !currentChatManager.i.equals(str)) {
            return false;
        }
        return true;
    }

    public static synchronized UserChatManager getUserChatManager(String str, String str2) {
        UserChatManager userChatManager;
        synchronized (UserChatManager.class) {
            if (currentChatManager == null || !TextUtils.equals(currentChatManager.i, str)) {
                if (currentChatManager != null) {
                    currentChatManager.destroy(false);
                }
                currentChatManager = new UserChatManager(str, str2);
                userChatManager = currentChatManager;
            } else {
                if (!TextUtils.isEmpty(str2)) {
                    currentChatManager.a(str2);
                }
                userChatManager = currentChatManager;
            }
        }
        return userChatManager;
    }

    private static String a(String str, int i, int i2) {
        return ChatListAdapter.appendSmallSize(str, i, i2);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("empty password");
            return;
        }
        this.b = str;
        if (this.j != null) {
            this.j.setPassword(str);
        }
    }

    public void onStop() {
        Util.imStorageQueue.postRunnable(new jh(this));
    }

    public void destroy(boolean z) {
        LogUtil.d("destroy:");
        if (this.j == null) {
            return;
        }
        if (z) {
            try {
                this.j.disconnectLater(QsbkApp.getInstance().getAutoDisconnectTime());
                Logger.getInstance().debug(a, "destroy", String.format("%ss后主动断开登录", new Object[]{Integer.valueOf(QsbkApp.getInstance().getAutoDisconnectTime())}));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.f != null) {
            this.f.onDestroy();
        }
        this.j.disconnect();
    }

    public void mockReceiveGroupMessage() {
        TimeDelta timeDelta = new TimeDelta();
        for (int i = 0; i < 200; i++) {
            timeDelta.renew();
            String str = "{\"status\": 1, \"data\": \"" + i + "\", \"from\": \"12388257\", \"fromgender\": \"M\", \"ex_create\": 0, \"msgid\": \"b6af9baf2b8f73c2\", \"usertype\": 3, \"fromage\": 30, \"gnick\": \"\\u9ad8bigger\", \"toid\": \"12305659\", \"to\": \"9819\", \"fromicon\": \"20150129181618.jpg\", \"notify\": true, \"iscontent\": false, \"time\": 1441106933621, \"gid\": \"9819\", \"type\": 1, \"fromnick\": \"powerfj\", \"inout\": 2, \"phone_ver\": \"8.0.2\"}";
            try {
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.parseFromJSONObject(new JSONObject(str));
                chatMsg.time = System.currentTimeMillis();
                chatMsg.msgid = chatMsg._genMsgId();
                onGroupMessageReceived(chatMsg);
            } catch (Exception e) {
            }
            LogUtil.d("onGroupMsgReceived:" + timeDelta.getDelta());
        }
    }

    private List<String> b() {
        List<String> linkedList = new LinkedList();
        linkedList.add(USER_PREFIX + this.i);
        for (ChatGroup chatGroup : ChatGroup.getAllGroups()) {
            linkedList.add(GROUP_PREFIX + chatGroup.id);
        }
        return linkedList;
    }

    public int connect() {
        return this.j.connect();
    }

    public void connectLater() {
        this.j.connectLater();
    }

    public boolean isConnected() {
        return this.j != null && this.j.isConnected();
    }

    public void getConnectHost(IOnConnectHostResp iOnConnectHostResp) {
        this.j.getConnectHost(iOnConnectHostResp);
    }

    public void removeDisconnentEvent() {
        this.j.removeDisconnectEvent();
    }

    public boolean destroyConnectHost(IOnConnectHostResp iOnConnectHostResp) {
        return this.j.destroyConnectHost(iOnConnectHostResp);
    }

    public int getConnectStatus() {
        return this.j.getConnectStatus();
    }

    public List<ChatGroup> getGroups() {
        return ChatGroup.getAllGroups();
    }

    public String getUserName() {
        if (QsbkApp.currentUser != null) {
            return QsbkApp.currentUser.userName;
        }
        return null;
    }

    public String getGender() {
        if (QsbkApp.currentUser != null) {
            return QsbkApp.currentUser.gender;
        }
        return null;
    }

    public int getAge() {
        if (QsbkApp.currentUser != null) {
            return QsbkApp.currentUser.age;
        }
        return 0;
    }

    public String getUserIcon() {
        if (QsbkApp.currentUser != null) {
            return QsbkApp.currentUser.userIcon;
        }
        return null;
    }

    public void sendSystemMsg(String str, String str2) {
        ChatMsg chatMsg = new ChatMsg(8, str2);
        chatMsg.from(this.i).to(str);
        String format = String.format("s_%s", new Object[]{str});
        chatMsg.status = 1;
        chatMsg.uid = str;
        chatMsg.data = str2;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
    }

    public void sendUnkonwTypeMsg(String str, String str2) {
        ChatMsg chatMsg = new ChatMsg(99, str2);
        chatMsg.from(this.i).to(str);
        String format = String.format("s_%s", new Object[]{str});
        chatMsg.status = 1;
        chatMsg.uid = str;
        chatMsg.data = str2;
        chatMsg.fromicon = getUserIcon();
        chatMsg.fromnick = getUserName();
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
    }

    public ChatMsg newImageChatMsg(ContactListItem contactListItem, ChatMsgImageData chatMsgImageData, IMChatMsgSource iMChatMsgSource) {
        if (!TextUtils.isEmpty(contactListItem.id) && chatMsgImageData != null) {
            return a(contactListItem, chatMsgImageData, iMChatMsgSource, 0);
        }
        LogUtil.d("newImageChatMsg empty argument");
        return null;
    }

    public ChatMsg newGroupImageChatMsg(ContactListItem contactListItem, ChatMsgImageData chatMsgImageData, IMChatMsgSource iMChatMsgSource) {
        if (!TextUtils.isEmpty(contactListItem.id) && chatMsgImageData != null) {
            return a(contactListItem, chatMsgImageData, iMChatMsgSource, 3);
        }
        LogUtil.d("newImageChatMsg empty argument");
        return null;
    }

    private ChatMsg a(ContactListItem contactListItem, ChatMsgImageData chatMsgImageData, IMChatMsgSource iMChatMsgSource, int i) {
        BaseChatMsgStore baseChatMsgStore;
        String str = contactListItem.id;
        ChatMsg chatMsg = new ChatMsg(3, chatMsgImageData.encodeToJsonObject().toString());
        chatMsg.from(this.i).to(str);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        chatMsg.genMsgId();
        if (i == 3) {
            chatMsg.gid = str;
            baseChatMsgStore = this.d;
        } else {
            chatMsg.uid = str;
            baseChatMsgStore = this.c;
        }
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        chatMsg.inout = 2;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        contactListItem.mLastContent = "[图片]";
        baseChatMsgStore.saveMessage(chatMsg);
        updateContactListitem(chatMsg, contactListItem);
        return chatMsg;
    }

    public ChatMsg newVoiceChatMsg(ContactListItem contactListItem, ChatMsgVoiceData chatMsgVoiceData, IMChatMsgSource iMChatMsgSource, int i) {
        String str = contactListItem.id;
        if (TextUtils.isEmpty(str) || chatMsgVoiceData == null) {
            LogUtil.d("newImageChatMsg empty argument");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(4, chatMsgVoiceData.encodeToJsonObject().toString());
        chatMsg.from(this.i).to(str);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        chatMsg.genMsgId();
        if (i == 3) {
            chatMsg.gid = str;
            baseChatMsgStore = this.d;
        } else {
            chatMsg.uid = str;
            baseChatMsgStore = this.c;
        }
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.tag = chatMsgVoiceData;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        contactListItem.mLastContent = "[语音]";
        baseChatMsgStore.saveMessage(chatMsg);
        updateContactListitem(chatMsg, contactListItem);
        return chatMsg;
    }

    public void sendImageTo(ChatMsg chatMsg) {
        if (chatMsg == null) {
            LogUtil.d("empty argument");
            return;
        }
        String format;
        if (chatMsg.isGroupMsg()) {
            format = String.format("g_%s", new Object[]{chatMsg.gid});
        } else {
            format = String.format("s_%s", new Object[]{chatMsg.uid});
        }
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
    }

    public ChatMsg sendTo(ContactListItem contactListItem, String str, IMChatMsgSource iMChatMsgSource, boolean z) {
        return sendTo(contactListItem, str, null, 1, iMChatMsgSource, z);
    }

    public ChatMsg sendTo(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        return sendTo(contactListItem, str, str2, 1, iMChatMsgSource, z);
    }

    public ChatMsg sendPureEmotionTo(ContactListItem contactListItem, String str, IMChatMsgSource iMChatMsgSource) {
        return sendTo(contactListItem, str, null, 5, iMChatMsgSource, true);
    }

    public ChatMsg sendVoiceTo(ContactListItem contactListItem, String str, IMChatMsgSource iMChatMsgSource) {
        return sendTo(contactListItem, str, null, 4, iMChatMsgSource, true);
    }

    public ChatMsg sendTo(ContactListItem contactListItem, String str, String str2, int i, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str3 = contactListItem.id;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            LogUtil.d("empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        String format;
        ChatMsg chatMsg = new ChatMsg(i, str);
        chatMsg.from(this.i).to(str3);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        chatMsg.genMsgId();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str3;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
        } else {
            chatMsg.uid = str3;
            baseChatMsgStore = this.c;
        }
        chatMsg.fromicon = getUserIcon();
        chatMsg.fromnick = getUserName();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        chatMsg.at = str2;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        LogUtil.d("local msg id:" + chatMsg.dbid);
        Logger.getInstance().debug(a, "sendTo", String.format("Sendto...发送消息[%s]", new Object[]{chatMsg.toString()}));
        if (chatMsg.isGroupMsg()) {
            format = String.format("g_%s", new Object[]{str3});
        } else {
            format = String.format("s_%s", new Object[]{str3});
        }
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendWebShare(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z, int i) {
        ChatMsg chatMsg = null;
        String str3 = contactListItem.id;
        if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(str))) {
            BaseChatMsgStore baseChatMsgStore;
            if (i == ShareToImType.TYPE_WEB.getValue()) {
                chatMsg = new ChatMsg(23, str);
            } else if (i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue()) {
                chatMsg = new ChatMsg(29, str);
            } else if (i == ShareToImType.TYPE_NEWS.getValue()) {
                chatMsg = new ChatMsg(31, str);
            }
            chatMsg.from(this.i).to(str3);
            chatMsg.status = 1;
            chatMsg.time = IMTimer.getInstance().getCorrectTime();
            if (contactListItem.isGroupMsg()) {
                chatMsg.gid = str3;
                chatMsg.gnick = contactListItem.name;
                baseChatMsgStore = this.d;
                str3 = String.format("g_%s", new Object[]{str3});
            } else {
                chatMsg.uid = str3;
                baseChatMsgStore = this.c;
                str3 = String.format("s_%s", new Object[]{str3});
            }
            chatMsg.genMsgId();
            chatMsg.fromnick = getUserName();
            chatMsg.fromicon = getUserIcon();
            chatMsg.inout = 2;
            chatMsg.fromgender = getGender();
            chatMsg.fromage = getAge();
            if (i == ShareToImType.TYPE_NEWS.getValue()) {
                contactListItem.mLastContent = "[糗闻分享]" + str2;
            } else {
                contactListItem.mLastContent = "[分享]" + str2;
            }
            if (iMChatMsgSource != null) {
                chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
            }
            if (z) {
                baseChatMsgStore.saveMessage(chatMsg);
                updateContactListitem(chatMsg, contactListItem);
            }
            this.j.publish(str3, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        }
        return chatMsg;
    }

    public ChatMsg sendLiveShare(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str3 = contactListItem.id;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(25, str);
        chatMsg.from(this.i).to(str3);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str3;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str3 = String.format("g_%s", new Object[]{str3});
        } else {
            chatMsg.uid = str3;
            baseChatMsgStore = this.c;
            str3 = String.format("s_%s", new Object[]{str3});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        String str4 = "";
        try {
            str4 = new JSONObject(str).optString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        contactListItem.mLastContent = str4 + "正在直播，颜值爆表~快来一起看！";
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str3, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendQiushiShare(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str3 = contactListItem.id;
        Log.e("dsfdsfds", "id1=" + str3 + ",content1=" + str + ", contact:" + contactListItem.toString());
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            DebugUtil.debug("QiushiShare", "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(22, str);
        chatMsg.from(this.i).to(str3);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str3;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str3 = String.format("g_%s", new Object[]{str3});
        } else {
            chatMsg.uid = str3;
            baseChatMsgStore = this.c;
            str3 = String.format("s_%s", new Object[]{str3});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        contactListItem.mLastContent = "[糗事分享]" + str2;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str3, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendQiuyouCircleShare(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str3 = contactListItem.id;
        Log.e("dsfdsfds", "id1=" + str3 + ",content1=" + str + ", contact:" + contactListItem.toString());
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            DebugUtil.debug("QiushiShare", "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(27, str);
        chatMsg.from(this.i).to(str3);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str3;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str3 = String.format("g_%s", new Object[]{str3});
        } else {
            chatMsg.uid = str3;
            baseChatMsgStore = this.c;
            str3 = String.format("s_%s", new Object[]{str3});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        contactListItem.mLastContent = "[动态分享]" + str2;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str3, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendCircleTopicShare(ContactListItem contactListItem, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str3 = contactListItem.id;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            DebugUtil.debug("QiushiShare", "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(24, str);
        chatMsg.from(this.i).to(str3);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str3;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str3 = String.format("g_%s", new Object[]{str3});
        } else {
            chatMsg.uid = str3;
            baseChatMsgStore = this.c;
            str3 = String.format("s_%s", new Object[]{str3});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        contactListItem.mLastContent = "[话题分享]" + str2;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str3, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendQsjxShare(ContactListItem contactListItem, Qsjx qsjx, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str = contactListItem.id;
        if (TextUtils.isEmpty(str) || qsjx == null) {
            DebugUtil.debug(QYQShareInfo.TYPE_QSJX, "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(36, qsjx.toImDataJson());
        chatMsg.from(this.i).to(str);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str = String.format("g_%s", new Object[]{str});
        } else {
            chatMsg.uid = str;
            baseChatMsgStore = this.c;
            str = String.format("s_%s", new Object[]{str});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        contactListItem.mLastContent = "[糗事精选]" + qsjx.title;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendQiushiTopicShare(ContactListItem contactListItem, QiushiTopic qiushiTopic, IMChatMsgSource iMChatMsgSource, boolean z) {
        String str = contactListItem.id;
        if (TextUtils.isEmpty(str) || qiushiTopic == null) {
            DebugUtil.debug("qiushitopic", "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(37, qiushiTopic.toImDataJson());
        chatMsg.from(this.i).to(str);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            str = String.format("g_%s", new Object[]{str});
        } else {
            chatMsg.uid = str;
            baseChatMsgStore = this.c;
            str = String.format("s_%s", new Object[]{str});
        }
        chatMsg.genMsgId();
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        contactListItem.mLastContent = "[糗百爆社]" + qiushiTopic.content;
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        if (z) {
            baseChatMsgStore.saveMessage(chatMsg);
            updateContactListitem(chatMsg, contactListItem);
        }
        this.j.publish(str, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public ChatMsg sendLocalLaiseeMsg(ContactListItem contactListItem, Laisee laisee, IMChatMsgSource iMChatMsgSource) {
        String str = contactListItem.id;
        if (TextUtils.isEmpty(str) || laisee == null) {
            DebugUtil.debug(QYQShareInfo.TYPE_QSJX, "empty argument:");
            return null;
        }
        BaseChatMsgStore baseChatMsgStore;
        ChatMsg chatMsg = new ChatMsg(laisee instanceof LaiseeVoice ? 38 : 33, new LaiseeImInfo(laisee.id, laisee.secret, laisee.content).toJson());
        chatMsg.from(this.i).to(str);
        chatMsg.status = 1;
        chatMsg.time = IMTimer.getInstance().getCorrectTime();
        if (contactListItem.isGroupMsg()) {
            chatMsg.gid = str;
            chatMsg.gnick = contactListItem.name;
            baseChatMsgStore = this.d;
            String.format("g_%s", new Object[]{str});
        } else {
            chatMsg.uid = str;
            baseChatMsgStore = this.c;
            String.format("s_%s", new Object[]{str});
        }
        chatMsg.msgid = laisee.secret;
        chatMsg.fromnick = getUserName();
        chatMsg.fromicon = getUserIcon();
        chatMsg.inout = 2;
        chatMsg.fromgender = getGender();
        chatMsg.fromage = getAge();
        chatMsg.status = -1;
        contactListItem.mLastContent = chatMsg.getMsgTips();
        if (iMChatMsgSource != null) {
            chatMsg.msgsrc = iMChatMsgSource.encodeToJsonObject().toString();
        }
        baseChatMsgStore.saveMessage(chatMsg);
        updateContactListitem(chatMsg, contactListItem);
        return chatMsg;
    }

    public ChatMsg reSendFailMsg(ChatMsg chatMsg) {
        String format;
        LogUtil.d("local msg id:" + chatMsg.dbid);
        Logger.getInstance().debug(a, "reSendFailMsg", String.format("resend...重新发送消息[%s]", new Object[]{chatMsg.toString()}));
        if (chatMsg.isGroupMsg()) {
            format = String.format("g_%s", new Object[]{chatMsg.gid});
        } else {
            format = String.format("s_%s", new Object[]{chatMsg.uid});
        }
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), chatMsg.dbid);
        return chatMsg;
    }

    public void updateContactListitem(ChatMsg chatMsg, ContactListItem contactListItem) {
        ContactListItem withGroup;
        Object obj = null;
        int i = chatMsg.isGroupMsg() ? 3 : TextUtils.equals(chatMsg.uid, "32879940") ? 1 : 0;
        if (chatMsg.isGroupMsg()) {
            withGroup = this.g.getWithGroup(chatMsg.gid);
        } else {
            withGroup = this.g.getWithUser(chatMsg.to, i);
        }
        if (withGroup == null) {
            ContactListItem contactListItem2 = new ContactListItem();
            contactListItem2.id = chatMsg.to;
            contactListItem2.name = contactListItem.name;
            contactListItem2.icon = contactListItem.icon;
            withGroup = contactListItem2;
            obj = 1;
        }
        withGroup.inout = 2;
        withGroup.msgId = chatMsg.dbid;
        withGroup.type = i;
        withGroup.mimeType = chatMsg.type;
        withGroup.mLastContent = contactListItem.mLastContent;
        if (chatMsg.type == 1) {
            withGroup.mLastContent = chatMsg.data;
        } else if (chatMsg.type == 3) {
            withGroup.mLastContent = "[图片]";
        }
        withGroup.mLastUpdateTime = chatMsg.time;
        if (obj != null) {
            this.g.insert(withGroup);
        } else {
            this.g.update(withGroup);
        }
    }

    public void sendTypingStatus(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("empty argument:");
            return;
        }
        ChatMsg chatMsg = new ChatMsg(102);
        chatMsg.from(this.i).to(str);
        String format = String.format("s_%s", new Object[]{str});
        Logger.getInstance().debug(a, "sendTypingStatus", String.format("发送正在输入的消息[%s]", new Object[]{chatMsg.toString()}));
        this.j.publish(format, chatMsg.encodeToJsonObject().toString(), 0, 0);
    }

    public void sendReadedMsg(String str, JSONArray jSONArray, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 107);
            jSONObject.put("from", this.i);
            jSONObject.put("to", str);
            jSONObject.put("msgids", jSONArray);
            jSONObject.put("msgid", Md5.MD5_16("" + System.currentTimeMillis() + jSONArray));
            jSONObject.put("msgsrc", str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String format = String.format("s_%s", new Object[]{str});
        LogUtil.d("send msg readed msg:" + jSONObject.toString());
        Logger.getInstance().debug(a, "sendReadedMsg", String.format("发送已读消息[%s]", new Object[]{jSONObject.toString()}));
        long fakeLocalDbId = ChatMsg.getFakeLocalDbId();
        this.m.put(Long.valueOf(fakeLocalDbId), new Pair(str, fromJSONArray(jSONArray)));
        this.j.publish(format, jSONObject.toString(), 1, fakeLocalDbId);
    }

    public void sendSyncMsg(SyncMsg syncMsg) {
        if (syncMsg != null) {
            this.j.publish("p", syncMsg.toString(), 0, 0);
        }
    }

    public List<String> fromJSONArray(JSONArray jSONArray) {
        List<String> linkedList = new LinkedList();
        for (int i = 0; i < jSONArray.length(); i++) {
            linkedList.add(jSONArray.optString(i));
        }
        return linkedList;
    }

    public void sendReadedMsg(String str, List<String> list, IMChatMsgSource iMChatMsgSource) {
        sendReadedMsg(str, new JSONArray(list), iMChatMsgSource != null ? iMChatMsgSource.encodeToJsonObject().toString() : null);
    }

    private void a(ChatMsg chatMsg) {
        if ("wifi".equals(HttpUtils.getNetwork(QsbkApp.getInstance()))) {
            ChatMsgImageData chatMsgImageData = new ChatMsgImageData(chatMsg.data);
            IMImageSize imageSize = IMImageSizeHelper.getImageSize(Size.Medium, chatMsgImageData.width, chatMsgImageData.height, QsbkApp.getInstance());
            Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(a(chatMsgImageData.url, (imageSize.getDstWidth() / 3) + 1, (imageSize.getDstHeight() / 3) + 1)), QsbkApp.mContext);
        }
    }

    public void onMessageReceived(ChatMsg chatMsg) {
        boolean z = (chatMsg.type == 102 || TextUtils.isEmpty(chatMsg.seq_type) || !this.f.update(chatMsg.seq_type, chatMsg.uid, chatMsg.pre_seqid, chatMsg.this_seqid)) ? false : true;
        if (!z) {
            return;
        }
        if (chatMsg.type != 34 || chatMsg.getLaiseeLog().isAboutUser(QsbkApp.currentUser.userId)) {
            if (!(chatMsg == null || TextUtils.isEmpty(chatMsg.from) || !chatMsg.from.trim().equals(QiushiNotificationCountManager.QIUSHI_PUSH_UID))) {
                try {
                    if (!TextUtils.isEmpty(chatMsg.data)) {
                        JSONObject optJSONObject = new JSONObject(chatMsg.data).optJSONObject("jump_data");
                        String str = "";
                        if (optJSONObject != null) {
                            CharSequence optString = optJSONObject.optString("m_type");
                            if (!(TextUtils.isEmpty(optString) || !Type.QIUSHI_SMILE.equals(optString) || IMNotifyManager.isQiushiSmileNotify(QsbkApp.mContext))) {
                                return;
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            LogUtil.e("onMessageReceived");
            LogUtil.e("thread:" + Thread.currentThread().getId());
            if (chatMsg.type == 200) {
                e(chatMsg);
            }
            if (chatMsg.type == 202) {
                d(chatMsg);
                a(chatMsg, false);
                if (this.observers != null) {
                    Iterator it = this.observers.iterator();
                    while (it.hasNext()) {
                        IChatMsgListener iChatMsgListener = (IChatMsgListener) it.next();
                        if (chatMsg.isGroupMsg()) {
                            iChatMsgListener.onGroupMessageReceived(chatMsg);
                        } else {
                            iChatMsgListener.onMessageReceived(chatMsg);
                        }
                    }
                    return;
                }
                return;
            }
            if (chatMsg.type == 201) {
                c(chatMsg);
            }
            if (chatMsg.type == 3) {
                a(chatMsg);
            }
            if (!TextUtils.isEmpty(chatMsg.msgsrc)) {
                IMChatMsgSource msgSourceFromChatMsg = IMChatMsgSource.getMsgSourceFromChatMsg(chatMsg.msgsrc);
                if (msgSourceFromChatMsg != null && msgSourceFromChatMsg.type == 7) {
                    String str2 = msgSourceFromChatMsg.valueObj.group_id;
                    if (!TemporaryNoteUtils.isLoaded()) {
                        TemporaryNoteUtils.loadAll(new ji(this, str2, chatMsg));
                        return;
                    } else if (TemporaryNoteUtils.getPreferences().getBoolean(str2, true)) {
                        b(chatMsg);
                        return;
                    } else {
                        return;
                    }
                }
            }
            b(chatMsg);
        }
    }

    private void b(ChatMsg chatMsg) {
        boolean z = false;
        if (chatMsg.isContentMsg()) {
            LogUtil.e("receive content message");
            chatMsg.status = 4;
            LogUtil.e("msg time:" + chatMsg.time);
            LogUtil.e("save user message");
            chatMsg.inout = 1;
            chatMsg.uid = chatMsg.from;
            if (TextUtils.isEmpty(chatMsg.msgid) || !this.c.isChatMsgExist(chatMsg.msgid)) {
                if (this.c.saveMessage(chatMsg) > 0 && chatMsg.type <= 20) {
                    if (chatMsg.type == 20 && QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(chatMsg.from)) {
                        QiushiNotificationCountManager.getInstance(this.i).unread(chatMsg);
                    } else if (chatMsg.type == 20 && QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg.from)) {
                        QiuyouquanNotificationCountManager.getInstance(this.i).unread(chatMsg);
                    } else {
                        this.c.addUserTotalMsgUnread(1, true);
                    }
                }
                e(chatMsg);
                if (chatMsg.isContentMsg()) {
                    if (chatMsg.from.trim().equals(QiushiNotificationCountManager.QIUSHI_PUSH_UID) || chatMsg.from.trim().equals(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID)) {
                        chatMsg.notify = true;
                    }
                    a(chatMsg, false);
                }
            } else {
                z = true;
            }
        }
        if (chatMsg.type == 107 && !ArrayUtils.isEmpty(chatMsg.msgids) && chatMsg.type == 107) {
            this.c.updateMessageStateBatch(chatMsg.msgids, 5);
        }
        if (!(this.observers == null || r0)) {
            Iterator it = this.observers.iterator();
            while (it.hasNext()) {
                IChatMsgListener iChatMsgListener = (IChatMsgListener) it.next();
                if (chatMsg.isGroupMsg()) {
                    iChatMsgListener.onGroupMessageReceived(chatMsg);
                } else {
                    iChatMsgListener.onMessageReceived(chatMsg);
                }
            }
        }
        if (chatMsg.type == 28) {
            Intent intent = new Intent();
            intent.setAction(MainActivity.ACTION_RECEIVE_MEDAL_MSG);
            LocalBroadcastManager.getInstance(QsbkApp.mContext).sendBroadcast(intent);
            SharePreferenceUtils.setSharePreferencesValue("medal_tips", true);
        }
        if (chatMsg.type == 32) {
            String str = chatMsg.data;
            try {
                if (TextUtils.equals(new JSONObject(str).optString("t"), SpringFestivalUtil.HAMMER_QSJX)) {
                    Intent intent2 = new Intent(Constants.ACTION_SPRING_FESTIVAL_REMIND);
                    intent2.putExtra("data", str);
                    if (SpringFestivalUtil.needRemind()) {
                        PreferenceUtils.instance().putString(SpringFestivalUtil.KEY_SPRING_FESTIVAL, str);
                        LocalBroadcastManager.getInstance(QsbkApp.mContext).sendBroadcast(intent2);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (chatMsg.type == 33) {
            LaiseeImInfo laiseeInfo = chatMsg.getLaiseeInfo();
            if (laiseeInfo != null && laiseeInfo.isPop()) {
                Serializable laisee = new Laisee(laiseeInfo.laiseeId, laiseeInfo.secret, true);
                SharePreferenceUtils.setSharePreferencesValue(Laisee.SP_POP_LAISEE, laisee.toJson());
                intent = new Intent(Constants.ACTION_POP_LAISEE_WINDOW);
                intent.putExtra("laisee", laisee);
                LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(intent);
            }
        }
        if (chatMsg.type == 26) {
            LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(new Intent(Constants.ACTION_LIVE_BEGIN));
        }
    }

    private void c(ChatMsg chatMsg) {
        try {
            JSONArray jSONArray = new JSONObject(chatMsg.data).getJSONArray("delsession");
            if (chatMsg.usertype == 2) {
                this.g.updateUnarchiveCount(0);
                this.g.delete(chatMsg.from, 0);
                return;
            }
            int length = jSONArray.length();
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = jSONArray.optString(i);
                this.c.deleteMessagesWith(strArr[i]);
                ContactListItem withUser = this.g.getWithUser(strArr[i]);
                if (withUser != null) {
                    this.c.addUserTotalMsgUnread(-withUser.unreadCount, true);
                }
            }
            this.g.delete(strArr, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private synchronized void d(ChatMsg chatMsg) {
        Object obj = null;
        synchronized (this) {
            GroupNotice groupNotice = chatMsg.getGroupNotice();
            if (groupNotice.type == 10) {
                String valueOf = String.valueOf(groupNotice.tribe.id);
                this.c.deleteMessagesWith(valueOf);
                this.g.delete(valueOf, 3);
                Util.imStorageQueue.postRunnable(new jj(this, groupNotice));
            }
            this.e.insert(groupNotice);
            ContactListItem withGroupNotice = this.g.getWithGroupNotice(chatMsg.from);
            if (withGroupNotice == null) {
                withGroupNotice = new ContactListItem();
                withGroupNotice.type = 6;
                withGroupNotice.unreadCount = 0;
                withGroupNotice.status = 4;
                obj = 1;
            }
            withGroupNotice.id = chatMsg.from;
            withGroupNotice.name = chatMsg.fromnick;
            withGroupNotice.icon = chatMsg.fromicon;
            if (groupNotice.type != 9) {
                withGroupNotice.unreadCount++;
            }
            withGroupNotice.mLastContent = chatMsg.getGroupNotice().title;
            withGroupNotice.mLastUpdateTime = chatMsg.time;
            if (obj != null) {
                this.g.insert(withGroupNotice);
            } else {
                this.g.update(withGroupNotice);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void e(qsbk.app.im.ChatMsg r7) {
        /*
        r6 = this;
        r3 = 2;
        r0 = 1;
        monitor-enter(r6);
        r1 = r7.type;	 Catch:{ all -> 0x00da }
        r2 = 8;
        if (r1 != r2) goto L_0x000f;
    L_0x0009:
        r1 = r7.ex_create;	 Catch:{ all -> 0x00da }
        if (r1 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r6);
        return;
    L_0x000f:
        r1 = r7.type;	 Catch:{ all -> 0x00da }
        r2 = 34;
        if (r1 != r2) goto L_0x0023;
    L_0x0015:
        r1 = r7.getLaiseeLog();	 Catch:{ all -> 0x00da }
        r2 = qsbk.app.QsbkApp.currentUser;	 Catch:{ all -> 0x00da }
        r2 = r2.userId;	 Catch:{ all -> 0x00da }
        r1 = r1.isAboutUser(r2);	 Catch:{ all -> 0x00da }
        if (r1 == 0) goto L_0x000d;
    L_0x0023:
        r1 = r7.type;	 Catch:{ all -> 0x00da }
        r2 = 20;
        if (r1 != r2) goto L_0x00dd;
    L_0x0029:
        r1 = "21089551";
        r2 = r7.from;	 Catch:{ all -> 0x00da }
        r1 = r1.equals(r2);	 Catch:{ all -> 0x00da }
        if (r1 != 0) goto L_0x000d;
    L_0x0033:
        r1 = "27685144";
        r2 = r7.from;	 Catch:{ all -> 0x00da }
        r1 = r1.equals(r2);	 Catch:{ all -> 0x00da }
        if (r1 != 0) goto L_0x000d;
    L_0x003d:
        r1 = r7.usertype;	 Catch:{ all -> 0x00da }
        switch(r1) {
            case 1: goto L_0x0043;
            case 2: goto L_0x0042;
            case 3: goto L_0x0042;
            case 4: goto L_0x00e5;
            case 5: goto L_0x0042;
            case 6: goto L_0x00e8;
            default: goto L_0x0042;
        };	 Catch:{ all -> 0x00da }
    L_0x0042:
        r0 = 0;
    L_0x0043:
        r1 = new qsbk.app.im.ContactListItem;	 Catch:{ all -> 0x00da }
        r1.<init>();	 Catch:{ all -> 0x00da }
        r2 = r7.from;	 Catch:{ all -> 0x00da }
        r1.id = r2;	 Catch:{ all -> 0x00da }
        r1.type = r0;	 Catch:{ all -> 0x00da }
        r0 = r7.usertype;	 Catch:{ all -> 0x00da }
        if (r0 != r3) goto L_0x00f0;
    L_0x0052:
        r0 = 2;
        r1.type = r0;	 Catch:{ all -> 0x00da }
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00eb }
        r2 = r7.data;	 Catch:{ JSONException -> 0x00eb }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = r0.toString();	 Catch:{ JSONException -> 0x00eb }
        qsbk.app.utils.LogUtil.e(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = "new_count";
        r2 = r0.optInt(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.unreadCount = r2;	 Catch:{ JSONException -> 0x00eb }
        r2 = r6.g;	 Catch:{ JSONException -> 0x00eb }
        r3 = r1.unreadCount;	 Catch:{ JSONException -> 0x00eb }
        r2.updateUnarchiveCount(r3);	 Catch:{ JSONException -> 0x00eb }
        r2 = "new_fans_count";
        r3 = r1.unreadCount;	 Catch:{ JSONException -> 0x00eb }
        qsbk.app.utils.SharePreferenceUtils.setSharePreferencesValue(r2, r3);	 Catch:{ JSONException -> 0x00eb }
        r2 = new android.content.Intent;	 Catch:{ JSONException -> 0x00eb }
        r2.<init>();	 Catch:{ JSONException -> 0x00eb }
        r3 = "action_new_fans";
        r2.setAction(r3);	 Catch:{ JSONException -> 0x00eb }
        r3 = qsbk.app.QsbkApp.mContext;	 Catch:{ JSONException -> 0x00eb }
        r3 = android.support.v4.content.LocalBroadcastManager.getInstance(r3);	 Catch:{ JSONException -> 0x00eb }
        r3.sendBroadcast(r2);	 Catch:{ JSONException -> 0x00eb }
        r2 = "last_add_time";
        r2 = r0.optLong(r2);	 Catch:{ JSONException -> 0x00eb }
        r4 = r1.mLastUpdateTime;	 Catch:{ JSONException -> 0x00eb }
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00a0;
    L_0x0098:
        r2 = "last_add_time";
        r2 = r0.optLong(r2);	 Catch:{ JSONException -> 0x00eb }
        r1.mLastUpdateTime = r2;	 Catch:{ JSONException -> 0x00eb }
    L_0x00a0:
        r0 = r7.fromnick;	 Catch:{ all -> 0x00da }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00da }
        if (r0 != 0) goto L_0x00ac;
    L_0x00a8:
        r0 = r7.fromnick;	 Catch:{ all -> 0x00da }
        r1.name = r0;	 Catch:{ all -> 0x00da }
    L_0x00ac:
        r0 = r1.name;	 Catch:{ all -> 0x00da }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x00da }
        if (r0 == 0) goto L_0x00b8;
    L_0x00b4:
        r0 = r7.from;	 Catch:{ all -> 0x00da }
        r1.name = r0;	 Catch:{ all -> 0x00da }
    L_0x00b8:
        r0 = r7.isValidIcon();	 Catch:{ all -> 0x00da }
        if (r0 == 0) goto L_0x00c2;
    L_0x00be:
        r0 = r7.fromicon;	 Catch:{ all -> 0x00da }
        r1.icon = r0;	 Catch:{ all -> 0x00da }
    L_0x00c2:
        r0 = 1;
        r1.inout = r0;	 Catch:{ all -> 0x00da }
        r2 = r7.dbid;	 Catch:{ all -> 0x00da }
        r1.msgId = r2;	 Catch:{ all -> 0x00da }
        r0 = r7.type;	 Catch:{ all -> 0x00da }
        r1.mimeType = r0;	 Catch:{ all -> 0x00da }
        r0 = r7.getMsgTips();	 Catch:{ all -> 0x00da }
        r1.mLastContent = r0;	 Catch:{ all -> 0x00da }
        r0 = r6.g;	 Catch:{ all -> 0x00da }
        r0.insertOrReplace(r1);	 Catch:{ all -> 0x00da }
        goto L_0x000d;
    L_0x00da:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x00dd:
        r1 = r7.type;	 Catch:{ all -> 0x00da }
        r2 = 26;
        if (r1 != r2) goto L_0x003d;
    L_0x00e3:
        goto L_0x000d;
    L_0x00e5:
        r0 = 4;
        goto L_0x0043;
    L_0x00e8:
        r0 = 7;
        goto L_0x0043;
    L_0x00eb:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00da }
        goto L_0x00a0;
    L_0x00f0:
        r0 = r1.unreadCount;	 Catch:{ all -> 0x00da }
        r0 = r0 + 1;
        r1.unreadCount = r0;	 Catch:{ all -> 0x00da }
        r2 = r7.time;	 Catch:{ all -> 0x00da }
        r1.mLastUpdateTime = r2;	 Catch:{ all -> 0x00da }
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.UserChatManager.e(qsbk.app.im.ChatMsg):void");
    }

    public boolean isHostSetted() {
        return this.j.isHostSetted();
    }

    public void onSyncMsgReceived(ChatMsg chatMsg) {
        if (chatMsg != null && chatMsg.type == 401) {
            try {
                JSONArray jSONArray = new JSONArray(chatMsg.data);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        ChatMsg chatMsg2 = new ChatMsg();
                        chatMsg2.parseFromJSONObject(optJSONObject);
                        if (TextUtils.equals(Laisee.TYPE_P2P, chatMsg2.seq_type)) {
                            onMessageReceived(chatMsg2);
                        } else if (TextUtils.equals(Laisee.TYPE_TRIBE, chatMsg2.seq_type)) {
                            onGroupMessageReceived(chatMsg2);
                        } else if (TextUtils.equals("push", chatMsg2.seq_type)) {
                            onMessageReceived(chatMsg2);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSyncMsgMaintenance(ChatMsg chatMsg) {
        if (chatMsg != null && chatMsg.type == 400) {
            if (TextUtils.equals(Laisee.TYPE_P2P, chatMsg.seq_type)) {
                this.f.maintenance(chatMsg.seq_type, chatMsg.uid, chatMsg.pre_seqid, chatMsg.this_seqid);
            } else if (TextUtils.equals(Laisee.TYPE_TRIBE, chatMsg.seq_type)) {
                this.f.maintenance(chatMsg.seq_type, chatMsg.gid, chatMsg.pre_seqid, chatMsg.this_seqid);
            } else if (TextUtils.equals("push", chatMsg.seq_type)) {
                this.f.maintenance(chatMsg.seq_type, chatMsg.uid, chatMsg.pre_seqid, chatMsg.this_seqid);
            }
        }
    }

    public void onSyncMsgControl(ChatMsg chatMsg) {
        if (chatMsg != null && chatMsg.type == 402) {
            try {
                Object obj = chatMsg.data;
                if (!TextUtils.isEmpty(obj)) {
                    JSONObject jSONObject = new JSONObject(obj);
                    obj = jSONObject.optString("t");
                    Object optString = jSONObject.optString("v");
                    if (!TextUtils.isEmpty(obj) && !TextUtils.isEmpty(optString)) {
                        this.f.syncControl(obj, optString.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setChatContext(int i, String str) {
        LogUtil.d("set context:" + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        this.l.id = str;
        this.l.type = i;
    }

    private void a(ChatMsg chatMsg, boolean z) {
        boolean z2 = false;
        if (chatMsg.notify && chatMsg.type != 34) {
            LogUtil.e("notify msg by context:" + this.l.type);
            if (this.l.type == 3) {
                if (chatMsg.gid != null && chatMsg.gid.equals(this.l.id)) {
                    LogUtil.e("play in group contact");
                }
                z2 = true;
            } else if (this.l.type != 2) {
                if (this.l.type == 0) {
                    IMNotifyManager.instance().onChatMsgNotify(chatMsg, false);
                }
                z2 = true;
            } else if (chatMsg.isWith(this.l.id)) {
                LogUtil.e("play in user");
            } else {
                IMNotifyManager.instance().onChatMsgNotify(chatMsg, z);
                z2 = true;
            }
            if (!z2) {
            }
        }
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
        Object obj = (TextUtils.isEmpty(chatMsg.seq_type) || !this.f.update(chatMsg.seq_type, chatMsg.gid, chatMsg.pre_seqid, chatMsg.this_seqid)) ? null : 1;
        if (obj != null) {
            if (chatMsg.type != 34 || chatMsg.getLaiseeLog().isAboutUser(QsbkApp.currentUser.userId)) {
                this.h.a(chatMsg).a(100);
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return (',' + str.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "") + ',').contains(',' + str2 + ',');
    }

    private boolean f(ChatMsg chatMsg) {
        boolean z = true;
        if (QsbkApp.currentUser == null) {
            return false;
        }
        if (chatMsg.type == 8 && chatMsg.ex_create == 0) {
            return false;
        }
        boolean z2;
        ContactListItem withGroup = this.g.getWithGroup(chatMsg.gid);
        if (withGroup == null) {
            withGroup = new ContactListItem();
            withGroup.id = chatMsg.gid;
            withGroup.type = 3;
            z2 = true;
        } else {
            z2 = false;
        }
        if (withGroup.atMsgId == 0 && chatMsg.at != null) {
            if (a(chatMsg.at, QsbkApp.currentUser.userId) || chatMsg.isAtAll()) {
                withGroup.atMsgId = chatMsg.dbid;
            }
            if (chatMsg.isAtAll()) {
                withGroup.atType = 1;
            } else {
                withGroup.atType = 0;
            }
        }
        String str = withGroup.name;
        String str2 = withGroup.icon;
        GroupInfo joinedGroupFromLocal = JoinedGroupGetter.getJoinedGroupFromLocal(withGroup.id);
        if (joinedGroupFromLocal != null) {
            str2 = joinedGroupFromLocal.icon;
            str = joinedGroupFromLocal.name;
        }
        a(withGroup, chatMsg, str2, str, z2);
        if (withGroup.atMsgId == 0) {
            z = false;
        }
        return z;
    }

    private void a(ContactListItem contactListItem, ChatMsg chatMsg, String str, String str2, boolean z) {
        if (chatMsg.type <= 10) {
            contactListItem.unreadCount++;
        }
        contactListItem.mLastUpdateTime = chatMsg.time;
        contactListItem.inout = 1;
        contactListItem.msgId = chatMsg.dbid;
        contactListItem.mimeType = chatMsg.type;
        contactListItem.mLastContent = chatMsg.getGroupMsgTips();
        contactListItem.icon = str;
        contactListItem.name = str2;
        if (z) {
            this.g.insert(contactListItem);
        } else {
            this.g.update(contactListItem);
        }
    }

    public void onChatMsgStatusChanged(long j, int i) {
        LogUtil.d("dbid:" + j + " state:" + i);
        if (j > ChatMsg.MIN_GROUP_DB_ID) {
            this.d.updateMessageState(j, i);
        } else if (j > 0) {
            this.c.updateMessageState(j, i);
        }
        if (j < 0) {
            LogUtil.d("dbid:" + j);
            Pair pair = (Pair) this.m.get(Long.valueOf(j));
            if (pair != null) {
                if (i == 2) {
                    LogUtil.d("发送已读消息成功");
                    MessageReadManager.instance().removeUnReadMsgIds(this.i, (String) pair.first, (List) pair.second);
                } else if (i == 3) {
                    LogUtil.d("发送已读消息失败");
                    TimeDelta timeDelta = new TimeDelta();
                    MessageReadManager.instance().addUnreadMsgIds(this.i, (String) pair.first, (List) pair.second);
                    LogUtil.d("time delta:" + timeDelta.getDelta());
                }
                this.m.remove(Long.valueOf(j));
            } else {
                LogUtil.d("找不到dbid为" + j + " 的消息");
            }
        }
        if (this.observers != null) {
            Iterator it = this.observers.iterator();
            while (it.hasNext()) {
                ((IChatMsgListener) it.next()).onChatMsgStatusChanged(j, i);
            }
        }
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
        if (this.observers != null) {
            Iterator it = this.observers.iterator();
            while (it.hasNext()) {
                ((IChatMsgListener) it.next()).onDuplicateConnect(chatMsg);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnectStatusChange(int r9) {
        /*
        r8 = this;
        r7 = 2;
        r0 = r8.observers;
        r0 = qsbk.app.utils.comm.ArrayUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0060;
    L_0x0009:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "send to observers:";
        r0 = r0.append(r1);
        r1 = r8.observers;
        r0 = r0.append(r1);
        r0 = r0.toString();
        qsbk.app.utils.LogUtil.d(r0);
        r0 = qsbk.app.utils.image.issue.Logger.getInstance();
        r1 = a;
        r2 = "onConnectStatusChange";
        r3 = "onConnectStatusChange[ %s, %s]";
        r4 = new java.lang.Object[r7];
        r5 = 0;
        r6 = java.lang.Integer.valueOf(r9);
        r4[r5] = r6;
        r5 = 1;
        r6 = connectString;
        r6 = r6[r9];
        r4[r5] = r6;
        r3 = java.lang.String.format(r3, r4);
        r0.debug(r1, r2, r3);
        r0 = r8.observers;
        r1 = r0.iterator();
    L_0x0048:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0058;
    L_0x004e:
        r0 = r1.next();
        r0 = (qsbk.app.im.IChatMsgListener) r0;
        r0.onConnectStatusChange(r9);
        goto L_0x0048;
    L_0x0058:
        r0 = r8.observers;
        r0 = r0.size();
        if (r0 != 0) goto L_0x0060;
    L_0x0060:
        if (r9 != r7) goto L_0x0072;
    L_0x0062:
        r0 = r8.f;
        if (r0 == 0) goto L_0x006b;
    L_0x0066:
        r0 = r8.f;
        r0.init();
    L_0x006b:
        r8.d();
        r8.c();
    L_0x0071:
        return;
    L_0x0072:
        r0 = 3;
        if (r9 == r0) goto L_0x007b;
    L_0x0075:
        r0 = 5;
        if (r9 == r0) goto L_0x007b;
    L_0x0078:
        r0 = 6;
        if (r9 != r0) goto L_0x0071;
    L_0x007b:
        r0 = r8.f;
        if (r0 == 0) goto L_0x0071;
    L_0x007f:
        r0 = r8.f;
        r0.onConnectLost();
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.UserChatManager.onConnectStatusChange(int):void");
    }

    private void c() {
        JSONObject failedReadedMsg = MessageReadManager.instance().getFailedReadedMsg(this.i);
        if (failedReadedMsg != null) {
            Iterator keys = failedReadedMsg.keys();
            int i = 0;
            while (keys.hasNext()) {
                String str = (String) keys.next();
                i++;
                JSONArray optJSONArray = failedReadedMsg.optJSONArray(str);
                String optString = failedReadedMsg.optString("msgsrc", null);
                LogUtil.d("resend fail msg to " + str + " array:" + optJSONArray.toString());
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    Util.imStorageQueue.postRunnable(new jk(this, str, optJSONArray, optString), (long) (i * 200));
                }
            }
        }
    }

    private void d() {
        List sendFailMsg = ChatMsgStoreProxy.newInstance(this.i, 3).getSendFailMsg();
        for (int i = 0; i < sendFailMsg.size(); i++) {
            ChatMsg chatMsg = (ChatMsg) sendFailMsg.get(i);
            chatMsg.time = IMTimer.getInstance().getCorrectTime();
            Util.imStorageQueue.postRunnable(new jl(this, chatMsg), (long) (i * 300));
        }
    }

    public void addObserver(IChatMsgListener iChatMsgListener) {
        if (!this.observers.contains(iChatMsgListener)) {
            this.observers.add(iChatMsgListener);
        }
    }

    public void removeObserver(IChatMsgListener iChatMsgListener) {
        this.observers.remove(iChatMsgListener);
    }
}
