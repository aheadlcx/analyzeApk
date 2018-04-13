package qsbk.app.activity;

import android.support.v4.app.FragmentActivity;
import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;

public class StatFragmentActivity extends FragmentActivity {
    private boolean a = false;

    public boolean needActivityStat() {
        return true;
    }

    protected void onPause() {
        if (needActivityStat()) {
            FlurryAgent.onEndSession(this);
            StatService.onPause(this);
        }
        super.onPause();
    }

    protected void c() {
    }

    protected void onResume() {
        if (!this.a) {
            this.a = true;
            c();
        }
        if (needActivityStat()) {
            FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
            StatService.onResume(this);
        }
        super.onResume();
    }
}
