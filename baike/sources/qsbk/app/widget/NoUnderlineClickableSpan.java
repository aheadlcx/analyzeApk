package qsbk.app.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class NoUnderlineClickableSpan extends ClickableSpan {
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
