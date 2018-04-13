package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

class CheckableWrapperView extends WrapperView implements Checkable {
    public CheckableWrapperView(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return ((Checkable) this.mItem).isChecked();
    }

    public void setChecked(boolean z) {
        ((Checkable) this.mItem).setChecked(z);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
