package qsbk.app.share;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class k implements OnGlobalLayoutListener {
    final /* synthetic */ NewShareActivity a;

    k(NewShareActivity newShareActivity) {
        this.a = newShareActivity;
    }

    public void onGlobalLayout() {
        if (VERSION.SDK_INT >= 16) {
            this.a.i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.a.i.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        if (this.a.b != null) {
            this.a.b.centerY();
            Rect rectOnScreen = UIHelper.getRectOnScreen(this.a.findViewById(R.id.content_container));
            Rect rect = new Rect();
            this.a.a.setArrowOffset(this.a.b.centerX() - this.a.a.getLeft());
            this.a.a.setArrowIsAbove(this.a.b.centerY() <= rectOnScreen.centerY());
            int measuredHeight;
            if (this.a.b.centerY() > rectOnScreen.centerY()) {
                measuredHeight = (this.a.b.top - this.a.a.getMeasuredHeight()) - rectOnScreen.top;
                rect.set(this.a.a.getLeft(), measuredHeight, this.a.a.getRight(), this.a.a.getMeasuredHeight() + measuredHeight);
            } else {
                measuredHeight = this.a.b.bottom - rectOnScreen.top;
                rect.set(this.a.a.getLeft(), measuredHeight, this.a.a.getRight(), this.a.a.getMeasuredHeight() + measuredHeight);
            }
            LayoutParams layoutParams = (LayoutParams) this.a.a.getLayoutParams();
            layoutParams.leftMargin = rect.left;
            layoutParams.topMargin = rect.top;
            this.a.a.setLayoutParams(layoutParams);
            return;
        }
        layoutParams = (LayoutParams) this.a.a.getLayoutParams();
        layoutParams.gravity = 17;
        this.a.a.setLayoutParams(layoutParams);
    }
}
