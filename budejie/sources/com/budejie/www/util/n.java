package com.budejie.www.util;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class n {
    public Button a(Context context) {
        Button button = new Button(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(an.a(context, 35), an.a(context, 35));
        layoutParams.setMargins(10, 10, 10, 10);
        button.setLayoutParams(layoutParams);
        return button;
    }
}
