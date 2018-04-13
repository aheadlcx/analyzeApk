package de.greenrobot.event;

/* synthetic */ class EventBus$2 {
    static final /* synthetic */ int[] $SwitchMap$de$greenrobot$event$ThreadMode = new int[ThreadMode.values().length];

    static {
        try {
            $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.PostThread.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.MainThread.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.BackgroundThread.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.Async.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
