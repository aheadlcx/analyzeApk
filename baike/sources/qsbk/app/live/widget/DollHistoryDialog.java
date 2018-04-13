package qsbk.app.live.widget;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.DollHistoryAdapter;
import qsbk.app.live.model.LiveDollHistoryData;
import qsbk.app.live.ui.LiveBaseActivity;

public class DollHistoryDialog extends BaseDialog {
    private LiveBaseActivity c;
    private RecyclerView d;
    private LinearLayoutManager e;
    private Adapter f;
    private EmptyPlaceholderView g;
    private ArrayList<LiveDollHistoryData> h = new ArrayList();

    public DollHistoryDialog(LiveBaseActivity liveBaseActivity) {
        super(liveBaseActivity);
        this.c = liveBaseActivity;
    }

    protected int c() {
        return R.layout.live_doll_history_dialog;
    }

    protected void d() {
        i();
        this.d = (RecyclerView) a(R.id.recyclerview);
        this.g = (EmptyPlaceholderView) a(R.id.doll_history_empty);
        findViewById(R.id.iv_close).setOnClickListener(new r(this));
    }

    protected void e() {
        this.e = new LinearLayoutManager(getContext());
        this.d.setLayoutManager(this.e);
        this.f = new DollHistoryAdapter(this.c, this.h);
        this.d.setAdapter(this.f);
        this.d.setItemAnimator(new DefaultItemAnimator());
        this.d.setHasFixedSize(true);
        this.g.showProgressBar();
        j();
    }

    private void j() {
        NetRequest.getInstance().get(UrlConstants.LIVE_DOLL_HISTORY, new s(this));
    }

    protected int a() {
        return 48;
    }
}
