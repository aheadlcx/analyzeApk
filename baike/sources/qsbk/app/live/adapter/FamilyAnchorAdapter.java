package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.R;
import qsbk.app.live.adapter.FamilyEliteAdapter.ItemViewHolder;
import qsbk.app.live.model.FamilyAnchorData;

public class FamilyAnchorAdapter extends FamilyEliteAdapter {
    protected List<FamilyAnchorData> a = new ArrayList();

    public FamilyAnchorAdapter(Context context, List<FamilyAnchorData> list) {
        this.d = context;
        this.a = list;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (i >= 0 && i < this.a.size()) {
            FamilyAnchorData familyAnchorData = (FamilyAnchorData) this.a.get(i);
            if (familyAnchorData != null) {
                LayoutParams layoutParams = (LayoutParams) itemViewHolder.ivAvatar.getLayoutParams();
                layoutParams.width = this.b;
                layoutParams.height = this.c;
                itemViewHolder.ivAvatar.setLayoutParams(layoutParams);
                if (familyAnchorData.i == 0) {
                    itemViewHolder.ivAvatar.setImageResource(R.drawable.ic_family_wait);
                    itemViewHolder.tvName.setText(R.string.family_wait);
                    return;
                }
                AppUtils.getInstance().getImageProvider().loadAvatar(itemViewHolder.ivAvatar, familyAnchorData.a);
                itemViewHolder.tvName.setText(familyAnchorData.n);
                itemViewHolder.tvCredit.setText(this.d.getResources().getString(R.string.family_coupon, new Object[]{FormatUtils.formatCoupon(familyAnchorData.c) + ""}));
                itemViewHolder.ivAvatar.setOnClickListener(new m(this, familyAnchorData));
            }
        }
    }

    public int getItemCount() {
        return this.a.size();
    }
}
