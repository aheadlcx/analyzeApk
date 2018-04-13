package qsbk.app.widget.qiuyoucircle;

import qsbk.app.widget.ObservableTextView.OnTextMoreListener;

class bb implements OnTextMoreListener {
    final /* synthetic */ NormalCell a;

    bb(NormalCell normalCell) {
        this.a = normalCell;
    }

    public void onHasEllipsize() {
    }

    public void onTextMore() {
        this.a.moreView.setVisibility(0);
    }
}
