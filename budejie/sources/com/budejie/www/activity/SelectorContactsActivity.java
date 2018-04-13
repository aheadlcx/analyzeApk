package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.view.SideBar;
import com.budejie.www.activity.view.SideBar.a;
import com.budejie.www.adapter.a.p;
import com.budejie.www.bean.Fans;
import com.budejie.www.c.g;
import com.budejie.www.h.c;
import com.budejie.www.util.ag;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;
import com.budejie.www.util.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SelectorContactsActivity extends Activity implements OnClickListener {
    private p a;
    private l b;
    private List<Fans> c;
    private List<Fans> d;
    private ag e;
    private ListView f;
    private EditText g;
    private View h;
    private View i;
    private View j;
    private View k;
    private SideBar l;
    private TextView m;
    private TextView n;
    private g o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        setTheme(c.a().b());
        setContentView(R.layout.selector_contacts_layout);
        this.b = l.a();
        this.e = new ag();
        a();
        b();
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        findViewById(R.id.cancle_btn).setOnClickListener(this);
        findViewById(R.id.search_cancel).setOnClickListener(this);
        this.h = findViewById(R.id.search_layout);
        this.i = findViewById(R.id.title_layout);
        this.g = (EditText) findViewById(R.id.search_input_box);
        this.j = findViewById(R.id.search_clear_btn);
        this.j.setOnClickListener(this);
        this.k = findViewById(R.id.transparent_layout);
        this.k.setOnClickListener(this);
        this.f = (ListView) findViewById(R.id.contact_listview);
        this.l = (SideBar) findViewById(R.id.sidrbar);
        this.l.setTextView((TextView) findViewById(R.id.dialog));
        this.l.setOnTouchingLetterChangedListener(new a(this) {
            final /* synthetic */ SelectorContactsActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                int c = this.a.a.c(str.charAt(0));
                if (c != -1) {
                    this.a.f.setSelection(c + this.a.f.getHeaderViewsCount());
                }
            }
        });
        this.m = new TextView(this);
        this.m.setLayoutParams(new LayoutParams(-1, -2));
        this.m.setBackgroundResource(c.a().b(R.attr.choose_contact_item_title_bg));
        this.m.setTextColor(c.a().c(R.attr.choose_contact_item_title_text_color));
        int a = an.a((Context) this, 15);
        this.m.setPadding(a / 3, a, a / 3, a);
        this.m.setTextSize(15.0f);
        this.m.setOnClickListener(this);
        View linearLayout = new LinearLayout(this);
        linearLayout.addView(this.m);
        this.f.addHeaderView(linearLayout);
        this.m.setVisibility(8);
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SelectorContactsActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - this.a.f.getHeaderViewsCount();
                if (this.a.a.b(headerViewsCount)) {
                    this.a.h.setVisibility(0);
                    this.a.i.setVisibility(8);
                    this.a.k.setVisibility(0);
                    this.a.l.setVisibility(8);
                    this.a.g.requestFocus();
                    ao.a(this.a, this.a.g);
                    if (this.a.n == null) {
                        this.a.n = new TextView(this.a);
                        this.a.n.setLayoutParams(new LayoutParams(-1, -2));
                        this.a.n.setBackgroundResource(c.a().b(R.attr.choose_contact_item_title_bg));
                        this.a.n.setTextColor(c.a().c(R.attr.choose_contact_item_search_text_color));
                        this.a.n.setCompoundDrawablesWithIntrinsicBounds(0, 0, c.a().b(R.attr.choose_contact_item_arrow_icon), 0);
                        headerViewsCount = an.a(this.a, 15);
                        this.a.n.setPadding(headerViewsCount / 3, headerViewsCount, headerViewsCount / 3, headerViewsCount);
                        this.a.n.setTextSize(16.0f);
                        this.a.n.setOnClickListener(this.a);
                        this.a.n.setText(R.string.choose_contact_from_web);
                        return;
                    }
                    return;
                }
                Fans a = this.a.a.a(headerViewsCount);
                this.a.o.b(a.getId());
                this.a.b(a.getUsername());
            }
        });
        this.g.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SelectorContactsActivity a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String trim = charSequence.toString().trim();
                boolean isEmpty = TextUtils.isEmpty(trim);
                if (isEmpty) {
                    this.a.i.setVisibility(8);
                    this.a.j.setVisibility(8);
                    this.a.k.setVisibility(0);
                } else {
                    this.a.i.setVisibility(4);
                    this.a.j.setVisibility(0);
                    this.a.k.setVisibility(8);
                }
                this.a.a(trim);
                if (this.a.f.getFooterViewsCount() == 0 && !isEmpty) {
                    this.a.m.setVisibility(0);
                    this.a.f.addFooterView(this.a.n);
                } else if (isEmpty) {
                    this.a.m.setVisibility(8);
                    this.a.f.removeFooterView(this.a.n);
                }
                this.a.m.setText(isEmpty ? "" : "@" + trim);
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void b() {
        this.o = new g(this);
        new AsyncTask<Void, Void, ArrayList<Fans>>(this) {
            final /* synthetic */ SelectorContactsActivity a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((ArrayList) obj);
            }

            protected ArrayList<Fans> a(Void... voidArr) {
                Collection d = this.a.o.d();
                this.a.d = this.a.o.c();
                this.a.a(this.a.d);
                Collections.sort(this.a.d, this.a.e);
                ArrayList<Fans> arrayList = new ArrayList(d);
                Fans fans = new Fans();
                fans.setSortLetters(SideBar.a[0]);
                arrayList.add(0, fans);
                arrayList.addAll(this.a.d);
                return arrayList;
            }

            protected void a(ArrayList<Fans> arrayList) {
                this.a.c = arrayList;
                this.a.a = new p(this.a, this.a.c);
                this.a.f.setAdapter(this.a.a);
            }
        }.execute(new Void[0]);
    }

    private void a(List<Fans> list) {
        for (Fans fans : list) {
            String toUpperCase = this.b.b(fans.getUsername()).substring(0, 1).toUpperCase();
            if (toUpperCase.matches("[A-Z]")) {
                fans.setSortLetters(toUpperCase);
            } else {
                fans.setSortLetters("#");
            }
        }
    }

    private void a(String str) {
        List arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            this.a.a(this.c);
            return;
        }
        arrayList.clear();
        for (Fans fans : this.d) {
            String username = fans.getUsername();
            if (username.indexOf(str.toString()) != -1 || this.b.b(username).startsWith(str.toString())) {
                arrayList.add(fans);
            }
        }
        this.a.a(arrayList);
    }

    public void onClick(View view) {
        if (this.m == view) {
            b(this.g.getText().toString().trim());
        } else if (this.n == view) {
            startActivityForResult(new Intent(this, SelectorContactsFormWebActivity.class).putExtra(getString(R.string.REQUEST_CONTACT_NAME), this.g.getText().toString()), 0);
        } else {
            switch (view.getId()) {
                case R.id.cancle_btn:
                    finish();
                    return;
                case R.id.search_cancel:
                case R.id.transparent_layout:
                    this.g.setText("");
                    this.h.setVisibility(8);
                    this.i.setVisibility(0);
                    this.k.setVisibility(8);
                    this.l.setVisibility(0);
                    ao.a(this);
                    return;
                case R.id.search_clear_btn:
                    this.g.setText("");
                    return;
                default:
                    return;
            }
        }
    }

    private void b(String str) {
        setResult(-1, new Intent().putExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME), str));
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 0) {
            b(intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME)));
        }
    }
}
