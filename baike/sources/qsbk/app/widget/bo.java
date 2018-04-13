package qsbk.app.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

class bo extends ClickableSpan {
    final /* synthetic */ DiggerBar a;

    bo(DiggerBar diggerBar) {
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
