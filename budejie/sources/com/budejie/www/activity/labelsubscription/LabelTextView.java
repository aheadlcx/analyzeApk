package com.budejie.www.activity.labelsubscription;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.h.c;

public class LabelTextView extends TextView {
    private int a = getResources().getColor(c.a().b(R.attr.event_label_bg_color));
    private int b = getResources().getColor(c.a().b(R.attr.event_label_text_color));
    private int c = getResources().getDimensionPixelOffset(R.dimen.activity_label_fillet_radius);
    private float d = getResources().getDimension(R.dimen.divide_line_height);
    private SpannableStringBuilder e = new SpannableStringBuilder();
    private String f = "  ";
    private ListItemObject g;
    private a h;

    public interface a {
        void a(ListItemObject listItemObject, int i);
    }

    public LabelTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public LabelTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LabelTextView(Context context) {
        super(context);
    }

    public void setLabelData(ListItemObject listItemObject) {
        int i = 0;
        this.g = listItemObject;
        this.e.clear();
        this.e.append(this.f);
        if (TextUtils.isEmpty(listItemObject.getPlateNames())) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        String[] split = listItemObject.getPlateNames().split(",");
        while (i < split.length) {
            a(split[i], i);
            i++;
        }
        setText(this.e);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void a(String str, final int i) {
        int length = this.e.length() > this.f.length() ? this.e.append(this.f).length() : this.e.length();
        this.e.append(str);
        this.e.setSpan(new b(this) {
            final /* synthetic */ LabelTextView b;

            public void onClick(View view) {
                this.b.h.a(this.b.g, i);
            }
        }, length, this.e.length(), 33);
    }

    public void setOnLabelClickListener(a aVar) {
        this.h = aVar;
    }
}
