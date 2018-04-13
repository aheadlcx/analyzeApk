package cn.v6.sixrooms.hall;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.base.VLPagerView;
import cn.v6.sixrooms.base.VLStatedButtonBar;
import cn.v6.sixrooms.base.VLStatedButtonBar$VLStatedButton;
import cn.v6.sixrooms.base.VLStatedButtonBar.VLStatedButtonBarDelegate;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.room.presenter.LocationAndMoblieGiftStartPresenter;
import cn.v6.sixrooms.room.presenter.LocationAndMoblieGiftStartPresenter.LocationAndMobileGiftStarCallBack;
import cn.v6.sixrooms.room.statistic.StatiscProxy;
import cn.v6.sixrooms.room.utils.ConfigUpdataDispatcher;
import cn.v6.sixrooms.ui.fragment.BaseFragment;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class HallActivity extends BaseFragmentActivity implements OnClickListener, LocationAndMobileGiftStarCallBack {
    private VLPagerView a;
    private b b;
    private LocationAndMoblieGiftStartPresenter c;
    private RelativeLayout d;
    private Dialog e;
    private VLStatedButtonBar f;
    private BroadcastReceiver g = new i(this);
    private BroadcastReceiver h = new j(this);
    private UserInfoEngine i;

    private class a implements VLStatedButtonBarDelegate {
        final /* synthetic */ HallActivity a;
        private Context b;

        a(HallActivity hallActivity, Context context) {
            this.a = hallActivity;
            this.b = context;
        }

        public final void onStatedButtonBarCreated(VLStatedButtonBar vLStatedButtonBar) {
            VLStatedButtonBar$VLStatedButton vLStatedButtonBar$VLStatedButton = new VLStatedButtonBar$VLStatedButton(this.b);
            vLStatedButtonBar$VLStatedButton.setStatedButtonDelegate(new HallActivity$a$a(this, "关注", R.drawable.rooms_third_hall_atten_normal, R.drawable.rooms_third_hall_atten_down));
            vLStatedButtonBar$VLStatedButton.setPadding(SixRoomsUtils.dip2px(5.0f), 0, SixRoomsUtils.dip2px(5.0f), 0);
            vLStatedButtonBar.addStatedButton(vLStatedButtonBar$VLStatedButton);
            vLStatedButtonBar$VLStatedButton = new VLStatedButtonBar$VLStatedButton(this.b);
            vLStatedButtonBar$VLStatedButton.setStatedButtonDelegate(new HallActivity$a$a(this, "热门", R.drawable.rooms_third_hall_normal, R.drawable.rooms_third_hall_down));
            vLStatedButtonBar$VLStatedButton.setPadding(SixRoomsUtils.dip2px(5.0f), 0, SixRoomsUtils.dip2px(5.0f), 0);
            vLStatedButtonBar.addStatedButton(vLStatedButtonBar$VLStatedButton);
            vLStatedButtonBar$VLStatedButton = new VLStatedButtonBar$VLStatedButton(this.b);
            vLStatedButtonBar$VLStatedButton.setStatedButtonDelegate(new HallActivity$a$a(this, "我的", R.drawable.rooms_third_hall_me_normal, R.drawable.rooms_third_hall_me_down));
            vLStatedButtonBar$VLStatedButton.setPadding(SixRoomsUtils.dip2px(5.0f), 0, SixRoomsUtils.dip2px(5.0f), 0);
            vLStatedButtonBar.addStatedButton(vLStatedButtonBar$VLStatedButton);
        }

        public final void onStatedButtonBarChanged(VLStatedButtonBar vLStatedButtonBar, int i) {
            StatiscProxy.homeStatistics(i);
            if ((i == 0 || i == 2) && !LoginUtils.isLogin()) {
                vLStatedButtonBar.setChecked(1);
                new ao(this.a).show();
                return;
            }
            this.a.a.gotoPage(i, false);
        }
    }

    interface b {
        void a();
    }

    public static void startSelf(Context context) {
        context.startActivity(new Intent(context, HallActivity.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_hall);
        this.f = (VLStatedButtonBar) findViewById(R.id.buttonBar);
        this.a = (VLPagerView) findViewById(R.id.pagerView);
        this.a.setOffscreenPageLimit(3);
        this.a.setFragmentPages(getSupportFragmentManager(), new BaseFragment[]{AttentionFragment.newInstance(), HotFragment.newInstance(), MineFragment.newInstance(LoginUtils.getLoginUID())});
        this.a.setScrollable(false);
        this.f.setStatedButtonBarDelegate(new a(this, this));
        this.f.setChecked(1);
        if (V6Coop.firstGetGift) {
            ConfigUpdataDispatcher.updateConfig();
        }
        this.d = (RelativeLayout) findViewById(R.id.rl_mobileStar);
        findViewById(R.id.iv_mobileStar_close).setOnClickListener(this);
        findViewById(R.id.iv_mobileStar_tip).setOnClickListener(this);
        this.c = new LocationAndMoblieGiftStartPresenter(this, this);
        this.c.onCreate();
        this.d.setOnTouchListener(new h(this));
        V6Coop.getInstance().repeatGetUserInfo();
        AppCount.sendAppCountInfo("list");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGIN));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.g, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGOUT));
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_mobileStar_close) {
            this.d.setVisibility(8);
        } else if (view.getId() == R.id.iv_mobileStar_tip) {
            this.c.gainMobileGiftStar();
        }
    }

    public void showMobileStarGift() {
        this.d.setVisibility(0);
    }

    public void hideMobileStarGift() {
        this.d.setVisibility(8);
    }

    public void showLoadingDialog() {
        if (this.e == null) {
            this.e = DialogUtils.createProgressDialog(this, getString(R.string.gain_loading));
        }
        if (!this.e.isShowing()) {
            this.e.show();
        }
    }

    public void result(String str, String str2) {
        hideLoadingDialog();
        new DialogUtils(this).createDiaglog(str2).show();
    }

    public void handleErrorInfo(String str, String str2) {
        hideLoadingDialog();
        HandleErrorUtils.handleErrorResult(str, str2, this);
    }

    public void error(int i) {
        hideLoadingDialog();
        getString(R.string.msg_tip_getExPresent_fail);
    }

    public void tipLogin() {
        HandleErrorUtils.showLoginDialog((Activity) this);
    }

    public void tipLocation() {
        Dialog createConfirmDialog = new DialogUtils(this).createConfirmDialog(1, getResources().getString(R.string.InfoAbout), getResources().getString(R.string.hall_location_info), getResources().getString(R.string.cancel), getResources().getString(R.string.confirm), new k(this));
        if (!createConfirmDialog.isShowing()) {
            createConfirmDialog.show();
        }
    }

    public void hideLoadingDialog() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
    }

    public void setOnHideKeyboardListener(b bVar) {
        this.b = bVar;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || this.b == null || !SixRoomsUtils.isShouldHideInput(getCurrentFocus(), motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        this.b.a();
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.c.onDestory();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.d.getVisibility() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        this.d.setVisibility(8);
        return true;
    }

    protected void getUserInfo(String str) {
        if (this.i == null) {
            this.i = new UserInfoEngine(new l(this, str));
        }
        this.i.getUserInfo(SaveUserInfoUtils.getEncpass(this), "");
    }
}
