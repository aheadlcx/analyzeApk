package cn.xiaochuankeji.tieba.ui.widget.overscroll;

import android.view.MotionEvent;
import android.view.View;

public class f extends e {

    private static class a extends a {
        a() {
            this.a = View.TRANSLATION_Y;
        }

        protected void a(View view) {
            this.b = view.getTranslationY();
            this.c = (float) view.getHeight();
        }
    }

    private static class b extends e {
        private b() {
        }

        public boolean a(View view, MotionEvent motionEvent) {
            boolean z = false;
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.a = view.getTranslationY();
            this.b = y;
            if (this.b > 0.0f) {
                z = true;
            }
            this.c = z;
            return true;
        }
    }

    public /* bridge */ /* synthetic */ View a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ void a(c cVar) {
        super.a(cVar);
    }

    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public /* bridge */ /* synthetic */ boolean onTouch(View view, MotionEvent motionEvent) {
        return super.onTouch(view, motionEvent);
    }

    public f(cn.xiaochuankeji.tieba.ui.widget.overscroll.a.a aVar) {
        this(aVar, 3.0f, 1.0f, -2.0f);
    }

    private f(cn.xiaochuankeji.tieba.ui.widget.overscroll.a.a aVar, float f, float f2, float f3) {
        super(aVar, f3, f, f2);
    }

    protected e c() {
        return new b();
    }

    protected a d() {
        return new a();
    }

    protected void a(View view, float f) {
        view.setTranslationY(f);
    }

    protected void a(View view, float f, MotionEvent motionEvent) {
        view.setTranslationY(f);
        motionEvent.offsetLocation(f - motionEvent.getY(0), 0.0f);
    }
}
