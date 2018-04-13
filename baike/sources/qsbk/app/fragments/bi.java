package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextWatcher;

class bi implements TextWatcher {
    final /* synthetic */ CircleTopicsFragment a;

    bi(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            CircleTopicsFragment.f(this.a).setVisibility(0);
            CircleTopicsFragment.a(this.a).setVisibility(8);
            return;
        }
        CircleTopicsFragment.f(this.a).setVisibility(8);
        CircleTopicsFragment.a(this.a).setVisibility(0);
    }

    public void afterTextChanged(Editable editable) {
        if (editable.length() == 0) {
            if (CircleTopicsFragment.g(this.a) != null) {
                CircleTopicsFragment.g(this.a).cancel(true);
                CircleTopicsFragment.a(this.a, null);
            }
            CircleTopicsFragment.a(this.a, "");
            CircleTopicsFragment.a(this.a, false);
            CircleTopicsFragment.d(this.a).setLoadMoreEnable(CircleTopicsFragment.h(this.a));
            CircleTopicsFragment.i(this.a);
        }
    }
}
