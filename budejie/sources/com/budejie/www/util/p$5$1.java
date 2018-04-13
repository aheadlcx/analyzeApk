package com.budejie.www.util;

import com.budejie.www.util.p.AnonymousClass5;

class p$5$1 implements Runnable {
    final /* synthetic */ AnonymousClass5 a;

    p$5$1(AnonymousClass5 anonymousClass5) {
        this.a = anonymousClass5;
    }

    public void run() {
        this.a.b.findViewWithTag(this.a.c.get(0)).setVisibility(8);
        this.a.b.findViewWithTag(this.a.c.get(1)).setVisibility(8);
        this.a.b.findViewWithTag("举报").setVisibility(8);
        if (this.a.d) {
            this.a.b.findViewWithTag(this.a.c.get(3)).setVisibility(8);
            this.a.b.findViewWithTag(this.a.c.get(4)).setVisibility(8);
            if (this.a.b.findViewWithTag("删除") != null) {
                this.a.b.findViewWithTag("删除").setVisibility(8);
            }
            this.a.b.findViewWithTag("拉黑").setVisibility(8);
        }
    }
}
