package c.a.c.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import c.a.a.g;

public class a implements g {
    public View a(@NonNull Context context, String str, @NonNull AttributeSet attributeSet) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 1050766810:
                if (str.equals("android.support.constraint.ConstraintLayout")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new c.a.c.a(context, attributeSet);
            default:
                return null;
        }
    }
}
