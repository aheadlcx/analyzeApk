package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ExpressionGroup b;

    b(ExpressionGroup expressionGroup, int i) {
        this.b = expressionGroup;
        this.a = i;
    }

    public final void onClick(View view) {
        this.b.b.onItemClick(this.a);
    }
}
