package qsbk.app.im;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import java.util.Iterator;
import qsbk.app.activity.MemberChooseActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.UIHelper;

class fb implements TextWatcher {
    final /* synthetic */ GroupConversationActivity a;

    fb(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence instanceof Spannable) {
            Iterator it = this.a.az.iterator();
            while (it.hasNext()) {
                AtInfo atInfo = (AtInfo) it.next();
                int spanStart = ((Spannable) charSequence).getSpanStart(atInfo.span);
                int spanEnd = ((Spannable) charSequence).getSpanEnd(atInfo.span);
                if (spanStart >= i && spanEnd <= i + i2 && i2 > 0) {
                    ((Spannable) charSequence).removeSpan(Integer.valueOf(spanStart));
                    it.remove();
                }
            }
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i3 == 1 && charSequence.charAt(i) == '@' && this.a.az.size() < 5) {
            this.a.aA = i;
            UIHelper.hideKeyboard(this.a);
            GroupInfo e = this.a.au;
            if (e == null) {
                e = new GroupInfo();
                e.id = Integer.valueOf(this.a.getToId()).intValue();
            }
            MemberChooseActivity.launchForResult(this.a, e, 10);
        }
        this.a.Q();
    }

    public void afterTextChanged(Editable editable) {
        if (editable == null || TextUtils.isEmpty(editable.toString())) {
            this.a.f.setEnabled(false);
            this.a.f.setVisibility(8);
            this.a.x.setSendVoiceButtonVisibility(true);
            return;
        }
        this.a.f.setEnabled(true);
        this.a.f.setVisibility(0);
        this.a.x.setSendVoiceButtonVisibility(false);
    }
}
