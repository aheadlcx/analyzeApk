package cn.v6.sixrooms.socket;

import cn.v6.sixrooms.socket.common.SocketUtil;

public class TcpLogger {
    protected static void info(Object obj) {
        out("信息：" + obj.toString());
    }

    protected static void error(Object obj) {
    }

    protected static void debug(Object obj) {
    }

    protected static void out(String str) {
        if (!str.endsWith(SocketUtil.CRLF) && !str.endsWith("\n")) {
            new StringBuilder().append(str).append(SocketUtil.CRLF);
        }
    }
}
