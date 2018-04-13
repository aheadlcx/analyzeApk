package com.budejie.www.adapter.d;

class b$1 implements Runnable {
    final /* synthetic */ b$a a;
    final /* synthetic */ b b;

    b$1(b bVar, b$a b_a) {
        this.b = bVar;
        this.a = b_a;
    }

    public void run() {
        if (this.a.b.getLineCount() > 7) {
            this.b.a.mcollapsibleState = 2;
        } else {
            this.b.a.mcollapsibleState = 1;
        }
        this.b.a(this.a);
    }
}
