package com.budejie.www.activity.mycomment;

import android.os.Bundle;
import android.view.View;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.mycomment.e.a;
import com.budejie.www.util.an;

class a$4 implements a {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public void a(View view, d dVar) {
        an.a(dVar.c, a.d(this.a), a.e(this.a));
        com.budejie.www.util.a.a(this.a.getActivity(), dVar.c, "", false);
    }

    public void b(View view, d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, dVar.c.getUid());
        a.f(this.a).a(7, bundle).onClick(view);
    }

    public void c(View view, d dVar) {
    }
}
