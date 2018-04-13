package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.socket.common.SocketUtil;

public class ChatStyleUtils {
    public static RoommsgBean chatStyleHandle(RoommsgBean roommsgBean) {
        switch (Integer.parseInt(roommsgBean.getTypeID())) {
            case -2:
            case 102:
                if (!roommsgBean.isRankFlag()) {
                    roommsgBean.setChatStyle(3);
                    break;
                }
                roommsgBean.setChatStyle(4);
                break;
            case 101:
                roommsgBean.setChatStyle(2);
                break;
            case 123:
                roommsgBean.setChatStyle(1);
                break;
            case 135:
                roommsgBean.setChatStyle(0);
                break;
            case 201:
                if (Integer.parseInt(roommsgBean.getFid()) != 0) {
                    roommsgBean.setChatStyle(6);
                    break;
                }
                roommsgBean.setChatStyle(7);
                break;
            case 409:
                roommsgBean.setChatStyle(9);
                break;
            case SocketUtil.TYPEID_1304 /*1304*/:
                roommsgBean.setChatStyle(8);
                break;
            case SocketUtil.TYPEID_1309 /*1309*/:
                roommsgBean.setChatStyle(10);
                break;
        }
        return roommsgBean;
    }
}
