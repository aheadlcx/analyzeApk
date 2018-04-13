package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import qsbk.app.im.group.vote.GroupManagerVoteActivity;

class ex implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ex(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.ax)) {
            this.a.S();
            Toast.makeText(this.a, "投票信息加载中, 请稍后再试", 0).show();
            return;
        }
        GroupManagerVoteActivity.launch(this.a, this.a.au.id, this.a.ax);
    }
}
