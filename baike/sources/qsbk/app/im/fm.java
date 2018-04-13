package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.io.File;
import qsbk.app.utils.DeviceUtils;

class fm implements OnClickListener {
    final /* synthetic */ LatestUsedCollectionData a;
    final /* synthetic */ IMChatBaseActivityEx b;

    fm(IMChatBaseActivityEx iMChatBaseActivityEx, LatestUsedCollectionData latestUsedCollectionData) {
        this.b = iMChatBaseActivityEx;
        this.a = latestUsedCollectionData;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        File file;
        if (this.a.type == 1) {
            file = new File(DeviceUtils.getCollectSDPath() + File.separator + this.a.collectImageDomain.url);
            if (file.exists()) {
                file.delete();
            }
        } else if (this.a.type == 3) {
            file = new File(this.a.collectImageLocal.localUrl);
            if (file.exists()) {
                file.delete();
            }
        }
        this.b.R.delete(this.a.id);
        this.b.aa.getAll();
        this.b.hideEmojiAfterDeleteItemOrUploaded();
        dialogInterface.dismiss();
    }
}
