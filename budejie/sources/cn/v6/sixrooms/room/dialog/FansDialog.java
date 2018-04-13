package cn.v6.sixrooms.room.dialog;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.GiftListOfFullScreenAdapter;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.bean.GiftListItemBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter;
import cn.v6.sixrooms.room.interfaces.FansViewable;
import cn.v6.sixrooms.room.presenter.FansPresenter;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.List;

public class FansDialog extends BaseDialog implements OnClickListener, FansViewable {
    private static final String a = FansDialog.class.getSimpleName();
    private FansListOfFullScreenAdapter b;
    private BaseAdapter c;
    private ListView d;
    private ListView e;
    private View f;
    private View g;
    private TextView h;
    private TextView i;
    private View j;
    private RadioGroup k;
    private RadioButton l;
    private RadioButton m;
    private RadioButton n;
    private RadioButton o;
    private FansPresenter p = FansPresenter.getInstance();
    private final String q = "2";
    private Drawable r;
    private Drawable s;
    private int t;
    private int u;

    public FansDialog(BaseRoomActivity baseRoomActivity, WrapRoomInfo wrapRoomInfo) {
        super(baseRoomActivity, wrapRoomInfo);
        this.p.setFansViewable(this);
        this.h = (TextView) this.j.findViewById(R.id.tv_title_left);
        this.d = (ListView) this.j.findViewById(R.id.lv_fans_rank);
        this.i = (TextView) this.j.findViewById(R.id.tv_title_right);
        this.e = (ListView) this.j.findViewById(R.id.lv_gift_rank);
        this.f = this.j.findViewById(R.id.bar_left);
        this.g = this.j.findViewById(R.id.bar_right);
        this.k = (RadioGroup) this.j.findViewById(R.id.rg_tab);
        this.l = (RadioButton) this.j.findViewById(R.id.this_tab);
        this.m = (RadioButton) this.j.findViewById(R.id.seven_tab);
        this.n = (RadioButton) this.j.findViewById(R.id.thirty_tab);
        this.o = (RadioButton) this.j.findViewById(R.id.super_tab);
        this.s = this.mBaseRoomActivity.getResources().getDrawable(R.drawable.transparent_line);
        this.s.setBounds(0, 0, this.s.getMinimumWidth(), this.s.getMinimumHeight());
        this.u = this.mBaseRoomActivity.getResources().getColor(R.color.common_gray_textcolor);
        if (this.mBaseRoomActivity.isSuperGirlRoom().booleanValue()) {
            this.h.setText("亲密榜");
            this.i.setText("礼单");
            this.f.setBackgroundResource(R.drawable.v6_room_pop_title_line_superg_selector);
            this.g.setBackgroundResource(R.drawable.v6_room_pop_title_line_superg_selector);
            this.r = this.mBaseRoomActivity.getResources().getDrawable(R.drawable.pink_line);
            this.r.setBounds(0, 0, this.r.getMinimumWidth(), this.r.getMinimumHeight());
            this.t = this.mBaseRoomActivity.getResources().getColor(R.color.pop_text_pink);
            this.d.setSelector(this.mBaseRoomActivity.getResources().getDrawable(R.drawable.transparent));
        } else {
            this.h.setText("粉丝排行");
            this.i.setText("本场礼单");
            this.f.setBackgroundResource(R.drawable.v6_room_pop_title_line_selector);
            this.g.setBackgroundResource(R.drawable.v6_room_pop_title_line_selector);
            this.r = this.mBaseRoomActivity.getResources().getDrawable(R.drawable.red_line);
            this.r.setBounds(0, 0, this.r.getMinimumWidth(), this.r.getMinimumHeight());
            this.t = this.mBaseRoomActivity.getResources().getColor(R.color.pop_text_red);
            this.d.setSelector(this.mBaseRoomActivity.getResources().getDrawable(R.drawable.list_dark_selector));
        }
        if (!(this.mWrapRoomInfo == null || this.mWrapRoomInfo.getRoomParamInfoBean() == null)) {
            switch (this.mWrapRoomInfo.getRoomParamInfoBean().getSetranking()) {
                case -1:
                    this.o.setVisibility(8);
                    this.n.setVisibility(8);
                    this.m.setVisibility(8);
                    break;
                case 0:
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    this.m.setVisibility(0);
                    break;
                case 1:
                    this.o.setVisibility(8);
                    this.n.setVisibility(0);
                    this.m.setVisibility(0);
                    break;
                default:
                    this.o.setVisibility(0);
                    this.n.setVisibility(0);
                    this.m.setVisibility(0);
                    break;
            }
        }
        a();
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.k.setOnCheckedChangeListener(new a(this));
        this.l.setChecked(true);
        if (!"2".equals(this.mWrapRoomInfo.getTplType()) && !this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
            this.p.getSupperSortFansList(this.mWrapRoomInfo.getRoominfoBean().getId());
        }
    }

    protected View getDialogContentView() {
        this.j = View.inflate(this.mBaseRoomActivity, R.layout.dialog_fans, null);
        return this.j;
    }

