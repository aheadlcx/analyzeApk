package cn.xiaochuankeji.tieba.push.d;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.push.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.config.c;
import cn.xiaochuankeji.tieba.push.c.d;
import cn.xiaochuankeji.tieba.push.c.i;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import cn.xiaochuankeji.tieba.ui.utils.OpenActivityUtils;
import cn.xiaochuankeji.tieba.ui.utils.OpenActivityUtils.ActivityType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import com.izuiyou.a.a.a;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class b {
    private static boolean a;
    private static boolean b;
    private static boolean c;

    static void a(String str) {
        com.izuiyou.a.a.b.c(str);
        JSONObject parseObject = JSON.parseObject(str);
        if (parseObject.containsKey("type") && cn.xiaochuankeji.tieba.push.b.a(parseObject.getIntValue("type"))) {
            cn.xiaochuankeji.tieba.push.b.a(parseObject);
            return;
        }
        JSONArray jSONArray = parseObject.getJSONArray("list");
        if (jSONArray == null || jSONArray.isEmpty()) {
            a.b("NotifyHandler", "empty push list");
            return;
        }
        long longValue = parseObject.getLongValue("mid");
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i).getJSONObject("data");
            JSONObject jSONObject2 = jSONObject.getJSONObject("aps");
            if (!(jSONObject2 == null || TextUtils.isEmpty(jSONObject2.getString("msg_id")))) {
                c.a().a(e.a(jSONObject2));
            }
            int intValue = jSONObject.getIntValue("type");
            if (intValue != 999) {
                long longValue2;
                if (jSONObject.containsKey("ct")) {
                    longValue2 = jSONObject.getLongValue("ct");
                } else {
                    longValue2 = System.currentTimeMillis() / 1000;
                }
                if (intValue == 1) {
                    e(longValue2, longValue, jSONObject);
                } else if (intValue == 9) {
                    j(longValue2, longValue, jSONObject);
                } else if (intValue == 10) {
                    k(longValue2, longValue, jSONObject);
                } else if (intValue == 14) {
                    d(longValue2, longValue, jSONObject);
                } else if (intValue == 15) {
                    c(longValue2, longValue, jSONObject);
                } else if (intValue == 7) {
                    cn.xiaochuankeji.tieba.ui.homepage.c.a(true);
                    org.greenrobot.eventbus.c.a().d(new i());
                } else if (intValue == 11) {
                    org.greenrobot.eventbus.c.a().d(new d());
                } else if (intValue == 12) {
                    i(longValue2, longValue, jSONObject);
                } else if (intValue == 18) {
                    b(longValue2, longValue, jSONObject);
                } else if (intValue == 19) {
                    a(longValue2, longValue, jSONObject);
                } else if (intValue == 20) {
                    h(longValue2, longValue, jSONObject);
                } else if (intValue == 21) {
                    g(longValue2, longValue, jSONObject);
                } else if (intValue == 22) {
                    f(longValue2, longValue, jSONObject);
                } else if (intValue == 51) {
                    l(longValue2, longValue, jSONObject);
                } else if (!(intValue == 4 || jSONObject2 == null)) {
                    cn.xiaochuan.push.b.a aVar = new cn.xiaochuan.push.b.a(jSONObject2.getJSONObject("aps"));
                    if (!TextUtils.isEmpty(aVar.a)) {
                        a(aVar.a, intValue, jSONObject2);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private static void f(long j, long j2, JSONObject jSONObject) {
        com.izuiyou.a.a.b.c(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            boolean z;
            boolean z2;
            JSONObject jSONObject3 = jSONObject2.getJSONObject("article");
            int i = 0;
            int i2 = 0;
            long j3 = 0;
            long j4 = 0;
            long j5 = 0;
            String str = "";
            long j6 = 0;
            if (jSONObject3 != null) {
                j3 = jSONObject3.getLongValue("id");
            }
            String str2 = "";
            boolean z3 = false;
            long j7 = 0;
            JSONObject jSONObject4 = jSONObject2.getJSONObject(FFmpegMediaMetadataRetriever.METADATA_KEY_COMMENT);
            if (jSONObject4 != null) {
                j5 = jSONObject4.getLongValue("id");
                i2 = jSONObject4.getIntValue("like_inc");
                int intValue = jSONObject4.getIntValue("comment_inc");
                str = jSONObject4.getString("txt");
                if (jSONObject4.getIntValue("has_img") == 1) {
                    JSONArray jSONArray = jSONObject4.getJSONArray("imgs");
                    if (jSONArray != null && jSONArray.size() > 0) {
                        j6 = jSONArray.getLongValue(0);
                        i = intValue;
                        j4 = j5;
                    }
                }
                i = intValue;
                j4 = j5;
            }
            jSONObject4 = jSONObject2.getJSONObject("reply_comment");
            if (jSONObject4 != null) {
                z3 = jSONObject4.getIntValue("has_img") == 1;
                str2 = jSONObject4.getString("txt");
                j7 = jSONObject4.getLongValue("id");
                jSONObject4 = jSONObject4.getJSONObject("author");
                if (jSONObject4 != null) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("id", Long.valueOf(jSONObject4.getLongValue("id")));
                    jSONObject5.put("name", jSONObject4.getString("name"));
                    jSONObject5.put("gender", Integer.valueOf(jSONObject4.getIntValue("gender")));
                    jSONObject5.put("avatar", Long.valueOf(jSONObject4.getLongValue("avatar")));
                    jSONObject5.put("sign", jSONObject4.getString("sign"));
                    z = z3;
                    jSONObject3 = jSONObject5;
                    z2 = z;
                    if (jSONObject3 == null) {
                        jSONObject2 = jSONObject2.getJSONObject("like_member");
                        if (jSONObject2 != null) {
                            jSONObject3 = new JSONObject();
                            jSONObject3.put("id", Long.valueOf(jSONObject2.getLongValue("id")));
                            jSONObject3.put("name", jSONObject2.getString("name"));
                            jSONObject3.put("gender", Integer.valueOf(jSONObject2.getIntValue("gender")));
                            jSONObject3.put("avatar", Long.valueOf(jSONObject2.getLongValue("avatar")));
                            jSONObject3.put("sign", jSONObject2.getString("sign"));
                        }
                    }
                    cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(22).a(j4).a(jSONObject3).b(j3).c(j7).i(j5).c(i2).b(str2).c(z2).d(j6).f(i).a(str).f(j).a());
                }
            }
            z = z3;
            jSONObject3 = null;
            z2 = z;
            if (jSONObject3 == null) {
                jSONObject2 = jSONObject2.getJSONObject("like_member");
                if (jSONObject2 != null) {
                    jSONObject3 = new JSONObject();
                    jSONObject3.put("id", Long.valueOf(jSONObject2.getLongValue("id")));
                    jSONObject3.put("name", jSONObject2.getString("name"));
                    jSONObject3.put("gender", Integer.valueOf(jSONObject2.getIntValue("gender")));
                    jSONObject3.put("avatar", Long.valueOf(jSONObject2.getLongValue("avatar")));
                    jSONObject3.put("sign", jSONObject2.getString("sign"));
                }
            }
            cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(22).a(j4).a(jSONObject3).b(j3).c(j7).i(j5).c(i2).b(str2).c(z2).d(j6).f(i).a(str).f(j).a());
        }
    }

    private static void g(long j, long j2, JSONObject jSONObject) {
        com.izuiyou.a.a.b.c(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            boolean z;
            boolean z2;
            JSONObject jSONObject3 = jSONObject2.getJSONObject("article");
            int i = 0;
            int i2 = 0;
            long j3 = 0;
            String str = "";
            long j4 = 0;
            if (jSONObject3 != null) {
                j3 = jSONObject3.getLongValue("id");
                i2 = jSONObject3.getIntValue("like_inc");
                i = jSONObject3.getIntValue("comment_inc");
                str = jSONObject3.getString("abstract");
                j4 = jSONObject3.getLongValue("cover");
            }
            String str2 = "";
            boolean z3 = false;
            long j5 = 0;
            JSONObject jSONObject4 = jSONObject2.getJSONObject(FFmpegMediaMetadataRetriever.METADATA_KEY_COMMENT);
            if (jSONObject4 != null) {
                z3 = jSONObject4.getIntValue("has_img") == 1;
                str2 = jSONObject4.getString("txt");
                j5 = jSONObject4.getLongValue("id");
                jSONObject4 = jSONObject4.getJSONObject("author");
                if (jSONObject4 != null) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("id", Long.valueOf(jSONObject4.getLongValue("id")));
                    jSONObject5.put("name", jSONObject4.getString("name"));
                    jSONObject5.put("gender", Integer.valueOf(jSONObject4.getIntValue("gender")));
                    jSONObject5.put("avatar", Long.valueOf(jSONObject4.getLongValue("avatar")));
                    jSONObject5.put("sign", jSONObject4.getString("sign"));
                    z = z3;
                    jSONObject3 = jSONObject5;
                    z2 = z;
                    if (jSONObject3 == null) {
                        jSONObject2 = jSONObject2.getJSONObject("like_member");
                        if (jSONObject2 != null) {
                            jSONObject3 = new JSONObject();
                            jSONObject3.put("id", Long.valueOf(jSONObject2.getLongValue("id")));
                            jSONObject3.put("name", jSONObject2.getString("name"));
                            jSONObject3.put("gender", Integer.valueOf(jSONObject2.getIntValue("gender")));
                            jSONObject3.put("avatar", Long.valueOf(jSONObject2.getLongValue("avatar")));
                            jSONObject3.put("sign", jSONObject2.getString("sign"));
                        }
                    }
                    cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(21).a(j3).a(jSONObject3).b(j3).c(j5).c(i2).b(str2).c(z2).d(j4).f(i).a(str).f(j).a());
                }
            }
            z = z3;
            jSONObject3 = null;
            z2 = z;
            if (jSONObject3 == null) {
                jSONObject2 = jSONObject2.getJSONObject("like_member");
                if (jSONObject2 != null) {
                    jSONObject3 = new JSONObject();
                    jSONObject3.put("id", Long.valueOf(jSONObject2.getLongValue("id")));
                    jSONObject3.put("name", jSONObject2.getString("name"));
                    jSONObject3.put("gender", Integer.valueOf(jSONObject2.getIntValue("gender")));
                    jSONObject3.put("avatar", Long.valueOf(jSONObject2.getLongValue("avatar")));
                    jSONObject3.put("sign", jSONObject2.getString("sign"));
                }
            }
            cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(21).a(j3).a(jSONObject3).b(j3).c(j5).c(i2).b(str2).c(z2).d(j4).f(i).a(str).f(j).a());
        }
    }

    private static void h(long j, long j2, JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            boolean z;
            boolean z2;
            JSONObject jSONObject3 = jSONObject2.getJSONObject("theme");
            String str = "";
            int i = 0;
            long j3 = 0;
            if (jSONObject3 != null) {
                str = jSONObject3.getString("title");
                j3 = jSONObject3.getLongValue("id");
                i = jSONObject3.getIntValue("article_inc");
            }
            JSONObject jSONObject4 = jSONObject2.getJSONObject("article");
            long j4 = 0;
            String str2 = "";
            if (jSONObject4 != null) {
                boolean z3 = jSONObject4.getIntValue("has_img") == 1;
                boolean z4 = jSONObject4.getIntValue("has_video") == 1;
                long longValue = jSONObject4.getLongValue("id");
                String string = jSONObject4.getString("abstract");
                jSONObject4 = jSONObject4.getJSONObject("author");
                if (jSONObject4 != null) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("id", Long.valueOf(jSONObject4.getLongValue("id")));
                    jSONObject5.put("name", jSONObject4.getString("name"));
                    jSONObject5.put("gender", Integer.valueOf(jSONObject4.getIntValue("gender")));
                    jSONObject5.put("avatar", Long.valueOf(jSONObject4.getLongValue("avatar")));
                    jSONObject5.put("sign", jSONObject4.getString("sign"));
                    long j5 = longValue;
                    z = z4;
                    z2 = z3;
                    jSONObject3 = jSONObject5;
                    str2 = string;
                    j4 = j5;
                } else {
                    String str3 = string;
                    j4 = longValue;
                    z = z4;
                    z2 = z3;
                    str2 = str3;
                    jSONObject3 = null;
                }
            } else {
                z2 = false;
                z = false;
                jSONObject3 = null;
            }
            cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(20).a(j3).a(jSONObject3).b(j3).b(str2).c(j4).c(0).d(z).c(z2).f(i).a(str).f(j).a());
        }
    }

    private static void i(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        long j3 = 0;
        JSONObject jSONObject3 = jSONObject.getJSONObject("danmaku");
        String str = "";
        if (jSONObject3 != null) {
            j3 = jSONObject3.getLongValue("id");
            str = jSONObject3.getString("text");
        }
        int intValue = jSONObject.getIntValue("likes");
        int intValue2 = jSONObject.getIntValue("reviews");
        int intValue3 = jSONObject.getIntValue("danmakus");
        int intValue4 = jSONObject.getIntValue("shares");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(12).a(longValue).a(jSONObject2).b(longValue).c(intValue).j(j3).f(intValue2).g(intValue3).h(intValue4).b(jSONObject.getIntValue("imgtype")).d(jSONObject.getLongValue("imgid")).a(str).f(j).a());
    }

    public static void a(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        long longValue2 = jSONObject.getLongValue("rid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        long j3 = 0;
        JSONObject jSONObject3 = jSONObject.getJSONObject("danmaku");
        String str = "";
        if (jSONObject3 != null) {
            j3 = jSONObject3.getLongValue("id");
            str = jSONObject3.getString("text");
        }
        int intValue = jSONObject.getIntValue("likes");
        int intValue2 = jSONObject.getIntValue("reviews");
        int intValue3 = jSONObject.getIntValue("danmakus");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(19).a(longValue).a(jSONObject2).e(true).c(longValue2).b(longValue).c(intValue).j(j3).f(intValue2).g(intValue3).h(jSONObject.getIntValue("shares")).b(str).f(j).a());
    }

    public static void b(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        long longValue2 = jSONObject.getLongValue("rid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        long j3 = 0;
        JSONObject jSONObject3 = jSONObject.getJSONObject("danmaku");
        String str = "";
        if (jSONObject3 != null) {
            j3 = jSONObject3.getLongValue("id");
            str = jSONObject3.getString("text");
        }
        int intValue = jSONObject.getIntValue("likes");
        int intValue2 = jSONObject.getIntValue("reviews");
        int intValue3 = jSONObject.getIntValue("danmakus");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(18).a(longValue).a(jSONObject2).e(true).b(longValue).c(longValue2).j(j3).c(intValue).f(intValue2).g(intValue3).h(jSONObject.getIntValue("shares")).b(str).f(j).a());
    }

    public static void c(long j, long j2, JSONObject jSONObject) {
        int i;
        long longValue;
        int i2;
        long j3;
        JSONObject jSONObject2;
        String str;
        int intValue;
        int intValue2;
        int intValue3;
        long longValue2 = jSONObject.getLongValue("pid");
        long longValue3 = jSONObject.getLongValue("rid");
        JSONObject jSONObject3 = jSONObject.getJSONObject("member");
        int i3 = 0;
        JSONObject jSONObject4 = jSONObject.getJSONObject("review");
        if (jSONObject4 != null) {
            i3 = jSONObject4.getIntValue("has_video");
            JSONArray jSONArray = jSONObject4.getJSONArray("imgs");
            if (!(jSONArray == null || jSONArray.isEmpty())) {
                i = i3;
                longValue = jSONArray.getJSONObject(0).getLongValue("id");
                i2 = i;
                if (longValue == 0) {
                    longValue = jSONObject.getLongValue("imgid");
                }
                j3 = 0;
                jSONObject2 = jSONObject.getJSONObject("danmaku");
                str = "";
                if (jSONObject2 != null) {
                    j3 = jSONObject2.getLongValue("id");
                    str = jSONObject2.getString("text");
                }
                intValue = jSONObject.getIntValue("likes");
                intValue2 = jSONObject.getIntValue("reviews");
                intValue3 = jSONObject.getIntValue("danmakus");
                cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(15).a(longValue3).a(jSONObject3).e(true).b(longValue2).c(longValue3).j(j3).c(intValue).d(jSONObject.getIntValue("ugcs")).f(intValue2).g(intValue3).h(jSONObject.getIntValue("shares")).b(str).d(longValue).b(i2).f(j).a());
            }
        }
        i = i3;
        longValue = 0;
        i2 = i;
        if (longValue == 0) {
            longValue = jSONObject.getLongValue("imgid");
        }
        j3 = 0;
        jSONObject2 = jSONObject.getJSONObject("danmaku");
        str = "";
        if (jSONObject2 != null) {
            j3 = jSONObject2.getLongValue("id");
            str = jSONObject2.getString("text");
        }
        intValue = jSONObject.getIntValue("likes");
        intValue2 = jSONObject.getIntValue("reviews");
        intValue3 = jSONObject.getIntValue("danmakus");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(15).a(longValue3).a(jSONObject3).e(true).b(longValue2).c(longValue3).j(j3).c(intValue).d(jSONObject.getIntValue("ugcs")).f(intValue2).g(intValue3).h(jSONObject.getIntValue("shares")).b(str).d(longValue).b(i2).f(j).a());
    }

    public static void d(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        long longValue2 = jSONObject.getLongValue(SpeechConstant.ISV_VID);
        long longValue3 = jSONObject.getLongValue("imgid");
        int intValue = jSONObject.getIntValue("imgtype");
        long j3 = 0;
        JSONObject jSONObject2 = jSONObject.getJSONObject("danmaku");
        String str = "";
        if (jSONObject2 != null) {
            j3 = jSONObject2.getLongValue("id");
            str = jSONObject2.getString("text");
        }
        jSONObject2 = jSONObject.getJSONObject("member");
        int intValue2 = jSONObject.getIntValue("likes");
        int intValue3 = jSONObject.getIntValue("reviews");
        int intValue4 = jSONObject.getIntValue("danmakus");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(14).a(longValue).a(jSONObject2).e(true).b(longValue).h(longValue2).j(j3).c(intValue2).b(str).f(intValue3).g(intValue4).h(jSONObject.getIntValue("shares")).d(longValue3).b(intValue).f(j).a());
    }

    private static void j(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        long longValue2 = jSONObject.getLongValue(SpeechConstant.ISV_VID);
        JSONObject jSONObject2 = jSONObject.getJSONObject("voter");
        int intValue = jSONObject.getIntValue("likes");
        int intValue2 = jSONObject.getIntValue("reviews");
        int intValue3 = jSONObject.getIntValue("danmakus");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(9).a(longValue).a(jSONObject2).b(longValue).h(longValue2).c(intValue).f(intValue2).g(intValue3).h(jSONObject.getIntValue("shares")).e(1).f(j).a());
    }

    private static void k(long j, long j2, JSONObject jSONObject) {
        long longValue = jSONObject.getLongValue("pid");
        long longValue2 = jSONObject.getLongValue("prid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        int intValue = jSONObject.getIntValue("likes");
        int intValue2 = jSONObject.getIntValue("reviews");
        int intValue3 = jSONObject.getIntValue("vote_count");
        int intValue4 = jSONObject.getIntValue("danmakus");
        int intValue5 = jSONObject.getIntValue("shares");
        JSONObject jSONObject3 = jSONObject.getJSONObject("content");
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        long j3 = 0;
        if (jSONObject3 != null) {
            String string = jSONObject3.getString("review");
            boolean z4 = jSONObject3.getIntValue("has_img") == 1;
            boolean z5 = jSONObject3.getIntValue("has_video") == 1;
            z3 = jSONObject3.getIntValue("has_sound") == 1;
            z = z4;
            str = string;
            z2 = z5;
            j3 = jSONObject3.getLong("id").longValue();
        }
        JSONObject jSONObject4 = jSONObject.getJSONObject("review");
        long longValue3 = jSONObject4.getLongValue("id");
        String string2 = jSONObject4.getString("review");
        int i = 0;
        if (jSONObject4.getIntValue("has_sound") == 1) {
            i = 2;
        }
        if (jSONObject4.getIntValue("has_video") == 1) {
            i = 1;
        }
        JSONArray jSONArray = jSONObject4.getJSONArray("imgs");
        long j4 = 0;
        if (!(jSONArray == null || jSONArray.isEmpty())) {
            j4 = jSONArray.getJSONObject(0).getLongValue("id");
        }
        cn.xiaochuankeji.tieba.push.data.c.a b = new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(10).a(longValue3).a(jSONObject2).b(longValue);
        if (j3 <= 0) {
            j3 = longValue3;
        }
        cn.xiaochuankeji.tieba.push.b.b.a(b.c(j3).b(i).d(j4).c(z).d(z2).b(z3).b(str).a(string2).i(longValue2).c(intValue).f(intValue2).e(intValue3).g(intValue4).h(intValue5).e(intValue3).f(j).a());
    }

    public static void e(long j, long j2, JSONObject jSONObject) {
        boolean z;
        boolean z2;
        boolean z3;
        long j3;
        long longValue = jSONObject.getLongValue("id");
        long longValue2 = jSONObject.getLongValue("firstCommentId");
        long longValue3 = jSONObject.getLongValue("tid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        JSONObject jSONObject3 = jSONObject.getJSONObject("review");
        if (jSONObject3 != null) {
            z = jSONObject3.getIntValue("has_sound") == 1;
            z2 = jSONObject3.getIntValue("has_video") == 1;
            z3 = jSONObject3.getIntValue("has_img") == 1;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (longValue2 != 0 || jSONObject3 == null) {
            j3 = longValue2;
        } else {
            j3 = jSONObject3.getLongValue("id");
        }
        long longValue4 = jSONObject.getLongValue("imgid");
        int intValue = jSONObject.getIntValue("imgtype");
        String string = jSONObject.getString("cont");
        if (TextUtils.isEmpty(string)) {
            Object string2 = jSONObject.getString("topic");
            if (!TextUtils.isEmpty(string2)) {
                string = " " + string2;
            }
        }
        longValue2 = 0;
        String str = "";
        if (TextUtils.isEmpty(str)) {
            if (jSONObject3 != null) {
                str = jSONObject3.getString("review");
            }
            if (TextUtils.isEmpty(str)) {
                jSONObject3 = jSONObject.getJSONObject("danmaku");
                if (jSONObject3 != null) {
                    longValue2 = jSONObject3.getLongValue("id");
                    str = jSONObject3.getString("text");
                }
            }
        }
        int intValue2 = jSONObject.getIntValue("likes");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(1).a(longValue).a(jSONObject2).c(j3).b(longValue).g(longValue3).j(longValue2).d(longValue4).b(intValue).c(z3).d(z2).b(z).c(intValue2).f(jSONObject.getIntValue("reviews")).e(jSONObject.getIntValue("vote_count")).g(jSONObject.getIntValue("danmakus")).h(jSONObject.getIntValue("shares")).b(str).a(string).f(j).a());
    }

    private static void l(long j, long j2, JSONObject jSONObject) {
        boolean z;
        boolean z2;
        boolean z3;
        long j3;
        long longValue = jSONObject.getLongValue("id");
        long longValue2 = jSONObject.getLongValue("firstCommentId");
        long longValue3 = jSONObject.getLongValue("tid");
        JSONObject jSONObject2 = jSONObject.getJSONObject("member");
        JSONObject jSONObject3 = jSONObject.getJSONObject("review");
        if (jSONObject3 != null) {
            z = jSONObject3.getIntValue("has_sound") == 1;
            z2 = jSONObject3.getIntValue("has_video") == 1;
            z3 = jSONObject3.getIntValue("has_img") == 1;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (longValue2 != 0 || jSONObject3 == null) {
            j3 = longValue2;
        } else {
            j3 = jSONObject3.getLongValue("id");
        }
        long longValue4 = jSONObject.getLongValue("imgid");
        int intValue = jSONObject.getIntValue("imgtype");
        String string = jSONObject.getString("cont");
        if (TextUtils.isEmpty(string)) {
            Object string2 = jSONObject.getString("topic");
            if (!TextUtils.isEmpty(string2)) {
                string = " " + string2;
            }
        }
        longValue2 = 0;
        String str = "";
        if (TextUtils.isEmpty(str)) {
            if (jSONObject3 != null) {
                str = jSONObject3.getString("review");
            }
            if (TextUtils.isEmpty(str)) {
                jSONObject3 = jSONObject.getJSONObject("danmaku");
                if (jSONObject3 != null) {
                    longValue2 = jSONObject3.getLongValue("id");
                    str = jSONObject3.getString("text");
                }
            }
        }
        int intValue2 = jSONObject.getIntValue("likes");
        cn.xiaochuankeji.tieba.push.b.b.a(new cn.xiaochuankeji.tieba.push.data.c.a().e(j2).a(51).a(longValue).a(jSONObject2).c(j3).b(longValue).g(longValue3).j(longValue2).d(longValue4).b(intValue).c(z3).d(z2).b(z).c(intValue2).f(jSONObject.getIntValue("reviews")).e(jSONObject.getIntValue("vote_count")).g(jSONObject.getIntValue("danmakus")).h(jSONObject.getIntValue("shares")).b(str).a(string).f(j).a());
    }

    private static void a(String str, int i, JSONObject jSONObject) {
        PendingIntent a;
        int i2 = 0;
        if (-1 == i) {
            a = OpenActivityUtils.a(ActivityType.kNone);
        } else if (2 == i) {
            i2 = 1001;
            a = OpenActivityUtils.a(jSONObject.getLongValue("id"), false);
        } else if (5 == i) {
            i2 = 1003;
            a = OpenActivityUtils.c(jSONObject.getLongValue("id"));
        } else if (6 == i) {
            int i3;
            if (cn.xiaochuankeji.tieba.background.a.c().getBoolean("key_comment_notification", true)) {
                r0 = jSONObject.getLongValue("id");
                long longValue = jSONObject.getLongValue("rid");
                long longValue2 = jSONObject.getLongValue("cid");
                long longValue3 = jSONObject.getLongValue("prid");
                if (0 != r0) {
                    if (0 != longValue && 0 != longValue2 && 0 != longValue3) {
                        a = OpenActivityUtils.a(r0, longValue3, longValue, longValue2);
                        i3 = 1006;
                        i2 = i3;
                    } else if (0 == longValue3 && 0 != longValue2 && 0 != longValue) {
                        a = OpenActivityUtils.a(r0, longValue, longValue2);
                        i3 = 1006;
                        i2 = i3;
                    } else if (0 == longValue3 && 0 != longValue2) {
                        a = OpenActivityUtils.a(r0, longValue2, true);
                        i3 = 1004;
                        i2 = i3;
                    } else if (0 == longValue3 && 0 == longValue) {
                        a = OpenActivityUtils.a(r0, true);
                        i3 = 1004;
                        i2 = i3;
                    }
                }
            }
            a = null;
            i3 = 0;
            i2 = i3;
        } else if (16 == i) {
            r0 = jSONObject.getLongValue("rid");
            i2 = Packet.CLIENTVER_FIELD_NUMBER;
            a = OpenActivityUtils.b(r0);
        } else if (50 == i) {
            r0 = jSONObject.getLongValue("id");
            i2 = PointerIconCompat.TYPE_CROSSHAIR;
            a = OpenActivityUtils.a(r0);
        } else {
            a = null;
        }
        if (a != null) {
            a(i2, str, a);
        }
    }

    private static void a(int i, String str, PendingIntent pendingIntent) {
        Context appContext = BaseApplication.getAppContext();
        Notification build = new Builder(appContext, "贴子").setSmallIcon(R.drawable.mipush_small_notification).setLargeIcon(BitmapFactory.decodeResource(appContext.getResources(), R.drawable.mipush_notification)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentTitle(appContext.getString(R.string.app_name)).setContentText(str).setContentIntent(pendingIntent).build();
        build.flags = 17;
        build.ledARGB = -16711936;
        build.ledOnMS = 500;
        build.ledOffMS = 1000;
        if (1004 == i) {
            if (a) {
                build.defaults = 0;
            } else {
                a = true;
            }
        } else if (Packet.CLIENTVER_FIELD_NUMBER == i) {
            if (b) {
                build.defaults = 0;
            } else {
                b = true;
            }
        } else if (1006 == i) {
            if (c) {
                build.defaults = 0;
            } else {
                c = true;
            }
        }
        if (cn.xiaochuankeji.tieba.ui.utils.d.b()) {
            build.defaults |= 2;
            cn.xiaochuankeji.tieba.common.a.a.a(BaseApplication.getAppContext(), i, build);
            return;
        }
        build.defaults = 0;
        cn.xiaochuankeji.tieba.push.e.a().a(i, build);
    }
}
