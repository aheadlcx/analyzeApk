package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyMemberData;

public class FamilyEliteAdapter extends Adapter<ViewHolder> {
    protected final int b;
    protected final int c;
    protected Context d;
    protected List<FamilyMemberData> e;

    public static class ItemViewHolder extends ViewHolder {
        public ImageView ivAvatar;
        public TextView tvCredit;
        public TextView tvName;

        public ItemViewHolder(View view) {
            super(view);
            this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvCredit = (TextView) view.findViewById(R.id.tv_credit);
        }
    }

    public FamilyEliteAdapter() {
        this.e = new ArrayList();
        this.b = (int) (((float) (WindowUtils.getScreenWidth() - (WindowUtils.dp2Px(46) * 3))) / 3.5f);
        this.c = this.b;
    }

    public FamilyEliteAdapter(Context context, List<FamilyMemberData> list) {
        this.e = new ArrayList();
        this.d = context;
        this.e = list;
        this.b = (int) (((float) (WindowUtils.getScreenWidth() - (WindowUtils.dp2Px(46) * 3))) / 3.5f);
        this.c = this.b;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.d).inflate(R.layout.item_family_elite, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (i >= 0 && i < this.e.size()) {
            FamilyMemberData familyMemberData = (FamilyMemberData) this.e.get(i);
            if (familyMemberData != null) {
                LayoutParams layoutParams = (LayoutParams) itemViewHolder.ivAvatar.getLayoutParams();
                layoutParams.width = this.b;
                layoutParams.height = this.c;
                itemViewHolder.ivAvatar.setLayoutParams(layoutParams);
                if (familyMemberData.u == 0) {
                    itemViewHolder.ivAvatar.setImageResource(R.drawable.ic_family_wait);
                    itemViewHolder.tvName.setText(R.string.family_wait);
                    itemViewHolder.tvCredit.setVisibility(8);
                    itemViewHolder.ivAvatar.setClickable(false);
                    return;
                }
                AppUtils.getInstance().getImageProvider().loadAvatar(itemViewHolder.ivAvatar, familyMemberData.a, true);
                itemViewHolder.tvName.setText(familyMemberData.n);
                itemViewHolder.tvCredit.setVisibility(0);
                itemViewHolder.tvCredit.setText(this.d.getString(R.string.family_fame, new Object[]{FormatUtils.formatCoupon((long) familyMemberData.f) + ""}));
                itemViewHolder.ivAvatar.setOnClickListener(new n(this, familyMemberData));
            }
        }
    }

    public int getItemCount() {
        return this.e.size();
    }
}
