package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupLeader;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class GroupLeaderListActivity extends BaseActionBarActivity implements PtrListener {
    public static final String GROUP_INFO = "group_info";
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private PtrLayout b;
    private ListView c;
    private ArrayList<Object> d = new ArrayList();
    private a e;
    private GroupInfo f;

    class a extends BaseImageAdapter {
        final /* synthetic */ GroupLeaderListActivity a;

        a(GroupLeaderListActivity groupLeaderListActivity, Activity activity, ArrayList<Object> arrayList) {
            this.a = groupLeaderListActivity;
            super(arrayList, activity);
        }

        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        public int getItemViewType(int i) {
            return super.getItemViewType(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            int i2;
            if (view == null) {
                view = this.n.inflate(R.layout.layout_group_leader_item, null);
                bVar = new b(this.a);
                bVar.a = (ImageView) view.findViewById(R.id.user_icon);
                bVar.b = (TextView) view.findViewById(R.id.username);
                bVar.c = view.findViewById(R.id.gender_age);
                bVar.d = (ImageView) view.findViewById(R.id.gender);
                bVar.e = (TextView) view.findViewById(R.id.age);
                bVar.f = (TextView) view.findViewById(R.id.team_num);
                bVar.g = (TextView) view.findViewById(R.id.up_date);
                bVar.h = (TextView) view.findViewById(R.id.up_number);
                bVar.i = (TextView) view.findViewById(R.id.down_sign);
                bVar.j = (TextView) view.findViewById(R.id.down_date);
                bVar.k = (TextView) view.findViewById(R.id.down_number);
                bVar.l = (ImageView) view.findViewById(R.id.group_member_sign);
                bVar.m = (TextView) view.findViewById(R.id.group_member_info);
                bVar.n = view.findViewById(R.id.divider);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            GroupLeader groupLeader = (GroupLeader) getItem(i);
            a(bVar.a, null, true);
            a(bVar.a, QsbkApp.absoluteUrlOfMediumUserIcon(groupLeader.icon, String.valueOf(groupLeader.userId)), true);
            bVar.b.setText(groupLeader.login);
            bVar.b.setTextColor(UIHelper.isNightTheme() ? -9802626 : -16777216);
            if ("F".equalsIgnoreCase(groupLeader.gender)) {
                if (UIHelper.isNightTheme()) {
                    bVar.d.setImageResource(R.drawable.pinfo_female_dark);
                    bVar.e.setTextColor(UIHelper.getColor(R.color.age_female));
                } else {
                    bVar.c.setBackgroundResource(R.drawable.pinfo_female_bg);
                    bVar.d.setImageResource(R.drawable.pinfo_female);
                    bVar.e.setTextColor(UIHelper.getColor(R.color.white));
                }
            } else if (UIHelper.isNightTheme()) {
                bVar.d.setImageResource(R.drawable.pinfo_male_dark);
                bVar.e.setTextColor(UIHelper.getColor(R.color.age_male));
            } else {
                bVar.c.setBackgroundResource(R.drawable.pinfo_man_bg);
                bVar.d.setImageResource(R.drawable.pinfo_male);
                bVar.e.setTextColor(UIHelper.getColor(R.color.white));
            }
            bVar.e.setText(String.valueOf(groupLeader.age));
            if (TextUtils.equals(groupLeader.teamNum, "现任群大")) {
                bVar.f.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_current_leader_bg_night : R.drawable.group_current_leader_bg);
                bVar.f.setTextColor(UIHelper.isNightTheme() ? -5066062 : -1);
                bVar.f.setText(groupLeader.teamNum);
                bVar.g.setText(GroupLeaderListActivity.a.format(Long.valueOf(groupLeader.upDate * 1000)));
                bVar.g.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.h.setText(String.valueOf(groupLeader.upMember));
                bVar.h.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.i.setText("至今");
                groupLeader.downMember = this.a.f.memberNum;
                bVar.j.setText(null);
                bVar.k.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.k.setText(String.valueOf(groupLeader.downMember));
            } else {
                bVar.f.setBackgroundColor(0);
                bVar.f.setTextColor(UIHelper.isNightTheme() ? -12171434 : -6908266);
                bVar.f.setText(groupLeader.teamNum);
                bVar.g.setText(GroupLeaderListActivity.a.format(Long.valueOf(groupLeader.upDate * 1000)));
                bVar.g.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.h.setText(String.valueOf(groupLeader.upMember));
                bVar.h.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.i.setText("卸任 :");
                bVar.j.setText(GroupLeaderListActivity.a.format(Long.valueOf(groupLeader.downDate * 1000)));
                bVar.j.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
                bVar.k.setText(String.valueOf(groupLeader.downMember));
                TextView textView = bVar.k;
                if (UIHelper.isNightTheme()) {
                    i2 = -12171438;
                } else {
                    i2 = -6908266;
                }
                textView.setTextColor(i2);
            }
            if (groupLeader.upMember > groupLeader.downMember) {
                i2 = groupLeader.downMember > 0 ? ((groupLeader.upMember - groupLeader.downMember) * 100) / groupLeader.downMember : groupLeader.upMember;
                if (i2 == 0) {
                    i2 = 1;
                }
                bVar.l.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_member_reduce_night : R.drawable.group_member_reduce);
                bVar.m.setTextColor(UIHelper.isNightTheme() ? -11559870 : -9314466);
                bVar.m.setText(i2 + "%");
            } else if (groupLeader.upMember < groupLeader.downMember) {
                i2 = groupLeader.upMember > 0 ? ((groupLeader.downMember - groupLeader.upMember) * 100) / groupLeader.upMember : groupLeader.downMember;
                if (i2 == 0) {
                    i2 = 1;
                }
                bVar.l.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_member_increase_night : R.drawable.group_member_increase);
                bVar.m.setTextColor(UIHelper.isNightTheme() ? -7656403 : -31098);
                bVar.m.setText(i2 + "%");
            } else {
                bVar.l.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_member_equal_night : R.drawable.group_member_equal);
                bVar.m.setTextColor(UIHelper.isNightTheme() ? -12894910 : -12894910);
                bVar.m.setText("0%");
            }
            bVar.n.setBackgroundColor(UIHelper.isNightTheme() ? -16777216 : -1184275);
            view.setOnClickListener(new UserClickDelegate(groupLeader.userId + "", new nc(this, groupLeader)));
            view.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
            return view;
        }
    }

    class b {
        ImageView a;
        TextView b;
        View c;
        ImageView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        TextView j;
        TextView k;
        ImageView l;
        TextView m;
        View n;
        final /* synthetic */ GroupLeaderListActivity o;

        b(GroupLeaderListActivity groupLeaderListActivity) {
            this.o = groupLeaderListActivity;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Context context, GroupInfo groupInfo) {
        Intent intent = new Intent(context, GroupLeaderListActivity.class);
        intent.putExtra("group_info", groupInfo);
        context.startActivity(intent);
    }

    protected String e() {
        return "群大";
    }

    protected int a() {
        return R.layout.layout_ptr_listview;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        handleIntent(getIntent());
        if (this.f == null) {
            finish();
        }
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.b.setLoadMoreEnable(false);
        this.b.setRefreshEnable(false);
        this.c = (ListView) findViewById(R.id.listview);
        this.e = new a(this, this, this.d);
        this.c.setAdapter(this.e);
        this.e.notifyDataSetChanged();
        this.c.postDelayed(new na(this), 0);
    }

    public void handleIntent(Intent intent) {
        this.f = (GroupInfo) intent.getSerializableExtra("group_info");
    }

    public void onRefresh() {
        g();
    }

    public void onLoadMore() {
    }

    private void g() {
        new SimpleHttpTask(String.format(Constants.URL_GROUP_ADMIN_LIST, new Object[]{Integer.valueOf(this.f.id), Integer.valueOf(this.f.id)}), new nb(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
