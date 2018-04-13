package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import java.io.File;

public class b {
    private Activity a;
    private a b;
    private cn.htjyb.netlib.a c;

    public interface a {
        void a();

        void a(int i, int i2);

        void a(boolean z, String str);
    }

    public b(Activity activity, a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    public void a(String str) {
        if (this.c == null) {
            if (TextUtils.isEmpty(str)) {
                this.b.a(false, "下载链接无效");
                return;
            }
            String b = b(str);
            if (TextUtils.isEmpty(b)) {
                this.b.a(false, "目录创建失败");
            } else if (new File(b).exists()) {
                this.b.a(true, a());
            } else {
                File file = new File(cn.xiaochuankeji.tieba.background.a.e().j() + d.c(str).substring(0, 16));
                if (!file.exists()) {
                    d(str);
                } else if (cn.htjyb.c.a.b.a(file, new File(b))) {
                    this.b.a(true, a());
                    c(b);
                } else {
                    this.b.a(false, "文件拷贝失败");
                }
            }
        }
    }

    private String b(String str) {
        String D = cn.xiaochuankeji.tieba.background.a.e().D();
        if (D == null) {
            return null;
        }
        return D + PictureImpl.getSavedName(str) + ".mp4";
    }

    private void c(String str) {
        ContentResolver contentResolver = this.a.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("_data", str);
        contentValues.put("_size", Long.valueOf(new File(str).length()));
        try {
            contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (Exception e) {
            com.izuiyou.a.a.b.e("failed to add video to media store");
        }
        this.a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str))));
    }

    private void d(String str) {
        final String str2 = cn.xiaochuankeji.tieba.background.a.e().j() + d.c(str).substring(0, 16);
        final String b = b(str);
        this.c = new cn.htjyb.netlib.a(str, cn.xiaochuankeji.tieba.background.a.d(), str2, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ b c;

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                this.c.c = null;
                if (!dVar.c.a) {
                    this.c.b.a(false, dVar.c.c());
                } else if (cn.htjyb.c.a.b.a(new File(str2), new File(b))) {
                    this.c.c(b);
                    this.c.b.a(true, this.c.a());
                } else {
                    this.c.b.a(false, "文件拷贝失败");
                }
            }
        });
        this.c.a(new cn.htjyb.netlib.a.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.b.a(i, i2);
            }
        });
        this.c.b();
        this.b.a();
    }

    private String a() {
        return this.a.getString(R.string.download_tip);
    }
}
