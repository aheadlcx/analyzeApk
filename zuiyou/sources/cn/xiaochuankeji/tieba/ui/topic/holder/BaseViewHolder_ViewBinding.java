package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostGodReview;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;

public class BaseViewHolder_ViewBinding implements Unbinder {
    private BaseViewHolder b;

    @UiThread
    public BaseViewHolder_ViewBinding(BaseViewHolder baseViewHolder, View view) {
        this.b = baseViewHolder;
        baseViewHolder.postMemberView = (PostMemberView) b.b(view, R.id.post_member_view, "field 'postMemberView'", PostMemberView.class);
        baseViewHolder.postGodReview = (PostGodReview) b.b(view, R.id.post_god_review, "field 'postGodReview'", PostGodReview.class);
        baseViewHolder.operateView = (PostOperateView) b.b(view, R.id.post_operate_view, "field 'operateView'", PostOperateView.class);
    }

    @CallSuper
    public void a() {
        BaseViewHolder baseViewHolder = this.b;
        if (baseViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        baseViewHolder.postMemberView = null;
        baseViewHolder.postGodReview = null;
        baseViewHolder.operateView = null;
    }
}
