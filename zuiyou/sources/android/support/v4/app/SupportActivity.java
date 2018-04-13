package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.State;
import android.arch.lifecycle.g;
import android.arch.lifecycle.h;
import android.arch.lifecycle.m;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.SimpleArrayMap;
import cn.xiaochuankeji.aop.permission.c;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportActivity extends Activity implements g {
    private static final a ajc$tjp_0 = null;
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();
    private h mLifecycleRegistry = new h(this);

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            SupportActivity.onCreate_aroundBody0((SupportActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class ExtraData {
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("SupportActivity.java", SupportActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "android.support.v4.app.SupportActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 66);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    static final void onCreate_aroundBody0(SupportActivity supportActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        m.a((Activity) supportActivity);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    @CallSuper
    protected void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.a(State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        return (ExtraData) this.mExtraDataMap.get(cls);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
