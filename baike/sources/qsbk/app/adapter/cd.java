package qsbk.app.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.Article;

class cd extends ClickableSpan {
    final /* synthetic */ Article a;
    final /* synthetic */ ManageMyContentsAdapter b;

    cd(ManageMyContentsAdapter manageMyContentsAdapter, Article article) {
        this.b = manageMyContentsAdapter;
        this.a = article;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(view.getContext(), this.a.qiushiTopic);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
