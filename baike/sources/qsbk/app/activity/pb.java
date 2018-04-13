package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.ImageInfo;

class pb implements OnClickListener {
    final /* synthetic */ ImagesPickerActivity a;

    pb(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onClick(View view) {
        int size = this.a.e.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((ImageInfo) this.a.e.get(i)).url;
        }
        Intent intent = new Intent();
        intent.putExtra("paths", this.a.e);
        this.a.setResult(-1, intent);
        this.a.finish();
    }
}
