package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class cj implements OnClickListener {
    final /* synthetic */ ConversationActivity a;

    cj(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onClick(View view) {
        if (!this.a.isTemporary) {
            switch (cr.a[this.a.mRelationship.ordinal()]) {
                case 5:
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "对方回应后才可发送...", Integer.valueOf(0)).show();
                    return;
            }
        }
        String content = this.a.getContent();
        if (!TextUtils.isEmpty(content)) {
            if (content.length() > 3500) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您输入的内容太长，最多不超过3500个字符", Integer.valueOf(0)).show();
            } else if (this.a.a(content)) {
                this.a.setContentWithoutSendingTypingStatus("");
            } else {
                LogUtil.d("get to id:" + this.a.getToId());
                LogUtil.d("get to id:" + content);
                ChatMsg sendTo = this.a.z.sendTo(this.a.newContactItem(), content, this.a.s, true);
                if (sendTo != null) {
                    this.a.g.appendItem(sendTo);
                    this.a.g.moveSendingToLast();
                    this.a.g.notifyDataSetChanged();
                    this.a.y.postDelayed(new ck(this), 200);
                    if (!this.a.isTemporary) {
                        this.a.s = null;
                    } else if (this.a.s != null) {
                        this.a.s.valueObj.group_name = null;
                    }
                }
                this.a.b = true;
                if (this.a.getUserType() == 1 && this.a.getToId().equals("32879940")) {
                    this.a.x.setSendVoiceButtonVisibility(false);
                } else {
                    this.a.x.setSendVoiceButtonVisibility(true);
                }
                this.a.f.setVisibility(8);
                this.a.setContentWithoutSendingTypingStatus("");
                this.a.M();
            }
        }
    }
}
