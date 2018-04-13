package qsbk.app.live.widget;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.GameMVPAdapter;
import qsbk.app.live.model.LiveGameMVPData;
import qsbk.app.live.ui.LiveBaseActivity;

public class GameMVPDialog extends BaseDialog {
    private RecyclerView c;
    private LinearLayoutManager d;
    private Adapter e;
    private EmptyPlaceholderView f;
    private LiveBaseActivity g;
    private List<LiveGameMVPData> h = new ArrayList();
    private long i;
    private ImageView j;

    public GameMVPDialog(LiveBaseActivity liveBaseActivity, long j) {
        super(liveBaseActivity);
        this.g = liveBaseActivity;
        this.i = j;
    }

    protected int c() {
        switch ((int) this.i) {
            case 2:
                return R.layout.activity_game_mvp_ypdx_dialog;
            case 3:
                return R.layout.activity_game_mvp_catanddog_dialog;
            case 4:
                return R.layout.activity_game_mvp_fanfanle_dialog;
            case 5:
                return R.layout.activity_game_mvp_rolltable_dialog;
            default:
                return 0;
        }
    }

    protected void d() {
        i();
        setCanceledOnTouchOutside(false);
        this.c = (RecyclerView) a(R.id.recyclerview);
        this.f = (EmptyPlaceholderView) a(R.id.game_history_empty);
        this.j = (ImageView) a(R.id.iv_close);
    }

    protected void e() {
        this.d = new LinearLayoutManager(getContext());
        this.c.setLayoutManager(this.d);
        this.e = new GameMVPAdapter(getContext(), this.h, this.i);
        this.c.setAdapter(this.e);
        this.c.setItemAnimator(new DefaultItemAnimator());
        this.c.setHasFixedSize(true);
        this.f.showProgressBar();
        j();
        if (this.j != null) {
            this.j.setOnClickListener(new cd(this));
        }
    }

    private void j() {
        NetRequest.getInstance().get(UrlConstants.LIVE_GAME_MVP, new ce(this));
    }

    protected int a() {
        return 48;
    }
}
