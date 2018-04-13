package cn.tatagou.sdk.fragment;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;

class TtgMainFragment$2 implements f {
    final /* synthetic */ String a;
    final /* synthetic */ TtgMainFragment b;

    TtgMainFragment$2(TtgMainFragment ttgMainFragment, String str) {
        this.b = ttgMainFragment;
        this.a = str;
    }

    public void onFailure(e eVar, IOException iOException) {
        this.b.openMain(this.a);
    }

    public void onResponse(e eVar, aa aaVar) throws IOException {
        Bitmap decodeStream = BitmapFactory.decodeStream(aaVar.h().d());
        String uri = eVar.a().a().a().toString();
        if (decodeStream != null) {
            decodeStream.compress(CompressFormat.PNG, 100, this.b.getActivity().openFileOutput(uri.substring(uri.lastIndexOf("/") + 1, uri.length()), 0));
        }
        this.b.openMain(this.a);
    }
}
