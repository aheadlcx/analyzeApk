package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;

public class TaleCommentTextHolder extends b {
    @BindView
    AppCompatTextView comment_holder;

    public TaleCommentTextHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.comment_holder.setText(taleComment.detail.commentCnt < 1 ? "" : "评论");
        this.comment_holder.setHeight((int) TypedValue.applyDimension(1, taleComment.detail.commentCnt < 1 ? 8.0f : 44.0f, this.comment_holder.getResources().getDisplayMetrics()));
    }
}
