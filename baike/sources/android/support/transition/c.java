package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class c implements d {
    c() {
    }

    public void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter animatorListenerAdapter) {
        animator.addPauseListener(animatorListenerAdapter);
    }

    public void pause(@NonNull Animator animator) {
        animator.pause();
    }

    public void resume(@NonNull Animator animator) {
        animator.resume();
    }
}
