package qsbk.app.im;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.AddQiuYouActivity;
import qsbk.app.activity.CreateNewGroupActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.SearchGroupActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.StatisticFragment;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.DatabaseHelper;
import qsbk.app.im.datastore.DraftStore;
import qsbk.app.im.datastore.GroupChatMsgStore;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.GroupNotifier.OnNotifyListener;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.AutoLoadMoreListView;
import qsbk.app.widget.TipsView;

public class IMMessageListFragment extends StatisticFragment implements IChatMsgListener, IOnConnectHostResp, OnNotifyListener {
    public static final String ACTION_DELETE_CONTACT_ITEM = "action_delete_contact_item";
    public static final int TYPE_EMPTYLIST = 2;
    public static final int TYPE_LOGIN = 1;
    public static final String USER_ID = "user_id";
    private static final String d = IMMessageListFragment.class.getSimpleName();
    private static int e = 0;
    private boolean A = false;
    private View B = null;
    private int C = 0;
    private int D = 0;
    private int E;
    private ImageView F;
    private SplashAdItem G;
    private DataSource<CloseableReference<CloseableImage>> H;
    private ProgressDialog I;
    private final BroadcastReceiver J = new hm(this);
    private Toolbar K;
    private ImageView L;
    private View M;
    private TextView N;
    private LocalBroadcastManager O;
    protected TipsView a;
    protected BroadcastReceiver b;
    ListPopupWindow c;
    private final Handler f = new Handler();
    private final BroadcastReceiver g = new gm(this);
    private final c h = new c(this, this.f);
    private List<ContactListItem> i;
    private String j;
    private ChatMsgStore k = null;
    private GroupChatMsgStore l = null;
    private GroupNoticeStore m = null;
    private ContactListItemStore n = null;
    private DraftStore o;
    private AutoLoadMoreListView p = null;
    private ContactListAdapter q = null;
    private final BroadcastReceiver r = new hb(this);
    private UserChatManager s = null;
    private boolean t = false;
    private ProgressBar u;
    private Drawable v;
    private Drawable w;
    private boolean x = true;
    private boolean y = false;
    private boolean z = false;

    class a extends BaseAdapter {
        final /* synthetic */ IMMessageListFragment a;

        a(IMMessageListFragment iMMessageListFragment) {
            this.a = iMMessageListFragment;
        }

