package cn.v6.sdk.sixrooms.coop;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.utils.DialogUtils;

public class CoopLoadingActivity extends Activity {
    private Dialog dialog;
    private DismissReceiver receiver;

    public class DismissReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Log.d(V6Coop.TAG, "接受到取消loading广播");
            CoopLoadingActivity.this.dissMissDialog();
            CoopLoadingActivity.this.finish();
            CoopLoadingActivity.this.overridePendingTransition(0, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(V6Coop.TAG, "onCreate");
        setContentView(R.layout.activity_coop_loading);
        this.dialog = new DialogUtils(this).createLoadingDialog();
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();
        this.receiver = new DismissReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.receiver, new IntentFilter(CustomBroadcast.COOPLOGIN_LOADING));
        Log.d(V6Coop.TAG, "regisiterFinish");
        this.dialog.setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                CoopLoadingActivity.this.finish();
            }
        });
    }

    public void dissMissDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(V6Coop.TAG, "onNewIntent");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(V6Coop.TAG, "onDestroy");
        if (this.receiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.receiver);
            this.receiver = null;
        }
    }

    public void finish() {
        super.finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            finish();
        }
        return true;
    }
}
