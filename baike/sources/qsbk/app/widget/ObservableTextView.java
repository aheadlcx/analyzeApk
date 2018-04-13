package qsbk.app.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView.BufferType;

public class ObservableTextView extends QiushiEmotionTextView {
    private int a;
    private boolean b;
    private OnTextMoreListener c;

    public interface OnTextMoreListener {
        void onHasEllipsize();

        void onTextMore();
    }

    public ObservableTextView(Context context) {
        super(context);
    }

    public ObservableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ObservableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Layout layout = getLayout();
        if (layout != null && layout.getLineCount() >= this.a) {
            if (layout.getEllipsisCount(this.a - 1) > 0 && this.c != null) {
                this.c.onHasEllipsize();
            }
            if (getText().length() > layout.getLineEnd(this.a - 1)) {
                this.b = true;
                if (this.c != null) {
                    this.c.onTextMore();
                }
            }
        }
    }

    public void setOnTextMoreListener(OnTextMoreListener onTextMoreListener) {
        this.c = onTextMoreListener;
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.a = i;
    }

    public int getMaxLineTextOffset() {
        Layout layout = getLayout();
        if (layout == null || layout.getLineCount() < this.a) {
            return -1;
        }
        return layout.getLineEnd(this.a - 1);
    }

    public int getLineEndOffset(int i) {
        Layout layout = getLayout();
        if (layout == null || layout.getLineCount() <= i) {
            return -1;
        }
        return layout.getLineEnd(i);
    }

    public int getLinesMax() {
        return this.a;
    }

    public boolean isTextMore() {
        return this.b;
    }
}
