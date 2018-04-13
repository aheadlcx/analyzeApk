package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;

public class LogoView extends BarcodeView {
    public LogoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Bitmap getLeftTopImage() {
        return null;
    }

    public void setLeftTopImage(Bitmap bitmap) {
    }

    public Bitmap getRightBottomImage() {
        return null;
    }

    public void setRightBottomImage(Bitmap bitmap) {
    }

    public Rect getRightBottomRect() {
        return null;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public OnCloseListener getOnCloseListener() {
        return null;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
    }
}
