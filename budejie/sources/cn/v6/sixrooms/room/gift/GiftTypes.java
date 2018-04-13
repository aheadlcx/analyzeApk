package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class GiftTypes implements Cloneable {
    private List<Gift> giftTypeAdvanced;
    private List<Gift> giftTypeDance;
    private List<Gift> giftTypeGuard;
    private List<Gift> giftTypeInteresting;
    private List<Gift> giftTypeIntermediate;
    private List<Gift> giftTypeJunior;
    private List<Gift> giftTypeLuxury;
    private List<Gift> giftTypeNobility;
    private List<Gift> giftTypeOther;
    private List<Gift> giftTypeRoom;
    private List<Gift> giftTypeSpecial;
    private List<Gift> giftTypeStock;
    private List<Gift> giftTypeSuite;

    public static class WrapGiftType implements Cloneable {
        private String tag;
        private String tagName;
        private List<Gift> typeGiftList;

        public String getTag() {
            return this.tag;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public String getTagName() {
            String str = "";
            if (TextUtils.isEmpty(this.tag)) {
                return str;
            }
            if ("1".equals(this.tag)) {
                return "初级";
            }
            if ("2".equals(this.tag)) {
                return "中级";
            }
            if ("3".equals(this.tag)) {
                return "高级";
            }
            if ("4".equals(this.tag)) {
                return "豪华";
            }
            if ("5".equals(this.tag)) {
                return "特殊";
            }
            if ("6".equals(this.tag)) {
                return "趣味";
            }
            if (AlibcJsResult.CLOSED.equals(this.tag)) {
                return "伴舞";
            }
            if ("8".equals(this.tag)) {
                return "贵族";
            }
            if ("9".equals(this.tag)) {
                return "套礼";
            }
            if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(this.tag)) {
                return "守护";
            }
            if ("11".equals(this.tag)) {
                return "库存";
            }
            if ("99".equals(this.tag)) {
                return "炫动";
            }
            if (GiftConfigUtil.SUPER_GIRL_GIFT_TAG.equals(this.tag)) {
                return "专属";
            }
            return str;
        }

        public List<Gift> getTypeGiftList() {
            return this.typeGiftList;
        }

        public void setTypeGiftList(List<Gift> list) {
            this.typeGiftList = list;
        }

        public WrapGiftType clone() throws CloneNotSupportedException {
            WrapGiftType wrapGiftType = (WrapGiftType) super.clone();
            List arrayList = new ArrayList();
            for (Gift clone : wrapGiftType.typeGiftList) {
                arrayList.add(clone.clone());
            }
            wrapGiftType.typeGiftList = arrayList;
            return wrapGiftType;
        }
    }

    public List<Gift> getGiftTypeAdvanced() {
        return this.giftTypeAdvanced;
    }

    public List<Gift> getGiftTypeDance() {
        return this.giftTypeDance;
    }

    public List<Gift> getGiftTypeGuard() {
        return this.giftTypeGuard;
    }

    public List<Gift> getGiftTypeInteresting() {
        return this.giftTypeInteresting;
    }

    public List<Gift> getGiftTypeIntermediate() {
        return this.giftTypeIntermediate;
    }

    public List<Gift> getGiftTypeJunior() {
        return this.giftTypeJunior;
    }

    public List<Gift> getGiftTypeLuxury() {
        return this.giftTypeLuxury;
    }

    public List<Gift> getGiftTypeNobility() {
        return this.giftTypeNobility;
    }

    public List<Gift> getGiftTypeOther() {
        return this.giftTypeOther;
    }

    public List<Gift> getGiftTypeRoom() {
        return this.giftTypeRoom;
    }

    public List<Gift> getGiftTypeSpecial() {
        return this.giftTypeSpecial;
    }

    public List<Gift> getGiftTypeStock() {
        return this.giftTypeStock;
    }

    public List<Gift> getGiftTypeSuite() {
        return this.giftTypeSuite;
    }

    public GiftTypes clone() throws CloneNotSupportedException {
        GiftTypes giftTypes = (GiftTypes) super.clone();
        giftTypes.giftTypeAdvanced = cloneGiftList(giftTypes.giftTypeAdvanced);
        giftTypes.giftTypeDance = cloneGiftList(giftTypes.giftTypeDance);
        giftTypes.giftTypeGuard = cloneGiftList(giftTypes.giftTypeGuard);
        giftTypes.giftTypeInteresting = cloneGiftList(giftTypes.giftTypeInteresting);
        giftTypes.giftTypeIntermediate = cloneGiftList(giftTypes.giftTypeIntermediate);
        giftTypes.giftTypeJunior = cloneGiftList(giftTypes.giftTypeJunior);
        giftTypes.giftTypeLuxury = cloneGiftList(giftTypes.giftTypeLuxury);
        giftTypes.giftTypeNobility = cloneGiftList(giftTypes.giftTypeNobility);
        giftTypes.giftTypeOther = cloneGiftList(giftTypes.giftTypeOther);
        giftTypes.giftTypeRoom = cloneGiftList(giftTypes.giftTypeRoom);
        giftTypes.giftTypeSpecial = cloneGiftList(giftTypes.giftTypeSpecial);
        giftTypes.giftTypeStock = cloneGiftList(giftTypes.giftTypeStock);
        giftTypes.giftTypeSuite = cloneGiftList(giftTypes.giftTypeSuite);
        return giftTypes;
    }

    private List<Gift> cloneGiftList(List<Gift> list) {
        List<Gift> arrayList = new ArrayList();
        for (Gift gift : list) {
            if (gift != null) {
                try {
                    arrayList.add(gift.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }
}
