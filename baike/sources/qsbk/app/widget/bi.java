package qsbk.app.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.utils.Util;
import qsbk.app.video.VideoInListHelper;

class bi extends ClickableSpan {
    final /* synthetic */ CircleVideoCell a;

    bi(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            CircleTopicActivity.launch(Util.getActivityOrContext(view), this.a.article.topic, -1);
            return;
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
