package com.budejie.www.widget.erroredittext;

public class b {
    protected e a = new a();
    protected j b;

    public b(j jVar) {
        this.b = jVar;
    }

    public void a(l lVar) throws IllegalArgumentException {
        if (lVar == null) {
            throw new IllegalArgumentException("theValidator argument should not be null");
        }
        this.a.a(lVar);
    }

    public boolean a() {
        if (this.a.a(this.b.getTextContent())) {
            return true;
        }
        this.b.a(this.a.a());
        return false;
    }
}
