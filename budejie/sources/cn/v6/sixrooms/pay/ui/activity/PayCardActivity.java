package cn.v6.sixrooms.pay.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.pay.adapter.PaySelectAdapter;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.engine.PayInfoEngine;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.util.ArrayList;
import java.util.List;

public class PayCardActivity extends SlidingActivity implements OnClickListener {
    private Resources a;
    private TextView b;
    private TextView c;
    private TextView d;
    private RelativeLayout e;
    private PopupWindow f;
    private PaySelectBean g;
    private TextView h;
    private PayInfoEngine i;
    private List<PaySelectBean> j;
    private List<PaySelectBean> k;
    private List<PaySelectBean> l;
    private PaySelectBean m;
    private RelativeLayout n;
    private TextView o;
    private PopupWindow p;
    private List<PaySelectBean> q;
    private PaySelectAdapter r;
    private UserInfoEngine s;
    private TextView t;
    private RelativeLayout u;
    private int v;

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_paycard);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.str_pay_card), new av(this), new aw(this));
        this.v = getWindowManager().getDefaultDisplay().getWidth();
        this.a = getResources();
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new ax(this));
        this.b = (TextView) findViewById(R.id.tv_account);
        this.c = (TextView) findViewById(R.id.tv_coin6);
        this.d = (TextView) findViewById(R.id.tv_select_money);
        this.e = (RelativeLayout) findViewById(R.id.rl_pay_supply);
        this.h = (TextView) findViewById(R.id.tv_pay_supply_content);
        this.n = (RelativeLayout) findViewById(R.id.rl_pay_info_select);
        this.o = (TextView) findViewById(R.id.tv_pay_info);
        this.t = (TextView) findViewById(R.id.tv_warn_info);
        this.u = (RelativeLayout) findViewById(R.id.payCardView);
        this.q = new ArrayList();
        a();
        Object string = this.a.getString(R.string.str_pay_select_money);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), 7, string.length() - 1, 33);
        this.d.setText(spannableStringBuilder);
        this.j = new ArrayList();
        PaySelectBean paySelectBean = new PaySelectBean(0, 0, this.a.getString(R.string.str_pay_mobile_m));
        PaySelectBean paySelectBean2 = new PaySelectBean(0, 1, this.a.getString(R.string.str_pay_mobile_u));
        this.j.add(paySelectBean);
        this.j.add(paySelectBean2);
        this.g = (PaySelectBean) this.j.get(0);
        this.i = new PayInfoEngine(new ay(this));
        this.i.getPayInfo(SaveUserInfoUtils.getEncpass(this), GlobleValue.getUserBean().getId());
        this.e.setOnClickListener(this);
        this.n.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.v, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.u.startAnimation(translateAnimation);
    }

    public void onClick(View view) {
        int id = view.getId();
        View inflate;
        ListView listView;
        if (id == R.id.rl_pay_supply) {
            if (this.f == null) {
                inflate = View.inflate(this, R.layout.phone_pay_select_list, null);
                this.f = new PopupWindow(inflate, this.e.getWidth(), -2, true);
                this.f.setBackgroundDrawable(new ColorDrawable(0));
                listView = (ListView) inflate.findViewById(R.id.mListView);
                listView.setAdapter(new PaySelectAdapter(this, this.j));
                listView.setOnItemClickListener(new ba(this));
            }
            this.f.showAsDropDown(this.e, 0, -this.e.getHeight());
        } else if (id == R.id.rl_pay_info_select) {
            if (this.p == null && this.q.size() > 0) {
                inflate = View.inflate(this, R.layout.phone_pay_select_list, null);
                this.p = new PopupWindow(inflate, this.n.getWidth(), -2, true);
                this.p.setBackgroundDrawable(new ColorDrawable(0));
                listView = (ListView) inflate.findViewById(R.id.mListView);
                if (this.g.getId() == 0) {
                    this.q.clear();
                    this.q.addAll(this.k);
                } else if (this.g.getId() == 1) {
                    this.q.clear();
                    this.q.addAll(this.l);
                }
                this.r = new PaySelectAdapter(this, this.q);
                listView.setAdapter(this.r);
                listView.setOnItemClickListener(new az(this));
            }
            if (this.p != null) {
                this.p.showAsDropDown(this.n, 0, -this.n.getHeight());
            }
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.s == null) {
            this.s = new UserInfoEngine(new bb(this));
        }
        this.s.getUserInfo(SaveUserInfoUtils.getEncpass(this), "");
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

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }
}
