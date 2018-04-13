package qsbk.app.ad.feedsad.qbad;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.CircleArticle;
import qsbk.app.video.VideoInListHelper;

class k extends ClickableSpan {
    final /* synthetic */ AtInfo a;
    final /* synthetic */ CircleArticle b;
    final /* synthetic */ QbAdItem c;

    k(QbAdItem qbAdItem, AtInfo atInfo, CircleArticle circleArticle) {
        this.c = qbAdItem;
        this.a = atInfo;
        this.b = circleArticle;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            if (view.getTag() == null) {
                view.setTag("click");
            }
            MyInfoActivity.launch(view.getContext(), this.a.uid, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(8, this.a.uid, this.b.id));
            return;
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
