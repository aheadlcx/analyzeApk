package qsbk.app.im;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.base.CommDialogActivity;
import qsbk.app.activity.group.SplashGroup;
import qsbk.app.adapter.IMMoreAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatListAdapter.OnAvatarClickListener;
import qsbk.app.im.CollectEmotion.CollectImageDomain;
import qsbk.app.im.CollectEmotion.CollectionManager;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.DraftStore;
import qsbk.app.im.datastore.LatestUsedCollectionStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CampaignInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupSystemMsg;
import qsbk.app.model.Laisee;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.GroupNotifier.OnNotifyListener;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.widget.TextBlockSpan;

public class GroupConversationActivity extends IMChatBaseActivityEx implements OnAvatarClickListener, OnNotifyListener {
    public static final String KEY_IMG_URI = "TAKE_PHOTO_IMG_URI";
    private static final String b = GroupConversationActivity.class.getSimpleName();
    public static ArrayList<String> groupSets = new ArrayList();
    public static int ownerId;
    TextWatcher a = new fb(this);
    private int aA;
    private int aB = 0;
    private LinearLayout aC;
    private TextView aD;
    private TextView aE;
    private boolean aF = false;
    private boolean aG = false;
    private boolean aH = false;
    private boolean aI = false;
    private int aJ = 0;
    private int aK = 0;
    private long aL = 0;
    private long aM = 0;
    private GroupMemberManager aN;
    private long aO = Long.MAX_VALUE;
    private boolean aP = true;
    private Runnable aQ = new fc(this);
    private final a ad = new a(this, this.y);
    private DraftStore ae = null;
    private BaseChatMsgStore af = null;
    private ContactListItemStore ag = null;
    private TextView ah;
    private View ai;
    private ImageView aj;
    private TextView ak;
    private View al;
    private TextView am;
    private boolean an = false;
    private int ao = 0;
    private final Runnable ap = new ec(this);
    private View aq;
    private TextView ar;
    private TextView as;
    private boolean at;
    private GroupInfo au;
    private final BroadcastReceiver av = new eu(this);
    private CampaignInfo aw;
    private String ax;
    private int ay;
    private ArrayList<AtInfo> az = new ArrayList();
    private final BroadcastReceiver c = new de(this);

    public static class AtInfo extends JSONAble implements Serializable {
        public static final String AT_ALL_NAME = "全部成员";
        public static final String AT_ALL_UID = "all";
        public static final int TYPE_AT_ALL = 1;
        public static final int TYPE_AT_ONE = 0;
        public String name;
        public TextBlockSpan span;
        public int type;
        public String uid;

