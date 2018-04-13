package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.xiaomi.mipush.sdk.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class dz implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    dz(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        String stringBuilder;
        String trim;
        if (this.a.az.size() > 0) {
            int i;
            CharSequence text = this.a.G.getText();
            int[] iArr = new int[this.a.az.size()];
            StringBuilder stringBuilder2 = new StringBuilder();
            boolean z = true;
            for (i = 0; i < this.a.az.size(); i++) {
                AtInfo atInfo = (AtInfo) this.a.az.get(i);
                iArr[i] = text.getSpanStart(atInfo.span);
                if (z) {
                    z = false;
                } else {
                    stringBuilder2.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder2.append(atInfo.uid);
            }
            stringBuilder = stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder(text);
            for (i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                String str = ((AtInfo) this.a.az.get(i)).name;
                stringBuilder3.insert(i2 + 1, str);
                for (int i3 = 0; i3 < iArr.length; i3++) {
                    if (iArr[i3] > i2) {
                        iArr[i3] = iArr[i3] + str.length();
                    }
                }
            }
            trim = stringBuilder3.toString().trim();
        } else {
            trim = this.a.getContent();
            stringBuilder = null;
        }
        if (!TextUtils.isEmpty(trim)) {
            if (trim.length() > 3500) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您输入的内容太长，最多不超过3500个字符", Integer.valueOf(0)).show();
            } else if (this.a.G()) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.H(), Integer.valueOf(0)).show();
            } else {
                LogUtil.d("get to id:" + this.a.getToId());
                LogUtil.d("get to id:" + trim);
                ChatMsg sendTo = this.a.z.sendTo(this.a.newContactItem(), trim, stringBuilder, this.a.s, true);
                if (sendTo != null) {
                    this.a.g.appendItem(sendTo);
                    this.a.g.moveSendingToLast();
                    this.a.g.notifyDataSetChanged();
                    this.a.y.postDelayed(new ea(this), 200);
                    this.a.s = null;
                }
                this.a.f.setEnabled(false);
                this.a.f.setVisibility(8);
                this.a.x.setSendVoiceButtonVisibility(true);
                this.a.setContentWithoutSendingTypingStatus("");
                this.a.O();
            }
        }
    }
}
