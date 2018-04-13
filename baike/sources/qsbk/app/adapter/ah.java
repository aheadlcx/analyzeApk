package qsbk.app.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.CircleComment;
import qsbk.app.video.VideoInListHelper;

class ah extends ClickableSpan {
    final /* synthetic */ AtInfo a;
    final /* synthetic */ CircleComment b;
    final /* synthetic */ CircleCommentAdapter c;

    ah(CircleCommentAdapter circleCommentAdapter, AtInfo atInfo, CircleComment circleComment) {
        this.c = circleCommentAdapter;
        this.a = atInfo;
        this.b = circleComment;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
            return;
        }
        view.setTag(VideoInListHelper.TAG);
        MyInfoActivity.launch(view.getContext(), this.a.uid, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(8, this.a.uid, this.b.id));
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
