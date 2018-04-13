package cn.xiaochuankeji.tieba.ui.my.download;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.ui.widget.CheckView;
import com.zhihu.matisse.internal.ui.widget.MediaGrid.OnMediaGridClickListener;
import com.zhihu.matisse.internal.ui.widget.MediaGrid.OnMediaLongClickListener;
import com.zhihu.matisse.internal.ui.widget.MediaGrid.PreBindInfo;
import com.zhihu.matisse.internal.ui.widget.MediaGrid.ThumbnailProvider;
import com.zhihu.matisse.thumbnail.ThumbnailManager;
import java.util.ArrayList;
import java.util.List;

public class c extends Adapter<ViewHolder> implements OnMediaGridClickListener, OnMediaLongClickListener, ThumbnailProvider {
    private final ThumbnailManager a;
    private RecyclerView b;
    private int c;
    private final Drawable d;
    private List<a> e = new ArrayList();
    private c f;
    private OnMediaLongClickListener g;

    public interface c {
        void a(Item item);
    }

    private static class a extends ViewHolder {
        public TextView a;

        a(View view) {
            super(view);
            this.a = (TextView) view;
        }
    }

    private static class b extends ViewHolder {
        private DownloadGrid a;

        b(View view) {
            super(view);
            this.a = (DownloadGrid) view;
        }
    }

    public c(Context context, ThumbnailManager thumbnailManager, RecyclerView recyclerView) {
        this.a = thumbnailManager;
        this.b = recyclerView;
        this.d = new ColorDrawable(-1);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_date_item, viewGroup, false));
        }
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_grid_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 1) {
            ((a) viewHolder).a.setText(((a) this.e.get(i)).a);
            return;
        }
        b bVar = (b) viewHolder;
        bVar.a.preBindMedia(this, new PreBindInfo(a(bVar.a.getContext()), this.d, false, viewHolder));
        bVar.a.bindMedia(((a) this.e.get(i)).c);
        bVar.a.setOnMediaGridClickListener(this);
        bVar.a.setOnMediaLongClickListener(this);
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public void a(OnMediaLongClickListener onMediaLongClickListener) {
        this.g = onMediaLongClickListener;
    }

    public int getItemViewType(int i) {
        return ((a) this.e.get(i)).b;
    }

    public void a(List list) {
        this.e = list;
        notifyDataSetChanged();
    }

    private int a(Context context) {
        if (this.c == 0) {
            int spanCount = ((GridLayoutManager) this.b.getLayoutManager()).getSpanCount();
            this.c = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.media_grid_spacing) * (spanCount - 1))) / spanCount;
        }
        return this.c;
    }

    public void onThumbnailClicked(ImageView imageView, Item item, ViewHolder viewHolder) {
        if (this.f != null) {
            this.f.a(item);
        }
    }

    public void onCheckViewClicked(CheckView checkView, Item item, ViewHolder viewHolder) {
    }

    public int getItemCount() {
        return this.e.size();
    }

    public String provideThumbnailPath(Item item) {
        String wantThumbnailPath = this.a != null ? this.a.wantThumbnailPath(item) : null;
        if (item.isVideo()) {
            item.videoThumbnail = wantThumbnailPath;
        }
        return wantThumbnailPath;
    }

    public void onLongClick(Item item) {
        if (this.g != null) {
            this.g.onLongClick(item);
        }
    }
}
