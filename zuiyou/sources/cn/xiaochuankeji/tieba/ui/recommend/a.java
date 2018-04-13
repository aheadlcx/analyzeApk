package cn.xiaochuankeji.tieba.ui.recommend;

import android.arch.lifecycle.q;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a extends Adapter<cn.xiaochuankeji.tieba.ui.recommend.holder.a> {
    private List<c> a = new ArrayList();
    private LayoutInflater b;
    private Context c;
    private RecommendViewModel d;
    private NavigatorTag e;
    private HashMap<Long, Boolean> f;
    private HashMap<Long, f> g;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((cn.xiaochuankeji.tieba.ui.recommend.holder.a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(NewRecommendFragment newRecommendFragment, NavigatorTag navigatorTag) {
        this.c = newRecommendFragment.getContext();
        this.b = LayoutInflater.from(this.c);
        this.d = (RecommendViewModel) q.a((Fragment) newRecommendFragment).a(RecommendViewModel.class);
        this.f = new HashMap();
        this.g = new HashMap();
        this.e = navigatorTag;
    }

    public cn.xiaochuankeji.tieba.ui.recommend.holder.a a(ViewGroup viewGroup, int i) {
        cn.xiaochuankeji.tieba.ui.recommend.holder.a a = d.a().a(i, this.b, viewGroup, this.d, this.e);
        a.a(this.f);
        a.b(this.g);
        return a;
    }

    public void a(cn.xiaochuankeji.tieba.ui.recommend.holder.a aVar, int i) {
        if (i >= 0 && i <= this.a.size()) {
            aVar.a(aVar, (c) this.a.get(i));
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    public int getItemViewType(int i) {
        if (i < 0 || i > this.a.size()) {
            return 0;
        }
        return d.a().a((c) this.a.get(i));
    }

    public void a(List<c> list) {
        for (c cVar : list) {
            if (!this.a.contains(cVar)) {
                this.a.add(0, cVar);
            }
        }
        notifyDataSetChanged();
    }

    public void b(List<c> list) {
        int i = 0;
        for (c cVar : list) {
            int i2;
            if (this.a.contains(cVar)) {
                i2 = i;
            } else {
                this.a.add(cVar);
                i2 = i + 1;
            }
            i = i2;
        }
        notifyItemRangeInserted(this.a.size() - i, i);
    }

    public int a(long j) {
        for (int i = 0; i < this.a.size(); i++) {
            if (((c) this.a.get(i)).getId() == j) {
                return i;
            }
        }
        return -1;
    }

    public void b(long j) {
        for (int i = 0; i < this.a.size(); i++) {
            if (((c) this.a.get(i)).getId() == j) {
                this.a.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    public c a(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (c) this.a.get(i);
    }
}
