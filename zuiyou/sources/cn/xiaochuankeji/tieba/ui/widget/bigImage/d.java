package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.graphics.PointF;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.e;

public class d implements e {
    private final SubsamplingScaleImageView a;
    private int b;

    public d(SubsamplingScaleImageView subsamplingScaleImageView) {
        this.a = subsamplingScaleImageView;
    }

    public void a() {
        float f = 0.5f;
        int sWidth = this.a.getSWidth();
        int sHeight = this.a.getSHeight();
        int width = this.a.getWidth();
        int height = this.a.getHeight();
        int i = 0;
        if (sWidth == 0 || sHeight == 0 || width == 0 || height == 0) {
            i = 1;
        }
        if (i == 0) {
            if (sWidth <= sHeight) {
                f = ((float) width) / ((float) sWidth);
            } else {
                f = ((float) height) / ((float) sHeight);
            }
        }
        if (i == 0 && ((float) sHeight) / ((float) sWidth) > 2.0f) {
            this.a.b(f, new PointF((float) (sWidth / 2), 0.0f)).a(1).a();
        }
        if (Math.abs(((double) f) - 0.1d) < 0.20000000298023224d) {
            f += 0.2f;
        }
        if (this.b == 3) {
            float max = Math.max(((float) width) / ((float) sWidth), ((float) height) / ((float) sHeight));
            if (max > 1.0f) {
                this.a.setMinScale(1.0f);
                this.a.setMaxScale(Math.max(this.a.getMaxScale(), max * 1.2f));
            } else {
                this.a.setMinScale(Math.min(((float) width) / ((float) sWidth), ((float) height) / ((float) sHeight)));
            }
            if (this.a.getMaxScale() < this.a.getMinScale()) {
                this.a.a(this.a.getMinScale(), new PointF((float) (sWidth / 2), (float) (sHeight / 2)));
            }
            if (this.a.getMaxScale() < this.a.getMinScale() * 1.5f) {
                this.a.setMaxScale(this.a.getMinScale() * 1.5f);
            }
        }
        this.a.setDoubleTapZoomScale(f);
    }

    public void b() {
    }

    public void a(Exception exception) {
    }

    public void b(Exception exception) {
    }

    public void c(Exception exception) {
    }

    public void c() {
    }

    public void a(int i) {
        this.b = i;
    }
}
