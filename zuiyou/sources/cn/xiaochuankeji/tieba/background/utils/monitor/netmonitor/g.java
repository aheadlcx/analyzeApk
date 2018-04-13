package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;
import rx.j;

public class g {
    private static g a;
    private String b = cn.xiaochuankeji.tieba.background.a.e().A();
    private Context c;
    private cn.htjyb.netlib.g d;

    public interface a {
        void a(boolean z, String str);
    }

    private g(Context context) {
        this.c = context;
    }

    public static g a(Context context) {
        if (a == null) {
            a = new g(context);
        }
        return a;
    }

    public void a(String str, JSONObject jSONObject, final a aVar) {
        final File file = new File(this.b + cn.xiaochuankeji.tieba.background.utils.monitor.crash.a.a(str));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            cn.xiaochuankeji.tieba.api.log.a aVar2 = new cn.xiaochuankeji.tieba.api.log.a();
            com.alibaba.fastjson.JSONObject jSONObject2 = new com.alibaba.fastjson.JSONObject();
            jSONObject2.put("type", "diagnosis");
            jSONObject2.put("content", "host connect diagnosis");
            aVar2.a(jSONObject2, file).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<Void>(this) {
                final /* synthetic */ g c;

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                    file.delete();
                }

                public void onError(Throwable th) {
                    aVar.a(false, th.getMessage());
                }

                public void a(Void voidR) {
                    aVar.a(true, null);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            aVar.a(false, e.getMessage());
        } catch (IOException e2) {
            e2.printStackTrace();
            aVar.a(false, e2.getMessage());
        }
    }

    public void a() {
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
    }
}
