package qsbk.app.live.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.R;
import qsbk.app.live.adapter.LiveMessageAdapter.OnItemClickLitener;
import qsbk.app.live.model.GiftRankRichData;

public class GiftRankRichAdapter extends Adapter<ViewHolder> {
    private List<GiftRankRichData> a;
    private Context b;
    private OnItemClickLitener c;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public View iv_avatar_bg;
        public ImageView sd_avtar;
        public TextView tv_rich_amount;

        public ViewHolder(View view) {
            super(view);
            this.sd_avtar = (ImageView) view.findViewById(R.id.iv_avatar);
            this.tv_rich_amount = (TextView) view.findViewById(R.id.tv_rich_amount);
            this.iv_avatar_bg = view.findViewById(R.id.avatar_bg);
        }
    }

    public GiftRankRichAdapter(Context context, List<GiftRankRichData> list) {
        this.b = context;
        this.a = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.live_giftrankrich_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder != null && i >= 0 && i < this.a.size()) {
            GiftRankRichData giftRankRichData = (GiftRankRichData) this.a.get(i);
            AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder.sd_avtar, giftRankRichData.avatar, true);
            viewHolder.sd_avtar.setOnClickListener(new p(this, i));
            if (giftRankRichData.cp <= 0) {
                viewHolder.tv_rich_amount.setVisibility(8);
                viewHolder.iv_avatar_bg.setBackgroundResource(0);
                return;
            }
            viewHolder.tv_rich_amount.setVisibility(0);
            viewHolder.tv_rich_amount.setText(FormatUtils.formatCoupon(giftRankRichData.cp));
            if (i == 0) {
                viewHolder.tv_rich_amount.setTextColor(Color.parseColor("#6A3906"));
                viewHolder.iv_avatar_bg.setBackgroundResource(R.drawable.live_rich_1);
            } else if (i == 1) {
                viewHolder.tv_rich_amount.setTextColor(Color.parseColor("#6D6D71"));
                viewHolder.iv_avatar_bg.setBackgroundResource(R.drawable.live_rich_2);
            } else if (i == 2) {
                viewHolder.tv_rich_amount.setTextColor(Color.parseColor("#74442E"));
                viewHolder.iv_avatar_bg.setBackgroundResource(R.drawable.live_rich_3);
            } else {
                viewHolder.tv_rich_amount.setTextColor(Color.parseColor("#CFCFD2"));
                viewHolder.iv_avatar_bg.setBackgroundResource(R.drawable.live_rich_other);
            }
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.c = onItemClickLitener;
    }
}
