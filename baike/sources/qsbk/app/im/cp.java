package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MyInfoActivity;

class cp implements OnClickListener {
    final /* synthetic */ ConversationActivity a;

    cp(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onClick(View view) {
        if (this.a.getUserType() == 1) {
            Intent intent = new Intent(this.a, OfficialInfoActivity.class);
            intent.putExtra("uid", this.a.getToId());
            intent.putExtra("name", this.a.getToNick());
            intent.putExtra("avatar", this.a.getToIcon());
            this.a.startActivityForResult(intent, 6);
            return;
        }
        MyInfoActivity.launch(this.a, this.a.getToId());
    }
}
