package cn.xiaochuankeji.tieba.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuan.jsbridge.data.JSMenuConfig;
import cn.xiaochuan.jsbridge.data.JSMenuConfig.Item;
import cn.xiaochuan.jsbridge.f;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.utils.share.WebPageShare;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.a.a.a.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.List;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class WebActivity extends AbstractWebActivity {
    private static final a k = null;
    AppCompatTextView a;
    AppCompatImageView b;
    AppCompatTextView c;
    AppCompatImageView d;
    View g;
    WebImageView h;
    AppCompatTextView i;
    View j;

    static {
        k();
    }

    private static void k() {
        b bVar = new b("WebActivity.java", WebActivity.class);
        k = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.webview.WebActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 55);
    }

    public static void a(Context context, cn.xiaochuan.jsbridge.b bVar) {
        Intent intent = new Intent(context, WebActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        intent.putExtra("web_data", bVar);
        context.startActivity(intent);
    }

    public static void a(Activity activity, cn.xiaochuan.jsbridge.b bVar, int i) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra("web_data", bVar);
        activity.startActivityForResult(intent, i);
    }

    static final void a(WebActivity webActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(k, this, this, bundle);
        c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void e() {
        LayoutInflater.from(this).inflate(R.layout.menu_web_title, this.action_bar, true);
        this.a = (AppCompatTextView) findViewById(R.id.icon_title);
        this.b = (AppCompatImageView) findViewById(R.id.back);
        this.c = (AppCompatTextView) findViewById(R.id.close);
        this.g = findViewById(R.id.second_option);
        this.i = (AppCompatTextView) findViewById(R.id.second_txt);
        this.h = (WebImageView) findViewById(R.id.second_icon);
        this.j = findViewById(R.id.feedback);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.q();
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.g.setVisibility(8);
        this.d = (AppCompatImageView) findViewById(R.id.option);
        this.d.setVisibility(8);
    }

    protected void a(cn.xiaochuan.jsbridge.b bVar) {
        if (bVar.a()) {
            Bundle bundle = bVar.e;
            this.j.setVisibility(8);
            if (bundle != null && bundle.getBoolean("show_feed_back", false)) {
                bundle.remove("show_feed_back");
                this.j.setVisibility(0);
                this.j.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ WebActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Member member = new Member();
                        member.setId(51401010);
                        member.setName("柚柚灵");
                        cn.xiaochuankeji.tieba.ui.chat.a.b.a(this.a, member, true);
                    }
                });
            }
            getIntent().putExtra("web_data", bVar);
            b(bVar);
            this.a.setVisibility(0);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.a.setText(charSequence);
            this.a.setEllipsize(TruncateAt.MARQUEE);
            this.a.setSelected(true);
        }
    }

    protected void a(f fVar) {
        super.a(fVar);
        fVar.a(JSMenuConfig.a, new com.a.a.a.a(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, final e eVar) {
                JSONObject parseObject = JSON.parseObject(str);
                JSONArray jSONArray = parseObject.getJSONArray("items");
                if (jSONArray == null || jSONArray.isEmpty()) {
                    this.a.d.setVisibility(8);
                } else {
                    List parseArray = JSON.parseArray(jSONArray.toJSONString(), Item.class);
                    JSMenuConfig jSMenuConfig = new JSMenuConfig();
                    jSMenuConfig.c = (Item[]) parseArray.toArray(new Item[0]);
                    this.a.a(jSMenuConfig);
                }
                this.a.g.setVisibility(8);
                JSONObject jSONObject = parseObject.getJSONObject("button");
                if (jSONObject != null) {
                    CharSequence string = jSONObject.getString("title");
                    Object string2 = jSONObject.getString("image_url");
                    final String string3 = jSONObject.getString("callback");
                    if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
                        this.a.g.setVisibility(0);
                        this.a.g.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass4 c;

                            public void onClick(View view) {
                                if (eVar != null && this.c.a.e != null) {
                                    this.c.a.e.a(string3, null, null);
                                }
                            }
                        });
                        if (!TextUtils.isEmpty(string2)) {
                            this.a.h.setImageURI(string2);
                        } else if (!TextUtils.isEmpty(string)) {
                            this.a.i.setText(string);
                        }
                    }
                }
            }
        });
    }

    private void a(final JSMenuConfig jSMenuConfig) {
        if (jSMenuConfig == null || jSMenuConfig.c == null || jSMenuConfig.c.length == 0) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        if (jSMenuConfig.c.length == 1 && jSMenuConfig.c[0].id.equals("share")) {
            this.d.setImageResource(R.drawable.icon_web_share_new);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WebActivity b;

                public void onClick(View view) {
                    this.b.e.a(jSMenuConfig.c[0].callback, null, null);
                }
            });
            return;
        }
        this.d.setImageResource(R.drawable.ic_nav_more);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebActivity b;

            public void onClick(View view) {
                this.b.b(jSMenuConfig);
            }
        });
    }

    private void b(final JSMenuConfig jSMenuConfig) {
        SDPopupMenu sDPopupMenu = new SDPopupMenu(this, this.d, SDPopupMenu.a(this.d), new SDPopupMenu.b(this) {
            final /* synthetic */ WebActivity b;

            public void b(int i) {
                this.b.e.a(jSMenuConfig.c[i].callback, null, null);
            }
        });
        for (int i = 0; i < jSMenuConfig.c.length; i++) {
            boolean z;
            String str = jSMenuConfig.c[i].title;
            if (i == jSMenuConfig.c.length - 1) {
                z = true;
            } else {
                z = false;
            }
            sDPopupMenu.a(str, i, z);
        }
        sDPopupMenu.b();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    protected WebPageShare a(int i, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        return new WebPageShare(str, str2, str3, str4);
    }
}
