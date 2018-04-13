package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView.BufferType;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;

public class DraweeTextView extends AppCompatTextView {
    private boolean a;
    private boolean b;

    public DraweeTextView(Context context) {
        super(context);
    }

    public DraweeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DraweeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        boolean z = this.b;
        if (this.a && z) {
            b();
            this.a = false;
        }
        if (charSequence instanceof Spanned) {
            this.a = ((DraweeSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), DraweeSpan.class)).length > 0;
        }
        super.setText(charSequence, bufferType);
        if (this.a && z) {
            a();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        b();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        a();
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.a) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || (this.a && (drawable instanceof ForwardingDrawable) && (drawable.getCurrent() instanceof AnimatedDrawable2));
    }

    private void a() {
        for (DraweeSpan onAttach : getImages()) {
            onAttach.onAttach(this);
        }
        this.b = true;
    }

    private DraweeSpan[] getImages() {
        if (!this.a || length() <= 0) {
            return new DraweeSpan[0];
        }
        return (DraweeSpan[]) ((Spanned) getText()).getSpans(0, length(), DraweeSpan.class);
    }

    private void b() {
        for (DraweeSpan draweeSpan : getImages()) {
            Drawable drawable = draweeSpan.getDrawable();
            if (drawable != null) {
                unscheduleDrawable(drawable);
            }
            draweeSpan.onDetach();
        }
        this.b = false;
    }
}
