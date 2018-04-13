package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.ui.post.PostQueryListView;
import cn.xiaochuankeji.tieba.ui.topic.ReportedPostActivity.a;

public class ReportedPostQueryListView extends PostQueryListView {
    private a d;

    public ReportedPostQueryListView(Context context) {
        super(context);
    }

    public ReportedPostQueryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setUpdateListener(a aVar) {
        this.d = aVar;
    }

    public void message(MessageEvent messageEvent) {
        super.message(messageEvent);
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_DELETE && this.d != null) {
            this.d.a();
        }
    }
}
