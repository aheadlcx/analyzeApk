package com.budejie.www.adapter.d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.bean.ListItemObject;

public class l extends a {
    private Context a;

    public /* synthetic */ Object d() {
        return a();
    }

    public l(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = activity;
    }

    public View b() {
        ViewGroup viewGroup = (ViewGroup) View.inflate(this.a, R.layout.post_update_app_prompt_layout, null);
        ((TextView) viewGroup.findViewById(R.id.update_btn)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.budejie.com/budejie/download.php")));
            }
        });
        return viewGroup;
    }

    public int c() {
        return RowType.UPDATE_APP_ROW.ordinal();
    }

    public void a(b bVar) {
    }

    public ListItemObject a() {
        return null;
    }
}
