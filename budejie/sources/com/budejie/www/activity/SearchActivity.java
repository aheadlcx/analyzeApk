package com.budejie.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.adapter.a.o;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchResult;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActvityWithLoadDailog implements OnClickListener, com.budejie.www.widget.XListView.a {
    public static List<SearchItem> a = new ArrayList();
    private RelativeLayout b;
    private TextView c;
    private TextView d;
    private TextView e;
    private EditText f;
    private ImageView h;
    private XListView i;
    private o j;
    private String k;
    private int l = 1;
    private Toast m;
    private Activity n;
    private j o;
    private TextWatcher p = new TextWatcher(this) {
        final /* synthetic */ SearchActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(this.a.f.getText().toString())) {
                this.a.h.setVisibility(8);
            } else {
                this.a.h.setVisibility(0);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    private class a extends net.tsz.afinal.a.a<String> {
        final /* synthetic */ SearchActivity a;
        private int b;

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        private a(SearchActivity searchActivity, int i) {
            this.a = searchActivity;
            this.b = 1;
            this.b = i;
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.e();
            this.a.m = an.a(this.a.n, this.a.getString(R.string.search_error), -1);
            this.a.m.show();
        }

        public void a(String str) {
            SearchResult b;
            super.onSuccess(str);
            this.a.e();
            Log.e("SearchActivity", str);
            SearchResult searchResult = null;
            try {
                b = b(str);
            } catch (Exception e) {
                e.printStackTrace();
                b = searchResult;
            }
            if (b != null) {
                String msg;
                if (!b.getCode().equals(Constants.DEFAULT_UIN) || b.getList() == null) {
                    msg = b.getMsg();
                    if (TextUtils.isEmpty(msg)) {
                        msg = "未找到您搜索的数据";
                    }
                    this.a.m = an.a(this.a.n, msg, -1);
                    this.a.m.show();
                } else {
                    List list = b.getList();
                    if (this.b == 1) {
                        SearchActivity.a.clear();
                        this.a.j.b(list);
                    } else {
                        this.a.j.a(list);
                    }
                    this.a.l = this.b;
                    SearchActivity.a.addAll(list);
                }
                if ("1".equals(b.getMore())) {
                    this.a.i.setPullLoadEnable(true);
                } else {
                    this.a.i.setPullLoadEnable(false);
                }
                msg = Long.toString(b.getTotal());
                this.a.e.setVisibility(0);
                this.a.e.setText(this.a.getString(R.string.total_search_count, new Object[]{msg}));
                return;
            }
            if (this.b == 1) {
                this.a.e.setVisibility(8);
            }
            this.a.m = an.a(this.a.n, this.a.getString(R.string.search_error), -1);
            this.a.m.show();
        }

        private SearchResult b(String str) throws JsonSyntaxException {
            return (SearchResult) new Gson().fromJson(str, SearchResult.class);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.search_layout);
        d(R.id.navigation_bar);
        this.n = this;
        this.o = new j();
        g();
        this.i = (XListView) findViewById(R.id.listview);
        this.e = (TextView) findViewById(R.id.total_count_tv);
        this.i.setPullRefreshEnable(false);
        this.i.setPullLoadEnable(false);
        this.i.setXListViewListener(this);
        this.j = new o(this);
        this.i.setAdapter(this.j);
    }

    private void g() {
        this.b = (RelativeLayout) View.inflate(this, R.layout.navigation_bar_search_title, null);
        c().setMiddleView(this.b);
        this.c = (TextView) this.b.findViewById(R.id.back_btn);
        this.d = (TextView) this.b.findViewById(R.id.search_btn);
        this.f = (EditText) this.b.findViewById(R.id.search_keywords_text);
        this.h = (ImageView) this.b.findViewById(R.id.del_keywords_btn);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.f.addTextChangedListener(this.p);
    }

    public void onClick(View view) {
        if (view == this.c) {
            finish();
        } else if (view == this.d) {
            String obj = this.f.getText().toString();
            if (a(obj)) {
                this.m = an.a(this.n, getString(R.string.pls_input_keywords), -1);
                this.m.show();
                return;
            }
            f();
            this.k = obj;
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.o.e(this.n, obj, "1"), new a(1));
        } else if (view == this.h) {
            this.f.requestFocus();
            this.f.getText().clear();
        }
    }

    public void a() {
    }

    public void b() {
        int i = this.l + 1;
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.o.e(this.n, this.k, Integer.toString(i)), new a(i));
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || "".equals(str.trim());
    }

    public static void a(boolean z, String str) {
        if (a != null && a.size() > 0) {
            int size = a.size();
            int i = 0;
            while (i < size) {
                if (!((SearchItem) a.get(i)).getId().equalsIgnoreCase(str)) {
                    i++;
                } else if (z) {
                    ((SearchItem) a.get(i)).setRelationship("1");
                    try {
                        size = Integer.valueOf(((SearchItem) a.get(i)).getFansCount()).intValue() + 1;
                        if (size >= 0) {
                            ((SearchItem) a.get(i)).setFansCount(String.valueOf(size));
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        return;
                    }
                } else {
                    ((SearchItem) a.get(i)).setRelationship("0");
                    try {
                        size = Integer.valueOf(((SearchItem) a.get(i)).getFansCount()).intValue() - 1;
                        if (size >= 0) {
                            ((SearchItem) a.get(i)).setFansCount(String.valueOf(size));
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        return;
                    }
                }
            }
        }
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onDestroy() {
        super.onDestroy();
        a.clear();
    }
}
