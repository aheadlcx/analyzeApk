package com.bdj.picture.edit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.e.a;

public class TabsLayout extends LinearLayout implements OnClickListener {
    private a a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;

    public TabsLayout(Context context) {
        super(context);
    }

    public TabsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.b = (LinearLayout) findViewById(d.tabsWatermark);
        this.b.setTag(Integer.valueOf(1));
        this.b.setOnClickListener(this);
        this.c = (LinearLayout) findViewById(d.tabsPixelizaton);
        this.c.setTag(Integer.valueOf(2));
        this.c.setOnClickListener(this);
        this.d = (LinearLayout) findViewById(d.tabsPaster);
        this.d.setTag(Integer.valueOf(3));
        this.d.setOnClickListener(this);
        this.e = (LinearLayout) findViewById(d.tabsFilter);
        this.e.setTag(Integer.valueOf(4));
        this.e.setOnClickListener(this);
        this.f = (LinearLayout) findViewById(d.tabsRotation);
        this.f.setTag(Integer.valueOf(5));
        this.f.setOnClickListener(this);
        this.e.setSelected(true);
        this.a.a(BVType.IE_FILTER);
    }

    public void setEditBarChangeListener(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        switch (((Integer) view.getTag()).intValue()) {
            case 1:
                if (!this.b.isSelected()) {
                    this.b.setSelected(true);
                    this.e.setSelected(false);
                    this.a.a(BVType.IE_WATERMARK);
                    return;
                }
                return;
            case 2:
                this.a.a(BVType.IE_PIXELIZATION);
                return;
            case 3:
                this.a.a(BVType.IE_PASTER);
                return;
            case 4:
                if (!this.e.isSelected()) {
                    this.e.setSelected(true);
                    this.b.setSelected(false);
                    this.a.a(BVType.IE_FILTER);
                    return;
                }
                return;
            case 5:
                this.a.a(BVType.IE_ROTATION);
                return;
            default:
                return;
        }
    }
}
