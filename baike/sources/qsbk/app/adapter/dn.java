package qsbk.app.adapter;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Animation;

class dn implements OnClickListener {
    final /* synthetic */ b a;
    private Animation b;
    private int c;
    private int d;

    dn(b bVar) {
        this.a = bVar;
    }

    private void a() {
        if (this.b == null) {
            this.b = new do(this);
        }
    }

    public void onClick(View view) {
        if (this.a.m.getVisibility() == 0) {
            a();
            this.a.n = !this.a.n;
            int height = this.a.l.getHeight();
            if (this.a.n) {
                this.a.m.setText("收起");
                this.a.l.setMaxLines(Integer.MAX_VALUE);
                this.a.l.clearAnimation();
                this.a.l.measure(MeasureSpec.makeMeasureSpec(this.a.l.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
                this.c = height;
                this.d = this.a.l.getMeasuredHeight();
                this.a.l.setHeight(this.c);
                this.b.setDuration((long) (Math.abs(this.d - this.c) + 150));
                this.a.l.startAnimation(this.b);
                return;
            }
            this.a.m.setText("展开更多");
            this.d = (this.a.l.getLayout().getLineBottom(3) + this.a.l.getPaddingTop()) + this.a.l.getPaddingBottom();
            this.c = height;
            this.a.l.clearAnimation();
            this.b.setDuration((long) (Math.abs(this.d - this.c) + 150));
            this.a.l.startAnimation(this.b);
        }
    }
}
