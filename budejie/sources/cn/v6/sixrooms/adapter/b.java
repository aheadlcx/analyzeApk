package cn.v6.sixrooms.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class b implements OnTouchListener {
    final /* synthetic */ ExpressionItemAdapter a;

    b(ExpressionItemAdapter expressionItemAdapter) {
        this.a = expressionItemAdapter;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.a.e != null) {
                    this.a.e.onTouchAction(0);
                    break;
                }
                break;
            case 1:
                if (this.a.e != null) {
                    this.a.e.onTouchAction(1);
                    break;
                }
                break;
        }
        return false;
    }
}
