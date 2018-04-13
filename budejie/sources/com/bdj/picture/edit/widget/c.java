package com.bdj.picture.edit.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.d;
import com.bdj.picture.edit.util.b;
import com.bdj.picture.edit.util.i;
import java.util.List;

public abstract class c {
    public Activity a;
    protected a b;
    protected com.bdj.picture.edit.bean.a c = new com.bdj.picture.edit.bean.a();
    private RelativeLayout d;

    public interface a {
        void a(com.bdj.picture.edit.bean.a aVar);
    }

    public abstract void a(HorizontalScrollView horizontalScrollView, BVType bVType);

    public abstract void a(d dVar);

    public c(Activity activity) {
        this.a = activity;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(HorizontalScrollView horizontalScrollView, List<d> list) {
        a(horizontalScrollView, list, new LinearLayout(this.a));
    }

    public int a(float f) {
        return (int) ((this.a.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public void a(HorizontalScrollView horizontalScrollView, List<d> list, LinearLayout linearLayout) {
        if (horizontalScrollView != null) {
            if (linearLayout == null) {
                linearLayout = new LinearLayout(this.a);
            }
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            for (d dVar : list) {
                View inflate = LayoutInflater.from(this.a).inflate(e.inflat_item_editbar, null);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(a(69.0f), a(69.0f));
                layoutParams.setMargins(10, 10, 10, 10);
                inflate.setLayoutParams(layoutParams);
                ImageView imageView = (ImageView) inflate.findViewById(com.bdj.picture.edit.a.d.itemImageView);
                imageView.setBackgroundColor(15132390);
                TextView textView = (TextView) inflate.findViewById(com.bdj.picture.edit.a.d.itemTextView);
                RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(com.bdj.picture.edit.a.d.rl);
                Bitmap a = i.a(this.a, dVar.d());
                if (a != null) {
                    if (dVar.b() == 104) {
                        imageView.setImageDrawable(new BitmapDrawable(a));
                    } else {
                        imageView.setBackgroundDrawable(new BitmapDrawable(a));
                    }
                    if (dVar.f()) {
                        relativeLayout.setSelected(true);
                        this.d = relativeLayout;
                        this.d.setTag(dVar);
                    }
                }
                textView.setText(dVar.c());
                if (dVar.b() == 104) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams2.leftMargin = a(0.0f);
                    layoutParams2.rightMargin = a(0.0f);
                    imageView.setLayoutParams(layoutParams2);
                }
                inflate.setOnClickListener(new c$1(this, relativeLayout, dVar));
                linearLayout.addView(inflate);
            }
            View childAt = horizontalScrollView.getChildAt(0);
            if (childAt != null) {
                b.b(this.a, childAt, 76);
                horizontalScrollView.removeAllViews();
            }
            horizontalScrollView.addView(linearLayout, 0);
            b.a(this.a, linearLayout, 76);
        }
    }
}
