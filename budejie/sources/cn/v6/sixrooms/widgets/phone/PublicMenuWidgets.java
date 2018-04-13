package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.LogUtils;

public class PublicMenuWidgets extends RelativeLayout {
    public static final String TAG = PublicMenuWidgets.class.getSimpleName();
    private RelativeLayout a;
    private boolean b;

    public PublicMenuWidgets(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PublicMenuWidgets(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        LogUtils.i(TAG, "MotionEvent event");
        if (this.a.getVisibility() != 0 || this.b) {
            return false;
        }
        this.b = true;
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.public_menu_out);
        loadAnimation.setAnimationListener(new af(this));
        this.a.startAnimation(loadAnimation);
        return true;
    }

    protected void onFinishInflate() {
        this.a = (RelativeLayout) findViewById(R.id.public_menu_wrapper);
    }
}
