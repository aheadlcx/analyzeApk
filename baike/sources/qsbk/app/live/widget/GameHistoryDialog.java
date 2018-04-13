package qsbk.app.live.widget;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.ImageView;
import java.util.ArrayList;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.GameHistoryAdapter;
import qsbk.app.live.model.LiveGameHistoryData;
import qsbk.app.live.ui.LiveBaseActivity;

public class GameHistoryDialog extends BaseDialog {
    private RecyclerView c;
    private LinearLayoutManager d;
    private GridLayoutManager e;
    private Adapter f;
    private EmptyPlaceholderView g;
    private LiveBaseActivity h;
    private ArrayList<Integer> i = new ArrayList();
    private ArrayList<LiveGameHistoryData> j = new ArrayList();
    private ArrayList<Integer> k = new ArrayList();
    private long l;
    private long m;
    private ImageView n;

    public GameHistoryDialog(LiveBaseActivity liveBaseActivity, long j, long j2) {
        super(liveBaseActivity);
        this.h = liveBaseActivity;
        this.m = j;
        this.l = j2;
    }

    private boolean j() {
        return this.m == 1;
    }

    private boolean k() {
        return this.m == 5;
    }

    protected int c() {
        switch ((int) this.m) {
            case 1:
                return R.layout.activity_game_history_dialog;
            case 2:
                return R.layout.activity_game_history_dialog_ypdx;
            case 3:
                return R.layout.activity_game_catanddog_history_dialog;
            case 4:
                return R.layout.activity_game_fanfanle_history_dialog;
            case 5:
                return R.layout.activity_game_rolltable_history_dialog;
            default:
                return 0;
        }
    }

    protected void d() {
        i();
        setCanceledOnTouchOutside(false);
        this.c = (RecyclerView) a(R.id.recyclerview);
        this.g = (EmptyPlaceholderView) a(R.id.game_history_empty);
        this.n = (ImageView) a(R.id.iv_close);
    }

    protected void e() {
        if (this.m == 5) {
            this.e = new GridLayoutManager(getContext(), 4);
            this.c.setLayoutManager(this.e);
        } else {
            this.d = new LinearLayoutManager(getContext());
            this.c.setLayoutManager(this.d);
        }
        this.f = new GameHistoryAdapter(getContext(), this.j, this.k, this.m);
        this.c.setAdapter(this.f);
        this.c.setItemAnimator(new DefaultItemAnimator());
        this.c.setHasFixedSize(true);
        this.g.showProgressBar();
        l();
        if (this.n != null) {
            this.n.setOnClickListener(new by(this));
        }
    }

    private void l() {
        String str = null;
        switch ((int) this.m) {
            case 1:
                str = UrlConstants.LIVE_GAME_HISTORY_HLNB;
                break;
            case 2:
                str = UrlConstants.LIVE_GAME_HISTORY_YPDX;
                break;
            case 3:
                str = UrlConstants.LIVE_GAME_HISTORY_YPDX;
                break;
            case 4:
                str = UrlConstants.GAME_FANFANLE_HISTORY;
                break;
            case 5:
                str = UrlConstants.GAME_ROLLTABLE_HISTORY;
                break;
        }
        if (str != null) {
            NetRequest.getInstance().get(str, new bz(this));
        }
    }

    protected int a() {
        return 48;
    }
}
