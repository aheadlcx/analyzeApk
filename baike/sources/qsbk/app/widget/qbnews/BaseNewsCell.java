package qsbk.app.widget.qbnews;

import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;

public abstract class BaseNewsCell extends BaseCell {
    protected int a = 0;
    protected int b = 0;
    protected double c = 0.0d;

    protected abstract void a();

    public void onClick() {
        super.onClick();
    }

    protected void a(int i) {
        this.a = i;
        if (this.c != 0.0d) {
            this.b = (int) (((double) this.a) / this.c);
        }
    }

    protected void b(int i) {
        this.b = i;
        if (this.c != 0.0d) {
            this.a = (int) (((double) this.b) * this.c);
        }
    }

    protected void a(double d) {
        this.c = d;
    }

    public void setImageLayoutParams(ImageView imageView) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        b();
        layoutParams.width = this.a;
        layoutParams.height = this.b;
        imageView.setLayoutParams(layoutParams);
        imageView.requestLayout();
    }

    protected ImageSize b() {
        if (this.b != 0 && this.a != 0) {
            return new ImageSize(this.a, this.b);
        }
        if (this.a != 0 && this.c > 0.0d) {
            this.b = (int) (((double) this.a) / this.c);
        } else if (this.b == 0 || this.c <= 0.0d) {
            this.a = UIHelper.getScreenWidth(getContext());
            this.b = (int) (((double) this.a) / this.c);
        } else {
            this.a = (int) (((double) this.b) * this.c);
        }
        return new ImageSize(this.a, this.b);
    }
}
