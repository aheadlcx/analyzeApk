package cn.xiaochuankeji.tieba.background.utils.b;

import cn.htjyb.c.a.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.json.JSONException;
import org.json.JSONObject;

class g {
    private File a;
    private a b;

    interface a {
        void a(boolean z, boolean z2, String str);
    }

    public g(File file, a aVar) {
        this.a = file;
        this.b = aVar;
    }

    void a() {
        String b = b();
        if (b != null) {
            a(b);
        } else {
            this.b.a(false, false, "抽样文件md5获取失败");
        }
    }

    private String b() {
        String b;
        long length = this.a.length();
        String str = cn.xiaochuankeji.tieba.background.a.e().B() + (System.currentTimeMillis() / 1000);
        File file;
        try {
            byte[] bArr = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.a, "r");
            length /= 4;
            int i = 0;
            while (i < 4) {
                try {
                    randomAccessFile.seek(((long) i) * length);
                    if (randomAccessFile.read(bArr, 0, bArr.length) == bArr.length) {
                        fileOutputStream.write(bArr, 0, bArr.length);
                        i++;
                    } else {
                        b = b.b(new File(str));
                        file = new File(str);
                        if (file.exists()) {
                            file.delete();
                        }
                        return b;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            randomAccessFile.close();
            b = b.b(new File(str));
            file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            b = b.b(new File(str));
            file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
            b = b.b(new File(str));
            file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
        return b;
    }

    private void a(String str) {
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/zyapi/upload/samplecheck");
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("sample", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    this.a.b.a(true, dVar.c.c.optBoolean("issame"), null);
                    return;
                }
                this.a.b.a(false, false, dVar.c.c());
            }
        }).b();
    }
}
