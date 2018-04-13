package qsbk.app.widget.barcode;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.widget.FrameLayout;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;

public class SimpleZoomListener implements OnTouchListener {
    private ZoomState a;
    private ControlType b = ControlType.ZOOM;
    private float c;
    private float d;
    private boolean e = false;

    public enum ControlType {
        PAN,
        ZOOM
    }

    public void setZoomState(ZoomState zoomState) {
        this.a = zoomState;
    }

    public void setControlType(ControlType controlType) {
        this.b = controlType;
    }

    private boolean b(View view, float f, float f2) {
        BarcodeView barcodeView = (BarcodeView) view;
        Rect innerRect = barcodeView.getInnerRect();
        Bitmap leftTopImage = barcodeView.getLeftTopImage();
        if (f < ((float) (innerRect.left - (leftTopImage.getWidth() / 2))) || f > ((float) (innerRect.left + leftTopImage.getWidth())) || f2 < ((float) (innerRect.top - (leftTopImage.getHeight() / 2))) || f2 > ((float) (innerRect.top + leftTopImage.getHeight()))) {
            return false;
        }
        return true;
    }

    private boolean c(View view, float f, float f2) {
        BarcodeView barcodeView = (BarcodeView) view;
        Rect innerRect = barcodeView.getInnerRect();
        Bitmap rightBottomImage = barcodeView.getRightBottomImage();
        if (f > ((float) innerRect.right) + (((float) rightBottomImage.getWidth()) * this.a.getZoom()) || f < ((float) innerRect.right) - (((float) rightBottomImage.getWidth()) * this.a.getZoom()) || f2 > ((float) (innerRect.bottom + (rightBottomImage.getHeight() / 2))) || f2 < ((float) (innerRect.bottom - (rightBottomImage.getHeight() * 2)))) {
            return false;
        }
        return true;
    }

    protected void a(View view, float f, float f2) {
        BarcodeView barcodeView = (BarcodeView) view;
        Rect innerRect = barcodeView.getInnerRect();
        Bitmap leftTopImage = barcodeView.getLeftTopImage();
        Bitmap rightBottomImage = barcodeView.getRightBottomImage();
        ViewParent parent = view.getParent();
        if (f >= ((float) (innerRect.left - (leftTopImage.getWidth() / 2))) && f <= ((float) (innerRect.left + leftTopImage.getWidth())) && f2 >= ((float) (innerRect.top - (leftTopImage.getHeight() / 2)))) {
            if (f2 <= ((float) (leftTopImage.getHeight() + innerRect.top))) {
                if (parent != null && (parent instanceof FrameLayout)) {
                    ((FrameLayout) parent).removeView(view);
                    view.setOnTouchListener(null);
                    ((BarcodeView) view).getZoomState().deleteObservers();
                    OnCloseListener onCloseListener = ((BarcodeView) view).getOnCloseListener();
                    if (onCloseListener != null) {
                        onCloseListener.onClose();
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if ((f > ((float) innerRect.right) + (((float) rightBottomImage.getWidth()) * this.a.getZoom()) || f < ((float) innerRect.right) - (((float) rightBottomImage.getWidth()) * this.a.getZoom()) || f2 > ((float) (innerRect.bottom + (rightBottomImage.getHeight() / 2))) || f2 < ((float) (innerRect.bottom - (rightBottomImage.getHeight() * 2)))) && (((BarcodeView) view).getRightBottomRect() == null || !((BarcodeView) view).getRightBottomRect().contains((int) f, (int) f2))) {
            this.b = ControlType.PAN;
        } else {
            this.b = ControlType.ZOOM;
        }
    }

    public boolean isTouchInBounds(View view, float f, float f2) {
        LogUtil.d(String.format("checkBound:left:%s,top:%s,right:%s,bottom:%s", new Object[]{Integer.valueOf(((BarcodeView) view).getInnerRect().left), Integer.valueOf(((BarcodeView) view).getInnerRect().top), Integer.valueOf(((BarcodeView) view).getInnerRect().right), Integer.valueOf(((BarcodeView) view).getInnerRect().bottom)}));
        LogUtil.d(String.format("touch x:%s,touch y:%s", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
        return ((BarcodeView) view).getInnerRect().contains((int) f, (int) f2) || b(view, f, f2) || c(view, f, f2);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean isTouchInBounds = isTouchInBounds(view, x, y);
        switch (action) {
            case 0:
                if (isTouchInBounds) {
                    this.e = true;
                    this.c = x;
                    this.d = y;
                    a(view, x, y);
                    break;
                }
                return false;
            case 1:
                if (this.e) {
                    this.e = false;
                    return true;
                }
                break;
            case 2:
                if (this.e) {
                    float width = (x - this.c) / ((float) view.getWidth());
                    float height = (y - this.d) / ((float) view.getHeight());
                    if (this.b == ControlType.ZOOM) {
                        this.a.setZoom(((float) Math.pow(20.0d, ((double) height) * 2.5d)) * this.a.getZoom());
                        this.a.notifyObservers();
                    } else {
                        float zoom = 1.0f / this.a.getZoom();
                        float a = a() * zoom;
                        this.a.setPanX(this.a.getPanX() - (width * zoom));
                        this.a.setPanY(this.a.getPanY() - (height * a));
                        this.a.notifyObservers();
                    }
                    this.c = x;
                    this.d = y;
                    break;
                }
                return false;
        }
        return true;
    }

    protected float a() {
        return 1.32f;
    }
}
