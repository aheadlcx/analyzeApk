package org.greenrobot.eventbus;

class c extends ThreadLocal<a> {
    final /* synthetic */ EventBus a;

    c(EventBus eventBus) {
        this.a = eventBus;
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }

    protected a a() {
        return new a();
    }
}
