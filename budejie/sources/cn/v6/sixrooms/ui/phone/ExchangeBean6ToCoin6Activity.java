package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.AuthCodeEngine;
import cn.v6.sixrooms.engine.ExchangeBean6ToCoin6Engine;
import cn.v6.sixrooms.engine.GetExchangeBean6ToCoin6RulesEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.text.DecimalFormat;

public class ExchangeBean6ToCoin6Activity extends SlidingActivity implements OnClickListener {
    private RelativeLayout a;
    private ExchangeBean6ToCoin6Activity b;
    private Handler c = new ag(this);
    private String d;
    private TextView e;
    private ListView f;
    private TextView g;
    private RelativeLayout h;
    private ExchangeBean6ToCoin6Engine i;
    private DecimalFormat j;
    private TextView k;
    private TextView l;
    private int m = 0;
    private UserInfoEngine n;
    public int width;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        setContentView(R.layout.phone_activity_exchange_bean6);
        this.h = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.h.setOnClickListener(this);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, getResources().getString(R.string.exchange_title), new am(this), null);
        this.a = (RelativeLayout) findViewById(R.id.rootView);
        this.width = getWindowManager().getDefaultDisplay().getWidth();
        Animation translateAnimation = new TranslateAnimation((float) this.width, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.a.startAnimation(translateAnimation);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new ao(this));
        this.c.postDelayed(new an(this), 300);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        finishWithAnimation();
        return true;
    }

    protected void finishWithAnimation() {
        getSlidingMenu().a();
    }

    protected void initListener() {
    }

    protected void initData() {
        new GetExchangeBean6ToCoin6RulesEngine(new ap(this)).sendRequest(SaveUserInfoUtils.getEncpass(this), this.d, "", "exchange");
        this.i = new ExchangeBean6ToCoin6Engine(new aq(this));
    }

    public String getErrorCodeString(int i) {
        if (i == 1007) {
            return getString(R.string.tip_json_parse_error_title);
        }
        return getString(R.string.tip_network_error_title);
    }

    protected void updateUserInfo(String str) {
        if (this.n == null) {
            this.n = new UserInfoEngine(new ar(this, str));
        }
        this.n.getUserInfo(SaveUserInfoUtils.getEncpass(this.b), "");
    }

    protected void initView() {
        this.j = new DecimalFormat("###,###");
        this.e = (TextView) findViewById(R.id.userName);
        String alias = GlobleValue.getUserBean().getAlias();
        this.e.setText(String.format(getString(R.string.exchange_userName), new Object[]{alias}));
        this.k = (TextView) findViewById(R.id.coin6Num);
        this.l = (TextView) findViewById(R.id.bean6Num);
        a();
        this.f = (ListView) findViewById(R.id.listView);
        this.g = (TextView) findViewById(R.id.rate);
    }

    public void onClick(View view) {
        view.getId();
    }

    public void showLoadingScreen(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    public void exchange(String str) {
        showLoadingScreen(true);
        String encpass = SaveUserInfoUtils.getEncpass(this.b);
        String id = GlobleValue.getUserBean().getId();
        this.i.sendRequest(encpass, id, str, SaveUserInfoUtils.getUserCoinV(this.b, id));
    }

    private void a() {
        String coin6 = GlobleValue.getUserBean().getCoin6();
        this.k.setText(String.format(getString(R.string.exchange_number), new Object[]{this.j.format(Long.parseLong(coin6))}));
        coin6 = GlobleValue.getUserBean().getWealth();
        this.l.setText(String.format(getString(R.string.exchange_number), new Object[]{this.j.format(Long.parseLong(coin6))}));
    }

    static /* synthetic */ void e(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        MsgVerifyDialog msgVerifyDialog = new MsgVerifyDialog(exchangeBean6ToCoin6Activity.b);
        msgVerifyDialog.setOnDismissListener(new as(exchangeBean6ToCoin6Activity));
        AuthCodeEngine authCodeEngine = new AuthCodeEngine(new at(exchangeBean6ToCoin6Activity, msgVerifyDialog));
        msgVerifyDialog.setCodeBtnOnclickListener(new ah(exchangeBean6ToCoin6Activity, msgVerifyDialog, authCodeEngine));
        msgVerifyDialog.setComfirmOnclickListener(new aj(exchangeBean6ToCoin6Activity, authCodeEngine, msgVerifyDialog));
        authCodeEngine.getAuthCode(exchangeBean6ToCoin6Activity.d, SaveUserInfoUtils.getEncpass(exchangeBean6ToCoin6Activity.b), new al(exchangeBean6ToCoin6Activity, msgVerifyDialog));
    }
}
