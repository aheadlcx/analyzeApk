package cn.v6.sixrooms.widgets.phone;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.GuardListOfFullScreenAdapter;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.presenter.SpectatorsPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.view.interfaces.SpectatorsViewable;
import java.util.List;

public class SpectatorsPop extends BaseFullScreenPop implements OnClickListener, SpectatorsViewable {
    private static final String a = SpectatorsPop.class.getSimpleName();
    private GuardListOfFullScreenAdapter b;
    private ReplyWeiBoListView c;
    private RelativeLayout d;
    private TextView e;
    private TextView f;
    private View g;
    private ListView h;
    private RelativeLayout i;
    private TextView j;
    private TextView k;
    private View l;
    private RelativeLayout m;
    private TextView n;
    private TextView o;
    private View p;
    private View q;
    private TextView r;
    private FullScreenOpenGuardDialog s;
    private SpectatorsPresenter t;

    public SpectatorsPop(BaseRoomActivity baseRoomActivity, int i, int i2, int i3, float f, WrapRoomInfo wrapRoomInfo, OnDismissListener onDismissListener, boolean z) {
        super(baseRoomActivity, i, i2, i3, f, wrapRoomInfo, onDismissListener, z);
    }

    public void showAsDropDown(View view, int i) {
        super.showAsDropDown(view, i);
    }

    @Deprecated
    public void showAsDropDown(View view) {
        super.showAsDropDown(view);
    }

    @Deprecated
    public void showAsDropDown(View view, int i, int i2) {
        super.showAsDropDown(view, i, i2);
    }

    @SuppressLint({"NewApi"})
    @Deprecated
    public void showAsDropDown(View view, int i, int i2, int i3) {
        super.showAsDropDown(view, i, i2, i3);
    }

    @Deprecated
    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
    }

    final View a() {
        this.q = View.inflate(this.mBaseRoomActivity, R.layout.pop_guard, null);
        this.t = SpectatorsPresenter.getInstance();
        this.t.setSpectatorsViewable(this);
        this.d = (RelativeLayout) this.q.findViewById(R.id.rl_left);
        this.e = (TextView) this.q.findViewById(R.id.tv_guard_num);
        this.f = (TextView) this.q.findViewById(R.id.tv_title_left);
        this.g = this.q.findViewById(R.id.bar_left);
        this.i = (RelativeLayout) this.q.findViewById(R.id.rl_center);
        this.j = (TextView) this.q.findViewById(R.id.tv_manager_num);
        this.k = (TextView) this.q.findViewById(R.id.tv_title_center);
        this.l = this.q.findViewById(R.id.bar_center);
        this.m = (RelativeLayout) this.q.findViewById(R.id.rl_right);
        this.n = (TextView) this.q.findViewById(R.id.tv_spectator_num);
        this.o = (TextView) this.q.findViewById(R.id.tv_title_right);
        this.p = this.q.findViewById(R.id.bar_right);
        this.r = (TextView) this.q.findViewById(R.id.tv_open_guard);
        this.h = (ListView) this.q.findViewById(R.id.lv_guard_rank);
        this.c = (ReplyWeiBoListView) this.q.findViewById(R.id.pullToRefresh);
        this.c.getFoot_line().setVisibility(8);
        a(true, false, false);
        this.d.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.c.isBanPullUpRefresh(true);
        this.c.setOnHeaderRefreshListener(new av(this));
        this.c.setOnFooterRefreshListener(new aw(this));
        return this.q;
    }

    protected void initData() {
        super.initData();
        this.t.initSpectators(this.mWrapRoomInfo.getWrapUserInfo());
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_left) {
            a(true, false, false);
            this.c.isBanPullUpRefresh(true);
            this.t.changeToGuardList();
        } else if (id == R.id.rl_center) {
            a(false, true, false);
            this.c.isBanPullUpRefresh(true);
            this.t.changeToManagerList();
        } else if (id == R.id.rl_right) {
            a(false, false, true);
            this.c.isBanPullUpRefresh(false);
            this.t.changeToSpectatorList();
        } else if (id == R.id.tv_open_guard) {
            this.t.openGuard();
        }
        this.t.updateSelectedType();
        this.b.notifyDataSetChanged();
        if (!this.h.isStackFromBottom()) {
            this.h.setStackFromBottom(true);
        }
        this.h.setStackFromBottom(false);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        this.d.setSelected(z);
        this.f.setSelected(z);
        this.g.setSelected(z);
        this.i.setSelected(z2);
        this.l.setSelected(z2);
        this.k.setSelected(z2);
        this.m.setSelected(z3);
        this.o.setSelected(z3);
        this.p.setSelected(z3);
    }

    public void updateSpectatorsView(List<UserInfoBean> list, String str, String str2, String str3) {
        this.e.setText("(" + str + ")");
        this.j.setText("(" + str2 + ")");
        this.n.setText("(" + str3 + ")");
        if (this.b != null) {
            pullToRefreshComplete();
            this.b.notifyDataSetChanged();
            return;
        }
        this.b = new GuardListOfFullScreenAdapter(this.mBaseRoomActivity, list);
        this.h.setAdapter(this.b);
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

    public void showOpenGuard() {
        if (this.mIsLive || this.mWrapRoomInfo.getRoominfoBean().getId().equals(LoginUtils.getLoginUID())) {
            ToastUtils.showToast("您不能开通自己的守护");
            return;
        }
        if (this.s == null) {
            this.s = new FullScreenOpenGuardDialog(this.mBaseRoomActivity, this.mWrapRoomInfo.getRoominfoBean(), new ax(this));
        }
        this.s.show();
    }
}
