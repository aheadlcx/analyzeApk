package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.BestBetResult;

public class BestBetAdapter extends Adapter<ViewHolder> {
    private List<BestBetResult> a;
    private Context b;
    private int c;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ImageView ivAvatar;
        public ImageView ivRank;
        public LinearLayout llBg;
        public TextView tvBetResult;
        public TextView tvName;
        public TextView tvRank;
        public TextView tvStep;

        public ViewHolder(View view) {
            super(view);
            this.ivRank = (ImageView) view.findViewById(R.id.iv_rank);
            this.tvRank = (TextView) view.findViewById(R.id.tv_rank);
            this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvBetResult = (TextView) view.findViewById(R.id.tv_bet_result);
            this.tvStep = (TextView) view.findViewById(R.id.tv_step);
            this.llBg = (LinearLayout) view.findViewById(R.id.ll_bg);
        }
    }

    public BestBetAdapter(Context context, List<BestBetResult> list, int i) {
        this.b = context;
        this.a = list;
        this.c = i;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.c == 4) {
            return new ViewHolder(LayoutInflater.from(AppUtils.getInstance().getAppContext()).inflate(R.layout.live_bestbet_item_fanfanle, viewGroup, false));
        }
        return new ViewHolder(LayoutInflater.from(AppUtils.getInstance().getAppContext()).inflate(R.layout.live_bestbet_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        BestBetResult bestBetResult = (BestBetResult) this.a.get(i);
        AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder.ivAvatar, bestBetResult.getAvatar());
        viewHolder.tvName.setText(bestBetResult.getName());
        viewHolder.tvBetResult.setText(FormatUtils.formatCoupon(bestBetResult.getWinNum()));
        if (this.c == 4) {
            viewHolder.llBg.setBackgroundDrawable(i % 2 == 0 ? this.b.getResources().getDrawable(R.drawable.game_fanfanle_history_item) : null);
            viewHolder.tvStep.setText("闯至第" + bestBetResult.p + "关");
            return;
        }
        switch (bestBetResult.getRank()) {
            case 1:
                viewHolder.tvRank.setVisibility(4);
                viewHolder.ivRank.setVisibility(0);
                viewHolder.ivRank.setImageResource(R.drawable.live_best_bet_1);
                return;
            case 2:
                viewHolder.tvRank.setVisibility(4);
                viewHolder.ivRank.setVisibility(0);
                viewHolder.ivRank.setImageResource(R.drawable.live_best_bet_2);
                return;
            case 3:
                viewHolder.tvRank.setVisibility(4);
                viewHolder.ivRank.setVisibility(0);
                viewHolder.ivRank.setImageResource(R.drawable.live_best_bet_3);
                return;
            default:
                viewHolder.ivRank.setVisibility(4);
                viewHolder.tvRank.setVisibility(0);
                viewHolder.tvRank.setText(String.valueOf(bestBetResult.getRank()));
                return;
        }
    }

    public int getItemCount() {
        return this.a != null ? this.a.size() : 0;
    }
}
