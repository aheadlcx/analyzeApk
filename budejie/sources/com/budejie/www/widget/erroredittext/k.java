package com.budejie.www.widget.erroredittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;

public class k {
    public CharSequence a;
    private View b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private Context k;
    private a l;
    private boolean m;
    private b n;
    private boolean o;

    class a {
        Drawable a;
        Drawable b;
        int c;
        int d;
        int e;
        int f;
    }

    private static class b extends PopupWindow {
        private boolean a = false;
        private TextView b;
        private int c = 0;
        private int d = 0;

        b(Context context, TextView textView, int i, int i2) {
            super(textView, i, i2);
            this.b = textView;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$styleable.Theme);
            this.c = obtainStyledAttributes.getResourceId(0, 0);
            this.d = obtainStyledAttributes.getResourceId(1, 0);
            this.b.setBackgroundResource(this.c);
        }

        void a(boolean z) {
            this.a = z;
            if (z) {
                this.b.setBackgroundResource(this.d);
            } else {
                this.b.setBackgroundResource(this.c);
            }
        }

        public void update(int i, int i2, int i3, int i4, boolean z) {
            super.update(i, i2, i3, i4, z);
            boolean isAboveAnchor = isAboveAnchor();
            if (isAboveAnchor != this.a) {
                a(isAboveAnchor);
            }
        }
    }

    public k(Context context, View view) {
        this.k = context;
        this.b = view;
        e();
    }

    private void e() {
        this.c = this.b.getTop();
        this.d = this.b.getBottom();
        this.e = this.b.getLeft();
        this.f = this.b.getRight();
        this.g = this.b.getPaddingTop();
        this.h = this.b.getPaddingBottom();
        this.i = this.b.getPaddingLeft();
        this.j = this.b.getPaddingRight();
    }

    public void a(CharSequence charSequence, Drawable drawable) {
        a(charSequence, drawable, true, true);
    }

    public void a(CharSequence charSequence, Drawable drawable, boolean z, boolean z2) {
        Log.d("SetErrorHandler", ".setError(error, icon, showError, showCompoundDrawableOnRight)...");
        if (drawable != null) {
            Log.d("SetErrorHandler", "...icon is not null...");
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        CharSequence stringOrSpannedString = TextUtils.stringOrSpannedString(charSequence);
        this.m = true;
        this.a = stringOrSpannedString;
        this.m = true;
        a aVar = this.l;
        if ((this.b instanceof TextView) && stringOrSpannedString != null) {
            if (z2) {
                Log.d("SetErrorHandler", "...showing CompoundDrawable on right)...");
                ((TextView) this.b).setCompoundDrawables(null, null, drawable, null);
            } else {
                ((TextView) this.b).setCompoundDrawables(drawable, null, null, null);
            }
        }
        if (stringOrSpannedString == null) {
            if (this.n != null) {
                if (this.n.isShowing()) {
                    this.n.dismiss();
                }
                if (this.b instanceof TextView) {
                    ((TextView) this.b).setCompoundDrawables(null, null, null, null);
                }
                this.n = null;
            }
        } else if (z) {
            a();
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                }
            }, 1000);
        }
    }

    public void a() {
        Log.d("SetErrorHandler", ".showError()...");
        if (this.b.getWindowToken() == null) {
            this.o = true;
            return;
        }
        TextView textView;
        if (this.n == null) {
            textView = (TextView) LayoutInflater.from(this.b.getContext()).inflate(R.layout.textview_hint, null);
            textView.setText(this.a);
            float f = this.k.getResources().getDisplayMetrics().density;
            this.n = new b(this.k, textView, (int) ((200.0f * f) + 0.5f), (int) ((f * 50.0f) + 0.5f));
            this.n.setFocusable(false);
            this.n.setInputMethodMode(1);
        }
        Log.d("SetErrorHandler", "...error: " + this.a);
        textView = (TextView) this.n.getContentView();
        a(this.n, this.a, textView);
        textView.setText(this.a);
        this.n.showAsDropDown(this.b, f(), g());
        this.n.a(this.n.isAboveAnchor());
    }

    public void b() {
        if (this.n != null && this.n.isShowing()) {
            this.n.dismiss();
        }
        this.o = false;
    }

    private void a(PopupWindow popupWindow, CharSequence charSequence, TextView textView) {
        float f = 0.0f;
        int paddingLeft = textView.getPaddingLeft() + textView.getPaddingRight();
        int paddingTop = textView.getPaddingTop() + textView.getPaddingBottom();
        CharSequence charSequence2 = charSequence;
        Layout staticLayout = new StaticLayout(charSequence2, textView.getPaint(), this.k.getResources().getDimensionPixelSize(R.dimen.textview_error_popup_default_width), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        for (int i = 0; i < staticLayout.getLineCount(); i++) {
            f = Math.max(f, staticLayout.getLineWidth(i));
        }
        Log.d("SetErrorHandler", "max: " + f + ", height: " + staticLayout.getHeight());
        popupWindow.setWidth(((int) Math.ceil((double) f)) + paddingLeft);
        popupWindow.setHeight(staticLayout.getHeight() + paddingTop);
    }

    private int f() {
        a aVar = this.l;
        return this.b.getWidth() - this.n.getWidth();
    }

    private int g() {
        int d = ((this.d - this.c) - d()) - c();
        a aVar = this.l;
        int c = ((d - (aVar != null ? aVar.e : 0)) / 2) + c();
        return 0 - (this.b.getHeight() / 2);
    }

    public int c() {
        a aVar = this.l;
        if (aVar == null || aVar.a == null) {
            return this.g;
        }
        return aVar.c + (this.g + aVar.f);
    }

    public int d() {
        a aVar = this.l;
        if (aVar == null || aVar.b == null) {
            return this.h;
        }
        return aVar.d + (this.h + aVar.f);
    }
}
