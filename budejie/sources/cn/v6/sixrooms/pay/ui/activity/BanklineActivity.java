package cn.v6.sixrooms.pay.ui.activity;

import android.app.Dialog;
import android.content.Intent;
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
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import com.umeng.update.net.f;
import com.unionpay.UPPayAssistEx;
import java.util.ArrayList;
import java.util.List;

public class BanklineActivity extends SlidingActivity implements OnClickListener {
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
    private Handler u = new v(this);

    static /* synthetic */ void a(BanklineActivity banklineActivity, OrderBean orderBean) {
        if (UPPayAssistEx.startPay(banklineActivity, null, null, orderBean.getMsg(), "00") == -1) {
            UPPayAssistEx.installUPPayPlugin(banklineActivity);
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_bankline);
        this.a = getResources();
        this.t = getWindowManager().getDefaultDisplay().getWidth();
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new t(this));
        this.b = (TextView) findViewById(R.id.tv_account);
        this.c = (TextView) findViewById(R.id.tv_coin6);
        this.d = (RelativeLayout) findViewById(R.id.rl_bankline_pay_info);
        this.i = (TextView) findViewById(R.id.tv_bankline_pay_text);
        this.s = (RelativeLayout) findViewById(R.id.mBankline);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.str_pay_bankline), new o(this), new s(this));
        a();
        this.f = new ArrayList();
        this.h = new PayInfoEngine(new u(this));
        this.h.getPayInfo(SaveUserInfoUtils.getEncpass(this), GlobleValue.getUserBean().getId());
        this.d.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.t, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.s.startAnimation(translateAnimation);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            Object string = intent.getExtras().getString("pay_result");
            if (!string.equalsIgnoreCase(f.c) && !TextUtils.isEmpty(string) && string.equalsIgnoreCase("success")) {
                if (this.l == null) {
                    this.l = DialogUtils.createProgressDialog(this, getString(R.string.str_submiting));
                }
                if (!this.l.isShowing()) {
                    this.l.show();
                }
                if (this.p == null) {
                    this.p = new OrderStatusEngine(new y(this));
                }
                new z(this).execute(new Void[0]);
            }
        }
    }

    protected void showErrorDialog() {
        new DialogUtils(this).createConfirmDialogs(25, "提示", "充值失败", "确定", new p(this)).show();
    }

    protected void showSucessDialog() {
        new DialogUtils(this).createConfirmDialogs(23, "提示", "充值成功", "确定", new q(this)).show();
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
        if (view.getId() == R.id.rl_bankline_pay_info) {
            if (this.e == null && this.f.size() > 0) {
                View inflate = View.inflate(this, R.layout.phone_pay_select_list, null);
                this.e = new PopupWindow(inflate, this.d.getWidth(), -2, true);
                this.e.setBackgroundDrawable(new ColorDrawable(0));
                ListView listView = (ListView) inflate.findViewById(R.id.mListView);
                this.g = new PaySelectAdapter(this, this.f);
                listView.setAdapter(this.g);
                listView.setOnItemClickListener(new w(this));
            }
            if (this.e != null) {
                this.e.showAsDropDown(this.d, 0, -this.d.getHeight());
            }
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.u != null) {
            this.u.removeCallbacksAndMessages(null);
        }
    }

    static /* synthetic */ void a(BanklineActivity banklineActivity) {
        banklineActivity.o = true;
        String id = GlobleValue.getUserBean().getId();
        String encpass = SaveUserInfoUtils.getEncpass(banklineActivity);
        if (banklineActivity.k == null) {
            banklineActivity.k = new MakeOrderEngine(new x(banklineActivity));
        }
        if (banklineActivity.j != null) {
            banklineActivity.k.makeOrder(CommonPay.GATETYPE_BANKLINE, id, encpass, banklineActivity.j.getMoney(), banklineActivity.j.getCoin6(), "", "", "");
        }
    }

    static /* synthetic */ void k(BanklineActivity banklineActivity) {
        if (banklineActivity.m == null) {
            View inflate = View.inflate(banklineActivity, R.layout.phone_pay_dialog_error, null);
            Dialog dialog = new Dialog(banklineActivity, R.style.Theme_dialog);
            dialog.setCancelable(false);
            dialog.setContentView(inflate);
            banklineActivity.m = dialog;
        }
        if (!banklineActivity.m.isShowing()) {
            banklineActivity.m.show();
            banklineActivity.u.sendEmptyMessageDelayed(2, 1000);
        }
    }

    static /* synthetic */ void m(BanklineActivity banklineActivity) {
        if (banklineActivity.r == null) {
            banklineActivity.r = new UserInfoEngine(new r(banklineActivity));
        }
        banklineActivity.r.getUserInfo(SaveUserInfoUtils.getEncpass(banklineActivity), "");
    }

    static /* synthetic */ void p(BanklineActivity banklineActivity) {
        if (!banklineActivity.isFinishing()) {
            new DialogUtils(banklineActivity).createDiaglog(banklineActivity.a.getString(R.string.str_pay_delayed_success)).show();
        }
    }
}
