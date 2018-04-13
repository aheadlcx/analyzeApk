package com.budejie.www.activity.mycomment;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.widget.CmtPopupWindow;

public class e extends c<d> {
    Activity a;
    a b;
    CmtPopupWindow c;

    public interface a {
        void a(View view, d dVar);

        void b(View view, d dVar);

        void c(View view, d dVar);
    }

    public e(Activity activity, a aVar, CmtPopupWindow cmtPopupWindow) {
        this.a = activity;
        this.b = aVar;
        this.c = cmtPopupWindow;
    }

    public void a(d dVar) {
        for (int i = 0; i < this.d.size(); i++) {
            d dVar2 = (d) this.d.get(i);
            Object d = dVar2.d();
            if ((d instanceof d) && ((d) d).a.id == dVar.a.id) {
                this.d.remove(dVar2);
                break;
            }
        }
        notifyDataSetChanged();
    }

    protected d a(d dVar, int i) {
        ListItemObject listItemObject = dVar.c;
        if (dVar.d == 1) {
            return new com.budejie.www.adapter.d.a(this.a, null, dVar.c, i);
        }
        if (!TextUtils.isEmpty(listItemObject.getVoiceUri())) {
            return new k(this.a, this.b, dVar, i, this.c);
        } else if (!TextUtils.isEmpty(listItemObject.getVideouri())) {
            return new i(this.a, this.b, dVar, i, this.c);
        } else if (!TextUtils.isEmpty(listItemObject.getIs_gif()) && "1".equals(listItemObject.getIs_gif())) {
            return new b(this.a, this.b, dVar, i, this.c);
        } else if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
            return new g(this.a, this.b, dVar, i, this.c);
        } else {
            return new b(this.a, this.b, dVar, i, this.c);
        }
    }
}
