package cn.xiaochuankeji.tieba.ui.widget.customtv;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.background.utils.g;

public class ExpandableTextView extends a {
    public boolean b;
    private String c;
    private f d;
    private String e;
    private String f;
    private int g;
    private SpannableString h;
    private SpannableString i;
    private int j;
    private b k;
    private c l;
    private String m;

    public interface c {
        void a(boolean z);
    }

    private class a extends ClickableSpan {
        final /* synthetic */ ExpandableTextView a;

        private a(ExpandableTextView expandableTextView) {
            this.a = expandableTextView;
        }

        public void onClick(View view) {
            this.a.a(false);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            int currentTextColor = this.a.getCurrentTextColor();
            textPaint.setUnderlineText(false);
            textPaint.setColor(currentTextColor);
        }
    }

    public interface b {
        void a(boolean z);
    }

    private class d extends ClickableSpan {
        final /* synthetic */ ExpandableTextView a;

        private d(ExpandableTextView expandableTextView) {
            this.a = expandableTextView;
        }

        public void onClick(View view) {
            this.a.a(true);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            int parseColor = Color.parseColor("#2caef1");
            textPaint.setUnderlineText(false);
            textPaint.setColor(parseColor);
        }
    }

    private class e extends ClickableSpan {
        final /* synthetic */ ExpandableTextView a;

        private e(ExpandableTextView expandableTextView) {
            this.a = expandableTextView;
        }

        public void onClick(View view) {
            this.a.e();
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            textPaint.setColor(Color.parseColor("#999999"));
        }
    }

    public static class f {
        private boolean a;
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 4;
        this.j = 0;
        this.b = true;
        this.e = " … ";
        this.f = this.e + "[双击展开]";
        a();
    }

    private void a() {
        setOnDoubleTapListener(new cn.xiaochuankeji.tieba.ui.widget.customtv.a.c(this) {
            final /* synthetic */ ExpandableTextView a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.e();
            }
        });
    }

    public void setTextMaxLine(int i) {
        this.g = i;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!TextUtils.isEmpty(this.c)) {
            Pair a = cn.xiaochuankeji.tieba.ui.utils.e.a(this.c, getPaint(), getMeasuredWidth(), this.g, this.f);
            this.i = new SpannableString(this.c);
            b();
            if (((Boolean) a.first).booleanValue()) {
                this.a = true;
                this.h = new SpannableString((String) a.second);
                c();
                if (this.d == null || !this.d.a) {
                    setText(this.h);
                } else {
                    setText(this.i);
                }
            } else {
                this.a = false;
                try {
                    if (this.i.length() > 0) {
                        setText(this.i);
                    }
                } catch (Throwable e) {
                    cn.xiaochuankeji.tieba.analyse.a.a(e);
                }
            }
            this.c = null;
        }
    }

    private void b() {
        a aVar = new a();
        if (TextUtils.isEmpty(this.m)) {
            this.i.setSpan(aVar, 0, this.i.length(), 17);
            return;
        }
        d dVar = new d();
        this.i.setSpan(aVar, 0, "回复 ".length(), 17);
        int length = ("回复 ".length() + this.m.length()) + "：".length();
        this.i.setSpan(dVar, "回复 ".length(), length, 17);
        this.i.setSpan(aVar, length, this.i.length(), 17);
    }

    private void c() {
        a aVar = new a();
        e eVar = new e();
        int length = this.f.length();
        if (TextUtils.isEmpty(this.m)) {
            length = Math.max(0, this.h.length() - length);
            this.h.setSpan(aVar, 0, length, 17);
            this.h.setSpan(eVar, length, this.h.length(), 17);
            return;
        }
        length = Math.max(0, this.h.length() - length);
        d dVar = new d();
        this.h.setSpan(aVar, 0, "回复 ".length(), 17);
        int length2 = ("回复 ".length() + this.m.length()) + "：".length();
        this.h.setSpan(dVar, "回复 ".length(), length2, 17);
        if (length2 < length) {
            this.h.setSpan(aVar, length2, length, 17);
        }
        this.h.setSpan(eVar, length, this.h.length(), 17);
    }

    public void a(String str, String str2, f fVar) {
        this.m = str2;
        if (TextUtils.isEmpty(str2)) {
            this.c = str;
        } else {
            this.c = "回复 " + str2 + "：" + str;
        }
        this.d = fVar;
        requestLayout();
    }

    private void a(boolean z) {
        if (this.l != null) {
            this.l.a(z);
        }
    }

    private void e() {
        boolean z = false;
        if (this.a && this.b) {
            boolean z2;
            this.j++;
            if (this.j % 2 == 0) {
                setText(this.h);
                com.izuiyou.a.a.b.e("bbbb_神评双击");
                z2 = false;
            } else {
                setText(this.i);
                com.izuiyou.a.a.b.e("bbbb_神评双击");
                f();
                z2 = true;
            }
            if (this.k != null) {
                this.k.a(z2);
            }
            if (this.d != null) {
                f fVar = this.d;
                if (this.j % 2 != 0) {
                    z = true;
                }
                fVar.a = z;
            }
        }
    }

    private void f() {
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("kExpandTxtTipCount", 0);
        if (i < 2) {
            g.a("双击可收回");
            i++;
            Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
            edit.putInt("kExpandTxtTipCount", i);
            edit.apply();
        }
    }

    public void setOnDoubleClickListener(b bVar) {
        this.k = bVar;
    }

    public void setOnSingleClickListener(c cVar) {
        this.l = cVar;
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
    }
}
