package net.tsz.afinal;

import net.tsz.afinal.a.a;

class c {
    private static c a;

    private c(d dVar, a<Object> aVar, b bVar) {
        b(dVar, aVar, bVar);
    }

    public static c a(d dVar, a<Object> aVar, b bVar) {
        if (dVar == null) {
            a = new c(dVar, aVar, bVar);
        }
        return a;
    }

    private void b(d dVar, a<Object> aVar, b bVar) {
        if (dVar != null && dVar.a() != null && dVar.a().length() > 1) {
            bVar.a("http://sprite.spriteapp.com/ad/device.php", aVar);
        }
    }
}
