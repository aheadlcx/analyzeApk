package cn.xiaochuankeji.tieba.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.e;
import cn.xiaochuan.push.f;
import cn.xiaochuankeji.tieba.api.config.c;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.push.c.d;
import cn.xiaochuankeji.tieba.push.c.i;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.utils.OpenActivityUtils.ActivityType;
import com.alibaba.fastjson.JSONObject;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class b {
    private f a = new f(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public Context a() {
            return BaseApplication.getAppContext();
        }

        public void a(int i, String str, e eVar) throws Exception {
            this.a.a(i, null, String.valueOf(eVar.k));
            if (eVar.f == 999) {
                c.a().a(eVar);
                return;
            }
            switch (i) {
                case 1:
                    boolean a = this.a.a(i, eVar);
                    if (!a) {
                        cn.xiaochuankeji.tieba.push.e.a().a(eVar);
                        eVar.e = true;
                        eVar.h = System.currentTimeMillis();
                        cn.xiaochuankeji.tieba.push.b.c.a(eVar);
                    }
                    eVar.n = a ? 2 : 0;
                    c.a().a(eVar);
                    return;
                case 2:
                    this.a.b(eVar);
                    return;
                case 3:
                    this.a.a(eVar);
                    return;
                case 4:
                    cn.xiaochuankeji.tieba.push.b.c.a(eVar.b, 0, System.currentTimeMillis());
                    return;
                default:
                    return;
            }
        }

        public void a(String str, String str2) throws Exception {
            this.a.a(-1, "register:", String.valueOf("mid:" + a.g().c() + " with " + String.valueOf(cn.xiaochuan.push.a.b())));
            this.a.e();
        }
    };
    private Runnable b = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            c.a().b();
        }
    };

    private void a(int i, String str, String str2) {
        String str3 = null;
        switch (i) {
            case 1:
                str3 = "receive through:";
                break;
            case 2:
                str3 = "receive:";
                break;
            case 3:
                str3 = "click:";
                break;
            case 4:
                str3 = ShareConstants.RES_DEL_TITLE;
                break;
        }
        if (TextUtils.isEmpty(str)) {
            str = str3;
        }
        com.izuiyou.a.a.a.b("PushManager", String.valueOf(str) + " " + str2);
    }

    public void a() {
        cn.xiaochuan.push.c.a().a(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                cn.xiaochuan.push.a.a().a(this.a.a);
            }
        });
    }

    public void b() {
        cn.xiaochuan.push.a.a().a(a.g().c());
    }

    public void c() {
        c.c();
        cn.xiaochuan.push.a.a().b(a.g().c());
    }

    private void a(e eVar) {
        if (eVar != null) {
            cn.xiaochuan.push.a.a().a(eVar.g);
            JSONObject jSONObject = eVar.k;
            c.a().b(eVar);
            if (!TextUtils.isEmpty(eVar.b)) {
                cn.xiaochuankeji.tieba.push.b.c.a(eVar.b, System.currentTimeMillis(), 0);
            }
            int i = eVar.f;
            if (i != 4) {
                Intent intent = new Intent(BaseApplication.getAppContext(), MainActivity.class);
                intent.setFlags(872415232);
                ActivityType activityType = null;
                long longValue;
                switch (i) {
                    case -1:
                        activityType = ActivityType.kNone;
                        break;
                    case 2:
                        longValue = jSONObject.getLongValue("id");
                        activityType = ActivityType.kActivityPostDetail;
                        intent.putExtra("PostID", longValue);
                        intent.putExtra("ToPostComment", false);
                        break;
                    case 5:
                        longValue = jSONObject.getLongValue("id");
                        activityType = ActivityType.kActivityTopicDetail;
                        intent.putExtra("ActivityType", activityType.toInt());
                        intent.putExtra("TopicID", longValue);
                        break;
                    case 6:
                        longValue = jSONObject.getLongValue("id");
                        long longValue2 = jSONObject.getLongValue("rid");
                        long longValue3 = jSONObject.getLongValue("cid");
                        long longValue4 = jSONObject.getLongValue("prid");
                        if (0 != longValue) {
                            if (0 == longValue2 || 0 == longValue3 || 0 == longValue4) {
                                if (0 != longValue4 || 0 == longValue3 || 0 == longValue2) {
                                    if (0 != longValue4 || 0 == longValue3 || 0 != longValue2) {
                                        if (0 != longValue4 || 0 != longValue3 || 0 != longValue2) {
                                            activityType = ActivityType.kNone;
                                            break;
                                        }
                                        activityType = ActivityType.kActivityPostDetail;
                                        intent.putExtra("ActivityType", activityType.toInt());
                                        intent.putExtra("PostID", longValue);
                                        intent.putExtra("ToPostComment", true);
                                        break;
                                    }
                                    activityType = ActivityType.kActivityPostDetail;
                                    intent.putExtra("ActivityType", activityType.toInt());
                                    intent.putExtra("PostID", longValue);
                                    intent.putExtra("post_comment_id", longValue3);
                                    intent.putExtra("ToPostComment", true);
                                    break;
                                }
                                activityType = ActivityType.kInnerCommentDetail;
                                intent.putExtra("PostID", longValue);
                                intent.putExtra("second_parent_id", longValue2);
                                intent.putExtra("second_src_id", 0);
                                intent.putExtra("second_child_id", longValue3);
                                break;
                            }
                            activityType = ActivityType.kInnerCommentDetail;
                            intent.putExtra("PostID", longValue);
                            intent.putExtra("second_parent_id", longValue4);
                            intent.putExtra("second_src_id", longValue2);
                            intent.putExtra("second_child_id", longValue3);
                            break;
                        }
                        activityType = ActivityType.kNone;
                        break;
                        break;
                    case 16:
                        if (cn.xiaochuankeji.tieba.background.utils.c.a.c().p()) {
                            longValue = jSONObject.getLongValue("rid");
                            activityType = ActivityType.kActivityUgcVideoDetail;
                            intent.putExtra("ugcvideo_id", longValue);
                            break;
                        }
                        break;
                }
                if (activityType != null) {
                    intent.putExtra("ActivityType", activityType.toInt());
                    BaseApplication.getAppContext().startActivity(intent);
                }
            }
        }
    }

    private void b(e eVar) {
        if (eVar != null) {
            int i = eVar.f;
            JSONObject jSONObject = eVar.k;
            switch (i) {
                case 7:
                    d();
                    break;
                case 11:
                    org.greenrobot.eventbus.c.a().d(new d());
                    MainActivity.a("tab_my", 1);
                    break;
                case 14:
                    if (cn.xiaochuankeji.tieba.background.utils.c.a.c().p()) {
                        break;
                    }
                    break;
                case 15:
                    if (cn.xiaochuankeji.tieba.background.utils.c.a.c().p()) {
                        break;
                    }
                    break;
            }
            c.a().a(eVar);
            eVar.h = System.currentTimeMillis();
            cn.xiaochuankeji.tieba.push.b.c.a(eVar);
        }
    }

    private static void d() {
        cn.xiaochuankeji.tieba.ui.homepage.c.a(true);
        org.greenrobot.eventbus.c.a().d(new i());
    }

    private void e() {
        cn.xiaochuan.push.c.a().b(this.b);
        cn.xiaochuan.push.c.a().a(this.b, 5000);
    }

    private boolean a(int i, e eVar) {
        if (i != 1) {
            return true;
        }
        return cn.xiaochuankeji.tieba.push.b.c.a(eVar.b);
    }
}
