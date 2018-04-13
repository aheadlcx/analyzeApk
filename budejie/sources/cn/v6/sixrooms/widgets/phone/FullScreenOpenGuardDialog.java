package cn.v6.sixrooms.widgets.phone;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine;
import cn.v6.sixrooms.engine.PurchaseGuardEngine;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import java.util.ArrayList;
import java.util.List;

public class FullScreenOpenGuardDialog extends Dialog implements OnClickListener {
    private RelativeLayout a = ((RelativeLayout) findViewById(R.id.rl_gold_guard));
    private TextView b = ((TextView) findViewById(R.id.gold_price));
    private TextView c = ((TextView) findViewById(R.id.gold_time));
    private RelativeLayout d = ((RelativeLayout) findViewById(R.id.rl_silver_guard));
    private TextView e = ((TextView) findViewById(R.id.silver_price));
    private TextView f = ((TextView) findViewById(R.id.silver_time));
    private RoominfoBean g;
    private List<GuardPropBean> h = new ArrayList();
    private GuardPropDetailBean i;
    private GuardPropDetailBean j;
    private BaseRoomActivity k;
    private PurchaseGuardEngine l;
    private Dialog m;
    private DialogUtils n;
    private OnPurchaseGuardListener o;
    private View p = findViewById(R.id.rl_content);
    private RelativeLayout q = ((RelativeLayout) findViewById(R.id.progressBar));
    private RelativeLayout r = ((RelativeLayout) findViewById(R.id.rl_root));
    private ImageView s = ((ImageView) findViewById(R.id.iv_close));

    public interface OnPurchaseGuardListener {
        void onPurchaseSuccess();
    }

    public FullScreenOpenGuardDialog(BaseRoomActivity baseRoomActivity, RoominfoBean roominfoBean, OnPurchaseGuardListener onPurchaseGuardListener) {
        super(baseRoomActivity, R.style.ImprovedDialog);
        this.k = baseRoomActivity;
        this.g = roominfoBean;
        this.o = onPurchaseGuardListener;
        this.n = new DialogUtils(baseRoomActivity);
        a((Dialog) this);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_fullscreen_open_guard);
        new CommodityInfoEngine(new n(this)).getProps(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), LoginUtils.getLoginUserBean().getId(), LoginUtils.getLoginUserBean().getRid());
        this.l = new PurchaseGuardEngine(new o(this));
        this.a.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
    }

    private void a(Dialog dialog) {
        if (getContext().getResources().getConfiguration().orientation == 2) {
            dialog.getWindow().addFlags(1024);
        } else {
            dialog.getWindow().addFlags(2048);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_gold_guard) {
            a(this.i, 0);
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
        } else if (id == R.id.rl_silver_guard) {
            a(this.j, 1);
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
        } else if (id == R.id.rl_root || id == R.id.iv_close) {
            dismiss();
        }
    }

    private void a(GuardPropDetailBean guardPropDetailBean, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("守护对象： ").append(this.g.getAlias()).append("(" + this.g.getRid() + ")").append("\n");
        stringBuilder.append("守护类型： ").append(i == 0 ? "黄金守护" : "白银守护").append("\n");
        stringBuilder.append("购买天数： ").append(guardPropDetailBean.getDays() + "天\n");
        stringBuilder.append("价格： ").append(guardPropDetailBean.getPrice() + "六币");
        if (this.m != null) {
            this.m.dismiss();
            this.m = null;
        }
        this.m = this.n.createConfirmDialog(0, "购买守护确认", stringBuilder.toString(), this.k.getString(R.string.cancel), this.k.getString(R.string.confirm), new p(this, i, guardPropDetailBean));
        a(this.m);
        this.m.show();
    }
}
