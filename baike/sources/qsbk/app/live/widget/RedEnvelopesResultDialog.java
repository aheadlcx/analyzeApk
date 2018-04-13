package qsbk.app.live.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.RefreshRecyclerView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.RedEnvelopesRecordAdapter;
import qsbk.app.live.model.LiveRobRedEnvelopesResultMessage;

public class RedEnvelopesResultDialog extends BaseDialog {
    private View c;
    private TextView d;
    private View e;
    private View f;
    private TextView g;
    private View h;
    private View i;
    private View j;
    private TextView k;
    private RefreshRecyclerView l;
    private LiveRobRedEnvelopesResultMessage m;

    public RedEnvelopesResultDialog(Context context, LiveRobRedEnvelopesResultMessage liveRobRedEnvelopesResultMessage) {
        super(context);
        this.m = liveRobRedEnvelopesResultMessage;
    }

    protected int c() {
        return R.layout.live_red_envelopes_result_dialog;
    }

    protected void d() {
        this.c = a(R.id.iv_close);
        this.d = (TextView) a(R.id.tv_title);
        this.e = a(R.id.iv_up);
        this.f = a(R.id.ll_content);
        this.h = a(R.id.btn_view_others);
        this.g = (TextView) a(R.id.tv_diamond);
        this.i = a(R.id.ll_failed);
        this.j = a(R.id.ll_record);
        this.k = (TextView) a(R.id.tv_size);
        this.l = (RefreshRecyclerView) a(R.id.recyclerview);
    }

    protected void e() {
        getWindow().setWindowAnimations(R.style.SimpleDialog_RedEnvelopes);
        this.c.setOnClickListener(new ib(this));
        long coin = this.m.getCoin();
        if (coin > 0) {
            this.f.setVisibility(0);
            this.i.setVisibility(8);
            this.g.setText(String.valueOf(coin));
            this.h.setOnClickListener(new ic(this));
        } else {
            this.f.setVisibility(8);
            this.i.setVisibility(0);
        }
        this.e.setOnClickListener(new id(this));
    }

    private void j() {
        if (this.l.getAdapter() == null) {
            BaseRecyclerViewAdapter redEnvelopesRecordAdapter = new RedEnvelopesRecordAdapter(getContext(), new ArrayList());
            Map hashMap = new HashMap();
            hashMap.put("id", String.valueOf(this.m.getRedEnvelopesId()));
            hashMap.put("count", "20");
            this.l.init(redEnvelopesRecordAdapter, UrlConstants.LIVE_RED_ENVELOPES_RECORD, hashMap, "rrec", new ie(this));
            this.l.setRefreshListener(new if(this));
        } else if (this.l.getAdapter().getItemCount() == 0) {
            this.l.forceRefresh();
        }
    }

    protected float b() {
        return 0.0f;
    }

    protected int a() {
        return 17;
    }

    protected float f() {
        return 0.6f;
    }
}
