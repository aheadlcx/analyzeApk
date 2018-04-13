package com.crashlytics.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.internal.aQ;

final class au implements Runnable {
    final /* synthetic */ az a;
    final /* synthetic */ Crashlytics b;
    private /* synthetic */ Activity c;
    private /* synthetic */ x d;
    private /* synthetic */ aQ e;

    au(Crashlytics crashlytics, Activity activity, az azVar, x xVar, aQ aQVar) {
        this.b = crashlytics;
        this.c = activity;
        this.a = azVar;
        this.d = xVar;
        this.e = aQVar;
    }

    public final void run() {
        Builder builder = new Builder(this.c);
        OnClickListener avVar = new av(this);
        float f = this.c.getResources().getDisplayMetrics().density;
        int a = ((int) (((float) 5) * f));
        View textView = new TextView(this.c);
        textView.setAutoLinkMask(15);
        textView.setText(this.d.b());
        textView.setTextAppearance(this.c, 16973892);
        textView.setPadding(a, a, a, a);
        textView.setFocusable(false);
        View scrollView = new ScrollView(this.c);
        scrollView.setPadding(((int) (((float) 14) * f)), ((int) (((float) 2) * f)), ((int) (((float) 10) * f)), ((int) (((float) 12) * f)));
        scrollView.addView(textView);
        builder.setView(scrollView).setTitle(this.d.a()).setCancelable(false).setNeutralButton(this.d.c(), avVar);
        if (this.e.d) {
            builder.setNegativeButton(this.d.e(), new aw(this));
        }
        if (this.e.f) {
            builder.setPositiveButton(this.d.d(), new ax(this));
        }
        builder.show();
    }
}
