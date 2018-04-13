package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.ViewerAdapter;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.engine.RefreshChatListEngine;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class SpectatorPage extends RelativeLayout implements OnClickListener {
    private SwitchSpectatorListener A;
    private BaseRoomActivity a;
    private WrapRoomInfo b;
    private RelativeLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    public boolean isLoadingDate = true;
    private TextView j;
    private TextView k;
    private TextView l;
    private SpectorPullToRefresh m;
    private final int n = 30;
    private final int o = 31;
    private final int p = 32;
    private int q = 32;
    private List<UserInfoBean> r;
    private RefreshChatListEngine s;
    private List<UserInfoBean> t;
    private List<UserInfoBean> u;
    private List<UserInfoBean> v;
    private ViewerAdapter w;
    private ListView x;
    private boolean y = false;
    private CallBack z;

    public interface CallBack {
        void onSetSpectatorNum(String str);

        void onShowPublicMenu(UserInfoBean userInfoBean);
    }

    public interface SwitchSpectatorListener {
        void onSwitchSpectator();
    }

    public SpectatorPage(Context context, WrapRoomInfo wrapRoomInfo, CallBack callBack) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.phone_room_spectator_page, this, true);
        this.a = (BaseRoomActivity) context;
        this.isLoadingDate = true;
        this.z = callBack;
        this.b = wrapRoomInfo;
        this.c = (RelativeLayout) findViewById(R.id.ll_spectator_page);
        this.d = (LinearLayout) findViewById(R.id.ll_manager);
        this.e = (LinearLayout) findViewById(R.id.ll_guard);
        this.f = (LinearLayout) findViewById(R.id.ll_common_spectator);
        this.h = (ImageView) findViewById(R.id.iv_manager);
        this.i = (ImageView) findViewById(R.id.iv_common_spectator);
        this.j = (TextView) findViewById(R.id.tv_manager_count);
        this.k = (TextView) findViewById(R.id.tv_common_spectator_count);
        this.l = (TextView) findViewById(R.id.tv_guard_count);
        this.m = (SpectorPullToRefresh) findViewById(R.id.ll_spectator_view);
        this.m.initSwitchSpectatorListener(this);
        this.x = (ListView) findViewById(R.id.lv_spectator);
        this.g = (ImageView) findViewById(R.id.iv_guard);
        a();
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void setRoomInfo(WrapRoomInfo wrapRoomInfo) {
        this.b = wrapRoomInfo;
        if (this.r == null) {
            a();
        } else if (!this.y) {
            WrapUserInfo wrapUserInfo = this.b.getWrapUserInfo();
            ArrayList allList = wrapUserInfo.getAllList();
            this.r.clear();
            this.t.clear();
            this.u.clear();
            this.v.clear();
            if (wrapUserInfo.getAllAdmList() != null) {
                this.t.addAll(wrapUserInfo.getAllAdmList());
            }
            this.u.addAll(b(allList));
            this.v.addAll(wrapUserInfo.getSafeList());
            this.r.addAll(this.u);
            this.w.notifyDataSetChanged();
            if (allList.size() > 0) {
                this.y = true;
            }
        }
    }

    private static ArrayList<UserInfoBean> b(ArrayList<UserInfoBean> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size() - 1;
            String uid = ((UserInfoBean) arrayList.get(size)).getUid();
            if (!TextUtils.isEmpty(uid) && uid.length() > 8 && uid.equals("1000000000")) {
                arrayList.remove(size);
            }
        }
        return arrayList;
    }

    private void a() {
        if (this.b != null && this.r == null) {
            this.r = new ArrayList();
            this.t = new ArrayList();
            this.u = new ArrayList();
            this.v = new ArrayList();
            WrapUserInfo wrapUserInfo = this.b.getWrapUserInfo();
            this.t.clear();
            this.u.clear();
            this.v.clear();
            this.t.addAll(wrapUserInfo.getAllAdmList());
            this.u.addAll(b(wrapUserInfo.getAllList()));
            this.v.addAll(wrapUserInfo.getSafeList());
            this.r.addAll(this.u);
            this.l.setText("守护(" + wrapUserInfo.getSafeList().size() + ")");
            this.k.setText("观众(" + wrapUserInfo.getNum() + ")");
            this.k.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
            this.j.setText(" 管理员(" + wrapUserInfo.getAllAdmList().size() + ")");
            this.w = new ViewerAdapter(this.a, this.r);
            this.x.setAdapter(this.w);
            this.x.setOnItemClickListener(new aq(this));
            this.s = new RefreshChatListEngine(new ar(this));
            this.m.setOnHeaderRefreshListener(new as(this));
            this.m.setOnFooterRefreshListener(new at(this));
        }
    }

    public void getDate() {
        LogUtils.d("getDate", "413---getDate");
        if (this.r.size() <= 50) {
            LogUtils.d("getDate", "413---getDate1111");
            this.s.getRoomList(this.b.getRoominfoBean().getId(), this.a.getLastUpadateTime());
            return;
        }
        this.a.sendLoadAllRoomList();
    }

    private void b() {
        switch (this.q) {
            case 30:
                this.l.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                this.k.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.j.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.i.setBackgroundResource(R.drawable.rooms_third_room_spectator_no);
                this.f.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.h.setBackgroundResource(R.drawable.rooms_third_manager_normal);
                this.d.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.g.setBackgroundResource(R.drawable.rooms_third_guard_press);
                this.e.setBackgroundResource(R.drawable.room_bottombar_bg_checked);
                this.r.clear();
                this.r.addAll(this.v);
                this.w.state(30);
                this.w.notifyDataSetChanged();
                this.m.isBanFooterRefresh(false);
                break;
            case 31:
                this.k.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.l.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.j.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                this.i.setBackgroundResource(R.drawable.rooms_third_room_spectator_no);
                this.f.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.h.setBackgroundResource(R.drawable.rooms_third_manager_press);
                this.d.setBackgroundResource(R.drawable.room_bottombar_bg_checked);
                this.g.setBackgroundResource(R.drawable.rooms_third_guard_normal);
                this.e.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.r.clear();
                this.r.addAll(this.t);
                this.w.state(31);
                this.w.notifyDataSetChanged();
                this.m.isBanFooterRefresh(false);
                break;
            case 32:
                this.l.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.k.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
                this.j.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
                this.i.setBackgroundResource(R.drawable.rooms_third_room_spectator);
                this.f.setBackgroundResource(R.drawable.room_bottombar_bg_checked);
                this.h.setBackgroundResource(R.drawable.rooms_third_manager_normal);
                this.d.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.g.setBackgroundResource(R.drawable.rooms_third_guard_normal);
                this.e.setBackgroundResource(R.drawable.room_bottombar_bg_unchecked);
                this.r.clear();
                this.r.addAll(this.u);
                this.w.state(32);
                this.w.notifyDataSetChanged();
                this.m.isBanFooterRefresh(false);
                break;
        }
        if (!this.x.isStackFromBottom()) {
            this.x.setStackFromBottom(true);
        }
        this.x.setStackFromBottom(false);
    }

    public void updateSpectatorList(WrapUserInfo wrapUserInfo) {
        if (this.isLoadingDate) {
            new Handler(this.a.getMainLooper()).post(new au(this, wrapUserInfo.getNum()));
        }
    }

    public void updateSpectator(WrapUserInfo wrapUserInfo) {
        if (this.r != null && this.m.isShown()) {
            if (this.q == 31) {
                this.t.clear();
                this.t.addAll(wrapUserInfo.getAllAdmList());
            } else if (this.q == 32) {
                this.u.clear();
                this.u.addAll(b(wrapUserInfo.getAllList()));
            }
        }
    }

    public void setSpectatorPageVisible(int i) {
        this.c.setVisibility(i);
    }

    public void setSpectatorPageVisible() {
        this.c.setVisibility(0);
    }

    public void setSpectatorNum(String str) {
        this.z.onSetSpectatorNum(str);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_guard) {
            if (this.b != null && this.q != 30) {
                this.q = 30;
                a();
                b();
            }
        } else if (id == R.id.ll_manager) {
            if (this.b != null && this.q != 31) {
                this.q = 31;
                a();
                b();
            }
        } else if (id == R.id.ll_common_spectator && this.b != null && this.q != 32) {
            this.q = 32;
            a();
            b();
        }
    }

    public void switchSpectator() {
        if (this.A != null) {
            this.A.onSwitchSpectator();
        }
    }

    public void setSwitchSpectatorListener(SwitchSpectatorListener switchSpectatorListener) {
        this.A = switchSpectatorListener;
    }

    public void showPublicMenu(UserInfoBean userInfoBean) {
        this.z.onShowPublicMenu(userInfoBean);
    }
}
