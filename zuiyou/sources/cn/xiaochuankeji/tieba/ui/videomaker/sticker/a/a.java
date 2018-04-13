package cn.xiaochuankeji.tieba.ui.videomaker.sticker.a;

import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class a {
    private String a;

    public a(String str) {
        this.a = str;
    }

    public d<String> a() {
        return d.a(new d$a<String>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                jVar.onStart();
                Object obj = null;
                File file = new File(this.a.a);
                if (file.exists()) {
                    JSONObject a = b.a(file, AppController.kDataCacheCharsetUTF8.name());
                    JSONObject optJSONObject = a.optJSONObject("data");
                    if (optJSONObject != null) {
                        obj = optJSONObject.toString();
                    } else {
                        obj = a.toString();
                    }
                }
                jVar.onNext(obj);
                jVar.onCompleted();
            }
        });
    }

    public void a(final String str) {
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    File file = new File(this.b.a);
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("data", new JSONObject(str));
                        b.a(jSONObject, file, AppController.kDataCacheCharsetUTF8.name());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }
}
