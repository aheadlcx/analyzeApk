package cn.v6.sixrooms.pay.ui.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.pay.adapter.PaySelectAdapter;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine;
import cn.v6.sixrooms.pay.engine.PayInfoEngine;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.util.ArrayList;
import java.util.List;

public class CoopPayAcitvity extends SlidingActivity implements OnClickListener {
    private Resources a;
    private TextView b;
    private TextView c;
    private RelativeLayout d;
    private PopupWindow e;
    private List<PaySelectBean> f;
    private PaySelectAdapter g;
    private PayInfoEngine h;
    private TextView i;
    private PaySelectBean j;
    private MakeOrderEngine k;
    private Dialog l;
    private OrderStatusEngine m;
    private String n;
    private UserInfoEngine o;
    private RelativeLayout p;
    private int q;
    private RelativeLayout r;
    private TextView s;
    private InnerReceiver t;
    private Handler u = new aa(this);

    public class InnerReceiver extends BroadcastReceiver {
        final /* synthetic */ CoopPayAcitvity a;

        public InnerReceiver(CoopPayAcitvity coopPayAcitvity) {
            this.a = coopPayAcitvity;
        }

        public void onReceive(Context context, Intent intent) {
            CoopPayAcitvity.a(this.a);
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_alipay);
        this.q = getWindowManager().getDefaultDisplay().getWidth();
        this.a = getResources();
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new af(this));
        this.b = (TextView) findViewById(R.id.tv_account);
        this.c = (TextView) findViewById(R.id.tv_coin6);
        this.d = (RelativeLayout) findViewById(R.id.rl_alipay_info);
        this.i = (TextView) findViewById(R.id.tv_alipay_text);
        this.p = (RelativeLayout) findViewById(R.id.mRechargeView);
        this.r = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.s = (TextView) findViewById(R.id.tv_loadingHint);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.str_pay), new ad(this), new ae(this));
        a();
        this.f = new ArrayList();
        this.h = new PayInfoEngine(new ag(this));
        this.h.getPayInfo(SaveUserInfoUtils.getEncpass(this), GlobleValue.getUserBean().getId());
        this.d.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.q, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.p.startAnimation(translateAnimation);
        this.t = new InnerReceiver(this);
        registerReceiver(this.t, new IntentFilter(CustomBroadcast.COOP_RECHARGE));
    }

    private void a() {
        String alias = GlobleValue.getUserBean().getAlias();
        String str = this.a.getString(R.string.str_recharge_account) + alias;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), str.indexOf(alias), str.length(), 33);
        this.b.setText(spannableStringBuilder);
        alias = GlobleValue.getUserBean().getCoin6();
        str = this.a.getString(R.string.str_recharge_coin6) + alias + this.a.getString(R.string.user_coin6);
        spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), str.indexOf(alias), str.length(), 33);
        this.c.setText(spannableStringBuilder);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.rl_alipay_info) {
            if (this.e == null && this.f.size() > 0) {
                View inflate = View.inflate(this, R.layout.phone_pay_select_list, null);
                this.e = new PopupWindow(inflate, this.d.getWidth(), -2, true);
                this.e.setBackgroundDrawable(new ColorDrawable(0));
                ListView listView = (ListView) inflate.findViewById(R.id.mListView);
                this.g = new PaySelectAdapter(this, this.f);
                listView.setAdapter(this.g);
                listView.setOnItemClickListener(new ah(this));
            }
            if (this.e != null) {
                this.e.showAsDropDown(this.d, 0, -this.d.getHeight());
            }
        }
    }

    protected void showErrorDialog() {
        new DialogUtils(this).createConfirmDialogs(25, "提示", "充值失败", "确定", new ab(this)).show();
    }

    protected void showSucessDialog() {
        new DialogUtils(this).createConfirmDialogs(23, "提示", "充值成功", "确定", new ac(this)).show();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.r.isShown()) {
            return super.onKeyUp(i, keyEvent);
        }
        this.r.setVisibility(8);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.u != null) {
            this.u.removeCallbacksAndMessages(null);
        }
        unregisterReceiver(this.t);
    }

    static /* synthetic */ void a(CoopPayAcitvity coopPayAcitvity) {
        if (coopPayAcitvity.m == null) {
            coopPayAcitvity.m = new OrderStatusEngine(new ak(coopPayAcitvity));
        }
    }

    static /* synthetic */ void b(CoopPayAcitvity coopPayAcitvity) {
        String id = GlobleValue.getUserBean().getId();
        String encpass = SaveUserInfoUtils.getEncpass(coopPayAcitvity);
        if (coopPayAcitvity.k == null) {
            coopPayAcitvity.k = new MakeOrderEngine();
        }
        if (coopPayAcitvity.j != null) {
            coopPayAcitvity.k.coopMakeOrder(encpass, id, coopPayAcitvity.j.getMoney(), new ai(coopPayAcitvity));
        }
    }

    static /* synthetic */ void j(CoopPayAcitvity coopPayAcitvity) {
        LogUtils.i("CoopPayAcitvity", "开始请求用户信息");
        if (coopPayAcitvity.o == null) {
            coopPayAcitvity.o = new UserInfoEngine(new aj(coopPayAcitvity));
        }
        coopPayAcitvity.o.getUserInfo(SaveUserInfoUtils.getEncpass(coopPayAcitvity), "");
    }
}
