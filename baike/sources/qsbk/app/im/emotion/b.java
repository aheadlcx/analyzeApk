package qsbk.app.im.emotion;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.utils.image.issue.Logger;

class b implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ChatMsgEmotionData b;
    final /* synthetic */ a c;

    b(a aVar, int i, ChatMsgEmotionData chatMsgEmotionData) {
        this.c = aVar;
        this.a = i;
        this.b = chatMsgEmotionData;
    }

    public void onClick(View view) {
        Logger.getInstance().debug(EmotionGridView.a, "onItemClick", String.format("onItemClick position: %d, id: %d, data: %s", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.c.a.getId()), this.b}));
        if (this.c.a.e != null) {
            this.c.a.e.onEmotionItemClick(this.a, this.b);
        }
    }
}
