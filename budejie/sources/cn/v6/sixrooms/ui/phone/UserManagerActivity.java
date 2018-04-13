package cn.v6.sixrooms.ui.phone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.listener.LoginManagerListener;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.ui.fragment.LoginFragment;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.ImprovedProgressDialog;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class UserManagerActivity extends BaseFragmentActivity implements LoginManagerListener {
    private Bundle a;
    private FragmentManager b;
    private LoginFragment c;
    private DialogUtils d;
    private ImprovedProgressDialog e;
    private UserInfoEngine f;
    private String g;
    private String h;
    private int i;

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.a = bundle;
        setContentView(R.layout.phone_activity_user_manager);
        Intent intent = getIntent();
        this.g = intent.getStringExtra("rid");
        this.h = intent.getStringExtra(RoomBaseFragment.RUID_KEY);
        this.i = intent.getIntExtra("type", 0);
        this.a = bundle;
        this.b = getSupportFragmentManager();
        if (this.i == 0) {
            if (this.c == null && this.a != null) {
                this.c = (LoginFragment) this.b.findFragmentByTag(LoginFragment.class.getSimpleName());
            }
            if (this.c == null) {
                this.c = new LoginFragment();
            }
            changeFragment(this.c);
            return;
        }
        changeRegisterPage();
    }

    public void makeView(Context context, String str, String str2) {
        if (this.e == null) {
            this.e = new ImprovedProgressDialog(context, str2);
        }
        this.e.show();
    }

    public void dismiss() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_content, fragment).commitAllowingStateLoss();
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
        beginTransaction.add(R.id.ll_content, fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    protected void getUserInfo(String str) {
        this.f = new UserInfoEngine(new db(this, str));
        this.f.getUserInfo(SaveUserInfoUtils.getEncpass(this), "");
    }

    public void showTipDialog(String str) {
        if (this.d == null) {
            this.d = new DialogUtils(this);
        }
        this.d.createDiaglog(str, getString(R.string.InfoAbout)).show();
    }

    public void userLoginSuccess(String str) {
        getUserInfo(str);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.msg_verify_fragment_out);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1 && intent != null) {
            getUserInfo(intent.getStringExtra("op"));
        }
    }

    public void changeRegisterPage() {
        startActivityForResult(new Intent(this, RegisterActivity.class), 1);
    }
}
