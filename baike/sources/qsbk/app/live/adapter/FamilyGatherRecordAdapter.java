package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyGatherRecordData;

public class FamilyGatherRecordAdapter extends Adapter<ViewHolder> {
    private Context a;
    private List<FamilyGatherRecordData> b;

    public static class ItemTailViewHolder extends ViewHolder {
        public TextView tvEmpty;

        public ItemTailViewHolder(View view) {
            super(view);
            this.tvEmpty = (TextView) view.findViewById(R.id.tv_empty);
        }
    }

    public static class ItemViewHolder extends ViewHolder {
        public TextView tvAnchor;
        public TextView tvDateTime;

        public ItemViewHolder(View view) {
            super(view);
            this.tvDateTime = (TextView) view.findViewById(R.id.tv_datetime);
            this.tvAnchor = (TextView) view.findViewById(R.id.tv_anchor);
        }
    }

    public FamilyGatherRecordAdapter(Context context, List<FamilyGatherRecordData> list) {
        this.a = context;
        this.b = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ItemViewHolder(LayoutInflater.from(this.a).inflate(R.layout.item_gather_record, viewGroup, false));
        }
        return new ItemTailViewHolder(LayoutInflater.from(this.a).inflate(R.layout.item_gather_record_tail, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i < 0 || i >= this.b.size()) {
            ItemTailViewHolder itemTailViewHolder = (ItemTailViewHolder) viewHolder;
            if (this.b.size() == 0) {
                itemTailViewHolder.tvEmpty.setVisibility(0);
                return;
            } else {
                itemTailViewHolder.tvEmpty.setVisibility(8);
                return;
            }
        }
        FamilyGatherRecordData familyGatherRecordData = (FamilyGatherRecordData) this.b.get(i);
        if (familyGatherRecordData != null) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            itemViewHolder.tvDateTime.setText(familyGatherRecordData.t);
            itemViewHolder.tvAnchor.setText(this.a.getString(R.string.family_gather_success, new Object[]{familyGatherRecordData.n}));
        }
    }

    public int getItemCount() {
        return this.b.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i < this.b.size()) {
            return 0;
        }
        return 1;
    }
}
