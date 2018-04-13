package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.activity.label.f;
import com.budejie.www.activity.label.i;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.label.widget.HorizontalWaterfallFlow;
import com.budejie.www.label.widget.TagEditText;
import com.budejie.www.util.an;
import com.budejie.www.util.ar;
import com.budejie.www.util.r;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SelectLabelsActivity extends BaseActvityWithLoadDailog {
    private OnClickListener a;
    private int b;
    private TagEditText c;
    private HorizontalWaterfallFlow d;
    private a e;
    private c f;
    private ArrayList<LabelBean> h;
    private RelativeLayout i;
    private LinearLayout j;
    private ImageView k;
    private int l;
    private ListView m;
    private List<LabelBean> n = new ArrayList();
    private i o;
    private SharedPreferences p;
    private j q;
    private net.tsz.afinal.a.a<String> r = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SelectLabelsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void a(String str) {
            Log.d("SelectLabelsActivity", "onSucces t=" + str);
            this.a.h = (ArrayList) f.c(str);
            if (this.a.h != null) {
                this.a.d.setTags(this.a.h);
                this.a.i.setVisibility(0);
                this.a.k.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.a.d.a();
                    }
                });
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            Log.d("SelectLabelsActivity", "onFailure errorNo=" + i + " strMsg=" + str);
        }
    };
    private net.tsz.afinal.a.a s = new net.tsz.afinal.a.a(this) {
        final /* synthetic */ SelectLabelsActivity a;

        {
            this.a = r1;
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            Log.d("SelectLabelsActivity", "onFailure()");
            if (this.a.n != null) {
                this.a.o.a(this.a.n);
                this.a.o.notifyDataSetChanged();
            }
        }

        public void onSuccess(Object obj) {
            Log.d("SelectLabelsActivity", "onSuccess()");
            if (obj != null) {
                Collection c = f.c(obj.toString());
                if (c != null) {
                    this.a.n.addAll(c);
                }
                if (this.a.n != null && this.a.n.size() > 0) {
                    this.a.o.a(this.a.n);
                    this.a.o.notifyDataSetChanged();
                }
            }
        }
    };

    class a implements TextWatcher {
        final /* synthetic */ SelectLabelsActivity a;

        a(SelectLabelsActivity selectLabelsActivity) {
            this.a = selectLabelsActivity;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Log.d("SelectLabelsActivity", "onTextChanged arg0=" + charSequence + " arg1" + i + " arg2=" + i2 + " arg3=" + i3);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Log.d("SelectLabelsActivity", "beforeTextChanged arg0=" + charSequence + " arg1" + i + " arg2=" + i2 + " arg3=" + i3);
        }

        public void afterTextChanged(Editable editable) {
            String str = "";
            if (editable.toString().contains(",")) {
                this.a.b(editable.toString().substring(0, editable.toString().indexOf(",")));
            } else if (editable.toString().contains("，")) {
                this.a.b(editable.toString().substring(0, editable.toString().indexOf("，")));
            } else {
                str = editable.toString();
                if (ar.a(str) > 20) {
                    Log.d("SelectLabelsActivity", "StrUtils.getCharacterNum(labelName)=" + ar.a(str));
                    Toast.makeText(this.a, "标签最多输入10个中文或20个英文", 0).show();
                    this.a.c.b();
                    return;
                }
                this.a.a(str);
            }
        }
    }

    class b implements OnItemClickListener {
        final /* synthetic */ SelectLabelsActivity a;

        b(SelectLabelsActivity selectLabelsActivity) {
            this.a = selectLabelsActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            LabelBean labelBean = (LabelBean) adapterView.getItemAtPosition(i);
            if (labelBean != null) {
                int theme_id = labelBean.getTheme_id();
                String theme_name = labelBean.getTheme_name();
                if (theme_id == -1) {
                    this.a.b(theme_name);
                } else {
                    theme_id = this.a.d.a(theme_name);
                    if (-1 != theme_id) {
                        if (this.a.h == null || !((LabelBean) this.a.h.get(theme_id)).isSelected()) {
                            this.a.a((LabelBean) this.a.h.get(theme_id), this.a.d.a(theme_id));
                        } else {
                            Toast.makeText(this.a, "该标签已经存在", 0).show();
                        }
                    } else if (this.a.c.a(theme_name)) {
                        Toast.makeText(this.a, "该标签已经存在", 0).show();
                    } else {
                        this.a.a(labelBean, null);
                    }
                }
            }
            this.a.c.a();
            this.a.a(false);
        }
    }

    class c implements OnKeyListener {
        final /* synthetic */ SelectLabelsActivity a;

        c(SelectLabelsActivity selectLabelsActivity) {
            this.a = selectLabelsActivity;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == 66) {
                if (keyEvent.getAction() == 0) {
                    this.a.b(this.a.c.getText());
                }
                return true;
            }
            if (i == 67) {
            }
            return false;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_label);
        com.budejie.www.widget.a.a((Activity) this);
        this.q = new j();
        this.l = ((WindowManager) getSystemService("window")).getDefaultDisplay().getHeight();
        this.b = getIntent().getIntExtra("TOUGAO_TYPE", 0);
        this.p = getSharedPreferences("weiboprefer", 0);
        b();
        a();
        a(this.b);
    }

    private void a() {
        this.c = (TagEditText) findViewById(R.id.tag_edit_view);
        this.j = (LinearLayout) findViewById(R.id.ll_input_layout);
        this.e = new a(this);
        this.f = new c(this);
        this.c.a(this.e, this.f);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectLabelsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                an.a(this.a, this.a.c.getEditText());
            }
        });
        this.i = (RelativeLayout) findViewById(R.id.ll_label_title);
        this.k = (ImageView) findViewById(R.id.iv_next_page);
        this.d = (HorizontalWaterfallFlow) findViewById(R.id.horizontal_waterfall_flow);
        if (this.l < SecExceptionCode.SEC_ERROR_UMID_VALID) {
            this.d.setLines(2);
        }
        this.m = (ListView) findViewById(R.id.list_view_search);
        this.o = new i(this);
        this.m.setAdapter(this.o);
        this.m.setOnItemClickListener(new b(this));
        this.d.setTagClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectLabelsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                LabelBean labelBean = (LabelBean) view.getTag();
                TextView textView = (TextView) view;
                if (!labelBean.isSelected()) {
                    if (this.a.c.getLabelSize() < 5) {
                        this.a.a(labelBean, textView);
                    } else {
                        Toast.makeText(this.a, "最多只能添加5个标签", 0).show();
                    }
                }
            }
        });
        if (this.h != null) {
            this.d.setTags(this.h);
        }
    }

    private void a(LabelBean labelBean, final TextView textView) {
        View textView2 = new TextView(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        textView2.setLayoutParams(layoutParams);
        textView2.setSingleLine(true);
        textView2.setTextSize(15.0f);
        textView2.setGravity(17);
        textView2.setTextColor(getResources().getColor(com.budejie.www.util.j.bh));
        textView2.setEllipsize(TruncateAt.END);
        CharSequence theme_name = labelBean.getTheme_name();
        if (theme_name.length() > 4) {
            theme_name = theme_name.substring(0, 3) + "…";
        }
        textView2.setText(theme_name);
        textView2.setTag(R.id.tag_key, labelBean);
        textView2.setBackgroundResource(com.budejie.www.util.j.bg);
        textView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectLabelsActivity b;

            public void onClick(View view) {
                this.b.c.removeView(view);
                this.b.d.a(textView);
            }
        });
        this.c.addView(textView2, this.c.getChildCount() - 1);
        if (textView != null) {
            this.d.a(textView);
        }
    }

    private void b() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        d(R.id.navigation_bar);
        setTitle((int) R.string.add_label);
        a(null);
        this.a = new OnClickListener(this) {
            final /* synthetic */ SelectLabelsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    String selectTagId = this.a.c.getSelectTagId();
                    String selectTagName = this.a.c.getSelectTagName();
                    String selectTagType = this.a.c.getSelectTagType();
                    Log.d("selectTag", "ids=" + selectTagId);
                    Log.d("selectTag", "names=" + selectTagName);
                    Log.d("selectTag", "types=" + selectTagType);
                    if (an.a(this.a.p)) {
                        MobclickAgent.onEvent(this.a, "E05-A10", "视频上传发表点击");
                        Intent intent = new Intent();
                        intent.putExtra("theme_id_key", selectTagId);
                        intent.putExtra("theme_name_key", selectTagName);
                        intent.putExtra("theme_type_key", selectTagType);
                        this.a.setResult(20, intent);
                        this.a.g();
                        return;
                    }
                    an.a(this.a, null);
                }
            }
        };
        c(this.a, getResources().getString(R.string.sure));
    }

    private void c(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_right, null);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectLabelsActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setRightView(textView);
        }
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    private void a(int i) {
        if (i == 0) {
            i = 1;
        }
        BudejieApplication.a.a(RequstMethod.GET, new h("http://d.api.budejie.com/tag/recommend").a(i).b().a().toString(), new j(), this.r);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        g();
        return true;
    }

    private void g() {
        finish();
    }

    private void b(String str) {
        String trim = str.trim();
        if (this.c.getLabelSize() >= 5) {
            this.c.a();
            Toast.makeText(this, "最多只能添加5个标签", 0).show();
        } else if (TextUtils.isEmpty(trim) || trim.getBytes().length < 2) {
            this.c.a();
            Toast.makeText(this, "标签最少需要2个字符", 0).show();
        } else {
            if (r.a(trim)) {
                Toast.makeText(this, "标签中不能包含特殊符号", 0).show();
            } else {
                int a = this.d.a(trim);
                if (-1 != a) {
                    if (this.h == null || !((LabelBean) this.h.get(a)).isSelected()) {
                        a((LabelBean) this.h.get(a), this.d.a(a));
                    } else {
                        Toast.makeText(this, "该标签已经存在", 0).show();
                    }
                } else if (this.c.a(trim)) {
                    Toast.makeText(this, "该标签已经存在", 0).show();
                } else {
                    LabelBean labelBean = new LabelBean();
                    labelBean.setTheme_id(-1);
                    labelBean.setTheme_name(trim);
                    labelBean.setTheme_type("0");
                    a(labelBean, null);
                }
            }
            this.c.a();
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            a(false);
            return;
        }
        a(true);
        a(this.b, str);
    }

    private void a(boolean z) {
        if (z) {
            this.m.setVisibility(0);
            this.i.setVisibility(8);
            this.d.setVisibility(8);
            return;
        }
        this.m.setVisibility(8);
        this.i.setVisibility(0);
        this.d.setVisibility(0);
    }

    private void a(int i, String str) {
        if (this.n == null) {
            this.n = new ArrayList();
        } else {
            this.n.clear();
        }
        LabelBean labelBean = new LabelBean();
        labelBean.setTheme_id(-1);
        labelBean.setTheme_name(str);
        this.n.add(labelBean);
        this.o.a(this.n);
        this.o.notifyDataSetChanged();
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.q.f((Context) this, i + "", str), this.s);
    }
}
