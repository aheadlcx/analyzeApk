package c.a.e.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import c.a.a.g;
import c.a.e.b.b;
import c.a.e.b.c;
import c.a.e.b.d;
import c.a.e.b.e;
import c.a.e.b.f;
import c.a.e.b.h;
import c.a.e.b.i;

public class a implements g {
    public View a(@NonNull Context context, String str, @NonNull AttributeSet attributeSet) {
        if (!str.startsWith("android.support.design.widget.")) {
            return null;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1830764433:
                if (str.equals("android.support.design.widget.TextInputLayout")) {
                    obj = 2;
                    break;
                }
                break;
            case -1138726461:
                if (str.equals("android.support.design.widget.CollapsingToolbarLayout")) {
                    obj = 8;
                    break;
                }
                break;
            case -272444186:
                if (str.equals("android.support.design.widget.FloatingActionButton")) {
                    obj = 5;
                    break;
                }
                break;
            case 170302044:
                if (str.equals("android.support.design.widget.TextInputEditText")) {
                    obj = 3;
                    break;
                }
                break;
            case 285085340:
                if (str.equals("android.support.design.widget.BottomNavigationView")) {
                    obj = 6;
                    break;
                }
                break;
            case 285456578:
                if (str.equals("android.support.design.widget.CoordinatorLayout")) {
                    obj = 7;
                    break;
                }
                break;
            case 796212404:
                if (str.equals("android.support.design.widget.AppBarLayout")) {
                    obj = null;
                    break;
                }
                break;
            case 890321297:
                if (str.equals("android.support.design.widget.NavigationView")) {
                    obj = 4;
                    break;
                }
                break;
            case 951543143:
                if (str.equals("android.support.design.widget.TabLayout")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new c.a.e.b.a(context, attributeSet);
            case 1:
                return new c.a.e.b.g(context, attributeSet);
            case 2:
                return new i(context, attributeSet);
            case 3:
                return new h(context, attributeSet);
            case 4:
                return new f(context, attributeSet);
            case 5:
                return new e(context, attributeSet);
            case 6:
                return new b(context, attributeSet);
            case 7:
                return new d(context, attributeSet);
            case 8:
                return new c(context, attributeSet);
            default:
                return null;
        }
    }
}
