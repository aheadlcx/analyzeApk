package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

class aj extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ai b;

    aj(ai aiVar, ImageView imageView) {
        this.b = aiVar;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        ScaleType scaleType = (ScaleType) this.a.getTag(R.id.save_scale_type);
        this.a.setScaleType(scaleType);
        this.a.setTag(R.id.save_scale_type, null);
        if (scaleType == ScaleType.MATRIX) {
            this.a.setImageMatrix((Matrix) this.a.getTag(R.id.save_image_matrix));
            this.a.setTag(R.id.save_image_matrix, null);
        }
        animator.removeListener(this);
    }
}
