package qsbk.app.widget.qiuyoucircle;

import qsbk.app.widget.ObservableTextView.OnTextMoreListener;

class by implements OnTextMoreListener {
    final /* synthetic */ WebAdCell a;

    by(WebAdCell webAdCell) {
        this.a = webAdCell;
    }

    public void onHasEllipsize() {
    }

    public void onTextMore() {
        this.a.moreView.setVisibility(0);
    }
}