        public static AtInfo constructJson(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            try {
                AtInfo atInfo = new AtInfo();
                atInfo.name = jSONObject.getString(QsbkDatabase.LOGIN);
                atInfo.uid = jSONObject.getString("user_id");
                atInfo.type = jSONObject.optInt("type");
                if (atInfo.type == 1) {
                    atInfo.uid = "all";
                }
                return atInfo;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static JSONObject toJson(AtInfo atInfo) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(QsbkDatabase.LOGIN, atInfo.name);
                jSONObject.put("user_id", atInfo.uid);
                jSONObject.put("type", atInfo.type);
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static ArrayList<AtInfo> constructJson(JSONArray jSONArray) {
            ArrayList<AtInfo> arrayList = new ArrayList();
            if (!(jSONArray == null || jSONArray.length() == 0)) {
                int i = 0;
                while (i < jSONArray.length()) {
                    try {
                        AtInfo constructJson = constructJson(jSONArray.getJSONObject(i));
                        if (constructJson != null) {
                            arrayList.add(constructJson);
                        }
                        i++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        }
    }

    private final class a implements Runnable {
        List<ChatMsg> a = new ArrayList();
        Handler b;
        final /* synthetic */ GroupConversationActivity c;

        public a(GroupConversationActivity groupConversationActivity, Handler handler) {
            this.c = groupConversationActivity;
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
            boolean z = this.c.d.getFirstVisiblePosition() + this.c.d.getChildCount() >= this.c.g.getCount();
            if (!z && this.c.ay == 0) {
                this.c.ay = this.c.g.getCount();
            }
            List<ChatMsg> arrayList = new ArrayList(this.a.size());
            List arrayList2 = new ArrayList(this.a.size());
            arrayList.addAll(this.a);
            for (ChatMsg chatMsg : arrayList) {
                this.c.g.removeSendingMsg(false);
                this.c.g.appendItem(chatMsg);
                chatMsg.status = 5;
                arrayList2.add(chatMsg.msgid);
            }
            LogUtil.d("执行到这里，将信息设置为已读，并且准备存储数据");
            this.c.g.notifyDataSetChanged();
            Util.imStorageQueue.postRunnable(new fi(this, arrayList2));
            if (z) {
                this.c.ay = 0;
                this.c.ah.setVisibility(4);
                this.c.d.setListSelection(this.c.g.getCount());
            } else {
                int count = this.c.g.getCount() - this.c.ay;
                if (count > 0) {
                    this.c.ah.setVisibility(0);
                    if (count <= 99) {
                        this.c.ah.setText(String.valueOf(count));
                    } else {
                        this.c.ah.setText("99+");
                    }
                } else {
                    this.c.ah.setVisibility(4);
                }
            }
            this.c.af.addUserTotalMsgUnread(-this.a.size(), GroupMsgUtils.isOpen(this.c.getToId(), true));
            this.a.clear();
            Logger.getInstance().debug(GroupConversationActivity.b, "run", "onMsgReceived 真正刷新界面..");
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void onCreate(Bundle bundle) {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "你暂未登录，请登录之后再打开小纸条", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(this);
            finish();
            return;
        }
        super.onCreate(bundle);
        C();
        GroupNotifier.register(this);
        this.V.registerReceiver(this.c, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        this.V.registerReceiver(this.av, new IntentFilter(Constants.ACTION_SEND_VOICE_LAISEE_TOO));
        this.Y = this.W;
    }

    private void B() {
        boolean z = true;
        if (this.au == null) {
            z = false;
        } else if (!((this.au.status == 1 && this.au.right == 1) || this.au.status == 2)) {
            z = false;
        }
        this.A.setMenuItem0Visible(z);
    }

    private void a(GroupInfo groupInfo) {
        if (groupInfo != null) {
            this.au = groupInfo;
            this.g.setTitles(this.au.getTitlesIfEnable());
            this.g.notifyDataSetChanged();
            this.aN = new GroupMemberManager(groupInfo);
            E();
            B();
            ownerId = this.au.ownerId;
            if (getToNick() == null) {
                getIntent().putExtra(IMChatBaseActivity.TO_NICK, this.au.name);
                g();
            }
            if (this.aF || (groupSets.size() > 0 && groupSets.contains(String.valueOf(this.au.id)))) {
                this.aB = 0;
            } else if (this.af.getUnreadMsgIds(getToId()).size() < 100 || !this.au.notifySwitch) {
                this.aB = 0;
            } else {
                this.aB = 1;
            }
            U();
            return;
        }
        String str = String.format(Constants.URL_GROUP_DETAIL, new Object[]{str}) + "?tid=" + getToId();
        new HttpTask(str, str, new fd(this)).execute(new Void[0]);
    }

    private void a(ArrayList<BaseUserInfo> arrayList) {
        this.g.setMembers(arrayList);
        this.g.notifyDataSetChanged();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) it.next();
            if (QsbkApp.currentUser.userId.equals(baseUserInfo.userId)) {
                this.aH = baseUserInfo.isAdmin;
                M();
                return;
            }
        }
    }

    private void C() {
        if (getIntent().getBooleanExtra(IMChatBaseActivityEx.REMOVE_NOTIFICATION, false)) {
            IMNotifyManager.instance().cleanNotification();
        }
    }

    protected void onResume() {
        this.z.setChatContext(3, getToId());
        super.onResume();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
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
        Util.imStorageQueue.postRunnable(new fe(this));
    }

    private void a(long j) {
        this.g.removeMsgById(j);
        Util.imStorageQueue.postRunnable(new ff(this, j));
    }

    public void insertAtUser(String str, String str2) {
        if (this.az.size() < 5) {
            AtInfo atInfo = new AtInfo();
            atInfo.uid = str;
            atInfo.name = str2;
            Editable text = this.G.getText();
            int selectionStart = this.G.getSelectionStart();
            int selectionEnd = this.G.getSelectionEnd();
            if (selectionStart != selectionEnd) {
                text.delete(selectionStart, selectionEnd);
            }
            text.insert(selectionStart, "@ ");
            atInfo.span = new TextBlockSpan("@" + atInfo.name, this.G);
            text.setSpan(atInfo.span, selectionStart, selectionStart + 1, 33);
            this.az.add(atInfo);
            this.G.performClick();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != C) {
            if (i == 10) {
                if (i2 == -1 && this.az.size() < 5) {
                    this.G.getText().insert(this.aA + 1, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    AtInfo atInfo = new AtInfo();
                    atInfo.uid = intent.getStringExtra("uid");
                    atInfo.name = intent.getStringExtra("uname");
                    atInfo.type = intent.getIntExtra("type", 0);
                    if (atInfo.type == 1) {
                        atInfo.name = "全体成员";
                        atInfo.uid = "all";
                    }
                    atInfo.span = new TextBlockSpan("@" + atInfo.name, this.G);
                    this.G.getText().setSpan(atInfo.span, this.aA, this.aA + 1, 33);
                    this.az.add(atInfo);
                    this.G.requestFocus();
                    UIHelper.showKeyboard(this);
                }
            } else if (i == 12 && intent != null) {
                Laisee laisee = (Laisee) intent.getSerializableExtra("laisee");
                if (laisee != null) {
                    sendLaiseeLocal(newContactItem(), laisee, this.s);
                }
            }
            if (this.g != null) {
                this.g.onActivityResult(i, i2, intent);
            }
        } else if (i2 == D) {
            if (this.S != null) {
                StringUtils.copyToClipboard(this.S, this);
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "对话内容已复制到粘贴板", Integer.valueOf(0)).show();
            }
            LogUtil.d("copy success");
        } else if (i2 == E) {
            onDeleteMsg();
        }
    }

    protected void onPause() {
        LogUtil.d("set chat context hide");
        hideEmojiAfterClickItem();
        this.z.setChatContext(0, null);
        super.onPause();
    }

    protected void onStop() {
        if (this.aF) {
            W();
        }
        super.onStop();
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.activity_im_group_conversation;
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        if (Build.MODEL != null && Build.MODEL.contains("HM NOTE")) {
            ViewCompat.setLayerType(findViewById(16908290), 1, null);
        }
        if (bundle != null) {
            Object string = bundle.getString("TAKE_PHOTO_IMG_URI");
            if (!TextUtils.isEmpty(string)) {
                this.Q = Uri.parse(string);
            }
        }
        this.J = (InputMethodManager) getSystemService("input_method");
        this.aC = (LinearLayout) findViewById(R.id.group_msg_remind_lin);
        this.aD = (TextView) findViewById(R.id.group_msg_remind);
        this.aE = (TextView) findViewById(R.id.go_to_group_set);
        this.aE.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.used_btn_yellow_night : R.drawable.used_btn_yellow);
        ownerId = getOwnerId();
        String userId = getUserId();
        MessageCountManager.getMessageCountManager(userId);
        LogUtil.d("userid:" + userId);
        this.af = ChatMsgStoreProxy.newInstance(userId, 3);
        SharePreferenceUtils.getClickGroupRemind();
        Intent intent = getIntent();
        this.at = intent.getBooleanExtra("from_group_info", false);
        this.aL = intent.getLongExtra("at_id", 0);
        this.aJ = intent.getIntExtra("unreadCount", 0);
        this.aq = findViewById(R.id.group_board);
        this.ar = (TextView) findViewById(R.id.group_board_tv);
        this.as = (TextView) findViewById(R.id.group_board_btn);
        g();
        this.d = (ScrollTopListView) findViewById(R.id.id_content);
        this.e = (LinearLayout) findViewById(R.id.sendlayout);
        this.al = findViewById(R.id.muteLayout);
        this.al.setOnClickListener(new fh(this));
        this.am = (TextView) findViewById(R.id.muteText);
        this.am.setTextColor(UIHelper.isNightTheme() ? -12171421 : -6908266);
        this.am.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.group_mute_icon_night : R.drawable.group_mute_icon), null, null, null);
        this.G = (EditText) findViewById(R.id.addCmtEditText);
        this.f = (ImageView) findViewById(R.id.send);
        this.i = (GridView) findViewById(R.id.more_container);
        this.z = UserChatManager.getUserChatManager(getUserId(), QsbkApp.currentUser.token);
        if (this.z.isHostSetted()) {
            Logger.getInstance().debug(b, "init", "chatManager.connect()");
            this.z.connect();
            onConnectStatusChange(this.z.getConnectStatus());
        } else {
            Logger.getInstance().debug(b, "init", "chatManager.getConnectHost(this)");
            this.z.getConnectHost(this);
            onConnectStatusChange(this.z.getConnectStatus());
        }
        this.af = ChatMsgStoreProxy.newInstance(userId, 3);
        this.ae = DraftStore.getDraftStore(userId);
        this.ag = ContactListItemStore.getContactStore(userId);
        this.aa = CollectionManager.getInstance(userId);
        this.R = LatestUsedCollectionStore.getCollectionStore(userId);
        K();
        X();
        a((GroupInfo) intent.getSerializableExtra("group_info"));
        J();
    }

    private void E() {
        this.aG = QsbkApp.currentUser.userId.equals(String.valueOf(this.au.ownerId));
        this.aH = this.aG;
        this.aN.loadMember(new df(this));
        M();
    }

    protected void onStart() {
        if (!this.ab) {
            I();
        }
        this.ab = false;
        super.onStart();
    }

    private void a(int i, boolean z) {
        if (((int) (IMTimer.getInstance().getCorrectTime() / 1000)) < i) {
            this.an = z;
            this.al.setVisibility(0);
            this.ao = i;
            this.am.setText(H());
            this.ap.run();
        }
    }

    private void F() {
        this.al.setVisibility(8);
        this.ao = 0;
        this.am.removeCallbacks(this.ap);
    }

    private boolean G() {
        return this.ao > ((int) (IMTimer.getInstance().getCorrectTime() / 1000));
    }

    private String H() {
        if (this.an) {
            return "全体禁言中";
        }
        int correctTime = (int) (((long) this.ao) - (IMTimer.getInstance().getCorrectTime() / 1000));
        if (correctTime > 60) {
            return "您已被禁言，" + ((correctTime + 30) / 60) + "分钟后解除";
        }
        return "您已被禁言，" + correctTime + "秒钟后解除";
    }

    private void I() {
        if (this.g != null && this.af != null) {
            Util.imStorageQueue.postRunnable(new dg(this));
        }
    }

    protected void onDestroy() {
        if (this.z != null) {
            this.z.destroyConnectHost(this);
        }
        GroupNotifier.unregister(this);
        if (this.V != null) {
            if (this.c != null) {
                this.V.unregisterReceiver(this.c);
            }
            if (this.av != null) {
                this.V.unregisterReceiver(this.av);
            }
        }
        this.ab = true;
        super.onDestroy();
    }

    public void setContentWithoutSendingTypingStatus(String str) {
        this.G.removeTextChangedListener(this.a);
        this.az.clear();
        this.G.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.G.setSelection(str.length());
        }
        this.G.addTextChangedListener(this.a);
    }

