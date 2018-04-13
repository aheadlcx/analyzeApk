package qsbk.app.activity;

import android.support.v4.internal.view.SupportMenu;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import qsbk.app.activity.LaiseeDetailActivity.VoiceManager.VoiceCallback;
import qsbk.app.core.AsyncTask;

class qq extends AsyncTask<Void, Integer, String> {
    final /* synthetic */ VoiceCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ Object c;
    final /* synthetic */ VoiceManager d;

    qq(VoiceManager voiceManager, VoiceCallback voiceCallback, String str, Object obj) {
        this.d = voiceManager;
        this.a = voiceCallback;
        this.b = str;
        this.c = obj;
    }

    protected /* synthetic */ void b(Object[] objArr) {
        a((Integer[]) objArr);
    }

    protected void a() {
        if (this.a != null) {
            this.a.onStart(this.b, this.c);
        }
    }

    protected String a(Void... voidArr) {
        IOException e;
        Throwable th;
        String path = VoiceManager.getPath(this.b);
        File file = new File(path + FileType.TEMP);
        FileOutputStream fileOutputStream;
        try {
            InputStream a = this.d.c(this.b);
            byte[] bArr = new byte[SupportMenu.USER_MASK];
            fileOutputStream = new FileOutputStream(file);
            try {
                int available = a.available();
                while (true) {
                    int read = a.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    d(new Integer[]{Integer.valueOf(read), Integer.valueOf(available)});
                    fileOutputStream.write(bArr, 0, read);
                }
                VoiceManager.forceRename(file, new File(path));
                if (fileOutputStream == null) {
                    return path;
                }
                try {
                    fileOutputStream.close();
                    return path;
                } catch (IOException e2) {
                    return path;
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            e = e6;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    protected void a(Integer... numArr) {
        if (this.a != null) {
            this.a.onProgress(this.b, (long) numArr[0].intValue(), (long) numArr[1].intValue(), this.c);
        }
    }

    protected void a(String str) {
        if (this.a == null) {
            return;
        }
        if (str == null) {
            this.a.onFaiure(this.b, "下载失败", this.c);
        } else {
            this.a.onSuccess(this.b, str, this.c);
        }
    }
}
