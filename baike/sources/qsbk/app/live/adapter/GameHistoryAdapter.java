package qsbk.app.live.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.utils.DateUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGameHistoryData;

public class GameHistoryAdapter extends Adapter<ViewHolder> {
    private Context a;
    private ArrayList<LiveGameHistoryData> b;
    private ArrayList<Integer> c;
    private long d;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ImageView game_role_1;
        public ImageView game_role_2;
        public ImageView game_role_3;
        public ImageView ivHistory;
        public LinearLayout llBg;
        private ImageView m;
        private ImageView n;
        private ImageView o;
        public ImageView tab_new;
        public TextView tvResult;
        public TextView tvTime;
        public TextView tvWin;

        public ViewHolder(View view) {
            super(view);
            this.game_role_1 = (ImageView) view.findViewById(R.id.game_role_1);
            this.game_role_2 = (ImageView) view.findViewById(R.id.game_role_2);
            this.game_role_3 = (ImageView) view.findViewById(R.id.game_role_3);
            this.m = (ImageView) view.findViewById(R.id.iv_me_1);
            this.n = (ImageView) view.findViewById(R.id.iv_me_2);
            this.o = (ImageView) view.findViewById(R.id.iv_me_3);
            this.tab_new = (ImageView) view.findViewById(R.id.tab_new);
            this.tvResult = (TextView) view.findViewById(R.id.tv_result);
            this.tvWin = (TextView) view.findViewById(R.id.tv_win);
            this.tvTime = (TextView) view.findViewById(R.id.tv_time);
            this.llBg = (LinearLayout) view.findViewById(R.id.ll_history_bg);
            this.ivHistory = (ImageView) view.findViewById(R.id.iv_history);
        }
    }

    public GameHistoryAdapter(Context context, ArrayList<LiveGameHistoryData> arrayList, ArrayList<Integer> arrayList2, long j) {
        this.a = context;
        this.b = arrayList;
        this.d = j;
        this.c = arrayList2;
    }

    private boolean a() {
        return this.d == 1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        switch ((int) this.d) {
            case 1:
                i2 = R.layout.live_game_history_item;
                break;
            case 2:
                i2 = R.layout.live_game_history_item_ypdx;
                break;
            case 3:
                i2 = R.layout.live_game_history_item_catanddog;
                break;
            case 4:
                i2 = R.layout.live_game_history_item_fanfanle;
                break;
            case 5:
                i2 = R.layout.live_game_rolltable_history_item;
                break;
            default:
                i2 = 0;
                break;
        }
        return new ViewHolder(LayoutInflater.from(this.a).inflate(i2, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tab_new.setVisibility(i == 0 ? 0 : 8);
        LiveGameHistoryData liveGameHistoryData;
        if (a() && this.b != null) {
            viewHolder.game_role_1.setImageResource(R.drawable.live_game_history_lose);
            viewHolder.game_role_2.setImageResource(R.drawable.live_game_history_lose);
            viewHolder.game_role_3.setImageResource(R.drawable.live_game_history_lose);
            liveGameHistoryData = (LiveGameHistoryData) this.b.get(i);
            if (liveGameHistoryData != null) {
                a(viewHolder, liveGameHistoryData);
            }
            if (liveGameHistoryData.h != null) {
                viewHolder.m.setVisibility(8);
                viewHolder.n.setVisibility(8);
                viewHolder.o.setVisibility(8);
                if (((Integer) liveGameHistoryData.h.get(0)).intValue() == 1) {
                    viewHolder.m.setVisibility(0);
                }
                if (((Integer) liveGameHistoryData.h.get(1)).intValue() == 1) {
                    viewHolder.n.setVisibility(0);
                }
                if (((Integer) liveGameHistoryData.h.get(2)).intValue() == 1) {
                    viewHolder.o.setVisibility(0);
                }
            }
        } else if (this.d == 2 && this.b != null) {
            viewHolder.game_role_1.setImageResource(R.drawable.live_game_history_lose);
            viewHolder.game_role_2.setImageResource(R.drawable.live_game_history_lose);
            viewHolder.game_role_3.setImageResource(R.drawable.live_game_history_lose);
            liveGameHistoryData = (LiveGameHistoryData) this.b.get(i);
            if (liveGameHistoryData != null) {
                a(viewHolder, liveGameHistoryData);
                if (liveGameHistoryData.isShunZi()) {
                    viewHolder.game_role_2.setImageResource(R.drawable.live_game_history_win);
                }
            }
            if (liveGameHistoryData.h != null) {
                if (((Integer) liveGameHistoryData.h.get(0)).intValue() == 1) {
                    viewHolder.m.setVisibility(0);
                } else {
                    viewHolder.m.setVisibility(8);
                }
                if (((Integer) liveGameHistoryData.h.get(1)).intValue() == 1) {
                    viewHolder.n.setVisibility(0);
                } else {
                    viewHolder.n.setVisibility(8);
                }
                if (((Integer) liveGameHistoryData.h.get(2)).intValue() == 1) {
                    viewHolder.o.setVisibility(0);
                } else {
                    viewHolder.o.setVisibility(8);
                }
            }
        } else if (this.c != null && this.c.size() > 0 && this.c.get(i) != null && this.d == 5) {
            int intValue = ((Integer) this.c.get(i)).intValue();
            if (intValue == -1 || intValue == -2) {
                viewHolder.ivHistory.setVisibility(0);
                if (intValue == -1) {
                    viewHolder.ivHistory.setImageResource(R.drawable.rolltabel_yuanbao);
                    return;
                } else {
                    viewHolder.ivHistory.setImageResource(R.drawable.rolltable_diamond);
                    return;
                }
            }
            viewHolder.tvResult.setVisibility(0);
            viewHolder.ivHistory.setVisibility(8);
            viewHolder.tvResult.setText(this.c.get(i) + "");
        } else if (this.d == 4) {
            a(viewHolder, i);
        } else if (this.b != null) {
            a(viewHolder, (LiveGameHistoryData) this.b.get(i), i);
        }
    }

    private void a(ViewHolder viewHolder, int i) {
        viewHolder.tab_new.setVisibility(8);
        if (i >= 0 && i < this.b.size()) {
            LiveGameHistoryData liveGameHistoryData = (LiveGameHistoryData) this.b.get(i);
            if (liveGameHistoryData != null) {
                viewHolder.tvWin.setText(liveGameHistoryData.IsWin == 1 ? "获胜" : "失败");
                viewHolder.tvWin.setTextColor(liveGameHistoryData.IsWin == 1 ? Color.parseColor("#FD625F") : Color.parseColor("#929292"));
                viewHolder.tvResult.setText(liveGameHistoryData.VotePlayers);
                viewHolder.tvTime.setText(DateUtil.getTimeOrYestody(liveGameHistoryData.CreateAt));
            }
            viewHolder.llBg.setBackgroundDrawable(i % 2 == 0 ? this.a.getResources().getDrawable(R.drawable.game_fanfanle_history_item) : null);
        }
    }

    private void a(ViewHolder viewHolder, LiveGameHistoryData liveGameHistoryData, int i) {
        int i2 = liveGameHistoryData.w;
        if (i % 2 == 1) {
            viewHolder.game_role_1.setBackgroundDrawable(null);
            viewHolder.game_role_2.setBackgroundDrawable(null);
            viewHolder.game_role_3.setBackgroundDrawable(null);
        } else {
            viewHolder.game_role_1.setBackgroundColor(Color.parseColor("#F9DFB7"));
            viewHolder.game_role_2.setBackgroundColor(Color.parseColor("#F9DFB7"));
            viewHolder.game_role_3.setBackgroundColor(Color.parseColor("#F9DFB7"));
        }
        viewHolder.game_role_1.setImageDrawable(null);
        viewHolder.game_role_2.setImageDrawable(null);
        viewHolder.game_role_3.setImageDrawable(null);
        switch (i2) {
            case 1:
                viewHolder.game_role_1.setImageResource(R.drawable.live_game_catanddog_win);
                break;
            case 2:
                viewHolder.game_role_2.setImageResource(R.drawable.live_game_catanddog_draw);
                break;
            case 3:
                viewHolder.game_role_3.setImageResource(R.drawable.live_game_catanddog_win);
                break;
        }
        if (liveGameHistoryData.h != null) {
            if (((Integer) liveGameHistoryData.h.get(0)).intValue() == 1) {
                viewHolder.m.setVisibility(0);
            } else {
                viewHolder.m.setVisibility(8);
            }
            if (((Integer) liveGameHistoryData.h.get(1)).intValue() == 1) {
                viewHolder.n.setVisibility(0);
            } else {
                viewHolder.n.setVisibility(8);
            }
            if (((Integer) liveGameHistoryData.h.get(2)).intValue() == 1) {
                viewHolder.o.setVisibility(0);
            } else {
                viewHolder.o.setVisibility(8);
            }
        }
    }

    private void a(ViewHolder viewHolder, LiveGameHistoryData liveGameHistoryData) {
        switch (liveGameHistoryData.w) {
            case 1:
                viewHolder.game_role_1.setImageResource(R.drawable.live_game_history_win);
                return;
            case 2:
                viewHolder.game_role_2.setImageResource(R.drawable.live_game_history_win);
                return;
            case 3:
                viewHolder.game_role_3.setImageResource(R.drawable.live_game_history_win);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        if (this.d == 5) {
            if (this.c != null) {
                return this.c.size();
            }
            return 0;
        } else if (this.b != null) {
            return this.b.size();
        } else {
            return 0;
        }
    }
}
