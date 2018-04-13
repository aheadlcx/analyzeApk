package cn.v6.sixrooms.socket;

import cn.v6.sixrooms.socket.common.TcpCommand;
import java.util.EventObject;

public class ReceiveEvent extends EventObject {
    private static final long serialVersionUID = 7537330765566764653L;
    private TcpCommand a;

    public ReceiveEvent(Object obj) {
        super(obj);
    }

    public ReceiveEvent(Object obj, TcpCommand tcpCommand) {
        this(obj);
        this.a = tcpCommand;
    }

    public TcpCommand getRecCmd() {
        return this.a;
    }

    public void setRecCmd(TcpCommand tcpCommand) {
        this.a = tcpCommand;
    }
}
