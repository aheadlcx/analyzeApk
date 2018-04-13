package cn.xiaochuankeji.tieba.analyse.log;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.d.e;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.v$b;
import rx.b.g;
import rx.d;
import rx.d$a;
import rx.j;

public class b implements a {
    private static final String a = b.class.getSimpleName();
    private LogCommandModel b;
    private String c = a.e().L();
    private String d;

    public b(LogCommandModel logCommandModel) {
        this.b = logCommandModel;
        this.d = a.e().B() + logCommandModel.opid + ".zip";
    }

    public void a() {
        d.a(this.c).d(new g<String, File>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((String) obj);
            }

            public File a(String str) {
                try {
                    e.a(str, this.a.d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new File(this.a.d);
            }
        }).c(new g<File, d<String>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((File) obj);
            }

            public d<String> a(File file) {
                return this.a.a(file);
            }
        }).c(new g<String, d<String>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((String) obj);
            }

            public d<String> a(String str) {
                return this.a.a(str);
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).a(new rx.e<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.a.d(b.a, th);
                this.a.b(this.a.d);
            }

            public void a(String str) {
                com.izuiyou.a.a.a.c(b.a, "upload finished ");
            }
        });
    }

    private d<String> a(File file) {
        if (file == null) {
            com.izuiyou.a.a.a.d(a, "file is null");
            return null;
        } else if (file.exists()) {
            return new cn.xiaochuankeji.tieba.api.log.b().a(v$b.a("file", file.getName(), new cn.xiaochuankeji.tieba.background.upload.a(file, new cn.xiaochuankeji.tieba.background.upload.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(long j, long j2, int i) {
                    com.izuiyou.a.a.a.b(b.a, "upload file " + j2 + "--" + j);
                }
            })), this.b.opid);
        } else {
            com.izuiyou.a.a.a.d(a, "file not exist");
            return null;
        }
    }

    private d<String> a(final String str) {
        return d.a(new d$a<String>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                jVar.onStart();
                e.b(a.e().L(), "izuiyou_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlog");
                this.b.b(this.b.d);
                com.izuiyou.a.a.a.b(b.a, "deleteInvalidFile success");
                jVar.onNext(str);
                jVar.onCompleted();
            }
        });
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            e.a(str);
        }
    }
}
