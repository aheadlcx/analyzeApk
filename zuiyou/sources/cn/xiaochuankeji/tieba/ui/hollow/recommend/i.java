package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class i extends Adapter {
    private final Context a;
    private List<HollowRecommendItemBean> b = new ArrayList();

    public i(Context context) {
        this.a = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TreeHoleHolder(LayoutInflater.from(this.a).inflate(R.layout.holder_tree_hole_constraint, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TreeHoleHolder treeHoleHolder = (TreeHoleHolder) viewHolder;
        List arrayList = new ArrayList();
        int i2 = i * 4;
        for (int i3 = 0; i3 < 4; i3++) {
            if (i2 + i3 < this.b.size()) {
                arrayList.add(this.b.get(i2 + i3));
            }
        }
        treeHoleHolder.a(i, arrayList);
    }

    public int getItemCount() {
        return (int) Math.ceil((double) (((float) this.b.size()) / 4.0f));
    }

    public int a() {
        return this.b.size();
    }

    public void a(List<HollowRecommendItemBean> list) {
        Collection arrayList = new ArrayList();
        for (HollowRecommendItemBean hollowRecommendItemBean : list) {
            if (this.b.contains(hollowRecommendItemBean)) {
                c(hollowRecommendItemBean);
            } else {
                arrayList.add(hollowRecommendItemBean);
            }
        }
        this.b.addAll(0, arrayList);
        notifyDataSetChanged();
    }

    private void c(HollowRecommendItemBean hollowRecommendItemBean) {
        for (HollowRecommendItemBean hollowRecommendItemBean2 : this.b) {
            if (hollowRecommendItemBean2.equals(hollowRecommendItemBean)) {
                hollowRecommendItemBean2.a(hollowRecommendItemBean);
                return;
            }
        }
    }

    public void b(List<HollowRecommendItemBean> list) {
        Collection arrayList = new ArrayList();
        for (HollowRecommendItemBean hollowRecommendItemBean : list) {
            if (!this.b.contains(hollowRecommendItemBean)) {
                arrayList.add(hollowRecommendItemBean);
            }
        }
        this.b.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void a(HollowRecommendItemBean hollowRecommendItemBean) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        for (HollowRecommendItemBean equals : this.b) {
            if (equals.equals(hollowRecommendItemBean)) {
                return;
            }
        }
        this.b.add(0, hollowRecommendItemBean);
        notifyDataSetChanged();
    }

    public void a(long j) {
        for (HollowRecommendItemBean hollowRecommendItemBean : this.b) {
            if (hollowRecommendItemBean.id == j) {
                this.b.remove(hollowRecommendItemBean);
                break;
            }
        }
        notifyDataSetChanged();
    }

    public List<HollowRecommendItemBean> b() {
        return this.b;
    }

    public void b(HollowRecommendItemBean hollowRecommendItemBean) {
        if (this.b != null && this.b.size() != 0) {
            for (HollowRecommendItemBean hollowRecommendItemBean2 : this.b) {
                if (hollowRecommendItemBean2.id == hollowRecommendItemBean.id) {
                    hollowRecommendItemBean2.audio = hollowRecommendItemBean.audio;
                    hollowRecommendItemBean2.subject = hollowRecommendItemBean.subject;
                    hollowRecommendItemBean2.msgCount = hollowRecommendItemBean.msgCount;
                    hollowRecommendItemBean2.member = hollowRecommendItemBean.member;
                    hollowRecommendItemBean2.emotion = hollowRecommendItemBean.emotion;
                    hollowRecommendItemBean2.hugs = hollowRecommendItemBean.hugs;
                    hollowRecommendItemBean2.hugged = hollowRecommendItemBean.hugged;
                    notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.clear();
            notifyDataSetChanged();
        }
    }
}
