package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.h.c;
import com.budejie.www.util.b;
import com.budejie.www.util.i;
import com.budejie.www.widget.a;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.google.analytics.tracking.android.HitTypes;
import com.umeng.analytics.MobclickAgent;

public class ReprintPostsActivity extends OauthWeiboBaseAct implements OnClickListener {
    TextWatcher a = new TextWatcher(this) {
        final /* synthetic */ ReprintPostsActivity a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int a = this.a.h - charSequence.length();
            this.a.c.setSelected(a < 0);
            this.a.c.setText(String.valueOf(a));
        }
    };
    private ParseTagEditText b;
    private TextView c;
    private TextView d;
    private AsyncImageView e;
    private ListItemObject f;
    private InputMethodManager g;
    private int h = R$styleable.Theme_Custom_send_btn_text_color;
    private final int i = 435;
    private final int j = 475;
    private TextView k;
    private BudejieApplication l;
    private Handler m = new Handler(this) {
        final /* synthetic */ ReprintPostsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.finish();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(c.a().b());
        setContentView(R.layout.reprint_posts_layout);
        a.a((Activity) this);
        this.l = (BudejieApplication) getApplication();
        this.f = (ListItemObject) getIntent().getSerializableExtra(HitTypes.ITEM);
        this.g = (InputMethodManager) getSystemService("input_method");
        b();
        c();
    }

    private void b() {
        this.b = (ParseTagEditText) findViewById(R.id.repint_input_content);
        this.b.setTextChangedListener(this.a);
        this.b.setListener(new com.budejie.www.widget.parsetagview.a(this));
        this.b.requestFocus();
        this.b.setSelection(0);
        this.d = (TextView) findViewById(R.id.posts_content);
        this.e = (AsyncImageView) findViewById(R.id.posts_image);
        this.c = (TextView) findViewById(R.id.reprint_textsize_limit);
        findViewById(R.id.write_left_btn).setOnClickListener(this);
        findViewById(R.id.comment_send).setOnClickListener(this);
        findViewById(R.id.selector_contacts).setOnClickListener(this);
        findViewById(R.id.selector_label).setOnClickListener(this);
        findViewById(R.id.add_vote).setVisibility(8);
        findViewById(R.id.add_vote_name).setVisibility(8);
        this.k = (TextView) findViewById(R.id.selector_label_name);
        this.k.setOnClickListener(this);
    }

    private void c() {
        ListItemObject listItemObject = this.f;
        if ("61".equals(this.f.getType())) {
            listItemObject = this.f.getOriginal_topic();
            if (listItemObject == null) {
                Toast.makeText(this, R.string.reprint_post_delete, 0).show();
                finish();
                return;
            }
            this.b.setText("//@" + this.f.getName() + ":" + this.f.getContent());
        }
        int length = listItemObject.getName().length() + 1;
        if (!"51".equals(listItemObject.getType()) || listItemObject.getRichObject() == null) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(listItemObject.getName() + ":" + listItemObject.getContent());
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(getString(c.a().b(R.attr.repint_post_user_name_color)))), 0, length, 33);
            this.d.setText(spannableStringBuilder);
            if (!TextUtils.isEmpty(listItemObject.getImgUrl())) {
                this.e.setVisibility(0);
                this.e.setAsyncCacheImage(listItemObject.getImgUrl(), R.color.apply_listview_cacahecolor);
                return;
            }
            return;
        }
        spannableStringBuilder = new SpannableStringBuilder(listItemObject.getName() + ":" + listItemObject.getRichObject().getTitle());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(getString(c.a().b(R.attr.repint_post_user_name_color)))), 0, length, 33);
        this.d.setText(spannableStringBuilder);
        if (!TextUtils.isEmpty(listItemObject.getRichObject().getImgUrl())) {
            this.e.setVisibility(0);
            this.e.setAsyncCacheImage(listItemObject.getRichObject().getImgUrl(), R.color.apply_listview_cacahecolor);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selector_contacts:
                MobclickAgent.onEvent((Context) this, "E04-A02", "转载-选择联系人");
                startActivityForResult(new Intent(this, SelectorContactsActivity.class), 435);
                return;
            case R.id.selector_label:
            case R.id.selector_label_name:
                MobclickAgent.onEvent((Context) this, "E04-A02", "转载-选择标签");
                this.l.g().am.a(Integer.valueOf(this.l.g().am.a().intValue() + 1));
                Intent intent = new Intent(this, SelectLabelsActivity.class);
                intent.putExtra("TOUGAO_TYPE", 1);
                startActivityForResult(intent, 20);
                return;
            case R.id.write_left_btn:
                MobclickAgent.onEvent((Context) this, "E04-A02", "转载-取消");
                a();
                return;
            case R.id.comment_send:
                if (this.c.isSelected()) {
                    Toast.makeText(this, R.string.reprint_content_too_much, 0).show();
                    return;
                } else {
                    a(this.b.getText().toString());
                    return;
                }
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            String stringExtra;
            CharSequence append;
            if (i == 435 || i == 436) {
                stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
                if (!TextUtils.isEmpty(stringExtra)) {
                    if (i == 436) {
                        append = new StringBuilder("").append(stringExtra).append(" ");
                    } else {
                        append = new StringBuilder("@").append(stringExtra).append(" ");
                    }
                    this.b.getEditableText().insert(this.b.getSelectionStart(), append);
                }
            } else if (i == 475 || i == 476) {
                stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_TOPIC_NAME));
                if (!TextUtils.isEmpty(stringExtra)) {
                    if (i == 476) {
                        append = new StringBuilder("").append(stringExtra).append("#");
                    } else {
                        append = new StringBuilder("#").append(stringExtra).append("#");
                    }
                    this.b.getEditableText().insert(this.b.getSelectionStart(), append);
                }
            }
        } else if (i2 == 20) {
            Object stringExtra2 = intent.getStringExtra("theme_name_key");
            if (!TextUtils.isEmpty(stringExtra2)) {
                a(stringExtra2.split(","), i);
            }
        }
    }

    public void a(String[] strArr, int i) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            CharSequence append;
            if (i == 21) {
                append = new StringBuilder("").append(strArr[i2]).append("#");
            } else {
                append = new StringBuilder("#").append(strArr[i2]).append("#");
            }
            this.b.getEditableText().insert(this.b.getSelectionStart(), append);
        }
    }

    private void a(String str) {
        String string;
        int currentTimeMillis = ((int) System.currentTimeMillis()) % 100;
        String d = i.a().d();
        String string2 = getSharedPreferences("weiboprefer", 0).getString("id", "");
        String wid = this.f.getWid();
        String str2 = null;
        if ("61".equals(this.f.getType())) {
            wid = this.f.getOriginal_topic().getWid();
            str2 = this.f.getWid();
        }
        if (TextUtils.isEmpty(str)) {
            string = getResources().getString(R.string.reship);
        } else {
            string = str;
        }
        b.a(this, a(string2, string, "", ((BudejieApplication) getApplication()).b, wid, str2), d, currentTimeMillis, getString(R.string.repint_failed));
        a();
    }

    public void a() {
        this.g.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        this.m.sendEmptyMessageDelayed(0, 200);
    }

    public net.tsz.afinal.a.b a(String str, String str2, String str3, String[] strArr, String str4, String str5) {
        net.tsz.afinal.a.b bVar = new net.tsz.afinal.a.b();
        bVar.d(HistoryOpenHelper.COLUMN_UID, str);
        bVar.d("format", "json");
        bVar.d("app", "8");
        bVar.d("content", str2);
        bVar.d("shareType", str3);
        if (strArr != null) {
            bVar.d("longitude", strArr[0]);
            bVar.d("latitude", strArr[1]);
        }
        bVar.d("type", "61");
        bVar.d("original_pid", str4);
        bVar.d("forward_pid", str5);
        return bVar;
    }
}
