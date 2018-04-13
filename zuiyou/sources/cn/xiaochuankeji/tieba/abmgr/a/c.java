package cn.xiaochuankeji.tieba.abmgr.a;

import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;

public class c {
    public static void a(String str, int i) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 2009461527:
                if (str.equals("ab_short_refresh_btn")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                a(i);
                return;
            default:
                return;
        }
    }

    private static void a(int i) {
        switch (i) {
            case 0:
                b.a().a(Requirement.HOME_REFRESH, RequireStyle.HOME_REFRESH_GONE);
                return;
            case 1:
                b.a().a(Requirement.HOME_REFRESH, RequireStyle.HOME_REFRESH_VISIBLE);
                return;
            default:
                return;
        }
    }
}
