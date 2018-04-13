package com.bdj.picture.edit.c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.b;
import com.bdj.picture.edit.bean.f;
import com.bdj.picture.edit.d.c;
import com.bdj.picture.edit.util.i;
import com.bdj.picture.edit.widget.CalculateDistanceScrollView;
import com.bdj.picture.edit.widget.HSuperImageView;
import com.bdj.picture.edit.widget.HSuperImageView$a;
import com.bdj.picture.edit.widget.d;

public class a extends Fragment implements HSuperImageView$a, com.bdj.picture.edit.widget.c.a {
    public static String a;
    private ImageView b;
    private FrameLayout c;
    private float d = 30.0f;
    private c e;
    private b f;
    private FrameLayout g;
    private View h;
    private com.bdj.picture.edit.e.b i;
    private d j;
    private f k;
    private CalculateDistanceScrollView l;
    private double m = 1.0d;
    private float n = 1.0f;
    private int o = 0;
    private Bitmap p;

    public void a(com.bdj.picture.edit.e.b bVar) {
        this.i = bVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a = getString(h.click_input_text);
        this.e = new c();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(e.edit_everyframe_fragment, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.l = (CalculateDistanceScrollView) view.findViewById(com.bdj.picture.edit.a.d.editScrollView);
        this.c = (FrameLayout) view.findViewById(com.bdj.picture.edit.a.d.editLayout);
        this.b = (ImageView) view.findViewById(com.bdj.picture.edit.a.d.editFrameIv);
        this.b.setOnClickListener(new a$1(this));
        a();
    }

    public void a() {
        b bVar = new b();
        bVar.a(true);
        bVar.a(0);
        bVar.a("");
        this.i = new a$2(this);
        this.e.a(bVar);
        this.g = new FrameLayout(getActivity());
        if (this.h != null) {
            this.c.removeView(this.h);
        }
        this.c.addView(this.g);
        this.h = this.g;
        this.f = bVar;
    }

    public void a(com.bdj.picture.edit.bean.a aVar) {
        if (this.e.a(this.f, BVType.IE_WATERMARK) >= 12) {
            Toast makeText = Toast.makeText(getActivity(), String.format(getString(h.edit_picture_max_size_message), new Object[]{Integer.valueOf(12), getString(h.edit_picture_function_bubbles)}), 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        } else if (aVar.b() != null) {
            com.bdj.picture.edit.bean.a aVar2 = aVar;
            f fVar = new f(System.currentTimeMillis(), aVar2, new com.bdj.picture.edit.bean.e(com.bdj.picture.edit.util.b.a(getActivity(), this.e.b(this.f), this.l.getScrollDistance(), this.o), 0.0f, this.n), this.f.a(), this.f.a(), this.b.getWidth(), this.b.getHeight(), a, this.m);
            Object hSuperImageView = new HSuperImageView(getActivity(), fVar);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, this.g.getHeight());
            layoutParams.topMargin = hSuperImageView.getViewT();
            layoutParams.leftMargin = hSuperImageView.getViewL();
            layoutParams.height = hSuperImageView.getViewH();
            layoutParams.width = hSuperImageView.getViewW();
            hSuperImageView.setLayoutParams(layoutParams);
            hSuperImageView.setSuperViewClickListener(this);
            hSuperImageView.setTag(Long.valueOf(fVar.a()));
            Log.d("savePic", "mergeBitmap iv.getWidth()=" + hSuperImageView.getWidth());
            Log.d("savePic", "mergeBitmap iv.getHeight()=" + hSuperImageView.getHeight());
            this.g.setLayoutParams(new FrameLayout.LayoutParams(-1, this.b.getHeight()));
            this.g.addView(hSuperImageView);
            hSuperImageView.a(i.a(getActivity(), hSuperImageView.getmBitmap(), a, aVar.b()), a);
            if (this.e != null) {
                this.e.a(this.f, hSuperImageView);
            }
            this.e.a(this.f, fVar);
        }
    }

    public void a(f fVar, String str) {
        if (fVar != null && fVar.b() != null && fVar.b().b() != null) {
            View findViewWithTag = this.c.findViewWithTag(Long.valueOf(fVar.a()));
            if (findViewWithTag != null) {
                HSuperImageView hSuperImageView = (HSuperImageView) findViewWithTag;
                hSuperImageView.a(i.a(getActivity(), hSuperImageView.getmBitmap(), str, fVar.b().b()), str);
                f b = this.e.b(this.f, Long.valueOf(hSuperImageView.getWidgets().a()));
                if (b != null) {
                    b.a(str);
                }
            }
        }
    }

    public void b(com.bdj.picture.edit.bean.a aVar) {
        if (this.e.a(this.f, BVType.IE_PASTER) >= 12) {
            Toast makeText = Toast.makeText(getActivity(), String.format(getString(h.edit_picture_max_size_message), new Object[]{Integer.valueOf(12), getString(h.edit_picture_function_stickers)}), 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        } else if (aVar.b() != null) {
            com.bdj.picture.edit.bean.a aVar2 = aVar;
            f fVar = new f(System.currentTimeMillis(), aVar2, new com.bdj.picture.edit.bean.e(com.bdj.picture.edit.util.b.a(getActivity(), this.e.b(this.f), this.l.getScrollDistance(), this.o), 0.0f, this.n), this.f.a(), this.f.a(), this.b.getWidth(), this.b.getHeight(), "", this.m);
            Object hSuperImageView = new HSuperImageView(getActivity(), fVar);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, this.g.getHeight());
            layoutParams.topMargin = hSuperImageView.getViewT();
            layoutParams.leftMargin = hSuperImageView.getViewL();
            layoutParams.height = hSuperImageView.getViewH();
            layoutParams.width = hSuperImageView.getViewW();
            hSuperImageView.setLayoutParams(layoutParams);
            hSuperImageView.setSuperViewClickListener(this);
            this.g.setLayoutParams(new FrameLayout.LayoutParams(-1, this.b.getHeight()));
            this.g.addView(hSuperImageView);
            if (this.e != null) {
                this.e.a(this.f, hSuperImageView);
            }
            this.e.a(this.f, fVar);
        }
    }

    public void a(View view) {
        if (this.g != null) {
            this.g.removeView(view);
        }
        if (view instanceof HSuperImageView) {
            HSuperImageView hSuperImageView = (HSuperImageView) view;
            this.e.a(this.f, Long.valueOf(hSuperImageView.getWidgets().a()));
            this.e.b(this.f, hSuperImageView);
        }
    }

    @SuppressLint({"NewApi"})
    public void a(HSuperImageView hSuperImageView) {
        hSuperImageView.b();
    }

    public void b(f fVar, String str) {
        if (fVar.b().a() == BVType.IE_WATERMARK && this.i != null) {
            this.i.a(fVar, str);
        }
    }

    public void a(boolean z) {
        if (this.e != null) {
            this.e.a(this.f, z);
        }
    }

    private boolean b(boolean z) {
        if (this.e != null) {
            return this.e.a(this.f, z);
        }
        return true;
    }

    public void b() {
        this.e.a(getActivity(), new a$3(this), this.p);
    }

    public void a(Bitmap bitmap) {
        if (isAdded() && bitmap != null) {
            this.m = ((double) bitmap.getWidth()) / ((double) getActivity().getWindowManager().getDefaultDisplay().getWidth());
            LayoutParams layoutParams = this.l.getLayoutParams();
            layoutParams.height = (int) (((double) bitmap.getHeight()) / this.m);
            this.l.setLayoutParams(layoutParams);
            if (((double) bitmap.getHeight()) / this.m < ((double) getActivity().getWindowManager().getDefaultDisplay().getWidth())) {
                this.o = (getActivity().getWindowManager().getDefaultDisplay().getWidth() - layoutParams.height) / 2;
                Log.d("mHeightOffset", "mHeightOffset=" + this.o);
            }
            layoutParams = this.b.getLayoutParams();
            layoutParams.height = (int) (((double) bitmap.getHeight()) / this.m);
            this.b.setLayoutParams(layoutParams);
            Matrix matrix = new Matrix();
            this.b.setScaleType(ScaleType.MATRIX);
            float width = ((float) getActivity().getWindowManager().getDefaultDisplay().getWidth()) / ((float) bitmap.getWidth());
            matrix.postScale(width, width);
            this.b.setImageMatrix(matrix);
            this.b.setImageBitmap(bitmap);
            if (!(this.p == null || this.p.isRecycled())) {
                this.p.recycle();
            }
            this.p = bitmap;
        }
    }

    public void c() {
        if (this.p != null && !this.p.isRecycled()) {
            this.p.recycle();
        }
    }
}
