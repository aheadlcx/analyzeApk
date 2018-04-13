package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.SetUserVisibleEngine;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class ChangeUserVisibilityActivity extends SlidingActivity implements OnClickListener {
    private String a = "ChangeUserVisibilityActivity";
    private Handler b = new o(this);
    public final int backFromModifyPws = 0;
    private LinearLayout c;
    private ImageView d;
    private LinearLayout e;
    private ImageView f;
    private SetUserVisibleEngine g;
    private RelativeLayout h;
    private String i = "";

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_change_user_visibility);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlRootView);
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        relativeLayout.startAnimation(translateAnimation);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new s(this));
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, "在线状态", new p(this), null);
        this.h = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.h.setOnClickListener(this);
        this.b.postDelayed(new q(this), 500);
    }

    protected void initData() {
        this.i = getIntent().getStringExtra("visibleStatus");
        LogUtils.d(this.a, "currentUserStatus: " + this.i);
        this.g = new SetUserVisibleEngine(new r(this));
    }

    protected void setTheMark() {
        if ("0".equals(this.i)) {
            this.d.setVisibility(0);
            this.f.setVisibility(4);
        } else if ("1".equals(this.i)) {
            this.d.setVisibility(4);
            this.f.setVisibility(0);
        }
        Intent intent = new Intent();
        intent.putExtra("currentUserStatus", this.i);
        setResult(-1, intent);
    }

    protected void initListener() {
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    protected void initView() {
        this.c = (LinearLayout) findViewById(R.id.llSetUserVisible);
        this.d = (ImageView) findViewById(R.id.markWhenUserVisible);
        this.e = (LinearLayout) findViewById(R.id.llSetUserInvisible);
        this.f = (ImageView) findViewById(R.id.markWhenUserInvisible);
        setTheMark();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        getSlidingMenu().a();
        return true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.llSetUserVisible) {
            showLoadingScreen();
            this.i = "0";
            a();
        } else if (id == R.id.llSetUserInvisible) {
            showLoadingScreen();
            this.i = "1";
            a();
        }
    }

    private void a() {
        this.g.setUserVisible(this.i, GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(this));
    }

    public void showLoadingScreen() {
        this.h.setVisibility(0);
    }

    public void hideLoadingScreen() {
        this.h.setVisibility(8);
    }
}
