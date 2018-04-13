package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import cn.v6.sixrooms.utils.DialogUtils;

public class DialogActivity extends BaseFragmentActivity {
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        new DialogUtils(this).createDiaglog(getIntent().getStringExtra("msg"), "提示", new x(this)).show();
    }

    public void onBackPressed() {
    }
}
