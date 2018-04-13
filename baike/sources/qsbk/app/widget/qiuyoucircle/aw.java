package qsbk.app.widget.qiuyoucircle;

import android.app.Activity;
import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;
import qsbk.app.video.VideoInListHelper;

class aw extends ClickableSpan {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ NormalCell b;

    aw(NormalCell normalCell, CircleArticle circleArticle) {
        this.b = normalCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            Context activityOrContext = Util.getActivityOrContext(view);
            if (this.b.fromCircleTopic && (activityOrContext instanceof CircleArticleActivity)) {
                ((Activity) activityOrContext).finish();
                return;
            } else {
                CircleTopicActivity.launch(activityOrContext, this.a.topic, -1);
                return;
            }
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
