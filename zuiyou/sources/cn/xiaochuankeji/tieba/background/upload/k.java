package cn.xiaochuankeji.tieba.background.upload;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.api.upload.a;
import cn.xiaochuankeji.tieba.background.utils.b.e;
import cn.xiaochuankeji.tieba.json.upload.AllCheckJson;
import cn.xiaochuankeji.tieba.json.upload.BlockInitJson;
import cn.xiaochuankeji.tieba.json.upload.ImgResultJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import okhttp3.v$b;
import okhttp3.z;
import rx.j;

public class k {
    private a a = new a();
    private LocalMedia b;
    private File c;
    private c d;
    private Exception e;
    private String f;
    private int g;
    private long h;
    private volatile boolean i;
    private b j;
    private b k;
    private String l;

    public void a(LocalMedia localMedia, b bVar) throws Exception {
        this.e = null;
        this.b = localMedia;
        this.c = new File(localMedia.path);
        this.b = localMedia;
        this.b.md5 = b.b(this.c);
        this.j = bVar;
        this.a.a(v$b.a("file", this.c.getName(), new a(this.c, new b(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                this.a.j.a(j, j2, -1);
            }
        }))).b(new j<ImgResultJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ImgResultJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.e = new UploadException(th);
            }

            public void a(ImgResultJson imgResultJson) {
                if (imgResultJson != null && !TextUtils.isEmpty(imgResultJson.uri)) {
                    this.a.b.uri = imgResultJson.uri;
                }
            }
        });
        if (this.e != null) {
            throw this.e;
        }
        if (this.b.width <= 0 || this.b.height <= 0) {
            Pair a = cn.htjyb.c.b.b.a(localMedia.path);
            if (a != null) {
                this.b.width = ((Integer) a.first).intValue();
                this.b.height = ((Integer) a.second).intValue();
            }
        }
        this.b.resId = this.b.md5;
        Object a2 = i.a(this.b);
        this.b.fmt = a2;
        if (!TextUtils.isEmpty(a2)) {
            this.b.resType = a2.equals("gif") ? "gif" : "img";
        }
    }

    public void a(b bVar) {
        this.k = bVar;
    }

    public void a() {
        this.i = true;
    }

    public void b(LocalMedia localMedia, b bVar) throws Exception {
        this.i = false;
        this.j = bVar;
        this.f = cn.xiaochuankeji.tieba.background.a.e().B();
        this.f += File.separator + (System.currentTimeMillis() / 1000);
        this.e = null;
        this.b = localMedia;
        this.c = new File(localMedia.path);
        this.b.md5 = b.b(this.c);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.b.path);
        if (this.b.width <= 0 || this.b.height <= 0) {
            this.b.width = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            this.b.height = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        }
        if (this.b.width <= 0 || this.b.height <= 0) {
            throw new UploadException("视频已损坏");
        } else if (Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue() >= 900000) {
            throw new UploadException("上传视频不能超过15分钟");
        } else {
            this.h = this.c.length();
            this.d = h.a().a(localMedia.path, this.b.md5);
            if (this.d == null || this.d.e == 0) {
                this.d = new c();
                this.d.b = this.b.md5;
                this.d.a = localMedia.path;
                this.a.a(this.h).b(new j<BlockInitJson>(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((BlockInitJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        this.a.e = new UploadException(th);
                    }

                    public void a(BlockInitJson blockInitJson) {
                        this.a.d.e = blockInitJson.uploadid;
                        this.a.d.g = blockInitJson.bsize;
                    }
                });
                if (this.e != null) {
                    throw this.e;
                }
                this.g = e();
                h.a().b(this.d);
                b();
                return;
            }
            this.g = e();
            b();
        }
    }

    private void b() throws Exception {
        if (this.d.f == this.g) {
            c();
            return;
        }
        while (this.d.f < this.g) {
            File f = f();
            this.a.a(v$b.a("data", f.getName(), new a(f, new b(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void a(long j, long j2, int i) {
                    if (this.a.j != null) {
                        this.a.j.a(this.a.h, ((long) (this.a.d.f * this.a.d.g)) + j2, 0);
                    }
                }
            })), this.d.e, this.d.f).b(new j<String>(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((String) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if ((th instanceof ClientErrorException) && ((ClientErrorException) th).errCode() == 2) {
                        h.a().c(this.a.d);
                    }
                    this.a.e = new UploadException(th);
                }

                public void a(String str) {
                }
            });
            if (this.e != null) {
                throw this.e;
            } else if (!this.i) {
                c cVar = this.d;
                cVar.f++;
                h.a().b(this.d);
            } else {
                return;
            }
        }
        c();
    }

    private void c() throws Exception {
        this.a.a(this.d.e, this.b.mimeType).b(new j<AllCheckJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((AllCheckJson) obj);
            }

            public void onCompleted() {
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onError(java.lang.Throwable r4) {
                /*
                r3 = this;
                r0 = r4 instanceof cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
                if (r0 == 0) goto L_0x001c;
            L_0x0004:
                r0 = r4;
                r0 = (cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException) r0;
                r1 = r0.errCode();
                r2 = 2;
                if (r1 == r2) goto L_0x001c;
            L_0x000e:
                r1 = r0.errCode();
                r2 = 3;
                if (r1 == r2) goto L_0x001c;
            L_0x0015:
                r0 = r0.errCode();
                r1 = 4;
                if (r0 != r1) goto L_0x001c;
            L_0x001c:
                r0 = cn.xiaochuankeji.tieba.background.upload.h.a();
                r1 = r3.a;
                r1 = r1.d;
                r0.c(r1);
                r0 = r3.a;
                r1 = new cn.xiaochuankeji.tieba.background.upload.UploadException;
                r1.<init>(r4);
                r0.e = r1;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.background.upload.k.6.onError(java.lang.Throwable):void");
            }

            public void a(AllCheckJson allCheckJson) {
                this.a.b.uri = allCheckJson.uri;
            }
        });
        if (this.e != null) {
            throw this.e;
        } else if (!this.i) {
            d();
            this.b.resId = this.b.md5;
            this.b.fmt = i.a(this.b);
            this.b.resType = "video";
            h.a().c(this.d);
        }
    }

    private void d() throws Exception {
        z aVar;
        Throwable th;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        if (TextUtils.isEmpty(this.l) || !b.c(this.l)) {
            MediaMetadataRetriever mediaMetadataRetriever2;
            try {
                mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever2.setDataSource(this.b.path);
                    Bitmap frameAtTime = mediaMetadataRetriever2.getFrameAtTime();
                    try {
                        mediaMetadataRetriever2.release();
                    } catch (Exception e) {
                        this.e = e;
                    }
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    if (frameAtTime != null) {
                        frameAtTime.compress(CompressFormat.JPEG, 80, byteArrayOutputStream);
                        if (this.b.height <= 0 || this.b.width <= 0) {
                            this.b.height = frameAtTime.getHeight();
                            this.b.width = frameAtTime.getWidth();
                        }
                        frameAtTime.recycle();
                        aVar = new a(byteArrayOutputStream.toByteArray(), new b(this) {
                            final /* synthetic */ k a;

                            {
                                this.a = r1;
                            }

                            public void a(long j, long j2, int i) {
                                if (this.a.k != null) {
                                    this.a.k.a(j, j2, i);
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    try {
                        mediaMetadataRetriever.release();
                        return;
                    } catch (Exception e3) {
                        this.e = e3;
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        mediaMetadataRetriever2.release();
                    } catch (Exception e4) {
                        this.e = e4;
                    }
                    throw th;
                }
            } catch (Exception e5) {
                mediaMetadataRetriever.release();
                return;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                mediaMetadataRetriever2 = null;
                th = th4;
                mediaMetadataRetriever2.release();
                throw th;
            }
        }
        aVar = new a(new File(this.l), new b(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                if (this.a.k != null) {
                    this.a.k.a(j, j2, i);
                }
            }
        });
        this.a.a(v$b.a("file", this.c.getName(), aVar)).b(new j<ImgResultJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((ImgResultJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.e = new UploadException(th);
            }

            public void a(ImgResultJson imgResultJson) {
                if (imgResultJson != null && !TextUtils.isEmpty(imgResultJson.uri)) {
                    this.a.b.videoThumbUrl = imgResultJson.uri;
                }
            }
        });
        if (this.e != null) {
            throw this.e;
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.l = str;
        }
    }

    private int e() {
        long length = this.c.length();
        if (length < ((long) this.d.g)) {
            return 1;
        }
        return (int) (length / ((long) this.d.g));
    }

    private File f() {
        long length = this.c.length();
        File file = new File(this.f);
        try {
            byte[] bArr;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.c, "r");
            long j = (long) (this.d.f * this.d.g);
            randomAccessFile.seek(j);
            length -= j;
            if (length >= ((long) (this.d.g * 2))) {
                bArr = new byte[this.d.g];
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
}
