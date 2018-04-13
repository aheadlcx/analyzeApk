package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompat.Callback;

class e extends Callback {
    final /* synthetic */ e a;

    e(e eVar) {
        this.a = eVar;
    }

    public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
        if (this.a.w == volumeProviderCompat) {
            this.a.a(new ParcelableVolumeInfo(this.a.u, this.a.v, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
        }
    }
}
