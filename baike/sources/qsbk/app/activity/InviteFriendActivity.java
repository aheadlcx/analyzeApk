package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.alipay.sdk.cons.b;
import com.meetme.android.horizontallistview.HorizontalListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.QiuYouAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class InviteFriendActivity extends BaseActionBarActivity implements OnItemClickListener, PtrListener {
    protected int a = 1;
    protected PtrLayout b;
    protected ListView c;
    protected QiuYouAdapter d;
    protected List<BaseUserInfo> e = new ArrayList();
    protected List<BaseUserInfo> f = new ArrayList();
    private int g;
    private View h;
    private HorizontalListView i;
    private a j;
    private View k;
    private EditText l;
    private boolean m = false;
    private TipsHelper n;
    private boolean o = false;

    private class a extends BaseAdapter {
        ArrayList<BaseUserInfo> a;
        final /* synthetic */ InviteFriendActivity b;

        private a(InviteFriendActivity inviteFriendActivity) {
            this.b = inviteFriendActivity;
            this.a = this.b.d.getChecked();
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
        return e();
    }

    public static void launch(Context context, int i) {
        Intent intent = new Intent(context, InviteFriendActivity.class);
        intent.putExtra("id", i);
        context.startActivity(intent);
    }

    private static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.toLowerCase().contains(str2.toLowerCase());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_search);
        findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        findItem.setVisible(!this.m);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.m) {
            UIHelper.hideKeyboard(this);
        }
        switch (menuItem.getItemId()) {
            case R.id.action_search:
                this.m = true;
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setCustomView(R.layout.widget_search_view);
                supportActionBar.setDisplayShowCustomEnabled(true);
                menuItem.setVisible(false);
                View findViewById = findViewById(16908332);
                int i = (int) (6.0f * getResources().getDisplayMetrics().density);
                if (findViewById != null) {
                    findViewById.setPadding(0, 0, i, 0);
                }
                f();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void f() {
        int i;
        int i2 = -1;
        ((ImageView) findViewById(R.id.group_search_iv)).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        this.l = (EditText) findViewById(R.id.search_text);
        this.l.setHint("搜索糗友");
        EditText editText = this.l;
        if (UIHelper.isNightTheme()) {
            i = -12105387;
        } else {
            i = -1;
        }
        editText.setHintTextColor(i);
        EditText editText2 = this.l;
        if (UIHelper.isNightTheme()) {
            i2 = -8882028;
        }
        editText2.setTextColor(i2);
        this.l.addTextChangedListener(new pv(this));
        this.l.requestFocus();
        UIHelper.showKeyboard(this);
        findViewById(R.id.search_clear).setOnClickListener(new pw(this));
        findViewById(R.id.search_clear).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_search_clear_night : R.drawable.group_search_clear);
    }

    private void b(String str) {
        this.e.clear();
        for (BaseUserInfo baseUserInfo : this.f) {
            if (a(baseUserInfo.userName, str)) {
                this.e.add(baseUserInfo);
            }
        }
        if (this.e.size() == 0 && this.o) {
            this.n.set(UIHelper.getEmptyImg(), "没查到此成员");
            this.n.show();
            return;
        }
        if (this.e.size() < 10 && !this.o) {
            this.b.loadMore();
        }
        this.n.hide();
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
        this.g = getIntent().getIntExtra("id", 0);
        g();
    }

    private void g() {
        this.n = new TipsHelper(findViewById(R.id.tips));
        this.n.hide();
        this.h = findViewById(R.id.selected_group);
        this.i = (HorizontalListView) findViewById(R.id.avatar_list);
        this.k = findViewById(R.id.invite_button);
        this.k.setOnClickListener(new px(this));
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.c = (ListView) findViewById(R.id.listview);
        this.b.setRefreshEnable(true);
        this.b.setLoadMoreEnable(false);
        this.b.setPtrListener(this);
        this.c.setOnItemClickListener(this);
        this.d = new QiuYouAdapter(this, this.e, true);
        this.c.setAdapter(this.d);
        this.j = new a();
        this.i.setAdapter(this.j);
        this.b.refresh();
    }

    public void onRefresh() {
        this.a = 1;
        a(this.a);
    }

    public void onLoadMore() {
        a(this.a);
    }

    protected void a(int i) {
        String str = String.format(Constants.URL_INVITE_JOIN_GROUP, new Object[]{Integer.valueOf(this.g)}) + "?tid=" + this.g + "&page=" + i;
        this.n.hide();
        new HttpTask(str, str, new py(this, i)).execute(new Void[0]);
    }

    protected void a(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在邀请中...");
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.g));
        hashMap.put("uids", str);
        String format = String.format(Constants.URL_INVITE_JOIN_GROUP, new Object[]{Integer.valueOf(this.g)});
        HttpTask httpTask = new HttpTask(format, format, new pz(this, progressDialog));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.c);
        if (i >= headerCount) {
            this.d.toggleCheck(i - headerCount, view);
            ArrayList checked = this.d.getChecked();
            this.j.notifyDataSetChanged();
            if (checked.size() > 0) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
        }
    }

    protected String e() {
        return "邀请互粉糗友";
    }

    protected int a() {
        return R.layout.layout_invite_activity;
    }

    public void onBackPressed() {
        if (this.m) {
            this.m = false;
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            supportInvalidateOptionsMenu();
            this.l = null;
            b("");
            return;
        }
        super.onBackPressed();
    }

    public void finish() {
        super.finish();
    }
}
