package qsbk.app.widget;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class es implements OnClickListener {
    final /* synthetic */ SupportOrNotView a;

    es(SupportOrNotView supportOrNotView) {
        this.a = supportOrNotView;
    }

    public void onClick(View view) {
        this.a.support.setEnabled(false);
        this.a.support.setClickable(false);
        this.a.support.setHighlighted(true);
        this.a.support.setImageDrawable(UIHelper.getDrawable(R.drawable.funny_shake));
        ((AnimationDrawable) this.a.support.getDrawable()).start();
        if (this.a.unSupport.isHighlighted()) {
            this.a.unSupport.setEnabled(true);
            this.a.unSupport.setHighlighted(false);
            this.a.unSupport.setImageDrawable(UIHelper.getDrawable(UIHelper.isNightTheme() ? R.drawable.operation_unsupport_night : R.drawable.operation_unsupport));
            this.a.unSupport.setClickable(true);
            SupportOrNotView.a(this.a).onSupportSelect(view, true);
            return;
        }
        SupportOrNotView.a(this.a).onSupportSelect(view, false);
    }
}
