package cn.v6.sixrooms.ui.phone;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.BillAdapter;
import cn.v6.sixrooms.adapter.DropDownAdapter;
import cn.v6.sixrooms.bean.BillBean;
import cn.v6.sixrooms.engine.BillEngine;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.widgets.phone.BillPullToRefreshView;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BillManagerActivity extends SlidingActivity {
    private TextView a;
    private TextView b;
    private RelativeLayout c;
    private RelativeLayout d;
    private RelativeLayout e;
    private PopupWindow f;
    private PopupWindow g;
    private List<String> h;
    private List<String> i;
    private DropDownAdapter j;
    private DropDownAdapter k;
    private BillEngine l;
    private StickyListHeadersListView m;
    private BillAdapter n;
    private BillPullToRefreshView o;
    private int p = 0;
    private String q = "";
    private int r;
    private SparseArray<String> s;
    private List<BillBean> t;
    private boolean u = false;
    private boolean v = true;
    private OnItemClickListener w = new m(this);
    private OnItemClickListener x = new n(this);

    public enum Popup {
        Menu,
        Calendar
    }

    static /* synthetic */ void a(BillManagerActivity billManagerActivity) {
        if (billManagerActivity.c.getVisibility() == 0) {
            billManagerActivity.c.setVisibility(8);
        }
        if (billManagerActivity.a.getVisibility() == 0) {
            billManagerActivity.a.setVisibility(8);
        }
        billManagerActivity.o.onComplete();
    }

    static /* synthetic */ boolean l(BillManagerActivity billManagerActivity) {
        if (billManagerActivity.s == null || TextUtils.isEmpty((CharSequence) billManagerActivity.s.get(2)) || Integer.parseInt((String) billManagerActivity.s.get(2)) <= billManagerActivity.r) {
            return false;
        }
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_bill);
        this.e = (RelativeLayout) findViewById(R.id.phone_avtivity_rl);
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.e.startAnimation(translateAnimation);
        this.c = (RelativeLayout) findViewById(R.id.phone_activity_bill_loding);
        this.a = (TextView) findViewById(R.id.phone_activity_bill_tv_not_data);
        this.m = (StickyListHeadersListView) findViewById(R.id.phone_activity_bill_lv);
        this.d = (RelativeLayout) findViewById(R.id.phone_activity_bill_title_include);
        this.b = (TextView) findViewById(R.id.item_bill_title_content);
        this.o = (BillPullToRefreshView) findViewById(R.id.phone_activity_bill_refresh);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.rooms_thirds_calendar), getResources().getString(R.string.receive_gift), getResources().getDrawable(R.drawable.phone_room_select_man_arrow_down), new f(this), new g(this), new h(this));
        this.r = 1;
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new l(this));
        this.h = a(Popup.Menu);
        Resources resources = getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.ranking_drop_down_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.ranking_drop_down_heigh);
        int dimensionPixelOffset3 = resources.getDimensionPixelOffset(R.dimen.ranking_drop_down_pading_height);
        View inflate = LayoutInflater.from(this).inflate(R.layout.phone_ranking_drop_down, null);
        ListView listView = (ListView) inflate.findViewById(R.id.drop_lv);
        this.j = new DropDownAdapter(this, this.h, (dimensionPixelOffset2 - (dimensionPixelOffset3 * 2)) / 4);
        listView.setAdapter(this.j);
        listView.setOnItemClickListener(this.w);
        this.f = new PopupWindow(inflate, dimensionPixelOffset, dimensionPixelOffset2, true);
        this.f.setBackgroundDrawable(new ColorDrawable(0));
        this.i = a(Popup.Calendar);
        Resources resources2 = getResources();
        dimensionPixelOffset2 = resources2.getDimensionPixelOffset(R.dimen.ranking_drop_down_width);
        dimensionPixelOffset3 = resources2.getDimensionPixelOffset(R.dimen.ranking_drop_down_heigh);
        int dimensionPixelOffset4 = resources2.getDimensionPixelOffset(R.dimen.ranking_drop_down_pading_height);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.phone_ranking_drop_down, null);
        inflate2.setBackgroundResource(R.drawable.rooms_thirdcalendar_drop_down);
        listView = (ListView) inflate2.findViewById(R.id.drop_lv);
        this.k = new DropDownAdapter(this, this.i, (dimensionPixelOffset3 - (dimensionPixelOffset4 * 2)) / 4);
        listView.setAdapter(this.k);
        listView.setOnItemClickListener(this.x);
        this.g = new PopupWindow(inflate2, dimensionPixelOffset2, resources2.getDimensionPixelOffset(R.dimen.bill_date_height), true);
        this.g.setBackgroundDrawable(new ColorDrawable(0));
        this.l = new BillEngine();
        this.n = new BillAdapter(this);
        this.m.setAdapter(this.n);
        this.l.setCallBack(new i(this));
        this.o.setOnHeaderRefreshListener(new j(this));
        this.o.setOnFooterRefreshListener(new k(this));
        a(0);
    }

    private void a(int i) {
        this.c.setVisibility(0);
        this.a.setVisibility(8);
        switch (i) {
            case 0:
                setTitleBarRightVisible(true);
                this.l.getGift(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this), this.q, 0, this.r);
                return;
            case 1:
                setTitleBarRightVisible(true);
                this.l.getGift(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this), this.q, 1, this.r);
                return;
            case 2:
                setTitleBarRightVisible(false);
                this.l.getGift(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this), this.q, 2, this.r);
                return;
            case 3:
                setTitleBarRightVisible(true);
                this.l.getGift(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this), this.q, 3, this.r);
                return;
            default:
                return;
        }
    }

    private void a(View view, Popup popup) {
        if (popup == Popup.Menu) {
            this.f.showAsDropDown(view, getResources().getDimensionPixelOffset(R.dimen.offset_x), 0);
        } else if (popup == Popup.Calendar) {
            this.g.showAsDropDown(view, getResources().getDimensionPixelOffset(R.dimen.offset_x_calendar), 0);
        }
    }

    private List<String> a(Popup popup) {
        List<String> arrayList = new ArrayList();
        if (popup == Popup.Menu) {
            arrayList = Arrays.asList(getResources().getStringArray(R.array.bill_drop_down));
        } else if (popup == Popup.Calendar) {
            Calendar instance = Calendar.getInstance();
            if (!TextUtils.isEmpty(this.q)) {
                instance.setTimeInMillis(Long.parseLong(this.q) * 1000);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            for (int i = 0; i < 30; i++) {
                arrayList.add(simpleDateFormat.format(instance.getTime()));
                instance.add(5, -1);
            }
        }
        LogUtils.i("BillManagerActivity", "list:" + arrayList.toString());
        return arrayList;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }

    public void handleErrorResult(String str, String str2) {
        handleErrorResult(str, str2, this);
    }

    static /* synthetic */ void a(BillManagerActivity billManagerActivity, View view) {
        if (billManagerActivity.g == null || !billManagerActivity.g.isShowing()) {
            billManagerActivity.a(view, Popup.Calendar);
        } else {
            billManagerActivity.g.dismiss();
        }
    }

    static /* synthetic */ void b(BillManagerActivity billManagerActivity, View view) {
        if (billManagerActivity.f == null || !billManagerActivity.f.isShowing()) {
            billManagerActivity.a(view, Popup.Menu);
        } else {
            billManagerActivity.f.dismiss();
        }
    }

    static /* synthetic */ void s(BillManagerActivity billManagerActivity) {
        billManagerActivity.r = 1;
        billManagerActivity.u = false;
    }
}
