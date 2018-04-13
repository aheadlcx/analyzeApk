package cn.xiaochuankeji.tieba.ui.my.mypost;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.BaseViewHolder;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import java.util.List;

public class b extends Adapter<BaseViewHolder> {
    private List<c> a;
    private Activity b;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((BaseViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    b(Activity activity) {
        this.b = activity;
    }

    public void a(List<c> list) {
        this.a = list;
        notifyDataSetChanged();
    }

    int a(long j) {
        if (this.a == null) {
            return 0;
        }
        for (c cVar : this.a) {
            if (j == cVar.getId()) {
                this.a.remove(cVar);
                break;
            }
        }
        notifyDataSetChanged();
        return this.a.size();
    }

    public BaseViewHolder a(ViewGroup viewGroup, int i) {
        return HolderCreator.a(this.b, viewGroup, i, PostFromType.FROM_MY_POST);
    }

    public void a(BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.a((c) this.a.get(i));
    }

    public int getItemCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public int getItemViewType(int i) {
        return ((c) this.a.get(i)).localPostType();
    }
}
