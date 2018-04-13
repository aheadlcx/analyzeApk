package com.volokh.danylo.visibility_utils.b;

import android.view.View;

public interface a {
    void deactivate(View view, int i);

    boolean getIsPlayArea(View view);

    int getVisibilityPercents(View view);

    void setActive(View view, int i, boolean z, boolean z2);
}
