package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
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
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.MemberAdapter;
import qsbk.app.adapter.MemberAdapter.OnActionListener;
import qsbk.app.adapter.MemberAdapter.OtherItem;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.OfficialInfoActivity;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupInfo$MemberInfo;
import qsbk.app.utils.GroupActionUtil;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class MemberManagerActivity extends BaseActionBarActivity implements OnActionListener, PtrListener {
    private static GroupInfo a = null;
    private boolean b;
    private boolean c;
    private boolean d;
    private GroupInfo e;
    private PtrLayout f;
    private ListView g;
    private MemberAdapter h;
    private final Runnable i = new tl(this);
    private ArrayList<Object> j = new ArrayList();
    private OtherItem k = a("群大");
    private Object l;
    private OtherItem m = a("管理员");
    private ArrayList<Object> n = new ArrayList();
    private OtherItem o = a("成员");
    private ArrayList<Object> p = new ArrayList();
    private boolean q;
    private EditText r;
    private int s = 0;
    private TipsHelper t;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, GroupInfo groupInfo) {
        context.startActivity(new Intent(context, MemberManagerActivity.class));
        a = groupInfo;
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

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.e = a;
        if (a == null) {
            finish();
            return;
        }
        a = null;
        this.b = QsbkApp.currentUser.userId.equals(String.valueOf(this.e.ownerId));
        this.c = this.b;
        this.d = this.e.joinStatus == 2;
        g();
        ArrayList loadMemberFromCache = new GroupMemberManager(this.e).loadMemberFromCache();
        if (loadMemberFromCache == null) {
            i();
            return;
        }
        handlerMembers(loadMemberFromCache);
        k();
    }

    private void g() {
        this.f = (PtrLayout) findViewById(R.id.ptr);
        this.g = (ListView) findViewById(R.id.listview);
        this.t = new TipsHelper(findViewById(R.id.tips));
        this.t.hide();
        this.f.setLoadMoreEnable(false);
        this.f.setPtrListener(this);
        this.h = new MemberAdapter(this.j, this, this.d ? this.e.getTitlesIfEnable() : null, this.c ? this : null, this.c);
        this.h.setAtType(false);
        if (this.c) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "点击群成员右侧（三角）可以管理群成员哦", Integer.valueOf(0)).show();
        }
        this.g.setAdapter(this.h);
        this.g.setOnItemClickListener(new to(this));
    }

    private void a(String str, String str2, String str3) {
        if (QsbkApp.currentUser.userId.equals(str)) {
            MyInfoActivity.launch(this);
        } else if (UserClickDelegate.isOfficialAccount(str)) {
            OfficialInfoActivity.launch(this, str, str3, str2);
        } else {
            IMChatMsgSource iMChatMsgSource = new IMChatMsgSource(7, str, String.valueOf(this.e.id) + Config.TRACE_TODAY_VISIT_SPLIT + this.e.name);
            if (this.d) {
                MyInfoActivity.launch(this, str, String.valueOf(this.e.id), this.e.name, MyInfoActivity.FANS_ORIGINS[3], iMChatMsgSource);
                return;
            }
            MyInfoActivity.launch(this, str, MyInfoActivity.FANS_ORIGINS[3], iMChatMsgSource);
        }
    }

    private OtherItem a(String str) {
        OtherItem otherItem = new OtherItem();
        otherItem.type = 1;
        otherItem.msg = str;
        return otherItem;
    }

    private OtherItem b(String str) {
        OtherItem otherItem = new OtherItem();
        otherItem.type = 2;
        otherItem.msg = str;
        return otherItem;
    }

    public void handlerMembers(ArrayList<BaseUserInfo> arrayList) {
        this.l = null;
        this.n.clear();
        this.p.clear();
        this.h.setAdmin(false);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) it.next();
            if (baseUserInfo.isOwner) {
                this.h.setAdmin(true);
                this.l = baseUserInfo;
                it.remove();
            } else if (baseUserInfo.isAdmin) {
                if (!this.c && baseUserInfo.userId.equals(QsbkApp.currentUser.userId)) {
                    this.c = true;
                    this.h.setAdmin(true);
                    ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "点击群成员右侧（三角）可以管理群成员哦", Integer.valueOf(0)).show();
                    this.h.setOnActionListener(this);
                }
                this.n.add(baseUserInfo);
                it.remove();
            }
        }
        if (this.l == null) {
            it = this.e.memberList.iterator();
            while (it.hasNext()) {
                GroupInfo$MemberInfo groupInfo$MemberInfo = (GroupInfo$MemberInfo) it.next();
                if (groupInfo$MemberInfo.uid == this.e.ownerId) {
                    this.l = groupInfo$MemberInfo.toBaseUserInfo();
                    break;
                }
            }
        }
        if (this.l == null) {
            this.l = b("当前没有群大");
        }
        if (this.n.size() == 0) {
            this.n.add(b("没有设置管理员"));
        }
        this.p.addAll(arrayList);
    }

    private void i() {
        showLoading();
        this.t.hide();
        new GroupMemberManager(this.e).loadMemberFromServer(new tp(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.member, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = true;
        boolean onPrepareOptionsMenu = super.onPrepareOptionsMenu(menu);
        if (onPrepareOptionsMenu) {
            boolean z2;
            MenuItem findItem = menu.findItem(R.id.action_search);
            findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
            if (this.q) {
                z2 = false;
            } else {
                z2 = true;
            }
            findItem.setVisible(z2);
            if (this.d) {
                findItem = menu.findItem(R.id.action_normal_sort);
                if (this.s != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                findItem.setVisible(z2);
                MenuItem findItem2 = menu.findItem(R.id.action_level_sort);
                if (this.s == 1) {
                    z = false;
                }
                findItem2.setVisible(z);
            } else {
                menu.findItem(R.id.action_normal_sort).setVisible(false);
                menu.findItem(R.id.action_level_sort).setVisible(false);
                menu.findItem(R.id.action_invite).setVisible(false);
            }
        }
        return onPrepareOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.q) {
            UIHelper.hideKeyboard(this);
        }
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case R.id.action_search:
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setCustomView(R.layout.widget_search_view);
                supportActionBar.setDisplayShowCustomEnabled(true);
                this.q = true;
                this.h.setSearchModeEnable(this.q);
                menuItem.setVisible(false);
                View findViewById = findViewById(16908332);
                int i = (int) (6.0f * getResources().getDisplayMetrics().density);
                if (findViewById != null) {
                    findViewById.setPadding(0, 0, i, 0);
                }
                j();
                return true;
            case R.id.action_normal_sort:
                this.s = 0;
                supportInvalidateOptionsMenu();
                k();
                return true;
            case R.id.action_level_sort:
                this.s = 1;
                supportInvalidateOptionsMenu();
                k();
                return true;
            case R.id.action_invite:
                InviteQiuYouActivity.launch(this, this.e);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onBackPressed() {
        if (this.q) {
            this.q = false;
            this.h.setSearchModeEnable(this.q);
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            supportInvalidateOptionsMenu();
            this.r = null;
            k();
            this.h.notifyDataSetChanged();
            return;
        }
        super.onBackPressed();
    }

    private void j() {
        int i;
        int i2 = -1;
        ((ImageView) findViewById(R.id.group_search_iv)).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        this.r = (EditText) findViewById(R.id.search_text);
        this.r.setHint("搜索群成员");
        EditText editText = this.r;
        if (UIHelper.isNightTheme()) {
            i = -12105387;
        } else {
            i = -1;
        }
        editText.setHintTextColor(i);
        EditText editText2 = this.r;
        if (UIHelper.isNightTheme()) {
            i2 = -8882028;
        }
        editText2.setTextColor(i2);
        this.r.addTextChangedListener(new tq(this));
        this.r.requestFocus();
        UIHelper.showKeyboard(this);
        findViewById(R.id.search_clear).setOnClickListener(new tr(this));
        findViewById(R.id.search_clear).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_search_clear_night : R.drawable.group_search_clear);
    }

    private boolean a(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
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

    private boolean a(Object obj, Object obj2) {
        if (obj == null || obj2 == null || !(obj instanceof BaseUserInfo) || !(obj2 instanceof BaseUserInfo) || ((BaseUserInfo) obj).role <= ((BaseUserInfo) obj2).role) {
            return false;
        }
        return true;
    }

    private void k() {
        c(this.r == null ? "" : this.r.getText().toString());
    }

    private void c(String str) {
        int i;
        this.j.clear();
        if (a(this.l, str)) {
            this.j.add(this.k);
            this.j.add(this.l);
        }
        int size = this.j.size();
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (a(next, str)) {
                if (size == this.j.size()) {
                    this.j.add(this.m);
                }
                if (this.s == 1) {
                    int size2 = this.j.size();
                    i = size + 1;
                    while (i < this.j.size()) {
                        if (a(this.j.get(i), next)) {
                            break;
                        }
                        i++;
                    }
                    i = size2;
                    this.j.add(i, next);
                } else {
                    this.j.add(next);
                }
            }
        }
        int size3 = this.j.size();
        Iterator it2 = this.p.iterator();
        while (it2.hasNext()) {
            Object next2 = it2.next();
            if (a(next2, str)) {
                if (size3 == this.j.size()) {
                    this.j.add(this.o);
                }
                if (this.s == 1) {
                    size2 = this.j.size();
                    i = size3 + 1;
                    while (i < this.j.size()) {
                        if (a(this.j.get(i), next2)) {
                            break;
                        }
                        i++;
                    }
                    i = size2;
                    this.j.add(i, next2);
                } else {
                    this.j.add(next2);
                }
            }
        }
        if (this.j.size() == 0) {
            this.t.set(UIHelper.getEmptyImg(), "没查到此成员");
            this.t.show();
        } else {
            this.t.hide();
        }
        MemberAdapter memberAdapter = this.h;
        i = this.b ? size : this.c ? size3 : Integer.MAX_VALUE;
        memberAdapter.setMinShowActionPos(i);
        this.h.notifyDataSetChanged();
    }

    private void b(String str, String str2) {
        GroupActionUtil.appointAdminIfConfirm(this, this.e.id, str, str2, new ts(this, this, "处理中...", false));
    }

    private void c(String str, String str2) {
        GroupActionUtil.firedAdminIfConfirm(this, this.e.id, str, str2, new tt(this, this, "处理中...", false));
    }

    private void d(String str, String str2) {
        GroupActionUtil.deleteMemberIfConfirm(this, this.e.id, str, str2, new tu(this, this, "处理中...", false, str2));
    }

    private void d(String str) {
        GroupActionUtil.muteMemberIfConfirm(this, this.e.id, str, new tv(this, this, "处理中...", false));
    }

    private void a(BaseUserInfo baseUserInfo) {
        GroupActionUtil.unmuteMember(this.e.id, baseUserInfo.userId, new tm(this, this, "处理中...", baseUserInfo));
    }

    public void onRefresh() {
        i();
    }

    public void finish() {
        super.finish();
    }

    public void onLoadMore() {
    }

    public void onAction(int i) {
        String str;
        int i2;
        String str2;
        int i3;
        int[] iArr;
        CharSequence[] charSequenceArr;
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.h.getItem(i);
        if (baseUserInfo.silenceTime > System.currentTimeMillis()) {
            str = "解除禁言";
            i2 = 2;
        } else {
            str = "禁言";
            i2 = 1;
        }
        if (baseUserInfo.isAdmin) {
            str2 = "撤销管理员";
            i3 = 5;
        } else {
            str2 = "设为管理员";
            i3 = 4;
        }
        if (this.b) {
            String[] strArr = new String[]{str2, str, "移出本群"};
            iArr = new int[]{i3, i2, 3};
            charSequenceArr = strArr;
        } else {
            iArr = new int[]{i2, 3};
            Object[] objArr = new String[]{str, "移出本群"};
        }
        new Builder(this).setItems(charSequenceArr, new tn(this, iArr, baseUserInfo)).show();
    }
}
