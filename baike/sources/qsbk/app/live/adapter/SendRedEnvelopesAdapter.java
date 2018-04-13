package qsbk.app.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import qsbk.app.core.model.RedEnvelopes;
import qsbk.app.live.R;
import qsbk.app.live.adapter.LiveMessageAdapter.OnItemClickLitener;

public class SendRedEnvelopesAdapter extends Adapter<ViewHolder> {
    private List<RedEnvelopes> a;
    private Context b;
    private int c = 0;
    private OnItemClickLitener d;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public View selected;
        public TextView tv_count;
        public TextView tv_diamond;

        public ViewHolder(View view) {
            super(view);
            this.tv_diamond = (TextView) view.findViewById(R.id.tv_diamond);
            this.tv_count = (TextView) view.findViewById(R.id.tv_count);
            this.selected = view.findViewById(R.id.selected);
        }
    }

    public SendRedEnvelopesAdapter(Context context, List<RedEnvelopes> list) {
        this.b = context;
        this.a = list;
    }

    public RedEnvelopes getSelectedItem() {
        return (this.c < 0 || this.c >= this.a.size()) ? null : (RedEnvelopes) this.a.get(this.c);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.live_red_envelopes_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i >= 0 && i < this.a.size()) {
            RedEnvelopes redEnvelopes = (RedEnvelopes) this.a.get(i);
            viewHolder.tv_diamond.setText(String.valueOf(redEnvelopes.coin));
            viewHolder.tv_count.setText(String.valueOf(redEnvelopes.size));
        }
        viewHolder.selected.setVisibility(i == this.c ? 0 : 8);
        viewHolder.itemView.setOnClickListener(new y(this, i));
    }

    public int getItemCount() {
        return this.a != null ? this.a.size() : 0;
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.d = onItemClickLitener;
    }
}
