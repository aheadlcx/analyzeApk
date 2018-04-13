package qsbk.app.pay.adapter;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.core.web.ui.WebActivity;

class e extends ClickableSpan {
    final /* synthetic */ String a;
    final /* synthetic */ DiamondAdapter b;

    e(DiamondAdapter diamondAdapter, String str) {
        this.b = diamondAdapter;
        this.a = str;
    }

    public void onClick(View view) {
        WebActivity.launch(this.b.b, this.b.g, this.a);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.linkColor);
        textPaint.setUnderlineText(false);
    }
}
