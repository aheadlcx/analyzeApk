package com.budejie.www.adapter.a;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.htmlpage.NoViewActivity;
import com.budejie.www.type.MySquareIcon;

class f$1 implements OnClickListener {
    final /* synthetic */ MySquareIcon a;
    final /* synthetic */ f b;

    f$1(f fVar, MySquareIcon mySquareIcon) {
        this.b = fVar;
        this.a = mySquareIcon;
    }

    public void onClick(View view) {
        f.a(this.b).startActivity(new Intent(f.a(this.b), NoViewActivity.class).setData(Uri.parse(this.a.getUrl())).addFlags(268435456));
    }
}
