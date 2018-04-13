package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.stat.DeviceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.IChatMsgListener;
import qsbk.app.im.TimeUtils;
import qsbk.app.im.UserChatManager;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CampaignInfo;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class GroupNoticeActivity extends BaseActionBarActivity implements IChatMsgListener, PtrListener {
    private int a = 1;
    private GroupNoticeStore b;
    private CampaignInfo c;
    private a d;
    private PtrLayout e;
    private ListView f;
    private ArrayList<Object> g = new ArrayList();
    private SimpleHttpTask h = null;
    private UserChatManager i;

    private class a extends BaseImageAdapter {
        final /* synthetic */ GroupNoticeActivity a;

        class a {
            final /* synthetic */ a a;
            private int b;
            public TextView mAgeTV;
            public ImageView mAvatarIV;
            public TextView mButton;
            public View mDivider;
            public LinearLayout mGenderAgeLL;
            public ImageView mGenderIV;
            public TextView mMsg;
            public TextView mNameTV;
            public TextView mTime;

            a(a aVar) {
                this.a = aVar;
            }
        }

        class b {
            final /* synthetic */ a a;
            public ImageView mAvatarIV;
            public View mDivider;
            public TextView mInfo;
            public TextView mMsg;
            public TextView mNameTV;
            public TextView mTime;

            b(a aVar) {
                this.a = aVar;
            }
        }

        public a(GroupNoticeActivity groupNoticeActivity, ArrayList<Object> arrayList) {
            this.a = groupNoticeActivity;
            super(arrayList, groupNoticeActivity);
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            switch (((GroupNotice) getItem(i)).type) {
                case 1:
                case 2:
                case 12:
                    return 0;
                default:
                    return 1;
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (getItemViewType(i) == 0) {
                return getJoinView(i, view, viewGroup);
            }
            return getMsgView(i, view, viewGroup);
        }

        public View getMsgView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            int i2;
            int i3 = -12171438;
            if (view == null) {
                bVar = new b(this);
                view = this.a.getLayoutInflater().inflate(R.layout.layout_notice_msg, viewGroup, false);
                bVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
                bVar.mNameTV = (TextView) view.findViewById(R.id.name);
                bVar.mInfo = (TextView) view.findViewById(R.id.info);
                bVar.mMsg = (TextView) view.findViewById(R.id.msg);
                bVar.mTime = (TextView) view.findViewById(R.id.time);
                bVar.mDivider = view.findViewById(R.id.divider);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.mDivider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
            bVar.mNameTV.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            TextView textView = bVar.mInfo;
            if (UIHelper.isNightTheme()) {
                i2 = -12171438;
            } else {
                i2 = -6908266;
            }
            textView.setTextColor(i2);
            bVar.mMsg.setTextColor(UIHelper.isNightTheme() ? -12171426 : -10263970);
            TextView textView2 = bVar.mTime;
            if (!UIHelper.isNightTheme()) {
                i3 = -6908266;
            }
            textView2.setTextColor(i3);
            GroupNotice groupNotice = (GroupNotice) getItem(i);
            GroupBriefInfo groupBriefInfo = groupNotice.tribe;
            b(bVar.mAvatarIV, groupBriefInfo.icon);
            bVar.mAvatarIV.setOnClickListener(new nr(this, groupBriefInfo));
            bVar.mNameTV.setText(groupBriefInfo.name);
            if (TextUtils.isEmpty(groupNotice.handler)) {
                bVar.mInfo.setVisibility(8);
            } else {
                bVar.mInfo.setVisibility(0);
                if (groupNotice.type == 11) {
                    bVar.mInfo.setText("申请无效");
                } else {
                    bVar.mInfo.setText("处理人：" + groupNotice.handler);
                }
            }
            if (groupNotice.type == 4) {
                bVar.mMsg.setCompoundDrawablesWithIntrinsicBounds(UIHelper.isNightTheme() ? R.drawable.group_attention_night : R.drawable.group_attention, 0, 0, 0);
            } else {
                bVar.mMsg.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            bVar.mMsg.setText(groupNotice.detail);
            bVar.mTime.setText(TimeUtils.formatTime(groupNotice.time));
            if (groupNotice.type == 5) {
                view.setOnClickListener(new ns(this, groupNotice));
            } else {
                view.setOnClickListener(new nt(this, groupBriefInfo));
            }
            return view;
        }

        public View getJoinView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            int i2;
            if (view == null) {
                aVar = new a(this);
                view = this.a.getLayoutInflater().inflate(R.layout.layout_notice_join, viewGroup, false);
                aVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
                aVar.mNameTV = (TextView) view.findViewById(R.id.name);
                aVar.mGenderAgeLL = (LinearLayout) view.findViewById(R.id.gender_age);
                aVar.mGenderIV = (ImageView) view.findViewById(R.id.gender);
                aVar.mAgeTV = (TextView) view.findViewById(R.id.age);
                aVar.mMsg = (TextView) view.findViewById(R.id.msg);
                aVar.mTime = (TextView) view.findViewById(R.id.time);
                aVar.mButton = (TextView) view.findViewById(R.id.button);
                aVar.mDivider = view.findViewById(R.id.divider);
                aVar.mButton.setOnClickListener(new nu(this, aVar));
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.b = i;
            GroupNotice groupNotice = (GroupNotice) getItem(i);
            aVar.mDivider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
            aVar.mNameTV.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            aVar.mMsg.setTextColor(UIHelper.isNightTheme() ? -12171426 : -10263970);
            aVar.mTime.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
            BaseUserInfo baseUserInfo = groupNotice.user;
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                aVar.mAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                b(aVar.mAvatarIV, absoluteUrlOfMediumUserIcon);
            }
            aVar.mAvatarIV.setOnClickListener(new UserClickDelegate(baseUserInfo.userId, new nw(this, groupNotice, baseUserInfo)));
            CharSequence remark = RemarkManager.getRemark(baseUserInfo.userId);
            if (TextUtils.isEmpty(remark)) {
                aVar.mNameTV.setText(baseUserInfo.userName);
            } else {
                aVar.mNameTV.setText(remark);
            }
            if (!UIHelper.isNightTheme()) {
                if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                    aVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_female_bg);
                    aVar.mGenderIV.setImageResource(R.drawable.pinfo_female);
                } else {
                    aVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_man_bg);
                    aVar.mGenderIV.setImageResource(R.drawable.pinfo_male);
                }
                aVar.mAgeTV.setTextColor(-1);
            } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                aVar.mGenderIV.setImageResource(R.drawable.pinfo_female_dark);
                aVar.mAgeTV.setTextColor(this.a.getResources().getColor(R.color.age_female));
            } else {
                aVar.mGenderIV.setImageResource(R.drawable.pinfo_male_dark);
                aVar.mAgeTV.setTextColor(this.a.getResources().getColor(R.color.age_male));
            }
            aVar.mMsg.setText(groupNotice.detail);
            aVar.mAgeTV.setText(baseUserInfo.age + "");
            aVar.mTime.setText(TimeUtils.formatTime(groupNotice.time));
            int i3 = groupNotice.act;
            aVar.mButton.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_agree_button_night : R.drawable.group_agree_button);
            TextView textView = aVar.mButton;
            if (UIHelper.isNightTheme()) {
                i2 = -5066062;
            } else {
                i2 = -1;
            }
            textView.setTextColor(i2);
            if (i3 == -1) {
                aVar.mButton.setVisibility(4);
            } else {
                aVar.mButton.setVisibility(0);
            }
            if (i3 == 0) {
                aVar.mButton.setEnabled(true);
                aVar.mButton.setText("同意");
            } else if (i3 == 1) {
                aVar.mButton.setEnabled(false);
                aVar.mButton.setText("已同意");
            } else if (i3 == 2) {
                aVar.mButton.setEnabled(false);
                aVar.mButton.setText("已拒绝");
            } else if (i3 == 3) {
                aVar.mButton.setEnabled(false);
                aVar.mButton.setText("已申请");
            } else {
                aVar.mButton.setEnabled(false);
                aVar.mButton.setText("已失效");
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, GroupNoticeActivity.class));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == GroupNoticeDetailActivity.REQ_CODE) {
            int i3 = i2 & 255;
            if (i3 > 0) {
                ((GroupNotice) this.g.get(i2 >> 8)).act = i3;
                this.d.notifyDataSetChanged();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.i == null) {
            this.i = UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token);
            this.i.addObserver(this);
        }
        this.i.setChatContext(1, null);
    }

    private void a(GroupNotice groupNotice) {
        new Builder(this).setItems(new String[]{"删除"}, new ni(this, groupNotice)).create().show();
    }

    private void b(GroupNotice groupNotice) {
        this.b.deleteMessage(groupNotice);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在处理中...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String str = Constants.URL_NOTICE_LIST;
        Map hashMap = new HashMap();
        hashMap.put(DeviceInfo.TAG_MID, groupNotice.msgid);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(str, new nj(this, groupNotice, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b = null;
        if (this.i != null) {
            this.i.removeObserver(this);
        }
    }

    protected String e() {
        return "群通知";
    }

    protected int a() {
        return R.layout.layout_ptr_listview;
    }

    protected void a(Bundle bundle) {
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        setActionbarBackable();
        this.b = GroupNoticeStore.getInstance(QsbkApp.currentUser.userId);
        this.e = (PtrLayout) findViewById(R.id.ptr);
        this.f = (ListView) findViewById(R.id.listview);
        this.e.setRefreshEnable(true);
        this.e.setLoadMoreEnable(false);
        this.d = new a(this, this.g);
        this.f.setAdapter(this.d);
        this.e.setPtrListener(this);
        this.f.setOnItemClickListener(new nk(this));
        this.f.setOnItemLongClickListener(new nl(this));
        this.e.refresh();
    }

    private void a(int i) {
        this.h = new SimpleHttpTask(Constants.URL_NOTICE_LIST + "?page=" + i, new nm(this, i));
        this.h.execute();
    }

    public void onRefresh() {
        this.a = 1;
        a(1);
    }

    public void onLoadMore() {
        a(this.a + 1);
    }

    public void onMessageReceived(ChatMsg chatMsg) {
        runOnUiThread(new nn(this));
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
    }

    public void onChatMsgStatusChanged(long j, int i) {
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
        runOnUiThread(new no(this));
    }

    public void onConnectStatusChange(int i) {
        runOnUiThread(new np(this, i));
    }

    private void b(int i) {
        new SimpleHttpTask(String.format(Constants.URL_TRANSITION, new Object[]{Integer.valueOf(i)}) + "?tid=" + i, new nq(this, i)).execute();
    }
}
