package com.zhihu.matisse.internal.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import cn.xiaochuankeji.aop.permission.c;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class SelectedPreviewActivity extends BasePreviewActivity {
    private static final a ajc$tjp_0 = null;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            SelectedPreviewActivity.onCreate_aroundBody0((SelectedPreviewActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("SelectedPreviewActivity.java", SelectedPreviewActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.zhihu.matisse.internal.ui.SelectedPreviewActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 30);
    }

    static final void onCreate_aroundBody0(SelectedPreviewActivity selectedPreviewActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        selectedPreviewActivity.mAdapter.addAll(selectedPreviewActivity.getIntent().getBundleExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE).getParcelableArrayList(SelectedItemCollection.STATE_SELECTION));
        selectedPreviewActivity.mAdapter.notifyDataSetChanged();
        selectedPreviewActivity.mCheckView.setSelected(true);
        selectedPreviewActivity.mPreviousPos = 0;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }
}
