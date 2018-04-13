package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import qsbk.app.R;

public class SeekView extends View {
    private int a;
    private int b;
    private int c;
    private Paint d;
    private int e;
    private OnSeekViewChangeListener f;

    public interface OnSeekViewChangeListener {
        void onProgressChanged(SeekView seekView, int i, boolean z);

        void onStartTrackingTouch(SeekView seekView);

        void onStopTrackingTouch(SeekView seekView);
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new eq();
        private int a;
        private int b;
        private int c;
        private int d;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
        }
    }

    public SeekView(Context context) {
        super(context);
        a(null, 0);
    }

    public SeekView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public SeekView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SeekView, i, 0);
        this.a = obtainStyledAttributes.getInt(0, 100);
        this.b = obtainStyledAttributes.getInt(1, 0);
        this.c = obtainStyledAttributes.getInt(2, 0);
        obtainStyledAttributes.recycle();
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setStrokeCap(Cap.ROUND);
        float f = getResources().getDisplayMetrics().density;
        this.d.setStrokeWidth(2.0f * f);
        this.e = Math.round(f * 3.0f);
    }

    public int getMax() {
        return this.a;
    }

    public void setMax(int i) {
        this.a = Math.max(1, i);
        postInvalidate();
    }

    public int getProgress() {
        return this.b;
    }

    public void setProgress(int i) {
        a(i, false);
    }

    private void a(int i, boolean z) {
        int max = Math.max(0, Math.min(i, this.a));
        this.b = max;
        postInvalidate();
        if (this.f != null) {
            this.f.onProgressChanged(this, max, z);
        }
    }

    public int getSecondaryProgress() {
        return this.c;
    }

    public void setSecondaryProgress(int i) {
        this.c = Math.max(0, Math.min(i, this.a));
        postInvalidate();
    }

    public void setOnSeekViewChangeListener(OnSeekViewChangeListener onSeekViewChangeListener) {
        this.f = onSeekViewChangeListener;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(this.e, i), resolveSize(MeasureSpec.getSize(i2), i2));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft() + this.e;
        int width = (getWidth() - getPaddingRight()) - this.e;
        int paddingTop = getPaddingTop() + ((getHeight() - getPaddingBottom()) / 2);
        this.d.setColor(Integer.MAX_VALUE);
        canvas.drawLine((float) paddingLeft, (float) paddingTop, (float) width, (float) paddingTop, this.d);
        this.d.setColor(-2130720256);
        canvas.drawLine((float) paddingLeft, (float) paddingTop, (float) ((((width - paddingLeft) * this.c) / this.a) + paddingLeft), (float) paddingTop, this.d);
        this.d.setColor(-13824);
        width = (((width - paddingLeft) * this.b) / this.a) + paddingLeft;
        canvas.drawLine((float) paddingLeft, (float) paddingTop, (float) width, (float) paddingTop, this.d);
        canvas.drawCircle((float) width, (float) paddingTop, (float) this.e, this.d);
    }

    public boolean canScrollHorizontally(int i) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f != null) {
                    this.f.onStartTrackingTouch(this);
                }
                a(motionEvent);
                break;
            case 1:
                a(motionEvent);
                break;
            case 2:
                a(motionEvent);
                break;
            case 3:
                break;
        }
        if (this.f != null) {
            this.f.onStopTrackingTouch(this);
        }
        return true;
    }

    private void a(MotionEvent motionEvent) {
        int paddingLeft = getPaddingLeft() + this.e;
        a((int) (((motionEvent.getX() - ((float) paddingLeft)) * ((float) this.a)) / ((float) (((getWidth() - getPaddingRight()) - this.e) - paddingLeft))), true);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.a;
        savedState.b = this.b;
        savedState.c = this.c;
        savedState.d = this.e;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
            this.e = savedState.d;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
