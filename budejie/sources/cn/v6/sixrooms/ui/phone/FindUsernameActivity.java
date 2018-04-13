package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.UsernameListAdapter;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.engine.FindUsernameEngine;
import cn.v6.sixrooms.utils.DialogUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class FindUsernameActivity extends SlidingActivity {
    private ListView a;
    private UsernameListAdapter b;
    private RelativeLayout c;
    private FindUsernameEngine d;
    private Handler e = new Handler();

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_find_username);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, "找回用户名", new au(this), null);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new aw(this));
        this.a = (ListView) findViewById(R.id.username_listView);
        this.c = (RelativeLayout) findViewById(R.id.rl_progressBar);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_find_username_view);
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        relativeLayout.startAnimation(translateAnimation);
        this.c.setVisibility(0);
        this.e.postDelayed(new av(this), 500);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        finishWithAnimation();
        return true;
    }

    public void finishWithAnimation() {
        getSlidingMenu().a();
    }

    private void a(String str) {
        if (!isFinishing()) {
            new DialogUtils(this).createConfirmDialogs(206, getResources().getString(R.string.tip_show_tip_title), str, getResources().getString(R.string.phone_confirm), new ay(this)).show();
        }
    }

    static /* synthetic */ void a(FindUsernameActivity findUsernameActivity) {
        findUsernameActivity.d = new FindUsernameEngine(new ax(findUsernameActivity));
        findUsernameActivity.d.findUsername(findUsernameActivity.getIntent().getExtras().get("authCode").toString(), findUsernameActivity.getIntent().getExtras().get("mobileNumber").toString());
    }

    static /* synthetic */ void a(FindUsernameActivity findUsernameActivity, int i) {
        String string;
        if (i == 1007 || i == CommonInts.NUMBER_FORMAT_EXCEPTION || i == CommonInts.STRING_OUTOFBOUNDS_EXCEPTION) {
            string = findUsernameActivity.getResources().getString(R.string.tip_json_parse_error_title);
        } else {
            string = findUsernameActivity.getResources().getString(R.string.tip_network_error_title);
        }
        findUsernameActivity.a(string);
    }
}
