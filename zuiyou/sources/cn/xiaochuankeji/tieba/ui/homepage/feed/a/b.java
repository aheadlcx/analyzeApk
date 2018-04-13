package cn.xiaochuankeji.tieba.ui.homepage.feed.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.homepage.feed.holder.FeedFooterHolder;
import cn.xiaochuankeji.tieba.ui.homepage.feed.holder.FeedMemberHolder;
import cn.xiaochuankeji.tieba.ui.homepage.feed.holder.a;
import java.util.ArrayList;
import java.util.List;

public class b extends Adapter<cn.xiaochuankeji.tieba.ui.homepage.feed.holder.b> {
    private final long a = Long.MAX_VALUE;
    private final long b = Long.MIN_VALUE;
    private final MemberInfoBean c = new MemberInfoBean();
    private final MemberInfoBean d = new MemberInfoBean();
    private ArrayList<MemberInfoBean> e = new ArrayList();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((cn.xiaochuankeji.tieba.ui.homepage.feed.holder.b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public b() {
        this.c.setId(Long.MAX_VALUE);
        this.e.add(this.c);
        this.d.setId(Long.MIN_VALUE);
        this.e.add(this.d);
    }

    public cn.xiaochuankeji.tieba.ui.homepage.feed.holder.b a(ViewGroup viewGroup, int i) {
        if (i > 0) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_header, viewGroup, false));
        }
        if (i < 0) {
            return new FeedFooterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_footer, viewGroup, false));
        }
        return new FeedMemberHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_member, viewGroup, false));
    }

    public void a(cn.xiaochuankeji.tieba.ui.homepage.feed.holder.b bVar, int i) {
        bVar.a(bVar, i, a(i));
    }

    public int getItemViewType(int i) {
        MemberInfoBean a = a(i);
        if (a.getId() == Long.MAX_VALUE) {
            return 1;
        }
        if (a.getId() == Long.MIN_VALUE) {
            return -1;
        }
        return 0;
    }

    public MemberInfoBean a(int i) {
        return (MemberInfoBean) this.e.get(i);
    }

    public int getItemCount() {
        return this.e.size();
    }

    public void a(@Nullable List<MemberInfoBean> list) {
        if (list != null && !list.isEmpty()) {
            this.e.clear();
            this.e.add(this.c);
            this.e.addAll(1, list);
            this.e.add(this.d);
            notifyDataSetChanged();
        }
    }
}
