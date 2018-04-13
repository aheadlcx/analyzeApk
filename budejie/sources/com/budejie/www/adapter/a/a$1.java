package com.budejie.www.adapter.a;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.activity.DetailContentActivity;

class a$1 implements OnClickListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        String string = a.a(this.a).getString(R.string.help_page_url);
        Intent intent = new Intent(a.a(this.a), DetailContentActivity.class);
        intent.putExtra("operator", "help");
        intent.putExtra("url", string);
        a.a(this.a).startActivity(intent);
    }
}
