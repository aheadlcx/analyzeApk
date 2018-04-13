package qsbk.app.im;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

class ak implements OnItemLongClickListener {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ z b;

    ak(z zVar, ChatMsg chatMsg) {
        this.b = zVar;
        this.a = chatMsg;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a != null) {
            if (view.getContext() instanceof ConversationActivity) {
                ((ConversationActivity) view.getContext()).onRichMediaContentLongClick(this.a.dbid, this.a);
            } else if (view.getContext() instanceof GroupConversationActivity) {
                ((GroupConversationActivity) view.getContext()).onRichMediaContentLongClick(this.a.dbid, this.a);
            }
        }
        return true;
    }
}
