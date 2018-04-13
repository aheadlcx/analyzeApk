package com.budejie.www.adapter.a;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.PersonalProfileActivity;

public class o$a implements OnClickListener {
    final /* synthetic */ o a;
    private String b;
    private Context c;

    private o$a(o oVar, String str, Context context) {
        this.a = oVar;
        this.b = "";
        this.b = str;
        this.c = context;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.c, PersonalProfileActivity.class);
        intent.putExtra(PersonalProfileActivity.c, this.b);
        this.c.startActivity(intent);
    }
}
