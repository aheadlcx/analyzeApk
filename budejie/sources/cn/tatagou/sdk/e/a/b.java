package cn.tatagou.sdk.e.a;

import android.os.Build;
import android.os.Build.VERSION;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.e.a;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.util.p;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;

public class b {
    public static void spStatEvent(String str, String str2, String str3) {
        a aVar = new a("SP");
        aVar.addProperty("spId", str);
        aVar.addProperty("marker", str2);
        aVar.addProperty(UserTrackerConstants.FROM, str3);
        a.reportEvent(aVar);
    }

    public static void ttgStatEvent(String str) {
        if (TtgSDK.getContext() != null) {
            a.init(TtgSDK.getContext()).setPid(String.valueOf(str));
        }
        a aVar = new a("TTG");
        aVar.addProperty(UserTrackerConstants.FROM, str);
        a.reportEvent(aVar);
    }

    public static void homeShowStatEvent(String str) {
        if (TtgSDK.getContext() != null) {
            a.init(TtgSDK.getContext()).setPid(String.valueOf(str));
        }
        a aVar = new a("HOMESHOW");
        aVar.addProperty(UserTrackerConstants.FROM, str);
        a.reportEvent(aVar);
    }

    public static void cateStatEvent(String str) {
        a aVar = new a("CATE");
        aVar.addProperty("appCat", str);
        a.reportEvent(aVar);
    }

    public static void mineStatEvent(String str) {
        a aVar = new a("MINE");
        aVar.addProperty(UserTrackerConstants.FROM, str);
        a.reportEvent(aVar);
    }

    public static void cartStatEvent(String str) {
        a aVar = new a("CART");
        aVar.addProperty(UserTrackerConstants.FROM, str);
        a.reportEvent(aVar);
    }

    public static void myOrdersStatEvent(String str) {
        a aVar = new a("MYORDERS");
        aVar.addProperty(UserTrackerConstants.FROM, str);
        a.reportEvent(aVar);
    }

    public static void pullStatEvent(String str) {
        a aVar = new a("PULL");
        aVar.addProperty("appCat", str);
        a.reportEvent(aVar);
    }

    public static void homeNumStatEvent(String str) {
        a aVar = new a("HOMENUM");
        aVar.addProperty("appCat", str);
        a.reportEvent(aVar);
    }

    public static void rrStatEvent(String str, String str2) {
        a aVar = new a("RR");
        aVar.addProperty("appCat", str);
        aVar.addProperty(UserTrackerConstants.FROM, str2);
        a.reportEvent(aVar);
    }

    public static void searchStatEvent(String str, String str2) {
        a aVar = new a("SEARCH");
        aVar.addProperty("word", str);
        aVar.addProperty("type", str2);
        a.reportEvent(aVar);
    }

    public static void itemStatEvent(Item item, String str, String str2, String str3) {
        a aVar = new a("ITEM");
        aVar.addProperty("tbId", item.getTaobaoId());
        aVar.addProperty("spId", str);
        aVar.addProperty(UserTrackerConstants.FROM, str3);
        aVar.addProperty("gId", item.getId());
        aVar.addProperty("marker", item.getMarker());
        aVar.addProperty("appCat", str2);
        aVar.addProperty("gCat", item.getgCat());
        aVar.addProperty("gSubCat", item.getgSubCat());
        a.reportEvent(aVar);
    }

    public static void launchStatEvent() {
        a aVar = new a("LAUNCH");
        aVar.addProperty("pType", Build.MODEL);
        aVar.addProperty("sysInfo", "SDK版本:" + VERSION.SDK_INT + ",系统版本:" + VERSION.RELEASE);
        int[] windowWidthAndHeight = p.getWindowWidthAndHeight(TtgSDK.getContext());
        aVar.addProperty("screenSize", windowWidthAndHeight[0] + "_" + windowWidthAndHeight[1]);
        a.reportEvent(aVar);
    }
}
