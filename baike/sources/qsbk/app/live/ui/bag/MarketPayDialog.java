package qsbk.app.live.ui.bag;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMarketPayRecord;

public class MarketPayDialog extends BaseDialog {
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private ImageView l;
    private TextView m;
    private SwipeRefreshLayout n;
    private long o;
    private List<LiveMarketPayRecord> p;
    private int[] q;
    private int r;
    private FragmentActivity s;

    public void setParent(FragmentActivity fragmentActivity) {
        this.s = fragmentActivity;
    }

    public MarketPayDialog(Context context, long j) {
        super(context);
        this.o = j;
    }

    protected int c() {
        return R.layout.dialog_enteranim_pay;
    }

    protected void d() {
        this.c = (TextView) findViewById(R.id.tv_item1);
        this.d = (TextView) findViewById(R.id.tv_item2);
        this.e = (TextView) findViewById(R.id.tv_item3);
        this.f = (TextView) findViewById(R.id.tv_off_item1);
        this.g = (TextView) findViewById(R.id.tv_off_item2);
        this.h = (TextView) findViewById(R.id.tv_off_item3);
        this.i = (TextView) findViewById(R.id.tv_origin);
        this.i.getPaint().setFlags(16);
        this.j = (TextView) findViewById(R.id.tv_current);
        this.k = (TextView) findViewById(R.id.tv_balance);
        this.l = (ImageView) findViewById(R.id.iv_pay);
        this.m = (TextView) findViewById(R.id.tv_title);
        this.n = (SwipeRefreshLayout) findViewById(R.id.swipe);
        this.n.setOnRefreshListener(new s(this));
        this.c.setOnClickListener(new t(this));
        this.d.setOnClickListener(new u(this));
        this.e.setOnClickListener(new v(this));
        this.k.setText("当前余额 " + AppUtils.getInstance().getUserInfoProvider().getBalance() + "钻石");
        this.l.setOnClickListener(new w(this));
    }

    private void j() {
        NetRequest.getInstance().get(UrlConstants.LIVE_MARKET_BUY, new z(this));
    }

    private void b(int i) {
        this.c.setSelected(false);
        this.d.setSelected(false);
        this.e.setSelected(false);
        switch (i) {
            case 0:
                this.c.setSelected(true);
                break;
            case 1:
                this.d.setSelected(true);
                break;
            case 2:
                this.e.setSelected(true);
                break;
        }
        if (this.p != null && i < this.p.size()) {
            this.i.setText(((LiveMarketPayRecord) this.p.get(i)).p + "钻石");
            this.j.setText(((LiveMarketPayRecord) this.p.get(i)).n + "钻石");
            if (((LiveMarketPayRecord) this.p.get(i)).p == ((LiveMarketPayRecord) this.p.get(i)).n) {
                this.i.setVisibility(4);
            } else {
                this.i.setVisibility(0);
            }
        }
        this.r = i;
    }

    protected void e() {
        this.n.setRefreshing(true);
        k();
    }

    private void k() {
        NetRequest.getInstance().get(UrlConstants.LIVE_MARKET_PRICE, new aa(this));
    }

    protected int a() {
        return 17;
    }
}
