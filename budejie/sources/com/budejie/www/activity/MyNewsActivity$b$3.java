package com.budejie.www.activity;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.activity.MyNewsActivity.b;
import com.budejie.www.bean.CommentItemForNews;
import com.budejie.www.util.aa;

class MyNewsActivity$b$3 extends ClickableSpan {
    final /* synthetic */ b a;

    MyNewsActivity$b$3(b bVar) {
        this.a = bVar;
    }

    public void onClick(View view) {
        aa.b("clickableSpan", "clickableSpan onClick");
        MyNewsActivity.a(this.a.a, System.currentTimeMillis());
        b.a(this.a, (CommentItemForNews) view.getTag(R.id.COMMENT));
    }

    public void updateDrawState(@NonNull TextPaint textPaint) {
        super.updateDrawState(textPaint);
        aa.b("clickableSpan", "clickableSpan updateDrawState");
        textPaint.setColor(this.a.a.b.getResources().getColor(R.color.post_hot_comment_name_color));
        textPaint.setUnderlineText(false);
        textPaint.clearShadowLayer();
    }
}
