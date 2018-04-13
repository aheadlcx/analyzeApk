package cn.xiaochuankeji.tieba.push;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.hollow.HollowService;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.upload.f;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.push.api.ChatSyncService;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.c.h;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.push.data.b;
import cn.xiaochuankeji.tieba.push.service.DaemonService;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechEvent;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import java.io.File;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import rx.b.g;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;

public class d {
    private static long a = -1;

    private static class a {
        private static final d a = new d();
    }

    public static d a() {
        return a.a;
    }

    public void b() {
        DaemonService.b();
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            c.a();
            a(1);
        }
    }

    private static JSONObject a(long j, long j2, int i) {
        long j3 = 0;
        JSONObject jSONObject = new JSONObject();
        String str = "msgid_begin";
        if (j != 0) {
            j3 = 1 + j;
        }
        jSONObject.put(str, Long.valueOf(j3));
        jSONObject.put("mask", Integer.valueOf(i));
        jSONObject.put("limit", Long.valueOf(j2));
        return jSONObject;
    }

    public static JSONObject a(long j, long j2, long j3, long j4, int i, long j5) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("fromuser", Long.valueOf(j));
        jSONObject.put("session_type", Integer.valueOf(i));
        jSONObject.put("id_begin", Long.valueOf(j2 == 0 ? 0 : 1 + j2));
        jSONObject.put("id_end", Long.valueOf(j3));
        if (j5 > 0) {
            jSONObject.put("me", Long.valueOf(j5));
        }
        if (j4 > 0) {
            jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(j4));
        }
        jSONObject.put("limit", Integer.valueOf(100));
        return jSONObject;
    }

    private static JSONObject a(long j, long j2, int i, long j3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(j2));
        jSONObject.put("session_type", Integer.valueOf(i));
        jSONObject.put("fromuser", Long.valueOf(j));
        jSONObject.put("msgid", Long.valueOf(j3));
        return jSONObject;
    }

    public void a(int i) {
        a(i, false);
    }

    private void a(final int i, boolean z) {
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            if (!z) {
                a = -1;
            }
            long c = e.c(i);
            if (!z || a != c) {
                a = c;
                ((ChatSyncService) c.b(ChatSyncService.class)).session(a(c, 100, i == 1 ? 5 : 2)).d(new g<JSONObject, Boolean>(this) {
                    final /* synthetic */ d b;

                    public /* synthetic */ Object call(Object obj) {
                        return a((JSONObject) obj);
                    }

                    public Boolean a(JSONObject jSONObject) {
                        JSONArray jSONArray = jSONObject.getJSONArray(com.umeng.analytics.b.g.U);
                        boolean z = e.a(i, jSONArray) && !jSONArray.isEmpty();
                        return Boolean.valueOf(z);
                    }
                }).d().a(new rx.e<Boolean>(this) {
                    final /* synthetic */ d b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((Boolean) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        th.printStackTrace();
                    }

                    public void a(Boolean bool) {
                        if (bool.booleanValue()) {
                            cn.xiaochuankeji.tieba.background.h.d.a().d();
                            org.greenrobot.eventbus.c.a().d(new h());
                            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.push.c.g());
                            this.b.a(i, true);
                        }
                    }
                });
            }
        }
    }

    public void a(XSession xSession) {
        a(xSession, e.e(xSession), Long.MAX_VALUE);
    }

    private void a(XSession xSession, long j, long j2) {
        long j3 = xSession.x_sid;
        long j4 = xSession.session_id;
        int i = xSession.session_type;
        long j5 = i == 2 ? xSession.x_mask.id : -1;
        final XSession xSession2 = xSession;
        j4 = j;
        final long j6 = j2;
        final long j7 = j2;
        final XSession xSession3 = xSession;
        j6 = j3;
        final long j8 = j;
        ((ChatSyncService) c.b(ChatSyncService.class)).message(a(j3, j, j2, j4, i, j5)).d(new g<JSONObject, b>(this) {
            final /* synthetic */ d d;

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public b a(JSONObject jSONObject) {
                JSONArray jSONArray = jSONObject.getJSONArray(MNSConstants.LOCATION_MESSAGES);
                String string = jSONObject.getString("state");
                b bVar = new b();
                if (jSONArray == null) {
                    bVar.c = 0;
                    bVar.a = false;
                    bVar.b = false;
                } else {
                    bVar.c = jSONArray.size();
                    bVar.b = e.a(xSession2, j4, j6, jSONArray, "CONTINUE".equalsIgnoreCase(string));
                    bVar.a = "CONTINUE".equalsIgnoreCase(string);
                }
                return bVar;
            }
        }).a(new rx.e<b>(this) {
            final /* synthetic */ d e;

            public /* synthetic */ void onNext(Object obj) {
                a((b) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(b bVar) {
                if (bVar.b && bVar.c > 0) {
                    if (j7 == Long.MAX_VALUE) {
                        this.e.c(xSession3);
                    }
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.push.c.b(xSession3.x_mask.id, j6, j8, j7));
                }
            }
        });
    }

    public void a(XSession xSession, cn.xiaochuankeji.tieba.push.data.a aVar, cn.xiaochuankeji.tieba.push.a.c cVar) {
        final long j = aVar.j;
        if (xSession.session_type == 2) {
            e.k(xSession);
        }
        xSession.status = 0;
        e.g(xSession);
        if (!NetworkMonitor.a()) {
            aVar.h = 2;
            cn.xiaochuankeji.tieba.background.utils.g.a("网络不给力哦~");
            e.a(xSession, aVar, j);
            if (cVar != null) {
                cVar.b(j, aVar);
            }
        } else if (aVar.g == 2) {
            final JSONObject parseObject = JSON.parseObject(aVar.f);
            long longValue = parseObject.getLongValue("id");
            int intValue = parseObject.getIntValue("w");
            int intValue2 = parseObject.getIntValue("h");
            Object string = parseObject.getString(AIUIConstant.RES_TYPE_PATH);
            if (longValue != 0 || TextUtils.isEmpty(string)) {
                a(xSession, aVar, j, cVar);
                return;
            }
            j jVar = new j();
            LocalMedia localMedia = new LocalMedia();
            localMedia.width = intValue;
            localMedia.height = intValue2;
            localMedia.path = string;
            r4 = aVar;
            r5 = xSession;
            r8 = cVar;
            jVar.a(Arrays.asList(new LocalMedia[]{localMedia}), "chat", null, new f(this) {
                final /* synthetic */ d f;

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    parseObject.put("id", list2.get(0));
                    if (parseObject.containsKey(AIUIConstant.RES_TYPE_PATH)) {
                        parseObject.remove(AIUIConstant.RES_TYPE_PATH);
                    }
                    r4.f = parseObject.toJSONString();
                    e.a(r5, r4, j);
                    this.f.a(r5, r4, j, r8);
                }

                public void a(String str) {
                    r4.h = 2;
                    cn.xiaochuankeji.tieba.background.utils.g.a(str);
                    e.a(r5, r4, j);
                    if (r8 != null) {
                        r8.b(j, r4);
                    }
                }
            });
        } else if (aVar.g == 3) {
            r4 = aVar;
            r5 = xSession;
            r4 = aVar;
            r5 = xSession;
            r8 = cVar;
            rx.d.a((Object) aVar).d(new g<cn.xiaochuankeji.tieba.push.data.a, JSONObject>(this) {
                final /* synthetic */ d d;

                public /* synthetic */ Object call(Object obj) {
                    return a((cn.xiaochuankeji.tieba.push.data.a) obj);
                }

                public JSONObject a(cn.xiaochuankeji.tieba.push.data.a aVar) {
                    JSONObject parseObject = JSON.parseObject(r4.f);
                    String string = parseObject.getString("fmt");
                    String string2 = parseObject.getString("name");
                    if (TextUtils.isEmpty(string2)) {
                        return parseObject;
                    }
                    int lastIndexOf = string2.lastIndexOf(".");
                    if (lastIndexOf > 0) {
                        string2 = string2.substring(0, lastIndexOf);
                    }
                    File file = new File(cn.xiaochuankeji.tieba.background.a.e().x(), string2 + ".aac");
                    if ("wav".equalsIgnoreCase(string)) {
                        if (file.exists()) {
                            file.delete();
                        }
                        string2 = parseObject.getString(AIUIConstant.RES_TYPE_PATH);
                        if (new File(string2).exists()) {
                            FFmpegMainCaller.wavToAac(string2, file.getAbsolutePath());
                            if (file.exists()) {
                                parseObject.put("fmt", "aac");
                                parseObject.put(AIUIConstant.RES_TYPE_PATH, file.getAbsolutePath());
                                File file2 = new File(string2);
                                if (file2.exists()) {
                                    file2.delete();
                                }
                                r4.f = parseObject.toJSONString();
                                e.a(r5, r4, j);
                            } else {
                                parseObject.put("fmt", "wav");
                                parseObject.put(AIUIConstant.RES_TYPE_PATH, string2);
                            }
                        } else {
                            com.izuiyou.a.a.b.e("文件不存在");
                            return null;
                        }
                    }
                    return parseObject;
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<JSONObject>(this) {
                final /* synthetic */ d e;

                public /* synthetic */ void onNext(Object obj) {
                    a((JSONObject) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                    r4.h = 2;
                    e.a(r5, r4, j);
                    if (r8 != null) {
                        r8.b(j, r4);
                    }
                }

                public void a(final JSONObject jSONObject) {
                    if (jSONObject == null) {
                        if (r8 != null) {
                            r8.b(j, r4);
                        }
                    } else if (TextUtils.isEmpty(jSONObject.getString("uri"))) {
                        new cn.xiaochuankeji.tieba.background.utils.f(jSONObject.getString(AIUIConstant.RES_TYPE_PATH), jSONObject.getString("fmt"), new cn.xiaochuankeji.tieba.background.utils.f.a(this) {
                            final /* synthetic */ AnonymousClass13 b;

                            public void a(boolean z, String str, String str2) {
                                if (z) {
                                    jSONObject.put("uri", str);
                                    if (jSONObject.containsKey("name")) {
                                        jSONObject.remove("name");
                                    }
                                    r4.f = jSONObject.toJSONString();
                                    e.a(r5, r4, j);
                                    this.b.e.a(r5, r4, j, r8);
                                    return;
                                }
                                cn.xiaochuankeji.tieba.background.utils.g.a(str2);
                                r4.h = 2;
                                e.a(r5, r4, j);
                                if (r8 != null) {
                                    r8.b(j, r4);
                                }
                            }
                        }).a();
                    } else {
                        this.e.a(r5, r4, j, r8);
                    }
                }
            });
        } else {
            a(xSession, aVar, j, cVar);
        }
    }

    private void a(XSession xSession, cn.xiaochuankeji.tieba.push.data.a aVar, long j, cn.xiaochuankeji.tieba.push.a.c cVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "chat");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("session_type", Integer.valueOf(xSession.session_type));
        jSONObject2.put("localid", Long.valueOf(j));
        if (aVar.g == 3) {
            JSONObject parseObject = JSON.parseObject(aVar.f);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("fmt", parseObject.getString("fmt"));
            jSONObject3.put("uri", parseObject.getString("uri"));
            jSONObject3.put("duration", Long.valueOf(parseObject.getLongValue("duration")));
            jSONObject2.put("content", jSONObject3.toJSONString());
        } else {
            jSONObject2.put("content", aVar.f);
        }
        jSONObject2.put("fromuser", Long.valueOf(xSession.x_mask.id));
        jSONObject2.put("touser", Long.valueOf(xSession.x_sid));
        jSONObject2.put("mtype", Integer.valueOf(aVar.g));
        if (xSession.session_id > 0) {
            jSONObject2.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(xSession.session_id));
        }
        jSONObject.put("data", jSONObject2);
        final cn.xiaochuankeji.tieba.push.data.a aVar2 = aVar;
        final XSession xSession2 = xSession;
        final long j2 = j;
        final cn.xiaochuankeji.tieba.push.a.c cVar2 = cVar;
        ((ChatSyncService) c.b(ChatSyncService.class)).send(jSONObject).a(rx.a.b.a.a()).a(new rx.e<JSONObject>(this) {
            final /* synthetic */ d e;

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                if (th instanceof ConnectException) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("网络不给力哦~");
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                }
                aVar2.h = 2;
                e.a(xSession2, aVar2, j2);
                if (cVar2 != null) {
                    cVar2.b(j2, aVar2);
                }
            }

            public void a(JSONObject jSONObject) {
                long longValue = jSONObject.getLongValue("localid");
                long longValue2 = jSONObject.getLongValue(SpeechEvent.KEY_EVENT_SESSION_ID);
                long longValue3 = jSONObject.getLongValue("msgid");
                long longValue4 = jSONObject.getLongValue(Statics.TIME);
                aVar2.j = longValue3;
                aVar2.h = 0;
                aVar2.k = longValue4;
                e.a(xSession2, aVar2, longValue);
                xSession2.session_id = longValue2;
                xSession2.x_last_msg_id = longValue3;
                xSession2.time = longValue4;
                xSession2.unread = 0;
                e.g(xSession2);
                org.greenrobot.eventbus.c.a().d(new h());
                cn.xiaochuankeji.tieba.background.h.d.a().d();
                if (cVar2 != null) {
                    cVar2.a(longValue, aVar2);
                }
            }
        });
    }

    public List<cn.xiaochuankeji.tieba.push.data.a> b(XSession xSession) {
        return e.c(xSession);
    }

    public void c(XSession xSession) {
        e.a().b(xSession);
        e.j(xSession);
        if (xSession.session_id >= 1) {
            long f = e.f(xSession);
            if (f != 0) {
                ((ChatSyncService) c.b(ChatSyncService.class)).read(a(xSession.x_mask.id, xSession.session_id, xSession.session_type, f)).a(new rx.e<EmptyJson>(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((EmptyJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        th.printStackTrace();
                    }

                    public void a(EmptyJson emptyJson) {
                    }
                });
            }
        }
    }

    public static void d(XSession xSession) {
        c(xSession, true);
    }

    private static void c(final XSession xSession, final boolean z) {
        if (xSession.session_id >= 0 && xSession.x_last_msg_id >= 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(xSession.session_id));
            jSONObject.put("session_type", Integer.valueOf(xSession.session_type));
            jSONObject.put("msgid", Long.valueOf(xSession.x_last_msg_id));
            ((ChatSyncService) c.b(ChatSyncService.class)).cleanMessages(jSONObject).a(new rx.e<EmptyJson>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                    if (z) {
                        d.c(xSession, false);
                    }
                }

                public void a(EmptyJson emptyJson) {
                }
            });
        }
    }

    public static void e(XSession xSession) {
        d(xSession, true);
    }

    private static void d(final XSession xSession, final boolean z) {
        if (xSession.session_id >= 0 && xSession.x_last_msg_id >= 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(xSession.session_id));
            jSONObject.put("session_type", Integer.valueOf(xSession.session_type));
            jSONObject.put("msgid", Long.valueOf(xSession.x_last_msg_id));
            ((ChatSyncService) c.b(ChatSyncService.class)).deleteSession(jSONObject).a(new rx.e<EmptyJson>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                    if (z) {
                        d.d(xSession, false);
                    }
                }

                public void a(EmptyJson emptyJson) {
                }
            });
        }
    }

    public static void a(cn.xiaochuankeji.tieba.push.data.a aVar, XSession xSession) {
        b(aVar, xSession, true);
    }

    private static void b(final cn.xiaochuankeji.tieba.push.data.a aVar, final XSession xSession, final boolean z) {
        if (xSession.session_id >= 0 && aVar.j >= 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SpeechEvent.KEY_EVENT_SESSION_ID, Long.valueOf(xSession.session_id));
            jSONObject.put("session_type", Integer.valueOf(xSession.session_type));
            jSONObject.put("msgid", Long.valueOf(aVar.j));
            ((ChatSyncService) c.b(ChatSyncService.class)).deleteMessage(jSONObject).a(new rx.e<EmptyJson>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                    if (z) {
                        d.b(aVar, xSession, false);
                    }
                }

                public void a(EmptyJson emptyJson) {
                }
            });
        }
    }

    public static int a(boolean z, int i) {
        switch (i) {
            case 1:
                return z ? R.layout.view_item_chat_self_txt : R.layout.view_item_chat_txt;
            case 2:
                return z ? R.layout.view_item_chat_self_image : R.layout.view_item_chat_image;
            case 3:
                return z ? R.layout.view_item_chat_self_voice : R.layout.view_item_chat_voice;
            case 5:
                return R.layout.view_item_chat_topic;
            case 6:
            case 8:
            case 9:
                return R.layout.view_item_chat_post;
            case 7:
            case 12:
                return R.layout.view_item_chat_link;
            case 10:
                return R.layout.view_item_chat_ugc;
            case 11:
                return R.layout.view_item_chat_user;
            case 13:
                return R.layout.view_item_chat_tale;
            default:
                return R.layout.view_item_chat_unsup;
        }
    }

    @Nullable
    public static cn.xiaochuankeji.tieba.push.data.a a(long j, long j2) {
        if (Math.abs(j2 - j) <= 300) {
            return null;
        }
        cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
        aVar.k = j2 - 300;
        aVar.i = R.layout.view_item_chat_timeline;
        aVar.c = -1;
        return aVar;
    }

    public void c() {
        final long c = cn.xiaochuankeji.tieba.background.a.g().c();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", Integer.valueOf(1));
        ((UserService) c.b(UserService.class)).getBlockedUsers(jSONObject).d(new g<JSONObject, Boolean>(this) {
            final /* synthetic */ d b;

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public Boolean a(JSONObject jSONObject) {
                return Boolean.valueOf(cn.xiaochuankeji.tieba.push.b.d.a(c, jSONObject.getJSONArray("block_ids")));
            }
        }).a(new rx.e<Boolean>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(Boolean bool) {
            }
        });
        ((HollowService) c.b(HollowService.class)).getBlock().d(new g<JSONObject, Boolean>(this) {
            final /* synthetic */ d b;

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public Boolean a(JSONObject jSONObject) {
                return Boolean.valueOf(cn.xiaochuankeji.tieba.push.b.d.a(c, jSONObject.getJSONArray("blocked")));
            }
        }).a(new rx.e<Boolean>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(Boolean bool) {
            }
        });
    }
}
