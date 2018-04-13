package com.budejie.www.activity.label;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.label.a.c;
import com.budejie.www.activity.label.enumeration.LabelUserEnum;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.label.response.ApplyModeratorResponse;
import com.budejie.www.activity.label.response.ApplyModeratorResponse.ApplyBean;
import com.budejie.www.activity.label.view.DeputyListView;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.i.b.b;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchResult;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.f;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tencent.connect.common.Constants;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeputyModeratorSetActivity extends BaseActvityWithLoadDailog implements com.budejie.www.activity.label.a.c.a, b {
    private List<SearchItem> a;
    private boolean b;
    private c c;
    private EditText d;
    private ImageView e;
    private XListView f;
    private DeputyListView h;
    private com.budejie.www.i.a.b i;
    private String j;
    private j k;
    private int l = 1;
    private DeputyModeratorSetActivity m;
    private f n;
    private boolean o;
    private SearchItem p;
    private TextView q;
    private com.budejie.www.widget.XListView.a r = new com.budejie.www.widget.XListView.a(this) {
        final /* synthetic */ DeputyModeratorSetActivity a;

        {
            this.a = r1;
        }

        public void a() {
        }

        public void b() {
            int a = this.a.l + 1;
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.k.e(this.a, this.a.d.getText().toString(), Integer.toString(a)), new a(a));
        }
    };
    private TextWatcher s = new TextWatcher(this) {
        final /* synthetic */ DeputyModeratorSetActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence.toString())) {
                this.a.d.setHint(this.a.getResources().getString(R.string.search_section));
                this.a.e.setVisibility(8);
                return;
            }
            this.a.e.setVisibility(0);
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    public enum FinishEnum {
        FINISH
    }

    private class a extends net.tsz.afinal.a.a<String> {
        final /* synthetic */ DeputyModeratorSetActivity a;
        private int b;

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        private a(DeputyModeratorSetActivity deputyModeratorSetActivity, int i) {
            this.a = deputyModeratorSetActivity;
            this.b = 1;
            this.b = i;
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.g();
        }

        public void a(String str) {
            SearchResult b;
            this.a.g();
            this.a.f.setVisibility(0);
            SearchResult searchResult = null;
            try {
                b = b(str);
            } catch (Exception e) {
                e.printStackTrace();
                b = searchResult;
            }
            if (b != null) {
                if (!b.getCode().equals(Constants.DEFAULT_UIN) || b.getList() == null) {
                    String msg = b.getMsg();
                    if (TextUtils.isEmpty(msg)) {
                        msg = "未找到您搜索的数据";
                    }
                    this.a.f.setVisibility(8);
                    au.a(msg);
                } else {
                    Collection list = b.getList();
                    this.a.l = this.b;
                    if (this.a.a == null) {
                        this.a.a = new ArrayList();
                    }
                    if (this.b == 1) {
                        this.a.a.clear();
                    }
                    this.a.a.addAll(list);
                    this.a.n();
                }
                if ("1".equals(b.getMore())) {
                    this.a.f.setPullLoadEnable(true);
                    return;
                } else {
                    this.a.f.setPullLoadEnable(false);
                    return;
                }
            }
            this.a.f.setVisibility(8);
            au.a(this.a.getString(R.string.search_error));
        }

        private SearchResult b(String str) throws JsonSyntaxException {
            return (SearchResult) new Gson().fromJson(str, SearchResult.class);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_deputy_moderator);
        this.i = new com.budejie.www.i.a.b();
        this.i.a((b) this);
        this.m = this;
        EventBus.getDefault().register(this);
        d(R.id.navigation_bar);
        i();
        j();
        b();
    }

    private void b() {
        this.k = new j();
        n();
    }

    private synchronized void a(String str) {
        a_();
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.k.e((Context) this, str, "1"), new a(1));
    }

    public void onEventMainThread(FinishEnum finishEnum) {
        com.budejie.www.busevent.b bVar = new com.budejie.www.busevent.b();
        bVar.a = !this.o;
        EventBus.getDefault().post(bVar);
        if (!this.o) {
            finish();
        }
        this.o = false;
    }

    private void i() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.b = extras.getBoolean("is_set_moderator_tag", false);
                this.j = extras.getString("theme_id_tag");
                if (this.b) {
                    List list = (List) extras.getSerializable("user_data_tag");
                    if (!com.budejie.www.goddubbing.c.a.a(list)) {
                        if (this.a == null) {
                            this.a = new ArrayList();
                        }
                        this.a.addAll(list);
                    }
                }
            }
        }
    }

    private void j() {
        int i;
        int i2 = 0;
        setTitle(this.b ? "设置副版主" : "添加副版主");
        a(null);
        this.q = (TextView) findViewById(R.id.moderator_text_view);
        k();
        TextView textView = this.q;
        if (this.b) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        View findViewById = findViewById(R.id.search_layout);
        if (this.b) {
            i = 8;
        } else {
            i = 0;
        }
        findViewById.setVisibility(i);
        this.d = (EditText) findViewById(R.id.search_editText);
        this.e = (ImageView) findViewById(R.id.clear_imageView);
        this.f = (XListView) findViewById(R.id.moderator_select_list_view);
        this.h = (DeputyListView) findViewById(R.id.moderator_list_view);
        this.f.setPullRefreshEnable(false);
        this.f.setPullLoadEnable(false);
        if (!this.b) {
            this.f.setXListViewListener(this.r);
        }
        XListView xListView = this.f;
        if (this.b) {
            i = 8;
        } else {
            i = 0;
        }
        xListView.setVisibility(i);
        DeputyListView deputyListView = this.h;
        if (!this.b) {
            i2 = 8;
        }
        deputyListView.setVisibility(i2);
        m();
        l();
        if (!this.b) {
            an.a((Activity) this, this.d);
        }
    }

    private void k() {
        TextView textView = this.q;
        String string = getResources().getString(R.string.deputy_moderator_set_text);
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(com.budejie.www.goddubbing.c.a.a(this.a) ? 0 : this.a.size() - 1);
        textView.setText(String.format(string, objArr));
    }

    private void l() {
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DeputyModeratorSetActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d.setText("");
                this.a.d.setHint(this.a.getResources().getString(R.string.search));
            }
        });
    }

    private void m() {
        this.d.addTextChangedListener(this.s);
        this.d.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ DeputyModeratorSetActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    an.b(this.a);
                    String obj = this.a.d.getText().toString();
                    if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj.trim())) {
                        au.a(this.a.getResources().getString(R.string.please_input_search_content));
                    } else {
                        this.a.a(obj.trim());
                    }
                }
                return false;
            }
        });
    }

    private void n() {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        if (this.c == null) {
            this.c = new c(this, this.a, this.b);
            this.c.a((com.budejie.www.activity.label.a.c.a) this);
            this.f.setAdapter(this.c);
            this.h.setAdapter(this.c);
            return;
        }
        this.c.notifyDataSetChanged();
    }

    public void a(SearchItem searchItem) {
        if (this.i != null && searchItem != null) {
            this.i.a(this.j, PlatePostEnum.VICE_MASTER_APPROVE.getValue(), searchItem.getId());
        }
    }

    public void a() {
        this.p = null;
        this.o = false;
        com.budejie.www.util.a.a((Context) this, this.j, false, null);
    }

    public void b(SearchItem searchItem) {
        if (this.i != null && searchItem != null) {
            this.p = searchItem;
            this.o = true;
            this.i.a(this.j, PlatePostEnum.VICE_MASTER_DENY.getValue(), searchItem.getId());
        }
    }

    public void a(Throwable th) {
    }

    public void a(ApplyModeratorResponse applyModeratorResponse) {
        ApplyBean info = applyModeratorResponse.getInfo();
        if (info != null) {
            if (this.n == null) {
                this.n = new f(this.m, R.style.dialogTheme);
            }
            boolean z = info.getCode() == LabelUserEnum.APPLY_DEPUTY_MODERATOR_SUCCESS.getValue();
            this.n.a(z, info.getResult(), 2000);
            if (z) {
                p();
                o();
            }
            this.n.show();
        }
    }

    private void o() {
        if (this.p != null && !com.budejie.www.goddubbing.c.a.a(this.a)) {
            this.a.remove(this.p);
            this.c.notifyDataSetChanged();
            this.p = null;
            k();
        }
    }

    private void p() {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ DeputyModeratorSetActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                Thread.sleep(2000);
                return Boolean.valueOf(true);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            Disposable a;
            final /* synthetic */ DeputyModeratorSetActivity b;

            {
                this.b = r1;
            }

            public /* synthetic */ void onNext(@NonNull Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.a = disposable;
            }

            public void a(@NonNull Boolean bool) {
                if (!this.b.o) {
                    this.b.finish();
                }
                EventBus.getDefault().post(FinishEnum.FINISH);
            }

            public void onError(@NonNull Throwable th) {
                this.a.dispose();
            }

            public void onComplete() {
                this.a.dispose();
            }
        });
    }

    public void a_() {
        f();
    }

    public void g() {
        e();
    }

    public Context h() {
        return this;
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (this.i != null) {
            this.i.a();
        }
    }
}
