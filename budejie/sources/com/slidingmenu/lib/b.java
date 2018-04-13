package com.slidingmenu.lib;

class b extends com.slidingmenu.lib.CustomViewAbove.b {
    final /* synthetic */ CustomViewAbove a;

    b(CustomViewAbove customViewAbove) {
        this.a = customViewAbove;
    }

    public void a(int i) {
        if (this.a.t != null) {
            switch (i) {
                case 0:
                case 2:
                    this.a.t.setChildrenEnabled(true);
                    return;
                case 1:
                    this.a.t.setChildrenEnabled(false);
                    return;
                default:
                    return;
            }
        }
    }
}
