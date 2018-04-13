package cn.xiaochuankeji.tieba.ui.picker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.IndexLayout;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.e;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.aspectj.a.b.b;
import rx.b.g;
import rx.d;

public class RegionPicker extends a implements TextWatcher {
    private static final org.aspectj.lang.a.a b = null;
    private SearchFragment a;
    @BindView
    IndexLayout mIndexLayout;
    @BindView
    FrameLayout mProgressBar;
    @BindView
    AppCompatEditText mSearchView;
    @BindView
    TextView title;

    static {
        k();
    }

    private static void k() {
        b bVar = new b("RegionPicker.java", RegionPicker.class);
        b = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.picker.RegionPicker", "android.os.Bundle", "savedInstanceState", "", "void"), 46);
    }

    static final void a(RegionPicker regionPicker, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(b, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_pick_prefecture;
    }

    protected void c() {
        super.c();
        ButterKnife.a((Activity) this);
        this.title.setText("选择地区");
        this.mIndexLayout.setLayoutManager(new LinearLayoutManager(this));
        this.mIndexLayout.setCompareMode(2);
        final cn.xiaochuankeji.tieba.ui.widget.indexablerv.c regionAdapter = new RegionAdapter(this);
        this.mIndexLayout.setAdapter(regionAdapter);
        e kVar = new k(regionAdapter, null, null, e());
        this.mIndexLayout.a(kVar);
        kVar.a(new e.a<a>(this) {
            final /* synthetic */ RegionPicker a;

            {
                this.a = r1;
            }

            public void a(View view, int i, a aVar) {
            }
        });
        this.mIndexLayout.a();
        regionAdapter.a(new cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.b<a>(this) {
            final /* synthetic */ RegionPicker a;

            {
                this.a = r1;
            }

            public void a(View view, int i, int i2, a aVar) {
                if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                    String str = aVar.b;
                    if (!TextUtils.isEmpty(str)) {
                        int indexOf = str.indexOf(" ");
                        if (indexOf > 0) {
                            Intent intent = new Intent();
                            intent.putExtra("kRegionCode", str.substring(indexOf));
                            this.a.setResult(-1, intent);
                            this.a.finish();
                        }
                    }
                }
            }
        });
        this.a = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);
        getSupportFragmentManager().beginTransaction().hide(this.a).commit();
        d.a(Boolean.valueOf(true)).d(new g<Boolean, List<a>>(this) {
            final /* synthetic */ RegionPicker a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public List<a> a(Boolean bool) {
                return this.a.j();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<List<a>>(this) {
            final /* synthetic */ RegionPicker b;

            public /* synthetic */ void onNext(Object obj) {
                a((List) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(final List<a> list) {
                regionAdapter.a((List) list, new cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.a<a>(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a(List<cn.xiaochuankeji.tieba.ui.widget.indexablerv.b<a>> list) {
                        this.b.b.a.a(list);
                        this.b.b.mProgressBar.setVisibility(8);
                    }
                });
            }
        });
    }

    protected void i_() {
    }

    private List<a> e() {
        List arrayList = new ArrayList();
        a aVar = new a();
        aVar.b = "中国 +86";
        aVar.c = R.drawable.cn_86;
        arrayList.add(aVar);
        aVar = new a();
        aVar.b = "香港 +852";
        aVar.c = R.drawable.hk_852;
        arrayList.add(aVar);
        aVar = new a();
        aVar.b = "澳门 +853";
        aVar.c = R.drawable.mo_853;
        arrayList.add(aVar);
        aVar = new a();
        aVar.b = "台湾 +886";
        aVar.c = 0;
        arrayList.add(aVar);
        return arrayList;
    }

    protected void onResume() {
        super.onResume();
        this.mSearchView.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.mSearchView.removeTextChangedListener(this);
    }

    @WorkerThread
    private List<a> j() {
        List<a> arrayList = new ArrayList();
        Resources resources = getResources();
        for (String str : Arrays.asList(resources.getStringArray(R.array.region))) {
            a aVar = new a();
            int indexOf = str.indexOf(" ");
            aVar.b = str.substring(indexOf + 1);
            aVar.c = resources.getIdentifier(str.substring(0, indexOf), "drawable", getPackageName());
            arrayList.add(aVar);
        }
        return arrayList;
    }

    public void onBackPressed() {
        if (this.a.isHidden()) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().beginTransaction().hide(this.a).commit();
        }
    }

    @OnClick
    public void back() {
        setResult(0);
        finish();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (obj.trim().length() > 0) {
            if (this.a.isHidden()) {
                getSupportFragmentManager().beginTransaction().show(this.a).commit();
            }
        } else if (!this.a.isHidden()) {
            getSupportFragmentManager().beginTransaction().hide(this.a).commit();
        }
        this.a.a(obj);
    }
}
