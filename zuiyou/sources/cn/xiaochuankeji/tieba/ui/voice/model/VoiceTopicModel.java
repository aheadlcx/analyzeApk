package cn.xiaochuankeji.tieba.ui.voice.model;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.voice.a;
import cn.xiaochuankeji.tieba.json.voice.VoiceTopicJson;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.voice.adapter.VoiceTopicAdapter;
import rx.j;

public class VoiceTopicModel extends o {
    a a = new a();
    private VoiceTopicAdapter b;
    private b c;
    private int d;

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(VoiceTopicAdapter voiceTopicAdapter) {
        this.b = voiceTopicAdapter;
    }

    public void b() {
        this.a.a(this.d).a(rx.a.b.a.a()).b(new j<VoiceTopicJson>(this) {
            final /* synthetic */ VoiceTopicModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VoiceTopicJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", 0, true);
            }

            public void a(VoiceTopicJson voiceTopicJson) {
                this.a.d = voiceTopicJson.offset;
                if (this.a.c != null) {
                    boolean z;
                    b a = this.a.c;
                    String str = "";
                    if (this.a.d == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    a.a(true, str, 0, z);
                }
                this.a.b.b(voiceTopicJson.list);
            }
        });
    }

    public void c() {
        this.a.a(this.d).a(rx.a.b.a.a()).b(new j<VoiceTopicJson>(this) {
            final /* synthetic */ VoiceTopicModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VoiceTopicJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c.a(false, "网络不给力哦~", 0, true);
            }

            public void a(VoiceTopicJson voiceTopicJson) {
                this.a.d = voiceTopicJson.offset;
                if (this.a.c != null) {
                    boolean z;
                    b a = this.a.c;
                    String str = "";
                    if (this.a.d == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    a.a(true, str, 0, z);
                }
                this.a.b.a(voiceTopicJson.list);
            }
        });
    }
}
