package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import qsbk.app.R;

public class QiushiEmotionEditText extends EditText {
    private int a;
    private int b;
    private int c;

    public QiushiEmotionEditText(Context context) {
        super(context);
        this.a = (int) getTextSize();
        this.c = (int) getTextSize();
    }

    public QiushiEmotionEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public QiushiEmotionEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.QiushiEmotion);
        this.a = (int) obtainStyledAttributes.getDimension(0, getTextSize());
        this.b = obtainStyledAttributes.getInt(1, 1);
        obtainStyledAttributes.recycle();
        this.c = (int) getTextSize();
        setText(getText());
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        a();
    }

    private void a() {
        QiushiEmotionHandler.getInstance();
        QiushiEmotionHandler.addEmotions(getContext(), getText(), this.a, this.b, this.c);
    }

    public int getEmojiconSize() {
        return this.a;
    }

    public void setEmojiconSize(int i) {
        this.a = i;
        a();
    }

    public int getEmojiconAlignment() {
        return this.b;
    }

    public int getEmojiconTextSize() {
        return this.c;
    }
}
