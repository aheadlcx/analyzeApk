package qsbk.app.share;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import qsbk.app.model.Article;

final class o implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ EditText g;
    final /* synthetic */ CheckBox h;
    final /* synthetic */ Article i;
    final /* synthetic */ AlertDialog j;

    o(Context context, String str, String str2, String str3, String str4, String str5, EditText editText, CheckBox checkBox, Article article, AlertDialog alertDialog) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = editText;
        this.h = checkBox;
        this.i = article;
        this.j = alertDialog;
    }

    public void onClick(View view) {
        Share2QiuyouCircleDialogHelper.b(this.a, this.b, this.c, this.d, this.e, this.f, this.g.getText().toString());
        if (this.h.isChecked()) {
            Share2QiuyouCircleDialogHelper.b(this.a, this.i, this.g.getText().toString());
        }
        this.j.dismiss();
    }
}
