package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.content.SharedPreferences.Editor;
import cn.xiaochuankeji.tieba.json.UgcVideoDotJson;
import org.greenrobot.eventbus.c;
import rx.j;

public class UgcCrumbManger {
    private static UgcCrumbManger a;
    private long b;

    public enum CrumbType {
        UGCTAB,
        ATTRI,
        RECOMM
    }

    public static class a {
        public boolean a;
        public boolean b;
    }

    private UgcCrumbManger() {
    }

    public static UgcCrumbManger a() {
        if (a == null) {
            a = new UgcCrumbManger();
        }
        return a;
    }

    public void b() {
        long j = cn.xiaochuankeji.tieba.background.a.a().getLong("key_ugc_attri_last_id", 0);
        if (System.currentTimeMillis() - this.b > 1800000) {
            this.b = System.currentTimeMillis();
            new cn.xiaochuankeji.tieba.api.ugcvideo.a().i(j).a(rx.a.b.a.a()).b(new j<UgcVideoDotJson>(this) {
                final /* synthetic */ UgcCrumbManger a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoDotJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(UgcVideoDotJson ugcVideoDotJson) {
                    boolean z;
                    boolean z2 = false;
                    a aVar = new a();
                    aVar.a = ugcVideoDotJson.attDot >= 1;
                    if (ugcVideoDotJson.recommDot >= 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.b = z;
                    c.a().d(aVar);
                    this.a.a(CrumbType.ATTRI, aVar.a);
                    this.a.a(CrumbType.RECOMM, aVar.b);
                    UgcCrumbManger ugcCrumbManger = this.a;
                    CrumbType crumbType = CrumbType.UGCTAB;
                    if (aVar.a || aVar.b) {
                        z2 = true;
                    }
                    ugcCrumbManger.a(crumbType, z2);
                }
            });
        }
    }

    public void a(CrumbType crumbType, boolean z) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        String str = null;
        if (crumbType == CrumbType.UGCTAB) {
            str = "s_key_ugctab_crumb";
        } else if (crumbType == CrumbType.ATTRI) {
            str = "s_key_ugctab_attri_crumb";
        } else if (crumbType == CrumbType.RECOMM) {
            str = "s_key_ugctab_recomm_crumb";
        }
        edit.putBoolean(str, z).apply();
    }

    public boolean a(CrumbType crumbType) {
        String str = null;
        if (crumbType == CrumbType.UGCTAB) {
            str = "s_key_ugctab_crumb";
        } else if (crumbType == CrumbType.ATTRI) {
            str = "s_key_ugctab_attri_crumb";
        } else if (crumbType == CrumbType.RECOMM) {
            str = "s_key_ugctab_recomm_crumb";
        }
        return cn.xiaochuankeji.tieba.background.a.a().getBoolean(str, false);
    }

    public void a(long j) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putLong("key_ugc_attri_last_id", j);
        edit.apply();
    }
}
