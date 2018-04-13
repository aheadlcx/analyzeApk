package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.LiveRoom;

class qz implements OnClickListener {
    final /* synthetic */ LiveRoom a;
    final /* synthetic */ View b;
    final /* synthetic */ a c;

    qz(a aVar, LiveRoom liveRoom, View view) {
        this.c = aVar;
        this.a = liveRoom;
        this.b = view;
    }

    public void onClick(View view) {
        if (this.a.isLiveBegin()) {
            AppUtils.getInstance().getUserInfoProvider().toLive(this.c.a, this.a.author.id, this.a.author.id, this.a.author.origin);
        } else if (this.a.author != null) {
            MyInfoActivity.launch(this.c.a, this.a.author.platform_id + "", MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, this.a.author.platform_id + "", "来自直播"));
        }
        this.b.setEnabled(false);
        this.b.postDelayed(new ra(this), 1000);
    }
}
