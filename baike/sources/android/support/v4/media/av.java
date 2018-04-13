package android.support.v4.media;

import android.support.v4.media.VolumeProviderCompatApi21.Delegate;

class av implements Delegate {
    final /* synthetic */ VolumeProviderCompat a;

    av(VolumeProviderCompat volumeProviderCompat) {
        this.a = volumeProviderCompat;
    }

    public void onSetVolumeTo(int i) {
        this.a.onSetVolumeTo(i);
    }

    public void onAdjustVolume(int i) {
        this.a.onAdjustVolume(i);
    }
}
