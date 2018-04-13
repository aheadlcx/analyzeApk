package qsbk.app.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.QsbkApp;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.LoginPermissionClickDelegate;

class xi implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    xi(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
            return;
        }
        Intent intent = new Intent(this.a, ConversationActivity.class);
        intent.putExtra("user_id", QsbkApp.currentUser.userId);
        intent.putExtra("to_id", this.a.bF.userId);
        intent.putExtra(IMChatBaseActivity.TO_ICON, this.a.bF.userIcon);
        intent.putExtra(IMChatBaseActivity.TO_NICK, this.a.bF.userName);
        intent.putExtra(ConversationActivity.RELATIONSHIP, this.a.bF.relationship);
        if ((Relationship.NO_REL == this.a.bF.relationship || Relationship.NO_REL_CHATED == this.a.bF.relationship || Relationship.FOLLOW_UNREPLIED == this.a.bF.relationship) && this.a.bz != null) {
            intent.putExtra("source", new IMChatMsgSource(7, this.a.bF.userId, this.a.bz + Config.TRACE_TODAY_VISIT_SPLIT + this.a.bA).encodeToJsonObject().toString());
            intent.putExtra(ConversationActivity.TEMPORARY, true);
        } else if (!(TextUtils.isEmpty(this.a.bY) || this.a.bZ)) {
            intent.putExtra("source", this.a.bY);
        }
        this.a.startActivity(intent);
    }
}
