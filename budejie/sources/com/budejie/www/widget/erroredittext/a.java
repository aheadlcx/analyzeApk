package com.budejie.www.widget.erroredittext;

public class a extends e {
    public a() {
        super(null);
    }

    public boolean a(String str) {
        for (l lVar : this.a) {
            if (!lVar.a(str)) {
                this.b = lVar.a();
                return false;
            }
        }
        return true;
    }
}
