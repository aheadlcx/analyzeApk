package qsbk.app.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.Article;

class l extends ClickableSpan {
    final /* synthetic */ Article a;
    final /* synthetic */ ArticleAdapter b;

    l(ArticleAdapter articleAdapter, Article article) {
        this.b = articleAdapter;
        this.a = article;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(view.getContext(), this.a.qiushiTopic);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
