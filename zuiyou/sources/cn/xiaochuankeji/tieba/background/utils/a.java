package cn.xiaochuankeji.tieba.background.utils;

import android.media.AudioRecord;
import android.os.AsyncTask;
import com.izuiyou.a.a.b;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class a {
    static final /* synthetic */ boolean b;
    private static a c;
    private static int e = AudioRecord.getMinBufferSize(16000, 16, 2);
    boolean a;
    private AudioRecord d;
    private boolean f = false;
    private byte[] g;
    private File h;
    private File i;
    private OutputStream j;
    private String k = (cn.xiaochuankeji.tieba.background.a.e().B() + "record");
    private a l;

    public interface a {
        void a(String str);

        void b(String str);
    }

    static {
        boolean z;
        if (a.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        b = z;
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public void a(a aVar) {
        this.l = aVar;
        if (this.d != null) {
            if (this.d.getRecordingState() == 3) {
                b();
            } else {
                this.d.release();
            }
        }
        this.a = d();
        if (this.a) {
            this.d = new AudioRecord(1, 16000, 16, 2, e);
            try {
                this.d.startRecording();
                if (this.d.getRecordingState() != 3) {
                    b();
                    aVar.b("没有录音权限,请去设置中打开录音权限");
                    return;
                }
                this.f = true;
                new AsyncTask(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    protected Object doInBackground(Object[] objArr) {
                        return Boolean.valueOf(this.a.c());
                    }

                    protected void onPostExecute(Object obj) {
                        super.onPostExecute(obj);
                        if (!((Boolean) obj).booleanValue() || this.a.i.length() <= 0) {
                            this.a.l.b("录音过程中失败");
                        } else {
                            this.a.l.a(this.a.i.getPath());
                        }
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                return;
            } catch (Exception e) {
                b.d("record error", e);
                return;
            }
        }
        aVar.b("录音文件创建失败");
    }

    public void b() {
        if (this.a) {
            this.f = false;
            try {
                this.d.stop();
                this.d.release();
            } catch (Exception e) {
                b.e(e);
            }
        }
    }

    private boolean c() {
        this.g = new byte[e];
        try {
            this.j = new BufferedOutputStream(new FileOutputStream(this.h));
            while (this.f) {
                if (this.d.read(this.g, 0, e) > 0) {
                    try {
                        this.j.write(this.g);
                    } catch (IOException e) {
                        return false;
                    }
                }
            }
            if (this.j != null) {
                try {
                    this.j.close();
                } catch (IOException e2) {
                    return false;
                }
            }
            return a(this.h.getPath(), this.i.getPath());
        } catch (IOException e3) {
            return false;
        }
    }

    private boolean a(String str, String str2) {
        File file = new File(str);
        if (file != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[ShareConstants.MD5_FILE_BUF_LENGTH];
                int i = 0;
                for (int read = fileInputStream.read(bArr); read != -1; read = fileInputStream.read(bArr)) {
                    i += read;
                }
                fileInputStream.close();
                j jVar = new j();
                jVar.b = i + 36;
                jVar.e = 16;
                jVar.k = (short) 16;
                jVar.g = (short) 1;
                jVar.f = (short) 1;
                jVar.h = 16000;
                jVar.j = (short) ((jVar.g * jVar.k) / 8);
                jVar.i = jVar.j * jVar.h;
                jVar.m = i;
                byte[] a = jVar.a();
                if (b || a.length == 44) {
                    File file2 = new File(str2);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    byte[] bArr2 = new byte[ShareConstants.MD5_FILE_BUF_LENGTH];
                    fileOutputStream.write(a, 0, a.length);
                    for (i = fileInputStream2.read(bArr2); i != -1; i = fileInputStream2.read(bArr2)) {
                        fileOutputStream.write(bArr2, 0, i);
                    }
                    fileOutputStream.close();
                } else {
                    throw new AssertionError();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean d() {
        File file = new File(this.k);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.h = new File(this.k + File.separator + "local_record.pcm");
        this.i = new File(this.k + File.separator + "local_record.wav");
        if (this.h.exists()) {
            this.h.delete();
        }
        if (this.i.exists()) {
            this.i.delete();
        }
        try {
            this.h.createNewFile();
            this.i.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
