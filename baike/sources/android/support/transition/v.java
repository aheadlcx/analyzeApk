package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.view.View;

class v extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ Matrix b;
    final /* synthetic */ View c;
    final /* synthetic */ c d;
    final /* synthetic */ b e;
    final /* synthetic */ ChangeTransform f;
    private boolean g;
    private Matrix h = new Matrix();

    v(ChangeTransform changeTransform, boolean z, Matrix matrix, View view, c cVar, b bVar) {
        this.f = changeTransform;
        this.a = z;
        this.b = matrix;
        this.c = view;
        this.d = cVar;
        this.e = bVar;
    }

    public void onAnimationCancel(Animator animator) {
        this.g = true;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.g) {
            if (this.a && this.f.k) {
                a(this.b);
            } else {
                this.c.setTag(R.id.transition_transform, null);
                this.c.setTag(R.id.parent_matrix, null);
            }
        }
        bz.c(this.c, null);
        this.d.restore(this.c);
    }

    public void onAnimationPause(Animator animator) {
        a(this.e.a());
    }

    public void onAnimationResume(Animator animator) {
        ChangeTransform.c(this.c);
    }

    private void a(Matrix matrix) {
        this.h.set(matrix);
        this.c.setTag(R.id.transition_transform, this.h);
        this.d.restore(this.c);
    }
}
