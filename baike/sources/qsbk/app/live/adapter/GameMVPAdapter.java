package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGameMVPData;

public class GameMVPAdapter extends Adapter<ViewHolder> {
    private Context a;
    private List<LiveGameMVPData> b;
    private long c;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView tvLove;
        public TextView tvName;
        public TextView tvRank;

        public ViewHolder(View view) {
            super(view);
            this.tvRank = (TextView) view.findViewById(R.id.tv_rank);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvLove = (TextView) view.findViewById(R.id.tv_love);
        }
    }

    public GameMVPAdapter(Context context, List<LiveGameMVPData> list, long j) {
        this.a = context;
        this.b = list;
        this.c = j;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        switch ((int) this.c) {
            case 2:
                i2 = R.layout.live_game_mvp_item_ypdx;
                break;
            case 3:
                i2 = R.layout.live_game_mvp_item_catanddog;
                break;
            case 4:
                i2 = R.layout.live_game_mvp_item_fanfanle;
                break;
            case 5:
                i2 = R.layout.live_game_mvp_item_rolltable;
                break;
            default:
                i2 = 0;
                break;
        }
        return new ViewHolder(LayoutInflater.from(this.a).inflate(i2, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        LiveGameMVPData liveGameMVPData = (LiveGameMVPData) this.b.get(i);
        if (liveGameMVPData != null) {
            viewHolder.tvRank.setText(liveGameMVPData.r + "");
            viewHolder.tvName.setText(liveGameMVPData.n);
            viewHolder.tvLove.setText(FormatUtils.formatCoupon(liveGameMVPData.p));
        }
    }

    public int getItemCount() {
        return this.b != null ? this.b.size() : 0;
    }
}
