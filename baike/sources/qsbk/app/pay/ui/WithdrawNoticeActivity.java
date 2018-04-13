package qsbk.app.pay.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.webkit.WebView;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;

public class WithdrawNoticeActivity extends BaseActivity {
    private WebView a;
    private SwipeRefreshLayout b;
    private String c;
    private boolean d = false;

    protected int getLayoutId() {
        return R.layout.pay_withdraw_notice_activity;
    }

    protected void initView() {
        this.a = (WebView) $(R.id.wv_notice);
        this.b = (SwipeRefreshLayout) $(R.id.refresher);
        setTitle(getString(R.string.pay_withdraw_system_notice));
        setUp();
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initData() {
        try {
            this.b.post(new af(this));
            Intent intent = getIntent();
            if (intent != null) {
                this.c = intent.getStringExtra("url");
            }
        } catch (Exception e) {
            ToastUtil.Long("访问网页出错");
        }
        this.b.setOnRefreshListener(new ag(this));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.a.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.a.goBack();
        return true;
    }

    private void a(String str) {
        if (str != null) {
            this.b.setRefreshing(true);
            this.a.getSettings().setJavaScriptEnabled(true);
            this.a.loadUrl(str);
            this.a.setWebViewClient(new ah(this));
        }
    }
}
