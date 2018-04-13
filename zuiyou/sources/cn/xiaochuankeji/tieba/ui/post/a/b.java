package cn.xiaochuankeji.tieba.ui.post.a;

import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Advert;
import com.qq.e.ads.nativ.NativeMediaADData;

public class b {
    public NativeMediaADData a;
    public AdvertismentBean b;
    public Advert c;

    public b(NativeMediaADData nativeMediaADData) {
        this.a = nativeMediaADData;
    }

    public b(AdvertismentBean advertismentBean) {
        this.b = advertismentBean;
    }

    public b(Advert advert) {
        this.c = advert;
    }
}
