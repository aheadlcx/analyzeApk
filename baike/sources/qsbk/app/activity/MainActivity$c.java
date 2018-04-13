package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import qsbk.app.R;

class MainActivity$c implements Runnable {
    final /* synthetic */ MainActivity a;

    MainActivity$c(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        new Builder(this.a).setMessage(R.string.push_config_dialog_message).setPositiveButton("立即打开", new ta(this)).setNegativeButton(R.string.cancel, new sz(this)).create().show();
        this.a.getSharedPreferences("qiushibaike", 0).edit().putLong(MainActivity.PUSH_CONFIG_CHECK_TIME, System.currentTimeMillis()).apply();
    }
}
