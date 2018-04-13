package de.greenrobot.event;

import java.util.List;

interface EventBus$PostCallback {
    void onPostCompleted(List<SubscriberExceptionEvent> list);
}
