package com.flurry.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

final class y extends RelativeLayout {
    private u a;
    private p b;
    private String c;
    private int d;

    public y(Context context, u uVar, p pVar, e eVar, int i, boolean z) {
        super(context);
        this.a = uVar;
        this.b = pVar;
        v vVar = pVar.b;
        this.d = i;
        switch (this.d) {
            case 1:
                break;
            case 2:
                if (!z) {
                    a(context, eVar, vVar, true);
                    break;
                } else {
                    a(context, eVar, vVar, false);
                    break;
                }
        }
        if (z) {
            a(context, eVar, vVar, false);
        } else {
            a(context, eVar, vVar, true);
        }
        setFocusable(true);
    }

    private void a(Context context, e eVar, v vVar, boolean z) {
        Bitmap createBitmap;
        setLayoutParams(new LayoutParams(-1, -1));
        c cVar = eVar.d;
        View imageView = new ImageView(context);
        imageView.setId(1);
        AdImage adImage = vVar.h;
        if (adImage != null) {
            Object obj = adImage.e;
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(obj, 0, obj.length);
            if (decodeByteArray == null) {
                ah.a("FlurryAgent", "Ad with bad image: " + vVar.d + ", data: " + obj);
            }
            if (decodeByteArray != null) {
                Bitmap copy;
                createBitmap = Bitmap.createBitmap(decodeByteArray.getWidth(), decodeByteArray.getHeight(), Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                Paint paint = new Paint();
                Rect rect = new Rect(0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight());
                RectF rectF = new RectF(rect);
                float a = (float) r.a(context, 8);
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(-16777216);
                canvas.drawRoundRect(rectF, a, a, paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(decodeByteArray, rect, rect, paint);
                if (Integer.parseInt(VERSION.SDK) > 4) {
                    MaskFilter blurMaskFilter = new BlurMaskFilter(3.0f, Blur.OUTER);
                    Paint paint2 = new Paint();
                    paint2.setMaskFilter(blurMaskFilter);
                    int[] iArr = new int[2];
                    copy = createBitmap.extractAlpha(paint2, iArr).copy(Config.ARGB_8888, true);
                    new Canvas(copy).drawBitmap(createBitmap, (float) (-iArr[0]), (float) (-iArr[1]), null);
                } else {
                    copy = createBitmap;
                }
                imageView.setImageBitmap(copy);
                r.a(context, imageView, r.a(context, cVar.m), r.a(context, cVar.n));
                imageView.setScaleType(ScaleType.FIT_XY);
            }
        }
        adImage = this.a.a(cVar.c);
        if (adImage != null) {
            Drawable ninePatchDrawable;
            byte[] bArr = adImage.e;
            createBitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (NinePatch.isNinePatchChunk(createBitmap.getNinePatchChunk())) {
                ninePatchDrawable = new NinePatchDrawable(createBitmap, createBitmap.getNinePatchChunk(), new Rect(0, 0, 0, 0), null);
            } else {
                ninePatchDrawable = new BitmapDrawable(createBitmap);
            }
            setBackgroundDrawable(ninePatchDrawable);
        }
        View textView = new TextView(context);
        textView.setId(5);
        textView.setPadding(0, 0, 0, 0);
        View textView2 = new TextView(context);
        textView2.setId(3);
        textView2.setPadding(0, 0, 0, 0);
        if (z) {
            textView.setTextColor(cVar.f);
            textView.setTextSize((float) cVar.e);
            textView.setText(new String("• " + cVar.b));
            textView.setTypeface(Typeface.create(cVar.d, 0));
            textView2.setTextColor(cVar.i);
            textView2.setTextSize((float) cVar.h);
            textView2.setTypeface(Typeface.create(cVar.g, 0));
            textView2.setText(vVar.d);
        } else {
            textView.setId(3);
            textView.setText(vVar.d);
            textView.setTextColor(cVar.i);
            textView.setTextSize((float) cVar.h);
            textView.setTypeface(Typeface.create(cVar.g, 0));
            textView2.setId(4);
            textView2.setText(vVar.c);
            textView2.setTextColor(cVar.l);
            textView2.setTextSize((float) cVar.k);
            textView2.setTypeface(Typeface.create(cVar.j, 0));
        }
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(new ImageView(context), new LayoutParams(-1, -2));
        int i = (cVar.q - (cVar.o << 1)) - cVar.m;
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.setMargins(cVar.o, cVar.p, i, 0);
        addView(imageView, layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(6, imageView.getId());
        layoutParams2.addRule(1, imageView.getId());
        layoutParams2.setMargins(0, 0, 0, 0);
        addView(textView, layoutParams2);
        layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(1, imageView.getId());
        layoutParams2.addRule(3, textView.getId());
        layoutParams2.setMargins(0, -2, 0, 0);
        addView(textView2, layoutParams2);
    }

    final void a(String str) {
        this.c = str;
    }

    final String b(String str) {
        return str + this.c + System.currentTimeMillis();
    }

    final p a() {
        return this.b;
    }
}
