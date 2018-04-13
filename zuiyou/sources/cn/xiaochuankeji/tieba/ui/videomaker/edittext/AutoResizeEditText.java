package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.R;

public class AutoResizeEditText extends AppCompatEditText {
    private int a;
    private boolean b;
    private int c;
    private int d;
    private int e;

    public AutoResizeEditText(Context context) {
        this(context, null);
    }

    public AutoResizeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public AutoResizeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ AutoResizeEditText a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (!this.a.b) {
                    CharSequence obj = editable.toString();
                    if (!TextUtils.isEmpty(obj)) {
                        this.a.a(obj);
                    }
                }
            }
        });
    }

    public void setMaxTextLine(int i) {
        this.c = i;
    }

    private void a(CharSequence charSequence) {
        this.d = (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        this.e = (getHeight() - getCompoundPaddingTop()) - getCompoundPaddingBottom();
        if (this.d > 0 && this.e > 0) {
            a(charSequence, 0, 250);
            StaticLayout staticLayout = new StaticLayout(charSequence, getPaint(), this.d, Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), true);
            if (staticLayout.getLineCount() > this.c) {
                for (int lineCount = staticLayout.getLineCount() - 2; lineCount >= 0; lineCount--) {
                    if (lineCount + 1 <= this.c) {
                        CharSequence subSequence = charSequence.subSequence(staticLayout.getLineStart(0), staticLayout.getLineEnd(lineCount));
                        this.b = true;
                        setText(subSequence);
                        setSelection(subSequence.length());
                        this.b = false;
                        return;
                    }
                }
            }
        }
    }

    private void a(CharSequence charSequence, int i, int i2) {
        int b = b(charSequence, i, i2);
        if (b != this.a) {
            setTextSize(0, (float) b);
            this.a = b;
        }
    }

    private int b(CharSequence charSequence, int i, int i2) {
        int i3 = i2;
        int i4 = i;
        int i5 = i;
        while (i4 <= i3) {
            int i6 = (i4 + i3) >>> 1;
            int a = a(i6, charSequence);
            if (a < 0) {
                i4 = i6 + 1;
            } else if (a > 0) {
                i3 = i6 - 1;
                i6 = i5;
            } else {
                i6 = i5;
            }
            i5 = i6;
        }
        return i5;
    }

    private int a(int i, CharSequence charSequence) {
        TextPaint textPaint = new TextPaint(getPaint());
        textPaint.setTextSize((float) i);
        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, this.d, Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), true);
        int lineCount = staticLayout.getLineCount();
        if (lineCount <= this.c) {
            if (staticLayout.getHeight() > this.e) {
                return 1;
            }
            return -1;
        } else if ((staticLayout.getHeight() / lineCount) * this.c <= this.e) {
            return -1;
        } else {
            return 1;
        }
    }
}
