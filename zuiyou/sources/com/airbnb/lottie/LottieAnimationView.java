package com.airbnb.lottie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.Settings.Global;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LottieAnimationView extends AppCompatImageView {
    private static final String a = LottieAnimationView.class.getSimpleName();
    private static final Map<String, ai> b = new HashMap();
    private static final Map<String, WeakReference<ai>> c = new HashMap();
    private final am d = new am(this) {
        final /* synthetic */ LottieAnimationView a;

        {
            this.a = r1;
        }

        public void a(ai aiVar) {
            this.a.setComposition(aiVar);
            this.a.h = null;
        }
    };
    private final aj e = new aj();
    private CacheStrategy f;
    private String g;
    @Nullable
    private o h;
    @Nullable
    private ai i;

    public enum CacheStrategy {
        None,
        Weak,
        Strong
    }

    private static class a extends BaseSavedState {
        public static final Creator<a> CREATOR = new Creator<a>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public a a(Parcel parcel) {
                return new a(parcel);
            }

            public a[] a(int i) {
                return new a[i];
            }
        };
        String a;
        float b;
        boolean c;
        boolean d;

        a(Parcelable parcelable) {
            super(parcelable);
        }

        private a(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            super(parcel);
            this.a = parcel.readString();
            this.b = parcel.readFloat();
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.c = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.d = z2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a);
            parcel.writeFloat(this.b);
            if (this.c) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!this.d) {
                i3 = 0;
            }
            parcel.writeInt(i3);
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        a(null);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(@Nullable AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.airbnb.lottie.as.a.LottieAnimationView);
        String string = obtainStyledAttributes.getString(com.airbnb.lottie.as.a.LottieAnimationView_lottie_fileName);
        if (!(isInEditMode() || string == null)) {
            setAnimation(string);
        }
        if (obtainStyledAttributes.getBoolean(com.airbnb.lottie.as.a.LottieAnimationView_lottie_autoPlay, false)) {
            this.e.h();
        }
        this.e.a(obtainStyledAttributes.getBoolean(com.airbnb.lottie.as.a.LottieAnimationView_lottie_loop, false));
        setImageAssetsFolder(obtainStyledAttributes.getString(com.airbnb.lottie.as.a.LottieAnimationView_lottie_imageAssetsFolder));
        this.f = CacheStrategy.values()[obtainStyledAttributes.getInt(com.airbnb.lottie.as.a.LottieAnimationView_lottie_cacheStrategy, CacheStrategy.None.ordinal())];
        obtainStyledAttributes.recycle();
        setLayerType(1, null);
        if (VERSION.SDK_INT >= 17 && Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f) {
            this.e.e();
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(this.e);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable aVar = new a(super.onSaveInstanceState());
        aVar.a = this.g;
        aVar.b = this.e.b();
        aVar.c = this.e.g();
        aVar.d = this.e.f();
        return aVar;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof a) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            this.g = aVar.a;
            if (!TextUtils.isEmpty(this.g)) {
                setAnimation(this.g);
            }
            setProgress(aVar.b);
            a(aVar.d);
            if (aVar.c) {
                b();
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDetachedFromWindow() {
        a();
        super.onDetachedFromWindow();
    }

    @UiThread
    @VisibleForTesting
    void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("This must be called from the main thread.");
        }
        this.e.d();
    }

    public void setAnimation(String str) {
        a(str, this.f);
    }

    public void a(final String str, final CacheStrategy cacheStrategy) {
        this.g = str;
        if (c.containsKey(str)) {
            WeakReference weakReference = (WeakReference) c.get(str);
            if (weakReference.get() != null) {
                setComposition((ai) weakReference.get());
                return;
            }
        } else if (b.containsKey(str)) {
            setComposition((ai) b.get(str));
            return;
        }
        this.g = str;
        this.e.j();
        e();
        this.h = com.airbnb.lottie.ai.a.a(getContext(), str, new am(this) {
            final /* synthetic */ LottieAnimationView c;

            public void a(ai aiVar) {
                if (cacheStrategy == CacheStrategy.Strong) {
                    LottieAnimationView.b.put(str, aiVar);
                } else if (cacheStrategy == CacheStrategy.Weak) {
                    LottieAnimationView.c.put(str, new WeakReference(aiVar));
                }
                this.c.setComposition(aiVar);
            }
        });
    }

    public void setAnimation(JSONObject jSONObject) {
        e();
        this.h = com.airbnb.lottie.ai.a.a(getResources(), jSONObject, this.d);
    }

    private void e() {
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
    }

    public void setComposition(@NonNull ai aiVar) {
        this.e.setCallback(this);
        if (this.e.a(aiVar)) {
            setImageDrawable(null);
            setImageDrawable(this.e);
            this.i = aiVar;
            requestLayout();
        }
    }

    public void setImageAssetsFolder(String str) {
        this.e.a(str);
    }

    public void a(boolean z) {
        this.e.a(z);
    }

    public void b() {
        this.e.h();
    }

    public void setSpeed(float f) {
        this.e.b(f);
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.e.a(f);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.e.b();
    }

    public long getDuration() {
        return this.i != null ? this.i.b() : 0;
    }
}
