package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.b;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import java.util.Collection;

public class d extends Adapter<StickerHolder> {
    b a = new b();
    RecyclerView b;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public StickerHolder a(ViewGroup viewGroup, int i) {
        return new StickerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_collection, viewGroup, false));
    }

    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public void a(StickerHolder stickerHolder, int i) {
        if (getItemViewType(i) == 0) {
            LayoutManager gridLayoutManager = new GridLayoutManager(stickerHolder.itemView.getContext(), 6);
            gridLayoutManager.setOrientation(1);
            stickerHolder.resource.setLayoutManager(gridLayoutManager);
            Adapter cVar = new c();
            cVar.a(b.b());
            stickerHolder.resource.setAdapter(cVar);
            return;
        }
        gridLayoutManager = new GridLayoutManager(stickerHolder.itemView.getContext(), 4);
        gridLayoutManager.setOrientation(1);
        stickerHolder.resource.setLayoutManager(gridLayoutManager);
        stickerHolder.resource.setAdapter(this.a);
        this.b = stickerHolder.resource;
    }

    public int getItemCount() {
        return 2;
    }

    public void a(Collection<a> collection) {
        this.a.a(collection);
    }

    public int b(Collection<a> collection) {
        this.a.b(collection);
        if (collection.size() <= 0 || this.b == null) {
            return 0;
        }
        int i = this.b.getResources().getDisplayMetrics().widthPixels / 4;
        this.b.scrollBy(0, i);
        return i;
    }
}
