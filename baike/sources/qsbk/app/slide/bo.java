package qsbk.app.slide;

import qsbk.app.model.EventWindow;
import qsbk.app.share.QYQShareInfo;

enum bo {
    ARTICLE("article"),
    CIRCLE_TOPIC("circle_topic"),
    CIRCLE_VIDEO(EventWindow.JUMP_CIRCLE_VIDEO),
    QSJX(QYQShareInfo.TYPE_QSJX),
    NEWS(QYQShareInfo.TYPE_NEWS),
    LIVE_RECOMMEND("live_recommend");
    
    String a;

    private bo(String str) {
        this.a = str;
    }

    public String getTypeValue() {
        return this.a;
    }
}
