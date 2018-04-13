package qsbk.app.core.ui.base;

import android.os.Bundle;
import qsbk.app.core.R;

public abstract class BaseBlurActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
