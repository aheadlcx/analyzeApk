package cn.v6.sixrooms.widgets.phone;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.GiftListOfFullScreenAdapter;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.bean.GiftListItemBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.engine.FansListEngine;
import cn.v6.sixrooms.room.BaseRoomActivity;
import java.util.ArrayList;
import java.util.List;

public class RankPop extends BaseFullScreenPop implements OnClickListener {
    private static final String a = RankPop.class.getSimpleName();
    private BaseAdapter b;
    private BaseAdapter c;
    private String d;
    private RelativeLayout e;
    private ListView f;
    private RelativeLayout g;
    private ListView h;
    private FansListEngine i;
    private List<FansBean> j;
    private List<GiftListItemBean> k;
    private TextView l;
    private View m;
    private View n;
    private TextView o;
    private View p;
    private boolean q = true;
    private boolean r = true;

    public BaseAdapter getGiftListAdapter() {
        return this.c;
    }

    public List<GiftListItemBean> getGiftsBeans() {
        return this.k;
    }

    public RankPop(BaseRoomActivity baseRoomActivity, int i, int i2, int i3, float f, WrapRoomInfo wrapRoomInfo, OnDismissListener onDismissListener, boolean z) {
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
        this.p = View.inflate(this.mBaseRoomActivity, R.layout.pop_rank, null);
        this.e = (RelativeLayout) this.p.findViewById(R.id.rl_left);
        this.o = (TextView) this.p.findViewById(R.id.tv_title_left);
        this.n = this.p.findViewById(R.id.bar_left);
        this.f = (ListView) this.p.findViewById(R.id.lv_fans_rank);
        this.g = (RelativeLayout) this.p.findViewById(R.id.rl_right);
        this.l = (TextView) this.p.findViewById(R.id.tv_title_right);
        this.m = this.p.findViewById(R.id.bar_right);
        this.h = (ListView) this.p.findViewById(R.id.lv_gift_rank);
        b();
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
        return this.p;
    }

    protected void initData() {
        super.initData();
        if (this.j == null) {
            this.j = new ArrayList();
        }
        if (this.i == null) {
            this.i = new FansListEngine(new ag(this));
            updateNowFans();
        }
    }

    public void initGiftList(List<GiftListItemBean> list) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.clear();
        this.k.addAll(list);
        if (this.r) {
            this.c = new GiftListOfFullScreenAdapter(this.mBaseRoomActivity, this.k);
            this.h.setAdapter(this.c);
            this.r = false;
            return;
        }
        this.c.notifyDataSetChanged();
    }

    public void sendGiftListSocket() {
        this.mBaseRoomActivity.sendGiftList(this.mWrapRoomInfo.getRoominfoBean().getRid());
    }

    public void updateFansTime(String str) {
        this.d = str;
    }

    public void updateNowFans() {
        if (this.mWrapRoomInfo != null) {
            this.i.getNowFansList(this.mWrapRoomInfo.getRoominfoBean().getId(), this.d);
        }
    }

    private void b() {
        this.e.setSelected(true);
        this.g.setSelected(false);
        this.o.setSelected(true);
        this.l.setSelected(false);
        this.n.setSelected(true);
        this.m.setSelected(false);
        this.f.setVisibility(0);
        this.h.setVisibility(8);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_left) {
            b();
        } else if (id == R.id.rl_right) {
            this.e.setSelected(false);
            this.g.setSelected(true);
            this.o.setSelected(false);
            this.l.setSelected(true);
            this.n.setSelected(false);
            this.m.setSelected(true);
            this.f.setVisibility(8);
            this.h.setVisibility(0);
        }
    }
}
