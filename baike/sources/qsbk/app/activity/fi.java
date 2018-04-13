package qsbk.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import java.util.Iterator;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.share.message.ChooseQiuyouActivity;
import qsbk.app.utils.UIHelper;

class fi implements TextWatcher {
    final /* synthetic */ CircleArticleActivity a;

    fi(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i3 == 1 && charSequence.charAt(i) == '@') {
            this.a.ab = i;
            this.a.s();
            Intent intent = new Intent(this.a, ChooseQiuyouActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("fromAt", true);
            intent.putExtras(bundle);
            this.a.startActivityForResult(intent, 87);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence instanceof Spannable) {
            Iterator it = this.a.aa.iterator();
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

    public void afterTextChanged(Editable editable) {
        if (editable.length() > 120) {
            this.a.j.setText((140 - editable.length()) + "");
            this.a.j.setVisibility(0);
            if (editable.length() > 140) {
                if (UIHelper.isNightTheme()) {
                    this.a.j.setTextColor(Color.parseColor("#ad4d30"));
                    return;
                } else {
                    this.a.j.setTextColor(Color.parseColor("#f86f45"));
                    return;
                }
            } else if (UIHelper.isNightTheme()) {
                this.a.j.setTextColor(Color.parseColor("#6a6c7e"));
                return;
            } else {
                this.a.j.setTextColor(Color.parseColor("#969696"));
                return;
            }
        }
        this.a.j.setVisibility(8);
    }
}
