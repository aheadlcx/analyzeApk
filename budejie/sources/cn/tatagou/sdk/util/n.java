package cn.tatagou.sdk.util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import cn.tatagou.sdk.adapter.h;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.b.e;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.HomeData;
import cn.tatagou.sdk.pojo.Special;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class n {
    public static void onSpecialDataReady(String str, List<Special> list, List<Special> list2) {
        if (list.size() > 0) {
            list.clear();
        }
        if ("1".equals(str)) {
            list.addAll(list2);
            return;
        }
        for (int i = 0; i < list2.size(); i++) {
            Special special = (Special) list2.get(i);
            if (!p.isEmpty(str) && str.equals(special.getSpecialCateId())) {
                list.add(special);
            }
        }
    }

    public static void onSysCfgShow(String str, TextView textView) {
        try {
            Config instance = Config.getInstance();
            if ("1".equals(str)) {
                textView.setText(!p.isEmpty(instance.getSpecialNoMoreHint()) ? instance.getSpecialNoMoreHint().replace("\\n", "\n") : "亲!今天的商品已逛完欢迎明天10点关注上新");
            } else {
                textView.setText(!p.isEmpty(instance.getCatNoMoreHint()) ? instance.getCatNoMoreHint().replace("\\n", "\n") : "亲!今天的商品已逛完欢迎明天10点关注上新");
            }
        } catch (Throwable e) {
            Log.e("TTG", "otherInformation gson error", e);
        }
    }

    private static String a() {
        int i;
        String multiLimit;
        int i2 = 0;
        String str = "";
        if (!(AppHomeData.getInstance().getRcmmParam() == null || AppHomeData.getInstance().getRcmmParam().getRcmmSpecials() == null)) {
            if (!(AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getLv2Cate() == null || AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getLv2Cate().getHitCates() == null)) {
                i2 = AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getLv2Cate().getHitCates().size();
            }
            if (AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getCateLimit() != null) {
                i = i2;
                multiLimit = AppHomeData.getInstance().getRcmmParam().getRcmmSpecials().getCateLimit().getMultiLimit();
                return "multiLimit:" + multiLimit + ", lv2: " + i + ", ";
            }
        }
        String str2 = str;
        i = i2;
        multiLimit = str2;
        return "multiLimit:" + multiLimit + ", lv2: " + i + ", ";
    }

    public static String getSpecialTopHint(int i) {
        String a = a();
        if (TtgSDK.isDebug) {
            if (i > 0 && !p.isEmpty(Config.getInstance().getRmbgn())) {
                return a + Config.getInstance().getRmbgn().replace("{rcmmCount}", String.valueOf(i));
            }
            return a + (!p.isEmpty(Config.getInstance().getSpecialTopHint()) ? Config.getInstance().getSpecialTopHint() : "每天百款新品，10点更新");
        } else if (i <= 0 || p.isEmpty(Config.getInstance().getRmbgn())) {
            return !p.isEmpty(Config.getInstance().getSpecialTopHint()) ? Config.getInstance().getSpecialTopHint() : "每天百款新品，10点更新";
        } else {
            return Config.getInstance().getRmbgn().replace("{rcmmCount}", String.valueOf(i));
        }
    }

    public static String getRmFresh(String str, String str2) {
        return p.isEmpty(Config.getInstance().getRmFresh()) ? str : Config.getInstance().getRmFresh() + "\t" + str2;
    }

    public static String getRmEnd(String str) {
        return p.isEmpty(Config.getInstance().getRmEnd()) ? str : Config.getInstance().getRmEnd();
    }

    public static long onRefreshTimeReady(long j) {
        long str2Long = p.str2Long(Config.getInstance().getTimeForNewItems());
        long j2 = (str2Long - j) / 60;
        if (str2Long - j <= 0 || j2 >= 10) {
            return 0;
        }
        return ((((long) new Random().nextInt(3)) + j2) * 60) * 1000;
    }

    public static boolean isShowFailLayout(HomeData homeData) {
        if (homeData == null || ((homeData.getBannerSpecialList() == null || homeData.getBannerSpecialList().size() <= 0) && ((homeData.getCateSpecialList() == null || homeData.getCateSpecialList().size() <= 0) && (homeData.getNormalSpecialList() == null || homeData.getNormalSpecialList().size() <= 0)))) {
            return false;
        }
        return true;
    }

    public static int getNumColumns(List<Special> list) {
        if (list == null || list.size() == 4) {
            return 4;
        }
        if (list.size() >= 8 && list.size() <= 9) {
            return 4;
        }
        if ((list.size() < 5 || list.size() > 7) && list.size() < 10) {
            return 4;
        }
        return 5;
    }

    public static void sort(List<Special> list, final boolean z) {
        Collections.sort(list, new Comparator<Special>() {
            public int compare(Special special, Special special2) {
                Integer valueOf = Integer.valueOf(special.getDefaultSort());
                Integer valueOf2 = Integer.valueOf(special2.getDefaultSort());
                if (z) {
                    return valueOf2.compareTo(valueOf);
                }
                return valueOf.compareTo(valueOf2);
            }
        });
    }

    public static void onUnRcmmSpDataReady(Context context, List<Special> list) {
        Collection querySpId = e.getInstance().querySpId(context);
        if (list != null && querySpId != null && querySpId.size() != 0) {
            List<String> linkedList = new LinkedList();
            for (Special special : list) {
                if (!(special == null || p.isEmpty(special.getId()))) {
                    linkedList.add(special.getId());
                }
            }
            linkedList.retainAll(querySpId);
            for (String special2 : linkedList) {
                list.remove(new Special(special2));
            }
            Log.d("TTG", "onRcmmDataReady 去重后: " + list.size());
        }
    }

    public static void saveSpecialData(Context context) {
        if (AppHomeData.getInstance().getSpHistorySet() != null && AppHomeData.getInstance().getSpHistorySet().size() > 0) {
            Set hashSet = new HashSet();
            hashSet.addAll(AppHomeData.getInstance().getSpHistorySet());
            if (hashSet.size() > 0) {
                List arrayList = new ArrayList();
                arrayList.addAll(AppHomeData.getInstance().getSpHistorySet());
                onUnRcmmSpDataReady(context, arrayList);
                if (arrayList.size() > 0) {
                    e.getInstance().insertSp(context, arrayList);
                }
            }
        }
    }

    public static void setSpHintText(h hVar, boolean z, int i) {
        if (hVar != null) {
            hVar.setSpHintText(z, getSpecialTopHint(i));
        }
    }

    public static boolean onSpecialListDataReady(List<Special> list, List<Special> list2, List<Special> list3, String str, String str2) {
        if (list2.size() > 0) {
            list2.clear();
        }
        if ("1".equals(str)) {
            boolean z;
            if (p.isEmpty(str2)) {
                z = false;
            } else {
                z = onFindSpExistList(list, list3, str2);
            }
            list2.addAll(list);
            return z;
        }
        for (Special special : list) {
            if (!p.isEmpty(str) && str.equals(special.getSpecialCateId())) {
                list2.add(special);
            }
        }
        return false;
    }

    public static boolean onFindSpExistList(List<Special> list, List<Special> list2, String str) {
        if (list2 == null || list2.size() == 0) {
            return onFindSpecialTopData(list, str);
        }
        boolean isSpExistList = isSpExistList(list2, str);
        if (isSpExistList) {
            return isSpExistList;
        }
        int indexOf = list.indexOf(new Special(str));
        if (indexOf < 0) {
            return isSpExistList;
        }
        list2.add(0, (Special) list.get(indexOf));
        return true;
    }

    public static boolean isSpExistList(List<Special> list, String str) {
        int indexOf = list.indexOf(new Special(str));
        if (indexOf <= 0) {
            return false;
        }
        Special special = (Special) list.get(indexOf);
        list.remove(indexOf);
        list.add(0, special);
        return true;
    }

    public static boolean onFindSpecialTopData(List<Special> list, String str) {
        if (list.size() <= 1) {
            return true;
        }
        boolean z;
        int indexOf = list.indexOf(new Special(str));
        if (indexOf > 0) {
            Special special = (Special) list.get(indexOf);
            list.remove(special);
            list.add(0, special);
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
