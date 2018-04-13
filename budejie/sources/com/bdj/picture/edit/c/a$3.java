package com.bdj.picture.edit.c;

import android.content.Intent;
import com.bdj.picture.edit.d.c.a;

class a$3 implements a {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void a(String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("imagePath", str);
        intent.putExtra("stickerIds", str2);
        this.a.getActivity().setResult(-1, intent);
        this.a.getActivity().finish();
    }
}
