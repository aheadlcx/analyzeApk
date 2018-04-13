package qsbk.app.core.widget;

import android.content.Context;
import android.widget.Button;
import qsbk.app.core.R;
import qsbk.app.core.utils.PictureGetHelper;

public class UserPicSelectDialog extends BaseDialog {
    private PictureGetHelper c;
    private Button d;
    private Button e;
    private Button f;

    public UserPicSelectDialog(Context context, PictureGetHelper pictureGetHelper) {
        super(context);
        this.c = pictureGetHelper;
    }

    protected int c() {
        return R.layout.view_avatar_edit_bottomsheet;
    }

    protected void d() {
        this.d = (Button) a(R.id.takephoto);
        this.e = (Button) a(R.id.album);
        this.f = (Button) a(R.id.sheet_bt_cancel);
    }

    protected void e() {
        this.d.setOnClickListener(new z(this));
        this.e.setOnClickListener(new aa(this));
        this.f.setOnClickListener(new ab(this));
    }

    protected int a() {
        return 80;
    }

    protected boolean g() {
        return false;
    }

    protected float f() {
        return 0.5f;
    }
}
