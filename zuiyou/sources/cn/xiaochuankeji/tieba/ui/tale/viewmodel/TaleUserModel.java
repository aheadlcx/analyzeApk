package cn.xiaochuankeji.tieba.ui.tale.viewmodel;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.tale.TaleThumbUserJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.tale.TaleThumbAdapter;
import com.alibaba.fastjson.JSONObject;
import rx.a.b.a;
import rx.d;
import rx.e;

public class TaleUserModel extends o {
    private TaleThumbAdapter a;
    private a b;
    private String c;
    private String d;
    private long e;
    private boolean f;
    private boolean g = true;

    public void a(TaleThumbAdapter taleThumbAdapter, String str, long j, boolean z) {
        this.a = taleThumbAdapter;
        this.d = str;
        this.e = j;
        this.f = z;
    }

    public void a(int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", this.d);
        jSONObject.put("id", Long.valueOf(this.e));
        jSONObject.put("value", Integer.valueOf(this.f ? 1 : -1));
        this.c = null;
        a(true, i, jSONObject);
    }

    public void b(int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", this.d);
        jSONObject.put("id", Long.valueOf(this.e));
        jSONObject.put("cursor", this.c);
        jSONObject.put("value", Integer.valueOf(this.f ? 1 : -1));
        a(false, i, jSONObject);
    }

    private void a(final boolean z, int i, JSONObject jSONObject) {
        d taleCommentThumbUsers;
        if (i == 2) {
            taleCommentThumbUsers = ((TaleService) c.b(TaleService.class)).taleCommentThumbUsers(jSONObject);
        } else {
            taleCommentThumbUsers = ((TaleService) c.b(TaleService.class)).taleThumbUsers(jSONObject);
        }
        taleCommentThumbUsers.a(a.a()).a(new e<TaleThumbUserJson>(this) {
            final /* synthetic */ TaleUserModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((TaleThumbUserJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
                if (z) {
                    if (this.b.b != null) {
                        this.b.b.a(false, th.getMessage(), 0, false);
                    }
                } else if (this.b.b != null) {
                    this.b.b.a(false, this.b.g, th.getMessage());
                }
            }

            public void a(TaleThumbUserJson taleThumbUserJson) {
                if (this.b.a != null) {
                    this.b.c = taleThumbUserJson.cursor;
                    if (z) {
                        if (this.b.b != null) {
                            this.b.b.a(true, "", 0, taleThumbUserJson.more);
                        }
                        this.b.a.a(taleThumbUserJson.users);
                        return;
                    }
                    if (this.b.b != null) {
                        this.b.b.a(true, taleThumbUserJson.more, "");
                    }
                    this.b.a.b(taleThumbUserJson.users);
                }
            }
        });
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
