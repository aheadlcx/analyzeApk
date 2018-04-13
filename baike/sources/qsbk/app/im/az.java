package qsbk.app.im;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import qsbk.app.im.ChatListAdapter.TxtViewShower;

class az implements OnLongClickListener {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ TxtViewShower b;

    az(TxtViewShower txtViewShower, ChatMsg chatMsg) {
        this.b = txtViewShower;
        this.a = chatMsg;
    }

    public boolean onLongClick(View view) {
        view.setTag("Test");
        String charSequence = ((TextView) view).getText().toString();
        if (this.b.a.h instanceof ConversationActivity) {
            ((ConversationActivity) this.b.a.h).onLongClickDialog(charSequence, this.a.dbid);
        } else if (this.b.a.h instanceof GroupConversationActivity) {
            ((GroupConversationActivity) this.b.a.h).onLongClickDialog(charSequence, this.a.dbid);
        }
        return true;
    }
}
