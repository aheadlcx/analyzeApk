package android.support.design.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bg implements OnClickListener {
    final /* synthetic */ TextInputLayout a;

    bg(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public void onClick(View view) {
        this.a.b(false);
    }
}
