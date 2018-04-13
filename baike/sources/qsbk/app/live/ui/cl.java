package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.live.model.LiveGiftZhouxingMessage;
import qsbk.app.live.model.LiveMessage;

class cl implements OnClickListener {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    cl(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void onClick(View view) {
        if (!this.b.isAnchor()) {
            WebActivity.launch(this.b, ((LiveGiftZhouxingMessage) this.a).getUrl());
        }
    }
}
