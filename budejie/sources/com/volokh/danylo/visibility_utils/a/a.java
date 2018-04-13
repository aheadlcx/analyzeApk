package com.volokh.danylo.visibility_utils.a;

import com.volokh.danylo.visibility_utils.scroll_utils.ScrollDirectionDetector;

public abstract class a implements c, com.volokh.danylo.visibility_utils.scroll_utils.ScrollDirectionDetector.a {
    private static final String a = a.class.getSimpleName();
    private final ScrollDirectionDetector b = new ScrollDirectionDetector(this);

    protected abstract void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar);

    protected abstract void b(com.volokh.danylo.visibility_utils.scroll_utils.a aVar);

    public void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, int i2, int i3) {
        this.b.a(aVar, i);
        switch (i3) {
            case 1:
                b(aVar);
                return;
            case 2:
                b(aVar);
                return;
            default:
                return;
        }
    }
}
