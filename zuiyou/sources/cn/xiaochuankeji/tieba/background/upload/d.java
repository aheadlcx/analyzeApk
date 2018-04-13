package cn.xiaochuankeji.tieba.background.upload;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Pair;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.api.upload.a;
import cn.xiaochuankeji.tieba.json.OSSTokenJson;
import cn.xiaochuankeji.tieba.json.upload.ImgResultJson;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.izuiyou.a.a.b;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import okhttp3.v$b;
import okhttp3.z;
import org.json.JSONObject;
import rx.j;

public class d {
    Exception a;
    private a b = new a();
    private OSS c;
    private OSSTokenJson d;
    private String e;
    private Exception f;
    private String g;
    private c h;
    private OSSAsyncTask i;
    private b j;

    public void a() throws Exception {
        if (this.d == null || TextUtils.isEmpty(this.d.endpoint) || TextUtils.isEmpty(this.d.imageBucket) || TextUtils.isEmpty(this.d.imageDir) || TextUtils.isEmpty(this.d.videoBucket) || TextUtils.isEmpty(this.d.videoDir)) {
            this.g = cn.xiaochuankeji.tieba.background.a.e().r() + "ossrecord/";
            File file = new File(this.g);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.f = null;
            this.b.a().b(new j<OSSTokenJson>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((OSSTokenJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.f = new UploadException(th);
                }

                public void a(OSSTokenJson oSSTokenJson) {
                    if (this.a.d == null) {
                        this.a.d = oSSTokenJson;
                    }
                }
            });
            if (this.f != null) {
                throw this.f;
            }
            OSSCredentialProvider anonymousClass6 = new OSSCustomSignerCredentialProvider(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public String signContent(String str) {
                    String str2 = "";
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(cn.xiaochuankeji.tieba.background.utils.d.a.b("/upload/custom_auth")).openConnection();
                        httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
                        b.put("cont", str);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                        outputStreamWriter.write(b.toString());
                        outputStreamWriter.close();
                        String readStreamAsString = IOUtils.readStreamAsString(new BufferedInputStream(httpURLConnection.getInputStream()), "utf-8");
                        b = new JSONObject(readStreamAsString).optJSONObject("data");
                        b.d(readStreamAsString);
                        if (b != null) {
                            return b.optString("appsig", "");
                        }
                    } catch (Exception e) {
                        b.d("get app sig error", e);
                    }
                    return str2;
                }
            };
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setConnectionTimeout(10000);
            clientConfiguration.setSocketTimeout(15000);
            clientConfiguration.setMaxConcurrentRequest(9);
            clientConfiguration.setMaxErrorRetry(1);
            this.c = new OSSClient(BaseApplication.getAppContext().getApplicationContext(), this.d.endpoint, anonymousClass6, clientConfiguration);
        }
    }

    private void c(LocalMedia localMedia) throws IOException {
        String calculateMd5Str = BinaryUtil.calculateMd5Str(localMedia.path);
        localMedia.md5 = calculateMd5Str;
        this.h = h.a().a(localMedia.path, calculateMd5Str);
        if (this.h == null || TextUtils.isEmpty(this.h.c)) {
            this.h = new c();
            this.h.a = localMedia.path;
            String uuid = UUID.randomUUID().toString();
            this.h.c = this.d.videoDir + "/" + uuid.substring(0, 2) + "/" + uuid.substring(2, 4) + "/" + uuid.substring(4);
            this.h.b = calculateMd5Str;
            h.a().a(this.h);
            return;
        }
        localMedia.ossKey = this.h.c;
        localMedia.resId = this.h.c;
        localMedia.resType = "video";
        localMedia.fmt = i.a(localMedia);
    }

    public boolean a(final LocalMedia localMedia) throws Exception {
        c(localMedia);
        if (!this.c.doesObjectExist(this.d.videoBucket, this.h.c)) {
            return false;
        }
        if (localMedia.width <= 0 || localMedia.height <= 0) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(localMedia.path);
            localMedia.width = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            localMedia.height = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        }
        if (localMedia.width <= 0 || localMedia.height <= 0) {
            throw new UploadException("视频已损坏");
        } else if (TextUtils.isEmpty(this.h.d)) {
            return false;
        } else {
            String uuid = UUID.randomUUID().toString();
            final String str = this.d.videoDir + "/" + uuid.substring(0, 2) + "/" + uuid.substring(2, 4) + "/" + uuid.substring(4);
            CopyObjectRequest copyObjectRequest = new CopyObjectRequest(this.d.videoBucket, this.h.c, this.d.videoBucket, str);
            MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
            mediaMetadataRetriever2.setDataSource(localMedia.path);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            String extractMetadata = mediaMetadataRetriever2.extractMetadata(12);
            if (TextUtils.isEmpty(extractMetadata) || !extractMetadata.contains("video")) {
                objectMetadata.setContentType("video/mp4");
            } else {
                objectMetadata.setContentType(extractMetadata);
            }
            copyObjectRequest.setNewObjectMetadata(objectMetadata);
            this.a = null;
            this.i = this.c.asyncCopyObject(copyObjectRequest, new OSSCompletedCallback<CopyObjectRequest, CopyObjectResult>(this) {
                final /* synthetic */ d c;

                public /* synthetic */ void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
                    a((CopyObjectRequest) oSSRequest, clientException, serviceException);
                }

                public /* synthetic */ void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
                    a((CopyObjectRequest) oSSRequest, (CopyObjectResult) oSSResult);
                }

                public void a(CopyObjectRequest copyObjectRequest, CopyObjectResult copyObjectResult) {
                    localMedia.resId = str;
                    localMedia.ossKey = str;
                    localMedia.resType = "video";
                    localMedia.uri = str;
                    localMedia.fmt = i.a(localMedia);
                }

                public void a(CopyObjectRequest copyObjectRequest, ClientException clientException, ServiceException serviceException) {
                    this.c.a = new UploadException();
                }
            });
            this.i.waitUntilFinished();
            if (this.a != null) {
                throw this.a;
            }
            d(localMedia);
            return true;
        }
    }

    public boolean b(final LocalMedia localMedia) throws Exception {
        String calculateMd5Str = BinaryUtil.calculateMd5Str(localMedia.path);
        localMedia.md5 = calculateMd5Str;
        this.h = h.a().a(localMedia.path, calculateMd5Str);
        if (this.h == null || TextUtils.isEmpty(this.h.c)) {
            this.h = new c();
            this.h.a = localMedia.path;
            String uuid = UUID.randomUUID().toString();
            this.h.c = this.d.imageDir + "/" + uuid.substring(0, 2) + "/" + uuid.substring(2, 4) + "/" + uuid.substring(4);
            this.h.b = calculateMd5Str;
            h.a().a(this.h);
            return false;
        } else if (!this.c.doesObjectExist(this.d.imageBucket, this.h.c)) {
            return false;
        } else {
            if (localMedia.width <= 0 || localMedia.height <= 0) {
                Pair a = cn.htjyb.c.b.b.a(localMedia.path);
                if (a != null) {
                    localMedia.width = ((Integer) a.first).intValue();
                    localMedia.height = ((Integer) a.second).intValue();
                }
            }
            calculateMd5Str = UUID.randomUUID().toString();
            calculateMd5Str = this.d.imageDir + "/" + calculateMd5Str.substring(0, 2) + "/" + calculateMd5Str.substring(2, 4) + "/" + calculateMd5Str.substring(4);
            final String a2 = i.a(localMedia);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/" + a2);
            CopyObjectRequest copyObjectRequest = new CopyObjectRequest(this.d.imageBucket, this.h.c, this.d.imageBucket, calculateMd5Str);
            copyObjectRequest.setNewObjectMetadata(objectMetadata);
            this.a = null;
            this.i = this.c.asyncCopyObject(copyObjectRequest, new OSSCompletedCallback<CopyObjectRequest, CopyObjectResult>(this) {
                final /* synthetic */ d d;

                public /* synthetic */ void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
                    a((CopyObjectRequest) oSSRequest, clientException, serviceException);
                }

                public /* synthetic */ void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
                    a((CopyObjectRequest) oSSRequest, (CopyObjectResult) oSSResult);
                }

                public void a(CopyObjectRequest copyObjectRequest, CopyObjectResult copyObjectResult) {
                    localMedia.ossKey = calculateMd5Str;
                    localMedia.resId = calculateMd5Str;
                    localMedia.fmt = a2;
                    if (!TextUtils.isEmpty(a2)) {
                        localMedia.resType = a2.equals("gif") ? "gif" : "img";
                    }
                    localMedia.uri = calculateMd5Str;
                }

                public void a(CopyObjectRequest copyObjectRequest, ClientException clientException, ServiceException serviceException) {
                    this.d.a = new UploadException();
                }
            });
            this.i.waitUntilFinished();
            if (this.a == null) {
                return true;
            }
            throw this.a;
        }
    }

    public void b() {
        if (this.c != null && this.i != null) {
            this.i.cancel();
        }
    }

    public void a(final LocalMedia localMedia, final b bVar) throws Exception {
        this.f = null;
        ResumableUploadRequest resumableUploadRequest = new ResumableUploadRequest(this.d.videoBucket, this.h.c, localMedia.path, this.g);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(localMedia.path);
        if (localMedia.width <= 0 || localMedia.height <= 0) {
            localMedia.width = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            localMedia.height = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        }
        if (localMedia.width <= 0 || localMedia.height <= 0) {
            UploadException uploadException = new UploadException("视频已损坏");
            uploadException.isNeedRetry = false;
            throw uploadException;
        } else if (Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue() >= 900000) {
            throw new UploadException("上传视频不能超过15分钟");
        } else {
            resumableUploadRequest.setProgressCallback(new OSSProgressCallback<ResumableUploadRequest>(this) {
                final /* synthetic */ d b;

                public /* synthetic */ void onProgress(Object obj, long j, long j2) {
                    a((ResumableUploadRequest) obj, j, j2);
                }

                public void a(ResumableUploadRequest resumableUploadRequest, long j, long j2) {
                    bVar.a(j2, j, 0);
                }
            });
            ObjectMetadata objectMetadata = new ObjectMetadata();
            String extractMetadata = mediaMetadataRetriever.extractMetadata(12);
            if (TextUtils.isEmpty(extractMetadata) || !extractMetadata.contains("video")) {
                objectMetadata.setContentType("video/mp4");
            } else {
                objectMetadata.setContentType(extractMetadata);
            }
            objectMetadata.setHeader("x-oss-meta-all-md5", cn.htjyb.c.a.b.b(new File(localMedia.path)));
            resumableUploadRequest.setMetadata(objectMetadata);
            resumableUploadRequest.setDeleteUploadOnCancelling(Boolean.valueOf(false));
            this.i = this.c.asyncResumableUpload(resumableUploadRequest, new OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult>(this) {
                final /* synthetic */ d b;

                public /* synthetic */ void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
                    a((ResumableUploadRequest) oSSRequest, clientException, serviceException);
                }

                public /* synthetic */ void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
                    a((ResumableUploadRequest) oSSRequest, (ResumableUploadResult) oSSResult);
                }

                public void a(ResumableUploadRequest resumableUploadRequest, ResumableUploadResult resumableUploadResult) {
                    localMedia.serverUrl = resumableUploadResult.getLocation();
                    localMedia.resId = this.b.h.c;
                    localMedia.ossKey = this.b.h.c;
                    localMedia.resType = "video";
                    localMedia.uri = this.b.h.c;
                    localMedia.fmt = i.a(localMedia);
                }

                public void a(ResumableUploadRequest resumableUploadRequest, ClientException clientException, ServiceException serviceException) {
                    if (clientException != null) {
                        this.b.f = clientException;
                    }
                    if (serviceException != null) {
                        this.b.f = serviceException;
                    }
                    if (this.b.f == null) {
                        this.b.f = new UploadException();
                    }
                }
            });
            this.i.waitUntilFinished();
            if (this.f != null) {
                throw this.f;
            }
            d(localMedia);
            h.a().a(this.h);
        }
    }

    public void b(final LocalMedia localMedia, final b bVar) throws Exception {
        this.f = null;
        final String a = i.a(localMedia);
        ResumableUploadRequest resumableUploadRequest = new ResumableUploadRequest(this.d.imageBucket, this.h.c, localMedia.path, this.g);
        resumableUploadRequest.setProgressCallback(new OSSProgressCallback<ResumableUploadRequest>(this) {
            final /* synthetic */ d b;

            public /* synthetic */ void onProgress(Object obj, long j, long j2) {
                a((ResumableUploadRequest) obj, j, j2);
            }

            public void a(ResumableUploadRequest resumableUploadRequest, long j, long j2) {
                bVar.a(j2, j, 0);
            }
        });
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/" + a);
        resumableUploadRequest.setMetadata(objectMetadata);
        this.i = this.c.asyncResumableUpload(resumableUploadRequest, new OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult>(this) {
            final /* synthetic */ d c;

            public /* synthetic */ void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
                a((ResumableUploadRequest) oSSRequest, clientException, serviceException);
            }

            public /* synthetic */ void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
                a((ResumableUploadRequest) oSSRequest, (ResumableUploadResult) oSSResult);
            }

            public void a(ResumableUploadRequest resumableUploadRequest, ResumableUploadResult resumableUploadResult) {
                localMedia.resId = this.c.h.c;
                localMedia.ossKey = this.c.h.c;
                localMedia.fmt = a;
                localMedia.resType = a.equals("gif") ? "gif" : "img";
                localMedia.uri = this.c.h.c;
            }

            public void a(ResumableUploadRequest resumableUploadRequest, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    this.c.f = clientException;
                }
                if (serviceException != null) {
                    this.c.f = serviceException;
                }
                if (this.c.f == null) {
                    this.c.f = new UploadException();
                }
            }
        });
        this.i.waitUntilFinished();
        if (this.f != null) {
            throw this.f;
        } else if (localMedia.width <= 0 || localMedia.height <= 0) {
            Pair a2 = cn.htjyb.c.b.b.a(localMedia.path);
            if (a2 != null) {
                localMedia.width = ((Integer) a2.first).intValue();
                localMedia.height = ((Integer) a2.second).intValue();
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
    }

    public void a(b bVar) {
        this.j = bVar;
    }

    private void d(LocalMedia localMedia) throws Exception {
        PutObjectRequest putObjectRequest;
        Throwable th;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        this.f = null;
        String uuid = UUID.randomUUID().toString();
        String str = this.d.imageDir + "/" + uuid.substring(0, 2) + "/" + uuid.substring(2, 4) + "/" + uuid.substring(4);
        uuid = "jpg";
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");
        if (TextUtils.isEmpty(this.e) || !cn.htjyb.c.a.b.c(this.e)) {
            MediaMetadataRetriever mediaMetadataRetriever2;
            try {
                mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever2.setDataSource(localMedia.path);
                    Bitmap frameAtTime = mediaMetadataRetriever2.getFrameAtTime();
                    try {
                        mediaMetadataRetriever2.release();
                    } catch (Exception e) {
                    }
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    frameAtTime.compress(CompressFormat.JPEG, 80, byteArrayOutputStream);
                    if (localMedia.height <= 0 || localMedia.width <= 0) {
                        localMedia.height = frameAtTime.getHeight();
                        localMedia.width = frameAtTime.getWidth();
                    }
                    frameAtTime.recycle();
                    putObjectRequest = new PutObjectRequest(this.d.imageBucket, str, byteArrayOutputStream.toByteArray());
                } catch (Exception e2) {
                    try {
                        mediaMetadataRetriever2.release();
                        return;
                    } catch (Exception e3) {
                        return;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    th = th3;
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                mediaMetadataRetriever2 = null;
                mediaMetadataRetriever2.release();
                return;
            } catch (Throwable th4) {
                th = th4;
                mediaMetadataRetriever.release();
                throw th;
            }
        }
        putObjectRequest = new PutObjectRequest(this.d.imageBucket, str, this.e);
        putObjectRequest.setMetadata(objectMetadata);
        putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onProgress(Object obj, long j, long j2) {
                a((PutObjectRequest) obj, j, j2);
            }

            public void a(PutObjectRequest putObjectRequest, long j, long j2) {
                if (this.a.j != null) {
                    this.a.j.a(j2, j, 0);
                }
            }
        });
        this.i = this.c.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onFailure(OSSRequest oSSRequest, ClientException clientException, ServiceException serviceException) {
                a((PutObjectRequest) oSSRequest, clientException, serviceException);
            }

            public /* synthetic */ void onSuccess(OSSRequest oSSRequest, OSSResult oSSResult) {
                a((PutObjectRequest) oSSRequest, (PutObjectResult) oSSResult);
            }

            public void a(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
            }

            public void a(PutObjectRequest putObjectRequest, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    this.a.f = clientException;
                }
                if (serviceException != null) {
                    this.a.f = serviceException;
                }
                if (this.a.f == null) {
                    this.a.f = new UploadException();
                }
            }
        });
        this.i.waitUntilFinished();
        if (this.f != null) {
            e(localMedia);
            return;
        }
        localMedia.videoThumbUrl = str;
        this.h.d = str;
    }

    private void e(final LocalMedia localMedia) throws Exception {
        MediaMetadataRetriever mediaMetadataRetriever;
        z aVar;
        Throwable th;
        MediaMetadataRetriever mediaMetadataRetriever2 = null;
        this.f = null;
        File file = new File(localMedia.path);
        if (TextUtils.isEmpty(this.e) || !cn.htjyb.c.a.b.c(this.e)) {
            try {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(localMedia.path);
                    Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e) {
                    }
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    if (frameAtTime != null) {
                        frameAtTime.compress(CompressFormat.JPEG, 80, byteArrayOutputStream);
                        if (localMedia.height <= 0 || localMedia.width <= 0) {
                            localMedia.height = frameAtTime.getHeight();
                            localMedia.width = frameAtTime.getWidth();
                        }
                        frameAtTime.recycle();
                        aVar = new a(byteArrayOutputStream.toByteArray(), new b(this) {
                            final /* synthetic */ d a;

                            {
                                this.a = r1;
                            }

                            public void a(long j, long j2, int i) {
                                if (this.a.j != null) {
                                    this.a.j.a(j, j2, i);
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    mediaMetadataRetriever2 = mediaMetadataRetriever;
                    try {
                        mediaMetadataRetriever2.release();
                        return;
                    } catch (Exception e3) {
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                mediaMetadataRetriever2.release();
                return;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                mediaMetadataRetriever = null;
                th = th4;
                mediaMetadataRetriever.release();
                throw th;
            }
        }
        aVar = new a(new File(this.e), new b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                if (this.a.j != null) {
                    this.a.j.a(j, j2, i);
                }
            }
        });
        this.b.a(v$b.a("file", file.getName(), aVar)).b(new j<ImgResultJson>(this) {
            final /* synthetic */ d b;

            public /* synthetic */ void onNext(Object obj) {
                a((ImgResultJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.f = new UploadException(th);
            }

            public void a(ImgResultJson imgResultJson) {
                if (imgResultJson != null && !TextUtils.isEmpty(imgResultJson.uri)) {
                    localMedia.videoThumbUrl = imgResultJson.uri;
                    this.b.h.d = imgResultJson.uri;
                }
            }
        });
        if (this.f != null) {
            UploadException uploadException = new UploadException(this.f);
            uploadException.isNeedRetry = false;
            throw uploadException;
        }
    }
}
