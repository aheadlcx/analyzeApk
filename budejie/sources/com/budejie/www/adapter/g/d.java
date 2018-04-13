package com.budejie.www.adapter.g;

import android.content.Context;
import android.view.ViewGroup;
import com.budejie.www.adapter.g.b.a;
import com.budejie.www.adapter.g.b.c;
import com.budejie.www.adapter.g.b.e;
import com.budejie.www.adapter.g.b.f;
import com.budejie.www.adapter.g.b.g;
import com.budejie.www.adapter.g.b.h;
import com.budejie.www.adapter.g.b.i;
import com.budejie.www.adapter.g.b.j;
import com.budejie.www.adapter.g.b.k;
import com.budejie.www.adapter.g.b.l;
import com.budejie.www.adapter.g.b.m;
import com.budejie.www.adapter.g.b.n;
import com.budejie.www.adapter.g.b.o;
import com.budejie.www.adapter.g.b.q;
import com.budejie.www.adapter.g.b.r;
import com.budejie.www.adapter.g.b.s;
import com.budejie.www.adapter.g.b.t;
import com.budejie.www.adapter.g.b.u;
import com.budejie.www.adapter.g.b.v;
import com.budejie.www.adapter.g.b.w;
import java.util.ArrayList;
import java.util.List;

public class d {
    public static List<a> a(Context context, ViewGroup viewGroup, c cVar, b bVar) {
        List arrayList = new ArrayList();
        c(context, viewGroup, cVar.a, bVar, arrayList);
        a(context, viewGroup, cVar.b, bVar, arrayList);
        if (cVar.k) {
            a(context, viewGroup, bVar, (ArrayList) arrayList);
        }
        if (cVar.c) {
            f(context, viewGroup, bVar, arrayList);
        }
        if (cVar.e) {
            d(context, viewGroup, bVar, arrayList);
        }
        if (cVar.g) {
            c(context, viewGroup, bVar, arrayList);
        }
        if (cVar.f) {
            b(context, viewGroup, bVar, arrayList);
        }
        if (cVar.d) {
            e(context, viewGroup, bVar, arrayList);
        }
        if (cVar.h) {
            a(context, viewGroup, bVar, arrayList);
        }
        return arrayList;
    }

    private static void a(Context context, ViewGroup viewGroup, b bVar, ArrayList<a> arrayList) {
        v vVar = new v(context, bVar);
        vVar.a(viewGroup);
        arrayList.add(vVar);
    }

    private static void a(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        c cVar = new c(context, bVar);
        cVar.a(viewGroup);
        list.add(cVar);
    }

    private static void b(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        m mVar = new m(context, bVar);
        mVar.a(viewGroup);
        list.add(mVar);
    }

    private static void c(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        w wVar = new w(context, bVar);
        wVar.a(viewGroup);
        list.add(wVar);
    }

    private static void d(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        a aVar = new a(context, bVar);
        aVar.a(viewGroup);
        list.add(aVar);
    }

    private static void e(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        i iVar = new i(context, bVar);
        iVar.a(viewGroup);
        list.add(iVar);
    }

    private static void f(Context context, ViewGroup viewGroup, b bVar, List<a> list) {
        l lVar = new l(context, bVar);
        lVar.a(viewGroup);
        list.add(lVar);
    }

    private static void a(Context context, ViewGroup viewGroup, int i, b bVar, List<a> list) {
        switch (i) {
            case 1:
                b(context, viewGroup, i, bVar, list);
                j jVar = new j(context, bVar);
                jVar.a(viewGroup);
                list.add(jVar);
                return;
            case 2:
                b(context, viewGroup, i, bVar, list);
                return;
            case 3:
                b(context, viewGroup, i, bVar, list);
                u uVar = new u(context, bVar);
                uVar.a(viewGroup);
                list.add(uVar);
                return;
            case 4:
                b(context, viewGroup, i, bVar, list);
                t tVar = new t(context, bVar);
                tVar.a(viewGroup);
                list.add(tVar);
                return;
            case 5:
                b(context, viewGroup, i, bVar, list);
                k kVar = new k(context, bVar);
                kVar.a(viewGroup);
                list.add(kVar);
                return;
            case 6:
                b(context, viewGroup, i, bVar, list);
                o oVar = new o(context, bVar);
                oVar.a(viewGroup);
                list.add(oVar);
                return;
            case 7:
                b(context, viewGroup, i, bVar, list);
                n nVar = new n(context, bVar);
                nVar.a(viewGroup);
                list.add(nVar);
                return;
            case 8:
                e eVar = new e(context, bVar);
                eVar.a(viewGroup);
                list.add(eVar);
                return;
            case 9:
                g gVar = new g(context, bVar);
                gVar.a(viewGroup);
                list.add(gVar);
                return;
            case 10:
                com.budejie.www.adapter.g.b.d dVar = new com.budejie.www.adapter.g.b.d(context, bVar);
                dVar.a(viewGroup);
                list.add(dVar);
                return;
            default:
                return;
        }
    }

    private static void b(Context context, ViewGroup viewGroup, int i, b bVar, List<a> list) {
        f fVar = new f(context, bVar);
        fVar.a(viewGroup);
        list.add(fVar);
    }

    private static void c(Context context, ViewGroup viewGroup, int i, b bVar, List<a> list) {
        switch (i) {
            case 1:
                s sVar = new s(context, bVar);
                sVar.a(viewGroup);
                list.add(sVar);
                return;
            case 2:
                q qVar = new q(context, bVar);
                qVar.a(viewGroup);
                list.add(qVar);
                return;
            case 4:
                h hVar = new h(context, bVar);
                hVar.a(viewGroup);
                list.add(hVar);
                return;
            case 5:
                r rVar = new r(context, bVar);
                rVar.a(viewGroup);
                list.add(rVar);
                return;
            default:
                return;
        }
    }
}
