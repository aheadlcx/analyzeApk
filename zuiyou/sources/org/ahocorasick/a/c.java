package org.ahocorasick.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ahocorasick.a.a.a;
import org.ahocorasick.a.a.b;

public class c {
    private d a;
    private b b;

    public Collection<a> a(CharSequence charSequence) {
        b aVar = new a();
        a(charSequence, aVar);
        List a = aVar.a();
        if (this.a.c()) {
            a(charSequence, a);
        }
        if (this.a.d()) {
            b(charSequence, a);
        }
        if (!this.a.b()) {
            new org.ahocorasick.interval.b(a).a(a);
        }
        return a;
    }

    public void a(CharSequence charSequence, b bVar) {
        b bVar2 = this.b;
        int i = 0;
        while (i < charSequence.length()) {
            Character valueOf = Character.valueOf(charSequence.charAt(i));
            if (this.a.e()) {
                valueOf = Character.valueOf(Character.toLowerCase(valueOf.charValue()));
            }
            bVar2 = a(bVar2, valueOf);
            if (!a(i, bVar2, bVar) || !this.a.a()) {
                i++;
            } else {
                return;
            }
        }
    }

    private boolean a(CharSequence charSequence, a aVar) {
        return (aVar.a() != 0 && Character.isAlphabetic(charSequence.charAt(aVar.a() - 1))) || (aVar.b() + 1 != charSequence.length() && Character.isAlphabetic(charSequence.charAt(aVar.b() + 1)));
    }

    private void a(CharSequence charSequence, List<a> list) {
        List<a> arrayList = new ArrayList();
        for (a aVar : list) {
            if (a(charSequence, aVar)) {
                arrayList.add(aVar);
            }
        }
        for (a aVar2 : arrayList) {
            list.remove(aVar2);
        }
    }

    private void b(CharSequence charSequence, List<a> list) {
        long length = (long) charSequence.length();
        List<a> arrayList = new ArrayList();
        for (a aVar : list) {
            if (!((aVar.a() == 0 || Character.isWhitespace(charSequence.charAt(aVar.a() - 1))) && (((long) (aVar.b() + 1)) == length || Character.isWhitespace(charSequence.charAt(aVar.b() + 1))))) {
                arrayList.add(aVar);
            }
        }
        for (a aVar2 : arrayList) {
            list.remove(aVar2);
        }
    }

    private b a(b bVar, Character ch) {
        b a = bVar.a(ch);
        while (a == null) {
            bVar = bVar.b();
            a = bVar.a(ch);
        }
        return a;
    }

    private boolean a(int i, b bVar, b bVar2) {
        boolean z = false;
        Collection<String> a = bVar.a();
        if (!(a == null || a.isEmpty())) {
            for (String str : a) {
                bVar2.a(new a((i - str.length()) + 1, i, str));
                z = true;
            }
        }
        return z;
    }
}
