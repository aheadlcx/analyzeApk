package cn.v6.sixrooms.pay.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
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
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.pay.adapter.PaySelectAdapter;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.contants.CommonPay;
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

public class AlipayActivity extends SlidingActivity implements OnClickListener {
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
    private Dialog m;
    private Dialog n;
    private boolean o = true;
    private OrderStatusEngine p;
    private String q;
    private UserInfoEngine r;
    private RelativeLayout s;
    private int t;
    private Handler u = new j(this);
    private Handler v = new f(this);

    public static class AlixOnCancelListener implements OnCancelListener {
        Activity a;

        public AlixOnCancelListener(Activity activity) {
            this.a = activity;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.onKeyDown(4, null);
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_alipay);
        this.t = getWindowManager().getDefaultDisplay().getWidth();
        this.a = getResources();
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new h(this));
        this.b = (TextView) findViewById(R.id.tv_account);
        this.c = (TextView) findViewById(R.id.tv_coin6);
        this.d = (RelativeLayout) findViewById(R.id.rl_alipay_info);
        this.i = (TextView) findViewById(R.id.tv_alipay_text);
        this.s = (RelativeLayout) findViewById(R.id.mRechargeView);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.str_pay_alipay), new a(this), new g(this));
        a();
        this.f = new ArrayList();
        this.h = new PayInfoEngine(new i(this));
        this.h.getPayInfo(SaveUserInfoUtils.getEncpass(this), GlobleValue.getUserBean().getId());
        this.d.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.t, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.s.startAnimation(translateAnimation);
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
                listView.setOnItemClickListener(new k(this));
            }
            if (this.e != null) {
                this.e.showAsDropDown(this.d, 0, -this.d.getHeight());
            }
        }
    }

    protected void showErrorDialog() {
        new DialogUtils(this).createConfirmDialogs(25, "提示", "充值失败", "确定", new d(this)).show();
    }

    protected void showSucessDialog() {
        new DialogUtils(this).createConfirmDialogs(23, "提示", "充值成功", "确定", new e(this)).show();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.u != null) {
            this.u.removeCallbacksAndMessages(null);
        }
    }

    static /* synthetic */ void a(AlipayActivity alipayActivity) {
        alipayActivity.o = true;
        String id = GlobleValue.getUserBean().getId();
        String encpass = SaveUserInfoUtils.getEncpass(alipayActivity);
        if (alipayActivity.k == null) {
            alipayActivity.k = new MakeOrderEngine(new l(alipayActivity));
        }
        if (alipayActivity.j != null) {
            alipayActivity.k.makeOrder(CommonPay.GATETYPE_ALIPAYLESS_NEW, id, encpass, alipayActivity.j.getMoney(), alipayActivity.j.getCoin6(), "", "", "");
        }
    }

    static /* synthetic */ void a(AlipayActivity alipayActivity, OrderBean orderBean) {
        try {
            Object msg = orderBean.getMsg();
            LogUtils.i("AlipayActivity", "payInfo = " + msg);
            if (!TextUtils.isEmpty(msg) && !"NULL".equals(msg) && !"null".equals(msg)) {
                new Thread(new m(alipayActivity, msg)).start();
            }
        } catch (Exception e) {
            super.showToast(alipayActivity.getString(R.string.remote_call_failed));
        }
    }

    static /* synthetic */ void j(AlipayActivity alipayActivity) {
        if (alipayActivity.m == null) {
            View inflate = View.inflate(alipayActivity, R.layout.phone_pay_dialog_error, null);
            Dialog dialog = new Dialog(alipayActivity, R.style.Theme_dialog);
            dialog.setCancelable(false);
            dialog.setContentView(inflate);
            alipayActivity.m = dialog;
        }
        if (!alipayActivity.m.isShowing()) {
            alipayActivity.m.show();
            alipayActivity.u.sendEmptyMessageDelayed(2, 1000);
        }
    }

    static /* synthetic */ void n(AlipayActivity alipayActivity) {
        LogUtils.i("AlipayActivity", "开始请求用户信息");
        if (alipayActivity.r == null) {
            alipayActivity.r = new UserInfoEngine(new n(alipayActivity));
        }
        alipayActivity.r.getUserInfo(SaveUserInfoUtils.getEncpass(alipayActivity), "");
    }

    static /* synthetic */ void r(AlipayActivity alipayActivity) {
        if (!alipayActivity.isFinishing()) {
            LogUtils.i("AlipayActivity", "显示充值成功对话框");
            new DialogUtils(alipayActivity).createDiaglog(alipayActivity.a.getString(R.string.str_pay_delayed_success)).show();
        }
    }

    static /* synthetic */ void s(AlipayActivity alipayActivity) {
        if (alipayActivity.l == null) {
            alipayActivity.l = DialogUtils.createProgressDialog(alipayActivity, alipayActivity.getString(R.string.str_submiting));
        }
        if (!alipayActivity.l.isShowing()) {
            alipayActivity.l.show();
        }
    }

    static /* synthetic */ void t(AlipayActivity alipayActivity) {
        if (alipayActivity.p == null) {
            alipayActivity.p = new OrderStatusEngine(new b(alipayActivity));
        }
        new c(alipayActivity).execute(new Void[0]);
    }
}
