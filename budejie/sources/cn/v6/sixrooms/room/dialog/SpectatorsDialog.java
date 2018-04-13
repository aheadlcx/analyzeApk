package cn.v6.sixrooms.room.dialog;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.GuardListOfFullScreenAdapter;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.presenter.SpectatorsPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.view.interfaces.SpectatorsViewable;
import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView;
import java.util.List;

public class SpectatorsDialog extends BaseDialog implements OnClickListener, SpectatorsViewable {
    public static final String OPEN_VFAN_URL = "http://m.v.6.cn/supergirl-group/shop";
    private static final String a = SpectatorsDialog.class.getSimpleName();
    private static String p = "2";
    private GuardListOfFullScreenAdapter b;
    private ReplyWeiBoListView c;
    private ListView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private View i;
    private View j;
    private View k;
    private View l;
    private RelativeLayout m;
    private SpectatorsPresenter n = SpectatorsPresenter.getInstance();
    private boolean o;

    public SpectatorsDialog(BaseRoomActivity baseRoomActivity, WrapRoomInfo wrapRoomInfo) {
        Drawable drawable;
        super(baseRoomActivity, wrapRoomInfo);
        this.o = p.equals(wrapRoomInfo.getTplType());
        this.n.setSpectatorsViewable(this);
        this.e = (TextView) this.l.findViewById(R.id.tv_title);
        this.f = (TextView) this.l.findViewById(R.id.guard_tab);
        this.g = (TextView) this.l.findViewById(R.id.manager_tab);
        this.h = (TextView) this.l.findViewById(R.id.spectator_tab);
        this.i = this.l.findViewById(R.id.bar_guard);
        this.j = this.l.findViewById(R.id.bar_manager);
        this.k = this.l.findViewById(R.id.bar_spectator);
        this.m = (RelativeLayout) this.l.findViewById(R.id.rl_open_guard);
        this.d = (ListView) this.l.findViewById(R.id.lv_guard_rank);
        this.c = (ReplyWeiBoListView) this.l.findViewById(R.id.pullToRefresh);
        this.c.getFoot_line().setVisibility(8);
        if (this.mBaseRoomActivity.isSuperGirlRoom().booleanValue()) {
            this.e.setText("开通V粉");
            this.i.setBackgroundResource(R.drawable.v6_room_pop_title_line_superg_selector);
            this.j.setBackgroundResource(R.drawable.v6_room_pop_title_line_superg_selector);
            this.k.setBackgroundResource(R.drawable.v6_room_pop_title_line_superg_selector);
            drawable = this.mBaseRoomActivity.getResources().getDrawable(R.drawable.open_vfan_icon);
            this.d.setSelector(this.mBaseRoomActivity.getResources().getDrawable(R.drawable.transparent));
        } else {
            this.e.setText("开通守护");
            this.i.setBackgroundResource(R.drawable.v6_room_pop_title_line_selector);
            this.j.setBackgroundResource(R.drawable.v6_room_pop_title_line_selector);
            this.k.setBackgroundResource(R.drawable.v6_room_pop_title_line_selector);
            drawable = this.mBaseRoomActivity.getResources().getDrawable(R.drawable.open_guard_icon);
            this.d.setSelector(this.mBaseRoomActivity.getResources().getDrawable(R.drawable.list_dark_selector));
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.e.setCompoundDrawables(drawable, null, null, null);
        if (this.o || this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
            a(false, false, true);
            this.l.findViewById(R.id.ll_title).setVisibility(8);
            this.m.setVisibility(8);
        } else {
            a(true, false, false);
        }
        if (this.o || this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
            this.n.initSpectators(this.mWrapRoomInfo.getWrapUserInfo());
            this.n.changeToSpectatorList();
            this.n.updateSelectedType();
        } else {
            this.n.initSpectators(this.mWrapRoomInfo.getWrapUserInfo());
        }
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.m.setOnClickListener(new d(this));
        this.c.setOnHeaderRefreshListener(new e(this));
        this.c.setOnFooterRefreshListener(new f(this));
    }

    protected View getDialogContentView() {
        this.l = View.inflate(this.mBaseRoomActivity, R.layout.dialog_spectators, null);
        return this.l;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.guard_tab) {
            a(true, false, false);
            this.n.changeToGuardList();
        } else if (id == R.id.manager_tab) {
            a(false, true, false);
            this.n.changeToManagerList();
        } else if (id == R.id.spectator_tab) {
            a(false, false, true);
            this.n.changeToSpectatorList();
        }
        this.n.updateSelectedType();
        this.b.notifyDataSetChanged();
        if (!this.d.isStackFromBottom()) {
            this.d.setStackFromBottom(true);
        }
        this.d.setStackFromBottom(false);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        this.f.setSelected(z);
        this.i.setSelected(z);
        this.g.setSelected(z2);
        this.j.setSelected(z2);
        this.h.setSelected(z3);
        this.k.setSelected(z3);
        if (z3) {
            this.c.isBanPullUpRefresh(false);
        } else {
            this.c.isBanPullUpRefresh(true);
        }
    }

    public void updateSpectatorsView(List<UserInfoBean> list, String str, String str2, String str3) {
        if (this.mBaseRoomActivity.isSuperGirlRoom().booleanValue()) {
            this.f.setText("V粉  (" + str + ")");
        } else {
            this.f.setText("守护  (" + str + ")");
        }
        this.g.setText("管理  (" + str2 + ")");
        if (str3.length() > 4 && this.mBaseRoomActivity.getResources().getConfiguration().orientation == 2) {
            str3 = str3.substring(0, 3) + "…";
        }
        this.h.setText("观众  (" + str3 + ")");
        if (this.b != null) {
            pullToRefreshComplete();
            this.b.notifyDataSetChanged();
            return;
        }
        this.b = new GuardListOfFullScreenAdapter(this.mBaseRoomActivity, list);
        this.d.setAdapter(this.b);
    }

    public void updateError(int i) {
        this.c.onHeaderRefreshComplete();
    }

    public void sendLoadAllRoomList() {
        this.mBaseRoomActivity.sendLoadAllRoomList();
    }

    public void updateSelectedType(int i) {
        this.b.setmState(i);
    }

    public void dismiss() {
        super.dismiss();
    }

    public void showOpenGuard() {
        this.mBaseRoomActivity.showOpenGuardPage();
    }

    public void pullToRefreshComplete() {
        this.c.onHeaderRefreshComplete();
        this.c.onFooterRefreshComplete();
    }

    public void showErrorDialog(String str, String str2) {
        this.mBaseRoomActivity.handleErrorResult(str, str2, this.mBaseRoomActivity);
    }

    public void showLoginDialog() {
        this.mBaseRoomActivity.showLoginDialog();
    }

    public void showErrorToast(int i) {
        this.mBaseRoomActivity.showErrorToast(i);
    }
}
