package cn.xiaochuankeji.tieba.ui.voice;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.BaseViewHolder;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import java.util.ArrayList;
import java.util.List;

public class a extends Adapter<BaseViewHolder> {
    private Activity a;
    private NavigatorTag b;
    private List<PostDataBean> c = new ArrayList();

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((BaseViewHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(Activity activity, NavigatorTag navigatorTag) {
        this.a = activity;
        this.b = navigatorTag;
    }

    public void a(List<PostDataBean> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    public void b(List<PostDataBean> list) {
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    public void a(PostDataBean postDataBean) {
        if (!this.c.contains(postDataBean)) {
            this.c.add(0, postDataBean);
            notifyDataSetChanged();
        }
    }

    public void a(long j) {
        if (this.c != null && !this.c.isEmpty()) {
            for (PostDataBean postDataBean : this.c) {
                if (postDataBean.getId() == j) {
                    this.c.remove(postDataBean);
                    break;
                }
            }
            notifyDataSetChanged();
        }
    }

    void a(long j, boolean z) {
        if (this.c != null) {
            for (PostDataBean postDataBean : this.c) {
                if (postDataBean.getMemberId() == j) {
                    postDataBean.setFollowStatus(z ? 1 : 0);
                }
            }
            notifyDataSetChanged();
        }
    }

    public List<PostDataBean> a() {
        return this.c;
    }

    public BaseViewHolder a(ViewGroup viewGroup, int i) {
        return HolderCreator.a(this.a, viewGroup, i, PostFromType.FROM_VOICE);
    }

    public int getItemViewType(int i) {
        return ((PostDataBean) this.c.get(i)).localPostType();
    }

    public void a(BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.a((PostDataBean) this.c.get(i), this.b);
    }

    public int getItemCount() {
        return this.c == null ? 0 : this.c.size();
    }
}
