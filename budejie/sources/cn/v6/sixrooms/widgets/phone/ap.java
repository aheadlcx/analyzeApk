package cn.v6.sixrooms.widgets.phone;

import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import cn.v6.sixrooms.widgets.phone.SofaView.InnerClassSofaCrop;

final class ap implements AnimationListener {
    final /* synthetic */ String a;
    final /* synthetic */ InnerClassSofaCrop b;

    ap(InnerClassSofaCrop innerClassSofaCrop, String str) {
        this.b = innerClassSofaCrop;
        this.a = str;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.b.b.setImageURI(Uri.parse(this.a));
    }
}
