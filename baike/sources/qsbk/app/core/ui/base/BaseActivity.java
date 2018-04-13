package qsbk.app.core.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qiushibaike.statsdk.StatSDK;
import com.umeng.analytics.MobclickAgent;
import qsbk.app.core.R;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.stat.BaiduStatistics;

public abstract class BaseActivity extends BaseSystemBarTintActivity {
    private final long ForceStatInterval = 600000;
    public volatile boolean isOnResume = false;
    private long lastForceStatTime;
    public Handler mHandler = new a(this);
    protected ProgressDialog mProgressDialog;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        initView();
        initData();
        this.lastForceStatTime = System.currentTimeMillis();
    }

    protected void setUp() {
        setOnUpClickListener(new b(this));
    }

    protected void setOnUpClickListener(OnClickListener onClickListener) {
        View findViewById = findViewById(R.id.iv_up);
        if (findViewById != null) {
            findViewById.setOnClickListener(onClickListener);
        }
    }

    protected void setTitle(String str) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(str);
        }
    }

    public FragmentActivity getActivity() {
        return this;
    }

    protected void onHandleMessage(Message message) {
    }

    public void showSavingDialog(int i) {
        showSavingDialog(getString(i));
    }

    public void showSavingDialog(String str) {
        showSavingDialog(str, true);
    }

    public void showSavingDialog(String str, boolean z) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            try {
                if (this.mProgressDialog == null) {
                    this.mProgressDialog = ProgressDialog.show(getActivity(), null, str, true, z);
                }
                if (this.mProgressDialog.isShowing()) {
                    this.mProgressDialog.setMessage(str);
                    return;
                }
                this.mProgressDialog.show();
                this.mProgressDialog.setMessage(str);
                this.mProgressDialog.setCancelable(z);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hideSavingDialog() {
        hideSavingDialog(this.mProgressDialog);
    }

    public void hideSavingDialog(ProgressDialog progressDialog) {
        if (!isFinishing() && progressDialog != null && progressDialog.getWindow() != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showSnackbar(String str) {
        showSnackbar(str, null);
    }

    public void showSnackbar(String str, String str2) {
        ToastUtil.Short(str);
    }

    protected void onResume() {
        super.onResume();
        this.isOnResume = true;
        BaiduStatistics.activityPageOnResume(this);
        StatSDK.onResume(this);
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        this.isOnResume = false;
        BaiduStatistics.acticityPageOnPause(this);
        StatSDK.onPause(this);
        MobclickAgent.onPause(this);
    }

    protected void onStop() {
        super.onStop();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_right, R.anim.roll_left);
    }

    protected void onDestroy() {
        hideSavingDialog();
        this.mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    protected <T extends View> T $(int i) {
        return findViewById(i);
    }

    protected void postDelayed(Runnable runnable) {
        postDelayed(runnable, 0);
    }

    protected void postDelayed(Runnable runnable, long j) {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(runnable);
            if (!isFinishing()) {
                this.mHandler.postDelayed(runnable, j);
            }
        }
    }

    protected void removeDelayed(Runnable runnable) {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    protected void statEvent(String str, String str2, String str3, String str4, String str5) {
        StatSDK.onEvent(this, str, str2, str3, str4, str5);
        forceStatIfNeed();
    }

    protected void statEventDuration(String str, String str2, long j, String str3, String str4, String str5) {
        StatSDK.onEventDuration(this, str, str2, j, str3, str4, str5);
        forceStatIfNeed();
    }

    private void forceStatIfNeed() {
        if (System.currentTimeMillis() - this.lastForceStatTime > 600000) {
            StatSDK.forceReport(this);
            this.lastForceStatTime = System.currentTimeMillis();
        }
    }
}
