package cn.xiaochuankeji.tieba.ui.videomaker;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import cn.htjyb.c.a;
import cn.htjyb.c.a.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.common.medialib.f;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.e;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;

public class j {
    private static i a;
    private static i b;

    public static boolean a(boolean z) {
        if (VERSION.SDK_INT < 18) {
            if (!z) {
                return false;
            }
            g.b("安卓手机版本低于4.3，不能拍视频哦→_→");
            return false;
        } else if (!b().a || !a.a()) {
            g.b("您的手机配置暂不支持录制");
            return false;
        } else if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        } else {
            g.b("您的手机没有检测到sd卡，不能启动录制");
            return false;
        }
    }

    public static void a() {
        i q = cn.xiaochuankeji.tieba.background.utils.c.a.c().q();
        if (q != null) {
            a = q;
            return;
        }
        a = null;
        d.a(new d$a<i>() {
            public /* synthetic */ void call(Object obj) {
                a((rx.j) obj);
            }

            public void a(rx.j<? super i> jVar) {
                jVar.onNext(j.h());
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<i>() {
            public /* synthetic */ void onNext(Object obj) {
                a((i) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(i iVar) {
                if (j.a == null) {
                    j.a = iVar;
                }
            }
        });
    }

    private static i h() {
        String toLowerCase = Build.MANUFACTURER.toLowerCase();
        String toLowerCase2 = Build.MODEL.toLowerCase();
        Object a = b.a(BaseApplication.getAppContext().getAssets(), "videomaker_config/" + toLowerCase + ".json", "UTF-8");
        if (TextUtils.isEmpty(a)) {
            return i.b();
        }
        try {
            JSONObject jSONObject;
            JSONObject jSONObject2 = new JSONObject(a);
            JSONObject optJSONObject = jSONObject2.optJSONObject(toLowerCase);
            JSONObject optJSONObject2 = jSONObject2.optJSONObject(toLowerCase2);
            if (optJSONObject == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = optJSONObject;
            }
            if (optJSONObject2 != null) {
                Iterator keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    toLowerCase = (String) keys.next();
                    jSONObject.put(toLowerCase, optJSONObject2.get(toLowerCase));
                }
            }
            return new i(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return i.b();
        }
    }

    public static i b() {
        if (b == null) {
            b = cn.xiaochuankeji.tieba.background.utils.c.a.c().q();
        }
        if (b != null) {
            return b;
        }
        if (a == null) {
            a = h();
        }
        return a;
    }

    static File c() {
        File file = new File(cn.xiaochuankeji.tieba.background.a.e().u(), "workspace");
        file.mkdirs();
        return file;
    }

    public static void d() {
        File[] listFiles = c().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public static void a(h hVar) {
        new cn.xiaochuankeji.tieba.ui.videomaker.a.b().c(hVar.f, hVar.g);
    }

    public static void b(h hVar) {
        if (hVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                hVar.a(jSONObject);
                File file = new File(c(), "workspace.json");
                if (file.exists()) {
                    file.delete();
                }
                b.a(jSONObject, file, org.apache.commons.io.a.f.name());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static h e() {
        File file = new File(c(), "workspace.json");
        if (file.exists()) {
            JSONObject a = b.a(file, org.apache.commons.io.a.f.name());
            if (a != null) {
                try {
                    return new h(a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static File c(h hVar) {
        return a(hVar.f, hVar.g);
    }

    public static File a(long j, long j2) {
        if (j > 0) {
            return new File(cn.xiaochuankeji.tieba.ui.videomaker.a.b.a(j, j2));
        }
        return c();
    }

    @WorkerThread
    public static h a(ArrayList<k> arrayList, e eVar, long j, long j2, UgcVideoMusicJson ugcVideoMusicJson, Topic topic) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        e eVar2;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            k kVar = (k) it.next();
            arrayList2.add(kVar.a);
            if (!TextUtils.isEmpty(kVar.b)) {
                arrayList3.add(kVar.b);
            }
        }
        File a = a(j, j2);
        File[] listFiles = a.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith("output_");
            }
        });
        if (listFiles != null && listFiles.length > 0) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        File file = new File(a, "output_" + System.currentTimeMillis() + ".mp4");
        if (arrayList2.size() > 1) {
            FFmpegMainCaller.concatMedia(arrayList2, file.getAbsolutePath(), new File(a, "muxer_video_list.txt").getAbsolutePath());
        } else {
            b.a(new File((String) arrayList2.get(0)), file);
        }
        File file2 = null;
        if (!arrayList3.isEmpty()) {
            File file3 = new File(a, "output_" + System.currentTimeMillis() + ".wav");
            if (arrayList3.size() > 1) {
                FFmpegMainCaller.concatMedia(arrayList3, file3.getAbsolutePath(), new File(a, "muxer_audio_list.txt").getAbsolutePath());
                file2 = file3;
            } else {
                b.a(new File((String) arrayList3.get(0)), file3);
                file2 = file3;
            }
        }
        File file4 = new File(a, "cover.jpg");
        f.a(file.getAbsolutePath(), file4.getAbsolutePath());
        h.a a2 = new h.a().a(file.getAbsolutePath());
        if (file2 == null) {
            eVar2 = null;
        } else {
            eVar2 = new e(file2.getAbsolutePath());
        }
        return a2.a(eVar2).b(eVar).b(file4.getAbsolutePath()).a((ArrayList) arrayList).a(j).b(j2).a(ugcVideoMusicJson).a(topic).a();
    }

    public static void a(final Activity activity) {
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, h>() {
                public /* synthetic */ Object call(Object obj) {
                    return a((Boolean) obj);
                }

                public h a(Boolean bool) {
                    return j.e();
                }
            }).a(new rx.b.g<h, Boolean>() {
                public /* synthetic */ Object call(Object obj) {
                    return a((h) obj);
                }

                public Boolean a(h hVar) {
                    return Boolean.valueOf(hVar != null);
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<h>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((h) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(final h hVar) {
                    final com.flyco.dialog.c.a aVar = new com.flyco.dialog.c.a(activity);
                    ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) ((com.flyco.dialog.c.a) aVar.b("你有未编辑完成的跟拍，是否继续？")).b(false)).a(1).c(3)).a(new String[]{"存草稿箱", "放弃", "继续拍摄"})).show();
                    aVar.setCancelable(false);
                    aVar.a(new com.flyco.dialog.a.a[]{new com.flyco.dialog.a.a(this) {
                        final /* synthetic */ AnonymousClass4 c;

                        public void a() {
                            cn.xiaochuankeji.tieba.ui.widget.g.a(activity, "正在保存中");
                            d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, h>(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ Object call(Object obj) {
                                    return a((Boolean) obj);
                                }

                                public h a(Boolean bool) {
                                    return j.a(hVar.e, hVar.c, hVar.f, hVar.g, hVar.h, hVar.i);
                                }
                            }).d(new rx.b.g<h, h>(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ Object call(Object obj) {
                                    return a((h) obj);
                                }

                                public h a(h hVar) {
                                    try {
                                        new cn.xiaochuankeji.tieba.ui.videomaker.a.b().a(hVar, new JSONObject());
                                        j.d();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    return hVar;
                                }
                            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<h>(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ void onNext(Object obj) {
                                    a((h) obj);
                                }

                                public void onCompleted() {
                                }

                                public void onError(Throwable th) {
                                    cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                                }

                                public void a(h hVar) {
                                    cn.xiaochuankeji.tieba.ui.widget.g.c(activity);
                                    aVar.dismiss();
                                }
                            });
                        }
                    }, new com.flyco.dialog.a.a(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public void a() {
                            j.d();
                            aVar.dismiss();
                        }
                    }, new com.flyco.dialog.a.a(this) {
                        final /* synthetic */ AnonymousClass4 c;

                        public void a() {
                            aVar.dismiss();
                            VideoRecordActivity.a(activity, hVar);
                        }
                    }});
                }
            });
        }
    }
}
