package cn.xiaochuankeji.tieba.background.utils.b;

import android.util.Log;
import cn.htjyb.netlib.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private b a;
    private a b;
    private File c;
    private long d;
    private int e;
    private int f;
    private int g;
    private String h = cn.xiaochuankeji.tieba.background.a.e().B();

    public interface a {
        void a(int i, int i2);
    }

    interface b {
        void a(boolean z, int i, int i2, String str);
    }

    d(File file, long j, int i, int i2, b bVar) {
        this.c = file;
        this.d = j;
        this.e = i;
        this.f = i2;
        this.a = bVar;
        this.h += File.separator + (System.currentTimeMillis() / 1000);
        this.g = c();
    }

    void a() {
        if (this.b != null) {
            this.b.a(this.g, this.e + 1);
        }
        if (this.e + 1 < this.g) {
            b();
        } else {
            this.a.a(true, 0, 0, null);
        }
    }

    private void b() {
        File d = d();
        if (d == null) {
            this.a.a(false, this.e, 0, "文件读取出错");
            return;
        }
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/zyapi/upload/blockdata");
        Collection arrayList = new ArrayList();
        arrayList.add(new cn.htjyb.netlib.b.b(d, "data"));
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("uploadid", this.d);
            jSONObject.put("block", this.e + 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new g(b, cn.xiaochuankeji.tieba.background.a.d(), arrayList, jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                if (dVar.c.a) {
                    this.a.e = this.a.e + 1;
                    this.a.a();
                    return;
                }
                int i = dVar.c.b;
                if (-2 == i) {
                    this.a.a.a(false, 0, i, dVar.c.c());
                } else {
                    this.a.a.a(false, this.a.e, i, dVar.c.c());
                }
            }
        }).b();
    }

    private int c() {
        long length = this.c.length();
        if (length < ((long) this.f)) {
            return 1;
        }
        return (int) (length / ((long) this.f));
    }

    private File d() {
        long length = this.c.length();
        File file = new File(this.h);
        try {
            byte[] bArr;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.c, "r");
            long j = (long) ((this.e + 1) * this.f);
            randomAccessFile.seek(j);
            length -= j;
            if (length >= ((long) (this.f * 2))) {
                bArr = new byte[this.f];
            } else {
                bArr = new byte[((int) length)];
            }
            if (randomAccessFile.read(bArr, 0, bArr.length) != bArr.length) {
                Log.i(e.a, "文件读取length错误");
                return null;
            }
            fileOutputStream.write(bArr, 0, bArr.length);
            fileOutputStream.flush();
            fileOutputStream.close();
            randomAccessFile.close();
            if (file.length() > 0) {
                return file;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
