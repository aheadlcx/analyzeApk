package com.budejie.www.wallpaper;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.budejie.www.R;
import com.budejie.www.util.an;
import com.budejie.www.wallpaper.a.a;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;

public class c extends Adapter<c$a> {
    private Context a;
    private List<a> b;
    private int c;
    private int d = ((this.c * 158) / 119);
    private ColorDrawable e = new ColorDrawable();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((c$a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public c(Context context, List<a> list, int i) {
        this.a = context;
        this.b = list;
        this.c = (an.x(context) - (an.a(context, i) * 2)) / 3;
        this.e.setColor(this.a.getResources().getColor(R.color.recyclerview_item_load_day_background));
    }

    public c$a a(ViewGroup viewGroup, int i) {
        return new c$a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wall_paper_layout, viewGroup, false));
    }

    public void a(c$a c_a, int i) {
        if (!com.budejie.www.goddubbing.c.a.a(this.b)) {
            a(c_a);
            c_a.a.measure(0, 0);
            String b = ((a) this.b.get(i)).b();
            if (c_a.a != null) {
                i.b(this.a).a(b).a(this.e).b(this.e).a(DiskCacheStrategy.SOURCE).a(c_a.a);
                c_a.a.setOnClickListener(new c$1(this, c_a));
            }
        }
    }

    private void a(c$a c_a) {
        LayoutParams layoutParams = (LayoutParams) c_a.a.getLayoutParams();
        layoutParams.width = this.c;
        layoutParams.height = this.d;
        c_a.a.setLayoutParams(layoutParams);
    }

    public int getItemCount() {
        return com.budejie.www.goddubbing.c.a.a(this.b) ? 0 : this.b.size();
    }
}
