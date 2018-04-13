package cn.xiaochuankeji.tieba.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.AppController;
import java.lang.reflect.Field;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public abstract class f extends Fragment {
    private String a;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (!c.a().b(this)) {
            c.a().a(this);
        }
        this.a = c.a.c.e().h();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onResume() {
        super.onResume();
        a("onResume", true);
    }

    public void onPause() {
        super.onPause();
        a("onPause", false);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
        try {
            Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
            declaredField = Fragment.class.getDeclaredField("mFragmentManager");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        if (c.a().b(this)) {
            c.a().c(this);
        }
        AppController.instance().watch(this);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        String str = "setUserVisibleHint";
        boolean z2 = !isHidden() && z;
        a(str, z2);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        String str = "onHiddenChanged";
        boolean z2 = !z && getUserVisibleHint();
        a(str, z2);
    }

    protected void a(String str, boolean z) {
        String h = c.a.c.e().h();
        if (z && !h.equalsIgnoreCase(this.a)) {
            this.a = h;
            a(c.a.c.e().c());
        }
    }

    public boolean a() {
        return !isHidden() && getUserVisibleHint() && isVisible();
    }

    @MainThread
    public void a(boolean z) {
    }

    @l(a = ThreadMode.POSTING)
    public void emptyEvent(k kVar) {
    }
}
