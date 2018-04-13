package com.slidingmenu.lib;

import com.slidingmenu.lib.CustomViewAbove.a;

class c implements a {
    final /* synthetic */ SlidingMenu a;

    c(SlidingMenu slidingMenu) {
        this.a = slidingMenu;
    }

    public void a(int i, float f, int i2) {
    }

    public void a(int i) {
        if (i == 0 && SlidingMenu.a(this.a) != null) {
            SlidingMenu.a(this.a).a();
        } else if (i == 1 && SlidingMenu.b(this.a) != null) {
            SlidingMenu.b(this.a).a();
        }
    }
}
