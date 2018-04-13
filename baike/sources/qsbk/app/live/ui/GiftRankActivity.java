package qsbk.app.live.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.model.User;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.ui.fragment.RankPagerFragment;

public class GiftRankActivity extends BaseActivity {
    private String a = GiftRankActivity.class.getSimpleName();
    private User b;
    private long c;
    private boolean d;
    public ImageView iv_avatar;
    public ImageView iv_up;
    public TextView tv_name;
    public TextView tv_total;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected int getLayoutId() {
        return R.layout.live_giftrank_activity;
    }

    protected void initView() {
        AppUtils.addSupportForTransparentStatusBar(findViewById(R.id.dynamic_adjust_position_contain));
        this.tv_total = (TextView) findViewById(R.id.tv_total);
        this.tv_name = (TextView) findViewById(R.id.tv_name);
        this.iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
        this.iv_up = (ImageView) findViewById(R.id.iv_up);
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.b = (User) intent.getSerializableExtra("user");
            this.d = intent.getBooleanExtra("is_anchor", false);
            this.c = intent.getLongExtra("total", 0);
        }
        if (this.b == null) {
            finish();
            return;
        }
        this.tv_total.setText(this.c + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getString(R.string.withdraw_certificate));
        this.tv_total.getPaint().setFakeBoldText(true);
        this.tv_name.setText(this.b.name);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.iv_avatar, this.b.headurl, true);
        this.iv_up.setOnClickListener(new a(this));
        getSupportFragmentManager().beginTransaction().add(R.id.container, RankPagerFragment.newInstance(this.b, this.d)).commitAllowingStateLoss();
    }

    public void setTotalGift(long j) {
        this.tv_total.setText(j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getString(R.string.withdraw_certificate));
    }

    protected boolean isNeedSystemBarTintEnable() {
        return false;
    }

    protected boolean isNeedImmersiveStatusBar() {
        return true;
    }
}
