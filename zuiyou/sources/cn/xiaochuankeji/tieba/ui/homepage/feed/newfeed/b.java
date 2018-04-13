package cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.BaseViewHolder;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import java.util.HashMap;
import java.util.List;

public class b extends Adapter<BaseViewHolder> {
    private List<c> a;
    private HashMap<Long, Boolean> b = new HashMap();
    private Activity c;

    public /* synthetic */ void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        a((BaseViewHolder) viewHolder, i);
    }

    @NonNull
    public /* synthetic */ ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    @SuppressLint({"UseSparseArrays"})
    b(Activity activity) {
        this.c = activity;
    }

    void a(List<c> list) {
        this.a = list;
    }

    @NonNull
    public BaseViewHolder a(@NonNull ViewGroup viewGroup, int i) {
        return HolderCreator.a(this.c, viewGroup, i, PostFromType.FROM_FOLLOW);
    }

    public void a(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.a((c) this.a.get(i));
        baseViewHolder.a(this.b);
    }

    public int getItemCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public int getItemViewType(int i) {
        return ((c) this.a.get(i)).localPostType();
    }
}
