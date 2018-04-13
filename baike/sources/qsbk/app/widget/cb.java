package qsbk.app.widget;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class cb implements OnClickListener {
    final /* synthetic */ ImageControlView a;

    cb(ImageControlView imageControlView) {
        this.a = imageControlView;
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
            this.a.unSupport.setImageDrawable(UIHelper.getDrawable(R.drawable.operation_unsupport));
            this.a.unSupport.setClickable(true);
            ImageControlView.a(this.a).onSupportSelect(view, true);
            return;
        }
        ImageControlView.a(this.a).onSupportSelect(view, false);
    }
}
