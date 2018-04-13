package c.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import c.a.a.g;
import cn.xiaochuan.base.BaseApplication;
import java.util.ArrayList;
import java.util.List;

public class c extends c.a.g.a {
    private static volatile c a;
    private static SparseArray<c> g = new SparseArray();
    private final Object b = new Object();
    private final Context c;
    private boolean d = false;
    private List<g> e = new ArrayList();
    private List<g> f = new ArrayList();
    private boolean h = true;
    private boolean i = true;

    private class a extends AsyncTask<String, Void, String> {
        final /* synthetic */ c a;
        private final b b;
        private final c c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        a(c cVar, @Nullable b bVar, @NonNull c cVar2) {
            this.a = cVar;
            this.b = bVar;
            this.c = cVar2;
        }

        protected void onPreExecute() {
            if (this.b != null) {
                this.b.a();
            }
        }

        protected String a(String... strArr) {
            synchronized (this.a.b) {
                while (this.a.d) {
                    try {
                        this.a.b.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.a.d = true;
            }
            try {
                if (strArr.length == 1) {
                    if (TextUtils.isEmpty(strArr[0])) {
                        c.a.d.a.a.a().b();
                        return strArr[0];
                    } else if (!TextUtils.isEmpty(this.c.a(this.a.c, strArr[0]))) {
                        return strArr[0];
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.a.d.a.a.a().b();
            return null;
        }

        protected void a(String str) {
            synchronized (this.a.b) {
                if (str != null) {
                    c.a.h.b.a().a(str).a(this.c.a()).d();
                    this.a.m();
                    if (this.b != null) {
                        this.b.b();
                    }
                } else {
                    c.a.h.b.a().a("").a(-1).d();
                    if (this.b != null) {
                        this.b.a("皮肤资源获取失败");
                    }
                }
                this.a.d = false;
                this.a.b.notifyAll();
            }
        }
    }

    public interface b {
        void a();

        void a(String str);

        void b();
    }

    public interface c {
        int a();

        String a(Context context, String str);

        String a(Context context, String str, int i);
    }

    public static void a() {
        e().i();
    }

    public static void b() {
        e().a("night", null, 1);
    }

    public boolean c() {
        return "night".equalsIgnoreCase(e().h());
    }

    public boolean d() {
        return !c();
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        c.a.h.b.a(context);
        c.a.d.a.a.a(context);
        return a;
    }

    public static c e() {
        if (a == null) {
            a(BaseApplication.getAppContext());
        }
        return a;
    }

    public static c a(Application application) {
        a((Context) application);
        c.a.a.a.a(application);
        return a;
    }

    private c(Context context) {
        this.c = context.getApplicationContext();
        n();
    }

    private void n() {
        g.put(0, new c.a.f.a());
        g.put(1, new c.a.f.b());
        g.put(2, new c.a.f.c());
    }

    public c a(g gVar) {
        this.e.add(gVar);
        return this;
    }

    public List<g> f() {
        return this.e;
    }

    public List<g> g() {
        return this.f;
    }

    public String h() {
        return c.a.h.b.a().b();
    }

    public void i() {
        a("");
    }

    public c a(boolean z) {
        this.h = z;
        return this;
    }

    public boolean j() {
        return this.h;
    }

    public c b(boolean z) {
        this.i = z;
        return this;
    }

    public boolean k() {
        return this.i;
    }

    public AsyncTask l() {
        Object b = c.a.h.b.a().b();
        int c = c.a.h.b.a().c();
        if (TextUtils.isEmpty(b) || c == -1) {
            return null;
        }
        return a(b, null, c);
    }

    @Deprecated
    public AsyncTask a(String str) {
        return a(str, null);
    }

    @Deprecated
    public AsyncTask a(String str, b bVar) {
        return a(str, bVar, 0);
    }

    public AsyncTask a(String str, b bVar, int i) {
        return new a(this, bVar, (c) g.get(i)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
    }

    public String b(String str) {
        return this.c.getPackageManager().getPackageArchiveInfo(str, 1).packageName;
    }

    @Nullable
    public Resources c(String str) {
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            assetManager.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            Resources resources = this.c.getResources();
            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(Window window) {
        final View decorView = window.getDecorView();
        Bitmap a = a(decorView);
        if ((decorView instanceof ViewGroup) && a != null) {
            final View view = new View(this.c);
            view.setBackgroundDrawable(new BitmapDrawable(this.c.getResources(), a));
            ((ViewGroup) decorView).addView(view, new LayoutParams(-1, -1));
            view.animate().alpha(0.0f).setDuration(350).setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ c c;

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    view.setAlpha(0.0f);
                    view.clearAnimation();
                    ((ViewGroup) decorView).removeView(view);
                }
            }).start();
        }
    }

    private Bitmap a(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        drawingCache = Bitmap.createBitmap(drawingCache);
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return drawingCache;
    }
}
