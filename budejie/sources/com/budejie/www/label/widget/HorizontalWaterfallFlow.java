package com.budejie.www.label.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import java.util.ArrayList;
import java.util.Iterator;

public class HorizontalWaterfallFlow extends LinearLayout {
    private Context a;
    private HorizontalScrollView b;
    private ArrayList<LinearLayout> c;
    private OnClickListener d;
    private ArrayList<LabelBean> e;
    private int f;
    private int g;
    private int h;
    private int i = 3;

    public HorizontalWaterfallFlow(Context context) {
        super(context);
        Log.d("HorizontalWaterfallFlow", "HorizontalWaterfallFlow(Context context)");
        this.a = context;
        this.g = an.a(context, 5);
        this.h = an.a(context, 10);
    }

    public HorizontalWaterfallFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d("HorizontalWaterfallFlow", "HorizontalWaterfallFlow(Context context, AttributeSet attrs)");
        this.a = context;
        this.g = an.a(context, 5);
        this.h = an.a(context, 10);
        this.f = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        LayoutInflater.from(context).inflate(R.layout.horizontal_waterfall_flow_layout, this, true);
        this.b = (HorizontalScrollView) findViewById(R.id.horizontalscrollview);
        this.c = new ArrayList();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout02);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layout03);
        this.c.add((LinearLayout) findViewById(R.id.layout01));
        this.c.add(linearLayout);
        this.c.add(linearLayout2);
    }

    public void setLines(int i) {
        if (i > 0 && i <= 3) {
            this.i = i;
        }
    }

    public void setTags(ArrayList<LabelBean> arrayList) {
        Log.d("HorizontalWaterfallFlow", "setTags(ArrayList<LabelBean> tags)");
        this.e = arrayList;
        Iterator it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            a((LabelBean) it.next(), i);
            int i2 = i + 1;
            if (i2 >= this.i) {
                i2 = 0;
            }
            i = i2;
        }
    }

    public void setTagClickListener(OnClickListener onClickListener) {
        this.d = onClickListener;
    }

    private void a(LabelBean labelBean, int i) {
        Log.d("HorizontalWaterfallFlow", "addChildToLayout(Tag tag, int line)");
        View textView = new TextView(this.a);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, this.g, this.h);
        textView.setLayoutParams(layoutParams);
        textView.setText(labelBean.getTheme_name());
        textView.setTextSize(16.0f);
        textView.setGravity(17);
        textView.setTextColor(getResources().getColor(j.bi));
        textView.setBackgroundResource(j.bk);
        textView.setOnClickListener(this.d);
        textView.setTag(labelBean);
        ((LinearLayout) this.c.get(i)).addView(textView);
    }

    public void a(TextView textView) {
        Log.d("HorizontalWaterfallFlow", "refreshChild(TextView childView)");
        if (textView != null) {
            ((LabelBean) textView.getTag()).setSelected(!((LabelBean) textView.getTag()).isSelected());
            if (((LabelBean) textView.getTag()).isSelected()) {
                textView.setBackgroundResource(j.bj);
            } else {
                textView.setBackgroundResource(j.bk);
            }
        }
    }

    public TextView a(int i) {
        Log.d("HorizontalWaterfallFlow", "getChild(int position)");
        return (TextView) ((LinearLayout) this.c.get(i % this.i)).getChildAt(i / this.i);
    }

    public int a(String str) {
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                LabelBean labelBean = (LabelBean) it.next();
                if (str.equals(labelBean.getTheme_name())) {
                    return this.e.indexOf(labelBean);
                }
            }
        }
        return -1;
    }

    public void a() {
        if (this.b != null) {
            this.b.getWidth();
            this.b.scrollBy(this.f, 0);
        }
    }
}
