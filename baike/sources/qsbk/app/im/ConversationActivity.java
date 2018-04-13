package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qiushibaike.statsdk.StatSDK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.NoAvailableSpaceActivity;
import qsbk.app.activity.base.CommDialogActivity;
import qsbk.app.activity.group.SplashGroup;
import qsbk.app.adapter.IMMoreAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.im.CollectEmotion.CollectImageDomain;
import qsbk.app.im.CollectEmotion.CollectionManager;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.DraftStore;
import qsbk.app.im.datastore.LatestUsedCollectionStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.model.Laisee;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;

public class ConversationActivity extends IMChatBaseActivityEx {
    public static final String KEY_IMG_URI = "TAKE_PHOTO_IMG_URI";
    public static final String RELATIONSHIP = "relationship";
    public static final String TEMPORARY = "temporary";
    private static final String ad = ConversationActivity.class.getSimpleName();
    boolean a = false;
    private final BroadcastReceiver ae = new bo(this);
    private final BroadcastReceiver af = new cl(this);
    private final a ag = new a(this, this.y);
    private DraftStore ah = null;
    private final BroadcastReceiver ai = new ct(this);
    private BaseChatMsgStore aj = null;
    private ContactListItemStore ak = null;
    private ChatMsgListenerWrapper al = null;
    private View am;
    private TextView an;
    private TextView ao;
    private TextView ap;
    private LocalBroadcastManager aq;
    private long ar = Long.MAX_VALUE;
    private boolean as = true;
    private final Runnable at = new cu(this);
    private Runnable au = new cv(this);
    boolean b = true;
    TextWatcher c = new cs(this);
    public boolean isTemporary = false;
    public Relationship mRelationship = Relationship.FAN;

    private final class a implements Runnable {
        List<ChatMsg> a = new ArrayList();
        Handler b;
        final /* synthetic */ ConversationActivity c;

        public a(ConversationActivity conversationActivity, Handler handler) {
            this.c = conversationActivity;
            this.b = handler;
        }

        a a(ChatMsg chatMsg) {
            if (!this.a.contains(chatMsg)) {
                this.a.add(chatMsg);
            }
            return this;
        }

        void a() {
            this.b.removeCallbacks(this);
        }

        void a(long j) {
            a();
            if (j <= 0) {
                this.b.post(this);
            } else {
                this.b.postDelayed(this, j);
            }
        }

        public void run() {
            List<ChatMsg> arrayList = new ArrayList(this.a.size());
            List arrayList2 = new ArrayList(this.a.size());
            arrayList.addAll(this.a);
            for (ChatMsg chatMsg : arrayList) {
                this.c.g.removeSendingMsg(false);
                this.c.g.appendItem(chatMsg);
                chatMsg.status = 5;
                arrayList2.add(Long.valueOf(chatMsg.dbid));
                this.c.z.sendReadedMsg(chatMsg.from, ArrayUtils.from(new String[]{chatMsg.msgid}), this.c.s);
            }
            this.c.g.notifyDataSetChanged();
            Util.imStorageQueue.postRunnable(new db(this, arrayList2));
            this.c.d.setListSelection(this.c.g.getCount());
            this.c.aj.addUserTotalMsgUnread(-this.a.size(), true);
            this.a.clear();
            Logger.getInstance().debug(ConversationActivity.ad, "run", "onMsgReceived 真正刷新界面..");
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "你暂未登录，请登录之后再打开小纸条", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(this);
            finish();
            return;
        }
        B();
        this.Y = this.X;
    }

    private void B() {
        if (getIntent().getBooleanExtra(IMChatBaseActivityEx.REMOVE_NOTIFICATION, false)) {
            IMNotifyManager.instance().cleanNotification();
        }
    }

    protected void onResume() {
        LogUtil.d("set chat context user");
        StatSDK.onEvent(this, "Conversation", "resume");
        this.z.setChatContext(2, getToId());
        super.onResume();
        C();
    }

