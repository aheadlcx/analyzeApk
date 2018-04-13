package qsbk.app.widget.qiuyoucircle;

import qsbk.app.widget.ObservableTextView.OnTextMoreListener;

class al implements OnTextMoreListener {
    final /* synthetic */ ForwardCell a;

    al(ForwardCell forwardCell) {
        this.a = forwardCell;
    }

    public void onHasEllipsize() {
    }

    public void onTextMore() {
        this.a.moreView.setVisibility(0);
    }
}
