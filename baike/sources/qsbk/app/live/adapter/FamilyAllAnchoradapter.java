package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyAnchorData;
import qsbk.app.live.ui.helper.LevelHelper;

public class FamilyAllAnchoradapter extends Adapter<ViewHolder> {
    protected List<FamilyAnchorData> a = new ArrayList();
    protected Context b;
    protected long c;

    public static class ItemViewHolder extends ViewHolder {
        public SimpleDraweeView ivAvatar;
        public ImageView ivMore;
        public TextView tvContribute;
        public TextView tvLevel;
        public TextView tvUserName;

        public ItemViewHolder(View view) {
            super(view);
            this.ivAvatar = (SimpleDraweeView) view.findViewById(R.id.avatar);
            this.tvUserName = (TextView) view.findViewById(R.id.tv_username);
            this.tvContribute = (TextView) view.findViewById(R.id.tv_contribute);
            this.tvLevel = (TextView) view.findViewById(R.id.tv_user_lv);
            this.ivMore = (ImageView) view.findViewById(R.id.iv_more);
        }
    }

    public FamilyAllAnchoradapter(Context context, List<FamilyAnchorData> list, long j) {
        this.b = context;
        this.a = list;
        this.c = j;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_family_member, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (i >= 0 && i < this.a.size()) {
            FamilyAnchorData familyAnchorData = (FamilyAnchorData) this.a.get(i);
            if (familyAnchorData != null) {
                AppUtils.getInstance().getImageProvider().loadAvatar(itemViewHolder.ivAvatar, familyAnchorData.a);
                itemViewHolder.tvUserName.setText(familyAnchorData.n);
                itemViewHolder.tvContribute.setText(this.b.getResources().getString(R.string.family_coupon, new Object[]{familyAnchorData.c + ""}));
                itemViewHolder.ivAvatar.setOnClickListener(new f(this, familyAnchorData));
                LevelHelper.setLevelText(itemViewHolder.tvLevel, familyAnchorData.e);
                if (familyAnchorData.l) {
                    itemViewHolder.ivMore.setImageResource(R.drawable.ic_family_living);
                } else {
                    itemViewHolder.ivMore.setImageBitmap(null);
                }
                itemViewHolder.tvUserName.requestLayout();
            }
        }
    }

    public int getItemCount() {
        return this.a.size();
    }
}
