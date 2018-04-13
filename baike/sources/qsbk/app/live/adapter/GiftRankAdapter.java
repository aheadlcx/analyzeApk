package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GiftRankData;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.live.widget.FamilyLevelView;

public class GiftRankAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
    private int a = 0;
    private int b = 1;
    private Context c;
    private ArrayList<GiftRankData> d;
    private boolean e;
    private int f;
    private int g;

    public static class EmptyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public View divider;
        public FamilyLevelView fl_level;
        public FrameLayout fl_rank;
        public ImageView iv_rank;
        public ImageView iv_rank_head;
        public TextView label_me;
        public ImageView rank_change_icon;
        public TextView rank_change_num;
        public ImageView sd_avatar;
        public TextView tv_gift_num;
        public TextView tv_rank_num;
        public TextView tv_user_level;
        public TextView tv_username;

        public ViewHolder(View view) {
            super(view);
            this.divider = view.findViewById(R.id.divider);
            this.tv_rank_num = (TextView) view.findViewById(R.id.rank_num);
            this.sd_avatar = (ImageView) view.findViewById(R.id.avatar);
            this.tv_username = (TextView) view.findViewById(R.id.live_gift_username);
            this.tv_user_level = (TextView) view.findViewById(R.id.live_gift_user_lv);
            this.tv_gift_num = (TextView) view.findViewById(R.id.gift_num);
            this.rank_change_icon = (ImageView) view.findViewById(R.id.rank_change_icon);
            this.rank_change_num = (TextView) view.findViewById(R.id.rank_change_num);
            this.label_me = (TextView) view.findViewById(R.id.live_label_me);
            this.iv_rank = (ImageView) view.findViewById(R.id.iv_rank);
            this.fl_rank = (FrameLayout) view.findViewById(R.id.fl_rank);
            this.iv_rank_head = (ImageView) view.findViewById(R.id.iv_rank_head);
            this.fl_level = (FamilyLevelView) view.findViewById(R.id.fl_level);
        }
    }

    public GiftRankAdapter(Context context, ArrayList<GiftRankData> arrayList, boolean z, int i, int i2) {
        this.c = context;
        this.d = arrayList;
        this.e = z;
        this.f = i;
        this.g = i2;
    }

    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.a) {
            return new ViewHolder(LayoutInflater.from(this.c).inflate(R.layout.live_giftrank_item, viewGroup, false));
        }
        return new EmptyViewHolder(LayoutInflater.from(this.c).inflate(R.layout.live_giftrank_item_empty, viewGroup, false));
    }

    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            viewHolder2.divider.setVisibility(i == 0 ? 8 : 0);
            GiftRankData giftRankData = (GiftRankData) this.d.get(i);
            if (giftRankData != null) {
                viewHolder2.tv_username.setText(giftRankData.n);
                if (giftRankData.b == AppUtils.getInstance().getUserInfoProvider().getUserId() && giftRankData.s == AppUtils.getInstance().getUserInfoProvider().getUserOrigin()) {
                    viewHolder2.itemView.setBackgroundResource(R.color.color_d5f2ff);
                    viewHolder2.sd_avatar.setBackgroundResource(R.drawable.avatar_bg_mask_2dp_blue);
                } else {
                    viewHolder2.itemView.setBackgroundResource(R.color.white);
                    viewHolder2.sd_avatar.setBackgroundResource(R.color.white);
                }
                viewHolder2.label_me.setVisibility(8);
                LevelHelper.setLevelText(viewHolder2.tv_user_level, giftRankData.l);
                AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder2.sd_avatar, giftRankData.a);
                viewHolder2.tv_rank_num.setText(Integer.toString(giftRankData.r));
                if (giftRankData.c > 0) {
                    viewHolder2.tv_gift_num.setVisibility(0);
                    if (this.f == 2 && this.g == 1) {
                        viewHolder2.tv_gift_num.setText(Long.toString(giftRankData.c) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.c.getString(R.string.live_diamond));
                    } else {
                        viewHolder2.tv_gift_num.setText(Long.toString(giftRankData.c) + this.c.getString(R.string.live_gift_certificate));
                    }
                } else {
                    viewHolder2.tv_gift_num.setVisibility(8);
                }
                viewHolder2.itemView.setOnClickListener(new o(this, giftRankData));
                if (giftRankData.u == 0) {
                    viewHolder2.rank_change_num.setVisibility(8);
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_equal);
                } else if (giftRankData.u > 0) {
                    viewHolder2.rank_change_num.setVisibility(0);
                    viewHolder2.rank_change_num.setText(Math.abs(giftRankData.u) + "");
                    viewHolder2.rank_change_num.setTextColor(this.c.getResources().getColor(R.color.color_ff4468));
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_up);
                } else {
                    viewHolder2.rank_change_num.setVisibility(0);
                    viewHolder2.rank_change_num.setText(Math.abs(giftRankData.u) + "");
                    viewHolder2.rank_change_num.setTextColor(this.c.getResources().getColor(R.color.color_72d36b));
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_down);
                }
                if (this.f == 1 && this.g == 2) {
                    viewHolder2.rank_change_icon.setVisibility(8);
                    viewHolder2.rank_change_num.setVisibility(8);
                }
                if (TextUtils.isEmpty(giftRankData.d)) {
                    viewHolder2.fl_level.setVisibility(8);
                } else {
                    viewHolder2.fl_level.setVisibility(0);
                    viewHolder2.fl_level.setLevelAndName(giftRankData.fl, giftRankData.d);
                }
            }
            LayoutParams layoutParams;
            FrameLayout.LayoutParams layoutParams2;
            if (i < 3) {
                viewHolder2.iv_rank.setVisibility(0);
                layoutParams = (LayoutParams) viewHolder2.fl_rank.getLayoutParams();
                layoutParams.height = WindowUtils.dp2Px(76);
                viewHolder2.fl_rank.setLayoutParams(layoutParams);
                layoutParams2 = (FrameLayout.LayoutParams) viewHolder2.sd_avatar.getLayoutParams();
                layoutParams2.topMargin = WindowUtils.dp2Px(14);
                viewHolder2.sd_avatar.setLayoutParams(layoutParams2);
                viewHolder2.iv_rank.setVisibility(0);
                viewHolder2.tv_rank_num.setVisibility(8);
                viewHolder2.iv_rank_head.setVisibility(0);
                viewHolder2.tv_gift_num.setTextColor(this.c.getResources().getColor(R.color.color_ff4496));
                switch (i) {
                    case 0:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top1);
                        viewHolder2.iv_rank.setImageResource(R.drawable.live_ic_head1);
                        return;
                    case 1:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top2);
                        viewHolder2.iv_rank.setImageResource(R.drawable.live_ic_head2);
                        return;
                    case 2:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top3);
                        viewHolder2.iv_rank.setImageResource(R.drawable.live_ic_head3);
                        return;
                    default:
                        return;
                }
            }
            viewHolder2.iv_rank.setVisibility(8);
            layoutParams = (LayoutParams) viewHolder2.fl_rank.getLayoutParams();
            layoutParams.height = WindowUtils.dp2Px(58);
            viewHolder2.fl_rank.setLayoutParams(layoutParams);
            layoutParams2 = (FrameLayout.LayoutParams) viewHolder2.sd_avatar.getLayoutParams();
            layoutParams2.topMargin = WindowUtils.dp2Px(0);
            viewHolder2.sd_avatar.setLayoutParams(layoutParams2);
            viewHolder2.iv_rank.setVisibility(8);
            viewHolder2.tv_rank_num.setVisibility(0);
            viewHolder2.iv_rank_head.setVisibility(8);
            viewHolder2.tv_gift_num.setTextColor(this.c.getResources().getColor(R.color.color_FF675E72));
        }
    }

    public int getItemCount() {
        return this.d.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i >= this.d.size()) {
            return this.b;
        }
        return this.a;
    }
}
