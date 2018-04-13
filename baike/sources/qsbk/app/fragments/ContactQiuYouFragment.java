package qsbk.app.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.adapter.ContactQiuYouAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.EventWindow;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.recyclerview.ItemClickSupport;
import qsbk.app.widget.recyclerview.ItemClickSupport.OnItemClickListener;

public class ContactQiuYouFragment extends StatisticFragment implements PtrListener, OnItemClickListener {
    public static final String QIU_YOU_RELATION_CHANGED = "QIU_YOU_RELATION_CHANGED";
    private static final String l = ContactQiuYouFragment.class.getSimpleName();
    protected boolean a;
    protected PtrLayout b;
    protected RecyclerView c;
    protected TipsHelper d;
    protected String e;
    protected int f;
    protected boolean g;
    protected ContactQiuYouAdapter h;
    protected ArrayList<BaseUserInfo> i;
    protected String j;
    QiuYouListener k;
    private LocalBroadcastManager m;
    private BroadcastReceiver n;
    private BroadcastReceiver o;
    private Bundle p;
    private IMChatMsgSource q;
    private UserChatManager r;
    private boolean s;
    private int t;
    private boolean u;

    public interface QiuYouListener {
        void qiuYouNum(int i, String str);
    }

    public ContactQiuYouFragment() {
        this.a = true;
        this.f = 1;
        this.g = true;
        this.i = new ArrayList();
        this.m = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.u = false;
        this.j = getClass().getSimpleName();
    }

    public ContactQiuYouFragment(String str, String str2) {
        this.a = true;
        this.f = 1;
        this.g = true;
        this.i = new ArrayList();
        this.m = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.u = false;
        setStatisticsEvent(str2);
        this.f = 0;
        this.j = str2;
        this.e = str;
    }

    public ContactQiuYouFragment(String str, String str2, QiuYouListener qiuYouListener) {
        this.a = true;
        this.f = 1;
        this.g = true;
        this.i = new ArrayList();
        this.m = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.u = false;
        setStatisticsEvent(str2);
        this.e = str;
        this.f = 0;
        this.k = qiuYouListener;
        this.j = str2;
    }

    public ContactQiuYouFragment(String str, String str2, Bundle bundle) {
        boolean z;
        boolean z2 = true;
        this.a = true;
        this.f = 1;
        this.g = true;
        this.i = new ArrayList();
        this.m = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.u = false;
        DebugUtil.debug(l, "QiuYouFragment," + bundle);
        setStatisticsEvent(str2);
        this.e = str;
        this.f = 0;
        this.s = true;
        this.p = bundle;
        if (this.p == null || !this.p.getBoolean("shared")) {
            z = false;
        } else {
            z = true;
        }
        this.s = z;
        if (this.p == null || !this.p.getBoolean("fromAt")) {
            z2 = false;
        }
        this.u = z2;
        this.j = str2;
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        LogUtil.e("needRefresh:" + this.a);
        if (this.a) {
            this.b.refresh();
            this.a = false;
        }
    }

    public void onAttach(Activity activity) {
        if (activity instanceof QiuYouListener) {
            this.k = (QiuYouListener) activity;
        }
        super.onAttach(activity);
    }

