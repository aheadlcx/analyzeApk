package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.CarBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;

public class DrawableResourceUtils {
    public static String IPHONE_CARIDS = "7863,7864";
    public static final int NO_RESOURCE_ID = -1;
    private static int a;

    static {
        int i = 2;
        a = 2;
        if (DensityUtil.getScreenWidth() >= 1080) {
            i = 3;
        }
        a = i;
    }

    public static CarBean getCarType(String str, String str2) {
        CarBean carBean = new CarBean();
        try {
            switch (Integer.parseInt(str)) {
                case 7551:
                    carBean.setCar(R.drawable.rooms_third_bike);
                    carBean.setCarName("单车座驾");
                    carBean.setRid(str2);
                    return carBean;
                case 7576:
                    carBean.setCar(R.drawable.rooms_third_bugattiveyron);
                    carBean.setCarLogo(R.drawable.rooms_third_bugattiveyron_logo);
                    carBean.setCarName("布加迪威航");
                    carBean.setRid(str2);
                    return carBean;
                case 7577:
                    carBean.setCar(R.drawable.rooms_third_lamborghini);
                    carBean.setCarLogo(R.drawable.rooms_third_lamborghini_logo);
                    carBean.setCarName("兰博基尼");
                    carBean.setRid(str2);
                    return carBean;
                case 7578:
                    carBean.setCar(R.drawable.rooms_third_ferrari);
                    carBean.setCarLogo(R.drawable.rooms_third_ferrari_logo);
                    carBean.setCarName("法拉利");
                    carBean.setRid(str2);
                    return carBean;
                case 7579:
                    carBean.setCar(R.drawable.rooms_third_astonmatin);
                    carBean.setCarLogo(R.drawable.rooms_third_astonmatin_logo);
                    carBean.setCarName("阿斯顿马丁");
                    carBean.setRid(str2);
                    return carBean;
                case 7580:
                    carBean.setCar(R.drawable.rooms_third_landrover);
                    carBean.setCarLogo(R.drawable.rooms_third_landrover_logo);
                    carBean.setCarName("路虎揽胜");
                    carBean.setRid(str2);
                    return carBean;
                case 7581:
                    carBean.setCar(R.drawable.rooms_third_volvo);
                    carBean.setCarLogo(R.drawable.rooms_third_volvo_logo);
                    carBean.setCarName("沃尔沃XC90");
                    carBean.setRid(str2);
                    return carBean;
                case 7582:
                    carBean.setCar(R.drawable.rooms_third_gallop);
                    carBean.setCarLogo(R.drawable.rooms_third_gallop_logo);
                    carBean.setCarName("奔驰E");
                    carBean.setRid(str2);
                    return carBean;
                case 7583:
                    carBean.setCar(R.drawable.rooms_third_lexus);
                    carBean.setCarLogo(R.drawable.rooms_third_lexus_logo);
                    carBean.setCarName("雷克萨斯ES");
                    carBean.setRid(str2);
                    return carBean;
                case 7584:
                    carBean.setCar(R.drawable.rooms_third_camry);
                    carBean.setCarLogo(R.drawable.rooms_third_camry_logo);
                    carBean.setCarName("凯美瑞");
                    carBean.setRid(str2);
                    return carBean;
                case 7585:
                    carBean.setCar(R.drawable.rooms_third_mazda);
                    carBean.setCarLogo(R.drawable.rooms_third_mazda_logo);
                    carBean.setCarName("马自达");
                    carBean.setRid(str2);
                    return carBean;
                case 7586:
                    carBean.setCar(R.drawable.rooms_third_hondafit);
                    carBean.setCarLogo(R.drawable.rooms_third_hondafit_logo);
                    carBean.setCarName("飞度");
                    carBean.setRid(str2);
                    return carBean;
                case 7587:
                    carBean.setCar(R.drawable.rooms_third_peugeot307);
                    carBean.setCarLogo(R.drawable.rooms_third_peugeot307_logo);
                    carBean.setCarName("标志307");
                    carBean.setRid(str2);
                    return carBean;
                case 7863:
                    carBean.setCar(R.drawable.rooms_third_bwmi8);
                    carBean.setCarLogo(R.drawable.rooms_third_bmw_logo);
                    carBean.setCarName("宝马i8");
                    carBean.setRid(str2);
                    return carBean;
                case 7864:
                    carBean.setCar(R.drawable.rooms_third_tesla);
                    carBean.setCarLogo(R.drawable.rooms_third_tesla_logo);
                    carBean.setCarName("特斯拉");
                    carBean.setRid(str2);
                    return carBean;
                case 7875:
                    carBean.setCar(R.drawable.rooms_third_optimus_prime);
                    carBean.setCarLarge(R.drawable.rooms_third_optimus_prime_large);
                    carBean.setCarLogo(R.drawable.rooms_third_optimus_prime_logo);
                    carBean.setCarLogoLarge(R.drawable.rooms_third_optimus_prime_logo_large);
                    carBean.setCarName("彼得比尔特389");
                    carBean.setRid(str2);
                    return carBean;
                case 7876:
                    carBean.setCar(R.drawable.rooms_third_bumblebee);
                    carBean.setCarLarge(R.drawable.rooms_third_bumblebee_large);
                    carBean.setCarLogo(R.drawable.rooms_third_bumblebee_logo);
                    carBean.setCarLogoLarge(R.drawable.rooms_third_bumblebee_logo_large);
                    carBean.setCarName("科迈罗");
                    carBean.setRid(str2);
                    return carBean;
                case 7894:
                    carBean.setCar(R.drawable.rooms_third_skateboard_shoes);
                    carBean.setCarName("滑板鞋");
                    carBean.setRid(str2);
                    return carBean;
                case 7904:
                    carBean.setCar(R.drawable.rooms_third_carpet);
                    carBean.setCarName("波斯飞毯座驾");
                    carBean.setRid(str2);
                    return carBean;
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getPropImageResourceByPropId(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            switch (Integer.valueOf(str).intValue()) {
                case 7104:
                    return R.drawable.rooms_third_prop_violet_vip;
                case 7105:
                    return R.drawable.rooms_third_prop_yellow_vip;
                case 7107:
                    return R.drawable.rooms_third_prop_suiyitie;
                case 7108:
                    return R.drawable.rooms_third_prop_suiyishuo;
                case 7109:
                    return R.drawable.rooms_third_prop_suiyijin;
                case 7110:
                    return R.drawable.rooms_third_prop_yinshenfu;
                case 7116:
                    return R.drawable.rooms_flash_satrt_1;
                case 7117:
                    return R.drawable.rooms_flash_satrt_2;
                case 7118:
                    return R.drawable.rooms_flash_satrt_3;
                case 7119:
                    return R.drawable.rooms_flash_satrt_4;
                case 7120:
                    return R.drawable.rooms_flash_satrt_5;
                case 7122:
                    return R.drawable.rooms_third_prop_lovemanager;
                case 7559:
                    return R.drawable.rooms_third_prop_green_card;
                case GiftIdStrs.SILVER_GUARD /*7569*/:
                    return R.drawable.rooms_third_prop_silver_guard;
                case GiftIdStrs.GOLD_GUARD /*7570*/:
                    return R.drawable.rooms_third_prop_gold_guard;
                case 7827:
                    return R.drawable.rooms_third_prop_yellow;
                case 7828:
                    return R.drawable.rooms_third_prop_red;
                case 7829:
                    return R.drawable.rooms_third_prop_violet;
                case 7858:
                    return R.drawable.rooms_third_music_talent_max;
                case 7859:
                    return R.drawable.rooms_third_prop_yellow_card;
                default:
                    return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getBean6ImageResourceByPosition(int i) {
        switch (i) {
            case 0:
                return R.drawable.rooms_third_bean6_50;
            case 1:
                return R.drawable.rooms_third_bean6_100;
            case 2:
                return R.drawable.rooms_third_bean6_200;
            case 3:
                return R.drawable.rooms_third_bean6_500;
            case 4:
                return R.drawable.rooms_third_bean6_1000;
            case 5:
                return R.drawable.rooms_third_bean6_5000;
            case 6:
                return R.drawable.rooms_third_bean6_more;
            default:
                if (i > 6) {
                    return R.drawable.rooms_third_bean6_more;
                }
                return -1;
        }
    }

    public static int getStarLevelImageResource(int i) {
        if (i == 0) {
            return R.drawable.rooms_third_anchor_level_0;
        }
        if (i == 1) {
            return R.drawable.rooms_third_anchor_level_1;
        }
        if (i == 2) {
            return R.drawable.rooms_third_anchor_level_2;
        }
        if (i == 3) {
            return R.drawable.rooms_third_anchor_level_3;
        }
        if (i == 4) {
            return R.drawable.rooms_third_anchor_level_4;
        }
        if (i == 5) {
            return R.drawable.rooms_third_anchor_level_5;
        }
        if (i == 6) {
            return R.drawable.rooms_third_anchor_level_6;
        }
        if (i == 7) {
            return R.drawable.rooms_third_anchor_level_7;
        }
        if (i == 8) {
            return R.drawable.rooms_third_anchor_level_8;
        }
        if (i == 9) {
            return R.drawable.rooms_third_anchor_level_9;
        }
        if (i == 10) {
            return R.drawable.rooms_third_anchor_level_10;
        }
        if (i == 11) {
            return R.drawable.rooms_third_anchor_level_11;
        }
        if (i == 12) {
            return R.drawable.rooms_third_anchor_level_12;
        }
        if (i == 13) {
            return R.drawable.rooms_third_anchor_level_13;
        }
        if (i == 14) {
            return R.drawable.rooms_third_anchor_level_14;
        }
        if (i == 15) {
            return R.drawable.rooms_third_anchor_level_15;
        }
        if (i == 16) {
            return R.drawable.rooms_third_anchor_level_16;
        }
        if (i == 17) {
            return R.drawable.rooms_third_anchor_level_17;
        }
        if (i == 18) {
            return R.drawable.rooms_third_anchor_level_18;
        }
        if (i == 19) {
            return R.drawable.rooms_third_anchor_level_19;
        }
        if (i == 20) {
            return R.drawable.rooms_third_anchor_level_20;
        }
        if (i == 21) {
            return R.drawable.rooms_third_anchor_level_21;
        }
        if (i == 22) {
            return R.drawable.rooms_third_anchor_level_22;
        }
        if (i == 23) {
            return R.drawable.rooms_third_anchor_level_23;
        }
        if (i == 24) {
            return R.drawable.rooms_third_anchor_level_24;
        }
        if (i == 25) {
            return R.drawable.rooms_third_anchor_level_25;
        }
        if (i == 26) {
            return R.drawable.rooms_third_anchor_level_26;
        }
        if (i == 27) {
            return R.drawable.rooms_third_anchor_level_27;
        }
        if (i == 28) {
            return R.drawable.rooms_third_anchor_level_28;
        }
        if (i == 29) {
            return R.drawable.rooms_third_anchor_level_29;
        }
        if (i == 30) {
            return R.drawable.rooms_third_anchor_level_30;
        }
        if (i == 31) {
            return R.drawable.rooms_third_anchor_level_31;
        }
        if (i == 32) {
            return R.drawable.rooms_third_anchor_level_32;
        }
        if (i == 33) {
            return R.drawable.rooms_third_anchor_level_33;
        }
        if (i == 34) {
            return R.drawable.rooms_third_anchor_level_34;
        }
        if (i == 35) {
            return R.drawable.rooms_third_anchor_level_35;
        }
        if (i == 36) {
            return R.drawable.rooms_third_anchor_level_36;
        }
        if (i == 37) {
            return R.drawable.rooms_third_anchor_level_37;
        }
        if (i == 38) {
            return R.drawable.rooms_third_anchor_level_38;
        }
        if (i == 39) {
            return R.drawable.rooms_third_anchor_level_39;
        }
        if (i == 40) {
            return R.drawable.rooms_third_anchor_level_40;
        }
        if (i == 41) {
            return R.drawable.rooms_third_anchor_level_41;
        }
        if (i == 42) {
            return R.drawable.rooms_third_anchor_level_42;
        }
        if (i == 43) {
            return R.drawable.rooms_third_anchor_level_43;
        }
        if (i == 44) {
            return R.drawable.rooms_third_anchor_level_44;
        }
        if (i == 45) {
            return R.drawable.rooms_fourth_anchor_level_45;
        }
        if (i == 46) {
            return R.drawable.rooms_fourth_anchor_level_46;
        }
        if (i == 47) {
            return R.drawable.rooms_fourth_anchor_level_47;
        }
        if (i == 48) {
            return R.drawable.rooms_fourth_anchor_level_48;
        }
        if (i == 49) {
            return R.drawable.rooms_fourth_anchor_level_49;
        }
        if (i == 50) {
            return R.drawable.rooms_fourth_anchor_level_50;
        }
        if (i == 51) {
            return R.drawable.rooms_fourth_anchor_level_51;
        }
        if (i == 52) {
            return R.drawable.rooms_fourth_anchor_level_52;
        }
        if (i == 53) {
            return R.drawable.rooms_fourth_anchor_level_53;
        }
        if (i == 54) {
            return R.drawable.rooms_fourth_anchor_level_54;
        }
        if (i == 55) {
            return R.drawable.rooms_fourth_anchor_level_55;
        }
        if (i == 56) {
            return R.drawable.rooms_fourth_anchor_level_56;
        }
        if (i == 57) {
            return R.drawable.rooms_fourth_anchor_level_57;
        }
        if (i == 58) {
            return R.drawable.rooms_fourth_anchor_level_58;
        }
        if (i == 59) {
            return R.drawable.rooms_fourth_anchor_level_59;
        }
        if (i == 60) {
            return R.drawable.rooms_five_anchor_level_60;
        }
        if (i == 61) {
            return R.drawable.rooms_five_anchor_level_61;
        }
        if (i == 62) {
            return R.drawable.rooms_five_anchor_level_62;
        }
        if (i == 63) {
            return R.drawable.rooms_five_anchor_level_63;
        }
        if (i == 64) {
            return R.drawable.rooms_five_anchor_level_64;
        }
        if (i == 65) {
            return R.drawable.rooms_five_anchor_level_65;
        }
        if (i == 66) {
            return R.drawable.rooms_five_anchor_level_66;
        }
        if (i == 67) {
            return R.drawable.rooms_five_anchor_level_67;
        }
        if (i == 68) {
            return R.drawable.rooms_five_anchor_level_68;
        }
        if (i == 69) {
            return R.drawable.rooms_five_anchor_level_69;
        }
        return -1;
    }

    public static String getCustomWealthRankImg(String str) {
        LogUtils.d("DrawableResourceUtils", "scale: " + a);
        return UrlStrs.GOD_USER_RANK_IMG + "?uid=" + str + "&from=client&scale=" + a;
    }

    public static String getCustomNextWealthRankImg(String str) {
        return UrlStrs.GOD_USER_NEXT_IMG + "?uid=" + str + "&from=client&scale=" + a;
    }

    public static String getFortuneLevelUrl(String str, int i, boolean z) {
        return getFortuneLevelUrl(str, i, z, a);
    }

    public static String getFortuneLevelUrl(String str, int i) {
        return getFortuneLevelUrl(str, i, a);
    }

    public static String getFortuneLevelUrl(String str, int i, boolean z, int i2) {
        String str2;
        if (z) {
            str2 = UrlStrs.WEALTH_LEVEL_PIC + "?uid=" + str + "&from=client&scale=" + i2;
        } else if (i >= 27) {
            str2 = UrlStrs.WEALTH_LEVEL_PIC_NEXT + "?uid=" + str + "&from=client&scale=" + i2;
        } else {
            str2 = "drawable://" + FortuneUtils.getFortuneLevelUrl(String.valueOf(i));
        }
        LogUtils.d("DrawableResourceUtils", "picUrl: " + str2);
        return str2;
    }

    public static String getFortuneLevelUrl(String str, int i, int i2) {
        return getFortuneLevelUrl(str, i, false, i2);
    }
}
