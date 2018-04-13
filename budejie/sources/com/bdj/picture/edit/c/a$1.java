package com.bdj.picture.edit.c;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.bdj.picture.edit.EditPictureActivity;

class a$1 implements OnClickListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        Log.d("editFrameIv", "editFrameIv.onClick() ");
        if (a.a(this.a, false)) {
            ((EditPictureActivity) this.a.getActivity()).a();
        }
    }
}
