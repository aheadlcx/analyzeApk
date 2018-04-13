package cn.v6.sixrooms.socket;

import java.util.EventListener;

public interface ReceiveListener extends EventListener {
    void onReceive(ReceiveEvent receiveEvent);
}
