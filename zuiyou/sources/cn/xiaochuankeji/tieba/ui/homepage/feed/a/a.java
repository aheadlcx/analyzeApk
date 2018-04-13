package cn.xiaochuankeji.tieba.ui.homepage.feed.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import cn.xiaochuankeji.tieba.ui.recommend.d;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class a extends Adapter<cn.xiaochuankeji.tieba.ui.recommend.holder.a> {
    private LinkedList<RecPostDataBean> a = new LinkedList();
    private NavigatorTag b;
    private HashMap<Long, Boolean> c = new HashMap();
    private HashMap<Long, f> d = new HashMap();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((cn.xiaochuankeji.tieba.ui.recommend.holder.a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a() {
        this.a.clear();
        this.c.clear();
        this.d.clear();
    }

    public void a(NavigatorTag navigatorTag) {
        this.b = navigatorTag;
    }

    public cn.xiaochuankeji.tieba.ui.recommend.holder.a a(ViewGroup viewGroup, int i) {
        cn.xiaochuankeji.tieba.ui.recommend.holder.a a = d.a().a(i, LayoutInflater.from(viewGroup.getContext()), viewGroup, new cn.xiaochuankeji.tieba.ui.recommend.holder.a.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(long j) {
            }
        }, this.b);
        a.a(this.c);
        a.b(this.d);
        return a;
    }

    public void a(cn.xiaochuankeji.tieba.ui.recommend.holder.a aVar, int i) {
        aVar.a(aVar, a(i));
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

    public c a(int i) {
        return (c) this.a.get(i);
    }

    public void a(@Nullable List<RecPostDataBean> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public void b(@Nullable List<RecPostDataBean> list) {
        if (this.a.isEmpty()) {
            this.a.addAll(list);
            notifyDataSetChanged();
            return;
        }
        this.a.addAll(0, list);
        notifyItemRangeInserted(0, list.size());
    }

    public void c(@Nullable List<RecPostDataBean> list) {
        int size = this.a.size();
        this.a.addAll(list);
        notifyItemRangeInserted(size, this.a.size() - size);
    }

    public List<RecPostDataBean> a() {
        return this.a;
    }

    public void b() {
        this.a.clear();
        notifyDataSetChanged();
    }
}
