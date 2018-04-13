package com.budejie.www.g;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.bean.Fans;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.u;
import java.io.Serializable;

public class b$b implements OnClickListener {
    final /* synthetic */ b a;
    private Bundle b;

    public b$b(b bVar, Bundle bundle) {
        this.a = bVar;
        this.b = bundle;
    }

    public void onClick(View view) {
        if (b.e(this.a)) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof Fans)) {
                u.a((Fans) tag);
            }
            String string = this.b.getString(PersonalProfileActivity.d);
            if (string == null) {
                string = this.b.getString(PersonalProfileActivity.c);
                Serializable valueOf = Boolean.valueOf(this.b.getBoolean(PersonalProfileActivity.f));
                Intent intent = new Intent(b.a(this.a), PersonalProfileActivity.class);
                aa.a("ListenerEx", "uid-->" + string);
                intent.putExtra(PersonalProfileActivity.c, string);
                intent.putExtra(PersonalProfileActivity.f, valueOf);
                b.a(this.a).startActivity(intent);
            } else if (string.equals("login")) {
                an.a(b.a(this.a), 0, null, null, 0);
            } else {
                Intent intent2 = new Intent(b.a(this.a), PersonalProfileActivity.class);
                String string2 = this.b.getString(PersonalProfileActivity.c);
                Serializable valueOf2 = Boolean.valueOf(this.b.getBoolean(PersonalProfileActivity.f));
                aa.a("ListenerEx", "uid-->" + string2);
                intent2.putExtra(PersonalProfileActivity.c, string2);
                intent2.putExtra(PersonalProfileActivity.f, valueOf2);
                b.a(this.a).startActivity(intent2);
            }
        }
    }
}
