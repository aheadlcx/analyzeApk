package qsbk.app.ad.feedsad.qbad;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;
import qsbk.app.video.VideoInListHelper;

class j extends ClickableSpan {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ QbAdItem b;

    j(QbAdItem qbAdItem, CircleArticle circleArticle) {
        this.b = qbAdItem;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            CircleTopicActivity.launch(Util.getActivityOrContext(view), this.a.topic, -1);
            return;
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
