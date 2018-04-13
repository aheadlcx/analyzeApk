package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.SubscriberMethod;

public class SimpleSubscriberInfo extends AbstractSubscriberInfo {
    private final SubscriberMethodInfo[] a;

    public SimpleSubscriberInfo(Class cls, boolean z, SubscriberMethodInfo[] subscriberMethodInfoArr) {
        super(cls, null, z);
        this.a = subscriberMethodInfoArr;
    }

    public synchronized SubscriberMethod[] getSubscriberMethods() {
        SubscriberMethod[] subscriberMethodArr;
        int length = this.a.length;
        subscriberMethodArr = new SubscriberMethod[length];
        for (int i = 0; i < length; i++) {
            SubscriberMethodInfo subscriberMethodInfo = this.a[i];
            subscriberMethodArr[i] = a(subscriberMethodInfo.a, subscriberMethodInfo.c, subscriberMethodInfo.b, subscriberMethodInfo.d, subscriberMethodInfo.e);
        }
        return subscriberMethodArr;
    }
}
