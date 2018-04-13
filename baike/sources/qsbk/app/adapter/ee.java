package qsbk.app.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class ee extends ClickableSpan {
    final /* synthetic */ VideoImmersionCell a;

    ee(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(view.getContext(), this.a.article.qiushiTopic);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
