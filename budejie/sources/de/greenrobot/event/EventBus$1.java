package de.greenrobot.event;

class EventBus$1 extends ThreadLocal<EventBus$PostingThreadState> {
    final /* synthetic */ EventBus this$0;

    EventBus$1(EventBus eventBus) {
        this.this$0 = eventBus;
    }

    protected EventBus$PostingThreadState initialValue() {
        return new EventBus$PostingThreadState();
    }
}
