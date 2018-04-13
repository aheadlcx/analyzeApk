package cn.xiaochuankeji.tieba.ui.voice.model;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.json.voice.VoiceListJson;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class VoiceModel extends o {
    private cn.xiaochuankeji.tieba.ui.voice.a a;
    private cn.xiaochuankeji.tieba.api.voice.a b = new cn.xiaochuankeji.tieba.api.voice.a();
    private cn.xiaochuankeji.tieba.ui.recommend.b c;
    private a d;

    public interface a {
        void c();

        void d();
    }

    public static class b {
        public String a;
        public boolean b;

        public b(String str, boolean z) {
            this.a = str;
            this.b = z;
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.recommend.b bVar, a aVar) {
        this.c = bVar;
        this.d = aVar;
    }

    public void a(cn.xiaochuankeji.tieba.ui.voice.a aVar) {
        this.a = aVar;
    }

    public void b() {
        d.b(new d$a<VoiceListJson>(this) {
            final /* synthetic */ VoiceModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super VoiceListJson> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.a.d()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    if (this.a.d != null) {
                        this.a.d.d();
                    }
                    this.a.a("down", true);
                    return;
                }
                VoiceListJson voiceListJson = (VoiceListJson) JSON.parseObject(a.toString(), VoiceListJson.class);
                if (voiceListJson == null || voiceListJson.list == null) {
                    if (this.a.d != null) {
                        this.a.d.d();
                    }
                    this.a.a("down", true);
                    return;
                }
                jVar.onNext(voiceListJson);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<VoiceListJson>(this) {
            final /* synthetic */ VoiceModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VoiceListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(VoiceListJson voiceListJson) {
                this.a.a.a(voiceListJson.list);
                if (this.a.d != null) {
                    this.a.d.c();
                }
            }
        });
    }

    private void a(final VoiceListJson voiceListJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ VoiceModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.b.d()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                VoiceListJson voiceListJson = (VoiceListJson) JSON.parseObject(a.toString(), VoiceListJson.class);
                if (voiceListJson == null) {
                    voiceListJson = new VoiceListJson();
                }
                if (voiceListJson.list == null) {
                    voiceListJson.list = new ArrayList();
                }
                voiceListJson.list.addAll(voiceListJson.list);
                if (voiceListJson.list.size() > 40) {
                    voiceListJson.list = voiceListJson.list.subList(voiceListJson.list.size() - 40, voiceListJson.list.size());
                }
                try {
                    cn.htjyb.c.a.b.a(new JSONObject(JSON.toJSONString(voiceListJson)), new File(this.b.d()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    private void b(final VoiceListJson voiceListJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ VoiceModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.b.d()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                VoiceListJson voiceListJson = (VoiceListJson) JSON.parseObject(a.toString(), VoiceListJson.class);
                if (voiceListJson == null) {
                    voiceListJson = new VoiceListJson();
                }
                if (voiceListJson.list == null) {
                    voiceListJson.list = new ArrayList();
                }
                voiceListJson.list.clear();
                voiceListJson.list.addAll(voiceListJson.list);
                try {
                    cn.htjyb.c.a.b.a(new JSONObject(JSON.toJSONString(voiceListJson)), new File(this.b.d()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    public void a(String str, boolean z) {
        this.b.a(str, z).a(rx.a.b.a.a()).b(new j<VoiceListJson>(this) {
            final /* synthetic */ VoiceModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VoiceListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.c != null) {
                    this.a.c.a(false, "网络不给力哦~", 0, true);
                }
            }

            public void a(VoiceListJson voiceListJson) {
                if (voiceListJson != null && voiceListJson.list != null) {
                    if (voiceListJson.list.size() != 0) {
                        this.a.a.a(voiceListJson.list);
                        this.a.b(voiceListJson);
                    }
                    if (this.a.c != null) {
                        this.a.c.a(true, "", voiceListJson.list.size(), voiceListJson.list.size() != 0);
                    }
                }
            }
        });
    }

    public void c() {
        this.b.a("up", false).a(rx.a.b.a.a()).b(new j<VoiceListJson>(this) {
            final /* synthetic */ VoiceModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VoiceListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.c != null) {
                    this.a.c.a(false, "网络不给力哦~", true);
                }
            }

            public void a(VoiceListJson voiceListJson) {
                if (voiceListJson != null && voiceListJson.list != null) {
                    this.a.a.b(voiceListJson.list);
                    if (this.a.c != null) {
                        this.a.c.a(true, "", voiceListJson.list.size() != 0);
                    }
                    this.a.a(voiceListJson);
                }
            }
        });
    }

    private String d() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "post_recommend_list_voice.dat";
    }
}
