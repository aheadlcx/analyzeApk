package c.a.a;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.view.View.OnClickListener;
import c.a.c;
import c.a.i.b;
import c.a.i.d;
import c.a.i.e;
import c.a.i.g;
import c.a.i.h;
import c.a.i.i;
import c.a.i.j;
import c.a.i.k;
import c.a.i.l;
import c.a.i.m;
import c.a.i.n;
import c.a.i.o;
import c.a.i.p;
import c.a.i.q;
import c.a.i.r;
import c.a.i.s;
import c.a.i.t;
import c.a.i.v;
import c.a.i.w;
import c.a.i.y;
import c.a.i.z;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class f {
    private static final Class<?>[] a = new Class[]{Context.class, AttributeSet.class};
    private static final int[] b = new int[]{16843375};
    private static final String[] c = new String[]{"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> d = new ArrayMap();
    private final Object[] e = new Object[2];

    private static class a implements OnClickListener {
        private final View a;
        private final String b;
        private Method c;
        private Context d;

        public a(@NonNull View view, @NonNull String str) {
            this.a = view;
            this.b = str;
        }

        public void onClick(@NonNull View view) {
            if (this.c == null) {
                a(this.a.getContext(), this.b);
            }
            try {
                this.c.invoke(this.d, new Object[]{view});
            } catch (Throwable e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (Throwable e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        @NonNull
        private void a(@Nullable Context context, @NonNull String str) {
            String str2;
            Context context2 = context;
            while (context2 != null) {
                try {
                    if (!context2.isRestricted()) {
                        Method method = context2.getClass().getMethod(this.b, new Class[]{View.class});
                        if (method != null) {
                            this.c = method;
                            this.d = context2;
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                }
                if (context2 instanceof ContextWrapper) {
                    context2 = ((ContextWrapper) context2).getBaseContext();
                } else {
                    context2 = null;
                }
            }
            int id = this.a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.a.getClass() + str2);
        }
    }

    public final View a(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        Context context2;
        View a;
        if (!z || view == null) {
            context2 = context;
        } else {
            context2 = view.getContext();
        }
        if (z2 || z3) {
            context2 = a(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = TintContextWrapper.wrap(context2);
        }
        View b = b(context2, str, attributeSet);
        if (b == null) {
            b = c(context2, str, attributeSet);
        }
        if (b == null) {
            b = d(context2, str, attributeSet);
        }
        if (b == null) {
            b = e(context2, str, attributeSet);
        }
        if (b == null) {
            b = f(context2, str, attributeSet);
        }
        if (b == null) {
            a = a(context2, str, attributeSet);
        } else {
            a = b;
        }
        if (a != null) {
            a(a, attributeSet);
        }
        return a;
    }

    private View b(Context context, String str, AttributeSet attributeSet) {
        View view = null;
        for (g a : c.e().g()) {
            view = a.a(context, str, attributeSet);
            if (view != null) {
                break;
            }
        }
        return view;
    }

    private View c(Context context, String str, AttributeSet attributeSet) {
        if (str.contains(".")) {
            return null;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    obj = 16;
                    break;
                }
                break;
            case -1495589242:
                if (str.equals("ProgressBar")) {
                    obj = 18;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    obj = 13;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    obj = 15;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    obj = 4;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    obj = 9;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    obj = 17;
                    break;
                }
                break;
            case -581606887:
                if (str.equals("TabWidget")) {
                    obj = 20;
                    break;
                }
                break;
            case -443652810:
                if (str.equals("RelativeLayout")) {
                    obj = 2;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    obj = 8;
                    break;
                }
                break;
            case 2666181:
                if (str.equals("View")) {
                    obj = null;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    obj = 11;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    obj = 5;
                    break;
                }
                break;
            case 1127291599:
                if (str.equals("LinearLayout")) {
                    obj = 1;
                    break;
                }
                break;
            case 1310765783:
                if (str.equals("FrameLayout")) {
                    obj = 3;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    obj = 14;
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    obj = 10;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    obj = 7;
                    break;
                }
                break;
            case 1969230692:
                if (str.equals("RadioGroup")) {
                    obj = 12;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    obj = 6;
                    break;
                }
                break;
            case 2059813682:
                if (str.equals("ScrollView")) {
                    obj = 19;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new z(context, attributeSet);
            case 1:
                return new j(context, attributeSet);
            case 2:
                return new q(context, attributeSet);
            case 3:
                return new g(context, attributeSet);
            case 4:
                return new w(context, attributeSet);
            case 5:
                return new i(context, attributeSet);
            case 6:
                return new b(context, attributeSet);
            case 7:
                return new c.a.i.f(context, attributeSet);
            case 8:
                return new t(context, attributeSet);
            case 9:
                return new h(context, attributeSet);
            case 10:
                return new d(context, attributeSet);
            case 11:
                return new m(context, attributeSet);
            case 12:
                return new n(context, attributeSet);
            case 13:
                return new e(context, attributeSet);
            case 14:
                return new c.a.i.a(context, attributeSet);
            case 15:
                return new k(context, attributeSet);
            case 16:
                return new o(context, attributeSet);
            case 17:
                return new s(context, attributeSet);
            case 18:
                return new l(context, attributeSet);
            case 19:
                return new r(context, attributeSet);
            case 20:
                return new v(context, attributeSet);
            default:
                return null;
        }
    }

    private View d(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    private View e(Context context, String str, AttributeSet attributeSet) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -1253434517:
                if (str.equals("android.support.v7.widget.AppCompatMultiAutoCompleteTextView")) {
                    obj = 8;
                    break;
                }
                break;
            case -1090498133:
                if (str.equals("android.support.v7.widget.AppCompatCheckBox")) {
                    obj = 5;
                    break;
                }
                break;
            case -1066126331:
                if (str.equals("android.support.v7.widget.AppCompatRadioButton")) {
                    obj = 9;
                    break;
                }
                break;
            case -1025327009:
                if (str.equals("android.support.v7.widget.AppCompatEditText")) {
                    obj = 3;
                    break;
                }
                break;
            case -848578758:
                if (str.equals("android.support.v7.widget.AppCompatButton")) {
                    obj = 4;
                    break;
                }
                break;
            case -721861224:
                if (str.equals("android.support.v7.widget.AppCompatImageView")) {
                    obj = 2;
                    break;
                }
                break;
            case -703660929:
                if (str.equals("android.support.v7.widget.RecyclerView")) {
                    obj = 13;
                    break;
                }
                break;
            case -254446176:
                if (str.equals("android.support.v7.widget.Toolbar")) {
                    obj = null;
                    break;
                }
                break;
            case 500769838:
                if (str.equals("android.support.v7.widget.AppCompatRatingBar")) {
                    obj = 11;
                    break;
                }
                break;
            case 664028026:
                if (str.equals("android.support.v7.widget.AppCompatTextView")) {
                    obj = 1;
                    break;
                }
                break;
            case 1055500194:
                if (str.equals("android.support.v7.widget.AppCompatAutoCompleteTextView")) {
                    obj = 7;
                    break;
                }
                break;
            case 1194292083:
                if (str.equals("android.support.v7.widget.AppCompatSeekBar")) {
                    obj = 10;
                    break;
                }
                break;
            case 1513038609:
                if (str.equals("android.support.v7.widget.AppCompatSpinner")) {
                    obj = 12;
                    break;
                }
                break;
            case 1515012453:
                if (str.equals("android.support.v7.widget.AppCompatImageButton")) {
                    obj = 6;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new y(context, attributeSet);
            case 1:
                return new w(context, attributeSet);
            case 2:
                return new i(context, attributeSet);
            case 3:
                return new c.a.i.f(context, attributeSet);
            case 4:
                return new b(context, attributeSet);
            case 5:
                return new d(context, attributeSet);
            case 6:
                return new h(context, attributeSet);
            case 7:
                return new c.a.i.a(context, attributeSet);
            case 8:
                return new k(context, attributeSet);
            case 9:
                return new m(context, attributeSet);
            case 10:
                return new s(context, attributeSet);
            case 11:
                return new o(context, attributeSet);
            case 12:
                return new t(context, attributeSet);
            case 13:
                return new p(context, attributeSet);
            default:
                return null;
        }
    }

    private View f(Context context, String str, AttributeSet attributeSet) {
        View view = null;
        for (g a : c.e().f()) {
            view = a.a(context, str, attributeSet);
            if (view != null) {
                break;
            }
        }
        return view;
    }

    public View a(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.e[0] = context;
            this.e[1] = attributeSet;
            View a;
            if (-1 == str.indexOf(46)) {
                for (String a2 : c) {
                    a = a(context, str, a2);
                    if (a != null) {
                        return a;
                    }
                }
                this.e[0] = null;
                this.e[1] = null;
                return null;
            }
            a = a(context, str, null);
            this.e[0] = null;
            this.e[1] = null;
            return a;
        } catch (Exception e) {
            return null;
        } finally {
            this.e[0] = null;
            this.e[1] = null;
        }
    }

    private void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private View a(Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        Constructor constructor = (Constructor) d.get(str);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(str2 != null ? str2 + str : str).asSubclass(View.class).getConstructor(a);
                d.put(str, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.e);
    }

    private static Context a(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.View, 0, 0);
        if (z) {
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.View_android_theme, 0);
        } else {
            resourceId = 0;
        }
        if (z2 && r0 == 0) {
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.View_theme, 0);
            if (resourceId != 0) {
                Log.i("SkinCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        int i = resourceId;
        obtainStyledAttributes.recycle();
        if (i == 0) {
            return context;
        }
        if ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == i) {
            return context;
        }
        return new ContextThemeWrapper(context, i);
    }
}
