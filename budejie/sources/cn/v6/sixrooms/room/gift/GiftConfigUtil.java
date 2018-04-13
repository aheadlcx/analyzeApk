package cn.v6.sixrooms.room.gift;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.NumberBean;
import cn.v6.sixrooms.constants.CommonInts;
import java.util.ArrayList;
import java.util.List;

public class GiftConfigUtil {
    public static final String COOL_GIFT_TAG = "99";
    public static final String GIFT_RES_URL_FILE = "giftRes";
    public static final String GTYPE_FACE = "5";
    public static final String GTYPE_H5 = "3";
    public static final String GTYPE_H5_EXCLUSIVE = "4";
    public static final String GTYPE_ORDINARY = "1";
    public static final String GTYPE_SHINE = "2";
    public static final String H5_FILE = "h5gift";
    public static final String H5_KEY = "h5key";
    public static final String JUNIOR_GIFT_TAG = "1";
    public static final String LOAD_GIFT_ERROR = V6Coop.getInstance().getContext().getString(R.string.load_gift_error);
    public static final String OTHER_GIFT_TAG = "12";
    public static final String REPERTORY_GIFT_TAG = "11";
    public static final String SPECIAL_GIFT_TAG = "5";
    public static final String SUPER_GIRL_GIFT_TAG = "100";
    public static final String TAG = GiftConfigUtil.class.getSimpleName();

    public static List<NumberBean> getGiftNumList() {
        List<NumberBean> arrayList = new ArrayList();
        arrayList.add(new NumberBean(1, ""));
        arrayList.add(new NumberBean(5, ""));
        arrayList.add(new NumberBean(10, ""));
        arrayList.add(new NumberBean(50, ""));
        arrayList.add(new NumberBean(99, ""));
        arrayList.add(new NumberBean(100, ""));
        arrayList.add(new NumberBean(300, ""));
        arrayList.add(new NumberBean(520, ""));
        arrayList.add(new NumberBean(999, ""));
        arrayList.add(new NumberBean(1314, ""));
        arrayList.add(new NumberBean(2345, ""));
        arrayList.add(new NumberBean(3344, ""));
        arrayList.add(new NumberBean(5200, ""));
        arrayList.add(new NumberBean(CommonInts.USER_MANAGER_REQUEST_CODE, ""));
        return arrayList;
    }
}
