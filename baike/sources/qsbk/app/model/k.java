package qsbk.app.model;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.EvaluateCard.ViewHolder;

class k implements OnClickListener {
    final /* synthetic */ ViewHolder a;

    k(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onClick(View view) {
        EvaluateCard.b(view.getContext(), this.a, EvaluateCard.d);
        this.a.evaluateSubmit.setEnabled(false);
        this.a.evaluateSubmit.setText("提交中...");
    }
}
