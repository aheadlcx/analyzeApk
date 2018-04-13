package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.widget.EditText;
import cn.htjyb.c.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.favorite.b;
import cn.xiaochuankeji.tieba.background.favorite.d;
import cn.xiaochuankeji.tieba.background.favorite.f;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import org.greenrobot.eventbus.c;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateOrEditFavoriteActivity extends h {
    protected String d;
    protected String e;
    private long f;
    private String g;
    private boolean h = false;
    private NavigationBar i;
    private EditText j;

    public static void a(Activity activity, int i) {
        a(activity, 0, null, i);
    }

    protected int a() {
        return R.layout.activity_favorite_create;
    }

    public static void a(Activity activity, long j, String str, int i) {
        Intent intent = new Intent(activity, CreateOrEditFavoriteActivity.class);
        intent.putExtra("key_f_id", j);
        intent.putExtra("key_f_name", str);
        if (-1 != i) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivity(intent);
        }
    }

    protected boolean a(Bundle bundle) {
        boolean z;
        this.f = getIntent().getLongExtra("key_f_id", 0);
        this.g = getIntent().getStringExtra("key_f_name");
        if (this.f == 0) {
            z = false;
        } else {
            z = true;
        }
        this.h = z;
        this.d = this.h ? "编辑收藏夹" : "创建收藏夹";
        this.e = this.h ? "完成" : "创建";
        return true;
    }

    protected void i_() {
        this.i = (NavigationBar) findViewById(R.id.navBar);
        this.j = (EditText) findViewById(R.id.etInput);
        this.j.setSelection(this.j.getText().length());
        this.i.setTitle(this.d);
        this.i.setOptionText(this.e);
        this.j.setSingleLine(true);
        this.j.setHint("请输入收藏夹名称");
        this.j.setFilters(new InputFilter[]{new LengthFilter(12)});
        if (this.h) {
            this.j.setText(this.g);
            this.j.setSelection(this.j.length());
        }
    }

    public void s() {
        String j = j();
        a.a((Activity) this);
        a(j);
    }

    protected String j() {
        return this.j.getText().toString();
    }

    protected void a(String str) {
        String trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            g.a("名称不能为空");
            return;
        }
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        if (TextUtils.isEmpty(this.g)) {
            b(trim);
        } else {
            c(trim);
        }
    }

    private void b(String str) {
        new b(System.currentTimeMillis(), str, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ CreateOrEditFavoriteActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                g.a("创建成功");
                this.a.finish();
                long optLong = jSONObject.optLong("id");
                String optString = jSONObject.optString("name");
                int optInt = jSONObject.optInt(Favorite.KEY_POST_COUNT);
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("id", optLong);
                    jSONObject2.put("name", optString);
                    jSONObject2.put(Favorite.KEY_POST_COUNT, optInt);
                    Favorite favorite = new Favorite(jSONObject2);
                    f.a().a(favorite);
                    c.a().d(new a(favorite));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ CreateOrEditFavoriteActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void c(String str) {
        new d(this.f, System.currentTimeMillis(), str, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ CreateOrEditFavoriteActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                g.a("编辑成功");
                String optString = jSONObject.optString("name");
                c.a().d(new c(optString, this.a.f));
                Intent intent = new Intent();
                intent.putExtra("edit_complete_name", optString);
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ CreateOrEditFavoriteActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                g.a(xCError.getMessage());
            }
        }).execute();
    }
}
