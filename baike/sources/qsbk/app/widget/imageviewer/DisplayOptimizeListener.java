package qsbk.app.widget.imageviewer;

import android.graphics.PointF;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.OnImageEventListener;

public class DisplayOptimizeListener implements OnImageEventListener {
    private final SubsamplingScaleImageView a;

    public DisplayOptimizeListener(SubsamplingScaleImageView subsamplingScaleImageView) {
        this.a = subsamplingScaleImageView;
    }

    public void onReady() {
        float f = 0.5f;
        int sWidth = this.a.getSWidth();
        int sHeight = this.a.getSHeight();
        int width = this.a.getWidth();
        int height = this.a.getHeight();
        Object obj = null;
        if (sWidth == 0 || sHeight == 0 || width == 0 || height == 0) {
            obj = 1;
        }
        if (obj == null) {
            if (sWidth <= sHeight) {
                f = ((float) width) / ((float) sWidth);
            } else {
                f = ((float) height) / ((float) sHeight);
            }
        }
        if (obj == null) {
            this.a.setMinScale(f);
            this.a.setScaleAndCenter(f, new PointF((float) (sWidth / 2), 0.0f));
        }
        if (this.a.getMaxScale() < this.a.getMinScale() * 1.5f) {
            this.a.setMaxScale(this.a.getMinScale() * 1.5f);
        }
        this.a.setDoubleTapZoomScale(this.a.getMaxScale());
    }

    public void onImageLoaded() {
    }

    public void onPreviewLoadError(Exception exception) {
    }

    public void onImageLoadError(Exception exception) {
    }

    public void onTileLoadError(Exception exception) {
    }

    public void onPreviewReleased() {
    }
}
