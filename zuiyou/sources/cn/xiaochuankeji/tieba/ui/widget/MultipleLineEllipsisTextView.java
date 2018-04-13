package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import c.a.i.w;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.lang.reflect.Field;
import java.util.HashMap;

public class MultipleLineEllipsisTextView extends w {
    private static HashMap<Long, Boolean> k = new HashMap();
    private int a;
    private boolean b;
    private boolean c;
    private long d;
    private int e;
    private int f;
    @ColorInt
    private int g;
    private boolean h;
    private c i;
    private d j;
    private boolean l;
    private boolean m;
    private String n;
    private String o;
    private float p;
    private float q;
    private HashMap<Long, Boolean> r;
    private GestureDetector s;

    public interface c {
        void a();

        void onClick();
    }

    public interface d {
        void a(boolean z);
    }

    private class a extends ClickableSpan {
        final /* synthetic */ MultipleLineEllipsisTextView a;

        private a(MultipleLineEllipsisTextView multipleLineEllipsisTextView) {
            this.a = multipleLineEllipsisTextView;
        }

        public void onClick(View view) {
            this.a.b();
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            textPaint.linkColor = this.a.e;
            textPaint.bgColor = this.a.getResources().getColor(R.color.transparent);
            textPaint.setColor(this.a.e);
        }
    }

    private class b extends SimpleOnGestureListener {
        final /* synthetic */ MultipleLineEllipsisTextView a;

        private b(MultipleLineEllipsisTextView multipleLineEllipsisTextView) {
            this.a = multipleLineEllipsisTextView;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            this.a.b();
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            if (this.a.i != null) {
                this.a.i.a();
            }
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            boolean z;
            CharSequence text = this.a.getText();
            if (!(text == null || text.toString().equals("") || (!(text instanceof SpannableString) && !(text instanceof SpannedString)))) {
                ClickableSpan[] clickableSpanArr;
                int x = (((int) motionEvent.getX()) - this.a.getTotalPaddingLeft()) + this.a.getScrollX();
                int y = (((int) motionEvent.getY()) - this.a.getTotalPaddingTop()) + this.a.getScrollY();
                Layout layout = this.a.getLayout();
                x = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
                if (text instanceof SpannableString) {
                    clickableSpanArr = (ClickableSpan[]) ((SpannableString) text).getSpans(x, x, ClickableSpan.class);
                } else {
                    clickableSpanArr = (ClickableSpan[]) ((SpannedString) text).getSpans(x, x, ClickableSpan.class);
                }
                if (clickableSpanArr.length != 0) {
                    clickableSpanArr[0].onClick(this.a);
                    z = true;
                    if (!(z || this.a.i == null)) {
                        this.a.i.onClick();
                    }
                    return true;
                }
            }
            z = false;
            this.a.i.onClick();
            return true;
        }
    }

    public MultipleLineEllipsisTextView(Context context) {
        this(context, null);
    }

