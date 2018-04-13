package qsbk.app.widget.qiuyoucircle;

import qsbk.app.widget.ObservableTextView.OnTextMoreListener;

class bh implements OnTextMoreListener {
    final /* synthetic */ ShareCell a;

    bh(ShareCell shareCell) {
        this.a = shareCell;
    }

    public void onHasEllipsize() {
    }

    public void onTextMore() {
        this.a.moreView.setVisibility(0);
    }
}
