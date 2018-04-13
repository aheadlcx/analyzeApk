package cn.xiaochuankeji.tieba.ui.topic.ui;

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

public class a extends Adapter<BaseViewHolder> {
    private HashMap<Long, Boolean> a = new HashMap();
    private List<c> b;
    private Activity c;

    public /* synthetic */ void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        a((BaseViewHolder) viewHolder, i);
    }

    @NonNull
    public /* synthetic */ ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    @SuppressLint({"UseSparseArrays"})
    a(Activity activity) {
        this.c = activity;
    }

    void a(List<c> list) {
        if (list != null) {
            this.b = list;
            notifyDataSetChanged();
        }
    }

    int a(long j) {
        if (this.b == null) {
            return 0;
        }
        for (c cVar : this.b) {
            if (cVar.getId() == j) {
                this.b.remove(cVar);
                break;
            }
        }
        notifyDataSetChanged();
        return this.b.size();
    }

    void a(long j, boolean z) {
        if (this.b != null) {
            for (c cVar : this.b) {
                if (cVar.getMemberId() == j) {
                    cVar.setFollowStatus(z ? 1 : 0);
                }
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    public BaseViewHolder a(@NonNull ViewGroup viewGroup, int i) {
        return HolderCreator.a(this.c, viewGroup, i, PostFromType.FROM_TOPIC);
    }

    public void a(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.a((c) this.b.get(i));
        baseViewHolder.a(this.a);
    }

    public int getItemCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public int getItemViewType(int i) {
        return ((c) this.b.get(i)).localPostType();
    }
}
