package qsbk.app.im.emotion;

import qsbk.app.R;

@Deprecated
public class QiubaiEmotionProvider implements EmotionProvider {
    public static final String[] KEYS = new String[]{"qb_00", "qb_01", "qb_02", "qb_03", "qb_04", "qb_05", "qb_06", "qb_07", "qb_08", "qb_09", "qb_10", "qb_11", "qb_12", "qb_13", "qb_14", "qb_15", "qb_16", "qb_17", "qb_18", "qb_19", "qb_20", "qb_21", "qb_22", "qb_23", "qb_24", "qb_25", "qb_26", "qb_27", "qb_28", "qb_29", "qb_30", "qb_31", "qb_32", "qb_33", "qb_34", "qb_35", "qb_36", "qb_37", "qb_38", "qb_39"};
    public static final String[] NAMES = new String[]{"[嘿嘿]", "[大哭]", "[哀怨]", "[哈哈]", "[色色的]", "[如花]", "[生气了]", "[心情不好]", "[惊恐]", "[诶~]", "[流鼻血]", "[惊讶]", "[惊呆了]", "[臭美]", "[吐了]", "[狂躁]", "[晚安睡觉]", "[诶不错噢]", "[笑着流泪]", "[撞到玻璃]", "[汗颜]", "[鼻孔有点痒]", "[再见咯]", "[中毒了]", "[传情]", "[可爱多]", "[面瘫神]", "[骄傲]", "[窃喜]", "[奸笑]", "[坏主意]", "[羞涩]", "[幻想]", "[呵呵]", "[难道说]", "[汗]", "[我考虑下]", "[楚楚可怜]", "[晕了]", "[好舒服]"};
    public static final String NAMESPACE = "qb";
    public static final int[] RESOURCE_IDS = new int[]{R.drawable.qb_00, R.drawable.qb_01, R.drawable.qb_02, R.drawable.qb_03, R.drawable.qb_04, R.drawable.qb_05, R.drawable.qb_06, R.drawable.qb_07, R.drawable.qb_08, R.drawable.qb_09, R.drawable.qb_10, R.drawable.qb_11, R.drawable.qb_12, R.drawable.qb_13, R.drawable.qb_14, R.drawable.qb_15, R.drawable.qb_16, R.drawable.qb_17, R.drawable.qb_18, R.drawable.qb_19, R.drawable.qb_20, R.drawable.qb_21, R.drawable.qb_22, R.drawable.qb_23, R.drawable.qb_24, R.drawable.qb_25, R.drawable.qb_26, R.drawable.qb_27, R.drawable.qb_28, R.drawable.qb_29, R.drawable.qb_30, R.drawable.qb_31, R.drawable.qb_32, R.drawable.qb_33, R.drawable.qb_34, R.drawable.qb_35, R.drawable.qb_36, R.drawable.qb_37, R.drawable.qb_38, R.drawable.qb_39};
    public static final String[] URLS = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    public String[] getNames() {
        return NAMES;
    }

    public String[] getKeys() {
        return KEYS;
    }

    public int[] getLocalResourceIds() {
        return RESOURCE_IDS;
    }

    public String getNameSpace() {
        return NAMESPACE;
    }

    public int getWidth() {
        return 261;
    }

    public int getHeight() {
        return 261;
    }

    public String[] getUrls() {
        return URLS;
    }
}
