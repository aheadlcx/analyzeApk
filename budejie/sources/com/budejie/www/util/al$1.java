package com.budejie.www.util;

import android.view.MotionEvent;
import com.budejie.www.util.al.b;

class al$1 implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ MotionEvent b;
    final /* synthetic */ al c;

    al$1(al alVar, b bVar, MotionEvent motionEvent) {
        this.c = alVar;
        this.a = bVar;
        this.b = motionEvent;
    }

    public void run() {
        if (!al.a(this.c)) {
            if (((al.c(this.c).getHeight() / 2) - ((int) this.b.getY())) % al.c(this.c).getHeight() == 0) {
                al.e(this.c).add(al.d(this.c));
            }
            this.b.setAction(2);
            int height = al.c(this.c).getHeight() - (((al.c(this.c).getHeight() / 2) - ((int) this.b.getY())) % al.c(this.c).getHeight());
            if (height >= 6 || height <= 0) {
                aa.b("ScrollableViewRECUtil", "setLocation=" + (((int) this.b.getY()) - 6));
                this.b.setLocation((float) ((int) this.b.getX()), (float) (((int) this.b.getY()) - 6));
            } else {
                aa.b("ScrollableViewRECUtil", "setLocation=" + (((int) this.b.getY()) - height));
                this.b.setLocation((float) ((int) this.b.getX()), (float) (((int) this.b.getY()) - height));
            }
            al.c(this.c).dispatchTouchEvent(this.b);
            if (al.c(this.c).getHeight() + ((al.c(this.c).getHeight() / 2) - ((int) this.b.getY())) > al.f(this.c)) {
                al.a(this.c, true);
            }
            al.c(this.c).postDelayed(this, 2);
        } else if (!al.b(this.c)) {
            new al$a(this.c).execute(new Object[]{this.b});
        } else if (this.a != null) {
            this.a.a("");
        }
    }
}
