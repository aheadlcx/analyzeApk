package cn.xiaochuankeji.tieba.analyse.log;

import android.os.Environment;
import cn.xiaochuankeji.tieba.api.log.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.d.e;
import java.io.File;
import java.io.IOException;
import okhttp3.v$b;
import rx.b.g;
import rx.d;

public class c implements a {
    private LogCommandModel a;

    public c(LogCommandModel logCommandModel) {
        this.a = logCommandModel;
    }

    public void a() {
        final Object obj = a.e().B() + this.a.opid + ".zip";
        d.a(obj).d(new g<String, File>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((String) obj);
            }

            public File a(String str) {
                File file = new File(Environment.getDataDirectory(), String.format("//data//%s//databases/", new Object[]{"cn.xiaochuankeji.tieba"}));
                if (file.exists()) {
                    try {
                        e.a(file.getAbsolutePath(), str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    com.izuiyou.a.a.a.b("Debug", "can't touch application's database");
                }
                return new File(str);
            }
        }).c(new g<File, d<String>>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((File) obj);
            }

            public d<String> a(File file) {
                return this.a.a(file);
            }
        }).d(new g<String, Boolean>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ Object call(Object obj) {
                return a((String) obj);
            }

            public Boolean a(String str) {
                return Boolean.valueOf(e.a(obj));
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).a(new rx.e<Boolean>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.a.d("Debug", th);
            }

            public void a(Boolean bool) {
                com.izuiyou.a.a.a.c("Debug", "upload finished ");
            }
        });
    }

    private d<String> a(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        return b.b(v$b.a("file", file.getName(), new cn.xiaochuankeji.tieba.background.upload.a(file, new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                com.izuiyou.a.a.a.b("Debug", "upload file " + i);
            }
        })), this.a.opid);
    }
}
