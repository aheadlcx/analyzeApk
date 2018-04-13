package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;

/* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[Event.values().length];
    static final /* synthetic */ int[] b = new int[State.values().length];

    static {
        try {
            b[State.INITIALIZED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[State.CREATED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[State.STARTED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[State.RESUMED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[State.DESTROYED.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[Event.ON_CREATE.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[Event.ON_STOP.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[Event.ON_START.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[Event.ON_PAUSE.ordinal()] = 4;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[Event.ON_RESUME.ordinal()] = 5;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[Event.ON_DESTROY.ordinal()] = 6;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[Event.ON_ANY.ordinal()] = 7;
        } catch (NoSuchFieldError e12) {
        }
    }
}
