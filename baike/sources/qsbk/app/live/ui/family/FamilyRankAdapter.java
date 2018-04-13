package qsbk.app.live.ui.family;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyRankData;
import qsbk.app.live.widget.FamilyLevelView;

public class FamilyRankAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
    private static int f = 1001;
    private int a = 0;
    private int b = 1;
    private int c = 2;
    private Context d;
    private ArrayList<FamilyRankData> e;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public View divider;
        public FamilyLevelView fl_level;
        public ImageView iv_rank_head;
        public ImageView rank_change_icon;
        public TextView rank_change_num;
        public ImageView sd_avatar;
        public TextView tv_gift_num;
        public TextView tv_rank_num;
        public TextView tv_username;

        public ViewHolder(View view) {
            super(view);
            this.divider = view.findViewById(R.id.divider);
            this.tv_rank_num = (TextView) view.findViewById(R.id.rank_num);
            this.sd_avatar = (ImageView) view.findViewById(R.id.avatar);
            this.tv_username = (TextView) view.findViewById(R.id.live_gift_username);
            this.fl_level = (FamilyLevelView) view.findViewById(R.id.fl_level);
            this.tv_gift_num = (TextView) view.findViewById(R.id.gift_num);
            this.rank_change_icon = (ImageView) view.findViewById(R.id.rank_change_icon);
            this.rank_change_num = (TextView) view.findViewById(R.id.rank_change_num);
            this.iv_rank_head = (ImageView) view.findViewById(R.id.iv_rank_head);
        }
    }

    public FamilyRankAdapter(Context context, ArrayList<FamilyRankData> arrayList) {
        this.d = context;
        this.e = arrayList;
    }

    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.a) {
            return new ViewHolder(LayoutInflater.from(this.d).inflate(R.layout.live_family_rank_item, viewGroup, false));
        }
        if (i != this.c) {
            return new ViewHolder(LayoutInflater.from(this.d).inflate(R.layout.live_familyrank_item_empty, viewGroup, false));
        }
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.live_familyrank_item_head, viewGroup, false);
        if (Constants.APP_FLAG == 1) {
            inflate.setBackgroundResource(R.drawable.live_family_rank_banner_qiubai);
        }
        return new ViewHolder(inflate);
    }

    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = i - 1;
        if (i2 >= 0 && i2 < this.e.size()) {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            viewHolder2.divider.setVisibility(i2 == 0 ? 8 : 0);
            viewHolder2.fl_level.setVisibility(8);
            FamilyRankData familyRankData = (FamilyRankData) this.e.get(i2);
            if (familyRankData != null) {
                viewHolder2.tv_username.setText(familyRankData.n);
                if (TextUtils.isEmpty(familyRankData.b)) {
                    viewHolder2.fl_level.setVisibility(8);
                } else {
                    viewHolder2.fl_level.setVisibility(0);
                    viewHolder2.fl_level.setLevelAndName(familyRankData.l, familyRankData.b);
                }
                AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder2.sd_avatar, familyRankData.c, WindowUtils.dp2Px(5));
                viewHolder2.tv_rank_num.setText(Integer.toString(familyRankData.r));
                if (familyRankData.f > 0) {
                    viewHolder2.tv_gift_num.setVisibility(0);
                    viewHolder2.tv_gift_num.setText(this.d.getString(R.string.family_fame, new Object[]{Long.toString(familyRankData.f)}));
                } else {
                    viewHolder2.tv_gift_num.setVisibility(8);
                }
                viewHolder2.itemView.setOnClickListener(new ba(this, familyRankData));
                if (familyRankData.u == 0) {
                    viewHolder2.rank_change_num.setVisibility(8);
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_equal);
                } else if (familyRankData.u > 0) {
                    viewHolder2.rank_change_num.setVisibility(0);
                    viewHolder2.rank_change_num.setText(Math.abs(familyRankData.u) + "");
                    viewHolder2.rank_change_num.setTextColor(this.d.getResources().getColor(R.color.color_ff4468));
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_up);
                } else {
                    viewHolder2.rank_change_num.setVisibility(0);
                    viewHolder2.rank_change_num.setText(Math.abs(familyRankData.u) + "");
                    viewHolder2.rank_change_num.setTextColor(this.d.getResources().getColor(R.color.color_72d36b));
                    viewHolder2.rank_change_icon.setImageResource(R.drawable.live_rank_down);
                }
            }
            if (i2 < 3) {
                viewHolder2.tv_rank_num.setVisibility(8);
                viewHolder2.iv_rank_head.setVisibility(0);
                viewHolder2.tv_gift_num.setTextColor(this.d.getResources().getColor(R.color.color_ff4496));
                switch (i2) {
                    case 0:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top1);
                        return;
                    case 1:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top2);
                        return;
                    case 2:
                        viewHolder2.iv_rank_head.setBackgroundResource(R.drawable.live_top3);
                        return;
                    default:
                        return;
                }
            }
            viewHolder2.tv_rank_num.setVisibility(0);
            viewHolder2.iv_rank_head.setVisibility(8);
            viewHolder2.tv_gift_num.setTextColor(this.d.getResources().getColor(R.color.color_FF675E72));
        }
    }

    public int getItemCount() {
        return this.e != null ? this.e.size() + 2 : 0;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.c;
        }
        if (i < 1 || i > this.e.size()) {
            return this.b;
        }
        return this.a;
    }
}