    public MultipleLineEllipsisTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultipleLineEllipsisTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 8;
        this.g = 0;
        this.h = true;
        this.l = true;
        this.o = "[双击展开]";
        this.p = 1.0f;
        this.q = 0.0f;
        this.s = new GestureDetector(context, new b());
        setMovementMethod(a.a());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.s.onTouchEvent(motionEvent);
        return true;
    }

    public void setLineSpacing(float f, float f2) {
        this.q = f;
        this.p = f2;
        super.setLineSpacing(f, f2);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (!this.m) {
            this.l = true;
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.l && a()) {
            e();
        }
        super.onDraw(canvas);
    }

    public String getFullText() {
        return this.n;
    }

    public void a(CharSequence charSequence, HashMap<Long, Boolean> hashMap, long j, int i, int i2) {
        this.n = charSequence.toString();
        if (i2 == 4) {
            this.a = 4;
        } else if (i2 == 5) {
            this.a = 2;
        } else if (i2 == 6) {
            this.a = 5;
        } else {
            this.a = 8;
        }
        switch (i2) {
            case 3:
                this.o = "[查看全文]";
                break;
        }
        if (a() && this.h) {
            boolean z;
            this.e = i;
            this.b = false;
            this.r = hashMap;
            this.d = j;
            if (this.r == null || !this.r.containsKey(Long.valueOf(j))) {
                z = true;
            } else {
                z = ((Boolean) hashMap.get(Long.valueOf(j))).booleanValue();
            }
            if (k.containsKey(Long.valueOf(j))) {
                this.b = ((Boolean) k.get(Long.valueOf(j))).booleanValue();
            }
            if (z) {
                setMaxLines(this.a);
            } else {
                setMaxLines(Integer.MAX_VALUE);
            }
            this.c = z;
            setText(charSequence);
            this.f = i2;
            return;
        }
        setEllipsize(null);
        setMaxLines(Integer.MAX_VALUE);
        setText(charSequence);
    }

    public final void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    private boolean a() {
        return VERSION.SDK_INT >= 11;
    }

    private void b() {
        if (this.b) {
            this.c = !this.c;
            if (this.c) {
                setMaxLines(this.a);
            } else {
                setMaxLines(Integer.MAX_VALUE);
                c();
            }
            if (this.r != null) {
                this.r.put(Long.valueOf(this.d), Boolean.valueOf(this.c));
            }
            setText(this.n);
            if (this.j != null) {
                this.j.a(this.c);
            }
        }
    }

    private void c() {
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("kExpandTxtTipCount", 0);
        if (i < 2) {
            g.a("双击可收回");
            i++;
            Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
            edit.putInt("kExpandTxtTipCount", i);
            edit.apply();
        }
    }

    public int getMaxLines() {
        Class cls = TextView.class;
        try {
            Field declaredField = cls.getDeclaredField("mMaxMode");
            declaredField.setAccessible(true);
            int i = declaredField.getInt(this);
            declaredField = cls.getDeclaredField("mMaximum");
            declaredField.setAccessible(true);
            int i2 = declaredField.getInt(this);
            Field declaredField2 = cls.getDeclaredField("LINES");
            declaredField2.setAccessible(true);
            if (i == declaredField2.getInt(this)) {
                return i2;
            }
            return -1;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return -1;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    private void e() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        if (width > 0) {
            int maxLines = getMaxLines();
            String str = " … " + this.o;
            int length = str != null ? str.length() : 0;
            if (this.f == 3) {
                this.b = false;
                setText(this.n);
                this.m = false;
            } else if (!TextUtils.isEmpty(this.n)) {
                Pair a = e.a(this.n, getPaint(), width, maxLines, str);
                if (((Boolean) a.first).booleanValue()) {
                    this.b = true;
                    k.put(Long.valueOf(this.d), Boolean.valueOf(this.b));
                }
                String str2 = (String) a.second;
                if (this.b) {
                    this.m = true;
                    try {
                        if (this.c) {
                            CharSequence spannableString = new SpannableString(str2);
                            a aVar = new a();
                            length = Math.max(0, str2.length() - length);
                            spannableString.setSpan(aVar, length, str2.length(), 33);
                            if (this.g != 0) {
                                spannableString.setSpan(new ForegroundColorSpan(this.g), length, str2.length(), 33);
                            }
                            setText(spannableString);
                        } else {
                            setText(str2);
                        }
                        this.m = false;
                    } catch (Throwable th) {
                        this.m = false;
                    }
                }
            } else {
                return;
            }
            this.l = false;
        }
    }

    public void setOnExpandableTextViewListener(c cVar) {
        this.i = cVar;
    }

    public void setOnToggleCollapseListener(d dVar) {
        this.j = dVar;
    }

    public void setEndDesc(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.o = str;
        }
    }

    public void setEndDescColor(int i) {
        this.g = i;
        this.e = i;
    }

    public void setExpandable(boolean z) {
        this.h = z;
    }
}