    private void a(List<ChatMsg> list) {
        Util.imStorageQueue.postRunnable(new dj(this, list));
    }

    private void J() {
        Util.imStorageQueue.postRunnable(new do(this), 500);
    }

    private void K() {
        this.x = new VoiceUIHelper(this);
        this.g = new ChatListAdapter(this, getToId(), getToNick(), this, true);
        this.g.setOnAvatarClickListener(this);
        this.d.setTopLoadingView(getInflater().inflate(R.layout.widget_chat_list_head, null), UIHelper.getDimensionPixelSize(this, R.dimen.im_head_scroll_height));
        this.d.setOnTouchListener(new dp(this));
        this.d.setOnScrollListener(new dq(this));
        this.d.setCanRefresh(false);
        this.d.setOnPullListener(new dr(this));
        this.d.setAdapter(this.g);
        this.ai = findViewById(R.id.at_layout);
        Util.imStorageQueue.postRunnable(new dw(this));
        this.f.setOnClickListener(new dz(this));
        this.f.setEnabled(false);
        this.G.addTextChangedListener(this.a);
        this.G.setOnEditorActionListener(new eb(this));
        this.G.setOnClickListener(new ed(this));
        this.aj = (ImageView) findViewById(R.id.at_avatar);
        this.ak = (TextView) findViewById(R.id.at_msg);
        this.ah = (TextView) findViewById(R.id.new_msg_count);
        this.ah.setOnClickListener(new ee(this));
        w();
        N();
    }

