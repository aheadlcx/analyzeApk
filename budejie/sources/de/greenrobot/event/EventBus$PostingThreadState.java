package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;

final class EventBus$PostingThreadState {
    boolean canceled;
    Object event;
    final List<Object> eventQueue = new ArrayList();
    boolean isMainThread;
    boolean isPosting;
    Subscription subscription;

    EventBus$PostingThreadState() {
    }
}
