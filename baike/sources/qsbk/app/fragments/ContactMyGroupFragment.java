package qsbk.app.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
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
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.adapter.ContactGroupAdapter;
import qsbk.app.fragments.ContactQiuYouFragment.QiuYouListener;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.GroupConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.EventWindow;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.GroupNotifier.OnNotifyListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.recyclerview.ItemClickSupport;
import qsbk.app.widget.recyclerview.ItemClickSupport.OnItemClickListener;
import qsbk.app.widget.recyclerview.ItemClickSupport.OnItemLongClickListener;

public class ContactMyGroupFragment extends StatisticFragment implements OnNotifyListener, PtrListener, OnItemClickListener, OnItemLongClickListener {
    public static final int NEW_CONVERSATION = 1;
    public static final int NORMAL = 0;
    public static final int SHARE_ONLY = 2;
    protected PtrLayout a;
    protected RecyclerView b;
    QiuYouListener c;
    String d;
    private ArrayList<Object> e;
    private ContactGroupAdapter f;
    private int g;
    private TipsHelper h;
    private int i = 1;
    private HttpTask j;

    public static ContactMyGroupFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        ContactMyGroupFragment contactMyGroupFragment = new ContactMyGroupFragment();
        contactMyGroupFragment.setArguments(bundle);
        return contactMyGroupFragment;
    }

    public static ContactMyGroupFragment newInstance(Bundle bundle, int i) {
        ContactMyGroupFragment contactMyGroupFragment = new ContactMyGroupFragment();
        bundle.putInt("type", i);
        contactMyGroupFragment.setArguments(bundle);
        return contactMyGroupFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = getArguments().getInt("type");
        GroupNotifier.register(this);
    }

    public void onAttach(Activity activity) {
        if (activity instanceof QiuYouListener) {
            this.c = (QiuYouListener) activity;
        }
        this.d = ContactMyGroupFragment.class.getSimpleName();
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
        GroupNotifier.unregister(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_recyclerview, null);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        boolean z;
        this.a = (PtrLayout) view.findViewById(R.id.ptr);
        this.b = (RecyclerView) view.findViewById(R.id.listview);
        this.b.setLayoutManager(new LinearLayoutManager(getContext()));
        this.a.setLoadMoreEnable(false);
        this.a.setPtrListener(this);
        ItemClickSupport.addTo(this.b).setOnItemClickListener(this).setOnItemLongClickListener(this);
        if (this.e == null) {
            this.e = new ArrayList();
            z = this.j == null;
        } else {
            z = false;
        }
        this.f = new ContactGroupAdapter(this.e, 0);
        this.b.setAdapter(this.f);
        if (z) {
            this.a.refresh();
        }
        this.h = new TipsHelper(view.findViewById(R.id.tips));
    }

    private void a() {
        if (this.h != null) {
            this.h.set(UIHelper.getEmptyImg(), getResources().getString(R.string.no_group_prompt));
            this.h.show();
        }
    }

    private void b() {
        if (this.h != null) {
            this.h.hide();
        }
    }

    private void a(int i) {
        String str = Constants.URL_MY_GROUP_LIST + "?page=" + this.i;
        this.j = new HttpTask(str, str, new bz(this, i));
        this.j.execute(new Void[0]);
    }

    public void onRefresh() {
        this.i = 1;
        a(this.i);
    }

    public void onLoadMore() {
        a(this.i);
    }

    public void onItemClicked(RecyclerView recyclerView, int i, View view) {
        Object obj = this.e.get(recyclerView.getChildAdapterPosition(view));
        if (obj instanceof GroupBriefInfo) {
            GroupBriefInfo groupBriefInfo = (GroupBriefInfo) obj;
            if (this.g == 0) {
                GroupInfoActivity.launch(getActivity(), groupBriefInfo);
            } else if (this.g == 1) {
                Intent intent = new Intent(getActivity(), GroupConversationActivity.class);
                intent.putExtra(IMChatBaseActivity.USER_TYPE, 3);
                intent.putExtra("user_id", QsbkApp.currentUser.userId);
                intent.putExtra("to_id", String.valueOf(groupBriefInfo.id));
                intent.putExtra(IMChatBaseActivity.TO_ICON, groupBriefInfo.icon);
                intent.putExtra(IMChatBaseActivity.TO_NICK, groupBriefInfo.name);
                startActivity(intent);
                getActivity().finish();
            } else if (this.g == 2) {
                a(groupBriefInfo, getArguments());
            }
        }
    }

    public boolean onItemLongClicked(RecyclerView recyclerView, int i, View view) {
        return false;
    }

    private void a(GroupBriefInfo groupBriefInfo, Bundle bundle) {
        CharSequence charSequence = "确定发给\n" + groupBriefInfo.name;
        Builder builder = new Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.share_dialog, null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(charSequence);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.confirm, new ca(this, groupBriefInfo, bundle));
        builder.setNegativeButton(R.string.cancel, new cb(this));
        builder.create().show();
    }

    private void b(GroupBriefInfo groupBriefInfo, Bundle bundle) {
        int i = bundle.getInt("share_type", ShareToImType.TYPE_ARTICLE.getValue());
        String makeMsgData;
        String qiushiShareSummary;
        ContactListItem contactListItem;
        if (i == ShareToImType.TYPE_CIRCLE_ARTICLE.getValue()) {
            makeMsgData = ShareUtils.makeMsgData(bundle);
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(bundle);
            contactListItem = new ContactListItem();
            contactListItem.id = groupBriefInfo.id + "";
            contactListItem.name = groupBriefInfo.name;
            contactListItem.icon = groupBriefInfo.icon;
            contactListItem.type = 3;
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiuyouCircleShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_LIVE.getValue()) {
            QsbkCommonVideo qsbkCommonVideo = (QsbkCommonVideo) bundle.getSerializable(EventWindow.JUMP_LIVE_ROOM);
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
            contactListItem.id = groupBriefInfo.id + "";
            contactListItem.name = groupBriefInfo.name;
            contactListItem.icon = groupBriefInfo.icon;
            contactListItem.type = 3;
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendLiveShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        } else if (i == ShareToImType.TYPE_WEB.getValue() || i == ShareToImType.TYPE_LIVE_ACTIVITY.getValue() || i == ShareToImType.TYPE_NEWS.getValue()) {
            ShareMsgItem shareMsgItem = (ShareMsgItem) bundle.getSerializable("share_item");
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
            contactListItem.id = groupBriefInfo.id + "";
            contactListItem.name = groupBriefInfo.name;
            contactListItem.icon = groupBriefInfo.icon;
            contactListItem.type = 3;
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendWebShare(contactListItem, makeMsgData, qiushiShareSummary, null, true, i);
        } else if (i == ShareToImType.TYPE_QSJX.getValue()) {
            Qsjx qsjx = (Qsjx) bundle.getSerializable(QYQShareInfo.TYPE_QSJX);
            contactListItem = new ContactListItem();
            contactListItem.id = groupBriefInfo.id + "";
            contactListItem.name = groupBriefInfo.name;
            contactListItem.icon = groupBriefInfo.icon;
            contactListItem.type = 3;
            if (qsjx != null) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQsjxShare(contactListItem, qsjx, null, true);
            }
        } else {
            if (bundle.getBoolean("isFromCircleTopic")) {
                makeMsgData = ShareUtils.makeShareMsgData(bundle);
            } else {
                makeMsgData = ShareUtils.makeMsgData(bundle);
            }
            qiushiShareSummary = ShareUtils.getQiushiShareSummary(bundle);
            contactListItem = new ContactListItem();
            contactListItem.id = groupBriefInfo.id + "";
            contactListItem.name = groupBriefInfo.name;
            contactListItem.icon = groupBriefInfo.icon;
            contactListItem.type = 3;
            if (bundle.getBoolean("isFromCircleTopic")) {
                QsbkApp.getInstance();
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendCircleTopicShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
                return;
            }
            QsbkApp.getInstance();
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendQiushiShare(contactListItem, makeMsgData, qiushiShareSummary, null, true);
        }
    }

    public void onGroupNotify(int i, int i2) {
        if (this.b != null) {
            onRefresh();
        }
    }
}
