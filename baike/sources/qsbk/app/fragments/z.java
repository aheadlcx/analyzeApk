package qsbk.app.fragments;

import android.media.MediaScannerConnection;
import java.io.File;
import qsbk.app.utils.FileUtils.CallBack;

class z implements CallBack {
    final /* synthetic */ File a;
    final /* synthetic */ BrowseGIFVideoFragment b;

    z(BrowseGIFVideoFragment browseGIFVideoFragment, File file) {
        this.b = browseGIFVideoFragment;
        this.a = file;
    }

    public void onResult(boolean z) {
        this.b.f = new MediaScannerConnection(this.b.getActivity(), new aa(this));
        this.b.f.connect();
    }
}
