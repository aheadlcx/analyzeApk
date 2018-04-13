package com.scwang.smartrefresh.layout.c;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout.a;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class b implements d {
    private View a;
    private SpinnerStyle b;

    public b(View view) {
        this.a = view;
    }

    @NonNull
    public View getView() {
        return this.a;
    }

    public int a(@NonNull h hVar, boolean z) {
        return 0;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        if (this.b != null) {
            return this.b;
        }
        LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof a) {
            this.b = ((a) layoutParams).b;
            if (this.b != null) {
                return this.b;
            }
        }
        if (layoutParams == null || layoutParams.height != 0) {
            SpinnerStyle spinnerStyle = SpinnerStyle.Translate;
            this.b = spinnerStyle;
            return spinnerStyle;
        }
        spinnerStyle = SpinnerStyle.Scale;
        this.b = spinnerStyle;
        return spinnerStyle;
    }

    public void a(@NonNull g gVar, int i, int i2) {
        LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof a) {
            gVar.b(((a) layoutParams).a);
        }
    }

    public boolean a() {
        return false;
    }

    public void a(float f, int i, int i2) {
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
    }

    public void b(@NonNull h hVar, int i, int i2) {
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
    }

    public boolean a(boolean z) {
        return false;
    }
}
