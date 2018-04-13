package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class NoAvailableSpaceActivity extends Activity {
    public static final String ACTION_NO_AVAILABLE_SPACE = "qsbk.intent.action.NO_AVAILABLE_SPACE";
    public static final String ACTION_NO_AVAILABLE_SPACE_EXIT = "qsbk.intent.action.NO_AVAILABLE_SPACE_EXIT";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.e(NoAvailableSpaceActivity.class.getSimpleName(), getIntent().getAction());
        if (!ACTION_NO_AVAILABLE_SPACE.equals(getIntent().getAction())) {
            finish();
        }
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppTheme.share.new.Dark);
        }
        a();
    }

    private void a() {
        new Builder(this).setTitle(R.string.no_available_space_title).setMessage(R.string.no_available_space_content).setCancelable(false).setPositiveButton(getString(R.string.no_available_space_exit_im), new yh(this)).show();
    }

    private void b() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_NO_AVAILABLE_SPACE_EXIT));
    }
}
