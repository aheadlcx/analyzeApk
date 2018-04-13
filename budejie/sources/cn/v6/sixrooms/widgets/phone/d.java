package cn.v6.sixrooms.widgets.phone;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class d implements OnTouchListener {
    final /* synthetic */ ExpressionGroup a;

    d(ExpressionGroup expressionGroup) {
        this.a = expressionGroup;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.a.a != null) {
                    this.a.a.onTouchAction(0);
                    break;
                }
                break;
            case 1:
                if (this.a.a != null) {
                    this.a.a.onTouchAction(1);
                    break;
                }
                break;
        }
        return false;
    }
}
