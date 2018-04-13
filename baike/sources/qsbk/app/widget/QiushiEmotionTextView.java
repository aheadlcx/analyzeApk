package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import qsbk.app.R;

public class QiushiEmotionTextView extends TextView {
    private int a;
    private int b;
    private int c;

    public QiushiEmotionTextView(Context context) {
        super(context);
        a(null);
    }

    public QiushiEmotionTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public QiushiEmotionTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.c = (int) getTextSize();
        if (attributeSet == null) {
            this.a = (int) getTextSize();
        } else {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.QiushiEmotion);
            this.a = (int) obtainStyledAttributes.getDimension(0, getTextSize());
            this.b = obtainStyledAttributes.getInt(1, 1);
            obtainStyledAttributes.recycle();
        }
        setText(getText());
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (!TextUtils.isEmpty(charSequence)) {
            Spannable spannableStringBuilder = new SpannableStringBuilder(charSequence);
            QiushiEmotionHandler.getInstance();
            QiushiEmotionHandler.addEmotions(getContext(), spannableStringBuilder, this.a, this.b, this.c);
            charSequence = spannableStringBuilder;
        }
        super.setText(charSequence, bufferType);
    }

    public void setEmojiconSize(int i) {
        this.a = i;
        super.setText(getText());
    }
}
