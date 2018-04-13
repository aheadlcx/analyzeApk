package com.scwang.smartrefresh.layout.a;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.e.e;

public interface f extends e {
    int a(@NonNull h hVar, boolean z);

    void a(float f, int i, int i2);

    void a(@NonNull g gVar, int i, int i2);

    boolean a();

    void b(@NonNull h hVar, int i, int i2);

    @NonNull
    SpinnerStyle getSpinnerStyle();

    @NonNull
    View getView();

    void setPrimaryColors(@ColorInt int... iArr);
}
