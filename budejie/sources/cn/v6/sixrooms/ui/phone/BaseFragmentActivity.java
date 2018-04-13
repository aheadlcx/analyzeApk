package cn.v6.sixrooms.ui.phone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.v6.sixrooms.Manage;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.event.ActivityOnRestartEvent;
import cn.v6.sixrooms.event.AppBackgroundEvent;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.utils.ActivityManagerUtils;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

public class BaseFragmentActivity extends FragmentActivity {
    private static final String TAG = BaseFragmentActivity.class.getSimpleName();
    private Handler handler = new Handler();
    protected Dialog loginDialog;
    protected BaseFragmentActivity mActivity;
    private TextView titlebar_right;
    private FrameLayout titlebar_right_frame;
    private TextView titlebar_title;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Manage.getInstance().addActivity(this);
        this.mActivity = this;
    }

    protected void onRestart() {
        this.handler.postDelayed(new a(this), 1000);
        EventManager.getDefault().nodifyObservers(new ActivityOnRestartEvent(), "");
        super.onRestart();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, "BaseFragmentActivity  -----》  onStop");
        if (!getPackageName().contains(ActivityManagerUtils.getTopPackageName(this)) && GlobleValue.status) {
            GlobleValue.status = false;
            AppCount.sendAppCountInfo(BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
            EventManager.getDefault().nodifyObservers(new AppBackgroundEvent(), "");
        }
    }

    public void handleErrorResult(String str, String str2, Activity activity) {
        HandleErrorUtils.handleErrorResult(str, str2, activity);
    }

    protected void showNotBoundMobileDialog(String str, Activity activity) {
        HandleErrorUtils.showNotBoundMobileDialog(str, activity);
    }

    public void handleIMSocketErrorResult(int i, String str, String str2, String str3) {
        HandleErrorUtils.handleIMSocketErrorResult(i, str, str2, str3, this);
    }

    public void showToast(String str) {
        ToastUtils.showToast(str);
    }

    public void showErrorToast(int i) {
        HandleErrorUtils.showErrorToast(i);
    }

    public void logout() {
        HandleErrorUtils.logout(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        Manage.getInstance().removeActivity(this);
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    public void showLoginDialog() {
        HandleErrorUtils.showLoginDialog((Activity) this);
    }

    public void show6CoinNotEnoughDialog() {
        HandleErrorUtils.show6CoinNotEnoughDialog("您当前拥有的六币数量不足，请充值", this);
    }

    public void initDefaultTitleBar(String str, Drawable drawable, String str2, Drawable drawable2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        TextView textView = (TextView) findViewById(R.id.titlebar_left);
        this.titlebar_right = (TextView) findViewById(R.id.titlebar_right);
        this.titlebar_title = (TextView) findViewById(R.id.titlebar_title);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.titlebar_left_frame);
        this.titlebar_right_frame = (FrameLayout) findViewById(R.id.titlebar_right_frame);
        if (str == null && drawable == null) {
            frameLayout.setVisibility(4);
        }
        if (str2 == null && drawable2 == null) {
            this.titlebar_right_frame.setVisibility(4);
        }
        this.titlebar_title.setText(str3);
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
        } else {
            textView.setText(str);
        }
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
        } else {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
        }
        if (str == null && drawable == null) {
            frameLayout.setVisibility(4);
        }
        if (onClickListener != null) {
            frameLayout.setOnClickListener(onClickListener);
        } else {
            frameLayout.setOnClickListener(new b(this));
        }
        if (TextUtils.isEmpty(str2)) {
            this.titlebar_right.setText("");
        } else {
            this.titlebar_right.setText(str2);
        }
        if (drawable2 == null) {
            this.titlebar_right.setCompoundDrawables(null, null, null, null);
        } else {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.titlebar_right.setCompoundDrawables(null, null, drawable2, null);
        }
        if (str2 == null && drawable2 == null) {
            this.titlebar_right_frame.setVisibility(4);
        }
        if (onClickListener2 != null) {
            this.titlebar_right_frame.setOnClickListener(onClickListener2);
        }
    }

    public void initDefaultTitleBar(String str, Drawable drawable, String str2, Drawable drawable2, String str3, Drawable drawable3, OnClickListener onClickListener, OnClickListener onClickListener2, OnClickListener onClickListener3) {
        initDefaultTitleBar(str, drawable, str2, drawable2, str3, onClickListener, onClickListener2);
        if (drawable3 == null) {
            this.titlebar_title.setCompoundDrawables(null, null, null, null);
        } else {
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            this.titlebar_title.setCompoundDrawables(null, null, drawable3, null);
        }
        if (onClickListener3 != null) {
            this.titlebar_title.setOnClickListener(onClickListener3);
        }
    }

    public void setTitleBarRightVisible(boolean z) {
        if (z) {
            this.titlebar_right_frame.setVisibility(0);
        } else {
            this.titlebar_right_frame.setVisibility(8);
        }
    }

    public void setTitleText(String str) {
        this.titlebar_title.setText(str);
    }

    public TextView getTitleBarRight() {
        return this.titlebar_right;
    }

    public FrameLayout getTitleBarRightFrame() {
        return this.titlebar_right_frame;
    }

    public void toRechargeActivity() {
        SixRoomsUtils.goToRechargeActivi(this);
    }

    public void startEventActivity(String str, String str2) {
        Intent intent = new Intent(this, EventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("eventurl", str);
        bundle.putString("eventUrlFrom", str2);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
