package com.budejie.www.widget.parsetagview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.util.j;

public class c extends ClickableSpan {
    private static String a = c.class.getSimpleName();
    private Context b;
    private String c;
    private Uri d = Uri.parse(this.c);
    private boolean e;

    public c(Context context, String str, boolean z) {
        this.c = str;
        this.b = context;
        this.e = z;
        Log.d(a, "mUrl=" + this.c);
        Log.d(a, "mUri=" + this.d);
    }

    public void onClick(View view) {
        if (this.e) {
            Context context = view.getContext();
            Log.d(a, "onClick" + this.d.getScheme());
            String substring;
            Intent intent;
            if (this.d.getScheme().startsWith("budejie.tag.user://".substring(0, "budejie.tag.user://".lastIndexOf(":")))) {
                substring = this.c.substring(this.c.lastIndexOf("@") + 1, this.c.length());
                intent = new Intent();
                intent.setClass(context, PersonalProfileActivity.class);
                intent.putExtra(PersonalProfileActivity.e, substring);
                context.startActivity(intent);
            } else if (this.d.getScheme().startsWith("budejie.tag.topic://".substring(0, "budejie.tag.topic://".lastIndexOf(":")))) {
                substring = this.c.substring(this.c.indexOf("#") + 1, this.c.lastIndexOf("#"));
                intent = new Intent();
                intent.setClass(context, CommonLabelActivity.class);
                intent.putExtra("theme_name", substring);
                context.startActivity(intent);
            }
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.b.getResources().getColor(j.bI));
        textPaint.setUnderlineText(false);
    }
}
