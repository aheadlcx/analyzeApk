package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;

public abstract class ErrorDialogFragmentFactory<T> {
    protected final ErrorDialogConfig a;

    @TargetApi(11)
    public static class Honeycomb extends ErrorDialogFragmentFactory<Fragment> {
        protected /* synthetic */ Object a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            return d(throwableFailureEvent, bundle);
        }

        public Honeycomb(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        protected Fragment d(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            Fragment honeycomb = new org.greenrobot.eventbus.util.ErrorDialogFragments.Honeycomb();
            honeycomb.setArguments(bundle);
            return honeycomb;
        }
    }

    public static class Support extends ErrorDialogFragmentFactory<android.support.v4.app.Fragment> {
        protected /* synthetic */ Object a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            return d(throwableFailureEvent, bundle);
        }

        public Support(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        protected android.support.v4.app.Fragment d(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            android.support.v4.app.Fragment support = new org.greenrobot.eventbus.util.ErrorDialogFragments.Support();
            support.setArguments(bundle);
            return support;
        }
    }

    protected abstract T a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle);

    protected ErrorDialogFragmentFactory(ErrorDialogConfig errorDialogConfig) {
        this.a = errorDialogConfig;
    }

    protected T a(ThrowableFailureEvent throwableFailureEvent, boolean z, Bundle bundle) {
        if (throwableFailureEvent.isSuppressErrorUi()) {
            return null;
        }
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = (Bundle) bundle.clone();
        } else {
            bundle2 = new Bundle();
        }
        if (!bundle2.containsKey(ErrorDialogManager.KEY_TITLE)) {
            bundle2.putString(ErrorDialogManager.KEY_TITLE, b(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey(ErrorDialogManager.KEY_MESSAGE)) {
            bundle2.putString(ErrorDialogManager.KEY_MESSAGE, c(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG)) {
            bundle2.putBoolean(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG, z);
        }
        if (!(bundle2.containsKey(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE) || this.a.i == null)) {
            bundle2.putSerializable(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE, this.a.i);
        }
        if (!(bundle2.containsKey(ErrorDialogManager.KEY_ICON_ID) || this.a.h == 0)) {
            bundle2.putInt(ErrorDialogManager.KEY_ICON_ID, this.a.h);
        }
        return a(throwableFailureEvent, bundle2);
    }

    protected String b(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.a.a.getString(this.a.b);
    }

    protected String c(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.a.a.getString(this.a.getMessageIdForThrowable(throwableFailureEvent.a));
    }
}
