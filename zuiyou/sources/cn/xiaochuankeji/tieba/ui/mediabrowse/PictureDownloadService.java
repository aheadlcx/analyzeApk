package cn.xiaochuankeji.tieba.ui.mediabrowse;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.htjyb.netlib.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.push.e;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.LinkedList;
import org.greenrobot.eventbus.c;

public class PictureDownloadService extends Service implements Callback {
    private final LinkedList<a> a = new LinkedList();
    private Handler b;
    private cn.htjyb.netlib.a c;

    class a extends AsyncTask<cn.htjyb.b.a, Void, Boolean> {
        final /* synthetic */ PictureDownloadService a;
        private final String b;
        private final cn.htjyb.b.a c;
        private final Pair<String, String> d;
        private final int e;
        private int f = -1;
        private boolean g = false;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((cn.htjyb.b.a[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        a(PictureDownloadService pictureDownloadService, String str, cn.htjyb.b.a aVar, Pair<String, String> pair, int i) {
            this.a = pictureDownloadService;
            this.b = str;
            this.c = aVar;
            this.d = pair;
            this.e = i;
        }

        public cn.htjyb.b.a a() {
            return this.c;
        }

        public void a(int i) {
            if (i > this.f) {
                b(i);
            }
            this.f = i;
        }

        public void a(boolean z) {
            if (z) {
                b(100);
                execute(new cn.htjyb.b.a[]{this.c});
                return;
            }
            c(z);
            this.a.b.sendEmptyMessage(1);
        }

        public void b(boolean z) {
            c(z);
            this.a.b.sendEmptyMessage(1);
        }

        private void b(int i) {
            Builder builder = new Builder(this.a, "下载");
            builder.setSmallIcon(R.mipmap.icon_launcher);
            builder.setProgress(100, i, false);
            builder.setContentTitle(this.b);
            builder.setContentText("下载中");
            builder.setOngoing(true);
            e.a().a(this.e, builder.build());
        }

        private void c(boolean z) {
            Builder builder = new Builder(this.a, "下载");
            builder.setSmallIcon(R.mipmap.icon_launcher);
            builder.setAutoCancel(true);
            if (z) {
                builder.setContentTitle(this.b);
                c.a().d(new DownloadProgressEvent(this.c.getPictureID(), 1, 100));
                builder.setContentText("已下载到 " + b() + " 目录");
                builder.setDefaults(1);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse((String) this.d.first), (String) this.d.second);
                intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                builder.setContentIntent(PendingIntent.getActivity(this.a, 0, intent, 134217728));
            } else {
                c.a().d(new DownloadProgressEvent(this.c.getPictureID(), -1, 100));
                builder.setContentTitle(this.b);
                builder.setContentText("下载失败");
                builder.setAutoCancel(true);
            }
            e.a().a(this.e, builder.build());
        }

        private boolean a(cn.htjyb.b.a aVar) {
            if (!aVar.hasLocalFile()) {
                return false;
            }
            if (new File((String) this.d.first).exists()) {
                return true;
            }
            return b.a(new File(aVar.cachePath()), new File((String) this.d.first));
        }

        protected Boolean a(cn.htjyb.b.a... aVarArr) {
            if (!a(aVarArr[0])) {
                return Boolean.FALSE;
            }
            if (a((String) this.d.second)) {
                b((String) this.d.first, (String) this.d.second);
            } else {
                a((String) this.d.first, (String) this.d.second);
            }
            a(new File((String) this.d.first));
            return Boolean.TRUE;
        }

        protected void a(Boolean bool) {
            b(bool.booleanValue());
        }

        private boolean a(String str) {
            return str.startsWith("video");
        }

        private void a(String str, String str2) {
            ContentResolver contentResolver = this.a.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("mime_type", str2);
            contentValues.put("_data", str);
            contentValues.put("_size", Long.valueOf(new File(str).length()));
            try {
                contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
            } catch (Exception e) {
                com.izuiyou.a.a.b.e("failed to add image to media store");
            }
        }

        private void b(String str, String str2) {
            ContentResolver contentResolver = this.a.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("mime_type", str2);
            contentValues.put("_data", str);
            contentValues.put("_size", Long.valueOf(new File(str).length()));
            try {
                contentResolver.insert(Video.Media.EXTERNAL_CONTENT_URI, contentValues);
            } catch (Exception e) {
                com.izuiyou.a.a.b.e("failed to add video to media store");
            }
        }

        private void a(File file) {
            this.a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
        }

        private String b() {
            String[] split = cn.xiaochuankeji.tieba.background.a.e().D().split("/");
            String str = "";
            for (int length = split.length - 1; length >= 0; length--) {
                if (!TextUtils.isEmpty(split[length])) {
                    str = split[length];
                    break;
                }
            }
            if (cn.xiaochuankeji.tieba.background.a.e().D().contains("/DCIM/zuiyou")) {
                return "DCIM/zuiyou";
            }
            return str;
        }
    }

    public static void a(Context context, String str, cn.htjyb.b.a aVar) {
        a(context, str, aVar.downloadUrl(), (Type) aVar.getType(), aVar.getPictureID());
    }

    public static void a(Context context, String str, String str2, Type type, long j) {
        Intent intent = new Intent(context, PictureDownloadService.class);
        intent.putExtra("title", str);
        intent.putExtra("picture_url", str2);
        intent.putExtra("picture_type", type);
        intent.putExtra("picture_id", j);
        context.startService(intent);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.b = new Handler(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.removeMessages(1);
        if (this.c != null) {
            this.c.a(null);
            this.c.c();
            this.c = null;
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String stringExtra = intent.getStringExtra("title");
        Object stringExtra2 = intent.getStringExtra("picture_url");
        Type type = (Type) intent.getSerializableExtra("picture_type");
        long longExtra = intent.getLongExtra("picture_id", 0);
        if (!(TextUtils.isEmpty(stringExtra2) || type == null)) {
            a(stringExtra, new PictureImpl(stringExtra2, type, longExtra), i2);
        }
        return 2;
    }

    private void a(String str, cn.htjyb.b.a aVar, int i) {
        if (this.c == null) {
            Pair a = a(aVar);
            if (a != null) {
                a aVar2 = new a(this, str, aVar, a, i);
                if (new File((String) a.first).exists()) {
                    aVar2.b(true);
                } else {
                    g.a("正在下载");
                    aVar2.a(0);
                    this.a.add(aVar2);
                }
            }
            a();
        }
    }

    private void a() {
        if (this.a.isEmpty()) {
            stopSelf();
            return;
        }
        final a aVar = (a) this.a.remove(0);
        final cn.htjyb.b.a a = aVar.a();
        if (a.hasLocalFile()) {
            aVar.a(true);
            return;
        }
        this.c = new cn.htjyb.netlib.a(a.downloadUrl(), cn.xiaochuankeji.tieba.background.a.d(), a.cachePath(), new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ PictureDownloadService b;

            public void onTaskFinish(d dVar) {
                this.b.c = null;
                aVar.a(dVar.c.a);
            }
        });
        this.c.a(new cn.htjyb.netlib.a.a(this) {
            final /* synthetic */ PictureDownloadService c;

            public void a(int i, int i2) {
                int i3 = (int) ((((float) i2) * 100.0f) / ((float) i));
                aVar.a(i3);
                if (a.getType() == Type.kMP4 || a.getType() == Type.kVideo) {
                    c.a().d(new DownloadProgressEvent(a.getPictureID(), 0, i3));
                }
            }
        });
        this.c.b();
    }

    private Pair<String, String> a(cn.htjyb.b.a aVar) {
        if (aVar == null) {
            return null;
        }
        String D = cn.xiaochuankeji.tieba.background.a.e().D();
        if (D == null) {
            return null;
        }
        Object obj;
        Object obj2;
        String str = D + aVar.getPictureID();
        if (aVar.getType() == Type.kVideo) {
            obj = str + PictureImpl.getSavedName(aVar.downloadUrl()) + ".mp4";
            obj2 = "video/mp4";
        } else if (aVar.getType() == Type.kGif) {
            obj = str + ".gif";
            obj2 = "image/gif";
        } else if (aVar.getType() == Type.kMP4) {
            obj = str + ".mp4";
            obj2 = "video/mp4";
        } else {
            obj = str + ".jpg";
            obj2 = "image/jpg";
        }
        return new Pair(obj, obj2);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            a();
        }
        return true;
    }
}
