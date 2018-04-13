package cn.v6.sixrooms.ui.phone;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import java.io.File;

final class c implements OnClickListener {
    final /* synthetic */ BaseWebviewActivity a;

    c(BaseWebviewActivity baseWebviewActivity) {
        this.a = baseWebviewActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            Intent.createChooser(intent, "Image Chooser");
            this.a.startActivityForResult(intent, 1);
            return;
        }
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/temp1.jpg";
        this.a.tempFile = new File(str);
        if (this.a.tempFile.exists()) {
            this.a.tempFile.delete();
        }
        intent.putExtra("output", Uri.fromFile(this.a.tempFile));
        this.a.startActivityForResult(intent, 2);
    }
}
