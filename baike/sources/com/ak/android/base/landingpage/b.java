package com.ak.android.base.landingpage;

import android.content.Context;
import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class b implements DynamicObject {
    private ILandingPageView a;

    public b(ILandingPageView iLandingPageView) {
        this.a = iLandingPageView;
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.o /*52001*/:
                    this.a.open((Context) objArr[0], (String) objArr[1], new a((DynamicObject) objArr[2]));
                    break;
            }
        }
        return null;
    }
}
