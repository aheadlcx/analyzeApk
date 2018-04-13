package qsbk.app.widget.qiushitopic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.DynamicLayout;
import android.text.Layout.Alignment;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.R;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiushiEmotionEditText;

public class MultiLayoutEditText extends QiushiEmotionEditText {
    private static final CharSequence c = "[pre]";
    private static final String d = MultiLayoutEditText.class.getSimpleName();
    private static int e = R.drawable.ic_topic_prefix;
    DynamicLayout a;
    QiushiTopic b = QiushiTopic.EMPTY;
    private transient boolean f;
    private int g;
    private OnTopicClickListener h;
    private Spannable i;
    private int j;
    private int k;
    private Rect l = new Rect();
    private Rect m = new Rect();
    private int n;

    public interface OnTopicClickListener {
        void onTopicClick(QiushiTopic qiushiTopic);
    }

    public MultiLayoutEditText(Context context) {
        super(context);
        a(context);
    }

    public MultiLayoutEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MultiLayoutEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    private void a(Context context) {
        setIncludeFontPadding(false);
        this.j = dip2px(context, 8.0f);
        this.k = dip2px(context, 5.0f);
        e = UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix;
        this.n = UIHelper.isNightTheme() ? Color.parseColor("#bc7b1b") : Color.parseColor("#ffbb00");
    }

    public void setQiushiTopic(QiushiTopic qiushiTopic) {
        this.f = true;
        if (qiushiTopic == null) {
            qiushiTopic = QiushiTopic.EMPTY;
        }
        this.b = qiushiTopic;
        invalidate();
    }

    public QiushiTopic getTopic() {
        return this.b;
    }

    private Spannable a(QiushiTopic qiushiTopic) {
        if (QiushiTopic.EMPTY.equals(qiushiTopic)) {
            return null;
        }
        Spannable spannableStringBuilder = new SpannableStringBuilder(c);
        PrefixSpan prefixSpan = new PrefixSpan(getResources().getDrawable(e), getPaint());
        prefixSpan.setMargin(0, 0, this.k, 0);
        spannableStringBuilder.setSpan(prefixSpan, 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(qiushiTopic.content);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.n), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    protected void onDraw(Canvas canvas) {
        float f;
        int i;
        int i2 = 0;
        int measuredWidth = (getMeasuredWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        if (this.b == null || QiushiTopic.EMPTY.equals(this.b)) {
            f = 0.0f;
            i = 0;
        } else {
            this.a = new DynamicLayout(a(this.b), getPaint(), measuredWidth, Alignment.ALIGN_NORMAL, 1.0f, (float) dip2px(getContext(), 0.0f), false);
            canvas.save();
            canvas.translate((float) getCompoundPaddingLeft(), (float) getCompoundPaddingTop());
            this.a.draw(canvas);
            canvas.restore();
            i = this.a.getLineCount();
            f = (float) this.a.getWidth();
            i2 = (int) this.a.getLineRight(i - 1);
        }
        if (this.f) {
            a(i, f, i2);
            if (this.a != null && i > 0) {
                this.l.set(getCompoundPaddingLeft(), getCompoundPaddingTop(), getCompoundPaddingLeft() + this.a.getWidth(), getCompoundPaddingTop() + this.a.getHeight());
                this.m.set(getCompoundPaddingLeft() + i2, this.a.getLineTop(i - 1) + getCompoundPaddingTop(), getCompoundPaddingLeft() + this.a.getWidth(), getCompoundPaddingTop() + this.a.getHeight());
            }
        }
        super.onDraw(canvas);
    }

    private void a(int i, float f, int i2) {
        if (b()) {
            a();
        }
        if (QiushiTopic.EMPTY.equals(this.b)) {
            this.g = getText().length();
            this.i = null;
        } else {
            getText().insert(0, b(i, f, i2));
        }
        this.f = false;
    }

    private Spannable b(int i, float f, int i2) {
        int i3 = 0;
        Spannable spannableStringBuilder = new SpannableStringBuilder();
        int i4 = 0;
        while (i3 < i) {
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (i3 == i - 1) {
                spannableStringBuilder.setSpan(new PlaceHolderSpan(i4, spannableStringBuilder.length(), this.j + i2), i4, spannableStringBuilder.length(), 33);
            } else if (i3 == 0) {
                spannableStringBuilder.setSpan(new PlaceHolderSpan(i4, spannableStringBuilder.length(), (int) f, getPaint()), i4, spannableStringBuilder.length(), 33);
            } else {
                spannableStringBuilder.setSpan(new PlaceHolderSpan(i4, spannableStringBuilder.length(), (int) f), i4, spannableStringBuilder.length(), 33);
            }
            i4 = spannableStringBuilder.length();
            i3++;
        }
        this.g = i4;
        this.i = spannableStringBuilder;
        return spannableStringBuilder;
    }

    private void a() {
        PlaceHolderSpan[] placeHolderSpanArr = (PlaceHolderSpan[]) getText().getSpans(0, getText().length(), PlaceHolderSpan.class);
        if (placeHolderSpanArr != null && placeHolderSpanArr.length > 0) {
            for (int length = placeHolderSpanArr.length - 1; length >= 0; length--) {
                PlaceHolderSpan placeHolderSpan = placeHolderSpanArr[length];
                if (getText().length() >= placeHolderSpan.g) {
                    getText().delete(placeHolderSpan.f, placeHolderSpan.g);
                }
            }
        }
    }

    public String getQiushiContent() {
        return getText().toString();
    }

    private boolean b() {
        PlaceHolderSpan[] placeHolderSpanArr = (PlaceHolderSpan[]) getText().getSpans(0, getText().length(), PlaceHolderSpan.class);
        if (placeHolderSpanArr == null || placeHolderSpanArr.length <= 0) {
            return false;
        }
        return true;
    }

    public Spannable getPlaceSpan() {
        return this.i;
    }

    public int getPlaceEnd() {
        return this.g;
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if ((i == this.g - 1 && i2 > i3 && !this.f) || (i < this.g && !this.f)) {
            setQiushiTopic(QiushiTopic.EMPTY);
        }
    }

    protected void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if ((i < this.g || i2 < this.g) && getText().length() >= this.g && !QiushiTopic.EMPTY.equals(this.b)) {
            Selection.setSelection(getText(), this.g);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent;
        Object obj = null;
        try {
            onTouchEvent = super.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            onTouchEvent = false;
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (!(QiushiTopic.EMPTY.equals(this.b) || !this.l.contains((int) x, (int) y) || this.m.contains((int) x, (int) y))) {
                obj = 1;
            }
            if (!(obj == null || this.h == null)) {
                this.h.onTopicClick(this.b);
            }
        }
        return onTouchEvent;
    }

    public void setSelection(int i) {
        if (i < this.g) {
            i = this.g;
        }
        super.setSelection(i);
    }

    public void setSelection(int i, int i2) {
        if (i < this.g) {
            i = this.g;
        }
        super.setSelection(i, i2);
    }

    public int getOffsetForPosition(float f, float f2) {
        int offsetForPosition = super.getOffsetForPosition(f, f2);
        if (offsetForPosition < this.g) {
            return this.g;
        }
        return offsetForPosition;
    }

    public void setOnTopicClickListener(OnTopicClickListener onTopicClickListener) {
        this.h = onTopicClickListener;
    }
}
