package cn.v6.sixrooms.ui.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.PropListAdapter;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.engine.PropActionEngine.CallBack;
import cn.v6.sixrooms.engine.PropListEngine;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class MyPropActivity extends SlidingActivity implements OnClickListener {
    public static final int BACK_FROM_SHOP = 0;
    private StickyListHeadersListView a;
    private int b;
    private RelativeLayout c;
    private TextView d;
    private RelativeLayout e;
    private TextView f;
    private boolean g = true;
    private PropListEngine h;
    private PropListAdapter i;
    private CallBack j;
    @SuppressLint({"HandlerLeak"})
    private Handler k = new bi(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_myprop);
        this.g = false;
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), getResources().getString(R.string.shop_title), null, getResources().getString(R.string.my_prop), new bg(this), new bh(this));
        this.c = (RelativeLayout) findViewById(R.id.slidingview);
        this.a = (StickyListHeadersListView) findViewById(R.id.myprop_list);
        this.d = (TextView) findViewById(R.id.emptyview);
        this.e = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.f = (TextView) findViewById(R.id.tv_loadingHint);
        a(0, getResources().getString(R.string.prop_list_loading));
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new bl(this));
        this.b = getWindowManager().getDefaultDisplay().getWidth();
        Animation translateAnimation = new TranslateAnimation((float) this.b, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.c.startAnimation(translateAnimation);
        this.k.sendEmptyMessageDelayed(1, 500);
    }

    private void a() {
        if (GlobleValue.getUserBean() != null) {
            Object id = GlobleValue.getUserBean().getId();
            if (!TextUtils.isEmpty(id)) {
                this.h.getPropList(id, SaveUserInfoUtils.getEncpass(this));
                a(0, getResources().getString(R.string.prop_list_loading));
                return;
            }
        }
        handleErrorResult(CommonStrs.FLAG_TYPE_NEED_LOGIN, getResources().getString(R.string.tip_shot_off_errro_str), this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.g = true;
    }

    public void onClick(View view) {
        view.getId();
    }

    private void a(int i, String str) {
        this.e.setVisibility(i);
        if (!TextUtils.isEmpty(str)) {
            this.f.setText(str);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (intent != null && intent.getBooleanExtra("hasBoughtSomething", false)) {
                    a();
                    break;
                }
        }
        super.onActivityResult(i, i2, intent);
    }

    static /* synthetic */ void a(MyPropActivity myPropActivity) {
        myPropActivity.j = new bj(myPropActivity);
        myPropActivity.i = new PropListAdapter(myPropActivity, myPropActivity.j);
        myPropActivity.a.setAdapter(myPropActivity.i);
        myPropActivity.h = new PropListEngine(new bk(myPropActivity));
        myPropActivity.a();
    }
}
