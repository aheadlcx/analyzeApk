package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.listener.VerifyMessageCallback;
import cn.v6.sixrooms.ui.fragment.BundlePhoneFragment;
import cn.v6.sixrooms.ui.fragment.MsgVerifyFragment;
import cn.v6.sixrooms.ui.fragment.MsgVerifyFragment.MsgVerifyCallBack;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class MsgVerifyFragmentActivity extends SlidingActivity implements VerifyMessageCallback, MsgVerifyCallBack {
    private LinearLayout a;
    private String b;
    private String c;
    private String d;
    private BundlePhoneFragment e;
    private MsgVerifyFragment f;
    private String g;
    public int width;

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_fragment_activity_msg_verify);
        Bundle extras = getIntent().getExtras();
        this.b = extras.getString(UserTrackerConstants.FROM);
        this.c = extras.getString("phoneNumber");
        this.d = extras.getString("isneedpaawd");
        this.g = extras.getString("ticket");
        this.a = (LinearLayout) findViewById(R.id.ll_fragment_activity_msg_verify);
        extras = new Bundle();
        if ("bundle".equals(this.b)) {
            this.e = BundlePhoneFragment.newInstance();
            extras.putString("isneedpaawd", this.d);
            this.e.setArguments(extras);
            changeFragment(this.e);
        } else if ("unbundle".equals(this.b)) {
            this.f = MsgVerifyFragment.newInstance();
            extras.putString("phoneNumber", this.c);
            extras.putString(UserTrackerConstants.FROM, "unbundle");
            this.f.setArguments(extras);
            changeFragment(this.f);
        } else if ("otherPlaceLogin".equals(this.b)) {
            this.f = MsgVerifyFragment.newInstance();
            extras.putString("phoneNumber", "");
            extras.putString(UserTrackerConstants.FROM, "otherPlaceLogin");
            extras.putString("ticket", this.g);
            this.f.setArguments(extras);
            changeFragment(this.f);
        }
        this.width = getWindowManager().getDefaultDisplay().getWidth();
        Animation translateAnimation = new TranslateAnimation((float) this.width, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.a.startAnimation(translateAnimation);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new bf(this));
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        showMenu();
        return true;
    }

    public void verifyMessage(String str, String str2) {
        if (this.f == null) {
            this.f = MsgVerifyFragment.newInstance();
        }
        Bundle bundle = new Bundle();
        bundle.putString("phoneNumber", str);
        bundle.putString(UserTrackerConstants.FROM, "bundle");
        bundle.putString("password", str2);
        this.f.setArguments(bundle);
        addFragment(this.f);
    }

    public void bundleAgain() {
        romoveFragment(this.f);
    }

    public void finish() {
        this.f = null;
        this.e = null;
        super.finish();
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.rl_fragment_activity_msg_verify_content, fragment).commitAllowingStateLoss();
    }

    public void romoveFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(0, R.anim.msg_verify_fragment_out);
        beginTransaction.remove(fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.msg_verify_fragment_in, 0);
        beginTransaction.add(R.id.rl_fragment_activity_msg_verify_content, fragment);
        beginTransaction.commitAllowingStateLoss();
    }
}
