package qsbk.app.fragments;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import qsbk.app.widget.imageviewer.DisplayOptimizeListener;

class ae extends DisplayOptimizeListener {
    final /* synthetic */ BrowseLongImgFragment a;

    ae(BrowseLongImgFragment browseLongImgFragment, SubsamplingScaleImageView subsamplingScaleImageView) {
        this.a = browseLongImgFragment;
        super(subsamplingScaleImageView);
    }

    public void onImageLoaded() {
        this.a.a.setVisibility(8);
    }
}
