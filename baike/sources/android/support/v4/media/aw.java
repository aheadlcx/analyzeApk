package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.v4.media.VolumeProviderCompatApi21.Delegate;

final class aw extends VolumeProvider {
    final /* synthetic */ Delegate a;

    aw(int i, int i2, int i3, Delegate delegate) {
        this.a = delegate;
        super(i, i2, i3);
    }

    public void onSetVolumeTo(int i) {
        this.a.onSetVolumeTo(i);
    }

    public void onAdjustVolume(int i) {
        this.a.onAdjustVolume(i);
    }
}
