package qsbk.app.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.video.VideoInListHelper;

class bj extends ClickableSpan {
    final /* synthetic */ AtInfo a;
    final /* synthetic */ CircleVideoCell b;

    bj(CircleVideoCell circleVideoCell, AtInfo atInfo) {
        this.b = circleVideoCell;
        this.a = atInfo;
    }

    public void onClick(View view) {
        if (view.getTag() == null || !((String) view.getTag()).equals("Test")) {
            if (SingleArticleAdapter.shouldSetTagWhenSetClickSpanForTextView()) {
                view.setTag(VideoInListHelper.TAG);
            }
            if (view.getTag() == null) {
                view.setTag("click");
            }
            MyInfoActivity.launch(view.getContext(), this.a.uid, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(8, this.a.uid, this.b.article.id));
            return;
        }
        view.setTag(null);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
