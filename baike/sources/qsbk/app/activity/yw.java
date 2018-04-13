package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.utils.ToastAndDialog;

class yw implements OnClickListener {
    final /* synthetic */ OtherInfoActivity a;

    yw(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onClick(View view) {
        switch (zd.a[OtherInfoActivity.b(this.a).ordinal()]) {
            case 3:
                if (!OtherInfoActivity.e(this.a)) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "TA回应后才可以聊天呢", Integer.valueOf(0)).show();
                    return;
                }
                return;
            case 7:
                if (!OtherInfoActivity.e(this.a)) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "粉TA后才有机会聊天哦", Integer.valueOf(0)).show();
                    return;
                }
                return;
            default:
                Intent intent = new Intent(this.a, ConversationActivity.class);
                intent.putExtra("user_id", QsbkApp.currentUser.userId);
                intent.putExtra("to_id", OtherInfoActivity.a(this.a).userId);
                intent.putExtra(IMChatBaseActivity.TO_ICON, OtherInfoActivity.a(this.a).userIcon);
                intent.putExtra(IMChatBaseActivity.TO_NICK, OtherInfoActivity.a(this.a).userName);
                intent.putExtra(ConversationActivity.RELATIONSHIP, OtherInfoActivity.a(this.a).relationship);
                intent.putExtra("source", this.a.getIntent().getStringExtra("source"));
                this.a.startActivity(intent);
                return;
        }
    }
}
