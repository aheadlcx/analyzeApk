package qsbk.app.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.R;

@Deprecated
public class GuideActivity extends StatFragmentActivity {
    public static final String KEY_DISMISS_DELAY_IN_SECONDS = "dismiss_delay";
    private static final String a = GuideActivity.class.getSimpleName();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (a()) {
            setContentView(R.layout.activity_guide);
            OnClickListener oeVar = new oe(this);
            findViewById(R.id.close).setOnClickListener(oeVar);
            findViewById(R.id.known).setOnClickListener(oeVar);
            return;
        }
        finish();
    }

    protected void onResume() {
        super.onResume();
        int intExtra = getIntent().getIntExtra(KEY_DISMISS_DELAY_IN_SECONDS, -1);
        if (intExtra <= 0) {
            intExtra = -1;
        }
        if (intExtra > 0) {
            new Handler().postDelayed(new of(this), (long) (intExtra * 1000));
        }
    }

    private boolean a() {
        boolean z = false;
        boolean equalsIgnoreCase = "1".equalsIgnoreCase(ConfigManager.getInstance().getConfig("showGuide"));
        SharedPreferences sharedPreferences = getSharedPreferences(a, 0);
        String string = sharedPreferences.getString("shown", "");
        int i = sharedPreferences.getInt("version", 0);
        if (equalsIgnoreCase && (!"shown".equalsIgnoreCase(string) || i < Constants.localVersion)) {
            z = true;
        }
        if (z) {
            Editor edit = sharedPreferences.edit();
            edit.putInt("version", Constants.localVersion);
            edit.putString("shown", "shown");
            edit.apply();
        }
        return z;
    }
}
