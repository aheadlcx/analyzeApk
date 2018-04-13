package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.baidu.mobstat.Config;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupActionUtil;
import qsbk.app.utils.UserClickDelegate;

class ej implements OnClickListener {
    final /* synthetic */ int[] a;
    final /* synthetic */ BaseUserInfo b;
    final /* synthetic */ BaseUserInfo c;
    final /* synthetic */ GroupConversationActivity d;

    ej(GroupConversationActivity groupConversationActivity, int[] iArr, BaseUserInfo baseUserInfo, BaseUserInfo baseUserInfo2) {
        this.d = groupConversationActivity;
        this.a = iArr;
        this.b = baseUserInfo;
        this.c = baseUserInfo2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a[i]) {
            case 1:
                GroupActionUtil.muteMemberIfConfirm(this.d, Integer.valueOf(this.d.getToId()).intValue(), this.b.userId, new ek(this, this.d, "处理中...", false));
                return;
            case 2:
                GroupActionUtil.unmuteMember(Integer.valueOf(this.d.getToId()).intValue(), this.b.userId, new el(this, this.d, "处理中..."));
                return;
            case 3:
                GroupActionUtil.deleteMemberIfConfirm(this.d, Integer.valueOf(this.d.getToId()).intValue(), this.b.userId, this.b.userName, new em(this, this.d, "处理中...", false));
                return;
            case 4:
                GroupActionUtil.appointAdminIfConfirm(this.d, Integer.valueOf(this.d.getToId()).intValue(), this.b.userId, this.b.userName, new en(this, this.d, "处理中...", false));
                return;
            case 5:
                GroupActionUtil.firedAdminIfConfirm(this.d, Integer.valueOf(this.d.getToId()).intValue(), this.b.userId, this.b.userName, new eo(this, this.d, "处理中...", false));
                return;
            case 6:
                if (UserClickDelegate.isOfficialAccount(this.b.userId)) {
                    OfficialInfoActivity.launch(this.d, this.b.userId, this.b.userName, this.b.userIcon);
                    return;
                }
                MyInfoActivity.launch(this.d, this.b.userId, this.d.getToId(), this.d.getToNick(), MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, String.valueOf(this.d.au.ownerId), String.valueOf(this.d.getToId()) + Config.TRACE_TODAY_VISIT_SPLIT + this.d.getToNick()));
                return;
            case 7:
                Intent intent = new Intent(this.d, ConversationActivity.class);
                intent.putExtra("user_id", QsbkApp.currentUser.userId);
                intent.putExtra("to_id", this.b.userId);
                intent.putExtra(IMChatBaseActivity.TO_ICON, this.b.userIcon);
                intent.putExtra(IMChatBaseActivity.TO_NICK, this.b.userName);
                intent.putExtra(ConversationActivity.RELATIONSHIP, this.b.relationship);
                intent.putExtra("source", new IMChatMsgSource(7, this.b.userId, String.valueOf(this.d.getToId()) + Config.TRACE_TODAY_VISIT_SPLIT + this.d.getToNick()).encodeToJsonObject().toString());
                intent.putExtra(ConversationActivity.TEMPORARY, true);
                this.d.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
