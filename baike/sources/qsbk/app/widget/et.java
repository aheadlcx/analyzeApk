package qsbk.app.widget;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class et implements OnClickListener {
    final /* synthetic */ SupportOrNotView a;

    et(SupportOrNotView supportOrNotView) {
        this.a = supportOrNotView;
    }

    public void onClick(View view) {
        this.a.unSupport.setEnabled(false);
        this.a.unSupport.setClickable(false);
        this.a.unSupport.setHighlighted(true);
        this.a.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.not_funny_shake));
        ((AnimationDrawable) this.a.unSupport.getDrawable()).start();
        if (this.a.support.isHighlighted()) {
            this.a.support.setEnabled(true);
            this.a.support.setHighlighted(false);
            this.a.support.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_support_night : R.drawable.operation_support));
            this.a.support.setClickable(true);
            SupportOrNotView.a(this.a).onUnSupportSelect(view, true);
            return;
        }
        SupportOrNotView.a(this.a).onUnSupportSelect(view, false);
    }
}
