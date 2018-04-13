package qsbk.app.provider;

public class Menus {
    public static final int[] ContentMenuTypes = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1};
    public static final int TYPE_DIVIDER_ITEM = 0;
    public static final int TYPE_MENU_ITEM = 1;
    public static final int[] UseSettingMenuTypes = new int[]{1, 1, 0, 1, 0, 1, 1, 1};
    public static final String[] UserSettingMenus = new String[]{"屏幕亮度", "图片加载方式", "", "我的帐号", "", "意见反馈", "新版本检测", "关于糗百"};
    public static final int[] menusIcon = new int[0];

    public class MenuOrder {
        public static final int ABOUT = 7;
        public static final int BRIGHTNESS_SETTING = 0;
        public static final int FEEDBACK = 5;
        public static final int IMAGE_LOAD = 1;
        public static final int USER_SETTING_ACTIVITY = 3;
        public static final int VERSION_CHECK = 6;
        final /* synthetic */ Menus a;

        public MenuOrder(Menus menus) {
            this.a = menus;
        }
    }
}
