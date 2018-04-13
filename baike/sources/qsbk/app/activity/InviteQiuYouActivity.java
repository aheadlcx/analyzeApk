package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.alipay.sdk.cons.b;
import com.meetme.android.horizontallistview.HorizontalListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.QiuYouAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;

public class InviteQiuYouActivity extends BaseActionBarActivity implements OnItemClickListener {
    private static GroupInfo e;
    protected PtrLayout a;
    protected ListView b;
    protected QiuYouAdapter c;
    protected List<BaseUserInfo> d = new ArrayList();
    private GroupInfo f;
    private View g;
    private HorizontalListView h;
    private a i;
    private View j;

    private class a extends BaseAdapter {
        ArrayList<BaseUserInfo> a;
        final /* synthetic */ InviteQiuYouActivity b;

        private a(InviteQiuYouActivity inviteQiuYouActivity) {
            this.b = inviteQiuYouActivity;
            this.a = this.b.c.getChecked();
        }

        public int getCount() {
            return this.a == null ? 0 : this.a.size();
        }

        public BaseUserInfo getItem(int i) {
            return (BaseUserInfo) this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null) {
                view = this.b.getLayoutInflater().inflate(R.layout.layout_invite_avatar, viewGroup, false);
                imageView = (ImageView) view.findViewById(R.id.avatar);
                view.setTag(imageView);
            } else {
                imageView = (ImageView) view.getTag();
            }
            BaseUserInfo item = getItem(i);
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(item.userIcon, item.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                imageView.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(imageView, absoluteUrlOfMediumUserIcon);
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, GroupInfo groupInfo) {
        Intent intent = new Intent(context, InviteQiuYouActivity.class);
        e = groupInfo;
        context.startActivity(intent);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.f = e;
        if (e == null) {
            finish();
        }
        g();
    }

    private void g() {
        this.g = findViewById(R.id.selected_group);
        this.h = (HorizontalListView) findViewById(R.id.avatar_list);
        this.j = findViewById(R.id.invite_button);
        this.j.setOnClickListener(new qa(this));
        this.a = (PtrLayout) findViewById(R.id.ptr);
        this.b = (ListView) findViewById(R.id.listview);
        View inflate = getLayoutInflater().inflate(R.layout.layout_invite, this.b, false);
        inflate.findViewById(R.id.friend_item).setOnClickListener(new qb(this));
        this.b.addHeaderView(inflate);
        this.a.setRefreshEnable(false);
        this.a.setLoadMoreEnable(false);
        this.b.setOnItemClickListener(this);
        this.c = new QiuYouAdapter(this, this.d, true);
        this.b.setAdapter(this.c);
        this.i = new a();
        this.h.setAdapter(this.i);
        e();
    }

    protected void e() {
        showLoading();
        ContactListItemStore.getContactStore(QsbkApp.currentUser.userId).getAllAsync(new qc(this));
    }

    private void a(List<ContactListItem> list) {
        new GroupMemberManager(this.f).loadMember(new qd(this, list));
    }

    private void a(List<ContactListItem> list, ArrayList<BaseUserInfo> arrayList) {
        for (ContactListItem contactListItem : list) {
            if (contactListItem.type == 0) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.userName = contactListItem.name;
                baseUserInfo.userIcon = contactListItem.icon;
                baseUserInfo.userId = contactListItem.id;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (baseUserInfo.userId.equals(((BaseUserInfo) it.next()).userId)) {
                        baseUserInfo.alreadyInGroup = true;
                        break;
                    }
                }
                this.d.add(baseUserInfo);
            }
        }
        this.c.notifyDataSetChanged();
    }

    protected void a(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在邀请中...");
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.f.id));
        hashMap.put("uids", str);
        String format = String.format(Constants.URL_INVITE_JOIN_GROUP, new Object[]{Integer.valueOf(this.f.id)});
        HttpTask httpTask = new HttpTask(format, format, new qe(this, progressDialog));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.b);
        if (i >= headerCount) {
            this.c.toggleCheck(i - headerCount, view);
            ArrayList checked = this.c.getChecked();
            this.i.notifyDataSetChanged();
            if (checked.size() > 0) {
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
        }
    }

    protected String f() {
        return "邀请糗友";
    }

    protected int a() {
        return R.layout.layout_invite_activity;
    }

    public void finish() {
        super.finish();
    }
}
