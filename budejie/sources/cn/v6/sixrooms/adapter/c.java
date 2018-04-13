package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.View.OnClickListener;

final class c implements OnClickListener {
    final /* synthetic */ ExpressionItemAdapter a;

    c(ExpressionItemAdapter expressionItemAdapter) {
        this.a = expressionItemAdapter;
    }

    public final void onClick(View view) {
        if (this.a.e != null) {
            this.a.e.onTouchAction(2);
        }
    }
}
