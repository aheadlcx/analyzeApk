package com.facebook.fresco.animation.backend;

class AnimationBackendDelegateWithInactivityCheck$1 implements Runnable {
    final /* synthetic */ AnimationBackendDelegateWithInactivityCheck this$0;

    AnimationBackendDelegateWithInactivityCheck$1(AnimationBackendDelegateWithInactivityCheck animationBackendDelegateWithInactivityCheck) {
        this.this$0 = animationBackendDelegateWithInactivityCheck;
    }

    public void run() {
        synchronized (this.this$0) {
            AnimationBackendDelegateWithInactivityCheck.access$002(this.this$0, false);
            if (!AnimationBackendDelegateWithInactivityCheck.access$100(this.this$0)) {
                AnimationBackendDelegateWithInactivityCheck.access$300(this.this$0);
            } else if (AnimationBackendDelegateWithInactivityCheck.access$200(this.this$0) != null) {
                AnimationBackendDelegateWithInactivityCheck.access$200(this.this$0).onInactive();
            }
        }
    }
}
