package qsbk.app.widget.qiuyoucircle;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.CircleArticle;
import qsbk.app.video.VideoInListHelper;

class ag extends ClickableSpan {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ForwardCell b;

    ag(ForwardCell forwardCell, CircleArticle circleArticle) {
        this.b = forwardCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            if (view.getTag() == null) {
                view.setTag("click");
            }
            MyInfoActivity.launch(view.getContext(), this.a.user.userId, MyInfoActivity.FANS_ORIGINS[2], new IMChatMsgSource(8, this.a.user.userId, this.a.id));
            return;
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
