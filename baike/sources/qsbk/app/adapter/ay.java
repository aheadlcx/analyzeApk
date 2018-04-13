package qsbk.app.adapter;

import qsbk.app.R;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.qiuyoucircle.ShareCell;

class ay extends ShareCell {
    final /* synthetic */ CircleHotCommentAdapter a;

    ay(CircleHotCommentAdapter circleHotCommentAdapter, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        this.a = circleHotCommentAdapter;
        super(shareUtils$OnCircleShareStartListener, z);
    }

    public int getLayoutId() {
        return R.layout.cell_qiuyoucircle_share_hot;
    }

    public void onUpdate() {
        super.onUpdate();
        CircleArticle circleArticle = (CircleArticle) getItem();
        if (this.hotComment == null) {
            return;
        }
        if (circleArticle.hotComment == null || this.isDetail) {
            this.hotCommentLabel.setVisibility(8);
            this.hotComment.setVisibility(8);
            this.hotCommentImage.setVisibility(8);
            return;
        }
        CharSequence charSequence;
        this.hotComment.setOnClickListener(new az(this));
        this.hotCommentLabel.setVisibility(0);
        this.hotCommentLabel.setImageResource(R.drawable.ic_my_hot_comment);
        this.hotComment.setVisibility(0);
        this.hotComment.setTextColor(UIHelper.isNightTheme() ? -9802626 : -9474193);
        String format = String.format("      评论了：%s", new Object[]{circleArticle.hotComment.content});
        if (circleArticle.hotComment.smallImage != null) {
            charSequence = format + "[图片]";
        } else {
            Object obj = format;
        }
        this.hotComment.setText(charSequence);
        this.hotCommentImage.setVisibility(8);
    }
}
