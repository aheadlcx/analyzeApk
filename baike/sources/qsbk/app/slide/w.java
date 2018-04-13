package qsbk.app.slide;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.Article;

class w extends ClickableSpan {
    final /* synthetic */ Article a;
    final /* synthetic */ SingleArticleFragment b;

    w(SingleArticleFragment singleArticleFragment, Article article) {
        this.b = singleArticleFragment;
        this.a = article;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(view.getContext(), this.a.qiushiTopic);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
