package cn.xiaochuankeji.tieba.ui.picker;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.c;

public class RegionAdapter extends c<a> {
    private LayoutInflater a;

    static class IndexVH extends ViewHolder {
        @BindView
        AppCompatTextView tv;

        IndexVH(View view) {
            super(view);
            ButterKnife.a(this, view);
        }
    }

    public class IndexVH_ViewBinding implements Unbinder {
        private IndexVH b;

        @UiThread
        public IndexVH_ViewBinding(IndexVH indexVH, View view) {
            this.b = indexVH;
            indexVH.tv = (AppCompatTextView) b.b(view, R.id.index, "field 'tv'", AppCompatTextView.class);
        }

        @CallSuper
        public void a() {
            IndexVH indexVH = this.b;
            if (indexVH == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            indexVH.tv = null;
        }
    }

    RegionAdapter(Context context) {
        this.a = LayoutInflater.from(context);
    }

    public ViewHolder a(ViewGroup viewGroup) {
        return new IndexVH(this.a.inflate(R.layout.item_index_region, viewGroup, false));
    }

    public ViewHolder b(ViewGroup viewGroup) {
        return new RegionHolder(this.a.inflate(R.layout.item_region, viewGroup, false));
    }

    public void a(ViewHolder viewHolder, String str) {
        ((IndexVH) viewHolder).tv.setText(str);
    }

    public void a(ViewHolder viewHolder, a aVar) {
        RegionHolder regionHolder = (RegionHolder) viewHolder;
        regionHolder.name.setText(aVar.b);
        if (aVar.c == 0) {
            regionHolder.flag.setImageResource(R.drawable.flag_loading);
        } else {
            regionHolder.flag.setImageResource(aVar.c);
        }
    }
}