        public int getCount() {
            return 6;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_im_add, viewGroup, false);
                d dVar2 = new d(this.a, view);
                view.setTag(dVar2);
                dVar = dVar2;
            } else {
                dVar = (d) view.getTag();
            }
            switch (i) {
                case 0:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_friend_add_night : R.drawable.ic_im_friend_add);
                    dVar.a.setText("加糗友");
                    break;
                case 1:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_group_join_night : R.drawable.ic_im_group_join);
                    dVar.a.setText("加入群");
                    break;
                case 2:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_group_create_night : R.drawable.ic_im_group_create);
                    dVar.a.setText("创建群");
                    break;
                case 3:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_group_nearby_night : R.drawable.ic_im_group_nearby);
                    dVar.a.setText("附近的群");
                    break;
                case 4:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_person_nearby_night : R.drawable.ic_im_person_nearby);
                    dVar.a.setText("附近的糗友");
                    break;
                case 5:
                    dVar.b.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_im_clear_unread_night : R.drawable.ic_im_clear_unread);
                    dVar.a.setText("清除未读");
                    break;
            }
            view.setOnClickListener(new ht(this, i));
            return view;
        }
    }

    private static class b {
        static final b a = new b(new ArrayList(0), 0, 0);
        final List<ContactListItem> b;
        final int c;
        final int d;

        b(List<ContactListItem> list, int i, int i2) {
            this.c = i;
            this.d = i2;
            this.b = list;
        }
    }

    private final class c implements Runnable {
        final /* synthetic */ IMMessageListFragment a;
        private Handler b;

        public c(IMMessageListFragment iMMessageListFragment, Handler handler) {
            this.a = iMMessageListFragment;
            this.b = handler;
        }

        void a() {
            this.b.removeCallbacks(this);
        }

        void b() {
            a(1000 - (System.currentTimeMillis() % 1000));
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
            this.a.o();
        }
    }

    class d {
        TextView a;
        ImageView b;
        final /* synthetic */ IMMessageListFragment c;

        public d(IMMessageListFragment iMMessageListFragment, View view) {
            this.c = iMMessageListFragment;
            this.a = (TextView) view.findViewById(R.id.text);
            this.b = (ImageView) view.findViewById(R.id.image);
        }
    }

    public IMMessageListFragment() {
        setStatisticsEvent("im.IMMessageListActivity");
    }

    private static final long[] a(List<Long> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((Long) list.get(i)).longValue();
        }
        return jArr;
    }

    public static void sortContactListItems(List<ContactListItem> list) {
        if (list != null && !list.isEmpty()) {
            ArrayUtils.sort(list, new hn());
        }
    }

    public static void sortContactListItemByMsgId(List<ContactListItem> list) {
        if (list != null && !list.isEmpty()) {
            ArrayUtils.sort(list, new ho());
        }
    }

    private void f() {
        if (this.I == null) {
            Context activity = getActivity();
            if (activity != null) {
                this.I = new ProgressDialog(activity);
                this.I.setCanceledOnTouchOutside(false);
                this.I.setTitle(null);
                this.I.setMessage("请稍候...");
            }
        }
        this.f.post(new hp(this));
    }

    private void g() {
        if (this.I != null) {
            this.f.post(new hq(this));
        }
    }

    public void onGroupNotify(int i, int i2) {
        o();
    }

    protected boolean a() {
        long time = new Date().getTime() / 1000;
        if (SplashAdManager.mAnnouncement == null || SplashAdManager.mAnnouncement.start >= time || SplashAdManager.mAnnouncement.end <= time || ((SplashAdManager.mAnnouncement.location != 2 && SplashAdManager.mAnnouncement.location != 0) || SplashAdManager.mAnnouncement.hasClick)) {
            return false;
        }
        return true;
    }

    protected void b() {
        if (SplashAdManager.mAnnouncement != null && this.a != null) {
            a(true);
            this.a.setTipsViewTextContent(SplashAdManager.mAnnouncement.content);
            this.a.setTipsViewBgColor(UIHelper.isNightTheme() ? -1723489030 : -431643398);
            this.a.setOnClickListener(new hr(this));
        }
    }

    protected void a(boolean z) {
        if (this.a != null) {
            if (z) {
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
        }
    }

    protected void c() {
        if (HttpUtils.netIsAvailable()) {
            a(false);
            if (a()) {
                b();
            }
        }
        if (!HttpUtils.netIsAvailable() && this.a != null) {
            a(true);
            this.a.setTipsViewTextContent(getActivity().getResources().getString(R.string.no_network));
            this.a.setTipsViewBgColor(getResources().getColor(R.color.widget_tips_view_bg_color));
            this.a.setOnClickListener(null);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        GroupNotifier.register(this);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.j = arguments.getString("user_id");
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        }
        this.O = LocalBroadcastManager.getInstance(getActivity());
        this.O.registerReceiver(this.J, new IntentFilter(ACTION_DELETE_CONTACT_ITEM));
        this.O.registerReceiver(this.r, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        this.O.registerReceiver(this.g, new IntentFilter(MainActivity.ACTION_NEW_FANS));
        this.b = new hs(this);
        getActivity().registerReceiver(this.b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void showDialog() {
        if (p()) {
            new Builder(getActivity()).setMessage("确认要清除所有未读的消息？").setNegativeButton("确定", new go(this)).setPositiveButton("取消", new gn(this)).show();
        } else {
            m();
        }
    }

    public void hideTips() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).clearIMMessageTips();
        }
    }

    public void clearAllMsgs() {
        f();
        Util.imStorageQueue.postRunnable(new gp(this));
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new gr(this));
    }

    private void h() {
        if (p()) {
            startActivity(new Intent(getActivity(), AddQiuYouActivity.class));
        } else {
            m();
        }
    }

    private void i() {
        if (p()) {
            startActivity(new Intent(getActivity(), CreateNewGroupActivity.class));
        } else {
            m();
        }
    }

    private void j() {
        if (p()) {
            SearchGroupActivity.launch(getActivity());
        } else {
            m();
        }
    }

    public void onDetach() {
        super.onDetach();
        GroupNotifier.unregister(this);
    }

    public void onPause() {
        super.onPause();
        this.y = false;
        if (this.s != null) {
            this.s.setChatContext(0, null);
        }
    }

    public void onResume() {
        super.onResume();
        IMNotifyManager.instance().cleanNotification();
        this.y = true;
        k();
        c();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            IMNotifyManager.instance().cleanNotification();
            e = SharePreferenceUtils.getSharePreferencesIntValue(MessageCountManager.NEWFANS_COUNT);
            k();
        }
    }

    private void k() {
        s();
        if (p()) {
            a(8, 1);
            q();
            o();
            UIHelper.hideKeyboard(getActivity());
            if (this.p != null) {
                this.p.setVisibility(0);
            }
            if (this.s != null) {
                this.s.addObserver(this);
                this.s.setChatContext(1, null);
            }
        } else {
            if (this.i != null) {
                this.i.clear();
            }
            if (this.q != null) {
                this.q.clear();
                this.q.notifyDataSetChanged();
            }
            b(2);
            this.p.setVisibility(8);
            a(0, 1);
        }
        l();
    }

    private void l() {
        if (this.K != null) {
            e = SharePreferenceUtils.getSharePreferencesIntValue(MessageCountManager.NEWFANS_COUNT);
            if (e <= 0 || this.N == null) {
                this.N.setText("");
                this.N.setVisibility(8);
                return;
            }
            this.N.setVisibility(0);
            this.N.setText(e > 99 ? "99+" : e + "");
        }
    }

    private void a(int i, int i2) {
        View view = getView();
        if (view != null && i == 0) {
            this.B = getView().findViewById(R.id.login_layout);
            if (i2 == 1) {
                view.findViewById(R.id.unlogin).setVisibility(0);
                view.findViewById(R.id.empty_list).setVisibility(8);
                View findViewById = view.findViewById(R.id.id_btn_login);
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new gs(this));
                ((TextView) view.findViewById(R.id.tips)).setText(R.string.nologin_when_open_message);
            } else if (i2 == 2) {
                view.findViewById(R.id.unlogin).setVisibility(8);
                view.findViewById(R.id.empty_list).setVisibility(0);
                view.findViewById(R.id.id_btn_login).setVisibility(8);
                ((TextView) view.findViewById(R.id.tips)).setText(R.string.no_messages_here);
                ((TextView) view.findViewById(R.id.tips)).setGravity(17);
            }
        }
        if (this.B != null) {
            this.B.setVisibility(i);
        }
    }

    private void m() {
        LoginPermissionClickDelegate.startLoginActivity(getActivity());
    }

    private void b(int i) {
        n();
        if (this.u != null) {
            switch (i) {
                case 1:
                    this.u.setIndeterminateDrawable(this.v);
                    this.u.setVisibility(0);
                    return;
                case 3:
                    if (this.x) {
                        this.x = false;
                        if (isResumed()) {
                            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "网络不可用，请稍后再试！", Integer.valueOf(1)).show();
                        }
                    }
                    if (this.y) {
                        this.w.setBounds(this.v.getBounds());
                        this.u.setIndeterminateDrawable(this.w);
                        this.u.setVisibility(0);
                        return;
                    }
                    return;
                case 4:
                    if (this.y) {
                        this.w.setBounds(this.v.getBounds());
                        this.u.setIndeterminateDrawable(this.w);
                        this.u.setVisibility(0);
                        return;
                    }
                    return;
                case 6:
                    return;
                default:
                    this.x = true;
                    this.u.setIndeterminateDrawable(this.v);
                    this.u.setVisibility(4);
                    return;
            }
        }
    }

    private void n() {
        if (!this.t) {
            this.t = true;
            try {
                View findViewById = getActivity().findViewById(Resources.getSystem().getIdentifier("progress_circular", "id", "android"));
                if (findViewById != null && (findViewById instanceof ProgressBar)) {
                    this.u = (ProgressBar) findViewById;
                    this.v = this.u.getIndeterminateDrawable();
                    this.w = getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.progress_circul_slow_night : R.drawable.progress_circul_slow);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void b(List<ContactListItem> list) {
        if (!isDetached()) {
            if (this.i != null) {
                this.i.clear();
            }
            this.i = list;
            this.q.replaceItem(this.i);
            if (this.i.isEmpty()) {
                a(0, 2);
            } else {
                a(8, 2);
            }
        }
    }

    private void o() {
        if (QsbkApp.currentUser != null) {
            Object obj = QsbkApp.currentUser.userId;
            if (!TextUtils.isEmpty(obj)) {
                Util.imStorageQueue.postRunnable(new gt(this, obj));
            }
        }
    }

    private void a(ContactListItem contactListItem) {
        f();
        if (contactListItem.isGroupNotice()) {
            this.m.deleteAllMessages();
            this.n.delete(contactListItem.id, 6);
            g();
        } else if (contactListItem.type == 2) {
            this.n.updateUnarchiveCount(0);
            this.n.delete(contactListItem.id, contactListItem.type);
            g();
        } else {
            Util.imStorageQueue.postRunnable(new gv(this, contactListItem.isGroupMsg() ? this.l : this.k, contactListItem));
        }
    }

    private boolean p() {
        return (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userId)) ? false : true;
    }

    private void b(ContactListItem contactListItem) {
        CharSequence charSequence;
        Builder builder = new Builder(getActivity());
        if (contactListItem.isGroupNotice()) {
            charSequence = contactListItem.name;
        } else {
            charSequence = String.format("与'%s'的对话", new Object[]{contactListItem.name});
        }
        builder.setTitle(charSequence).setItems(new String[]{"删除"}, new gx(this, contactListItem)).create().show();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        n();
        if (this.u != null) {
            this.u.setVisibility(0);
        }
        if (p()) {
            q();
        }
    }

    private void q() {
        Logger.getInstance().debug(d, "initIfNeed", "inite:" + this.z + "  restart:" + this.A + " ,mUid: " + this.j + " ," + QsbkApp.currentUser.userId);
        if (!this.z || this.A || (QsbkApp.currentUser != null && !QsbkApp.currentUser.userId.equalsIgnoreCase(this.j))) {
            if (!(QsbkApp.currentUser == null || QsbkApp.currentUser.userId.equalsIgnoreCase(this.j))) {
                this.j = QsbkApp.currentUser.userId;
            }
            this.z = true;
            this.A = false;
            DatabaseHelper.getInstance(getActivity(), this.j);
            if (this.i != null) {
                this.i.clear();
            }
            if (this.q != null) {
                this.q.clear();
            }
            this.q = null;
            this.q = new ContactListAdapter(getActivity(), this.j);
            this.p.setAdapter(this.q);
            this.s = UserChatManager.getUserChatManager(this.j, QsbkApp.currentUser.token);
            if (this.s.isConnected()) {
                Logger.getInstance().debug(d, "initIfNeed", "connected...");
                this.s.removeDisconnentEvent();
                onConnectStatusChange(this.s.getConnectStatus());
            } else {
                Logger.getInstance().debug(d, "initIfNeed", "chatManager.getConnectHost()");
                this.s.getConnectHost(this);
            }
            this.s.addObserver(this);
            this.k = ChatMsgStore.getChatMsgStore(this.j);
            this.n = ContactListItemStore.getContactStore(this.j);
            this.o = DraftStore.getDraftStore(this.j);
            this.l = GroupChatMsgStore.getInstance(this.j);
            this.m = GroupNoticeStore.getInstance(this.j);
            r();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.im_contact_list, null);
        this.F = (ImageView) inflate.findViewById(R.id.activity_notification);
        this.F.setOnClickListener(new gy(this));
        if (!t()) {
            SplashAdManager instance = SplashAdManager.instance();
            instance.doTaskOnLoaded(new gz(this));
            instance.loadSplashAd();
        }
        this.p = (AutoLoadMoreListView) inflate.findViewById(R.id.chat_list);
        this.a = (TipsView) inflate.findViewById(R.id.tipsView);
        IMNotifyManager.instance().cleanNotification();
        this.A = true;
        getAndInitPagerSlidingTabStrip(inflate);
        return inflate;
    }

    private HashMap<Long, ChatMsg> c(List<ChatMsg> list) {
        HashMap<Long, ChatMsg> hashMap = new HashMap(list.size());
        for (ChatMsg chatMsg : list) {
            if (chatMsg.dbid > 0) {
                hashMap.put(Long.valueOf(chatMsg.dbid), chatMsg);
            }
        }
        return hashMap;
    }

    private b a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        List<ContactListItem> all = this.n.getAll();
        if (all == null || all.isEmpty()) {
            Logger.getInstance().debug(d, "getContactListItem", "最近联系人列表为空，请在测试界面点击“测试插入一些消息”");
            return b.a;
        }
        int i;
        int i2;
        int i3;
        Iterator it = all.iterator();
        while (it.hasNext()) {
            ContactListItem contactListItem = (ContactListItem) it.next();
            if (contactListItem.type == 4) {
                if (QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(contactListItem.id) || QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(contactListItem.id) || "22791001".equals(contactListItem.id)) {
                    it.remove();
                }
            } else if (contactListItem.type == 2) {
                it.remove();
            }
        }
        int size = all.size();
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (i = 0; i < size; i++) {
            contactListItem = (ContactListItem) all.get(i);
            if (!contactListItem.isGroupNotice()) {
                if (contactListItem.isGroupMsg()) {
                    arrayList2.add(Long.valueOf(contactListItem.msgId));
                    arrayList4.add(contactListItem.id);
                } else {
                    arrayList.add(Long.valueOf(contactListItem.msgId));
                    arrayList3.add(contactListItem.id);
                }
            }
        }
        long[] a = a(arrayList);
        long[] a2 = a(arrayList2);
        arrayList2 = new ArrayList(a.length + a2.length);
        Collection collection = this.k.get(a);
        if (collection != null && collection.size() > 0) {
            arrayList2.addAll(collection);
        }
        collection = this.l.get(a2);
        if (collection != null && collection.size() > 0) {
            arrayList2.addAll(collection);
        }
        ChatMsg chatMsg;
        if (arrayList2.size() != size) {
            Logger.getInstance().debug(d, "getContactListItem", String.format("可能是Bug：最近联系人列表%s 与 所有会话记录列表%s 不同步。", new Object[]{Integer.valueOf(size), Integer.valueOf(arrayList2.size())}));
            HashMap c = c(arrayList2);
            for (ContactListItem contactListItem2 : all) {
                chatMsg = (ChatMsg) c.get(Long.valueOf(contactListItem2.msgId));
                if (chatMsg != null) {
                    contactListItem2.status = chatMsg.status;
                    contactListItem2.inout = chatMsg.inout;
                    contactListItem2.mimeType = chatMsg.type;
                    if (!(!contactListItem2.isGroupMsg() || TextUtils.isEmpty(chatMsg.from) || TextUtils.isEmpty(chatMsg.getFromNick()))) {
                        contactListItem2.lastMsgFromId = chatMsg.from;
                        contactListItem2.lastMsgFromName = chatMsg.getFromNick();
                    }
                }
            }
        } else {
            sortContactListItemByMsgId(all);
            for (i2 = 0; i2 < size; i2++) {
                contactListItem2 = (ContactListItem) all.get(i2);
                chatMsg = (ChatMsg) arrayList2.get(i2);
                LogUtil.d(String.format("name:%s , dbid:%s , content: %s , status: %s, inout: %s", new Object[]{contactListItem2.name, Long.valueOf(chatMsg.dbid), chatMsg.data, Integer.valueOf(chatMsg.status), Integer.valueOf(chatMsg.inout)}));
                contactListItem2.status = chatMsg.status;
                contactListItem2.inout = chatMsg.inout;
                contactListItem2.mimeType = chatMsg.type;
                if (!(!contactListItem2.isGroupMsg() || TextUtils.isEmpty(chatMsg.from) || TextUtils.isEmpty(chatMsg.getFromNick()))) {
                    contactListItem2.lastMsgFromId = chatMsg.from;
                    contactListItem2.lastMsgFromName = chatMsg.getFromNick();
                }
            }
        }
        int i4 = 0;
        i2 = 0;
        String[] strArr = new String[arrayList3.size()];
        arrayList3.toArray(strArr);
        String[] strArr2 = new String[arrayList4.size()];
        arrayList4.toArray(strArr2);
        int[] unreadCountWithIds = this.k.getUnreadCountWithIds(strArr);
        int[] unreadCountWithIds2 = this.l.getUnreadCountWithIds(strArr2);
        for (i3 = 0; i3 < size; i3++) {
            contactListItem2 = (ContactListItem) all.get(i3);
            if (contactListItem2.isGroupNotice()) {
                contactListItem2.unreadCount = this.m.getTotalUnReadCount();
                i4 += contactListItem2.unreadCount;
            } else if (contactListItem2.isGroupMsg()) {
                int i5;
                i = 0;
                while (i < strArr2.length) {
                    if (strArr2[i].equals(contactListItem2.id)) {
                        contactListItem2.unreadCount = unreadCountWithIds2[i];
                        if (GroupMsgUtils.isOpen(contactListItem2.id, true)) {
                            i = unreadCountWithIds2[i] + i4;
                            i5 = i2;
                        } else {
                            i5 = unreadCountWithIds2[i] + i2;
                            i = i4;
                        }
                        i2 = i5;
                        i4 = i;
                    } else {
                        i++;
                    }
                }
                i5 = i2;
                i = i4;
                i2 = i5;
                i4 = i;
            } else {
                i = 0;
                while (i < strArr.length) {
                    if (!strArr[i].equals(contactListItem2.id)) {
                        i++;
                    } else if (contactListItem2.type == 2) {
                        contactListItem2.unreadCount = MessageCountManager.getMessageCountManager(this.j).getUnarchiveCount();
                    } else {
                        contactListItem2.unreadCount = unreadCountWithIds[i];
                        i4 += unreadCountWithIds[i];
                    }
                }
            }
        }
        List all2 = this.o.getAll();
        if (!(all2 == null || all2.isEmpty())) {
            int size2 = all2.size();
            for (int i6 = 0; i6 < size2; i6++) {
                ChatMsg chatMsg2 = (ChatMsg) all2.get(i6);
                for (i3 = 0; i3 < size; i3++) {
                    ContactListItem contactListItem3 = (ContactListItem) all.get(i3);
                    if (chatMsg2.uid.equalsIgnoreCase(contactListItem3.id) && chatMsg2.data != null && !TextUtils.isEmpty(chatMsg2.data.toString())) {
                        contactListItem3.inout = 2;
                        contactListItem3.mLastContent = chatMsg2.data;
                        contactListItem3.mLastUpdateTime = chatMsg2.time;
                        contactListItem3.mimeType = chatMsg2.type;
                        contactListItem3.status = chatMsg2.status;
                        break;
                    }
                }
            }
        }
        sortContactListItems(all);
        Logger.getInstance().debug(d, "getContactListItem", "Cost " + (System.currentTimeMillis() - currentTimeMillis) + " Contact size: " + all.size());
        return new b(all, i4, i2);
    }

    private void r() {
        this.p.setOnLoadMoreListener(new hc(this));
        this.p.setOnItemClickListener(new hd(this));
        this.p.setOnItemLongClickListener(new he(this));
    }

    private void c(ContactListItem contactListItem) {
        if (QsbkApp.currentUser != null) {
            Util.imStorageQueue.postRunnable(new hf(this, ChatMsgStoreProxy.newInstance(QsbkApp.currentUser.userId, 0), contactListItem));
        }
    }

    public void onHostCallback(String str) {
        Logger.getInstance().debug(d, "onHostCallback", "Host:" + str);
        if (TextUtils.isEmpty(str) || this.s == null) {
            b(1);
        } else {
            onConnectStatusChange(this.s.getConnectStatus());
        }
    }

    public void getAndInitPagerSlidingTabStrip(View view) {
        this.K = (Toolbar) view.findViewById(R.id.toolbar_message);
        this.K.setTitle("小纸条");
        this.K.setLogo(UIHelper.getActionBarQiushiIC());
        this.L = (ImageView) this.K.findViewById(R.id.add);
        this.L.setImageResource(UIHelper.getNewSubmitMenuIcon());
        this.M = this.K.findViewById(R.id.contact);
        this.N = (TextView) this.K.findViewById(R.id.new_fans_unread);
        this.N.setBackgroundResource(UIHelper.getNewMessageTips());
        if (e > 0) {
            this.N.setVisibility(0);
            this.N.setText(e > 99 ? "99+" : e + "");
        } else {
            this.N.setText("");
            this.N.setVisibility(8);
        }
        this.L.setOnClickListener(new hg(this));
        this.M.setOnClickListener(new hh(this));
    }

    public void onMessageReceived(ChatMsg chatMsg) {
        Logger.getInstance().debug(d, "onMessageReceived", chatMsg + "");
        this.h.b();
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
        Logger.getInstance().debug(d, "onGroupMessageReceived", chatMsg + "");
        this.h.b();
    }

    public void onChatMsgStatusChanged(long j, int i) {
        this.f.postDelayed(new hi(this, j, i), 50);
    }

    public void onDestroy() {
        super.onDestroy();
        this.O.unregisterReceiver(this.J);
        this.O.unregisterReceiver(this.g);
        getActivity().unregisterReceiver(this.b);
    }

    public void onStart() {
        super.onStart();
        k();
    }

    private void s() {
        if (getView() != null) {
            getView().findViewById(R.id.loading_layout).setVisibility(8);
        }
    }

    public void onStop() {
        super.onStop();
        b(2);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.s != null) {
            LogUtil.d("disconnect chat manager on 60 seconds");
            this.s.removeObserver(this);
            this.s.destroyConnectHost(this);
        }
        if (this.i != null) {
            this.i.clear();
        }
        b(2);
        this.B = null;
        this.p = null;
        if (this.H != null && !this.H.isClosed()) {
            this.H.close();
        }
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
        this.f.post(new hj(this));
    }

    public void onConnectStatusChange(int i) {
        Logger.getInstance().debug(d, "onConnectStatusChange", Thread.currentThread().getStackTrace()[3] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format("status change to[%s:%s]", new Object[]{Integer.valueOf(i), IChatMsgListener.connectString[i]}));
        this.f.post(new hk(this, i));
    }

    ContactListItem a(String str, int i) {
        for (ContactListItem contactListItem : this.i) {
            if (str.equals(contactListItem.id) && i == contactListItem.type) {
                return contactListItem;
            }
        }
        return null;
    }

    public void scrollToNextUnreadPosition() {
        int i = -1;
        int i2 = 0;
        if (isResumed() && this.p != null && this.q != null) {
            int firstVisiblePosition = this.p.getFirstVisiblePosition();
            int i3;
            if (this.i == null || this.i.size() <= 1) {
                i3 = 0;
            } else {
                int i4 = 0;
                i3 = 0;
                int i5 = -1;
                int i6 = firstVisiblePosition;
                while (i4 < this.i.size()) {
                    if (((ContactListItem) this.i.get(i4)).unreadCount > 0) {
                        if (i5 == -1) {
                            i5 = i4;
                        }
                        if (i4 > i3) {
                            i3 = i4;
                        }
                        if (i6 <= firstVisiblePosition && i4 > firstVisiblePosition) {
                            i6 = i4;
                        }
                    }
                    i4++;
                }
                i = i5;
                firstVisiblePosition = i6;
            }
            if (ListUtil.isOnBottom(this.p) || this.E == r3) {
                if (i > 0) {
                    i2 = i;
                }
                firstVisiblePosition = i2;
            }
            c(firstVisiblePosition);
        }
    }

    private void c(int i) {
        if (this.p != null) {
            this.p.smoothScrollToPositionFromTop(i, -2, 200);
            this.E = i;
        }
    }

    private boolean t() {
        SplashAdGroup group = SplashAdManager.instance().getGroup();
        if (group == null) {
            this.F.setVisibility(8);
            return false;
        }
        SplashAdItem activityItem = group.getActivityItem("im");
        int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
        if (activityItem == null || activityItem.startTime > correctTime || correctTime >= activityItem.endTime) {
            this.F.setVisibility(8);
        } else {
            this.G = activityItem;
            this.F.setVisibility(8);
            this.H = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(activityItem.picUrl), QsbkApp.mContext);
            this.H.subscribe(new hl(this, activityItem), UiThreadImmediateExecutorService.getInstance());
        }
        return true;
    }
}