    private void C() {
        int firstVisiblePosition = this.d.getFirstVisiblePosition();
        int lastVisiblePosition = this.d.getLastVisiblePosition();
        for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
            if (this.d.getAdapter().getItemViewType(i) == 6) {
                this.d.getAdapter().getView(i, this.d.getChildAt(i - firstVisiblePosition), this.d);
            }
        }
    }

    public void onLongClickDialog(String str, long j) {
        Intent intent = new Intent(this, CommDialogActivity.class);
        int[] iArr = new int[]{D, E};
        intent.putExtra(CommDialogActivity.KEY_ITEMS, new String[]{"复制", "删除"});
        intent.putExtra(CommDialogActivity.KEY_ACTIONS, iArr);
        this.T = j;
        this.S = str;
        LogUtil.d("string to copy is:" + this.S);
        startActivityForResult(intent, C);
    }

    public void onRichMediaContentLongClick(long j, ChatMsg chatMsg) {
        this.T = j;
        Intent intent;
        int[] iArr;
        if (chatMsg.type != 3) {
            intent = new Intent(this, CommDialogActivity.class);
            iArr = new int[]{E};
            intent.putExtra(CommDialogActivity.KEY_ITEMS, new String[]{"删除"});
            intent.putExtra(CommDialogActivity.KEY_ACTIONS, iArr);
            LogUtil.d("delete image msg " + j);
            startActivityForResult(intent, C);
        } else if (chatMsg.inout == 1) {
            intent = new Intent(this, CommDialogActivity.class);
            iArr = new int[]{F, E};
            intent.putExtra(CommDialogActivity.KEY_ITEMS, new String[]{"添加到表情", "删除"});
            intent.putExtra(CommDialogActivity.KEY_ACTIONS, iArr);
            this.U = IMChatBaseActivityEx.b(chatMsg.data).url;
            LogUtil.d("delete image msg " + j);
            startActivityForResult(intent, C);
        } else if (IMChatBaseActivityEx.b(chatMsg.data).status == 1) {
            intent = new Intent(this, CommDialogActivity.class);
            iArr = new int[]{F, E};
            intent.putExtra(CommDialogActivity.KEY_ITEMS, new String[]{"添加到表情", "删除"});
            intent.putExtra(CommDialogActivity.KEY_ACTIONS, iArr);
            this.U = IMChatBaseActivityEx.b(chatMsg.data).url;
            LogUtil.d("delete image msg " + j);
            startActivityForResult(intent, C);
        } else {
            intent = new Intent(this, CommDialogActivity.class);
            iArr = new int[]{E};
            intent.putExtra(CommDialogActivity.KEY_ITEMS, new String[]{"删除"});
            intent.putExtra(CommDialogActivity.KEY_ACTIONS, iArr);
            LogUtil.d("delete image msg " + j);
            startActivityForResult(intent, C);
        }
    }

    private void D() {
        Util.imStorageQueue.postRunnable(new cw(this));
    }

    private void a(long j) {
        this.g.removeMsgById(j);
        Util.imStorageQueue.postRunnable(new cx(this, j));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != 6) {
            if (i == 102 && intent != null) {
                Laisee laisee = (Laisee) intent.getSerializableExtra("laisee");
                if (laisee != null) {
                    sendLaiseeLocal(newContactItem(), laisee, this.s);
                }
            }
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            finish();
        }
    }

    protected void onPause() {
        LogUtil.d("set chat context hide");
        hideEmojiAfterClickItem();
        this.z.setChatContext(0, null);
        super.onPause();
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.activity_im_conversation;
    }

    protected void a(Bundle bundle) {
        this.J = (InputMethodManager) getSystemService("input_method");
        this.aq = LocalBroadcastManager.getInstance(this);
        this.aq.registerReceiver(this.ai, new IntentFilter(NoAvailableSpaceActivity.ACTION_NO_AVAILABLE_SPACE_EXIT));
        this.aq.registerReceiver(this.af, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        this.aq.registerReceiver(this.ae, new IntentFilter(Constants.ACTION_SEND_VOICE_LAISEE_TOO));
        super.a(bundle);
        if (bundle != null) {
            Object string = bundle.getString("TAKE_PHOTO_IMG_URI");
            if (!TextUtils.isEmpty(string)) {
                this.Q = Uri.parse(string);
            }
        }
        this.isTemporary = getIntent().getBooleanExtra(TEMPORARY, false);
        CharSequence userId = getUserId();
        if (!TextUtils.isEmpty(userId) || QsbkApp.currentUser == null) {
            CharSequence charSequence = userId;
        } else {
            String str = QsbkApp.currentUser.userId;
        }
        if (TextUtils.isEmpty(str)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "你暂未登录，请登录之后再打开小纸条", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(this);
            finish();
            return;
        }
        MessageCountManager.getMessageCountManager(str);
        LogUtil.d("userid:" + str);
        this.am = findViewById(R.id.relationship_bar);
        this.an = (TextView) findViewById(R.id.black_report);
        this.ao = (TextView) findViewById(R.id.follow);
        this.ap = (TextView) findViewById(R.id.tips);
        this.d = (ScrollTopListView) findViewById(R.id.id_content);
        this.i = (GridView) findViewById(R.id.more_container);
        E();
        this.e = (LinearLayout) findViewById(R.id.sendlayout);
        this.f = (ImageView) findViewById(R.id.send);
        g();
        if (getUserType() == 4) {
            this.am.setVisibility(8);
            this.ap.setVisibility(8);
            this.e.setVisibility(8);
        } else if (getUserType() != 1) {
            this.an.setOnClickListener(new cz(this));
            this.ao.setOnClickListener(new bp(this));
            if (getIntent().getSerializableExtra(RELATIONSHIP) != null) {
                this.mRelationship = (Relationship) getIntent().getSerializableExtra(RELATIONSHIP);
                J();
            } else {
                Relationship relationship = RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).getRelationship(getToId());
                if (relationship != null) {
                    this.mRelationship = relationship;
                    J();
                } else {
                    new HttpTask(RELATIONSHIP, String.format(Constants.REL_GET_RELATIONSHIP, new Object[]{str, getToId()}), new br(this)).execute(new Void[0]);
                }
            }
        } else if (getToId().equals("32879940")) {
            this.am.setVisibility(8);
            this.ap.setVisibility(8);
            this.e.setVisibility(0);
        } else {
            this.am.setVisibility(8);
            this.ap.setVisibility(8);
            this.e.setVisibility(8);
        }
        this.s = j();
        if (!(this.mRelationship == Relationship.FRIEND || this.s == null || this.s.type != 7)) {
            this.A.setSubTitle(this.s.getPresentText());
        }
        this.z = UserChatManager.getUserChatManager(getUserId(), QsbkApp.currentUser.token);
        if (this.z.isHostSetted()) {
            Logger.getInstance().debug(ad, "init", "chatManager.connect()");
            this.z.connect();
            onConnectStatusChange(this.z.getConnectStatus());
        } else {
            Logger.getInstance().debug(ad, "init", "chatManager.getConnectHost(this)");
            this.z.getConnectHost(this);
            onConnectStatusChange(this.z.getConnectStatus());
        }
        this.aj = ChatMsgStoreProxy.newInstance(str, 0);
        this.ah = DraftStore.getDraftStore(str);
        this.ak = ContactListItemStore.getContactStore(str);
        this.aa = CollectionManager.getInstance(str);
        this.R = LatestUsedCollectionStore.getCollectionStore(str);
        this.al = new ChatMsgListenerWrapper(this, this.y);
        this.x = new VoiceUIHelper(this);
        if (getUserType() == 1 && getToId().equals("32879940")) {
            this.x.setSendVoiceButtonVisibility(false);
        }
        F();
    }

    private void E() {
        this.i.setAdapter(new IMMoreAdapter());
        this.i.setOnItemClickListener(new bs(this));
    }

    private void F() {
        K();
    }

    private void G() {
        findViewById(R.id.loading_layout).setVisibility(8);
    }

    protected void onStart() {
        if (!this.ab) {
            H();
        }
        this.ab = false;
        super.onStart();
    }

    private void H() {
        if (this.g != null && this.aj != null) {
            Util.imStorageQueue.postRunnable(new bt(this));
        }
    }

    protected void onDestroy() {
        if (this.z != null) {
            this.z.destroyConnectHost(this);
        }
        if (this.aq != null) {
            if (this.ai != null) {
                this.aq.unregisterReceiver(this.ai);
            }
            if (this.af != null) {
                this.aq.unregisterReceiver(this.af);
            }
            if (this.ae != null) {
                this.aq.unregisterReceiver(this.ae);
            }
        }
        this.ab = true;
        super.onDestroy();
    }

    public void setContentWithoutSendingTypingStatus(String str) {
        this.G.removeTextChangedListener(this.c);
        this.G.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.G.setSelection(str.length());
        }
        this.G.addTextChangedListener(this.c);
    }

    private void a(List<ChatMsg> list) {
        Util.imStorageQueue.postRunnable(new bv(this, list));
    }

    private void I() {
        Util.imStorageQueue.postRunnable(new ca(this));
    }

    private void J() {
        int i = 8;
        if (this.mRelationship == Relationship.FRIEND && this.isTemporary) {
            this.isTemporary = false;
            this.s = null;
        }
        switch (cr.a[this.mRelationship.ordinal()]) {
            case 1:
            case 2:
                this.am.setVisibility(8);
                this.ap.setVisibility(8);
                return;
            case 5:
                this.am.setVisibility(8);
                TextView textView = this.ap;
                if (!this.isTemporary) {
                    i = 0;
                }
                textView.setVisibility(i);
                return;
            default:
                this.am.setVisibility(0);
                this.ap.setVisibility(8);
                return;
        }
    }

    private void K() {
        this.a = true;
        ContactListItem withUser = this.ak.getWithUser(getToId());
        this.g = new ChatListAdapter(this, withUser == null ? getToIcon() : withUser.icon, this);
        G();
        I();
        this.d.setTopLoadingView(getInflater().inflate(R.layout.widget_chat_list_head, null), UIHelper.getDimensionPixelSize(this, R.dimen.im_head_scroll_height));
        this.d.setOnTouchListener(new cb(this));
        this.d.setOnPullListener(new cc(this));
        this.d.setAdapter(this.g);
        Util.imStorageQueue.postRunnable(new cf(this));
        this.f.setOnClickListener(new cj(this));
        this.f.setVisibility(8);
        this.G = (EditText) findViewById(R.id.addCmtEditText);
        this.G.addTextChangedListener(this.c);
        this.G.setOnEditorActionListener(new cm(this));
        this.G.setOnClickListener(new cn(this));
        L();
        w();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.Q != null) {
            bundle.putString("TAKE_PHOTO_IMG_URI", this.Q.toString());
        }
    }

    private void L() {
        ChatMsg userDraft = this.ah.getUserDraft(getToId());
        if (userDraft != null) {
            setContentWithoutSendingTypingStatus(userDraft.data);
            this.x.setSendVoiceButtonVisibility(false);
            this.f.setVisibility(0);
            O();
        }
    }

    public void finish() {
        N();
        UIHelper.hideKeyboard(this);
        if (isTaskRoot()) {
            startActivity(new Intent(this, SplashGroup.class));
        }
        super.finish();
    }

    private void M() {
        this.ah.delete(getToId());
    }

    private void N() {
        if (this.G != null) {
            String trim = this.G.getText().toString().trim();
            String toId = getToId();
            LogUtil.d("try save draft:" + trim);
            LogUtil.d("toid:" + toId);
            if (TextUtils.isEmpty(trim)) {
                this.ah.delete(toId);
            } else if (this.ah.getUserDraft(toId) != null) {
                this.ah.update(toId, trim, System.currentTimeMillis());
            } else {
                this.ah.insert(toId, trim, System.currentTimeMillis());
            }
        }
    }

    private void O() {
        TextView textView = (TextView) findViewById(R.id.remain_txt);
        int length = getContent().length();
        if (length > 3500) {
            textView.setText(String.valueOf(3500 - length));
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
        textView.setText("");
    }

    public void sendTypingStatus() {
        if (this.b) {
            this.z.sendTypingStatus(getToId());
            this.b = false;
        }
    }

    public void onMessageReceived(ChatMsg chatMsg) {
        if (this.a) {
            LogUtil.d("chat list on message received");
            if (this.ap != null && this.ap.getVisibility() == 0) {
                this.ap.setVisibility(8);
            }
            if (chatMsg.from != null && chatMsg.from.equals(getToId())) {
                if (chatMsg.type == 102) {
                    P();
                    this.g.addSendingMsg(chatMsg);
                } else if (chatMsg.type == 1 || chatMsg.type == 4 || chatMsg.type == 3 || chatMsg.type == 10 || chatMsg.isContentMsg()) {
                    Logger.getInstance().debug(ad, "onMessageReceived", "onMsgReceived 添加到队列里面去");
                    this.ag.a(chatMsg).a(100);
                } else if (chatMsg.type == 107) {
                    Logger.getInstance().debug(ad, "onMessageReceived", "onMessageReceived on message readed:" + ArrayUtils.join(chatMsg.msgids, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + " . " + chatMsg);
                    this.g.onMsgStateChanged(chatMsg.msgids, 5);
                } else if (chatMsg.type == 8) {
                    LogUtil.d("on message sended:" + ArrayUtils.join(chatMsg.msgids, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP));
                    this.g.addSystemMsg(chatMsg);
                }
            }
        }
    }

    private void P() {
        this.y.removeCallbacks(this.au);
        this.y.postDelayed(this.au, 18000);
    }

    public void uploaded(ChatMsg chatMsg) {
        Logger.getInstance().debug(ad, "uploaded", " msg " + chatMsg.toString());
        this.z.sendImageTo(chatMsg);
        this.aj.updateMessageData(chatMsg);
    }

    public void repeatUploaded(ChatMsg chatMsg) {
        this.z.sendImageTo(chatMsg);
        this.aj.updateMessageData(chatMsg);
    }

    private void a(int i, int i2) {
        String format = String.format(Constants.REL_FOLLOW, new Object[]{getUserId()});
        Map hashMap = new HashMap();
        hashMap.put("uid", getToId());
        hashMap.put("shake_time", Integer.valueOf(i));
        hashMap.put("shake_count", Integer.valueOf(i2));
        if (this.s != null) {
            hashMap.put("come_from", this.s.encodeToJsonObject().toString());
        }
        HttpTask httpTask = new HttpTask("follow", format, new co(this));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    private void Q() {
        if (this.ak != null) {
            int i;
            ContactListItem withUser = this.ak.getWithUser(getToId(), getUserType());
            if (withUser == null) {
                withUser = newContactItem();
                i = 1;
            } else {
                i = 0;
            }
            withUser.inout = 1;
            withUser.msgId = 0;
            withUser.mimeType = 1;
            withUser.mLastContent = "";
            withUser.mLastUpdateTime = System.currentTimeMillis();
            if (i != 0) {
                this.ak.insert(withUser);
            } else {
                this.ak.update(withUser);
            }
        }
    }

    public void onDeleteMsg() {
        if (this.T > 0) {
            a(this.T);
        }
    }

    public void onSavePhotoSucc(CollectImageDomain collectImageDomain) {
        long insert = this.R.insert(new LatestUsedCollectionData(collectImageDomain));
        this.aa.getAll();
        if (insert <= 0) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "保存历史记录失败", Integer.valueOf(0)).show();
        } else if (t() && this.n.isShown()) {
            int size = this.aa.feachAll().size();
            if (size != 0) {
                this.o.setNewDate(collectImageDomain);
                if (size == 1) {
                    this.m.setVisibility(4);
                } else {
                    this.m.setVisibility(0);
                }
            }
        }
    }

    public void hideEmojiAfterClickItem() {
        this.Z = false;
    }

    public void hideEmojiAfterDeleteItemOrUploaded() {
        z();
    }

    public ChatMsg sendEmotion(ChatMsgEmotionData chatMsgEmotionData) {
        ContactListItem newContactItem = newContactItem();
        newContactItem.mLastContent = chatMsgEmotionData.name;
        return this.z.sendPureEmotionTo(newContactItem, chatMsgEmotionData.encodeToJsonObject().toString(), this.s);
    }

    public ContactListItem newContactItem() {
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.id = getToId();
        contactListItem.name = getToNick();
        contactListItem.icon = getToIcon();
        contactListItem.type = getUserType();
        return contactListItem;
    }

    protected void g() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);
        this.A = new ConversationTitleBar(this);
        supportActionBar.setCustomView(this.A, new LayoutParams(-1, -1));
        ViewParent parent = this.A.getParent();
        if (parent != null && (parent instanceof Toolbar)) {
            ((Toolbar) parent).setContentInsetsAbsolute(0, 0);
        }
        if (getUserType() == 4) {
            this.A.setMenuItemVisible(false);
        } else {
            this.A.setMenuItemVisible(true);
            this.A.setMenuItemClickListener(new cp(this));
        }
        Object remark = RemarkManager.getRemark(getToId());
        String toNick = getToNick();
        if (TextUtils.isEmpty(toNick)) {
            toNick = getToId();
            if (this.ak != null) {
                ContactListItem withUser = this.ak.getWithUser(toNick);
                if (withUser != null) {
                    toNick = withUser.name;
                }
            }
        }
        if (TextUtils.isEmpty(remark)) {
            this.A.setCenterText(toNick);
        } else {
            this.A.setCenterText(remark);
        }
        this.A.setLeftText("消息");
    }

    public void setRemarkForCumstomBar() {
        Object remark = RemarkManager.getRemark(getToId());
        if (!TextUtils.isEmpty(remark) && this.A != null) {
            this.A.setCenterText(remark);
        }
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        if (!this.isTemporary) {
            switch (cr.a[this.mRelationship.ordinal()]) {
                case 5:
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "对方回应后才可发送...", Integer.valueOf(0)).show();
                    return;
            }
        }
        super.onEmotionItemClick(i, chatMsgEmotionData);
    }

    public void onCollectItemClick(int i, LatestUsedCollectionData latestUsedCollectionData) {
        if (!this.isTemporary) {
            switch (cr.a[this.mRelationship.ordinal()]) {
                case 5:
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "对方回应后才可发送...", Integer.valueOf(0)).show();
                    return;
            }
        }
        super.onCollectItemClick(i, latestUsedCollectionData);
    }

    public void sendLaiseeLocal(ContactListItem contactListItem, Laisee laisee, IMChatMsgSource iMChatMsgSource) {
        ChatMsg sendLocalLaiseeMsg = this.z.sendLocalLaiseeMsg(contactListItem, laisee, iMChatMsgSource);
        if (sendLocalLaiseeMsg != null) {
            this.g.appendItem(sendLocalLaiseeMsg);
            this.g.moveSendingToLast();
            this.g.notifyDataSetChanged();
            this.y.postDelayed(new cq(this), 200);
            if (!this.isTemporary) {
                this.s = null;
            } else if (this.s != null) {
                this.s.valueObj.group_name = null;
            }
        }
    }
}
