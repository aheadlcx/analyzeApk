package cn.xiaochuankeji.tieba.background.ad;

import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import com.qq.e.ads.nativ.NativeMediaADData;

public class GDTAdvertisment extends AbstractPost {
    public PostAdExtraInfo extraInfo;
    public NativeMediaADData mAD;

    public int classType() {
        return 4;
    }

    public long getMemberId() {
        return 0;
    }

    public void setFollowStatus(int i) {
    }

    public boolean equals(Object obj) {
        if (obj instanceof GDTAdvertisment) {
            return ((GDTAdvertisment) obj).mAD.equalsAdData(this.mAD);
        }
        return false;
    }
}