    private void a() {
        this.h.setSelected(true);
        this.f.setSelected(true);
        this.i.setSelected(false);
        this.g.setSelected(false);
        this.d.setVisibility(0);
        if ("2".equals(this.mWrapRoomInfo.getTplType())) {
            this.k.setVisibility(8);
            this.h.setText(V6Coop.getInstance().getContext().getString(R.string.str_contribute_fans_live));
            this.i.setText(V6Coop.getInstance().getContext().getString(R.string.str_red_fans_live));
        } else if (this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
            this.k.setVisibility(8);
            this.h.setText("亲密榜");
            this.i.setText("星光榜");
        } else {
            this.k.setVisibility(0);
        }
        this.e.setVisibility(8);
    }

    private void a(boolean z) {
        boolean z2 = true;
        this.f.setSelected(!z);
        TextView textView = this.h;
        if (z) {
            z2 = false;
        }
        textView.setSelected(z2);
        this.g.setSelected(z);
        this.i.setSelected(z);
        if (this.b != null) {
            this.b.setmIsStarLight(z);
        }
        this.p.changeToFansList(z);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_title_left) {
            if ("2".equals(this.mWrapRoomInfo.getTplType()) || this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
                a(false);
            } else {
                a();
            }
        } else if (id != R.id.tv_title_right) {
        } else {
            if ("2".equals(this.mWrapRoomInfo.getTplType()) || this.mBaseRoomActivity.isSuperGirlFamilyRoom().booleanValue()) {
                a(true);
                return;
            }
            this.h.setSelected(false);
            this.f.setSelected(false);
            this.i.setSelected(true);
            this.g.setSelected(true);
            this.d.setVisibility(8);
            this.k.setVisibility(8);
            this.e.setVisibility(0);
        }
    }

    public void updateFansView(List<FansBean> list) {
        LogUtils.e(a, "updateFansView");
        if (this.b == null) {
            this.b = new FansListOfFullScreenAdapter(this.mBaseRoomActivity, list);
            this.d.setAdapter(this.b);
            return;
        }
        this.b.notifyDataSetChanged();
    }

    public void updateGiftsView(List<GiftListItemBean> list) {
        LogUtils.e(a, "updateGiftsView");
        if (this.c == null) {
            this.c = new GiftListOfFullScreenAdapter(this.mBaseRoomActivity, list);
            this.e.setAdapter(this.c);
            return;
        }
        this.c.notifyDataSetChanged();
    }

    public void sendGiftListSocket() {
        this.mBaseRoomActivity.sendGiftList(this.mWrapRoomInfo.getRoominfoBean().getRid());
    }

    public void updateSelectedType(int i) {
        if (this.b != null) {
            this.b.setmState(i);
        }
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

    static /* synthetic */ void a(FansDialog fansDialog, int i) {
        if (i == R.id.this_tab) {
            fansDialog.l.setTextColor(fansDialog.t);
            fansDialog.m.setTextColor(fansDialog.u);
            fansDialog.n.setTextColor(fansDialog.u);
            fansDialog.o.setTextColor(fansDialog.u);
        } else if (i == R.id.seven_tab) {
            fansDialog.l.setTextColor(fansDialog.u);
            fansDialog.m.setTextColor(fansDialog.t);
            fansDialog.n.setTextColor(fansDialog.u);
            fansDialog.o.setTextColor(fansDialog.u);
        } else if (i == R.id.thirty_tab) {
            fansDialog.l.setTextColor(fansDialog.u);
            fansDialog.m.setTextColor(fansDialog.u);
            fansDialog.n.setTextColor(fansDialog.t);
            fansDialog.o.setTextColor(fansDialog.u);
        } else if (i == R.id.super_tab) {
            fansDialog.l.setTextColor(fansDialog.u);
            fansDialog.m.setTextColor(fansDialog.u);
            fansDialog.n.setTextColor(fansDialog.u);
            fansDialog.o.setTextColor(fansDialog.t);
        }
    }

    static /* synthetic */ void b(FansDialog fansDialog, int i) {
        if (i == R.id.this_tab) {
            fansDialog.l.setCompoundDrawables(null, null, null, fansDialog.r);
            fansDialog.m.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.n.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.o.setCompoundDrawables(null, null, null, fansDialog.s);
        } else if (i == R.id.seven_tab) {
            fansDialog.l.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.m.setCompoundDrawables(null, null, null, fansDialog.r);
            fansDialog.n.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.o.setCompoundDrawables(null, null, null, fansDialog.s);
        } else if (i == R.id.thirty_tab) {
            fansDialog.l.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.m.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.n.setCompoundDrawables(null, null, null, fansDialog.r);
            fansDialog.o.setCompoundDrawables(null, null, null, fansDialog.s);
        } else if (i == R.id.super_tab) {
            fansDialog.l.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.m.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.n.setCompoundDrawables(null, null, null, fansDialog.s);
            fansDialog.o.setCompoundDrawables(null, null, null, fansDialog.r);
        }
    }
}
