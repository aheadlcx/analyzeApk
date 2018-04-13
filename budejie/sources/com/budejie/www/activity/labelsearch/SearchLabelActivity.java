package com.budejie.www.activity.labelsearch;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.plate.bean.PlateResponseData;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.ah;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

public class SearchLabelActivity extends BaseActvityWithLoadDailog {
    a<String> a = new a<String>(this) {
        final /* synthetic */ SearchLabelActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            this.a.e();
            if (TextUtils.isEmpty(str)) {
                this.a.b.setVisibility(8);
                this.a.a("数据为空");
                return;
            }
            PlateResponseData plateResponseData = (PlateResponseData) z.a(str, PlateResponseData.class);
            if (plateResponseData != null) {
                List list = plateResponseData.list;
                if (com.budejie.www.goddubbing.c.a.a(list)) {
                    this.a.b.setVisibility(8);
                    this.a.a("抱歉，没有该标签！");
                    return;
                }
                ah.a(this.a.getApplicationContext(), list);
                this.a.c.clear();
                this.a.c.addAll(list);
                if (this.a.d == null) {
                    this.a.d = new a(this.a.k, this.a.c);
                    this.a.b.setAdapter(this.a.d);
                    return;
                }
                this.a.d.notifyDataSetChanged();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
        }
    };
    private XListView b;
    private List<PlateBean> c;
    private a d;
    private int e;
    private EditText f;
    private LinearLayout h;
    private TextView i;
    private ImageView j;
    private SearchLabelActivity k;
    private TextWatcher l = new TextWatcher(this) {
        final /* synthetic */ SearchLabelActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence.toString())) {
                this.a.f.setHint(this.a.getResources().getString(R.string.search_section));
                this.a.j.setVisibility(8);
                return;
            }
            this.a.j.setVisibility(0);
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_label);
        this.k = this;
        a();
        g();
        b();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getIntExtra("topicType", 1);
        }
    }

    private void b() {
        this.c = new ArrayList();
        this.d = new a(this, this.c);
        this.b.setAdapter(this.d);
        j();
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.b = (XListView) findViewById(R.id.listView);
        this.b.setPullRefreshEnable(false);
        this.b.setPullLoadEnable(false);
        this.f = (EditText) findViewById(R.id.search_editText);
        this.h = (LinearLayout) findViewById(R.id.search_layout);
        this.i = (TextView) findViewById(R.id.cancel_textView);
        this.j = (ImageView) findViewById(R.id.clear_imageView);
        i();
        h();
    }

    private void h() {
        this.f.addTextChangedListener(this.l);
        this.f.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ SearchLabelActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    an.b(this.a.k);
                    String obj = this.a.f.getText().toString();
                    if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj.trim())) {
                        this.a.a(this.a.getResources().getString(R.string.please_input_search_content));
                    } else {
                        this.a.b(obj.trim());
                    }
                }
                return false;
            }
        });
    }

    private void i() {
        this.b.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SearchLabelActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PlateBean plateBean = (PlateBean) this.a.b.getItemAtPosition(i);
                if (plateBean != null) {
                    String str = plateBean.theme_name;
                    Intent intent = new Intent();
                    intent.putExtra("selectLabelNameTag", str);
                    intent.putExtra("selectLabelThemeIdTag", plateBean.theme_id);
                    this.a.setResult(11, intent);
                    this.a.finish();
                }
            }
        });
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLabelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h.setVisibility(8);
                this.a.f.setHint(this.a.getResources().getString(R.string.search_section));
                an.a(this.a.k, this.a.f);
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLabelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchLabelActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.setText("");
                this.a.f.setHint(this.a.getResources().getString(R.string.search_section));
            }
        });
    }

    private synchronized void j() {
        f();
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.e), new j(this), this.a);
    }

    private synchronized void b(String str) {
        f();
        BudejieApplication.a.a(RequstMethod.GET, j.a(str, this.e), new j(this), this.a);
    }

    public void a(String str) {
        Toast.makeText(this, str, 0).show();
    }
}
