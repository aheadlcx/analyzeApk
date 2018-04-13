package com.flurry.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Html;
import android.text.SpannedString;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

final class ab extends RelativeLayout {
    private final SpannedString a = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px'>&lt; Previous</div></html>"));
    private final SpannedString b = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px;color:#ffA500'>&lt; Previous</div></html>"));

    public ab(CatalogActivity catalogActivity, Context context) {
        super(context);
        setBackgroundColor(-16777216);
        View textView = new TextView(context);
        textView.setTextColor(ColorStateList.valueOf(-1));
        textView.setId(10001);
        textView.setText(this.a);
        textView.setPadding(5, 2, 5, 2);
        textView.setFocusable(true);
        textView.setOnFocusChangeListener(new ac(this, textView));
        textView.setOnClickListener(catalogActivity);
        textView.setEnabled(true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(2, 0, 0, 0);
        layoutParams.addRule(4);
        addView(textView, layoutParams);
    }
}
