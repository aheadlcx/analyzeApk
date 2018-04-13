package com.bdj.picture.edit.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import java.util.List;

public class StickerTabs extends RelativeLayout {
    @SuppressLint({"HandlerLeak"})
    Handler a = new StickerTabs$1(this);
    private Context b;
    private LinearLayout c;
    private RadioGroup d;
    private int e = 0;
    private ImageView f;
    private int g = 0;
    private int h = 0;
    private int i;
    private a j;
    private OnClickListener k = new StickerTabs$2(this);

    public interface a {
        void a(int i);
    }

    public StickerTabs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        a();
    }

    private void a() {
        addView(LayoutInflater.from(this.b).inflate(e.sticker_tabs, null), new LayoutParams(-1, -2));
        this.d = (RadioGroup) findViewById(d.rdo_grooup);
        this.c = (LinearLayout) findViewById(d.rl_rdo_container);
    }

    private void a(int i) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(this.i / i, 3);
        this.f = (ImageView) findViewById(d.img_cursor);
        this.f.setLayoutParams(layoutParams);
        this.g = this.f.getWidth();
        this.h = ((this.i / i) - this.g) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate((float) this.h, 0.0f);
        this.f.setImageMatrix(matrix);
    }

    public void setTabsTitle(List<String> list) {
        if (list != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) this.b).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.i = displayMetrics.widthPixels;
            int size = list.size();
            this.c.setWeightSum((float) size);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.i / size, -1, 1.0f);
            for (int i = 0; i < size; i++) {
                RadioButton radioButton = (RadioButton) LayoutInflater.from(this.b).inflate(e.sticker_tabs_item, null);
                radioButton.setId(i);
                radioButton.setLayoutParams(layoutParams);
                radioButton.setText((CharSequence) list.get(i));
                radioButton.setTag(Integer.valueOf(i));
                radioButton.setOnClickListener(this.k);
                this.d.addView(radioButton, i);
            }
            ((RadioButton) this.d.getChildAt(0)).setChecked(true);
            a(list.size());
        }
    }

    public void setTabsSelectedByIndex(int i) {
        Message obtainMessage = this.a.obtainMessage();
        obtainMessage.what = i;
        this.a.sendMessage(obtainMessage);
    }

    private void b(int i) {
        int i2 = (this.h * 2) + this.g;
        Animation translateAnimation = new TranslateAnimation((float) (this.e * i2), (float) (i2 * i), 0.0f, 0.0f);
        this.e = i;
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(300);
        this.f.startAnimation(translateAnimation);
    }

    public void setTabsClickListener(a aVar) {
        this.j = aVar;
    }
}
