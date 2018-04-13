package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcEventJson;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.List;

public class a extends e<d> {
    private List<UgcEventJson> a;
    private LayoutInflater l;

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return b(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public a(Context context, List<UgcEventJson> list) {
        this.a = list;
        this.l = LayoutInflater.from(context);
    }

    public d a(ViewGroup viewGroup) {
        return new c(this.l.inflate(R.layout.view_item_ugcevent, null));
    }

    public int a() {
        return this.a.size();
    }

    public void a(d dVar, int i) {
        if (dVar instanceof c) {
            ((c) dVar).a((UgcEventJson) this.a.get(i));
        }
    }

    public d a(View view) {
        return null;
    }

    public d b(View view) {
        return null;
    }
}
