package cn.v6.sixrooms.bean;

import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

public class WrapGiftItem {
    private ArrayList<GiftItemBean> giftItemBeans;
    private String tag;
    private String tagName;

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public ArrayList<GiftItemBean> getGiftItemBeans() {
        return this.giftItemBeans;
    }

    public void setGiftItemBeans(ArrayList<GiftItemBean> arrayList) {
        this.giftItemBeans = arrayList;
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
        return str;
    }
}
