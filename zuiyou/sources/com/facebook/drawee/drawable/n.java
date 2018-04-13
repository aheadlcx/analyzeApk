package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

public class n {
    @Nullable
    public static m a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof m) {
            return (m) drawable;
        }
        if (drawable instanceof c) {
            return a(((c) drawable).a());
        }
        if (drawable instanceof a) {
            a aVar = (a) drawable;
            int a = aVar.a();
            for (int i = 0; i < a; i++) {
                Drawable a2 = a(aVar.a(i));
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }
}
