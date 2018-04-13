package qsbk.app.live.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.model.BarrageData;
import qsbk.app.live.R;
import qsbk.app.live.ui.LiveBaseActivity;

public class BarrageListAdapter extends Adapter<ViewHolder> {
    private LiveBaseActivity a;
    private List<BarrageData> b;
    private BarrageItemClickListener c;

    public interface BarrageItemClickListener {
        void onClick(int i);
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView tvBarrageText;

        public ViewHolder(View view) {
            super(view);
            this.tvBarrageText = (TextView) view.findViewById(R.id.barrage_text);
        }
    }

    public BarrageListAdapter(LiveBaseActivity liveBaseActivity, List<BarrageData> list) {
        this.a = liveBaseActivity;
        this.b = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.a).inflate(R.layout.live_barrage_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        BarrageData barrageData = (BarrageData) this.b.get(i);
        if (barrageData != null) {
            viewHolder.tvBarrageText.setText(barrageData.n);
            if (!TextUtils.isEmpty(barrageData.c)) {
                viewHolder.tvBarrageText.setTextColor(Color.parseColor(barrageData.c));
            }
            viewHolder.itemView.setOnClickListener(new d(this, i));
        }
    }

    public int getItemCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public void setBarrageItemClickListener(BarrageItemClickListener barrageItemClickListener) {
        this.c = barrageItemClickListener;
    }
}
