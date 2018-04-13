package cn.v6.sixrooms.room.utils;

import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.socket.IM.IMSocketUtil;
import cn.v6.sixrooms.socket.common.SocketUtil;
import java.util.ArrayList;

final class d extends ArrayList<String> {
    d() {
        add(SocketUtil.FLAG_ON_FULL);
        add("415");
        add(IMSocketUtil.INNER_TYPE_ID_USER_NO_LOGIN);
        add("1202");
        add(GiftIdStrs.SUPER_FIREWORKS);
        add("124");
        add("431");
        add("134");
        add("135");
    }
}
