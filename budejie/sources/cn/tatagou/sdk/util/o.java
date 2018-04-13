package cn.tatagou.sdk.util;

import android.content.Context;
import android.util.Log;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.CateLimit;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.RcmmSpecial;
import cn.tatagou.sdk.pojo.Special;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class o {
    private static final String a = o.class.getSimpleName();

    public static String getSex() {
        if (!p.isEmpty(Config.getInstance().getSexList())) {
            String[] split = Config.getInstance().getSexList().split(",");
            if (split.length == 1) {
                Config.getInstance().setSex(split[0]);
                return split[0];
            }
        }
        return null;
    }

    public static boolean onRcmmSpecialCateSortDataReady(List<Special> list, RcmmSpecial rcmmSpecial) {
        if (list == null || rcmmSpecial == null) {
            return false;
        }
        isSexChange();
        if (p.isEmpty(Config.getInstance().getSex())) {
            if (p.isEmpty(getSex())) {
                return false;
            }
            AppHomeData.getInstance().setSexList(onFindSexList());
        } else if (AppHomeData.getInstance().getSexList() == null) {
            AppHomeData.getInstance().setSexList(onFindSexList());
        }
        List sexList = AppHomeData.getInstance().getSexList();
        if (sexList == null) {
            return false;
        }
        Map map;
        if (rcmmSpecial.getLv2Cate() == null || rcmmSpecial.getLv2Cate().getHitCates() == null || rcmmSpecial.getLv2Cate().getHitCates().size() <= 0) {
            map = null;
        } else {
            map = rcmmSpecial.getLv2Cate().getHitCates();
        }
        AppHomeData.getInstance().clearBrowserCateSpList();
        AppHomeData.getInstance().clearUnBrowserCateSpList();
        for (int i = 0; i < list.size(); i++) {
            Special special = (Special) list.get(i);
            if (special != null && sexList.indexOf(special.getSex()) >= 0) {
                special.setDefaultSort(i);
                String str = special.getItem() != null ? special.getItem().getgSubCat() : special.getgSubCat();
                if (map == null || p.isEmpty((String) map.get(str))) {
                    if (TtgSDK.isDebug) {
                        special.setTestText(special.getSex() + ", dft:" + special.getDefaultSort() + ", lv2:" + str);
                    }
                    AppHomeData.getInstance().addUnBrowserCateSpList(special);
                } else {
                    if (TtgSDK.isDebug) {
                        special.setTestText("类目推荐, " + special.getSex() + ", dft:" + special.getDefaultSort() + ", lv2:" + str + ", lv2Pv:" + ((String) map.get(str)));
                    }
                    AppHomeData.getInstance().addBrowserCateSpList(special);
                }
            }
        }
        return true;
    }

    public static List<Special> onRcmmSpDataReady(Context context) {
        List browserCateSpList = AppHomeData.getInstance().getBrowserCateSpList();
        List unBrowserCateSpList = AppHomeData.getInstance().getUnBrowserCateSpList();
        int unBrowserCateSpSize = AppHomeData.getInstance().getUnBrowserCateSpSize();
        int browserCateSpSize = AppHomeData.getInstance().getBrowserCateSpSize();
        if (unBrowserCateSpSize > 0 && AppHomeData.getInstance().getSpHistorySize() > 0) {
            unBrowserCateSpList.removeAll(AppHomeData.getInstance().getSpHistorySet());
            AppHomeData.getInstance().setUnBrowserCateSpList(unBrowserCateSpList);
            unBrowserCateSpSize = AppHomeData.getInstance().getUnBrowserCateSpSize();
        }
        if (browserCateSpSize > 0 && AppHomeData.getInstance().getSpHistorySize() > 0) {
            browserCateSpList.removeAll(AppHomeData.getInstance().getSpHistorySet());
            AppHomeData.getInstance().setBrowserCateSpList(browserCateSpList);
            browserCateSpSize = AppHomeData.getInstance().getBrowserCateSpSize();
        }
        if (browserCateSpSize + unBrowserCateSpSize < Config.getInstance().getRcmmNum()) {
            return null;
        }
        List<Special> subList;
        if (unBrowserCateSpSize == 0) {
            subList = browserCateSpList.subList(0, Config.getInstance().getRcmmNum());
            AppHomeData.getInstance().addAllSpHistorySet(subList.subList(0, 3));
            return subList;
        } else if (browserCateSpSize == 0) {
            subList = unBrowserCateSpList.subList(0, Config.getInstance().getRcmmNum());
            AppHomeData.getInstance().addAllSpHistorySet(subList.subList(0, 3));
            return subList;
        } else {
            int[] spLimitNum = getSpLimitNum();
            Collection browserCateSpList2 = getBrowserCateSpList(browserCateSpList, spLimitNum[0], spLimitNum[1]);
            Collection linkedList = new LinkedList();
            browserCateSpSize = (browserCateSpList2 == null || browserCateSpList2.size() <= 0) ? 0 : browserCateSpList2.size();
            unBrowserCateSpSize = Config.getInstance().getRcmmNum() - browserCateSpSize;
            if (unBrowserCateSpSize < 0) {
                unBrowserCateSpSize = 0;
            }
            int unBrowserCateSpSize2 = AppHomeData.getInstance().getUnBrowserCateSpSize();
            if (unBrowserCateSpSize2 > 0) {
                if (unBrowserCateSpSize > unBrowserCateSpSize2) {
                    unBrowserCateSpSize = unBrowserCateSpSize2;
                }
                linkedList.addAll(unBrowserCateSpList.subList(0, unBrowserCateSpSize));
            }
            List<Special> linkedList2 = new LinkedList();
            unBrowserCateSpSize2 = linkedList.size() + browserCateSpSize;
            Log.d(a, "onRcmmSpDataReady 最后总长度 totalRcmmSpSize: " + unBrowserCateSpSize2);
            if (unBrowserCateSpSize2 == Config.getInstance().getRcmmNum()) {
                if (linkedList.size() > 0) {
                    linkedList2.addAll(browserCateSpList2);
                }
                linkedList2.addAll(linkedList);
                n.sort(linkedList2, false);
                AppHomeData.getInstance().addAllSpHistorySet(linkedList2.subList(0, 3));
                return linkedList2;
            } else if (unBrowserCateSpSize2 >= Config.getInstance().getRcmmNum() || AppHomeData.getInstance().getBrowserCateSpSize() < Config.getInstance().getRcmmNum()) {
                return linkedList2;
            } else {
                LinkedList linkedList3 = new LinkedList();
                linkedList3.removeAll(browserCateSpList2);
                browserCateSpSize = Config.getInstance().getRcmmNum() - unBrowserCateSpSize2;
                if (browserCateSpSize > linkedList3.size()) {
                    browserCateSpSize = linkedList3.size();
                }
                if (unBrowserCateSpSize2 + browserCateSpSize != Config.getInstance().getRcmmNum()) {
                    return null;
                }
                linkedList2.addAll(browserCateSpList2);
                if (linkedList.size() > 0) {
                    linkedList2.addAll(linkedList);
                }
                linkedList2.addAll(linkedList3.subList(0, browserCateSpSize));
                n.sort(linkedList2, false);
                AppHomeData.getInstance().addAllSpHistorySet(linkedList2.subList(0, 3));
                return linkedList2;
            }
        }
    }

    public static int[] getSpLimitNum() {
        int i;
        int i2;
        if (AppHomeData.getInstance().getRcmmParam() == null || AppHomeData.getInstance().getRcmmParam().getRcmmSpecials() == null || AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getCateLimit() == null) {
            i = 0;
            i2 = 0;
        } else {
            CateLimit cateLimit = AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getCateLimit();
            i2 = p.str2Int(cateLimit.getSingleLimit()) / 10;
            i = p.str2Int(cateLimit.getMultiLimit()) / 10;
        }
        return new int[]{i2, i};
    }

    public static LinkedList<Special> getBrowserCateSpList(List<Special> list, int i, int i2) {
        LinkedList<Special> linkedList = new LinkedList();
        if (list != null && list.size() > 0 && i2 > 0) {
            HashMap hashMap = new HashMap();
            for (Special special : list) {
                if (special != null) {
                    String str = special.getItem() != null ? special.getItem().getgSubCat() : special.getgSubCat();
                    if (linkedList.size() <= i2 && ((hashMap.get(str) == null || ((Integer) hashMap.get(str)).intValue() < i) && !p.isEmpty(str))) {
                        Integer num = (Integer) hashMap.get(str);
                        if (num == null || num.intValue() == 0) {
                            hashMap.put(str, Integer.valueOf(1));
                        } else {
                            hashMap.put(str, Integer.valueOf(num.intValue() + 1));
                        }
                        linkedList.add(special);
                        if (linkedList.size() == i2) {
                            break;
                        }
                    }
                }
            }
        }
        return linkedList;
    }

    public static List<String> onFindSexList() {
        String sex = Config.getInstance().getSex();
        List<String> linkedList = new LinkedList();
        if ("M".equals(sex)) {
            linkedList.addAll(Arrays.asList(new String[]{"M", "A"}));
            return linkedList;
        } else if ("F".equals(sex)) {
            linkedList.addAll(Arrays.asList(new String[]{"F", "A"}));
            return linkedList;
        } else if (!"L".equals(sex)) {
            return null;
        } else {
            linkedList.addAll(Arrays.asList(new String[]{"F", "A", "C"}));
            return linkedList;
        }
    }

    public static boolean isSexChange() {
        String sex = Config.getInstance().getSex();
        if (p.isEmpty(sex) || sex.equals(AppHomeData.getInstance().getSex())) {
            return false;
        }
        AppHomeData.getInstance().setSexList(onFindSexList());
        Log.d(a, "onRcmmSpecialSortDataReady isSexChange 改变了");
        return true;
    }
}
