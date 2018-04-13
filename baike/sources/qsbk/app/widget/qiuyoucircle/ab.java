package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;

class ab implements OnClickListener {
    final /* synthetic */ BaseUserCell a;

    ab(BaseUserCell baseUserCell) {
        this.a = baseUserCell;
    }

    public void onClick(View view) {
        this.a.untopDialog(this.a.getContext(), (CircleArticle) this.a.getItem());
    }
}
