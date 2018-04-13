package qsbk.app.live.ui;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.live.R;
import qsbk.app.pay.ui.PayActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;

public class LevelGiftPayActivity extends PayActivity implements OnClickListener {
    private long m;
    private ImageView n;
    private ImageView o;

    protected int getLayoutId() {
        return R.layout.level_gift_pay_activity;
    }

    protected void initView() {
        this.n = (ImageView) $(R.id.level_gift_pay_ali);
        this.o = (ImageView) $(R.id.level_gift_pay_wechat);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.m = intent.getLongExtra("boxid", 0);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.level_gift_pay_ali) {
            c("ali");
        } else if (view.getId() == R.id.level_gift_pay_wechat) {
            c(ThirdPartyConstants.THIRDPARTY_TYLE_WX);
        }
    }

    private void c(String str) {
        NetRequest.getInstance().get(UrlConstants.LIVE_LEVEL_GIFT_PAY, new b(this, str));
    }

    protected void a(Message message) {
        e();
        finish();
    }

    protected void a(String str) {
        e();
        finish();
    }

    private void e() {
        NetRequest.getInstance().get(UrlConstants.LIVE_LEVEL_GIFT_PAY_NOTIFY, new c(this));
    }
}
