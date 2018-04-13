package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class g extends Adapter<ViewHolder> {
    private List<UgcVideoInfoBean> a;
    private int b;
    private boolean c;
    private LayoutInflater d;
    private RecyclerView e;
    private ArrayList<e> f = new ArrayList();
    private ArrayList<e> g = new ArrayList();
    private boolean h;
    private OnScrollListener i = new OnScrollListener(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (this.a.h && i == 0) {
                Iterator it = this.a.g.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).a(this.a.e.getContext());
                }
                this.a.g.clear();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
        }
    };

    public g(Context context, List<UgcVideoInfoBean> list, int i, boolean z) {
        this.b = i;
        this.a = list;
        this.d = LayoutInflater.from(context);
        this.c = z;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new e(this.d.inflate(R.layout.view_item_ugc_video_recommend, null, false));
        }
        if (1 == i) {
            return new b(this.d.inflate(R.layout.view_item_ugcvideo_refresh, null, false));
        }
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof e) {
            if (b() && i >= this.b) {
                i--;
            }
            ((e) viewHolder).a((UgcVideoInfoBean) this.a.get(i), this.c);
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
        }
    }

    public int getItemViewType(int i) {
        if (b() && i == this.b) {
            return 1;
        }
        return 0;
    }

    public int getItemCount() {
        int size = this.a.size();
        if (b()) {
            return size + 1;
        }
        return size;
    }

    public void a(int i) {
        this.b = i;
        notifyDataSetChanged();
    }

    private boolean b() {
        return this.b > 0 && this.b < this.a.size();
    }

    public void a() {
        if (this.h) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((e) it.next()).a(this.e.getContext());
            }
        }
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (this.h && (viewHolder instanceof e)) {
            final e eVar = (e) viewHolder;
            eVar.a(new a(this) {
                final /* synthetic */ g b;

                public void a(WebImageView webImageView) {
                    if (this.b.e.getScrollState() == 0) {
                        eVar.a(this.b.e.getContext());
                    } else {
                        this.b.g.add(eVar);
                    }
                }

                public void a(WebImageView webImageView, Throwable th) {
                }
            });
            this.f.add(eVar);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (this.h && (viewHolder instanceof e)) {
            this.f.remove(viewHolder);
            this.g.remove(viewHolder);
            e eVar = (e) viewHolder;
            eVar.a();
            eVar.a(null);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.e = recyclerView;
        this.e.addOnScrollListener(this.i);
        GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
        if (C == null || C.ugcvideoCachePreloadEnabled != 1) {
            this.h = false;
        } else {
            this.h = true;
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView.removeOnScrollListener(this.i);
        this.e = null;
    }
}
