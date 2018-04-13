package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.bean.Badge;
import cn.v6.sixrooms.engine.ReadBadgeEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PropParseUtil {
    private static ReadBadgeEngine a;
    private static List<String> b = Arrays.asList(new String[]{"7569", "7570"});
    private static List<String> c = Arrays.asList(new String[]{"7116", "7117", "7118", "7119", "7120"});
    private static Map<String, Integer> d = new v();
    public static Map<String, Integer> mvpConfig = new w();

    public static boolean isFlashStar(List<String> list) {
        if (list == null) {
            return false;
        }
        for (String str : list) {
            for (String equals : c) {
                if (equals.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> parsePropResList(List<String> list) {
        List<String> list2 = null;
        if (list != null) {
            for (String str : list) {
                Integer num = (Integer) d.get(str);
                String str2 = "res://cn.v6.sixrooms/" + num;
                LogUtils.e("test", "parsePropResList   " + str2);
                if (num != null) {
                    List<String> arrayList;
                    if (list2 == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list2;
                    }
                    if (b.contains(str)) {
                        arrayList.add(0, str2);
                        list2 = arrayList;
                    } else {
                        arrayList.add(str2);
                        list2 = arrayList;
                    }
                }
            }
        }
        return list2;
    }

    public static List<Integer> parsePropDrawbleList(List<String> list) {
        List<Integer> list2 = null;
        if (list != null) {
            for (String str : list) {
                Integer num = (Integer) d.get(str);
                if (num != null) {
                    if (list2 == null) {
                        list2 = new ArrayList();
                    }
                    if (b.contains(str)) {
                        list2.add(0, num);
                    } else {
                        list2.add(num);
                    }
                }
            }
        }
        return list2;
    }

    public static List<String> parsePropImgUrlList(List<String> list) {
        List<String> list2 = null;
        if (list != null) {
            if (a == null) {
                a = new ReadBadgeEngine();
            }
            List<Badge> badgeList = a.getBadgeList();
            if (badgeList != null) {
                for (String str : list) {
                    for (Badge badge : badgeList) {
                        if (str.equals(badge.getId())) {
                            if (list2 == null) {
                                list2 = new ArrayList();
                            }
                            list2.add(badge.getSpic().getImg());
                        }
                    }
                }
            }
        }
        return list2;
    }

    public static List<String> parseVipResList(List<String> list) {
        List<String> list2 = null;
        if (list != null) {
            for (String str : list) {
                List<String> arrayList;
                Integer num = (Integer) mvpConfig.get(str);
                String str2 = "res://cn.v6.sixrooms/" + num;
                LogUtils.e("test", "parseVipResList   " + str2);
                if (num != null) {
                    if (list2 == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list2;
                    }
                    arrayList.add(str2);
                } else {
                    arrayList = list2;
                }
                list2 = arrayList;
            }
        }
        return list2;
    }

    public static List<String> parseVipAndBadge(List<String> list) {
        if (list == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        Collection parseVipResList = parseVipResList(list);
        Collection parsePropImgUrlList = parsePropImgUrlList(list);
        if (parsePropImgUrlList != null) {
            arrayList.addAll(parsePropImgUrlList);
        }
        if (parseVipResList == null) {
            return arrayList;
        }
        arrayList.addAll(parseVipResList);
        return arrayList;
    }
}
