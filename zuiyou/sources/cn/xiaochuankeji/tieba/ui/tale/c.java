package cn.xiaochuankeji.tieba.ui.tale;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;

public class c extends Adapter {
    private int a;
    private int b;

    class a extends ViewHolder {
        ImageView a;
        final /* synthetic */ c b;

        public a(c cVar, View view) {
            this.b = cVar;
            super(view);
            this.a = (ImageView) view.findViewById(R.id.iv_indicator);
        }
    }

    public c(int i) {
        this.b = i;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.indicator_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        if (this.a == i) {
            aVar.a.setImageResource(R.drawable.tale_indicator_select);
        } else {
            aVar.a.setImageResource(R.drawable.tale_indicator);
        }
    }

    public void a(int i) {
        this.a = i;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.b;
    }
}
