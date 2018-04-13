package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.MemberAdapter;
import qsbk.app.adapter.MemberAdapter.OtherItem;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupInfo$MemberInfo;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class MemberChooseActivity extends BaseActionBarActivity implements PtrListener {
    private static GroupInfo a = null;
    private boolean b;
    private GroupInfo c;
    private PtrLayout d;
    private ListView e;
    private MemberAdapter f;
    private ArrayList<Object> g = new ArrayList();
    private OtherItem h = b("群大");
    private Object i;
    private OtherItem j = b("管理员");
    private ArrayList<Object> k = new ArrayList();
    private OtherItem l = b("成员");
    private ArrayList<Object> m = new ArrayList();
    private EditText n;
    private boolean o = false;
    private TipsHelper p;
    private int q = -1;
    private Boolean r = Boolean.valueOf(false);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launchForResult(Activity activity, GroupInfo groupInfo, int i) {
        Intent intent = new Intent(activity, MemberChooseActivity.class);
        a = groupInfo;
        activity.startActivityForResult(intent, i);
    }

    private static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.toLowerCase().contains(str2.toLowerCase());
    }

    protected boolean d() {
        return true;
    }

    protected String f() {
        return "群成员";
    }

    protected int a() {
        return R.layout.group_member_list;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_search);
        findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        findItem.setVisible(!this.o);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_search:
                this.o = true;
                this.f.setSearchModeEnable(this.o);
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setCustomView(R.layout.widget_search_view);
                supportActionBar.setDisplayShowCustomEnabled(true);
                menuItem.setVisible(false);
                View findViewById = findViewById(16908332);
                int i = (int) (6.0f * getResources().getDisplayMetrics().density);
                if (findViewById != null) {
                    findViewById.setPadding(0, 0, i, 0);
                }
                g();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void g() {
        int i;
        int i2 = -1;
        ((ImageView) findViewById(R.id.group_search_iv)).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        this.n = (EditText) findViewById(R.id.search_text);
        this.n.setHint("搜索群成员");
        EditText editText = this.n;
        if (UIHelper.isNightTheme()) {
            i = -12105387;
        } else {
            i = -1;
        }
        editText.setHintTextColor(i);
        EditText editText2 = this.n;
        if (UIHelper.isNightTheme()) {
            i2 = -8882028;
        }
        editText2.setTextColor(i2);
        this.n.addTextChangedListener(new th(this));
        this.n.requestFocus();
        UIHelper.showKeyboard(this);
        findViewById(R.id.search_clear).setOnClickListener(new ti(this));
        findViewById(R.id.search_clear).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_search_clear_night : R.drawable.group_search_clear);
    }

    private void i() {
        a(this.n == null ? "" : this.n.getText().toString());
    }

    private boolean a(Object obj, String str) {
        if (str.length() == 0) {
            return true;
        }
        if (!(obj instanceof BaseUserInfo)) {
            return false;
        }
        BaseUserInfo baseUserInfo = (BaseUserInfo) obj;
        String remark = RemarkManager.getRemark(baseUserInfo.userId);
        if (a(baseUserInfo.userName, str) || a(remark, str)) {
            return true;
        }
        return false;
    }

    private void a(String str) {
        Object obj;
        Object obj2 = null;
        this.g.clear();
        if (a(this.i, str)) {
            this.g.add(this.h);
            this.g.add(this.i);
            obj = 1;
        } else {
            obj = null;
        }
        Iterator it = this.k.iterator();
        Object obj3 = null;
        while (it.hasNext()) {
            Object next = it.next();
            if (a(next, str)) {
                if (obj3 == null) {
                    this.g.add(this.j);
                    obj3 = 1;
                }
                this.g.add(next);
            }
        }
        it = this.m.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (a(next, str)) {
                if (obj2 == null) {
                    this.g.add(this.l);
                    obj2 = 1;
                }
                this.g.add(next);
            }
        }
        if (obj == null && obj3 == null && obj2 == null) {
            this.p.set(UIHelper.getEmptyImg(), "没查到此成员");
            this.p.show();
        } else {
            this.p.hide();
        }
        this.f.notifyDataSetChanged();
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
        if (a == null) {
            finish();
            return;
        }
        this.c = a;
        a = null;
        this.b = QsbkApp.currentUser.userId.equals(String.valueOf(this.c.ownerId));
        this.r = Boolean.valueOf(this.b);
        j();
        this.d.refresh();
        setResult(0);
    }

    private void j() {
        this.d = (PtrLayout) findViewById(R.id.ptr);
        this.e = (ListView) findViewById(R.id.listview);
        this.p = new TipsHelper(findViewById(R.id.tips));
        this.p.hide();
        this.d.setLoadMoreEnable(false);
        this.d.setPtrListener(this);
        this.f = new MemberAdapter(this.g, this, this.c.getTitlesIfEnable(), null, this.r.booleanValue());
        this.e.setAdapter(this.f);
        this.e.setOnItemClickListener(new tj(this));
    }

    private OtherItem b(String str) {
        OtherItem otherItem = new OtherItem();
        otherItem.type = 1;
        otherItem.msg = str;
        return otherItem;
    }

    private OtherItem c(String str) {
        OtherItem otherItem = new OtherItem();
        otherItem.type = 2;
        otherItem.msg = str;
        return otherItem;
    }

    public void handlerMember(ArrayList<BaseUserInfo> arrayList) {
        String valueOf = String.valueOf(this.c.ownerId);
        this.i = null;
        this.k.clear();
        this.m.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) it.next();
            if (baseUserInfo.userId.equals(valueOf)) {
                this.i = baseUserInfo;
                it.remove();
            } else if (baseUserInfo.isAdmin) {
                if (TextUtils.equals(QsbkApp.currentUser.userId, baseUserInfo.userId)) {
                    this.r = Boolean.valueOf(true);
                    this.f.setAdmin(this.r.booleanValue());
                }
                this.k.add(baseUserInfo);
                it.remove();
            }
        }
        if (this.i == null) {
            Iterator it2 = this.c.memberList.iterator();
            while (it2.hasNext()) {
                GroupInfo$MemberInfo groupInfo$MemberInfo = (GroupInfo$MemberInfo) it2.next();
                if (groupInfo$MemberInfo.uid == this.c.ownerId) {
                    this.i = groupInfo$MemberInfo.toBaseUserInfo();
                    break;
                }
            }
        }
        if (this.i == null) {
            this.i = c("当前没有群大");
        }
        if (this.k.size() == 0) {
            this.k.add(c("没有设置管理员"));
        }
        if (arrayList.size() == 0) {
            this.m.add(c("没有其他成员"));
        } else {
            this.m.addAll(arrayList);
        }
    }

    public void loadMembers() {
        showLoading();
        this.p.hide();
        new GroupMemberManager(this.c).loadMemberFromServer(new tk(this));
    }

    public void onRefresh() {
        loadMembers();
    }

    public void finish() {
        super.finish();
    }

    public void onLoadMore() {
    }

    public void onBackPressed() {
        if (this.o) {
            this.o = false;
            this.f.setSearchModeEnable(this.o);
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            supportInvalidateOptionsMenu();
            this.n = null;
            i();
            this.f.notifyDataSetChanged();
            return;
        }
        super.onBackPressed();
    }
}
