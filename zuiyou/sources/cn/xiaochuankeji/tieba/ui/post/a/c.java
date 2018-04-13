package cn.xiaochuankeji.tieba.ui.post.a;

import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.ad.AdAuditBean;
import cn.xiaochuankeji.tieba.background.ad.CheckAdBean;
import cn.xiaochuankeji.tieba.background.ad.PostAdExtraInfo;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import com.izuiyou.a.a.b;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaAD.NativeMediaADListener;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.j;

public class c {
    private static c a;
    private static String b;
    private static String c;
    private a d;
    private NativeMediaAD e;
    private Map<String, NativeMediaADData> f = new HashMap();
    private PostAdExtraInfo g;
    private NativeMediaADListener h = new NativeMediaADListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onADLoaded(List<NativeMediaADData> list) {
            b.b("load ads size:" + list.size());
            if (list != null && list.size() > 0) {
                NativeMediaADData nativeMediaADData = (NativeMediaADData) list.get(0);
                a.a().a(nativeMediaADData, this.a.g);
                if (nativeMediaADData.getAdPatternType() != 2) {
                    a.a().a(1003, nativeMediaADData, this.a.g);
                } else {
                    this.a.b(nativeMediaADData);
                }
            }
        }

        public void onNoAD(AdError adError) {
            b.d("广告加载失败，错误码：%d，错误信息：%s", Integer.valueOf(adError.getErrorCode()), adError.getErrorMsg());
            a.a().a(this.a.g);
        }

        public void onADStatusChanged(NativeMediaADData nativeMediaADData) {
            if (this.a.d != null) {
                this.a.d.a(nativeMediaADData);
            }
        }

        public void onADError(NativeMediaADData nativeMediaADData, AdError adError) {
            b.e(nativeMediaADData.getTitle() + " onADError, error code: " + adError.getErrorCode() + ", error msg: " + adError.getErrorMsg());
            a.a().a(this.a.g);
        }

        public void onADVideoLoaded(NativeMediaADData nativeMediaADData) {
            a.a().a(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, nativeMediaADData, this.a.g);
        }

        public void onADExposure(NativeMediaADData nativeMediaADData) {
        }

        public void onADClicked(NativeMediaADData nativeMediaADData) {
            int i = 0;
            if (nativeMediaADData.isAPP()) {
                switch (nativeMediaADData.getAPPStatus()) {
                    case 0:
                        i = PointerIconCompat.TYPE_CROSSHAIR;
                        break;
                    case 1:
                        i = Packet.CLIENTVER_FIELD_NUMBER;
                        break;
                    case 2:
                        i = PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW;
                        break;
                    case 4:
                        break;
                    case 8:
                        i = PointerIconCompat.TYPE_TEXT;
                        break;
                    case 16:
                        i = PointerIconCompat.TYPE_TEXT;
                        break;
                    default:
                        break;
                }
            }
            i = 1006;
            a.a().a(i, nativeMediaADData, this.a.g);
        }
    };

    public interface a {
        void a(NativeMediaADData nativeMediaADData);

        void a(NativeMediaADData nativeMediaADData, PostAdExtraInfo postAdExtraInfo, int i);
    }

    private c() {
    }

    public static c a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    public void a(@NonNull PostAdExtraInfo postAdExtraInfo) {
        Object obj = 1;
        this.g = postAdExtraInfo;
        Object obj2 = null;
        if (postAdExtraInfo != null && postAdExtraInfo.source != null) {
            if (!postAdExtraInfo.source.appId.equals(b)) {
                b = postAdExtraInfo.source.appId;
                obj2 = 1;
            }
            if (!postAdExtraInfo.source.slotId.equals(c)) {
                c = postAdExtraInfo.source.slotId;
                obj2 = 1;
            }
            if (TextUtils.isEmpty(b)) {
                b = "1106701465";
                obj2 = 1;
            }
            if (TextUtils.isEmpty(c)) {
                c = "4010937167329236";
            } else {
                obj = obj2;
            }
            if (obj != null) {
                this.e = new NativeMediaAD(BaseApplication.getAppContext(), b, c, this.h);
                this.e.setBrowserType(BrowserType.Inner);
                this.e.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.Default);
            }
        }
    }

    public void a(int i) {
        if (this.e != null) {
            try {
                this.e.loadAD(i);
            } catch (Exception e) {
                b.e("加载广告失败：" + e.getStackTrace());
            }
        }
    }

    public String a(NativeMediaADData nativeMediaADData) {
        String str = "浏览";
        if (nativeMediaADData == null) {
            return str;
        }
        if (!nativeMediaADData.isAPP()) {
            return "浏览";
        }
        switch (nativeMediaADData.getAPPStatus()) {
            case 0:
                return "下载";
            case 1:
                return "启动";
            case 2:
                return "更新";
            case 4:
                return nativeMediaADData.getProgress() + "%";
            case 8:
                return "安装";
            case 16:
                return "重新下载";
            default:
                return str;
        }
    }

    public void b(final NativeMediaADData nativeMediaADData) {
        cn.xiaochuankeji.tieba.api.ad.a aVar = new cn.xiaochuankeji.tieba.api.ad.a();
        AdAuditBean adAuditBean = new AdAuditBean();
        adAuditBean.adDesc = nativeMediaADData.getDesc();
        adAuditBean.adTitle = nativeMediaADData.getTitle();
        adAuditBean.adtype = "gdt";
        if (this.g != null) {
            adAuditBean.adtype = this.g.source.type;
        }
        adAuditBean.imageUrl = nativeMediaADData.getImgUrl();
        adAuditBean.slotId = c;
        adAuditBean.appId = b;
        aVar.a(adAuditBean).a(rx.a.b.a.a()).b(new j<CheckAdBean>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onNext(Object obj) {
                a((CheckAdBean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(CheckAdBean checkAdBean) {
                if (this.b.d != null && checkAdBean.status == 1) {
                    if (nativeMediaADData != null && nativeMediaADData.getAdPatternType() == 2) {
                        nativeMediaADData.preLoadVideo();
                    }
                    if (this.b.d != null) {
                        this.b.d.a(nativeMediaADData, this.b.g, this.b.g == null ? 3 : this.b.g.displayPosition);
                        this.b.f.put(nativeMediaADData.getImgUrl(), nativeMediaADData);
                    }
                }
            }
        });
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
