package qsbk.app.adapter;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Animation;

class as implements OnClickListener {
    final /* synthetic */ b a;
    private Animation b;
    private int c;
    private int d;

    as(b bVar) {
        this.a = bVar;
    }

    private void a() {
        if (this.b == null) {
            this.b = new at(this);
        }
    }

    public void onClick(View view) {
        if (this.a.l.getVisibility() == 0) {
            a();
            this.a.m = !this.a.m;
            int height = this.a.k.getHeight();
            if (this.a.m) {
                this.a.l.setText("收起");
                this.a.k.setMaxLines(Integer.MAX_VALUE);
                this.a.k.clearAnimation();
                this.a.k.measure(MeasureSpec.makeMeasureSpec(this.a.k.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
                this.c = height;
                this.d = this.a.k.getMeasuredHeight();
                this.a.k.setHeight(this.c);
                this.b.setDuration((long) (Math.abs(this.d - this.c) + 150));
                this.a.k.startAnimation(this.b);
                return;
            }
            this.a.l.setText("展开更多");
            this.d = (this.a.k.getLayout().getLineBottom(3) + this.a.k.getPaddingTop()) + this.a.k.getPaddingBottom();
            this.c = height;
            this.a.k.clearAnimation();
            this.b.setDuration((long) (Math.abs(this.d - this.c) + 150));
            this.a.k.startAnimation(this.b);
        }
    }
}
