package qsbk.app.activity.publish;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import java.util.Iterator;
import qsbk.app.activity.CircleTopicsActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.share.message.ChooseQiuyouActivity;
import qsbk.app.utils.UIHelper;

class n implements TextWatcher {
    int a;
    int b;
    int c;
    final /* synthetic */ CirclePublishActivity d;

    n(CirclePublishActivity circlePublishActivity) {
        this.d = circlePublishActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        if (charSequence instanceof Spannable) {
            Iterator it = this.d.P.iterator();
            while (it.hasNext()) {
                AtInfo atInfo = (AtInfo) it.next();
                int spanStart = ((Spannable) charSequence).getSpanStart(atInfo.span);
                int spanEnd = ((Spannable) charSequence).getSpanEnd(atInfo.span);
                if (spanStart >= i && spanEnd <= i + i2 && i2 > 0) {
                    ((Spannable) charSequence).removeSpan(charSequence);
                    it.remove();
                }
            }
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i3 == 1 && charSequence.charAt(i) == '@') {
            this.d.Q = i;
            UIHelper.hideKeyboard(this.d);
            Intent intent = new Intent(this.d, ChooseQiuyouActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("fromAt", true);
            intent.putExtras(bundle);
            this.d.startActivityForResult(intent, 87);
        }
    }

    public void afterTextChanged(Editable editable) {
        if (this.b != 0 || this.c != 1 || editable.charAt(this.a) != '#' || this.d.b.getFirstText() != null) {
            return;
        }
        if (!this.d.I || this.d.E == null || this.d.E.circleArticle == null) {
            CircleTopicsActivity.launchForResult(this.d);
        } else {
            CircleTopicsActivity.launchForResult(this.d, true);
        }
    }
}
