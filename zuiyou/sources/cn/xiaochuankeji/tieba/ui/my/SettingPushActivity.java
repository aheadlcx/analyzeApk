package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.user.SettingJson;
import cn.xiaochuankeji.tieba.api.user.a;
import cn.xiaochuankeji.tieba.ui.base.h;
import com.alibaba.fastjson.JSONObject;
import rx.b.g;
import rx.e;

public class SettingPushActivity extends h {
    private a d = new a();
    @BindView
    ImageView pushChat;
    @BindView
    ImageView pushComment;
    @BindView
    ImageView pushRecommend;

    public static void a(Context context) {
        context.startActivity(new Intent(context, SettingPushActivity.class));
    }

    protected int a() {
        return R.layout.activity_setting_push;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        j();
        this.d.a().d(new g<SettingJson, Boolean>(this) {
            final /* synthetic */ SettingPushActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((SettingJson) obj);
            }

            public Boolean a(SettingJson settingJson) {
                boolean z;
                boolean z2 = false;
                Editor putBoolean = cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_recommend_notification", settingJson.good == 1);
                String str = "key_comment_notification";
                if (settingJson.review == 1) {
                    z = true;
                } else {
                    z = false;
                }
                Editor putBoolean2 = putBoolean.putBoolean(str, z);
                String str2 = "kChatMsgNotification";
                if (settingJson.msg == 1) {
                    z2 = true;
                }
                putBoolean2.putBoolean(str2, z2).apply();
                return Boolean.valueOf(true);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ SettingPushActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Boolean bool) {
                this.a.j();
            }
        });
    }

    private void j() {
        SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
        boolean z = a.getBoolean("key_recommend_notification", true);
        boolean z2 = a.getBoolean("key_comment_notification", true);
        boolean z3 = a.getBoolean("kChatMsgNotification", true);
        this.pushRecommend.setSelected(z);
        this.pushComment.setSelected(z2);
        this.pushChat.setSelected(z3);
    }

    protected void onResume() {
        super.onResume();
        j();
    }

    @OnClick
    public void push_recommend() {
        int i = 1;
        boolean z = !this.pushRecommend.isSelected();
        this.pushRecommend.setSelected(z);
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_recommend_notification", z).apply();
        String str = "good";
        if (!z) {
            i = 0;
        }
        a(str, i);
    }

    @OnClick
    public void push_comment() {
        boolean z;
        int i = 1;
        if (this.pushComment.isSelected()) {
            z = false;
        } else {
            z = true;
        }
        this.pushComment.setSelected(z);
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_comment_notification", z).apply();
        String str = "review";
        if (!z) {
            i = 0;
        }
        a(str, i);
    }

    @OnClick
    public void push_chat() {
        boolean z;
        int i = 1;
        if (this.pushChat.isSelected()) {
            z = false;
        } else {
            z = true;
        }
        this.pushChat.setSelected(z);
        cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("kChatMsgNotification", z).apply();
        String str = "msg";
        if (!z) {
            i = 0;
        }
        a(str, i);
    }

    private void a(String str, int i) {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        this.d.a(str, i).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<JSONObject>(this) {
            final /* synthetic */ SettingPushActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
            }

            public void a(JSONObject jSONObject) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
            }
        });
    }
}
