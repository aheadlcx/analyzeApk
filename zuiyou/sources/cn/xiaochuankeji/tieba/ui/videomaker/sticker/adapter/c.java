package cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import rx.b.b;

public class c extends Adapter<StickerHolder> {
    private ArrayList<a> a = new ArrayList();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((StickerHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public StickerHolder a(ViewGroup viewGroup, int i) {
        return new StickerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_sticker_resource, viewGroup, false));
    }

    public void a(StickerHolder stickerHolder, int i) {
        final a aVar = (a) this.a.get(i);
        stickerHolder.image.setScaleType(ScaleType.FIT_CENTER);
        stickerHolder.image.setImageResource(aVar.n);
        stickerHolder.state.setVisibility(4);
        stickerHolder.progres.clearAnimation();
        stickerHolder.progres.setVisibility(4);
        com.jakewharton.a.b.a.a(stickerHolder.itemView).c(150, TimeUnit.MILLISECONDS).a(new b<Void>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b(13);
                bVar.c = aVar.m;
                bVar.d = aVar.s;
                org.greenrobot.eventbus.c.a().d(bVar);
            }
        });
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(Collection<a> collection) {
        this.a.clear();
        this.a.addAll(collection);
        notifyDataSetChanged();
    }
}
