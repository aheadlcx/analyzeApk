package android.support.v4.media;

import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private final int a;
    private final int b;
    private int c;
    private Callback d;
    private Object e;

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ControlType {
    }

    public VolumeProviderCompat(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public final int getCurrentVolume() {
        return this.c;
    }

    public final int getVolumeControl() {
        return this.a;
    }

    public final int getMaxVolume() {
        return this.b;
    }

    public final void setCurrentVolume(int i) {
        this.c = i;
        Object volumeProvider = getVolumeProvider();
        if (volumeProvider != null && VERSION.SDK_INT >= 21) {
            VolumeProviderCompatApi21.setCurrentVolume(volumeProvider, i);
        }
        if (this.d != null) {
            this.d.onVolumeChanged(this);
        }
    }

    public void onSetVolumeTo(int i) {
    }

    public void onAdjustVolume(int i) {
    }

    public void setCallback(Callback callback) {
        this.d = callback;
    }

    public Object getVolumeProvider() {
        if (this.e == null && VERSION.SDK_INT >= 21) {
            this.e = VolumeProviderCompatApi21.createVolumeProvider(this.a, this.b, this.c, new av(this));
        }
        return this.e;
    }
}
