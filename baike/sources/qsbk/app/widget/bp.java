package qsbk.app.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

class bp extends ClickableSpan {
    final /* synthetic */ DiggerBar a;

    bp(DiggerBar diggerBar) {
        this.a = diggerBar;
    }

    public void onClick(View view) {
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        textPaint.setColor(DiggerBar.a(this.a));
    }
}
