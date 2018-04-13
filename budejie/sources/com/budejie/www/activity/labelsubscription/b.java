package com.budejie.www.activity.labelsubscription;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class b extends ClickableSpan {
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.getColor());
        textPaint.setUnderlineText(false);
    }
}