    public void onStop() {
        super.onStop();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.m = LocalBroadcastManager.getInstance(getActivity());
        this.n = new cc(this);
        this.o = new cd(this);
        this.m.registerReceiver(this.n, new IntentFilter("QIU_YOU_RELATION_CHANGED"));
        this.m.registerReceiver(this.o, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        LogUtil.e("onCreateView...");
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_recyclerview, null);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        this.d = new TipsHelper(view.findViewById(R.id.tips));
        this.b = (PtrLayout) view.findViewById(R.id.ptr);
        this.c = (RecyclerView) view.findViewById(R.id.listview);
        this.c.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemClickSupport.addTo(this.c).setOnItemClickListener(this);
        this.b.setLoadMoreEnable(true);
        this.b.setPtrListener(this);
        this.h = new ContactQiuYouAdapter(getActivity(), this.i);
        this.h.setShowRelationship(this.k != null);
        this.c.setAdapter(this.h);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.m != null) {
            if (this.n != null) {
                this.m.unregisterReceiver(this.n);
            }
            if (this.o != null) {
                this.m.unregisterReceiver(this.o);
            }
        }
    }

    protected void c() {
        QsbkApp.getInstance();
        if (QsbkApp.currentUser == null) {
            getActivity().finish();
            return;
        }
        StringBuilder append = new StringBuilder().append("initData, id=");
        QsbkApp.getInstance();
        LogUtil.e(append.append(QsbkApp.currentUser.userId).toString());
        QsbkApp.getInstance();
        this.r = UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token);
    }

    public void onRefresh() {
        this.d.hide();
        this.f = 1;
        this.g = true;
        b();
    }

    public void onLoadMore() {
        b();
    }

    protected void b() {
        if (this.g) {
            String str = this.e;
            r1 = new Object[2];
            QsbkApp.getInstance();
            r1[0] = QsbkApp.currentUser.userId;
            r1[1] = Integer.valueOf(this.f);
            new SimpleHttpTask(String.format(str, r1), new ce(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void onItemClicked(RecyclerView recyclerView, int i, View view) {
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.i.get(i);
        if (this.s) {
            a(baseUserInfo);
        } else if (this.u) {
            b(baseUserInfo);
        } else if (e()) {
            a(getActivity(), baseUserInfo);
            getActivity().finish();
        } else if (FansFragment.class.getSimpleName().equals(this.j)) {
            MyInfoActivity.launch(getActivity(), baseUserInfo.userId, MyInfoActivity.FANS_ORIGINS[5], new IMChatMsgSource(5, baseUserInfo.userId, "来自糗友搜索"), true);
        } else {
            MyInfoActivity.launch(getActivity(), baseUserInfo.userId, MyInfoActivity.FANS_ORIGINS[5], new IMChatMsgSource(5, baseUserInfo.userId, "来自糗友搜索"), true);
        }
    }

    private void a(BaseUserInfo baseUserInfo) {
        CharSequence charSequence = "确定发给\n" + baseUserInfo.userName;
        DebugUtil.debug(l, "showShareDialog " + baseUserInfo.userName);
        Builder builder = new Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.share_dialog, null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(charSequence);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.confirm, new cf(this, baseUserInfo));
        builder.setNegativeButton(R.string.cancel, new cg(this));
        builder.create().show();
    }

    private void b(BaseUserInfo baseUserInfo) {
        Intent intent = new Intent();
        intent.putExtra(QsbkDatabase.USER_TABLE_NAME, baseUserInfo);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    private void c(BaseUserInfo baseUserInfo) {
        int i = this.p.getInt("share_type", ShareToImType.TYPE_ARTICLE.getValue());
        String makeMsgData;
        String qiushiShareSummary;
        ContactListItem contactListItem;
        if (i == ShareToImType.TYPE_CIRCLE_ARTICLE.getValue()) {
            makeMsgData = ShareUtils.makeMsgData(this.p);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.p);
            DebugUtil.debug(l, "shareQiushiArticleToFriends, " + makeMsgData + ",summary=" + qiushiShareSummary);
            contactListItem = new ContactListItem();
            contactListItem.id = baseUserInfo.userId;
            contactListItem.name = baseUserInfo.userName;
            contactListItem.icon = baseUserInfo.userIcon;
            this.r.sendQiuyouCircleShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_LIVE.getValue()) {
            QsbkCommonVideo qsbkCommonVideo = (QsbkCommonVideo) this.p.getSerializable(EventWindow.JUMP_LIVE_ROOM);
            JSONObject jSONObject = new JSONObject();
            qiushiShareSummary = qsbkCommonVideo.author.name + "正在直播，颜值爆表~快来一起看！" + qsbkCommonVideo.content;
            try {
                jSONObject.put("content", qiushiShareSummary);
                jSONObject.put("live_id", qsbkCommonVideo.live_id);
                jSONObject.put("pic_url", qsbkCommonVideo.getPicUrl());
                jSONObject.put("title", qiushiShareSummary);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            makeMsgData = jSONObject.toString();
            contactListItem = new ContactListItem();
            contactListItem.id = baseUserInfo.userId;
            contactListItem.name = baseUserInfo.userName;
            contactListItem.icon = baseUserInfo.userIcon;
            this.r.sendLiveShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_WEB.getValue() || i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue() || i == ShareToImType.TYPE_NEWS.getValue()) {
            ShareMsgItem shareMsgItem = (ShareMsgItem) this.p.getSerializable("share_item");
            String jSONObject2 = shareMsgItem.toJson().toString();
            makeMsgData = "";
            if (i == ShareToImType.TYPE_WEB.getValue()) {
                qiushiShareSummary = shareMsgItem.content;
                makeMsgData = jSONObject2;
            } else if (i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue()) {
                qiushiShareSummary = shareMsgItem.title;
                makeMsgData = jSONObject2;
            } else {
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("title", shareMsgItem.title);
                    jSONObject3.put("image_url", shareMsgItem.imageUrl);
                    jSONObject3.put("news_id", shareMsgItem.news_id);
                    jSONObject2 = jSONObject3.toString();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                qiushiShareSummary = shareMsgItem.title;
                makeMsgData = jSONObject2;
            }
            contactListItem = new ContactListItem();
            contactListItem.id = baseUserInfo.userId;
            contactListItem.name = baseUserInfo.userName;
            contactListItem.icon = baseUserInfo.userIcon;
            this.r.sendWebShare(contactListItem, makeMsgData, qiushiShareSummary, null, true, i);
            DebugUtil.debug(l, "Contact id=" + contactListItem.id + ",name=" + contactListItem.name);
        } else if (i == ShareToImType.TYPE_QSJX.getValue()) {
            Qsjx qsjx = (Qsjx) this.p.getSerializable(QYQShareInfo.TYPE_QSJX);
            contactListItem = new ContactListItem();
            contactListItem.id = baseUserInfo.userId;
            contactListItem.name = baseUserInfo.userName;
            contactListItem.icon = baseUserInfo.userIcon;
            if (qsjx != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQsjxShare(contactListItem, qsjx, null, true);
            }
            DebugUtil.debug(l, "Contact id=" + contactListItem.id + ",name=" + contactListItem.name);
        } else {
            if (this.p.getBoolean("isFromCircleTopic")) {
                makeMsgData = ShareUtils.makeShareMsgData(this.p);
            } else {
                makeMsgData = ShareUtils.makeMsgData(this.p);
            }
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(this.p);
            DebugUtil.debug(l, "shareQiushiArticleToFriends, " + makeMsgData + ",summary=" + qiushiShareSummary);
            contactListItem = new ContactListItem();
            contactListItem.id = baseUserInfo.userId;
            contactListItem.name = baseUserInfo.userName;
            contactListItem.icon = baseUserInfo.userIcon;
            if (this.p.getBoolean("isFromCircleTopic")) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendCircleTopicShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
            } else {
                this.r.sendQiushiShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
            }
            DebugUtil.debug(l, "Contact id=" + contactListItem.id + ",name=" + contactListItem.name);
        }
    }

    private boolean e() {
        return this.p == null ? false : this.p.getBoolean(Constants.START_A_CONVERSATION, false);
    }

    private void a(Context context, BaseUserInfo baseUserInfo) {
        DebugUtil.debug(l, "startAnConversation, userInfo=" + baseUserInfo.userName);
        Intent intent = new Intent(context, ConversationActivity.class);
        QsbkApp.getInstance();
        intent.putExtra("user_id", QsbkApp.currentUser.userId);
        intent.putExtra("to_id", baseUserInfo.userId);
        intent.putExtra(IMChatBaseActivity.TO_ICON, baseUserInfo.userIcon);
        intent.putExtra(IMChatBaseActivity.TO_NICK, baseUserInfo.userName);
        intent.putExtra(ConversationActivity.RELATIONSHIP, baseUserInfo.relationship);
        startActivity(intent);
    }

    protected void a() {
        DebugUtil.debug("luolong", "showEmptyPrompt, " + this.e);
        String str = "";
        if (this.e.equals(Constants.REL_GET_FRIENDS)) {
            str = getResources().getString(R.string.no_friends_prompt);
        } else if (this.e.equals(Constants.REL_GET_FOLLOWS)) {
            str = getResources().getString(R.string.no_follows_prompt);
        } else if (this.e.equals(Constants.REL_GET_FANS)) {
            str = getResources().getString(R.string.no_fans_prompt);
        } else if (this.e.equals(Constants.REL_GET_BLACKLIST)) {
            str = getResources().getString(R.string.no_balck_list);
        }
        this.d.set(UIHelper.getEmptyImg(), str);
        this.d.show();
    }

    public ArrayList<BaseUserInfo> getCacheData() {
        return this.i;
    }
}
