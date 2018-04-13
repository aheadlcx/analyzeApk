package qsbk.app.activity.security;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import qsbk.app.R;

class e implements OnClickListener {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    e(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.v)) {
            this.a.updateUserInfo(this.a.d.obtainMessage(), this.a.a);
        } else if (this.a.u) {
            this.a.n();
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.a, R.anim.shake);
            this.a.i.startAnimation(loadAnimation);
            this.a.j.startAnimation(AnimationUtils.loadAnimation(this.a, R.anim.shake2));
            this.a.k.startAnimation(loadAnimation);
        }
    }
}
