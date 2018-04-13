package qsbk.app.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.Comment;
import qsbk.app.video.VideoInListHelper;

class df extends ClickableSpan {
    final /* synthetic */ AtInfo a;
    final /* synthetic */ Comment b;
    final /* synthetic */ SingleArticleAdapter c;

    df(SingleArticleAdapter singleArticleAdapter, AtInfo atInfo, Comment comment) {
        this.c = singleArticleAdapter;
        this.a = atInfo;
        this.b = comment;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
            return;
        }
        if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
            view.setTag(VideoInListHelper.TAG);
        }
        if (view.getTag() == null) {
            view.setTag("click");
        }
        MyInfoActivity.launch(this.c.k, this.a.uid, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(2, this.a.uid, this.b.id));
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
