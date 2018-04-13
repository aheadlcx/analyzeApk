package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.topic.holder.PostViewHolder_ViewBinding;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;

public class SoftAdsItemHolder_ViewBinding extends PostViewHolder_ViewBinding {
    private SoftAdsItemHolder b;

    @UiThread
    public SoftAdsItemHolder_ViewBinding(SoftAdsItemHolder softAdsItemHolder, View view) {
        super(softAdsItemHolder, view);
        this.b = softAdsItemHolder;
        softAdsItemHolder.postMemberView = (PostMemberView) b.b(view, R.id.post_member_view, "field 'postMemberView'", PostMemberView.class);
    }

    public void a() {
        SoftAdsItemHolder softAdsItemHolder = this.b;
        if (softAdsItemHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        softAdsItemHolder.postMemberView = null;
        super.a();
    }
}
