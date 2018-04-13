package qsbk.app.im;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

class dc implements OnClickListener {
    final /* synthetic */ ConversationTitleBar a;

    dc(ConversationTitleBar conversationTitleBar) {
        this.a = conversationTitleBar;
    }

    public void onClick(View view) {
        if (this.a.getContext() instanceof Activity) {
            ((Activity) this.a.getContext()).finish();
        }
    }
}
