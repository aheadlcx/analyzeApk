package qsbk.app.live.debug;

import android.widget.Button;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.live.R;
import qsbk.app.live.widget.LargeGiftLayout;

public class LiveGiftAnimDebugActivity extends BaseActivity {
    private Button a;
    private LargeGiftLayout b;

    protected int getLayoutId() {
        return R.layout.live_gift_anim_debug_activity;
    }

    protected void initView() {
        this.a = (Button) findViewById(R.id.btn_send);
        this.b = (LargeGiftLayout) findViewById(R.id.fl_large_gift_anim);
    }

    protected void initData() {
        this.a.setOnClickListener(new j(this));
    }
}
