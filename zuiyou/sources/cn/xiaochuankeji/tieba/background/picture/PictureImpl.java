package cn.xiaochuankeji.tieba.background.picture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import cn.htjyb.b.a;
import cn.htjyb.c.b.b;
import cn.htjyb.c.d;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import com.danikula.videocache.q;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class PictureImpl implements a, Serializable {
    private static final long serialVersionUID = -6914040341609097679L;
    private transient cn.htjyb.netlib.a a;
    private transient HashSet<a.a> b = new HashSet();
    private boolean downloading = false;
    private boolean isSelect = false;
    private long mPicId;
    private int mRotateDegress;
    private Type mType;
    private String mUri;

    public enum Type {
        kAvatarMale,
        kAvatarFemale,
        kAvatarOriginMale,
        kAvatarOriginFemale,
        kPostPic228,
        kPostPic480,
        kPostPic600,
        kPostPicLarge,
        kTopicCover,
        kTopicOriginal,
        kTopicCover280,
        kGif,
        kMP4,
        kTopicCategoryCover,
        kCommentImg,
        kCommentOriginImg,
        kChatLocalOriginImg,
        kChatImg360,
        kPicWithUri,
        kLinkPic228White,
        kVideo,
        kShareImg
    }

    PictureImpl(Type type, long j) {
        this.mType = type;
        this.mPicId = j;
    }

    public PictureImpl(String str, Type type, long j) {
        this.mType = type;
        this.mPicId = j;
        if (!TextUtils.isEmpty(str)) {
            String str2 = "file://";
            if (str.startsWith(str2)) {
                str = str.substring(str2.length());
            }
            this.mUri = str;
        }
    }

    private int a() {
        switch (this.mType) {
            case kShareImg:
            case kPicWithUri:
                return 0;
            case kAvatarOriginFemale:
            case kAvatarOriginMale:
                return cn.htjyb.c.a.e(BaseApplication.getAppContext().getApplicationContext());
            case kAvatarMale:
            case kAvatarFemale:
            case kCommentImg:
                return 200;
            case kPostPic228:
            case kLinkPic228White:
                return 300;
            case kPostPic480:
                return 2400;
            case kChatImg360:
                return 2400;
            case kPostPic600:
                return 2400;
            case kTopicCover:
            case kTopicCover280:
            case kChatLocalOriginImg:
            case kCommentOriginImg:
            case kPostPicLarge:
                return 10000;
            default:
                return 400;
        }
    }

    public String downloadUrl() {
        switch (this.mType) {
            case kShareImg:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/sharepost/pid/", this.mPicId, null);
            case kPicWithUri:
                return this.mUri;
            case kAvatarOriginFemale:
            case kAvatarOriginMale:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/account/avatar/id/", this.mPicId, "/sz/src");
            case kAvatarMale:
            case kAvatarFemale:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/account/avatar/id/", this.mPicId, null);
            case kPostPic228:
            case kLinkPic228White:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/228");
            case kPostPic480:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/480x270");
            case kChatImg360:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/360");
            case kPostPic600:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/600");
            case kTopicCover:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/topic/cover/id/", this.mPicId, null);
            case kTopicCover280:
            case kTopicOriginal:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/topic/cover/id/", this.mPicId, "/sz/280");
            case kChatLocalOriginImg:
                return "";
            case kCommentOriginImg:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/src");
            case kPostPicLarge:
            case kGif:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/src");
            case kCommentImg:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", this.mPicId, "/sz/228");
            case kVideo:
                return this.mUri;
            case kTopicCategoryCover:
                return cn.xiaochuankeji.tieba.background.utils.d.a.b("/static/images/cate/") + this.mPicId + ".png";
            case kMP4:
                return cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/mp4/id/", this.mPicId, "");
            default:
                return null;
        }
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    private static boolean a(String str) {
        return str.indexOf("http") != 0;
    }

    public static String getSavedName(String str) {
        return d.c(str).substring(0, 16);
    }

    private int b() {
        if (canDownload()) {
            return R.drawable.pic_default;
        }
        switch (this.mType) {
            case kAvatarOriginFemale:
                return R.drawable.default_avatar_female_large;
            case kAvatarOriginMale:
                return R.drawable.default_avatar_male_large;
            case kAvatarMale:
                return R.drawable.default_avatar_male;
            case kAvatarFemale:
                return R.drawable.default_avatar_female;
            case kLinkPic228White:
                return R.drawable.image_link_placeholder;
            case kTopicCover:
            case kTopicCover280:
            case kTopicOriginal:
                return R.drawable.default_topic_cover;
            default:
                return R.drawable.pic_default;
        }
    }

    public void registerPictureDownloadListener(a.a aVar) {
        if (this.b == null) {
            this.b = new HashSet();
        }
        this.b.add(aVar);
    }

    public void unregisterPictureDownloadListener(a.a aVar) {
        if (this.b != null && this.b.size() != 0) {
            this.b.remove(aVar);
        }
    }

    public void cancelDownload() {
        if (this.a != null) {
            this.a.a(false);
            this.a = null;
        }
        this.downloading = false;
    }

    public String cachePath() {
        if (this.mUri == null) {
            return a(this.mType, this.mPicId);
        }
        if (a(this.mUri)) {
            return this.mUri;
        }
        if (this.mType == Type.kPicWithUri) {
            return cn.xiaochuankeji.tieba.background.a.e().k() + d.c(this.mUri).substring(0, 16);
        }
        if (this.mType == Type.kVideo) {
            return q.a().b().c(this.mUri).getAbsolutePath();
        }
        return null;
    }

    private static String a(Type type) {
        switch (type) {
            case kShareImg:
                return cn.xiaochuankeji.tieba.background.a.e().l();
            case kAvatarOriginFemale:
            case kAvatarOriginMale:
                return cn.xiaochuankeji.tieba.background.a.e().b();
            case kAvatarMale:
            case kAvatarFemale:
                return cn.xiaochuankeji.tieba.background.a.e().c();
            case kPostPic228:
            case kLinkPic228White:
            case kCommentImg:
                return cn.xiaochuankeji.tieba.background.a.e().d();
            case kPostPic480:
                return cn.xiaochuankeji.tieba.background.a.e().e();
            case kChatImg360:
            case kChatLocalOriginImg:
                return cn.xiaochuankeji.tieba.background.a.e().o();
            case kPostPic600:
                return cn.xiaochuankeji.tieba.background.a.e().f();
            case kTopicCover:
                return cn.xiaochuankeji.tieba.background.a.e().m();
            case kTopicCover280:
            case kTopicOriginal:
                return cn.xiaochuankeji.tieba.background.a.e().n();
            case kCommentOriginImg:
            case kPostPicLarge:
                return cn.xiaochuankeji.tieba.background.a.e().g();
            case kGif:
                return cn.xiaochuankeji.tieba.background.a.e().h();
            case kTopicCategoryCover:
                return cn.xiaochuankeji.tieba.background.a.e().p();
            case kMP4:
                return cn.xiaochuankeji.tieba.background.a.e().i();
            default:
                return null;
        }
    }

    private static String a(Type type, long j) {
        String a = a(type);
        if (a == null) {
            return null;
        }
        String valueOf = String.valueOf(j);
        if (j < 10) {
            return a + j;
        }
        final File file = new File(a + valueOf.substring(valueOf.length() - 2, valueOf.length()));
        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable() {
            public void run() {
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        });
        a = file.getAbsolutePath() + "/" + j;
        if (type == Type.kMP4) {
            return a + ".mp4";
        }
        return a;
    }

    public Bitmap getPlaceholderBitmap() {
        return cn.xiaochuankeji.tieba.background.a.f().a(b());
    }

    public Bitmap getActualBitmap() {
        File localFile = getLocalFile();
        if (localFile != null) {
            return b.a(localFile.getPath(), a());
        }
        return null;
    }

    public long getPictureID() {
        return this.mPicId;
    }

    public void download(boolean z) {
        if (this.a != null) {
            com.izuiyou.a.a.b.e("mDownloadTask不为null");
        } else if (canDownload()) {
            String cachePath = cachePath();
            String downloadUrl = downloadUrl();
            cn.htjyb.netlib.b d = cn.xiaochuankeji.tieba.background.a.d();
            HashMap hashMap = new HashMap();
            hashMap.put("Request-Type", "image/*");
            this.a = new cn.htjyb.netlib.a(downloadUrl, hashMap, d, cachePath, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ PictureImpl a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                    this.a.downloading = false;
                    this.a.a.a(null);
                    this.a.a = null;
                    this.a.a(dVar.c.a, dVar.c.b, dVar.c.c());
                }
            });
            cn.xiaochuankeji.tieba.background.a.f().a(this.a, z);
            this.downloading = true;
        } else {
            new Handler().post(new Runnable(this) {
                final /* synthetic */ PictureImpl a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(false, 1, "不能下载本地图片");
                }
            });
        }
    }

    private void a(boolean z, int i, String str) {
        Iterator it = new ArrayList(this.b).iterator();
        while (it.hasNext()) {
            ((a.a) it.next()).a(this, z, i, str);
        }
    }

    public void download(boolean z, cn.htjyb.netlib.a.a aVar) {
        download(z);
        if (this.a != null) {
            this.a.a(aVar);
        }
    }

    public boolean canDownload() {
        return this.mPicId > 0 || ((Type.kPicWithUri == this.mType || Type.kVideo == this.mType) && !a(this.mUri));
    }

    public boolean hasLocalFile() {
        Object cachePath = cachePath();
        return !TextUtils.isEmpty(cachePath) && new File(cachePath).exists();
    }

    public File getLocalFile() {
        Object cachePath = cachePath();
        if (!TextUtils.isEmpty(cachePath)) {
            File file = new File(cachePath);
            if (file.exists()) {
                file.setLastModified(System.currentTimeMillis());
                return file;
            }
        }
        return null;
    }

    public boolean isDownloading() {
        return this.downloading;
    }

    public void setRotate(int i) {
        this.mRotateDegress = i;
    }

    public int getRotate() {
        return this.mRotateDegress;
    }

    public Enum getType() {
        return this.mType;
    }

    public String getUrl() {
        return this.mUri;
    }

    public String webpDownloadUrl() {
        String downloadUrl = downloadUrl();
        if (cn.xiaochuankeji.tieba.d.a.e() != cn.xiaochuankeji.tieba.d.a.i || TextUtils.isEmpty(downloadUrl)) {
            return downloadUrl;
        }
        return downloadUrl.replaceAll("/img/view/id/", "/img/webp/id/");
    }

    public static Bundle getPictureBundle(a aVar) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", aVar.getType());
        bundle.putLong("id", aVar.getPictureID());
        Object url = aVar.getUrl();
        if (!TextUtils.isEmpty(url)) {
            bundle.putString("url", url);
        }
        return bundle;
    }

    public static a buildPictureBy(Bundle bundle) {
        return new PictureImpl(bundle.getString("url"), (Type) bundle.getSerializable("type"), bundle.getLong("id"));
    }
}
