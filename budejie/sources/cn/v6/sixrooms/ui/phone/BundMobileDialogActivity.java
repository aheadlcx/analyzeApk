package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.os.Bundle;

public class BundMobileDialogActivity extends BaseFragmentActivity {
    public static final int IS_SUCCEED = -1001;

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        showNotBoundMobileDialog(getIntent().getStringExtra("msg"), this);
    }

    public void onBackPressed() {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && intent != null) {
            setResult(IS_SUCCEED, intent);
        }
        if (intent == null) {
            Intent intent2 = new Intent();
            intent2.putExtra("issucceed", false);
            setResult(IS_SUCCEED, intent2);
        }
        finish();
    }
}
