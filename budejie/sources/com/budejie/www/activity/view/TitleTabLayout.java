package com.budejie.www.activity.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.util.j;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@Deprecated
public class TitleTabLayout extends LinearLayout implements OnClickListener {
    LinearLayout a;
    LinearLayout b;
    LinearLayout c;
    LinearLayout d;
    ImageView e;
    TextView f;
    ImageView g;
    TextView h;
    ImageView i;
    ImageView j;
    TextView k;
    TextView l;
    private Context m;
    private TitleTabLayout$a n;
    private String o;

    public String getPost_type() {
        return this.o;
    }

    public void setPost_type(String str) {
        this.o = str;
    }

    public TitleTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = context;
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.m).inflate(R.layout.title_popup_layout, null);
        addView(inflate, new LayoutParams(-2, -1));
        this.a = (LinearLayout) inflate.findViewById(R.id.title_popup_layout0);
        this.b = (LinearLayout) inflate.findViewById(R.id.title_popup_layout1);
        this.c = (LinearLayout) inflate.findViewById(R.id.title_popup_layout2);
        this.d = (LinearLayout) inflate.findViewById(R.id.title_popup_layout3);
        this.e = (ImageView) inflate.findViewById(R.id.title_popup_item0_img);
        this.g = (ImageView) inflate.findViewById(R.id.title_popup_item1_img);
        this.i = (ImageView) inflate.findViewById(R.id.title_popup_item2_img);
        this.j = (ImageView) inflate.findViewById(R.id.title_popup_item3_img);
        this.f = (TextView) inflate.findViewById(R.id.title_popup_item0_text);
        this.h = (TextView) inflate.findViewById(R.id.title_popup_item1_text);
        this.k = (TextView) inflate.findViewById(R.id.title_popup_item2_text);
        this.l = (TextView) inflate.findViewById(R.id.title_popup_item3_text);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.a.setSelected(true);
        a();
    }

    public void onClick(View view) {
        if (view == this.a) {
            a("new");
            this.n.a("new");
        } else if (view == this.b) {
            a("new_issue");
            this.n.a("new_issue");
        } else if (view == this.c) {
            a("suiji");
            this.n.a("suiji");
        } else if (view == this.d) {
            a("nearby");
            this.n.a("nearby");
        }
    }

    public void a() {
        this.a.setBackgroundResource(j.aD);
        this.b.setBackgroundResource(j.aD);
        this.c.setBackgroundResource(j.aD);
        this.d.setBackgroundResource(j.aD);
        this.e.setImageResource(j.aE);
        this.g.setImageResource(j.aF);
        this.i.setImageResource(j.aG);
        this.j.setImageResource(j.aH);
        try {
            ColorStateList createFromXml = ColorStateList.createFromXml(getResources(), getResources().getXml(j.aI));
            this.f.setTextColor(createFromXml);
            this.h.setTextColor(createFromXml);
            this.k.setTextColor(createFromXml);
            this.l.setTextColor(createFromXml);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str) {
        if (str.equals("new")) {
            this.d.setSelected(false);
            this.a.setSelected(true);
            this.b.setSelected(false);
            this.c.setSelected(false);
        } else if (str.equals("new_issue")) {
            this.d.setSelected(false);
            this.b.setSelected(true);
            this.a.setSelected(false);
            this.c.setSelected(false);
        } else if (str.equals("suiji")) {
            this.d.setSelected(false);
            this.c.setSelected(true);
            this.b.setSelected(false);
            this.a.setSelected(false);
        } else if (str.equals("nearby")) {
            this.d.setSelected(true);
            this.c.setSelected(false);
            this.b.setSelected(false);
            this.a.setSelected(false);
        }
    }

    public void setTabSelectedStateListener(TitleTabLayout$a titleTabLayout$a) {
        this.n = titleTabLayout$a;
    }

    public TitleTabLayout$a getTabSelectedStateListener() {
        return this.n;
    }
}
