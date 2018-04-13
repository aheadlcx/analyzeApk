package cn.tatagou.sdk.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R$drawable;
import cn.tatagou.sdk.R$styleable;
import cn.tatagou.sdk.adapter.CatAdapter;
import cn.tatagou.sdk.pojo.AppCate;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.p;
import java.io.IOException;
import java.util.Locale;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import okhttp3.w;

public class TtgPagerSlidingTab extends HorizontalScrollView {
    private static final int[] a = new int[]{16842901, 16842904};
    private int A;
    private Typeface B;
    private int C;
    private int D;
    private Locale E;
    private c F;
    private int G;
    private boolean H;
    private LayoutParams b;
    private LayoutParams c;
    private final b d;
    private LinearLayout e;
    private ViewPager f;
    private int g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public interface a {
        int getPageIconResId(int i);
    }

    private class b implements OnPageChangeListener {
        final /* synthetic */ TtgPagerSlidingTab a;

        private b(TtgPagerSlidingTab ttgPagerSlidingTab) {
            this.a = ttgPagerSlidingTab;
        }

        public void onPageScrolled(int i, float f, int i2) {
            this.a.h = i;
            this.a.j = f;
            View childAt = this.a.e.getChildAt(i);
            if (childAt != null) {
                this.a.b(i, (int) (((float) childAt.getWidth()) * f));
                this.a.invalidate();
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.a.b(this.a.f.getCurrentItem(), 0);
            }
        }

        public void onPageSelected(int i) {
            this.a.i = i;
            if (this.a.H) {
                this.a.b();
            } else {
                this.a.a();
            }
            if (this.a.F != null) {
                this.a.F.setPageSelected(i);
            }
        }
    }

    public void setShowPic(boolean z) {
        this.H = z;
    }

    public void setmOneCateLeftPadding(int i) {
        this.G = i;
    }

    public TtgPagerSlidingTab(Context context) {
        this(context, null);
    }

