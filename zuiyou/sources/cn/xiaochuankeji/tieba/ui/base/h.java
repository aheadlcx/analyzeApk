package cn.xiaochuankeji.tieba.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar.a;

public abstract class h extends a implements a {
    protected NavigationBar a;
    protected View b;
    protected boolean c;

    protected void f() {
        if (findViewById(R.id.navBar) != null) {
            this.a = (NavigationBar) findViewById(R.id.navBar);
        }
        this.b = findViewById(R.id.rootView);
        j();
        p();
    }

    protected void e() {
    }

    protected void p() {
        final int a = e.a(150.0f);
        if (this.b != null && !(this instanceof UgcVideoActivity)) {
            this.b.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ h b;

                public void onGlobalLayout() {
                    boolean z = true;
                    if (!ViewCompat.getFitsSystemWindows(this.b.b) || this.b.b.getPaddingBottom() <= 0) {
                        if (this.b.b.getRootView().getHeight() - this.b.b.getHeight() <= a) {
                            z = false;
                        }
                    } else if (this.b.b.getPaddingBottom() <= a) {
                        z = false;
                    }
                    if (this.b.c != z) {
                        this.b.c = z;
                        this.b.b(this.b.c);
                    }
                }
            });
        }
    }

    public boolean q() {
        return this.c;
    }

    public void a(boolean z) {
        e();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        cleanView(findViewById(R.id.rootView));
        System.gc();
    }

    public static void cleanView(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (cn.htjyb.ui.a.class.isInstance(view)) {
                ((cn.htjyb.ui.a) view).c();
            }
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    cleanView(((ViewGroup) view).getChildAt(i));
                }
            }
        }
    }

    private void j() {
        if (this.a != null) {
            this.a.bringToFront();
            this.a.setListener(this);
        }
    }

    public void b(boolean z) {
    }

    public void r() {
        cn.htjyb.c.a.a((Activity) this);
        onBackPressed();
    }

    public void s() {
    }

    public void t() {
    }

    public void u() {
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
}
