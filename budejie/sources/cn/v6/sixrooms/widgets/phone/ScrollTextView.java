package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;

public class ScrollTextView extends TextView {
    public ScrollTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollTextView(Context context) {
        super(context);
    }

    @ExportedProperty(category = "focus")
    public boolean isFocused() {
        return true;
    }
}