    public TtgPagerSlidingTab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TtgPagerSlidingTab(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new b();
        this.h = 0;
        this.i = 0;
        this.j = 0.0f;
        this.m = -10066330;
        this.n = 436207616;
        this.o = 436207616;
        this.p = false;
        this.q = true;
        this.r = 52;
        this.s = 8;
        this.t = 2;
        this.u = 12;
        this.v = 0;
        this.w = 1;
        this.x = 12;
        this.y = -10066330;
        this.z = 20;
        this.A = -10066330;
        this.B = null;
        this.C = 0;
        this.D = R$drawable.background_tab;
        this.G = 20;
        setWillNotDraw(false);
        this.e = new LinearLayout(context);
        this.e.setOrientation(0);
        this.e.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.e);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.r = (int) TypedValue.applyDimension(1, (float) this.r, displayMetrics);
        this.s = (int) TypedValue.applyDimension(1, (float) this.s, displayMetrics);
        this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        this.u = (int) TypedValue.applyDimension(1, (float) this.u, displayMetrics);
        this.v = (int) TypedValue.applyDimension(1, (float) this.v, displayMetrics);
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(2, (float) this.x, displayMetrics);
        context.obtainStyledAttributes(attributeSet, a).recycle();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TtgPagerSlidingTab);
        this.m = obtainStyledAttributes.getColor(R$styleable.TtgPagerSlidingTab_ttgPstsIndicatorColor, this.m);
        this.x = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgZmsTabTextSize, this.x);
        this.y = obtainStyledAttributes.getColor(R$styleable.TtgPagerSlidingTab_ttgZmsTabTextColor, this.y);
        this.z = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgZmsSelectedTabTextSize, this.z);
        this.A = obtainStyledAttributes.getColor(R$styleable.TtgPagerSlidingTab_ttgZmsSelectedTabTextColor, this.m);
        this.n = obtainStyledAttributes.getColor(R$styleable.TtgPagerSlidingTab_ttgPstsUnderlineColor, this.n);
        this.o = obtainStyledAttributes.getColor(R$styleable.TtgPagerSlidingTab_ttgPstsDividerColor, this.o);
        this.s = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgPstsIndicatorHeight, this.s);
        this.t = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgPstsUnderlineHeight, this.t);
        this.u = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgPstsDividerPadding, this.u);
        this.v = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgPstsTabPaddingLeftRight, this.v);
        this.D = obtainStyledAttributes.getResourceId(R$styleable.TtgPagerSlidingTab_ttgPstsTabBackground, this.D);
        this.p = obtainStyledAttributes.getBoolean(R$styleable.TtgPagerSlidingTab_ttgPstsShouldExpand, this.p);
        this.r = obtainStyledAttributes.getDimensionPixelSize(R$styleable.TtgPagerSlidingTab_ttgPstsScrollOffset, this.r);
        this.q = obtainStyledAttributes.getBoolean(R$styleable.TtgPagerSlidingTab_ttgPstsTextAllCaps, this.q);
        obtainStyledAttributes.recycle();
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.FILL);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setStrokeWidth((float) this.w);
        this.b = new LayoutParams(-2, -1);
        this.c = new LayoutParams(0, -1, 1.0f);
        if (this.E == null) {
            this.E = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.f = viewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.d);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        this.e.removeAllViews();
        this.g = this.f.getAdapter().getCount();
        for (int i = 0; i < this.g; i++) {
            if (this.f.getAdapter() instanceof a) {
                a(i, ((a) this.f.getAdapter()).getPageIconResId(i));
            } else if (this.H) {
                b(i, this.f.getAdapter().getPageTitle(i).toString());
            } else {
                a(i, this.f.getAdapter().getPageTitle(i).toString());
            }
        }
        if (this.H) {
            b();
        } else {
            a();
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ TtgPagerSlidingTab a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                if (this.a.f != null) {
                    this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    this.a.h = this.a.f.getCurrentItem();
                    this.a.b(this.a.h, 0);
                }
            }
        });
    }

    private void a() {
        for (int i = 0; i < this.g; i++) {
            View childAt = this.e.getChildAt(i);
            if (childAt != null && (childAt instanceof TextView)) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.x);
                textView.setTypeface(this.B, 0);
                textView.setTextColor(this.y);
                if (this.q) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.E));
                    }
                }
                if (i == this.i) {
                    textView.setTextColor(this.A);
                    textView.setTextSize(0, (float) this.z);
                }
            }
        }
    }

    private void a(int i, String str) {
        View textView = new TextView(getContext());
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine();
        a(i, textView);
    }

    private void b(int i, String str) {
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        View imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LayoutParams(p.dip2px(getContext(), 20.0f), p.dip2px(getContext(), 20.0f)));
        String thumbnail = ((AppCate) ((CatAdapter) this.f.getAdapter()).getmAppCatsList().get(i)).getThumbnail();
        if (i == 0) {
            thumbnail = ((AppCate) ((CatAdapter) this.f.getAdapter()).getmAppCatsList().get(i)).getSelectThumbnail();
        }
        if (!TextUtils.isEmpty(thumbnail)) {
            Bitmap storePic = p.getStorePic(getContext(), thumbnail.substring(thumbnail.lastIndexOf("/") + 1, thumbnail.length()));
            if (storePic != null) {
                imageView.setImageBitmap(storePic);
            } else {
                loadPic(imageView, thumbnail);
            }
        }
        linearLayout.addView(imageView);
        View textView = new TextView(getContext());
        textView.setText(str);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        textView.setGravity(17);
        layoutParams.topMargin = p.dip2px(getContext(), 3.0f);
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setSingleLine();
        textView.setTextSize(12.0f);
        textView.setLayoutParams(layoutParams);
        linearLayout.addView(textView);
        a(i, linearLayout);
    }

    private void a(int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        a(i, imageButton);
    }

    private void a(final int i, View view) {
        if (this.f != null) {
            int count;
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -1);
            view.setFocusable(true);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TtgPagerSlidingTab b;

                public void onClick(View view) {
                    if (this.b.f != null) {
                        this.b.f.setCurrentItem(i);
                    }
                }
            });
            if (this.f != null) {
                count = this.f.getAdapter().getCount();
            } else {
                count = 0;
            }
            if (i == 0) {
                view.setPadding(p.dip2px(getContext(), (float) this.G), 0, p.dip2px(getContext(), 15.0f), 0);
            } else if (count == i + 1) {
                view.setPadding(p.dip2px(getContext(), 15.0f), 0, p.dip2px(getContext(), (float) this.G), 0);
            } else {
                view.setPadding(p.dip2px(getContext(), 15.0f), 0, p.dip2px(getContext(), 15.0f), 0);
            }
            this.e.addView(view, i, layoutParams);
        }
    }

    private void b() {
        for (int i = 0; i < this.g; i++) {
            View childAt = this.e.getChildAt(i);
            if (childAt != null && (childAt instanceof LinearLayout)) {
                ImageView imageView = (ImageView) ((LinearLayout) childAt).getChildAt(0);
                TextView textView = (TextView) ((LinearLayout) childAt).getChildAt(1);
                textView.setTextSize(12.0f);
                textView.setTypeface(this.B, 0);
                textView.setTextColor(Color.parseColor("#666666"));
                String thumbnail = ((AppCate) ((CatAdapter) this.f.getAdapter()).getmAppCatsList().get(i)).getThumbnail();
                if (!TextUtils.isEmpty(thumbnail)) {
                    Bitmap storePic = p.getStorePic(getContext(), thumbnail.substring(thumbnail.lastIndexOf("/") + 1, thumbnail.length()));
                    if (storePic == null) {
                        loadPic(imageView, thumbnail);
                    } else {
                        imageView.setImageBitmap(storePic);
                    }
                }
                if (this.q) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.E));
                    }
                }
                if (i == this.i) {
                    textView.setTextColor(this.A);
                    String selectThumbnail = ((AppCate) ((CatAdapter) this.f.getAdapter()).getmAppCatsList().get(this.i)).getSelectThumbnail();
                    if (!TextUtils.isEmpty(selectThumbnail)) {
                        Bitmap storePic2 = p.getStorePic(getContext(), selectThumbnail.substring(selectThumbnail.lastIndexOf("/") + 1, selectThumbnail.length()));
                        if (storePic2 == null) {
                            loadPic(imageView, selectThumbnail);
                        } else {
                            imageView.setImageBitmap(storePic2);
                        }
                    }
                }
            }
        }
    }

    public void loadPic(final ImageView imageView, String str) {
        new w().a(new okhttp3.y.a().a(str).b()).a(new f(this) {
            final /* synthetic */ TtgPagerSlidingTab b;

            public void onFailure(e eVar, IOException iOException) {
                Looper.prepare();
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        imageView.setImageBitmap(null);
                    }
                });
                Looper.loop();
            }

            public void onResponse(e eVar, aa aaVar) throws IOException {
                final Bitmap decodeStream = BitmapFactory.decodeStream(aaVar.h().d());
                String uri = eVar.a().a().a().toString();
                if (decodeStream != null) {
                    decodeStream.compress(CompressFormat.PNG, 100, this.b.getContext().openFileOutput(uri.substring(uri.lastIndexOf("/") + 1, uri.length()), 0));
                    Looper.prepare();
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void run() {
                            imageView.setImageBitmap(decodeStream);
                        }
                    });
                    Looper.loop();
                }
            }
        });
    }

    private void b(int i, int i2) {
        if (this.g != 0 && this.e != null) {
            View childAt = this.e.getChildAt(i);
            if (childAt != null) {
                int left = childAt.getLeft() + i2;
                if (i > 0 || i2 > 0) {
                    left -= this.r;
                }
                if (left != this.C) {
                    this.C = left;
                    scrollTo(left, 0);
                }
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.g != 0 && this.k != null && this.l != null) {
            int height = getHeight();
            this.k.setColor(this.n);
            canvas.drawRect(0.0f, (float) (height - this.t), (float) this.e.getWidth(), (float) height, this.k);
            this.k.setColor(this.m);
            View childAt = this.e.getChildAt(this.h);
            if (childAt != null) {
                float left;
                float right;
                float left2;
                float right2;
                int i;
                int count = this.f.getAdapter().getCount();
                p.dip2px(getContext(), 8.0f);
                if (this.h == 0) {
                    left = (float) (childAt.getLeft() + p.dip2px(getContext(), (float) this.G));
                    right = (float) (childAt.getRight() - p.dip2px(getContext(), 15.0f));
                } else if (this.h == count - 1) {
                    left = (float) (childAt.getLeft() + p.dip2px(getContext(), 15.0f));
                    right = (float) (childAt.getRight() - p.dip2px(getContext(), (float) this.G));
                } else {
                    left = (float) (childAt.getLeft() + p.dip2px(getContext(), 15.0f));
                    right = (float) (childAt.getRight() - p.dip2px(getContext(), 15.0f));
                }
                if (this.j > 0.0f && this.h < this.g - 1) {
                    View childAt2 = this.e.getChildAt(this.h + 1);
                    if (childAt2 != null) {
                        if (this.h == 0) {
                            left2 = (float) (childAt2.getLeft() + p.dip2px(getContext(), 15.0f));
                            right2 = (float) (childAt2.getRight() - p.dip2px(getContext(), 15.0f));
                        } else if (this.h == count - 2) {
                            left2 = (float) (childAt2.getLeft() + p.dip2px(getContext(), 15.0f));
                            right2 = (float) (childAt2.getRight() - p.dip2px(getContext(), (float) this.G));
                        } else {
                            left2 = (float) (childAt2.getLeft() + p.dip2px(getContext(), 15.0f));
                            right2 = (float) (childAt2.getRight() - p.dip2px(getContext(), 15.0f));
                        }
                        left = (left * (1.0f - this.j)) + (left2 * this.j);
                        left2 = (right * (1.0f - this.j)) + (right2 * this.j);
                        right2 = left;
                        canvas.drawRect(right2, (float) (height - this.s), left2, (float) height, this.k);
                        this.l.setColor(this.o);
                        for (i = 0; i < this.g - 1; i++) {
                            childAt = this.e.getChildAt(i);
                            if (childAt != null) {
                                canvas.drawLine((float) childAt.getRight(), (float) this.u, (float) childAt.getRight(), (float) (height - this.u), this.l);
                            }
                        }
                    }
                }
                left2 = right;
                right2 = left;
                canvas.drawRect(right2, (float) (height - this.s), left2, (float) height, this.k);
                this.l.setColor(this.o);
                for (i = 0; i < this.g - 1; i++) {
                    childAt = this.e.getChildAt(i);
                    if (childAt != null) {
                        canvas.drawLine((float) childAt.getRight(), (float) this.u, (float) childAt.getRight(), (float) (height - this.u), this.l);
                    }
                }
            }
        }
    }

    public void setOnPageSelectedLister(c cVar) {
        this.F = cVar;
    }

    public void setTextSize(int i) {
        this.x = i;
        b();
    }

    public int getTextSize() {
        return this.x;
    }

    public void setTextColor(int i) {
        this.y = i;
        b();
    }

    public int getTextColor() {
        return this.y;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.h = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.h;
        return savedState;
    }

    public void setPstsIndicatorColor(int i) {
        this.m = i;
    }

    public void setSelectedTabTextColor(int i) {
        this.A = i;
    }
}
