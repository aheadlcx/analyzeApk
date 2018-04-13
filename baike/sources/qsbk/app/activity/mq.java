package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.GroupConversationActivity;
import qsbk.app.im.IMChatBaseActivity;

class mq implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    mq(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.E) {
            this.a.finish();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.a.getApplicationContext(), GroupConversationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(IMChatBaseActivity.USER_TYPE, 3);
        bundle.putString("user_id", QsbkApp.currentUser.userId);
        bundle.putString("to_id", String.valueOf(this.a.b.id));
        bundle.putString(IMChatBaseActivity.TO_ICON, this.a.b.icon);
        bundle.putString(IMChatBaseActivity.TO_NICK, this.a.b.name);
        bundle.putInt("groupOwnerId", this.a.b.ownerId);
        bundle.putBoolean("from_group_info", true);
        bundle.putSerializable("group_info", this.a.b);
        intent.putExtras(bundle);
        this.a.startActivity(intent);
    }
}
