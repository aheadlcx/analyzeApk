package okhttp3;

import okhttp3.EventListener.Factory;

final class k implements Factory {
    final /* synthetic */ EventListener a;

    k(EventListener eventListener) {
        this.a = eventListener;
    }

    public EventListener create(Call call) {
        return this.a;
    }
}
