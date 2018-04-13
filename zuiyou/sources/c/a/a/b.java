package c.a.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import c.a.i.c;

public class b implements g {
    public View a(@NonNull Context context, String str, @NonNull AttributeSet attributeSet) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 1677098064:
                if (str.equals("android.support.v7.widget.CardView")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new c(context, attributeSet);
            default:
                return null;
        }
    }
}