    private void L() {
        this.ai.post(new ef(this));
    }

    private void M() {
        F();
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("mute_all_" + getToId()) || this.aH) {
            this.ao = SharePreferenceUtils.getSharePreferencesIntValue("mute_time_" + getToId() + "_" + QsbkApp.currentUser.userId);
            if (G()) {
                a(this.ao, false);
                return;
            }
            return;
        }
        this.ao = SharePreferenceUtils.getSharePreferencesIntValue("mute_all_time_" + getToId());
        a(this.ao, true);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.Q != null) {
            bundle.putString("TAKE_PHOTO_IMG_URI", this.Q.toString());
        }
    }

    private void N() {
        String toId = getToId();
        ChatMsg groupDraft = this.ae.getGroupDraft(toId);
        if (groupDraft != null) {
            int[] iArr;
            String str = groupDraft.data;
            toId = getSharedPreferences("AtDraft", 0).getString(toId, "");
            ArrayList arrayList = new ArrayList();
            try {
                int i;
                JSONArray jSONArray = new JSONArray(toId);
                iArr = new int[jSONArray.length()];
                for (i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    AtInfo atInfo = new AtInfo();
                    atInfo.uid = jSONObject.getString("id");
                    atInfo.name = jSONObject.getString("name");
                    iArr[i] = jSONObject.getInt("pos");
                    arrayList.add(atInfo);
                }
                StringBuilder stringBuilder = new StringBuilder(groupDraft.data);
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    int i3 = iArr[i2];
                    if (i3 >= 0) {
                        String str2 = ((AtInfo) arrayList.get(i2)).name;
                        stringBuilder.delete(i3 + 1, (i3 + 1) + str2.length());
                        for (i = 0; i < iArr.length; i++) {
                            if (iArr[i] > i3) {
                                iArr[i] = iArr[i] - str2.length();
                            }
                        }
                    }
                }
                toId = stringBuilder.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                iArr = null;
                toId = str;
            }
            this.f.setEnabled(true);
            setContentWithoutSendingTypingStatus(toId);
            this.x.setSendVoiceButtonVisibility(false);
            this.f.setVisibility(0);
            if (iArr != null && iArr.length > 0) {
                Editable text = this.G.getText();
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    int i5 = iArr[i4];
                    if (i5 >= 0) {
                        AtInfo atInfo2 = (AtInfo) arrayList.get(i4);
                        TextBlockSpan textBlockSpan = new TextBlockSpan("@" + atInfo2.name, this.G);
                        text.setSpan(textBlockSpan, i5, i5 + 1, 33);
                        atInfo2.span = textBlockSpan;
                        this.az.add(atInfo2);
                    }
                }
            }
            Q();
        }
    }

    public void finish() {
        P();
        UIHelper.hideKeyboard(this);
        if (isTaskRoot()) {
            startActivity(new Intent(this, SplashGroup.class));
        }
        super.finish();
    }

    private void O() {
        this.ae.delete(getToId());
    }

    private void P() {
        int i = 0;
        CharSequence text = this.G.getText();
        String toId = getToId();
        SharedPreferences sharedPreferences = getSharedPreferences("AtDraft", 0);
        if (TextUtils.isEmpty(text.toString().trim())) {
            this.ae.delete(toId);
            sharedPreferences.edit().remove(toId).apply();
            return;
        }
        int i2;
        int[] iArr = new int[this.az.size()];
        JSONArray jSONArray = new JSONArray();
        for (i2 = 0; i2 < this.az.size(); i2++) {
            iArr[i2] = text.getSpanStart(((AtInfo) this.az.get(i2)).span);
        }
        StringBuilder stringBuilder = new StringBuilder(text);
        for (i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 >= 0) {
                String str = ((AtInfo) this.az.get(i2)).name;
                stringBuilder.insert(i3 + 1, str);
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (iArr[i4] > i3) {
                        iArr[i4] = iArr[i4] + str.length();
                    }
                }
            }
        }
        while (i < this.az.size()) {
            AtInfo atInfo = (AtInfo) this.az.get(i);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", atInfo.uid);
                jSONObject.put("name", atInfo.name);
                jSONObject.put("pos", iArr[i]);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
        sharedPreferences.edit().putString(toId, jSONArray.toString()).apply();
        if (this.ae.getGroupDraft(toId) != null) {
            this.ae.update(toId, stringBuilder.toString(), System.currentTimeMillis());
        } else {
            this.ae.insert(toId, stringBuilder.toString(), System.currentTimeMillis());
        }
    }

    private void Q() {
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

    public void onAvatarClick(BaseUserInfo baseUserInfo) {
        BaseUserInfo member = this.aN == null ? null : this.aN.getMember(baseUserInfo.userId);
        if (member == null) {
            if (this.aN != null) {
                this.aN.loadMember(new ei(this));
            } else {
                return;
            }
        }
        if (member != null && (this.aG || (this.aH && !member.isAdmin))) {
            String str;
            int i;
            String str2;
            int i2;
            int[] iArr;
            CharSequence[] charSequenceArr;
            if (member.silenceTime > System.currentTimeMillis()) {
                str = "解除禁言";
                i = 2;
            } else {
                str = "禁言";
                i = 1;
            }
            if (member.isAdmin) {
                str2 = "撤销管理员";
                i2 = 5;
            } else {
                str2 = "设为管理员";
                i2 = 4;
            }
            if (this.aG) {
                iArr = new int[]{6, 7, i2, i, 3};
                charSequenceArr = new String[]{"查看资料", "临时小纸条", str2, str, "移出本群"};
            } else {
                iArr = new int[]{6, 7, i, 3};
                Object[] objArr = new String[]{"查看资料", "临时小纸条", str, "移出本群"};
            }
            new Builder(this).setItems(charSequenceArr, new ej(this, iArr, baseUserInfo, member)).show();
        } else if (UserClickDelegate.isOfficialAccount(baseUserInfo.userId)) {
            OfficialInfoActivity.launch(this, baseUserInfo.userId, baseUserInfo.userName, baseUserInfo.userIcon);
        } else {
            MyInfoActivity.launch(this, baseUserInfo.userId, getToId(), getToNick(), MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, String.valueOf(this.au.ownerId), String.valueOf(getToId()) + Config.TRACE_TODAY_VISIT_SPLIT + getToNick()));
        }
    }

    private void c(String str) {
        new Builder(this).setMessage(str).setCancelable(false).setPositiveButton("确定", new ep(this)).create().show();
    }

    public void onGroupNotify(int i, int i2) {
        this.y.post(new eq(this, getToId(), i, i2));
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
        LogUtil.d("chat list on message received");
        if (chatMsg.type == 301 && getToId().equals(chatMsg.gid)) {
            GroupSystemMsg groupSystemMsg = chatMsg.getGroupSystemMsg();
            String str = QsbkApp.currentUser.userId;
            if (groupSystemMsg.type == 3) {
                M();
            } else if (groupSystemMsg.type == 4) {
                M();
            } else if (groupSystemMsg.type == 6) {
                if (groupSystemMsg.sid.equals(str)) {
                    this.aH = true;
                    M();
                }
            } else if (groupSystemMsg.type == 7 && groupSystemMsg.sid.equals(str)) {
                this.aH = false;
                M();
            }
        }
        if (!chatMsg.isGroupMsg() || !chatMsg.gid.equals(getToId())) {
            return;
        }
        if (chatMsg.type == 102) {
            R();
            this.g.addSendingMsg(chatMsg);
        } else if (chatMsg.type == 1 || chatMsg.type == 4 || chatMsg.type == 3 || chatMsg.type == 10 || chatMsg.isContentMsg() || chatMsg.type == 301 || chatMsg.type == 302) {
            LogUtil.d("msg.type =========" + chatMsg.type);
            Logger.getInstance().debug(b, "onMessageReceived", "onMsgReceived 添加到队列里面去");
            this.ad.a(chatMsg).a(100);
        } else if (chatMsg.type == 107) {
            Logger.getInstance().debug(b, "onMessageReceived", "onMessageReceived on message readed:" + ArrayUtils.join(chatMsg.msgids, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) + " . " + chatMsg);
            this.g.onMsgStateChanged(chatMsg.msgids, 5);
        } else if (chatMsg.type == 8) {
            LogUtil.d("on message sended:" + ArrayUtils.join(chatMsg.msgids, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP));
            this.g.addSystemMsg(chatMsg);
        }
    }

    private void R() {
        this.y.removeCallbacks(this.aQ);
        this.y.postDelayed(this.aQ, 18000);
    }

    public void uploaded(ChatMsg chatMsg) {
        Logger.getInstance().debug(b, "uploaded", " msg " + chatMsg.toString());
        if (G()) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, H(), Integer.valueOf(0)).show();
            return;
        }
        this.z.sendImageTo(chatMsg);
        this.af.updateMessageData(chatMsg);
    }

    public void repeatUploaded(ChatMsg chatMsg) {
        Logger.getInstance().debug(b, "uploaded", " msg " + chatMsg.toString());
        if (G()) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, H(), Integer.valueOf(0)).show();
            return;
        }
        this.z.sendImageTo(chatMsg);
        this.af.updateMessageData(chatMsg);
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
        if (G()) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, H(), Integer.valueOf(0)).show();
            return null;
        }
        ContactListItem newContactItem = newContactItem();
        newContactItem.mLastContent = chatMsgEmotionData.name;
        return this.z.sendPureEmotionTo(newContactItem, chatMsgEmotionData.encodeToJsonObject().toString(), this.s);
    }

    public ContactListItem newContactItem() {
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.id = getToId();
        contactListItem.name = getToNick();
        contactListItem.icon = getToIcon();
        contactListItem.type = 3;
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
        this.A.setMenuItemVisible(true);
        this.A.setMenuItemResource(UIHelper.isNightTheme() ? R.drawable.ic_group_info_night : R.drawable.ic_group_info);
        this.A.setMenuItemClickListener(new er(this));
        B();
        this.A.setMenuItem0Resource(R.drawable.group_notice);
        this.A.setMenuItem0ClickListener(new es(this));
        String toNick = getToNick();
        if (TextUtils.isEmpty(toNick)) {
            toNick = getToId();
            if (this.ag != null) {
                ContactListItem withGroup = this.ag.getWithGroup(toNick);
                if (withGroup != null) {
                    toNick = withGroup.name;
                }
            }
        }
        this.A.setCenterText(toNick);
        this.A.setLeftText("消息");
    }

    private void a(String str, String str2, OnClickListener onClickListener) {
        this.aq.setVisibility(0);
        this.ar.setTextColor(UIHelper.isNightTheme() ? -12171437 : -10658467);
        this.as.setTextColor(UIHelper.isNightTheme() ? -12871305 : -11215958);
        if (str.length() > 12) {
            if (str.length() > 20) {
                str = str.substring(0, 20) + "...";
            }
            this.ar.setGravity(3);
        } else {
            this.ar.setGravity(1);
        }
        this.ar.setText(str);
        this.as.setText(str2);
        this.as.setOnClickListener(onClickListener);
    }

    private void S() {
        new SimpleHttpTask(String.format(Constants.URL_GROUP_MANAGER_VOTE, new Object[]{String.valueOf(this.au.id)}) + "?tid=" + this.au.id, new et(this)).execute();
    }

    private void T() {
        new SimpleHttpTask(String.format(Constants.URL_TRANSITION, new Object[]{Integer.valueOf(this.au.id)}) + "?tid=" + this.au.id, new ev(this)).execute();
    }

    private void U() {
        if (this.au == null) {
            W();
        } else if (this.au.status == 1 && this.au.right == 1) {
            T();
        } else if (this.au.status == 2) {
            S();
        } else if (this.aB == 1) {
            V();
        } else {
            W();
        }
    }

    private void V() {
        if (this.au.status == 1 && this.au.right == 1) {
            a("群大海选火热进行中", "我要报名", new ew(this));
        } else if (this.au.status == 2) {
            a("群大海选火热进行中", "我要投票", new ex(this));
        } else if (this.aB == 1) {
            a("此群消息过多，是否关闭提醒？", "去设置", new ey(this));
        } else {
            this.aq.setVisibility(8);
        }
    }

    private void W() {
        this.aq.setVisibility(8);
    }

    private void X() {
        this.i.setAdapter(new IMMoreAdapter());
        this.i.setOnItemClickListener(new ez(this));
    }

    public void sendLaiseeLocal(ContactListItem contactListItem, Laisee laisee, IMChatMsgSource iMChatMsgSource) {
        ChatMsg sendLocalLaiseeMsg = this.z.sendLocalLaiseeMsg(contactListItem, laisee, iMChatMsgSource);
        if (sendLocalLaiseeMsg != null) {
            sendLocalLaiseeMsg.status = 5;
            this.g.appendItem(sendLocalLaiseeMsg);
            this.g.moveSendingToLast();
            this.g.notifyDataSetChanged();
            this.y.postDelayed(new fa(this), 200);
            this.s = null;
        }
    }
}
